package org.nd4j.linalg.api.ops.custom;

import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class RandomCrop extends DynamicCustomOp {

    public RandomCrop(INDArray input, INDArray shape) {
        Preconditions.checkArgument(shape.isVector(),"RandomCrop:Shape tensor should be a vector");
        Preconditions.checkArgument(input.rank() == shape.length(), "RandomCrop:The length of the shape vector is not match input rank");
        addInputArgument(input, shape);
    }

    @Override
    public String opName() {
        return "random_crop";
    }

    @Override
    public String tensorflowName() {
        return "RandomCrop";
    }
}
