package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class FusedBatchNorm extends DynamicCustomOp {

    public FusedBatchNorm() {
        super();
    }

    public FusedBatchNorm(@NonNull INDArray x, @NonNull INDArray scale, @NonNull INDArray offset,
                          int dataFormat, int isTraining,
                          @NonNull INDArray yOut, @NonNull INDArray batchMeanOut, @NonNull INDArray batchMeanVar) {
        addInputArgument(x, scale, offset);
        addIArgument(dataFormat, isTraining);
        addOutputArgument(yOut, batchMeanOut, batchMeanVar);
    }

    public FusedBatchNorm(@NonNull SameDiff sameDiff, @NonNull SDVariable x, @NonNull SDVariable scale, @NonNull SDVariable offset,
                          @NonNull SDVariable dataFormat, @NonNull SDVariable isTraining) {
        super("", sameDiff, new SDVariable[]{x, scale, offset, dataFormat, isTraining});
    }

    @Override
    public String opName() {
        return "fused_batch_norm";
    }

    @Override
    public String tensorflowName() {
        return "FusedBatchNormV2";
    }
}
