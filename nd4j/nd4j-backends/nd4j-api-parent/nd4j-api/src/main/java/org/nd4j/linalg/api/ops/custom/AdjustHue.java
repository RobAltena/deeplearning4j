package org.nd4j.linalg.api.ops.custom;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class AdjustHue extends DynamicCustomOp {
    public AdjustHue(INDArray in, double delta, INDArray out) {
        this(in, delta);
        outputArguments.add(out);
    }

    public AdjustHue(INDArray in, double delta) {
        Preconditions.checkArgument(in.rank() >= 3,
                String.format("AdjustSaturation: op expects rank of input array to be >= 3, but got %d instead", in.rank()));
        Preconditions.checkArgument(-1.0 <= delta && delta <= 1.0, "AdjustHue: parameter delta must be within [-1, 1] interval," +
                " but got %f instead", delta);
        inputArguments.add(in);

        addTArgument(delta);
    }

    public AdjustHue(SameDiff sameDiff, SDVariable in, SDVariable factor) {
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
