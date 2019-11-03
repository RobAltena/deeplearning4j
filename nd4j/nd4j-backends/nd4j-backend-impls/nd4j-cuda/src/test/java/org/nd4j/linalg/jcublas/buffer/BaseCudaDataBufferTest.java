package org.nd4j.linalg.jcublas.buffer;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.nd4j.jita.allocator.impl.AtomicAllocator;
import org.nd4j.jita.workspace.CudaWorkspace;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.memory.conf.WorkspaceConfiguration;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

@Slf4j
public class BaseCudaDataBufferTest {

    @Before
    public void setUp() {
        //
    }

    @Test
    public void testBasicAllocation_1() {
        val array = Nd4j.create(DataType.FLOAT, 5);

        // basic validation
        assertNotNull(array);
        assertNotNull(array.data());
        assertNotNull(((BaseCudaDataBuffer) array.data()).getOpaqueDataBuffer());

        // shape part
        assertArrayEquals(new long[]{1, 5, 1, 8192, 1, 99}, array.shapeInfoJava());
        assertArrayEquals(new long[]{1, 5, 1, 8192, 1, 99}, array.shapeInfoDataBuffer().asLong());

        // arrat as full of zeros at this point
        assertArrayEquals(new float[] {0.f, 0.f, 0.f, 0.f, 0.f}, array.data().asFloat(), 1e-5f);
    }

    @Test
    public void testBasicAllocation_2() {
        val array = Nd4j.createFromArray(1.f, 2.f, 3.f, 4.f, 5.f);

        // basic validation
        assertNotNull(array);
        assertNotNull(array.data());
        assertNotNull(((BaseCudaDataBuffer) array.data()).getOpaqueDataBuffer());

        // shape part
        assertArrayEquals(new long[]{1, 5, 1, 8192, 1, 99}, array.shapeInfoJava());
        assertArrayEquals(new long[]{1, 5, 1, 8192, 1, 99}, array.shapeInfoDataBuffer().asLong());

        // arrat as full of values at this point
        assertArrayEquals(new float[] {1.f, 2.f, 3.f, 4.f, 5.f}, array.data().asFloat(), 1e-5f);
    }

    @Test
    public void testBasicView_1() {
        val array = Nd4j.createFromArray(1.f, 2.f, 3.f, 4.f, 5.f, 6.f).reshape(3, 2);

        // basic validation
        assertNotNull(array);
        assertNotNull(array.data());
        assertNotNull(((BaseCudaDataBuffer) array.data()).getOpaqueDataBuffer());

        // checking TAD equality
        val row = array.getRow(1);
        assertArrayEquals(new float[]{3.0f, 4.0f}, row.data().dup().asFloat(), 1e-5f);
    }

    @Test
    public void testSerDe_1() throws Exception {
        val array = Nd4j.createFromArray(1.f, 2.f, 3.f, 4.f, 5.f, 6.f);
        val baos = new ByteArrayOutputStream();

        Nd4j.write(baos, array);
        INDArray restored = Nd4j.read(new ByteArrayInputStream(baos.toByteArray()));

        // basic validation
        assertNotNull(restored);
        assertNotNull(restored.data());
        assertNotNull(((BaseCudaDataBuffer) restored.data()).getOpaqueDataBuffer());

        // shape part
        assertArrayEquals(new long[]{1, 6, 1, 8192, 1, 99}, restored.shapeInfoJava());
        assertArrayEquals(new long[]{1, 6, 1, 8192, 1, 99}, restored.shapeInfoDataBuffer().asLong());

        // data equality
        assertArrayEquals(array.data().asFloat(), restored.data().asFloat(), 1e-5f);
    }

    @Test
    public void testBasicOpInvocation_1() {
        val array1 = Nd4j.createFromArray(1.f, 2.f, 3.f, 4.f, 5.f, 6.f);
        val array2 = Nd4j.createFromArray(1.f, 2.f, 3.f, 4.f, 5.f, 6.f);

        assertEquals(array1, array2);
    }

    @Test
    public void testBasicOpInvocation_2() {
        val array1 = Nd4j.createFromArray(1.f, 200.f, 3.f, 4.f, 5.f, 6.f);
        val array2 = Nd4j.createFromArray(1.f, 2.f, 3.f, 4.f, 5.f, 6.f);

        assertNotEquals(array1, array2);
    }
}