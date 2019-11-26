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

public class MatrixBandPart extends DynamicCustomOp {

    public MatrixBandPart() {}

    public MatrixBandPart(@NonNull INDArray input, int minLower, int maxUpper) {
        Preconditions.checkArgument(input.rank() >= 2, "MatrixBandPart: Input rank should be 2 or higher");
        long N = input.size(-2);
        long M = input.size(-1);
        Preconditions.checkArgument(minLower > -N && minLower < N, "MatrixBandPart: lower diagonal count %s should be less than %i",
                minLower, N);
        Preconditions.checkArgument(maxUpper > -M && maxUpper < M, "MatrixBandPart: upper diagonal count %s should be less than %i.",
                maxUpper, M);
        addInputArgument(input);
        addIArgument(minLower, maxUpper);
    }

    public MatrixBandPart(@NonNull SameDiff sameDiff, @NonNull SDVariable input, SDVariable minLower, SDVariable maxUpper) {
        super("", sameDiff, new SDVariable[]{input, minLower, maxUpper});
    }

    @Override
    public String opName() {
        return "matrix_band_part";
    }

    @Override
    public String tensorflowName() {
        return "MatrixBandPart";
    }

    @Override
    public List<DataType> calculateOutputDataTypes(List<DataType> inputDataTypes){
        int n = args().length;
        Preconditions.checkState(inputDataTypes != null && inputDataTypes.size() == n, "Expected %s input data types for %s, got %s", n, getClass(), inputDataTypes);
        return Collections.singletonList(inputDataTypes.get(0));
    }
}
