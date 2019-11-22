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

public class AdjustContrast extends BaseAdjustContrast {

    public AdjustContrast() {super();}

    public AdjustContrast(@NonNull INDArray in, double factor, INDArray out) {
        super(in, factor, out);
    }

    public AdjustContrast(@NonNull SameDiff sameDiff, @NonNull SDVariable in, @NonNull SDVariable factor) {
        super(sameDiff,new SDVariable[]{in,factor});
    }

    @Override
    public String opName() {
        return "adjust_contrast";
    }

    @Override
    public String tensorflowName() {
        return "AdjustContrast";
    }
}