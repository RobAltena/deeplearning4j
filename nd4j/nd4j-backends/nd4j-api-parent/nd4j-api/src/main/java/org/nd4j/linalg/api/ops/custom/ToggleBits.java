/* ******************************************************************************
 * Copyright (c) 2019 Konduit K.K.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ******************************************************************************/
package org.nd4j.linalg.api.ops.custom;

import lombok.NonNull;
import org.nd4j.autodiff.samediff.SDVariable;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.DynamicCustomOp;

public class ToggleBits extends DynamicCustomOp {

    public ToggleBits() {}

    public ToggleBits(@NonNull INDArray input, INDArray output) {
        this(input);
        addOutputArgument(input);
    }

    public ToggleBits(@NonNull INDArray input) {
        addInputArgument(input);
    }

    public ToggleBits(@NonNull SameDiff sameDiff, @NonNull SDVariable input) {
        super("", sameDiff, new SDVariable[]{input});
    }

    @Override
    public String opName() {
        return "toggle_bits";
    }

    @Override
    public String tensorflowName() {
        return "Invert";
    }
}
