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
import org.nd4j.linalg.indexing.conditions.Conditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NDBaseTest extends BaseNd4jTest {
    public NDBaseTest(Nd4jBackend backend) {
        super(backend);
    }

    @Test
    public void testAll() {
        NDBase base = new NDBase();

        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.all(x, 1);
        INDArray y_exp = Nd4j.create(new boolean[]{false, false, false});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testAny() {
        NDBase base = new NDBase();

        INDArray x = Nd4j.eye(3);
        INDArray y = base.any(x, 1);
        INDArray y_exp = Nd4j.create(new boolean[]{true, true, true});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testArgmax() {
        NDBase base = new NDBase();

        INDArray x = Nd4j.create(new double[][]{{0.75, 0.5, 0.25}, {0.5, 0.75, 0.25}, {0.5, 0.25, 0.75}});
        INDArray y = base.argmax(x, 0); //with default keepdims
        INDArray y_exp = Nd4j.createFromArray(0L, 1L, 2L);
        assertEquals(y.dataType(), DataType.LONG);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.argmax(x, false, 0); //with explicit keepdims false
        assertEquals(y.dataType(), DataType.LONG);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.argmax(x, true, 0); //with keepdims true
        y_exp = Nd4j.createFromArray(new long[][]{{0L, 1L, 2L}}); //expect different shape.
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testArgmin() {
        //Copy Paste from argmax, replaced with argmin.
        NDBase base = new NDBase();

        INDArray x = Nd4j.create(new double[][]{{0.75, 0.5, 0.25}, {0.5, 0.75, 0.25}, {0.5, 0.25, 0.75}});
        INDArray y = base.argmin(x, 0); //with default keepdims
        INDArray y_exp = Nd4j.createFromArray(1L, 2L, 0L);
        assertEquals(y.dataType(), DataType.LONG);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.argmin(x, false, 0); //with explicit keepdims false
        assertEquals(y.dataType(), DataType.LONG);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.argmin(x, true, 0); //with keepdims true
        y_exp = Nd4j.createFromArray(new long[][]{{1L, 2L, 0L}}); //expect different shape.
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testAssign() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = Nd4j.createUninitialized(x.shape());
        INDArray z = base.assign(x, y);
        assertEquals(x, y);
        assertEquals(z, y);

        y = null;
        z = base.assign(x, y); //test crashes.
    }

    @Test
    public void testConcat() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = Nd4j.ones(DataType.DOUBLE, 3, 3);

        INDArray z = base.concat(new INDArray[]{x, y}, 0);
        assertArrayEquals(z.shape(), new long[]{6, 3});

        z = base.concat(new INDArray[]{x, y}, 1);
        assertArrayEquals(z.shape(), new long[]{3, 6});
    }

    @Test
    public void testCumprod() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.cumprod(x, false, false, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{1.0, 2.0, 3.0}, {4.0, 10.0, 18.0}, {28.0, 80.0, 162.0}});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.cumprod(x, false, false, 1);
        y_exp = Nd4j.createFromArray(new double[][]{{1.0, 2.0, 6.0}, {4.0, 20.0, 120.0}, {7.0, 56.0, 504.0}});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

    }

    @Test
    public void testCumsum() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.cumsum(x, false, false, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{1.0, 2.0, 3.0}, {5.0, 7.0, 9.0}, {12.0, 15.0, 18.0}});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));

        y = base.cumsum(x, false, false, 1);
        y_exp = Nd4j.createFromArray(new double[][]{{1.0, 3.0, 6.0}, {4.0, 9.0, 15.0}, {7.0, 15.0, 24.0}});
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testDot() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 3);
        INDArray y = base.dot(x, x, 0);
        INDArray y_exp = Nd4j.scalar(14.0);
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testDynamicpartition() {
        //Try to execute the sample in the code dcumentation:
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 5);
        int numPartitions = 2;
        int[] partitions = new int[]{1, 0, 0, 1, 0};
        INDArray y = base.dynamicPartition(x, partitions, numPartitions);
        //TODO: crashes here. Op needs fixing.

    }

    @Test
    public void testDynamicStitch() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.dynamicStitch(new INDArray[]{x, x}, 0);
        //TODO: crashes here. Op needs fixing.
    }

    @Test
    public void testScalarEq() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.eq(x, 0.0);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testEq() {
        //element wise  eq.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.eq(x, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testExpandDims() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 1, 2);
        INDArray y = base.expandDims(x, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{1.0, 0.0, 0.0}});
        //TODO: Fix. Not getting the expected output.
        assertTrue(y.equalsWithEps(y_exp, 1e-6));
    }

    @Test
    public void testFill() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.createFromArray(2, 2);
        INDArray y = base.fill(x, DataType.DOUBLE, 1.1);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{1.1, 1.1}, {1.1, 1.1}});
        // TODO: Fails: y is a float, not a double as expected.
        assertTrue(y.equalsWithEps(y_exp, 1e-1));
    }

    @Test
    public void testGather() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        int[] ind = new int[]{0};
        INDArray y = base.gather(x, ind, 0);
        //TODO: crashes here. Op needs fixing.
    }

    @Test
    public void testGatherNd() {
        NDBase base = new NDBase();

        assertTrue(false); // TODO: Fix the Op. (current signature is wrong.)
    }

    @Test
    public void testScalarGt() {
        //Scalar gt.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.gt(x, -0.1);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
        //TODO: Passes, but I do not like the detailed memory management in the Op constructor.
    }

    @Test
    public void testGt() {
        //element wise  gt.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x1 = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.gt(x1, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
    }


    @Test
    public void testScalarGte() {
        //Scalar gte.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.gte(x, -0.1);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
        //TODO: Passes, but I do not like the detailed memory management in the Op constructor.
    }

    @Test
    public void testGte() {
        //element wise  gte.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x1 = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.gte(x1, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertTrue(y.equals(y_exp));
    }

    @Test
    public void testIdentity() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.identity(x);
        assertTrue(y.equalsWithEps(x, 1e-1));
    }

    @Test
    public void testInvertPermutation() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.LONG, 1.0, 1.0, 9);
        INDArray y = base.invertPermutation(x);
        //TODO: crashes here. Op needs fixing.
    }

    @Test
    public void testisNumericTensor() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.isNumericTensor(x);
        assertEquals(y.toString(), "true"); //TODO: There has to be a proper way to do this.
    }

    @Test
    public void testLinspace() {
        NDBase base = new NDBase();
        INDArray y = base.linspace(DataType.DOUBLE, 0.0, 9.0, 19);
        //TODO: test crashes.
    }

    @Test
    public void testScalarLt() {
        //Scalar gt.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.lt(x, 0.1);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
        //TODO: Passes, but I do not like the detailed memory management in the Op constructor.
    }

    @Test
    public void testLt() {
        NDBase base = new NDBase();
        INDArray x1 = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.lt(x1, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testScalarLte() {
        //Scalar gt.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.lte(x, 0.1);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
        //TODO: Passes, but I do not like the detailed memory management in the Op constructor.
    }

    @Test
    public void testLte() {
        NDBase base = new NDBase();
        INDArray x1 = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.lte(x1, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testMatchCondition() {
        // same test as TestMatchTransformOp,
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[] {1, 1, 1, 0, 1, 1});
        INDArray y = base.matchCondition(x, Conditions.epsEquals(0.0));
        INDArray y_exp = Nd4j.create(new boolean[] {false, false, false, true, false, false});
        assertEquals(y, y_exp);
    }

    @Test
    public void testMatchConditionCount() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[] {1, 1, 1, 0, 1, 1});
        INDArray y = base.matchConditionCount(x, Conditions.epsEquals(0.0));
        assertEquals(y.toString(), "1"); //TODO: There has to be a proper way to do this.

        x = Nd4j.eye(3);
        y = base.matchConditionCount(x, Conditions.epsEquals(1.0), true, 1);
        INDArray y_exp = Nd4j.createFromArray(new Long[][]{{1L}, {1L}, {1L}});
        assertEquals(y, y_exp);

        y = base.matchConditionCount(x, Conditions.epsEquals(1.0), true, 0);
        y_exp = Nd4j.createFromArray(new Long[][]{{1L, 1L, 1L}});
        assertEquals(y, y_exp);

        y = base.matchConditionCount(x, Conditions.epsEquals(1.0), false, 1);
        y_exp = Nd4j.createFromArray(new Long[]{1L, 1L, 1L});
        assertEquals(y, y_exp);

        y = base.matchConditionCount(x, Conditions.epsEquals(1.0), false, 0);
        assertEquals(y, y_exp);
    }

    @Test
    public void testMax() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.max(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(new float[]{1.0f, 1.0f, 1.0f});
        assertEquals(y, y_exp);

        y = base.max(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 1.0f, 1.0f}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testMean() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.mean(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(new float[]{0.333333f, 0.333333f, 0.333333f});
        assertEquals(y, y_exp);

        y = base.mean(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{0.333333f, 0.333333f, 0.333333f}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testMin() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.min(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(new float[]{0.0f, 0.0f, 0.0f});
        assertEquals(y, y_exp);

        y = base.min(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{0.0f, 0.0f, 0.0f}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testMmulTranspose() {
        //TODO: Transpose arguments.
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testMmul() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray x1 = Nd4j.eye(3).castTo(DataType.DOUBLE);
        INDArray y = base.mmul(x, x1);
        assertEquals(y, x);
    }

    @Test
    public void testScalarNeq() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.neq(x, 1.0);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testNeq() {
        //element wise  eq.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x1 = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.neq(x, x1);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testNorm1() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.norm1(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(new float[]{1.0f, 1.0f, 1.0f});
        assertEquals(y, y_exp);

        y = base.norm1(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 1.0f, 1.0f}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testNorm2() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.norm2(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(new float[]{1.0f, 1.0f, 1.0f});
        assertEquals(y, y_exp);

        y = base.norm2(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 1.0f, 1.0f}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testNormMax() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.normmax(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(new float[]{1.0f, 1.0f, 1.0f});
        assertEquals(y, y_exp);

        y = base.normmax(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 1.0f, 1.0f}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testOneHot() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{0, 1, 2});
        INDArray y = base.oneHot(x, 1, 0, 1.0, 0.0);
        INDArray y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 0.0f, 0.0f}});
        assertEquals(y, y_exp);

        y = base.oneHot(x, 1);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f},{ 0.0f}, {0.0f}});
        assertEquals(y, y_exp);

        y = base.oneHot(x, 1, 0, 1.0, 0.0, DataType.DOUBLE);
        y_exp = Nd4j.createFromArray(new double[][]{{1.0, 0.0, 0.0}});
        assertEquals(y, y_exp); //TODO: Looks like we're getting back the wrong datatype.
    }

    @Test
    public void testOnesLike() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{3, 3});
        INDArray y = base.onesLike(x);
        INDArray  y_exp = Nd4j.createFromArray(new double[]{1.0, 1.0});
        assertEquals(y, y_exp);

        y = base.onesLike(x, DataType.LONG);
        y_exp = Nd4j.createFromArray(new long[]{1, 1});
        assertEquals(y, y_exp); //TODO: Getting back a double array, not a long.
    }

    @Test
    public void testPermute() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{1, 2, 3});
        INDArray y = base.permute(x, 2, 0, 1);
        //TODO: fix. no output arrays were provided and calculateOutputShape failed to execute

    }

    @Test
    public void testProd() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.prod(x, 0);
        INDArray y_exp = Nd4j.createFromArray(new float[]{0.0f, 0.0f, 0.0f});
        assertEquals(y, y_exp);

        y = base.prod(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{0.0f, 0.0f, 0.0f}});
        assertEquals(y, y_exp);
    }

    @Test
    public void testRange() {
        NDBase base = new NDBase();
        INDArray y = base.range(0.0, 3.0, 1.0, DataType.DOUBLE);
        INDArray y_exp = Nd4j.createFromArray(new double[]{0.0, 1.0, 2.0f});
        assertEquals(y, y_exp); //TODO: Asked for DOUBLE, got back a FLOAT Array.
    }

    @Test
    public void testRank() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.rank(x);
        INDArray y_exp = Nd4j.scalar(2);
        System.out.println(y);
        assertEquals(y, y_exp);
    }

    @Test
    public void testRepeat() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.repeat(x, 0);
        //TODO: fix, crashes the JVM.
    }


    @Test
    public void testReplaceWhere() {
        // test from BooleanIndexingTest.
        NDBase base = new NDBase();
        INDArray array = Nd4j.create(new double[] {1, 2, 0, 4, 5});
        INDArray comp = Nd4j.create(new double[] {1, 2, 3, 4, 5});
        INDArray y = base.replaceWhere(array, comp, Conditions.lessThan(1));
        assertEquals(comp, array);
        assertEquals(y, array); // in fact y points to the same INDArray. Is that expected?

    }

    @Test
    public void testReshape() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray shape = Nd4j.create(new long[] {3, 3});
        INDArray y = base.reshape(x, shape);
        //TODO: Fix operator.  java.lang.RuntimeException: Op [reshape] execution failed
    }

    @Test
    public void testReverse() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 6).reshape(2, 3);
        INDArray y = base.reverse(x, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{ 4.0, 5.0, 6.0},{1.0, 2.0, 3.0} } );
        assertEquals(y, y_exp);
    }

    @Test
    public void testReverseSequence() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9);
        INDArray seq_kengths = Nd4j.create(new long[] {3});
        INDArray y = base.reverseSequence(x, seq_kengths);
        // TODO: org.nd4j.linalg.exception.ND4JIllegalStateException: Op name reverse_sequence - no output arrays were provided and calculateOutputShape failed to execute

        //TODO: test reverseSequence(INDArray x, INDArray seq_lengths, int seqDim, int batchDim)
    }

    @Test
    public void testScalarFloorMod() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.scalarFloorMod(x, 2.0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{ 1.0, 0.0, 1.0},{0.0, 1.0, 0.0}, { 1.0, 0.0, 1.0} } );
        assertEquals(y, y_exp);
    }

    @Test
    public void testScalarMax() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.scalarMax(x, 5.0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{ 5.0, 5.0, 5.0},{5.0, 5.0, 6.0}, { 7.0, 8.0, 9.0} } );
        assertEquals(y, y_exp);
        //System.out.println(y);
    }

    @Test
    public void testScalarMin() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.scalarMin(x, 5.0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{ 1.0, 2.0, 3.0},{4.0, 5.0, 5.0}, { 5.0, 5.0, 5.0} } );
        assertEquals(y, y_exp);
    }

    @Test
    public void testScalarSet() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[] {1, 2, 0, 4, 5});
        INDArray y = base.scalarSet(x, 1.0);
        INDArray y_exp = Nd4j.ones(DataType.DOUBLE, 5);
        assertEquals(y, y_exp);
    }

    @Test
    public void testScatterAdd() {
        NDBase base = new NDBase();

        //from testScatterOpGradients.
        INDArray x = Nd4j.ones(DataType.DOUBLE, 20, 10);
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT);
        INDArray updates = Nd4j.ones(DataType.DOUBLE, 5, 10);
        INDArray y = base.scatterAdd(x,indices, updates);

        //just be happy it runs for now.
        System.out.println(y);
    }

    @Test
    public void testScatterDiv() {
        NDBase base = new NDBase();

        //from testScatterOpGradients.
        INDArray x = Nd4j.ones(DataType.DOUBLE, 20, 10).add(1.0);
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT);
        INDArray updates = Nd4j.ones(DataType.DOUBLE, 5, 10).add(1.0);
        INDArray y = base.scatterDiv(x,indices, updates);

        //just be happy it runs for now.
        System.out.println(y);
    }

    @Test
    public void testScatterMax() {
        NDBase base = new NDBase();

        //from testScatterOpGradients.
        INDArray x = Nd4j.ones(DataType.DOUBLE, 20, 10).add(1.0);
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT);
        INDArray updates = Nd4j.ones(DataType.DOUBLE, 5, 10).add(1.0);
        INDArray y = base.scatterMax(x,indices, updates);

        //just be happy it runs for now.
        System.out.println(y);
    }

    @Test
    public void testScatterMin() {
        NDBase base = new NDBase();

        //from testScatterOpGradients.
        INDArray x = Nd4j.ones(DataType.DOUBLE, 20, 10).add(1.0);
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT);
        INDArray updates = Nd4j.ones(DataType.DOUBLE, 5, 10).add(1.0);
        INDArray y = base.scatterMin(x,indices, updates);

        //just be happy it runs for now.
        System.out.println(y);
    }

    @Test
    public void testScatterMul() {
        NDBase base = new NDBase();

        //from testScatterOpGradients.
        INDArray x = Nd4j.ones(DataType.DOUBLE, 20, 10).add(1.0);
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT);
        INDArray updates = Nd4j.ones(DataType.DOUBLE, 5, 10).add(1.0);
        INDArray y = base.scatterMul(x,indices, updates);

        //just be happy it runs for now.
        System.out.println(y);
    }

    @Test
    public void testScatterSub() {
        NDBase base = new NDBase();

        //from testScatterOpGradients.
        INDArray x = Nd4j.ones(DataType.DOUBLE, 20, 10).add(1.0);
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT);
        INDArray updates = Nd4j.ones(DataType.DOUBLE, 5, 10).add(1.0);
        INDArray y = base.scatterSub(x,indices, updates);

        //just be happy it runs for now.
        System.out.println(y);
    }

    @Test
    public void testScatterUpdate() {
        NDBase base = new NDBase();

        //from testScatterOpGradients.
        INDArray x = Nd4j.ones(DataType.DOUBLE, 20, 10).add(1.0);
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT);
        INDArray updates = Nd4j.ones(DataType.DOUBLE, 5, 10).add(1.0);
        INDArray y = base.scatterUpdate(x,indices, updates);

        //just be happy it runs for now.
        System.out.println(y);
    }

    @Test
    public void testSegmentMax() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{3, 6, 1, 4, 9,2});
        INDArray segmentIDs = Nd4j.create(0,0,1,1,1,2,2);
        INDArray y = base.segmentMax(x, segmentIDs);
        System.out.println(y);
        //TODO: org.nd4j.linalg.exception.ND4JIllegalStateException: Op name segment_max - no output arrays were provided and calculateOutputShape failed to execute
    }

    @Test
    public void testSegmentMean() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{3, 6, 1, 4, 9,2});
        INDArray segmentIDs = Nd4j.create(0,0,1,1,1,2,2);
        INDArray y = base.segmentMean(x, segmentIDs);
        System.out.println(y);
        //TODO: org.nd4j.linalg.exception.ND4JIllegalStateException: Op name segment_max - no output arrays were provided and calculateOutputShape failed to execute
    }

    @Test
    public void testSegmentMin() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{3, 6, 1, 4, 9,2});
        INDArray segmentIDs = Nd4j.create(0,0,1,1,1,2,2);
        INDArray y = base.segmentMin(x, segmentIDs);
        System.out.println(y);
        //TODO: org.nd4j.linalg.exception.ND4JIllegalStateException: Op name segment_max - no output arrays were provided and calculateOutputShape failed to execute
    }

    @Test
    public void testSegmentProd() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{3, 6, 1, 4, 9,2});
        INDArray segmentIDs = Nd4j.create(0,0,1,1,1,2,2);
        INDArray y = base.segmentProd(x, segmentIDs);
        System.out.println(y);
        //TODO: org.nd4j.linalg.exception.ND4JIllegalStateException: Op name segment_max - no output arrays were provided and calculateOutputShape failed to execute
    }

    @Test
    public void testSegmentSum() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{3, 6, 1, 4, 9,2});
        INDArray segmentIDs = Nd4j.create(0,0,1,1,1,2,2);
        INDArray y = base.segmentSum(x, segmentIDs);
        System.out.println(y);
        // TODO: This one crashes the JVM.
        //  EXCEPTION_INT_DIVIDE_BY_ZERO (0xc0000094) at pc=0x00000000710f6aca, pid=13936, tid=0x0000000000005780
    }

    @Test
    public void testSequenceMask() {
        NDBase base = new NDBase();
        INDArray length = Nd4j.create(new float[] {1, 3, 2});
        int maxlength = 5;
        DataType dt = DataType.FLOAT;
        INDArray y = base.sequenceMask(length, maxlength, dt);
        System.out.println(y);
        //TODO: org.nd4j.linalg.exception.ND4JIllegalStateException: Op name sequence_mask - no output arrays were provided and calculateOutputShape failed to execute
    }

    @Test
    public void testShape() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(3,3);
        INDArray y = base.shape(x);

        INDArray y_exp = Nd4j.createFromArray(3L, 3L);
        assertEquals(y, y_exp);
    }

    @Test
    public void testSize() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testSizeAt() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testSlice() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testSquaredNorm() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testSqueeze() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testStack() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testStandardDeviation() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testStridedSlice() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testSum() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testTensorMul() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testTile() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testTranspose() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testUnsegmentMax() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testUnsegmentMean() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testUnsegmentedMin() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testUnsegmentProd() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testUnsortedSegmentSqrtN() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testUnsortedSegmentSum() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testVariance() {
        assertTrue(false); //TODO: build test
    }

    @Test
    public void testZerosLike() {
        assertTrue(false); //TODO: build test
    }

}
