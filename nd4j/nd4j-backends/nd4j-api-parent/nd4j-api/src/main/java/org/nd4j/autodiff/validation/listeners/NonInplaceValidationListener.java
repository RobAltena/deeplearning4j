package org.nd4j.autodiff.validation.listeners;

import lombok.Getter;
import lombok.val;
import org.nd4j.autodiff.listeners.At;
import org.nd4j.autodiff.listeners.BaseListener;
import org.nd4j.autodiff.listeners.Operation;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.autodiff.samediff.internal.SameDiffOp;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;
import org.nd4j.linalg.api.ops.Op;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import org.nd4j.linalg.dataset.api.MultiDataSet;

public class NonInplaceValidationListener extends BaseListener {
    @Getter
    private static AtomicInteger useCounter = new AtomicInteger();
    @Getter
    private static AtomicInteger passCounter = new AtomicInteger();
    @Getter
    private static AtomicInteger failCounter = new AtomicInteger();

    protected INDArray[] opInputs;
    protected INDArray[] opInputsOrig;

    public NonInplaceValidationListener(){
        useCounter.getAndIncrement();
    }

    @Override
    public void preOpExecution(SameDiff sd, At at, SameDiffOp op) {
        if(op.getOp().isInPlace()){
            //Don't check inplace op
            return;
        }
        if(op.getOp() instanceof Op){
            Op o = (Op)op.getOp();
            if(o.x() == null){
                //No input op
                return;
            } else if(o.y() == null){
                opInputsOrig = new INDArray[]{o.x()};
                opInputs = new INDArray[]{o.x().dup()};
            } else {
                opInputsOrig = new INDArray[]{o.x(), o.y()};
                opInputs = new INDArray[]{o.x().dup(), o.y().dup()};
            }
        } else if(op.getOp() instanceof DynamicCustomOp){
            val arr = ((DynamicCustomOp) op.getOp()).inputArguments();
            opInputs = new INDArray[arr.size()];
            opInputsOrig = new INDArray[arr.length];
            for( int i=0; i<arr.size(); i++ ){
                opInputsOrig[i] = arr[i];
                opInputs[i] = arr.get(i).dup();
            }
        } else {
            throw new IllegalStateException("Unknown op type: " + op.getOp().getClass());
        }
    }

    @Override
    public void opExecution(SameDiff sd, At at, MultiDataSet batch, SameDiffOp op, INDArray[] outputs) {
        if(op.getOp().isInPlace()){
            //Don't check inplace op
            return;
        }

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (Throwable t){
            throw new RuntimeException(t);
        }
        for( int i=0; i<opInputs.length; i++ ){
            if(opInputs[i].isEmpty())
                continue;

            //Need to hash - to ensure zero changes to input array
            byte[] before = opInputs[i].data().asBytes();
            INDArray after = this.opInputsOrig[i];
            boolean dealloc = false;
            if(opInputs[i].ordering() != opInputsOrig[i].ordering() || Arrays.equals(opInputs[i].stride(), opInputsOrig[i].stride())
                    || opInputs[i].elementWiseStride() != opInputsOrig[i].elementWiseStride()){
                //Clone if required (otherwise fails for views etc)
                after = opInputsOrig[i].dup();
                dealloc = true;
            }
            byte[] afterB = after.data().asBytes();
            byte[] hash1 = md.digest(before);
            byte[] hash2 = md.digest(afterB);

            boolean eq = Arrays.equals(hash1, hash2);
            if(eq){
                passCounter.addAndGet(1);
            } else {
                failCounter.addAndGet(1);
            }

            Preconditions.checkState(eq, "Input array for non-inplace op was modified during execution " +
                    "for op %s - input %s", op.getOp().getClass(), i);

            //Deallocate:
            if(dealloc && after.closeable()){
                after.close();
            }
            if(opInputs[i].closeable()){
                opInputs[i].close();
            }
        }
    }

    @Override
    public boolean isActive(Operation operation) {
        return true;
    }
}
