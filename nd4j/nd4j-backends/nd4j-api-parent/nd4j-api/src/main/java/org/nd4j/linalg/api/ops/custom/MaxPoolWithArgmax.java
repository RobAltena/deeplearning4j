package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class MaxPoolWithArgmax extends DynamicCustomOp {

    public MaxPoolWithArgmax() {
        super();
    }

    public MaxPoolWithArgmax(@NonNull INDArray x) {
        Preconditions.checkArgument(x.rank() == 4, "MaxPoolWithArgmax: Input should have rank of 4, but got %s instead", x.rank());
        addInputArgument(x);
    }

    public MaxPoolWithArgmax(@NonNull SameDiff sameDiff, @NonNull SDVariable x) {
        super("", sameDiff, new SDVariable[]{x});
    }

    @Override
    public String opName() {
        return "max_pool_with_argmax";
    }

    @Override
    public String tensorflowName() {
        return "MaxPoolWithArgmax";
    }
}
