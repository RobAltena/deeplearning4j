package org.nd4j.linalg.api.ops.custom;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class ToggleBits extends DynamicCustomOp {

    public ToggleBits() {super();}

    public ToggleBits(INDArray input, INDArray output) {
        addInputArgument(input);
        addOutputArgument(input);
    }

    public ToggleBits(INDArray input) {
        addInputArgument(input);
    }

    public ToggleBits(SameDiff sameDiff, SDVariable input) {
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
