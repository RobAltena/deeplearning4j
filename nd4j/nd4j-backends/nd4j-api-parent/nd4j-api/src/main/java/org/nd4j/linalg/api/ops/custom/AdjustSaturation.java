package org.nd4j.linalg.api.ops.custom;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class AdjustSaturation extends DynamicCustomOp {
    public AdjustSaturation(INDArray in, double factor, INDArray out) {
        Preconditions.checkArgument(in.rank() >= 3,
                String.format("AdjustSaturation: op expects rank of input array to be >= 3, but got %d instead", in.rank()));
        inputArguments.add(in);
        outputArguments.add(out);

        addTArgument(factor);
    }

    public AdjustSaturation(SameDiff sameDiff, SDVariable in, SDVariable factor) {
        super(sameDiff,new SDVariable[]{in,factor});
    }

    @Override
    public String opName() {
        return "adjust_saturation";
    }

    @Override
    public String tensorflowName() {
        return "AdjustSaturation";
    }
}
