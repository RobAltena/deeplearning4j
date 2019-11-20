package org.nd4j.linalg.api.ops.custom;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class FusedBatchNorm extends DynamicCustomOp {

    public FusedBatchNorm() {
        super();
    }

    public FusedBatchNorm(INDArray x, INDArray scale, INDArray offset,
                          int dataFormat, int isTraining,
                          INDArray yOut, INDArray batchMeanOut, INDArray batchMeanVar) {
        addInputArgument(x, scale, offset);
        addIArgument(dataFormat, isTraining);
        addOutputArgument(yOut, batchMeanOut, batchMeanVar);
    }

    public FusedBatchNorm(SameDiff sameDiff, SDVariable x, SDVariable scale, SDVariable offset,
                          SDVariable dataFormat, SDVariable isTraining) {
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
