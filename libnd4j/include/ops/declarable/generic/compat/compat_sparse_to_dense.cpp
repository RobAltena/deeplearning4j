/*******************************************************************************
 * Copyright (c) 2015-2018 Skymind, Inc.
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

//
//  @author raver119@gmail.com
//

#include <op_boilerplate.h>
#if NOT_EXCLUDED(OP_split_string)

#include <ops/declarable/CustomOperations.h>

namespace nd4j {
    namespace ops {
        CUSTOM_OP_IMPL(compat_sparse_to_dense, 4, 1, false, 0, 0) {
            auto input = INPUT_VARIABLE(0);
            auto delim = INPUT_VARIABLE(1);

            return Status::OK();
        };

        DECLARE_SHAPE_FN(compat_sparse_to_dense) {
            auto values = INPUT_VARIABLE(0);
            auto shape = INPUT_VARIABLE(1);
            auto ranges = INPUT_VARIABLE(2);

            if (block.width() > 3) {
                auto def = INPUT_VARIABLE(3);

                REQUIRE_TRUE(def->dataType() == values->dataType() && def->isScalar(), 0, "compat_sparse_to_dense: default value must be a scalar of the same data type as actual values")
            };

            auto dtype = values->dataType();

            // basically output shape is defined by the type of input, and desired shape input
            return SHAPELIST(ConstantShapeHelper::getInstance()->createShapeInfo(dtype, 'c', shape->getBufferAsVector<Nd4jLong>()));
        }

        DECLARE_TYPES(compat_sparse_to_dense) {
            getOpDescriptor()
                    ->setAllowedInputTypes(0,nd4j::DataType::ANY)
                    ->setAllowedInputTypes(1, {ALL_INTS})
                    ->setAllowedInputTypes(2, {ALL_INTS})
                    ->setAllowedInputTypes(3,nd4j::DataType::ANY)
                    ->setAllowedOutputTypes(nd4j::DataType::ANY);
        }
    }
}

#endif