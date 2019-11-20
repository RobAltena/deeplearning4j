package org.nd4j.linalg.api.ops.custom;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class BetaInc extends DynamicCustomOp {

    public BetaInc() {
        super();
    }

    public BetaInc(INDArray a_input, INDArray b_input, INDArray x_input, INDArray output) {
        inputArguments.add(a_input);
        inputArguments.add(b_input);
        inputArguments.add(x_input);
        outputArguments.add(output);
    }

    public BetaInc(INDArray a_input, INDArray b_input, INDArray x_input) {
        inputArguments.add(a_input);
        inputArguments.add(b_input);
        inputArguments.add(x_input);
    }

    public BetaInc(SameDiff sameDiff, SDVariable a, SDVariable b, SDVariable x) {
        super(sameDiff, new SDVariable[]{a,b,x});
    }

    @Override
    public String opName() {
        return "betainc";
    }

    @Override
    public String tensorflowName() {
        return "Betainc";
    }
}
