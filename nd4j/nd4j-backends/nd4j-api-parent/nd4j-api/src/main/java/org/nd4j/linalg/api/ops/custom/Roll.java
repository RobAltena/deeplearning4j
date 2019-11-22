package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class Roll extends DynamicCustomOp {

    public Roll() {}

    public Roll(@NonNull INDArray input, @NonNull INDArray axes, @NonNull INDArray shifts) {
        Preconditions.checkArgument(axes.rank() == shifts.rank(), "Roll: shifts and axes should be the same rank");
        Preconditions.checkArgument(axes.length() == shifts.length(), "Roll: shifts and axes should be the same length");
        addInputArgument(input, axes, shifts);
    }

    public Roll(@NonNull INDArray input, int shift) {
        addInputArgument(input);
        addIArgument(shift);
    }

    public Roll(@NonNull SameDiff sameDiff, @NonNull SDVariable input, @NonNull SDVariable shift) {
        super("", sameDiff, new SDVariable[]{input,shift});
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
