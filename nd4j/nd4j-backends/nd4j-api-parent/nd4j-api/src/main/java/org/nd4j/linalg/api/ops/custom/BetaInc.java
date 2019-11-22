package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class BetaInc extends DynamicCustomOp {

    public BetaInc() {}

    public BetaInc(@NonNull INDArray a_input, @NonNull INDArray b_input, @NonNull INDArray x_input,
                   INDArray output) {
        addInputArgument(a_input, b_input, x_input);
        addOutputArgument(output);
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
