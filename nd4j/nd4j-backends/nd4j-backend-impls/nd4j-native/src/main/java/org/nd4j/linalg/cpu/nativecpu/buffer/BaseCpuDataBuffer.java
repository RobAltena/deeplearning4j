/*******************************************************************************
 * Copyright (c) 2015-2019 Skymind, Inc.
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

package org.nd4j.linalg.cpu.nativecpu.buffer;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.indexer.*;
import org.nd4j.linalg.api.buffer.BaseDataBuffer;
import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.buffer.DataType;
import org.nd4j.linalg.api.buffer.util.AllocUtil;
import org.nd4j.linalg.api.memory.MemoryWorkspace;

import java.nio.ByteBuffer;

/**
 * Base implementation for DataBuffer for CPU-like backend
 *
 * @author raver119@gmail.com
 */
public abstract class BaseCpuDataBuffer extends BaseDataBuffer {

    protected BaseCpuDataBuffer() {

    }

    /**
     * Create a data buffer from
     * the given length
     *
     * @param buffer
     * @param length
     */
    public BaseCpuDataBuffer(ByteBuffer buffer, long length) {
        if (length < 1)
            throw new IllegalArgumentException("Length must be >= 1");
        initTypeAndSize();

        this.length = length;
        allocationMode = AllocUtil.getAllocationModeFromContext();

        switch (dataType()){
            case DOUBLE:
                pointer = new DoublePointer(buffer.asDoubleBuffer());
                setIndexer(DoubleIndexer.create((DoublePointer) pointer));
                break;
            case FLOAT:
                pointer = new FloatPointer(buffer.asFloatBuffer());
                setIndexer(FloatIndexer.create((FloatPointer) pointer));
                break;
            case HALF:
                pointer = new ShortPointer(buffer.asShortBuffer());
                setIndexer(HalfIndexer.create((ShortPointer) pointer));
                break;
            case LONG:
                pointer = new LongPointer(buffer.asLongBuffer());
                setIndexer(LongIndexer.create((LongPointer) pointer));
                break;
            case INT:
                pointer = new IntPointer(buffer.asIntBuffer());
                setIndexer(IntIndexer.create((IntPointer) pointer));
                break;
            case SHORT:
                pointer = new ShortPointer(buffer.asShortBuffer());
                setIndexer(ShortIndexer.create((ShortPointer) pointer));
                break;
            case UBYTE: //Fall through
            case BYTE:
                pointer = new BytePointer(buffer);
                setIndexer(UByteIndexer.create((BytePointer)pointer));
                break;
            case BOOL:
                pointer = new BooleanPointer(length());
                setIndexer(BooleanIndexer.create((BooleanPointer) pointer));
                break;
            case UTF8:
                pointer = new BytePointer(length());
                setIndexer(ByteIndexer.create((BytePointer) pointer));
                break;
            case BFLOAT16:
                pointer = new ShortPointer(length());
                setIndexer(Bfloat16Indexer.create((ShortPointer) pointer));
                break;
            case UINT16:
                pointer = new ShortPointer(length());
                setIndexer(UShortIndexer.create((ShortPointer) pointer));
                break;
            case UINT32:
                pointer = new IntPointer(length());
                // FIXME: we need unsigned indexer here
                setIndexer(IntIndexer.create((IntPointer) pointer));
                break;
            case UINT64:
                pointer = new LongPointer(length());
                // FIXME: we need unsigned indexer here
                setIndexer(LongIndexer.create((LongPointer) pointer));
                break;
        }
    }

    /**
     * Create a data buffer from
     * the given length
     *
     * @param buffer
     * @param length
     */
    public BaseCpuDataBuffer(ByteBuffer buffer, long length, long offset) {
        this(buffer, length);
        this.offset = offset;
        this.originalOffset = offset;
        this.underlyingLength = length;
        this.length = length - offset;

    }


    /**
     *
     * @param length
     * @param elementSize
     */
    public BaseCpuDataBuffer(long length, int elementSize) {
        if (length < 1)
            throw new IllegalArgumentException("Length must be >= 1");
        initTypeAndSize();
        allocationMode = AllocUtil.getAllocationModeFromContext();
        this.length = length;
        this.underlyingLength = length;
        this.elementSize = (byte) elementSize;

        if (dataType() == DataType.DOUBLE) {
            pointer = new DoublePointer(length);
            indexer = DoubleIndexer.create((DoublePointer) pointer);
        } else if (dataType() == DataType.FLOAT) {
            pointer = new FloatPointer(length);
            setIndexer(FloatIndexer.create((FloatPointer) pointer));
        } else if (dataType() == DataType.INT) {
            pointer = new IntPointer(length);
            setIndexer(IntIndexer.create((IntPointer) pointer));
        } else if (dataType() == DataType.LONG) {
            pointer = new LongPointer(length);
            setIndexer(LongIndexer.create((LongPointer) pointer));
        } else if (dataType() == DataType.SHORT) {
            pointer = new ShortPointer(length);
            setIndexer(ShortIndexer.create((ShortPointer) pointer));
        } else if (dataType() == DataType.BYTE) {
            pointer = new BytePointer(length);
            setIndexer(ByteIndexer.create((BytePointer) pointer));
        } else if (dataType() == DataType.UBYTE) {
            pointer = new BytePointer(length);
            setIndexer(UByteIndexer.create((BytePointer) pointer));
        } else if (dataType() == DataType.UTF8) {
            pointer = new LongPointer(length);
            setIndexer(LongIndexer.create((LongPointer) pointer));
        }
    }

    /**
     *
     * @param data
     * @param length
     */
    public BaseCpuDataBuffer(byte[] data, long length) {
        this(ByteBuffer.wrap(data), length);
    }

    /**
     *
     * @param length
     * @param elementSize
     */
    public BaseCpuDataBuffer(int length, int elementSize, long offset) {
        this(length, elementSize);
        this.offset = offset;
        this.originalOffset = offset;
        this.length = length - offset;
        this.underlyingLength = length;
    }


    protected BaseCpuDataBuffer(DataBuffer underlyingBuffer, long length, long offset) {
        super(underlyingBuffer, length, offset);
    }

    /**
     * Instantiate a buffer with the given length
     *
     * @param length the length of the buffer
     */
    protected BaseCpuDataBuffer(long length) {
        this(length, true);
    }

    protected BaseCpuDataBuffer(long length, boolean initialize) {
        if (length < 0)
            throw new IllegalArgumentException("Length must be >= 0");
        initTypeAndSize();
        this.length = length;
        this.underlyingLength = length;
        allocationMode = AllocUtil.getAllocationModeFromContext();
        if (length < 0)
            throw new IllegalArgumentException("Unable to create a buffer of length <= 0");

        if (dataType() == DataType.DOUBLE) {
            pointer = new DoublePointer(length());
            indexer = DoubleIndexer.create((DoublePointer) pointer);
            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.FLOAT) {
            pointer = new FloatPointer(length());
            setIndexer(FloatIndexer.create((FloatPointer) pointer));

            if (initialize)
                fillPointerWithZero();

        } else if (dataType() == DataType.HALF) {
            pointer = new ShortPointer(length());
            setIndexer(HalfIndexer.create((ShortPointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.BFLOAT16) {
            pointer = new ShortPointer(length());
            setIndexer(Bfloat16Indexer.create((ShortPointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.INT) {
            pointer = new IntPointer(length());
            setIndexer(IntIndexer.create((IntPointer) pointer));
            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.LONG) {
            pointer = new LongPointer(length());
            setIndexer(LongIndexer.create((LongPointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.BYTE) {
            pointer = new BytePointer(length());
            setIndexer(ByteIndexer.create((BytePointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.SHORT) {
            pointer = new ShortPointer(length());
            setIndexer(ShortIndexer.create((ShortPointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.UBYTE) {
            pointer = new BytePointer(length());
            setIndexer(UByteIndexer.create((BytePointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.UINT16) {
            pointer = new ShortPointer(length());
            setIndexer(UShortIndexer.create((ShortPointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.UINT32) {
            pointer = new IntPointer(length());
            // FIXME: we need unsigned indexer here
            setIndexer(IntIndexer.create((IntPointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.UINT64) {
            pointer = new LongPointer(length());
            // FIXME: we need unsigned indexer here
            setIndexer(LongIndexer.create((LongPointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.BOOL) {
            pointer = new BooleanPointer(length());
            setIndexer(BooleanIndexer.create((BooleanPointer) pointer));

            if (initialize)
                fillPointerWithZero();
        } else if (dataType() == DataType.UTF8) {
            pointer = new BytePointer(length());
            setIndexer(ByteIndexer.create((BytePointer) pointer));

            if (initialize)
                fillPointerWithZero();
        }

        //// log.info("Creating new buffer of size: {}; dtype: {}; A", length, dataType());
    }

    protected BaseCpuDataBuffer(long length, boolean initialize, MemoryWorkspace workspace) {
        if (length < 1)
            throw new IllegalArgumentException("Length must be >= 1");
        initTypeAndSize();
        this.length = length;
        this.underlyingLength = length;
        allocationMode = AllocUtil.getAllocationModeFromContext();



        if (length < 0)
            throw new IllegalArgumentException("Unable to create a buffer of length <= 0");

        if (dataType() == DataType.DOUBLE) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asDoublePointer(); //new DoublePointer(length());
            indexer = DoubleIndexer.create((DoublePointer) pointer);

        } else if (dataType() == DataType.FLOAT) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asFloatPointer(); //new FloatPointer(length());
            setIndexer(FloatIndexer.create((FloatPointer) pointer));

        } else if (dataType() == DataType.HALF) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asShortPointer(); //new FloatPointer(length());
            setIndexer(HalfIndexer.create((ShortPointer) pointer));

        } else if (dataType() == DataType.BFLOAT16) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asShortPointer(); //new FloatPointer(length());
            setIndexer(Bfloat16Indexer.create((ShortPointer) pointer));
        } else if (dataType() == DataType.INT) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asIntPointer(); //new IntPointer(length());
            setIndexer(IntIndexer.create((IntPointer) pointer));

        } else if (dataType() == DataType.UINT32) {
            attached = true;
            parentWorkspace = workspace;

            // FIXME: need unsigned indexer here
            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asIntPointer(); //new IntPointer(length());
            setIndexer(IntIndexer.create((IntPointer) pointer));

        } else if (dataType() == DataType.UINT64) {
            attached = true;
            parentWorkspace = workspace;

            // FIXME: need unsigned indexer here
            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asLongPointer(); //new IntPointer(length());
            setIndexer(LongIndexer.create((LongPointer) pointer));

        } else if (dataType() == DataType.LONG) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asLongPointer(); //new LongPointer(length());
            setIndexer(LongIndexer.create((LongPointer) pointer));
        } else if (dataType() == DataType.BYTE) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asBytePointer(); //new LongPointer(length());
            setIndexer(ByteIndexer.create((BytePointer) pointer));
        } else if (dataType() == DataType.UBYTE) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asBytePointer(); //new LongPointer(length());
            setIndexer(UByteIndexer.create((BytePointer) pointer));
        } else if (dataType() == DataType.UINT16) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asShortPointer(); //new IntPointer(length());
            setIndexer(UShortIndexer.create((ShortPointer) pointer));

        } else if (dataType() == DataType.SHORT) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asShortPointer(); //new LongPointer(length());
            setIndexer(ShortIndexer.create((ShortPointer) pointer));
        } else if (dataType() == DataType.BOOL) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asBoolPointer(); //new LongPointer(length());
            setIndexer(BooleanIndexer.create((BooleanPointer) pointer));
        } else if (dataType() == DataType.UTF8) {
            attached = true;
            parentWorkspace = workspace;

            pointer = workspace.alloc(length * getElementSize(), dataType(), initialize).asLongPointer(); //new LongPointer(length());
            setIndexer(LongIndexer.create((LongPointer) pointer));
        }

        workspaceGenerationId = workspace.getGenerationId();
    }

    public BaseCpuDataBuffer(Pointer pointer, Indexer indexer, long length) {
        super(pointer, indexer, length);
    }

    /**
     *
     * @param data
     * @param copy
     */
    public BaseCpuDataBuffer(float[] data, boolean copy, long offset) {
        this(data, copy);
        this.offset = offset;
        this.originalOffset = offset;
        this.length = data.length - offset;
        this.underlyingLength = data.length;

    }

    public BaseCpuDataBuffer(float[] data, boolean copy, long offset, MemoryWorkspace workspace) {
        this(data, copy, workspace);
        this.offset = offset;
        this.originalOffset = offset;
        this.length = data.length - offset;
        this.underlyingLength = data.length;
    }

    /**
     *
     * @param data
     * @param copy
     */
    public BaseCpuDataBuffer(float[] data, boolean copy) {
        allocationMode = AllocUtil.getAllocationModeFromContext();
        initTypeAndSize();

        pointer = new FloatPointer(data);

        setIndexer(FloatIndexer.create((FloatPointer) pointer));
        //wrappedBuffer = pointer.asByteBuffer();

        length = data.length;
        underlyingLength = data.length;
    }

    public BaseCpuDataBuffer(float[] data, boolean copy, MemoryWorkspace workspace) {
        allocationMode = AllocUtil.getAllocationModeFromContext();
        length = data.length;
        underlyingLength = data.length;
        attached = true;
        parentWorkspace = workspace;

        initTypeAndSize();

        //log.info("Allocating FloatPointer from array of {} elements", data.length);

        pointer = workspace.alloc(data.length * getElementSize(), dataType(), false).asFloatPointer().put(data);
        workspaceGenerationId = workspace.getGenerationId();
        setIndexer(FloatIndexer.create((FloatPointer) pointer));
        //wrappedBuffer = pointer.asByteBuffer();
    }

    public BaseCpuDataBuffer(double[] data, boolean copy, MemoryWorkspace workspace) {
        allocationMode = AllocUtil.getAllocationModeFromContext();
        length = data.length;
        underlyingLength = data.length;
        attached = true;
        parentWorkspace = workspace;

        initTypeAndSize();

        //log.info("Allocating FloatPointer from array of {} elements", data.length);

        pointer = workspace.alloc(data.length * getElementSize(), dataType(), false).asDoublePointer().put(data);
        workspaceGenerationId = workspace.getGenerationId();
        indexer = DoubleIndexer.create((DoublePointer) pointer);
        //wrappedBuffer = pointer.asByteBuffer();
    }


    public BaseCpuDataBuffer(int[] data, boolean copy, MemoryWorkspace workspace) {
        allocationMode = AllocUtil.getAllocationModeFromContext();
        length = data.length;
        underlyingLength = data.length;
        attached = true;
        parentWorkspace = workspace;

        initTypeAndSize();

        //log.info("Allocating FloatPointer from array of {} elements", data.length);

        pointer = workspace.alloc(data.length * getElementSize(), dataType(), false).asIntPointer().put(data);
        workspaceGenerationId = workspace.getGenerationId();
        indexer = IntIndexer.create((IntPointer) pointer);
        //wrappedBuffer = pointer.asByteBuffer();
    }

    public BaseCpuDataBuffer(long[] data, boolean copy, MemoryWorkspace workspace) {
        allocationMode = AllocUtil.getAllocationModeFromContext();
        length = data.length;
        underlyingLength = data.length;
        attached = true;
        parentWorkspace = workspace;

        initTypeAndSize();

        //log.info("Allocating FloatPointer from array of {} elements", data.length);

        pointer = workspace.alloc(data.length * getElementSize(), dataType(), false).asLongPointer().put(data);
        workspaceGenerationId = workspace.getGenerationId();
        indexer = LongIndexer.create((LongPointer) pointer);
        //wrappedBuffer = pointer.asByteBuffer();
    }


    /**
     *
     * @param data
     * @param copy
     */
    public BaseCpuDataBuffer(double[] data, boolean copy, long offset) {
        this(data, copy);
        this.offset = offset;
        this.originalOffset = offset;
        this.underlyingLength = data.length;
        this.length = underlyingLength - offset;
    }

    public BaseCpuDataBuffer(double[] data, boolean copy, long offset, MemoryWorkspace workspace) {
        this(data, copy, workspace);
        this.offset = offset;
        this.originalOffset = offset;
        this.underlyingLength = data.length;
        this.length = underlyingLength - offset;
    }

    /**
     *
     * @param data
     * @param copy
     */
    public BaseCpuDataBuffer(double[] data, boolean copy) {
        allocationMode = AllocUtil.getAllocationModeFromContext();
        initTypeAndSize();

        pointer = new DoublePointer(data);
        indexer = DoubleIndexer.create((DoublePointer) pointer);
        //wrappedBuffer = pointer.asByteBuffer();

        length = data.length;
        underlyingLength = data.length;
    }


    /**
     *
     * @param data
     * @param copy
     */
    public BaseCpuDataBuffer(int[] data, boolean copy, long offset) {
        this(data, copy);
        this.offset = offset;
        this.originalOffset = offset;
        this.length = data.length - offset;
        this.underlyingLength = data.length;
    }

    /**
     *
     * @param data
     * @param copy
     */
    public BaseCpuDataBuffer(int[] data, boolean copy) {
        allocationMode = AllocUtil.getAllocationModeFromContext();
        initTypeAndSize();

        pointer = new IntPointer(data);
        setIndexer(IntIndexer.create((IntPointer) pointer));

        length = data.length;
        underlyingLength = data.length;

        // // log.info("Creating new buffer of size: {}; dtype: {}; B", data.length, dataType());
    }

    /**
     *
     * @param data
     * @param copy
     */
    public BaseCpuDataBuffer(long[] data, boolean copy) {
        allocationMode = AllocUtil.getAllocationModeFromContext();
        initTypeAndSize();

        pointer = new LongPointer(data);
        setIndexer(LongIndexer.create((LongPointer) pointer));

        length = data.length;
        underlyingLength = data.length;
    }


    /**
     *
     * @param data
     */
    public BaseCpuDataBuffer(double[] data) {
        this(data, true);
    }

    /**
     *
     * @param data
     */
    public BaseCpuDataBuffer(int[] data) {
        this(data, true);
    }

    /**
     *
     * @param data
     */
    public BaseCpuDataBuffer(float[] data) {
        this(data, true);
    }

    public BaseCpuDataBuffer(float[] data, MemoryWorkspace workspace) {
        this(data, true, workspace);
    }


}
