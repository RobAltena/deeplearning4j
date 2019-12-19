/* *****************************************************************************
 * Copyright (c) 2015-2018 Konduit k.k.
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

package org.nd4j.linalg.factory.ops;

import org.junit.Test;
import org.nd4j.linalg.BaseNd4jTest;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.factory.Nd4jBackend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NDBaseTest extends BaseNd4jTest {
    public NDBaseTest(Nd4jBackend backend) {
        super(backend);
    }

    @Test
    public void testAll() {
        NDBase base = new NDBase();

        INDArray x = Nd4j.zeros(DataType.DOUBLE,3, 3);
        INDArray y  = base.all(x, 1);
        INDArray y_exp = Nd4j.create(new boolean[] {false, false, false});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testAny() {
        NDBase base = new NDBase();

        INDArray x = Nd4j.eye(3);
        INDArray y  = base.any(x, 1);
        INDArray y_exp = Nd4j.create(new boolean[] {true, true, true});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testArgmax() {
        NDBase base = new NDBase();

        INDArray x = Nd4j.create(new double[][] {{0.75, 0.5, 0.25}, {0.5, 0.75, 0.25}, {0.5, 0.25, 0.75}});
        INDArray y = base.argmax(x, 0); //with default keepdims
        INDArray y_exp = Nd4j.createFromArray(0L,1L,2L);
        assertEquals(y.dataType(), DataType.LONG);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.argmax(x, false, 0); //with explicit keepdims false
        assertEquals(y.dataType(), DataType.LONG);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.argmax(x, true, 0); //with keepdims true
        y_exp = Nd4j.createFromArray(new long[][]{{0L,1L,2L}}); //expect different shape.
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testArgmin() {
        //Copy Paste from argmax, replaced with argmin.
        NDBase base = new NDBase();

        INDArray x = Nd4j.create(new double[][] {{0.75, 0.5, 0.25}, {0.5, 0.75, 0.25}, {0.5, 0.25, 0.75}});
        INDArray y = base.argmin(x, 0); //with default keepdims
        INDArray y_exp = Nd4j.createFromArray(1L,2L,0L);
        assertEquals(y.dataType(), DataType.LONG);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.argmin(x, false, 0); //with explicit keepdims false
        assertEquals(y.dataType(), DataType.LONG);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.argmin(x, true, 0); //with keepdims true
        y_exp = Nd4j.createFromArray(new long[][]{{1L,2L,0L}}); //expect different shape.
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testAssign() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = Nd4j.eye(3);
        INDArray z = base.assign(x,y); //test crashes.
    }

    @Test
    public void testConcat() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE,3, 3);
        INDArray y = Nd4j.ones(DataType.DOUBLE,3, 3);

        INDArray z = base.concat(new INDArray[]{x, y}, 0);
        assertArrayEquals(z.shape(), new long[]{6,3});

        z = base.concat(new INDArray[]{x, y}, 1);
        assertArrayEquals(z.shape(), new long[]{3,6});
    }

    @Test
    public void testCumprod() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE,1.0, 1.0,9).reshape(3,3);
        INDArray y = base.cumprod(x, false, false, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{ 1.0, 2.0, 3.0}, { 4.0, 10.0, 18.0}, { 28.0, 80.0, 162.0}});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.cumprod(x, false, false, 1);
        y_exp = Nd4j.createFromArray(new double[][]{{ 1.0, 2.0, 6.0}, { 4.0, 20.0, 120.0}, { 7.0, 56.0, 504.0}});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

    }

    @Test
    public void testCumsum() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE,1.0, 1.0,9).reshape(3,3);
        INDArray y = base.cumsum(x, false, false, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{ 1.0, 2.0, 3.0}, { 5.0, 7.0, 9.0}, { 12.0, 15.0, 18.0}});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.cumsum(x, false, false, 1);
        y_exp = Nd4j.createFromArray(new double[][]{{ 1.0, 3.0, 6.0}, { 4.0, 9.0, 15.0}, { 7.0, 15.0, 24.0}});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    void testDot() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE,1.0, 1.0,3);
        INDArray y = base.dot(x, x,0);
        INDArray y_exp = Nd4j.scalar(14.0);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    void testDynamicpartition() {

        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE,1.0, 1.0,5);
        int numPartitions = 2;
        int [] partitions = new int [] {1,0,0,1,0};
        INDArray y = base.dynamicPartition(x, partitions, numPartitions); //crashed at the moment.
    }
}
