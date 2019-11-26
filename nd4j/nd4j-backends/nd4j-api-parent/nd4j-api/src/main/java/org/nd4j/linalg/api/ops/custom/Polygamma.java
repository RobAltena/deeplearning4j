package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

import java.util.Collections;
import java.util.List;

public class Polygamma extends DynamicCustomOp {

    public Polygamma() {}

    public Polygamma(@NonNull INDArray n, @NonNull INDArray x) {
        Preconditions.checkArgument(n.shape() != x.shape(),
                "Polygamma: n and x must have the same shapes");
        addInputArgument(n,x);
    }

    public Polygamma(@NonNull INDArray n, @NonNull INDArray x, INDArray output) {
        this(n,x);
        addOutputArgument(output);
    }

    public Polygamma(@NonNull SameDiff sameDiff, @NonNull SDVariable n, @NonNull SDVariable x) {
        super("", sameDiff, new SDVariable[]{n ,x});
    }

    @Override
    public String opName() {
        return "polygamma";
    }

    @Override
    public String tensorflowName() {
        return "Polygamma";
    }

    @Override
    public List<DataType> calculateOutputDataTypes(List<DataType> inputDataTypes){
        int n = args().length;
        Preconditions.checkState(inputDataTypes != null && inputDataTypes.size() == n, "Expected %s input data types for %s, got %s", n, getClass(), inputDataTypes);
        return Collections.singletonList(inputDataTypes.get(0));
    }
}
