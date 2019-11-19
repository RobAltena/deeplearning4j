package org.nd4j.linalg.api.ops.custom;

import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class MaxPoolWithArgmax extends DynamicCustomOp {

    public MaxPoolWithArgmax(INDArray x) {
        Preconditions.checkArgument(x.rank() == 4, "MaxPoolWithArgmax: Input should have rank of 4, but got %i instead", x.rank());
        addInputArgument(x);
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
