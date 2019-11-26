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

public class BetaInc extends DynamicCustomOp {

    public BetaInc() {}

    public BetaInc(@NonNull INDArray a_input, @NonNull INDArray b_input, @NonNull INDArray x_input,
                   INDArray output) {
        addInputArgument(a_input, b_input, x_input);
        addOutputArgument(output);
    }

    public BetaInc(@NonNull INDArray a_input, @NonNull INDArray b_input, @NonNull INDArray x_input) {
        inputArguments.add(a_input);
        inputArguments.add(b_input);
        inputArguments.add(x_input);
    }

    public BetaInc(@NonNull SameDiff sameDiff, @NonNull SDVariable a, @NonNull SDVariable b, @NonNull SDVariable x) {
        super(sameDiff, new SDVariable[]{a,b,x});
    }

    @Override
    public String opName() {
        return "betainc";
    }

    @Override
    public String tensorflowName() {
        return "Betainc";
    }

    @Override
    public List<DataType> calculateOutputDataTypes(List<DataType> inputDataTypes){
        int n = args().length;
        Preconditions.checkState(inputDataTypes != null && inputDataTypes.size() == n, "Expected %s input data types for %s, got %s", n, getClass(), inputDataTypes);
        return Collections.singletonList(inputDataTypes.get(0));
    }
}
