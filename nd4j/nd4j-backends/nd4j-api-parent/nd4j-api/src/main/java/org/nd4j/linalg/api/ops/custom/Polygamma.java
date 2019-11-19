package org.nd4j.linalg.api.ops.custom;

import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class Polygamma extends DynamicCustomOp {

    public Polygamma(INDArray n, INDArray x, INDArray output) {
        Preconditions.checkArgument(n.shape() != x.shape(),
                "Polygamma: n and x must have the same shapes");
        addInputArgument(n,x);
        addOutputArgument(output);
    }

    @Override
    public String opName() {
        return "polygamma";
    }

    @Override
    public String tensorflowName() {
        return "Polygamma";
    }
}
