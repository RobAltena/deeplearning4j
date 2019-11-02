package org.nd4j.linalg.jcublas.buffer;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.nd4j.jita.allocator.impl.AtomicAllocator;
import org.nd4j.jita.workspace.CudaWorkspace;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.memory.conf.WorkspaceConfiguration;
import org.nd4j.linalg.factory.Nd4j;

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
    public void testSerDe_1() {

    }
}