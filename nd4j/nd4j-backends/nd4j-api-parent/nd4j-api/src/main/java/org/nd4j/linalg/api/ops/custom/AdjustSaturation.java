package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class AdjustSaturation extends DynamicCustomOp {

    public AdjustSaturation() {}

    public AdjustSaturation(@NonNull INDArray in, double factor, INDArray out) {
        this(in, factor);
        outputArguments.add(out);
    }

    public AdjustSaturation(@NonNull INDArray in, double factor) {
        Preconditions.checkArgument(in.rank() >= 3,
                "AdjustSaturation: op expects rank of input array to be >= 3, but got %s instead", in.rank());
        inputArguments.add(in);

        addTArgument(factor);
    }

    public AdjustSaturation(@NonNull SameDiff sameDiff, @NonNull SDVariable in, @NonNull SDVariable factor) {
        super(sameDiff, new SDVariable[]{in, factor});
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
