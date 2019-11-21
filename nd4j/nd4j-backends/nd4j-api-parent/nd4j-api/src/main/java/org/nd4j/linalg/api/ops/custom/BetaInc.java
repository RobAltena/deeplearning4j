package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class BetaInc extends DynamicCustomOp {

    public BetaInc() {
        super();
    }

    public BetaInc(@NonNull INDArray a_input, @NonNull INDArray b_input, @NonNull INDArray x_input,
                   @NonNull INDArray output) {
        inputArguments.add(a_input);
        inputArguments.add(b_input);
        inputArguments.add(x_input);
        outputArguments.add(output);
    }

    public BetaInc(@NonNull INDArray a_input, @NonNull INDArray b_input, @NonNull INDArray x_input) {
        inputArguments.add(a_input);
        inputArguments.add(b_input);
        inputArguments.add(x_input);
    }

    public BetaInc(@NonNull SameDiff sameDiff, @NonNull SDVariable a, @NonNull SDVariable b, @NonNull SDVariable x) {
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
