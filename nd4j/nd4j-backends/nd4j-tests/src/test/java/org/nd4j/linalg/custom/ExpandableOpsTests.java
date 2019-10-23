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

package org.nd4j.linalg.custom;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.nd4j.linalg.api.ops.compat.CompatStringSplit;
import org.nd4j.linalg.factory.Nd4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This is special test suit: we test operations that on C++ side modify arrays that come from Java
 */
@Slf4j
public class ExpandableOpsTests {

    @Test
    public void testCompatStringSplit_1() throws Exception {
        val array = Nd4j.create("first string", "second");
        val delimiter = Nd4j.create(" ");

        val exp0 = Nd4j.createFromArray(new long[] {0,0, 0,1, 1,0});
        val exp1 = Nd4j.create("first", "string", "second");

        val results = Nd4j.exec(new CompatStringSplit(array, delimiter));
        assertNotNull(results);
        assertEquals(2, results.length);

        assertEquals(exp0, results[0]);
        assertEquals(exp1, results[1]);
    }
}
