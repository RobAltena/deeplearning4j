package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class ToggleBits extends DynamicCustomOp {

    public ToggleBits() {}

    public ToggleBits(@NonNull INDArray input, INDArray output) {
        this(input);
        addOutputArgument(input);
    }

    public ToggleBits(@NonNull INDArray input) {
        addInputArgument(input);
    }

    public ToggleBits(@NonNull SameDiff sameDiff, @NonNull SDVariable input) {
        super("", sameDiff, new SDVariable[]{input});
    }

    @Override
    public String opName() {
        return "toggle_bits";
    }

    @Override
    public String tensorflowName() {
        return "Invert";
    }
}
