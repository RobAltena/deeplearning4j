package org.nd4j.linalg.api.ops.custom;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class FusedBatchNorm extends DynamicCustomOp {

    public FusedBatchNorm(INDArray x, INDArray scale, INDArray offset,
                          int dataFormat, int isTraining,
                          INDArray yOut, INDArray batchMeanOut, INDArray batchMeanVar) {
        addInputArgument(x, scale, offset);
        addIArgument(dataFormat, isTraining);
        addOutputArgument(yOut, batchMeanOut, batchMeanVar);
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
