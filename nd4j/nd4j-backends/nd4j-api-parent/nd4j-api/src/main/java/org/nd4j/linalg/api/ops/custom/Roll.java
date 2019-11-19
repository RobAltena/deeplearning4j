package org.nd4j.linalg.api.ops.custom;

import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class Roll extends DynamicCustomOp {

    public Roll(INDArray input, INDArray axes, INDArray shifts) {
        Preconditions.checkArgument(axes.rank() == shifts.rank(), "Roll: shifts and axes should be the same rank");
        Preconditions.checkArgument(axes.length() == shifts.length(), "Roll: shifts and axes should be the same length");
        addInputArgument(input, axes, shifts);
    }

    public Roll(INDArray input, int shift) {
        addInputArgument(input);
        addIArgument(shift);
    }

    @Override
    public String opName() {
        return "roll";
    }

    @Override
    public String tensorflowName() {
        return "Roll";
    }
}
