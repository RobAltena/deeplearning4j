package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class AdjustHue extends DynamicCustomOp {
    public AdjustHue() {
        super();
    }

    public AdjustHue(INDArray in, double delta, INDArray out) {
        this(in, delta);
        outputArguments.add(out);
    }

    public AdjustHue(@NonNull INDArray in, double delta) {
        Preconditions.checkArgument(in.rank() >= 3,
                "AdjustSaturation: op expects rank of input array to be >= 3, but got %s instead", in.rank());
        Preconditions.checkArgument(-1.0 <= delta && delta <= 1.0, "AdjustHue: parameter delta must be within [-1, 1] interval," +
                " but got %s instead", delta);
        inputArguments.add(in);

        addTArgument(delta);
    }

    public AdjustHue(@NonNull SameDiff sameDiff, @NonNull SDVariable in, @NonNull SDVariable factor) {
        super(sameDiff,new SDVariable[]{in,factor});
    }

    @Override
    public String opName() {
        return "adjust_hue";
    }

    @Override
    public String tensorflowName() {
        return "AdjustHue";
    }
}
