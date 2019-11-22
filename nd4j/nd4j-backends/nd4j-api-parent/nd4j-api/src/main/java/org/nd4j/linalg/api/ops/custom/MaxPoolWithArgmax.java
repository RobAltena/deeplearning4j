package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;
import org.nd4j.linalg.api.ops.impl.layers.convolution.Pooling2D;
import org.nd4j.linalg.api.ops.impl.layers.convolution.config.Pooling2DConfig;

import java.util.Collections;
import java.util.List;

public class MaxPoolWithArgmax extends DynamicCustomOp {

    protected Pooling2DConfig config;

    public MaxPoolWithArgmax() {}

    public MaxPoolWithArgmax(@NonNull INDArray x, @NonNull Pooling2DConfig config) {
        Preconditions.checkArgument(x.rank() == 4, "MaxPoolWithArgmax: Input should have rank of 4, but got %s instead", x.rank());
        config.setType(Pooling2D.Pooling2DType.MAX);
        this.config = config;
        addInputArgument(x);
    }

    public MaxPoolWithArgmax(@NonNull SameDiff sameDiff, @NonNull SDVariable x, @NonNull Pooling2DConfig config) {
        super("", sameDiff, new SDVariable[]{x});
        config.setType(Pooling2D.Pooling2DType.MAX);
        this.config = config;
    }

    @Override
    public String opName() {
        return "max_pool_with_argmax";
    }

    @Override
    public String tensorflowName() {
        return "MaxPoolWithArgmax";
    }

    @Override
    public List<DataType> calculateOutputDataTypes(List<DataType> inputDataTypes){
        Preconditions.checkState(inputDataTypes != null && inputDataTypes.size() == 1, "Expected 1 input data type for %s, got %s", getClass(), inputDataTypes);
        return Collections.singletonList(inputDataTypes.get(0));
    }
}
