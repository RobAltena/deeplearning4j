package org.nd4j.linalg.api.ops.custom;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;
import org.nd4j.linalg.api.rng.Random;

public class RandomCrop extends DynamicCustomOp {

    public RandomCrop() {
        super();
    }

    public RandomCrop(INDArray input, INDArray shape) {
        Preconditions.checkArgument(shape.isVector(),"RandomCrop:Shape tensor should be a vector");
        Preconditions.checkArgument(input.rank() == shape.length(), "RandomCrop:The length of the shape vector is not match input rank");
        addInputArgument(input, shape);
    }

    public RandomCrop(SameDiff sameDiff, SDVariable input, SDVariable shape) {
            super("", sameDiff, new SDVariable[]{input, shape});
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
