package org.nd4j.linalg.api.ops.custom;

import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class Polygamma extends DynamicCustomOp {

    public Polygamma() {
        super();
    }

    public Polygamma(INDArray n, INDArray x) {
        Preconditions.checkArgument(n.shape() != x.shape(),
                "Polygamma: n and x must have the same shapes");
        addInputArgument(n,x);
    }

    public Polygamma(INDArray n, INDArray x, INDArray output) {
        this(n,x);
        addOutputArgument(output);
    }

    public Polygamma(SameDiff sameDiff, SDVariable n, SDVariable x) {
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
}
