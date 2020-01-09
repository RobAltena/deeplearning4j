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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class NDBaseTest extends BaseNd4jTest {
    public NDBaseTest(Nd4jBackend backend) {
        super(backend);
    }

    @Override
    public char ordering(){
        return 'c';
    }

    // TODO: Comment from the review. We'll remove the new NDBase() at some point.

    @Test
    public void testAll() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.all(x, 1);
        INDArray y_exp = Nd4j.createFromArray(false, false, false);
        assertEquals(y_exp, y);
    }

    @Test
    public void testAny() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.any(x, 1);
        INDArray y_exp = Nd4j.createFromArray(true, true, true);
        assertEquals(y_exp, y);
    }

    @Test
    public void testArgmax() {
        NDBase base = new NDBase();

        INDArray x = Nd4j.create(new double[][]{{0.75, 0.5, 0.25}, {0.5, 0.75, 0.25}, {0.5, 0.25, 0.75}});
        INDArray y = base.argmax(x, 0); //with default keepdims
        INDArray y_exp = Nd4j.createFromArray(0L, 1L, 2L);
        assertEquals(y_exp, y);

        y = base.argmax(x, false, 0); //with explicit keepdims false
        assertEquals(y_exp, y);

        y = base.argmax(x, true, 0); //with keepdims true
        y_exp = Nd4j.createFromArray(new long[][]{{0L, 1L, 2L}}); //expect different shape.
        assertEquals(y_exp, y);
    }

    @Test
    public void testArgmin() {
        //Copy Paste from argmax, replaced with argmin.
        NDBase base = new NDBase();

        INDArray x = Nd4j.create(new double[][]{{0.75, 0.5, 0.25}, {0.5, 0.75, 0.25}, {0.5, 0.25, 0.75}});
        INDArray y = base.argmin(x, 0); //with default keepdims
        INDArray y_exp = Nd4j.createFromArray(1L, 2L, 0L);
        assertEquals(y_exp, y);

        y = base.argmin(x, false, 0); //with explicit keepdims false
        assertEquals(y_exp, y);

        y = base.argmin(x, true, 0); //with keepdims true
        y_exp = Nd4j.createFromArray(new long[][]{{1L, 2L, 0L}}); //expect different shape.
        assertEquals(y_exp, y);
    }

    @Test
    public void testConcat() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = Nd4j.ones(DataType.DOUBLE, 3, 3);

        INDArray z = base.concat(new INDArray[]{x, y}, 0);
        assertArrayEquals(new long[]{6, 3}, z.shape());

        z = base.concat(new INDArray[]{x, y}, 1);
        assertArrayEquals(new long[]{3, 6}, z.shape());
    }

    @Test
    public void testCumprod() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.cumprod(x, false, false, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{1.0, 2.0, 3.0}, {4.0, 10.0, 18.0}, {28.0, 80.0, 162.0}});
        assertEquals(y_exp, y);

        y = base.cumprod(x, false, false, 1);
        y_exp = Nd4j.createFromArray(new double[][]{{1.0, 2.0, 6.0}, {4.0, 20.0, 120.0}, {7.0, 56.0, 504.0}});
        assertEquals(y_exp, y);

    }

    @Test
    public void testCumsum() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.cumsum(x, false, false, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{1.0, 2.0, 3.0}, {5.0, 7.0, 9.0}, {12.0, 15.0, 18.0}});
        assertEquals(y_exp, y);

        y = base.cumsum(x, false, false, 1);
        y_exp = Nd4j.createFromArray(new double[][]{{1.0, 3.0, 6.0}, {4.0, 9.0, 15.0}, {7.0, 15.0, 24.0}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testDot() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 3);
        INDArray y = base.dot(x, x, 0);
        INDArray y_exp = Nd4j.scalar(14.0);
        assertEquals(y_exp, y);
    }

    @Test
    public void testDynamicpartition() {
        //Try to execute the sample in the code dcumentation:
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 5);
        int numPartitions = 2;
        int[] partitions = new int[]{1, 0, 0, 1, 0};
        //INDArray y = base.dynamicPartition(x, partitions, numPartitions); TODO: Fix
        //TODO: crashes here. Op needs fixing.

    }

    @Test
    public void testDynamicStitch() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        //INDArray y = base.dynamicStitch(new INDArray[]{x, x}, 0); TODO: Fix
        //TODO: crashes here. Op needs fixing.  Bad constructor, as previously flagged. Both input and indices need to be INDArrays
    }

    @Test
    public void testScalarEq() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.eq(x, 0.0);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testEq() {
        //element wise  eq.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.eq(x, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testExpandDims() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 1, 2);
        INDArray y = base.expandDims(x, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{1.0, 0.0, 0.0}});
        //TODO: Fix. Not getting the expected output.
        assertEquals(y_exp, y);
    }

    @Test
    public void testFill() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.createFromArray(2, 2);
        INDArray y = base.fill(x, DataType.DOUBLE, 1.1);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{1.1, 1.1}, {1.1, 1.1}});
        // TODO: Fails: y is a float, not a double as expected.
        assertEquals(y_exp, y);
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
        assertEquals(y_exp, y);
    }

    @Test
    public void testGt() {
        //element wise  gt.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x1 = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.gt(x1, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }


    @Test
    public void testScalarGte() {
        //Scalar gte.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.gte(x, -0.1);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testGte() {
        //element wise  gte.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x1 = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.gte(x1, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testIdentity() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.identity(x);
        assertEquals(x, y);
    }

    @Test
    public void testInvertPermutation() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.INT64, 1.0, 1.0, 9);
        INDArray y = base.invertPermutation(x);
        //TODO: crashes here. Op needs fixing.
    }

    @Test
    public void testisNumericTensor() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.isNumericTensor(x);
        assertEquals(Nd4j.scalar(true), y);
    }

    @Test
    public void testLinspace() {
        NDBase base = new NDBase();
        INDArray y = base.linspace(DataType.DOUBLE, 0.0, 9.0, 19);
        //TODO: test crashes.
    }

    @Test
    public void testScalarLt() {
        //Scalar lt.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.lt(x, 0.1);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testLt() {
        NDBase base = new NDBase();
        INDArray x1 = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.lt(x1, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testScalarLte() {
        //Scalar gt.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray y = base.lte(x, 0.1);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testLte() {
        NDBase base = new NDBase();
        INDArray x1 = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.lte(x1, x);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testMatchCondition() {
        // same test as TestMatchTransformOp,
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[] {1, 1, 1, 0, 1, 1});
        INDArray y = base.matchCondition(x, Conditions.epsEquals(0.0));
        INDArray y_exp = Nd4j.create(new boolean[] {false, false, false, true, false, false});
        assertEquals(y_exp, y);
    }

    @Test
    public void testMatchConditionCount() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[] {1, 1, 1, 0, 1, 1});
        INDArray y = base.matchConditionCount(x, Conditions.epsEquals(0.0));
        assertEquals(Nd4j.scalar(1L), y);

        x = Nd4j.eye(3);
        y = base.matchConditionCount(x, Conditions.epsEquals(1.0), true, 1);
        INDArray y_exp = Nd4j.createFromArray(new Long[][]{{1L}, {1L}, {1L}});
        assertEquals(y_exp, y);

        y = base.matchConditionCount(x, Conditions.epsEquals(1.0), true, 0);
        y_exp = Nd4j.createFromArray(new Long[][]{{1L, 1L, 1L}});
        assertEquals(y_exp, y);

        y = base.matchConditionCount(x, Conditions.epsEquals(1.0), false, 1);
        y_exp = Nd4j.createFromArray(1L, 1L, 1L);
        assertEquals(y_exp, y);

        y = base.matchConditionCount(x, Conditions.epsEquals(1.0), false, 0);
        assertEquals(y_exp, y);
    }

    @Test
    public void testMax() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.max(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(1.0f, 1.0f, 1.0f);
        assertEquals(y_exp, y);

        y = base.max(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 1.0f, 1.0f}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testMean() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.mean(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(0.333333f, 0.333333f, 0.333333f);
        assertEquals(y_exp, y);

        y = base.mean(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{0.333333f, 0.333333f, 0.333333f}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testMin() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.min(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(0.0f, 0.0f, 0.0f);
        assertEquals(y_exp, y);

        y = base.min(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{0.0f, 0.0f, 0.0f}});
        assertEquals(y_exp, y);
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
        assertEquals(y_exp, y);
    }

    @Test
    public void testNeq() {
        //element wise  eq.
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(DataType.DOUBLE, 3, 3);
        INDArray x1 = Nd4j.ones(DataType.DOUBLE, 3, 3);
        INDArray y = base.neq(x, x1);
        INDArray y_exp = Nd4j.createFromArray(new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testNorm1() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.norm1(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(1.0f, 1.0f, 1.0f);
        assertEquals(y_exp, y);

        y = base.norm1(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 1.0f, 1.0f}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testNorm2() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.norm2(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(1.0f, 1.0f, 1.0f);
        assertEquals(y_exp, y);

        y = base.norm2(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 1.0f, 1.0f}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testNormMax() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.normmax(x, 0);
        INDArray  y_exp = Nd4j.createFromArray(1.0f, 1.0f, 1.0f);
        assertEquals(y_exp, y);

        y = base.normmax(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 1.0f, 1.0f}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testOneHot() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{0, 1, 2});
        INDArray y = base.oneHot(x, 1, 0, 1.0, 0.0);
        INDArray y_exp = Nd4j.createFromArray(new float[][]{{1.0f, 0.0f, 0.0f}});
        assertEquals(y_exp, y);

        y = base.oneHot(x, 1);
        y_exp = Nd4j.createFromArray(new float[][]{{1.0f},{ 0.0f}, {0.0f}});
        assertEquals(y_exp, y);

        y = base.oneHot(x, 1, 0, 1.0, 0.0, DataType.DOUBLE);
        y_exp = Nd4j.createFromArray(new double[][]{{1.0, 0.0, 0.0}});
        assertEquals(y_exp, y); //TODO: Looks like we're getting back the wrong datatype.
    }

    @Test
    public void testOnesLike() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[]{3, 3});
        INDArray y = base.onesLike(x);
        INDArray  y_exp = Nd4j.createFromArray(1.0, 1.0);
        assertEquals(y_exp, y);

        y = base.onesLike(x, DataType.INT64);
        y_exp = Nd4j.createFromArray(1L, 1L);
        assertEquals(y_exp, y); //TODO: Getting back a double array, not a long.    https://github.com/eclipse/deeplearning4j/issues/8605
    }

    @Test
    public void testPermute() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.createFromArray(1.0, 2.0, 3.0);
        INDArray y = base.permute(x, 2, 0, 1);
        //TODO: fix. no output arrays were provided and calculateOutputShape failed to execute

    }

    @Test
    public void testProd() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.prod(x, 0);
        INDArray y_exp = Nd4j.createFromArray(0.0f, 0.0f, 0.0f);
        assertEquals(y_exp, y);

        y = base.prod(x, true, 0);
        y_exp = Nd4j.createFromArray(new float[][]{{0.0f, 0.0f, 0.0f}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testRange() {
        NDBase base = new NDBase();
        INDArray y = base.range(0.0, 3.0, 1.0, DataType.DOUBLE);
        INDArray y_exp = Nd4j.createFromArray(0.0, 1.0, 2.0);
        assertEquals(y_exp, y); //TODO: Asked for DOUBLE, got back a FLOAT Array.
    }

    @Test
    public void testRank() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.eye(3);
        INDArray y = base.rank(x);
        INDArray y_exp = Nd4j.scalar(2);
        System.out.println(y);
        assertEquals(y_exp, y);
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
        assertEquals(y, array);
        assertNotSame(y, array); //TODO: fails.
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
        assertEquals(y_exp, y);
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
        assertEquals(y_exp, y);
    }

    @Test
    public void testScalarMax() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.scalarMax(x, 5.0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{ 5.0, 5.0, 5.0},{5.0, 5.0, 6.0}, { 7.0, 8.0, 9.0} } );
        assertEquals(y_exp, y);
        //System.out.println(y);
    }

    @Test
    public void testScalarMin() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.scalarMin(x, 5.0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{ 1.0, 2.0, 3.0},{4.0, 5.0, 5.0}, { 5.0, 5.0, 5.0} } );
        assertEquals(y_exp, y);
    }

    @Test
    public void testScalarSet() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.create(new double[] {1, 2, 0, 4, 5});
        INDArray y = base.scalarSet(x, 1.0);
        INDArray y_exp = Nd4j.ones(DataType.DOUBLE, 5);
        assertEquals(y_exp, y);
    }

    @Test
    public void testScatterAdd() {
        NDBase base = new NDBase();

        //from testScatterOpGradients.
        INDArray x = Nd4j.ones(DataType.DOUBLE, 20, 10);
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT32);
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
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT32);
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
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT32);
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
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT32);
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
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT32);
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
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT32);
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
        INDArray indices = Nd4j.create(new double[]{3, 4, 5, 10, 18}).castTo(DataType.INT32);
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
        assertEquals(y_exp, y);
    }

    @Test
    public void testSize() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(3,3);
        INDArray y = base.size(x);
        assertEquals(Nd4j.scalar(9L), y);
    }

    @Test
    public void testSizeAt() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(10,20, 30);
        INDArray y = base.sizeAt(x, 1);
        assertEquals(Nd4j.scalar(20L), y);
    }

    @Test
    public void testSlice() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 6).reshape(2, 3);
        INDArray y = base.slice(x, new int[]{0,1}, 2,1);
        INDArray y_exp = Nd4j.create(new double[][]{{2.0}, {5.0}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testSquaredNorm() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3, 3);
        INDArray y = base.squaredNorm(x, 0);
        INDArray y_exp = Nd4j.createFromArray(66.0, 93.0, 126.0);
        assertEquals(y_exp, y);

        y = base.squaredNorm(x, true, 0);
        y_exp = Nd4j.createFromArray(new double[][]{{66.0, 93.0, 126.0}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testSqueeze() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 4);
        INDArray y = base.squeeze(x,2);
        INDArray y_exp = Nd4j.createFromArray(1.0, 2.0, 4.0); //based on code comments
        assertEquals(y_exp, y); //fails. op does nothing.
    }

    @Test
    public void testStack() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 3);
        INDArray y = base.stack(x, 1);
        // TODO: Op definition looks wrong. Compare stack in Nd4j.
    }

    @Test
    public void testStandardDeviation() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 4);
        INDArray y = base.standardDeviation(x, false, 0);
        assertEquals(Nd4j.scalar(1.118034), y);

        x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3,3);
        y = base.standardDeviation(x, false, true, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{2.4494898, 2.4494898, 2.4494898}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testStridedSlice() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3,3);
        INDArray y = base.stridedSlice(x, new int[]{0,1}, new int[] {2,2}, 2,1);

        INDArray y_exp = Nd4j.createFromArray(new double[][]{{2.0, 3.0}, {8.0, 9.0}});
        assertEquals(y_exp, y); //TODO: Not getting the expected answer. (reproducing the code sample in comments).
    }

    @Test
    public void testSum() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3,3);
        INDArray y = base.sum(x, 0);
        INDArray y_exp = Nd4j.createFromArray(12.0, 15.0, 18.0);
        assertEquals(y_exp, y);

        y = base.sum(x, true, 0);
        assertEquals(y_exp.reshape(1,3), y);
    }

    @Test
    public void testTensorMul() {
        assertTrue(false); //TODO: Op not implemented.
    }

    @Test
    public void testTile() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 4).reshape(2,2);
        INDArray repeat = Nd4j.create(2, 3);
        //INDArray y = base.tile(x, repeat); // the sample from the code docs.
        //TODO: org.nd4j.linalg.exception.ND4JIllegalStateException: Op name tile - no output arrays were provided and calculateOutputShape failed to execute

        INDArray y = base.tile(x, 2); // the sample from the code docs.
        // TODO: error. TILE op: this op requires repeats vector, either as IArgs or second array with length equal to rank of input array to be tiled !
        // org.nd4j.linalg.exception.ND4JIllegalStateException: Op name tile - no output arrays were provided and calculateOutputShape failed to execute
        System.out.println(y);
    }

    @Test
    public void testTranspose() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3,3);
        INDArray y = base.transpose(x);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{1.0, 4.0, 7.0}, {2.0, 5.0, 8.0}, {3.0, 6.0, 9.0}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testUnsegmentMax() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.createFromArray(1,3,2,6,4,9,8);
        INDArray segmentIDs = Nd4j.createFromArray(1,0,2,0,1,1,2);
        INDArray y = base.unsortedSegmentMax(x, segmentIDs, 3);
        INDArray y_exp = Nd4j.createFromArray(6,9,8);
        assertEquals(y_exp, y);
    }

    @Test
    public void testUnsegmentMean() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.createFromArray(1,3,2,6,4,9,8);
        INDArray segmentIDs = Nd4j.createFromArray(1,0,2,0,1,1,2);
        INDArray y = base.unsortedSegmentMean(x, segmentIDs, 3);
        // TODO: Op [unsorted_segment_mean] failed check for output [0], DataType: [INT32];
        //    leaving this in here for a moment. as everything works fine with unsortedSegmentMax, min and prod.
        INDArray y_exp = Nd4j.createFromArray(4.5,4.666, 5.0);
        assertEquals(y_exp, y);
    }

    @Test
    public void testUnsegmentedMin() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.createFromArray(1,3,2,6,4,9,8);
        INDArray segmentIDs = Nd4j.createFromArray(1,0,2,0,1,1,2);
        INDArray y = base.unsortedSegmentMin(x, segmentIDs, 3);
        INDArray y_exp = Nd4j.createFromArray(3,1,2);
        assertEquals(y_exp, y);
    }

    @Test
    public void testUnsegmentProd() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.createFromArray(1,3,2,6,4,9,8);
        INDArray segmentIDs = Nd4j.createFromArray(1,0,2,0,1,1,2);
        INDArray y = base.unsortedSegmentProd(x, segmentIDs, 3);
        INDArray y_exp = Nd4j.createFromArray(18,36,16);
        assertEquals(y_exp, y);
    }

    @Test
    public void testUnsortedSegmentSqrtN() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.createFromArray(1,3,2,6,4,9,8);
        INDArray segmentIDs = Nd4j.createFromArray(1,0,2,0,1,1,2);
        INDArray y = base.unsortedSegmentSqrtN(x, segmentIDs, 3);
        // Op [unsorted_segment_sqrt_n] failed check for input [0], DataType: [INT32]
        //    leaving this in here for a moment. as everything works fine with unsortedSegmentMax, min and prod
    }

    @Test
    public void testUnsortedSegmentSum() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.createFromArray(1,3,2,6,4,9,8);
        INDArray segmentIDs = Nd4j.createFromArray(1,0,2,0,1,1,2);
        INDArray y = base.unsortedSegmentSum(x, segmentIDs, 3);
        INDArray y_exp = Nd4j.createFromArray(9,14,10);
        assertEquals(y_exp, y);
    }

    @Test
    public void testVariance() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 4);
        INDArray y = base.variance(x, false, 0);
        assertEquals(Nd4j.scalar(1.250), y);

        x = Nd4j.linspace(DataType.DOUBLE, 1.0, 1.0, 9).reshape(3,3);
        y = base.variance(x, false, true, 0);
        INDArray y_exp = Nd4j.createFromArray(new double[][]{{6.0, 6.0, 6.0}});
        assertEquals(y_exp, y);
    }

    @Test
    public void testZerosLike() {
        NDBase base = new NDBase();
        INDArray x = Nd4j.zeros(3,3);
        INDArray y = base.zerosLike(x);
        assertEquals(x, y);
        assertNotSame(x, y);
    }

}
