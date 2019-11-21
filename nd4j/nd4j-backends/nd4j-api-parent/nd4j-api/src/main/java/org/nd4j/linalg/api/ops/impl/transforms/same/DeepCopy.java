package org.nd4j.linalg.api.ops.impl.transforms.same;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.transforms.BaseDynamicTransformOp;
import org.nd4j.linalg.api.shape.LongShapeDescriptor;
import org.nd4j.linalg.api.shape.Shape;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Copy operation
 *
 * @author Alexander Stoyakin
 */
public class DeepCopy extends BaseDynamicTransformOp {

    public DeepCopy(SameDiff sd, SDVariable input){
        super(sd, new SDVariable[]{input}, false);
    }

    public DeepCopy(INDArray x, INDArray z){
        super(new INDArray[]{x}, new INDArray[]{z});
    }

    public DeepCopy(){}

    @Override
    public String opName() {
        return "identity";
    }

    @Override
    public String onnxName() {
        return "Constant";
    }

    @Override
    public String tensorflowName() {
        return "DeepCopy";
    }

    @Override
    public List<SDVariable> doDiff(List<SDVariable> i_v) {
        //Eventually we'll optimize this out
        return Collections.singletonList(sameDiff.identity(i_v.get(0)));
    }

    @Override
    public List<DataType> calculateOutputDataTypes(List<DataType> dataTypes){
        Preconditions.checkState(dataTypes != null && dataTypes.size() == 1, "Expected exactly 1 input datatype for %s, got input %s", getClass(), dataTypes);
        return dataTypes;
    }

}
