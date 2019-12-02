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

//================== GENERATED CODE - DO NOT MODIFY THIS FILE ==================

package org.nd4j.linalg.factory.ops;

import static org.nd4j.linalg.factory.NDValidation.isSameType;

import org.nd4j.base.Preconditions;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.NDValidation;
import org.nd4j.linalg.factory.Nd4j;

public class NDBase {
  public NDBase() {
  }

  /**
   * Boolean and array reduction operation, optionally along specified dimensions<br>
   *
   * @param x Input variable (NUMERIC type)
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed (Size: AtLeast(min=0))
   * @return output reduced array of rank (input rank - num dimensions) (NUMERIC type)
   */
  public INDArray all(INDArray x, int... dimensions) {
    NDValidation.validateNumerical("all", "x", x);
    Preconditions.checkArgument(dimensions.length >= 0, "dimensions has incorrect size/length. Expected: dimensions.length >= 0, got %s", dimensions.length);
    return Nd4j.exec(new org.nd4j.linalg.api.ops.impl.reduce.bool.All(x, dimensions));
  }

  /**
   * Boolean or array reduction operation, optionally along specified dimensions<br>
   *
   * @param x  Input variable (NUMERIC type)
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed (Size: AtLeast(min=0))
   * @return output reduced array of rank (input rank - num dimensions) (NUMERIC type)
   */
  public INDArray any(INDArray x, int... dimensions) {
    NDValidation.validateNumerical("any", "x", x);
    Preconditions.checkArgument(dimensions.length >= 0, "dimensions has incorrect size/length. Expected: dimensions.length >= 0, got %s", dimensions.length);
    return Nd4j.exec(new org.nd4j.linalg.api.ops.impl.reduce.bool.Any(x, dimensions));
  }

  /**
   * Argmax array reduction operation, optionally along specified dimensions.<br>
   * Output values are the index of the maximum value of each slice along the specified dimension.<br>
   * <br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   *
   * @param in Input variable (NUMERIC type)
   * @param keepDims If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed (Size: AtLeast(min=0))
   * @return output reduced array of rank (input rank - num dimensions) if keepDims = false, or
   *  of rank (input rank) if keepdims = true (NUMERIC type)
   */
  public INDArray argmax(INDArray in, boolean keepDims, int... dimensions) {
    NDValidation.validateNumerical("argmax", "in", in);
    Preconditions.checkArgument(dimensions.length >= 0, "dimensions has incorrect size/length. Expected: dimensions.length >= 0, got %s", dimensions.length);
    return Nd4j.exec(new org.nd4j.linalg.api.ops.impl.indexaccum.IMax(in, keepDims, dimensions));
  }

  /**
   * Argmax array reduction operation, optionally along specified dimensions.<br>
   * Output values are the index of the maximum value of each slice along the specified dimension.<br>
   * <br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   *
   * @param in Input variable (NUMERIC type)
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed (Size: AtLeast(min=0))
   * @return output reduced array of rank (input rank - num dimensions) if keepDims = false, or
   *  of rank (input rank) if keepdims = true (NUMERIC type)
   */
  public INDArray argmax(INDArray in, int... dimensions) {
    NDValidation.validateNumerical("argmax", "in", in);
    Preconditions.checkArgument(dimensions.length >= 0, "dimensions has incorrect size/length. Expected: dimensions.length >= 0, got %s", dimensions.length);
    return Nd4j.exec(new org.nd4j.linalg.api.ops.impl.indexaccum.IMax(in, dimensions));
  }

  /**
   * Argmax array reduction operation, optionally along specified dimensions.<br>
   * Output values are the index of the maximum value of each slice along the specified dimension<br>
   *
   * @param in Input variable (NUMERIC type)
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed (Size: AtLeast(min=0))
   * @return output Reduced array of rank (input rank - num dimensions) (NUMERIC type)
   */
  public INDArray argmax(INDArray in, int... dimensions) {
    NDValidation.validateNumerical("argmax", "in", in);
    Preconditions.checkArgument(dimensions.length >= 0, "dimensions has incorrect size/length. Expected: dimensions.length >= 0, got %s", dimensions.length);
    return Nd4j.exec(new org.nd4j.linalg.api.ops.impl.indexaccum.Argmax(in, dimensions));
  }

  /**
   * Argmin array reduction operation, optionally along specified dimensions.<br>
   * Output values are the index of the minimum value of each slice along the specified dimension<br>
   * <br>
   * @param in         Input variable<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray argmin(INDArray in, INDArray dimensions) {
    NDValidation.validateNumerical("argmin", "in", in);
    NDValidation.validateNumerical("argmin", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Argmin(in, dimensions))[0];
  }

  /**
   * Argmin array reduction operation, optionally along specified dimensions.<br>
   * Output values are the index of the minimum value of each slice along the specified dimension.<br>
   * <br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name       Name of the output variable<br>
   * @param in         Input variable<br>
   * @param keepDims   If true: keep the dimensions that are reduced on (as length 1). False: remove the reduction dimensions<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Output variable: reduced array of rank (input rank - num dimensions) if keepDims = false, or<br>
   * of rank (input rank) if keepdims = true<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray argmin(INDArray in, INDArray keepDims, INDArray dimensions) {
    NDValidation.validateNumerical("argmin", "in", in);
    NDValidation.validateNumerical("argmin", "keepDims", keepDims);
    NDValidation.validateNumerical("argmin", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Argmin(in, keepDims, dimensions))[0];
  }

  /**
   * Assign/copy op: out = x.assign(y). Supports broadcasting<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input variable x<br>
   * @param y    Input variable y<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray assign(INDArray x, INDArray y) {
    NDValidation.validateNumerical("assign", "x", x);
    NDValidation.validateNumerical("assign", "y", y);
    return Nd4j.exec(new TODO.Assign(x, y))[0];
  }

  /**
   * Return an array with equal shape to the input, but all elements set to 'value'<br>
   * <br>
   * @param name  Name of the output variable<br>
   * @param in    Input variable<br>
   * @param value Value to set<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param value  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray assign(INDArray in, INDArray value) {
    NDValidation.validateNumerical("assign", "in", in);
    NDValidation.validateNumerical("assign", "value", value);
    return Nd4j.exec(new TODO.Assign(in, value))[0];
  }

  /**
   * Concatenate a set of inputs along the specified dimension.<br>
   * Note that inputs must have identical rank and identical dimensions, other than the dimension to stack on.<br>
   * For example, if 2 inputs have shape [a, x, c] and [a, y, c] and dimension = 1, then the output has shape [a, x+y, c]<br>
   * <br>
   * @param name      Name of the output variable<br>
   * @param dimension Dimension to concatenate on<br>
   * @param inputs    Input variables<br>
   * @return Output variable<br>
   * @see #stack(String, int, SDVariable...)<br>
   *     <br>
   *
   * @param dimension  (NUMERIC type)
   * @param inputs  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray concat(INDArray dimension, INDArray inputs) {
    NDValidation.validateNumerical("concat", "dimension", dimension);
    NDValidation.validateNumerical("concat", "inputs", inputs);
    return Nd4j.exec(new TODO.Concat(dimension, inputs))[0];
  }

  /**
   * Cumulative product operation.<br>
   * For input: [ a, b, c], output is:<br>
   * exclusize=false, reverse=false: [a, a*b, a*b*c]<br>
   * exclusive=true, reverse=false, [0, a, a*b]<br>
   * exclusive=false, reverse=true: [a*b*c, b*c, c]<br>
   * exclusive=true, reverse=true: [b*c, c, 0]<br><br>
   * <br>
   * @param name      Name of the output variable<br>
   * @param in        Input variable<br>
   * @param axis      Scalar axis argument for dimension to perform cumululative sum operations along<br>
   * @param exclusive If true: exclude the first value<br>
   * @param reverse   If true: reverse the direction of the accumulation<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param exclusive  (NUMERIC type)
   * @param reverse  (NUMERIC type)
   * @param axis  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray cumprod(INDArray in, INDArray exclusive, INDArray reverse, INDArray axis) {
    NDValidation.validateNumerical("cumprod", "in", in);
    NDValidation.validateNumerical("cumprod", "exclusive", exclusive);
    NDValidation.validateNumerical("cumprod", "reverse", reverse);
    NDValidation.validateNumerical("cumprod", "axis", axis);
    return Nd4j.exec(new TODO.Cumprod(in, exclusive, reverse, axis))[0];
  }

  /**
   * Cumulative sum operation.<br>
   * For input: [ a, b, c], output is:<br>
   * exclusize=false, reverse=false: [a, a+b, a+b+c]<br>
   * exclusive=true, reverse=false, [0, a, a+b]<br>
   * exclusive=false, reverse=true: [a+b+c, b+c, c]<br>
   * exclusive=true, reverse=true: [b+c, c, 0]<br><br>
   * <br>
   * @param name      Name of the output variable<br>
   * @param in        Input variable<br>
   * @param axis      Scalar axis argument for dimension to perform cumululative sum operations along<br>
   * @param exclusive If true: exclude the first value<br>
   * @param reverse   If true: reverse the direction of the accumulation<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param exclusive  (NUMERIC type)
   * @param reverse  (NUMERIC type)
   * @param axis  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray cumsum(INDArray in, INDArray exclusive, INDArray reverse, INDArray axis) {
    NDValidation.validateNumerical("cumsum", "in", in);
    NDValidation.validateNumerical("cumsum", "exclusive", exclusive);
    NDValidation.validateNumerical("cumsum", "reverse", reverse);
    NDValidation.validateNumerical("cumsum", "axis", axis);
    return Nd4j.exec(new TODO.Cumsum(in, exclusive, reverse, axis))[0];
  }

  /**
   * TODO doc string<br>
   * <br>
   * @param name<br>
   * @param x<br>
   * @param y<br>
   * @param dimensions<br>
   * @return<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray dot(INDArray x, INDArray y, INDArray dimensions) {
    NDValidation.validateNumerical("dot", "x", x);
    NDValidation.validateNumerical("dot", "y", y);
    NDValidation.validateNumerical("dot", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Dot(x, y, dimensions))[0];
  }

  /**
   * Dynamically partition the input variable values into the specified number of paritions, using the indices.<br>
   * Example:<br>
   * <pre><br>
   * {@code input = [1,2,3,4,5]<br>
   * numPartitions = 2<br>
   * partitions = [1,0,0,1,0]<br>
   * out[0] = [2,3,5]<br>
   * out[1] = [1,4] }<br>
   * </pre><br>
   * <br>
   * @param name          Names for the output variables. Length must be equal to numPartitions<br>
   * @param x             Input variable<br>
   * @param partitions    1D input with values 0 to numPartitions-1<br>
   * @param numPartitions Number of partitions, >= 1<br>
   * @return Output variables (equal in number to numPartitions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param partitions  (NUMERIC type)
   * @param numPartitions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray dynamicPartition(INDArray x, INDArray partitions, INDArray numPartitions) {
    NDValidation.validateNumerical("dynamicPartition", "x", x);
    NDValidation.validateNumerical("dynamicPartition", "partitions", partitions);
    NDValidation.validateNumerical("dynamicPartition", "numPartitions", numPartitions);
    return Nd4j.exec(new TODO.DynamicPartition(x, partitions, numPartitions))[0];
  }

  /**
   * Dynamically merge the specified input arrays into a single array, using the specified indices<br>
   * <br>
   * @param name    Name of the output variable<br>
   * @param indices Indices to use when merging. Must be >= 1, same length as input variables<br>
   * @param x       Input variables.<br>
   * @return Merged output variable<br>
   *     <br>
   *
   * @param indices  (NUMERIC type)
   * @param x  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray dynamicStitch(INDArray indices, INDArray x) {
    NDValidation.validateNumerical("dynamicStitch", "indices", indices);
    NDValidation.validateNumerical("dynamicStitch", "x", x);
    return Nd4j.exec(new TODO.DynamicStitch(indices, x))[0];
  }

  /**
   * Equals operation: elementwise x == y<br>
   * Returns an array with the same shape/size as the input, with values 1 where condition is satisfied, or<br>
   * value 0 otherwise<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input array<br>
   * @param y    Double value argument to use in operation<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray eq(INDArray x, INDArray y) {
    NDValidation.validateNumerical("eq", "x", x);
    NDValidation.validateNumerical("eq", "y", y);
    return Nd4j.exec(new TODO.Eq(x, y))[0];
  }

  /**
   * Equal to operation: elementwise x == y<br>
   * If x and y arrays have equal shape, the output shape is the same as these inputs.<br>
   * Note: supports broadcasting if x and y have different shapes and are broadcastable.<br>
   * Returns an array with values 1 where condition is satisfied, or value 0 otherwise.<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input 1<br>
   * @param y    Input 2<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray eq(INDArray x, INDArray y) {
    NDValidation.validateNumerical("eq", "x", x);
    NDValidation.validateNumerical("eq", "y", y);
    return Nd4j.exec(new TODO.Eq(x, y))[0];
  }

  /**
   * Reshape the input by adding a 1 at the specified location.<br>
   * For example, if input has shape [a, b], then output shape is:<br>
   * axis = 0: [1, a, b]<br>
   * axis = 1: [a, 1, b]<br>
   * axis = 2: [a, b, 1]<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input variable<br>
   * @param axis Axis to expand<br>
   * @return Output variable<br>
   * @see #squeeze(String, SDVariable, int)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param axis  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray expandDims(INDArray x, INDArray axis) {
    NDValidation.validateNumerical("expandDims", "x", x);
    NDValidation.validateNumerical("expandDims", "axis", axis);
    return Nd4j.exec(new TODO.ExpandDims(x, axis))[0];
  }

  /**
   * Generate an output variable with the specified (dynamic) shape with all elements set to the specified value<br>
   * <br>
   * @param name  Name of the output variable<br>
   * @param shape Shape: must be a 1D array/variable<br>
   * @param value Value to set all elements to<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param shape  (NUMERIC type)
   * @param dataType  (NUMERIC type)
   * @param value  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray fill(INDArray shape, INDArray dataType, INDArray value) {
    NDValidation.validateNumerical("fill", "shape", shape);
    NDValidation.validateNumerical("fill", "dataType", dataType);
    NDValidation.validateNumerical("fill", "value", value);
    return Nd4j.exec(new TODO.Fill(shape, dataType, value))[0];
  }

  /**
   * Gather slices from the input variable where the indices are specified as fixed int[] values.<br>
   * Output shape is same as input shape, except for axis dimension, which has size equal to indices.length.<br>
   * <br>
   * @param name    name of the output variable<br>
   * @param df      Input variable<br>
   * @param indices Indices to get<br>
   * @param axis    Axis that the indices refer to<br>
   * @return Output variable with slices pulled from the specified axis<br>
   *     <br>
   *
   * @param df  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @param axis  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray gather(INDArray df, INDArray indices, INDArray axis) {
    NDValidation.validateNumerical("gather", "df", df);
    NDValidation.validateNumerical("gather", "indices", indices);
    NDValidation.validateNumerical("gather", "axis", axis);
    return Nd4j.exec(new TODO.Gather(df, indices, axis))[0];
  }

  /**
   * Gather slices from the input variable where the indices are specified as dynamic SDVariable values.<br>
   * Output shape is same as input shape, except for axis dimension, which has size equal to indices.length.<br>
   * <br>
   * @param name    name of the output variable<br>
   * @param df      Input variable<br>
   * @param indices Indices to get slices for. Rank 0 or 1 input<br>
   * @param axis    Axis that the indices refer to<br>
   * @return Output variable with slices pulled from the specified axis<br>
   *     <br>
   *
   * @param df  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @param axis  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray gather(INDArray df, INDArray indices, INDArray axis) {
    NDValidation.validateNumerical("gather", "df", df);
    NDValidation.validateNumerical("gather", "indices", indices);
    NDValidation.validateNumerical("gather", "axis", axis);
    return Nd4j.exec(new TODO.Gather(df, indices, axis))[0];
  }

  /**
   * TODO doc string<br>
   * <br>
   * @param name<br>
   * @param df<br>
   * @param indices<br>
   * @return<br>
   *     <br>
   *
   * @param df  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray gatherNd(INDArray df, INDArray indices) {
    NDValidation.validateNumerical("gatherNd", "df", df);
    NDValidation.validateNumerical("gatherNd", "indices", indices);
    return Nd4j.exec(new TODO.GatherNd(df, indices))[0];
  }

  /**
   * Greater than operation: elementwise x > y<br>
   * Returns an array with the same shape/size as the input, with values 1 where condition is satisfied, or<br>
   * value 0 otherwise<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input array<br>
   * @param y    Double value argument to use in operation<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray gt(INDArray x, INDArray y) {
    NDValidation.validateNumerical("gt", "x", x);
    NDValidation.validateNumerical("gt", "y", y);
    return Nd4j.exec(new TODO.Gt(x, y))[0];
  }

  /**
   * Greater than operation: elementwise x > y<br>
   * If x and y arrays have equal shape, the output shape is the same as these inputs.<br>
   * Note: supports broadcasting if x and y have different shapes and are broadcastable.<br>
   * Returns an array with values 1 where condition is satisfied, or value 0 otherwise.<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input 1<br>
   * @param y    Input 2<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray gt(INDArray x, INDArray y) {
    NDValidation.validateNumerical("gt", "x", x);
    NDValidation.validateNumerical("gt", "y", y);
    return Nd4j.exec(new TODO.Gt(x, y))[0];
  }

  /**
   * Greater than or equals operation: elementwise x >= y<br>
   * Returns an array with the same shape/size as the input, with values 1 where condition is satisfied, or<br>
   * value 0 otherwise<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input array<br>
   * @param y    Double value argument to use in operation<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray gte(INDArray x, INDArray y) {
    NDValidation.validateNumerical("gte", "x", x);
    NDValidation.validateNumerical("gte", "y", y);
    return Nd4j.exec(new TODO.Gte(x, y))[0];
  }

  /**
   * Greater than or equal to operation: elementwise x >= y<br>
   * If x and y arrays have equal shape, the output shape is the same as these inputs.<br>
   * Note: supports broadcasting if x and y have different shapes and are broadcastable.<br>
   * Returns an array with values 1 where condition is satisfied, or value 0 otherwise.<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input 1<br>
   * @param y    Input 2<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray gte(INDArray x, INDArray y) {
    NDValidation.validateNumerical("gte", "x", x);
    NDValidation.validateNumerical("gte", "y", y);
    return Nd4j.exec(new TODO.Gte(x, y))[0];
  }

  /**
   * Elementwise identity operation: out = x<br>
   * <br>
   * @param name  name of the output variable<br>
   * @param input Input variable<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param input  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray identity(INDArray input) {
    NDValidation.validateNumerical("identity", "input", input);
    return Nd4j.exec(new TODO.Identity(input))[0];
  }

  /**
   * Compute the inverse permutation indices for a permutation operation<br>
   * Example: if input is [2, 0, 1] then output is [1, 2, 0]<br>
   * The idea is that x.permute(input).permute(invertPermutation(input)) == x<br>
   * <br>
   * @param name  name of the output variable<br>
   * @param input 1D indices for permutation<br>
   * @return 1D inverted permutation<br>
   *     <br>
   *
   * @param input  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray invertPermutation(INDArray input) {
    NDValidation.validateNumerical("invertPermutation", "input", input);
    return Nd4j.exec(new TODO.InvertPermutation(input))[0];
  }

  /**
   * Is the director a numeric tensor? In the current version of ND4J/SameDiff, this always returns true/1<br>
   * <br>
   * @param name Output variable name<br>
   * @param x    Input variable<br>
   * @return Scalar variable with value 1<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray isNumericTensor(INDArray x) {
    NDValidation.validateNumerical("isNumericTensor", "x", x);
    return Nd4j.exec(new TODO.IsNumericTensor(x))[0];
  }

  /**
   * Create a new 1d array with values evenly spaced between values 'start' and 'stop'<br>
   * For example, linspace(start=3.0, stop=4.0, number=3) will generate [3.0, 3.5, 4.0]<br>
   * <br>
   * @param name     Name of the new variable<br>
   * @param dataType Data type of the output array<br>
   * @param start    Start value<br>
   * @param stop     Stop value<br>
   * @param number   Number of values to generate<br>
   * @return SDVariable with linearly spaced elements<br>
   *     <br>
   *
   * @param dataType  (NUMERIC type)
   * @param start  (NUMERIC type)
   * @param stop  (NUMERIC type)
   * @param number  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray linspace(INDArray dataType, INDArray start, INDArray stop, INDArray number) {
    NDValidation.validateNumerical("linspace", "dataType", dataType);
    NDValidation.validateNumerical("linspace", "start", start);
    NDValidation.validateNumerical("linspace", "stop", stop);
    NDValidation.validateNumerical("linspace", "number", number);
    return Nd4j.exec(new TODO.Linspace(dataType, start, stop, number))[0];
  }

  /**
   * Create a new 1d array with values evenly spaced between values 'start' and 'stop'<br>
   * For example, linspace(start=3.0, stop=4.0, number=3) will generate [3.0, 3.5, 4.0]<br>
   * <br>
   * @param name   Name of the new variable<br>
   * @param from   Start value<br>
   * @param to     Stop value<br>
   * @param length Number of values to generate<br>
   * @param dt     Data type of the output array<br>
   * @return SDVariable with linearly spaced elements<br>
   *     <br>
   *
   * @param from  (NUMERIC type)
   * @param to  (NUMERIC type)
   * @param length  (NUMERIC type)
   * @param dt  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray linspace(INDArray from, INDArray to, INDArray length, INDArray dt) {
    NDValidation.validateNumerical("linspace", "from", from);
    NDValidation.validateNumerical("linspace", "to", to);
    NDValidation.validateNumerical("linspace", "length", length);
    NDValidation.validateNumerical("linspace", "dt", dt);
    return Nd4j.exec(new TODO.Linspace(from, to, length, dt))[0];
  }

  /**
   * Less than operation: elementwise x < y<br>
   * Returns an array with the same shape/size as the input, with values 1 where condition is satisfied, or<br>
   * value 0 otherwise<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input array<br>
   * @param y    Double value argument to use in operation<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray lt(INDArray x, INDArray y) {
    NDValidation.validateNumerical("lt", "x", x);
    NDValidation.validateNumerical("lt", "y", y);
    return Nd4j.exec(new TODO.Lt(x, y))[0];
  }

  /**
   * Less than operation: elementwise x < y<br>
   * If x and y arrays have equal shape, the output shape is the same as these inputs.<br>
   * Note: supports broadcasting if x and y have different shapes and are broadcastable.<br>
   * Returns an array with values 1 where condition is satisfied, or value 0 otherwise.<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input 1<br>
   * @param y    Input 2<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray lt(INDArray x, INDArray y) {
    NDValidation.validateNumerical("lt", "x", x);
    NDValidation.validateNumerical("lt", "y", y);
    return Nd4j.exec(new TODO.Lt(x, y))[0];
  }

  /**
   * Less than or equals operation: elementwise x <= y<br>
   * Returns an array with the same shape/size as the input, with values 1 where condition is satisfied, or<br>
   * value 0 otherwise<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input array<br>
   * @param y    Double value argument to use in operation<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray lte(INDArray x, INDArray y) {
    NDValidation.validateNumerical("lte", "x", x);
    NDValidation.validateNumerical("lte", "y", y);
    return Nd4j.exec(new TODO.Lte(x, y))[0];
  }

  /**
   * Less than or equal to operation: elementwise x <= y<br>
   * If x and y arrays have equal shape, the output shape is the same as these inputs.<br>
   * Note: supports broadcasting if x and y have different shapes and are broadcastable.<br>
   * Returns an array with values 1 where condition is satisfied, or value 0 otherwise.<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input 1<br>
   * @param y    Input 2<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray lte(INDArray x, INDArray y) {
    NDValidation.validateNumerical("lte", "x", x);
    NDValidation.validateNumerical("lte", "y", y);
    return Nd4j.exec(new TODO.Lte(x, y))[0];
  }

  /**
   * Returns a boolean mask of equal shape to the input, where the condition is satisfied - value 1 where satisfied, 0 otherwise<br>
   * <br>
   * @param in        Input<br>
   * @param condition Condition<br>
   * @return Boolean mask<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param condition  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray matchCondition(INDArray in, INDArray condition) {
    NDValidation.validateNumerical("matchCondition", "in", in);
    NDValidation.validateNumerical("matchCondition", "condition", condition);
    return Nd4j.exec(new TODO.MatchCondition(in, condition))[0];
  }

  /**
   * Returns a count of the number of elements that satisfy the condition<br>
   * <br>
   * @param name      Name of the output variable<br>
   * @param in        Input<br>
   * @param condition Condition<br>
   * @return Number of elements that the condition is satisfied for<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param condition  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray matchConditionCount(INDArray in, INDArray condition) {
    NDValidation.validateNumerical("matchConditionCount", "in", in);
    NDValidation.validateNumerical("matchConditionCount", "condition", condition);
    return Nd4j.exec(new TODO.MatchConditionCount(in, condition))[0];
  }

  /**
   * Returns a count of the number of elements that satisfy the condition (for each slice along the specified dimensions)<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name       Name of the output variable<br>
   * @param in         Input variable<br>
   * @param condition  Condition<br>
   * @param keepDim    If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Number of elements that the condition is satisfied for<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param condition  (NUMERIC type)
   * @param keepDim  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray matchConditionCount(INDArray in, INDArray condition, INDArray keepDim,
      INDArray dimensions) {
    NDValidation.validateNumerical("matchConditionCount", "in", in);
    NDValidation.validateNumerical("matchConditionCount", "condition", condition);
    NDValidation.validateNumerical("matchConditionCount", "keepDim", keepDim);
    NDValidation.validateNumerical("matchConditionCount", "dimensions", dimensions);
    return Nd4j.exec(new TODO.MatchConditionCount(in, condition, keepDim, dimensions))[0];
  }

  /**
   * Max array reduction operation, optionally along specified dimensions<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray max(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("max", "x", x);
    NDValidation.validateNumerical("max", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Max(x, dimensions))[0];
  }

  /**
   * Max array reduction operation, optionally along specified dimensions<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param keepDims   If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray max(INDArray x, INDArray keepDims, INDArray dimensions) {
    NDValidation.validateNumerical("max", "x", x);
    NDValidation.validateNumerical("max", "keepDims", keepDims);
    NDValidation.validateNumerical("max", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Max(x, keepDims, dimensions))[0];
  }

  /**
   * Element-wise maximum operation: out[i] = max(first[i], second[i])<br>
   * Supports broadcasting<br>
   * <br>
   * @param name   Name of the output variable<br>
   * @param first  First input array<br>
   * @param second Second input array<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param first  (NUMERIC type)
   * @param second  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray max(INDArray first, INDArray second) {
    NDValidation.validateNumerical("max", "first", first);
    NDValidation.validateNumerical("max", "second", second);
    return Nd4j.exec(new TODO.Max(first, second))[0];
  }

  /**
   * Mean (average) array reduction operation, optionally along specified dimensions<br>
   * <br>
   * @param name      Output variable name<br>
   * @param x         Input variable<br>
   * @param dimension Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimension  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray mean(INDArray x, INDArray dimension) {
    NDValidation.validateNumerical("mean", "x", x);
    NDValidation.validateNumerical("mean", "dimension", dimension);
    return Nd4j.exec(new TODO.Mean(x, dimension))[0];
  }

  /**
   * Mean (average) array reduction operation, optionally along specified dimensions<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name      Output variable name<br>
   * @param x         Input variable<br>
   * @param keepDims  If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions<br>
   * @param dimension Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimension  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray mean(INDArray x, INDArray keepDims, INDArray dimension) {
    NDValidation.validateNumerical("mean", "x", x);
    NDValidation.validateNumerical("mean", "keepDims", keepDims);
    NDValidation.validateNumerical("mean", "dimension", dimension);
    return Nd4j.exec(new TODO.Mean(x, keepDims, dimension))[0];
  }

  /**
   * Minimum array reduction operation, optionally along specified dimensions. out = min(in)<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray min(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("min", "x", x);
    NDValidation.validateNumerical("min", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Min(x, dimensions))[0];
  }

  /**
   * Minimum array reduction operation, optionally along specified dimensions. out = min(in)<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param keepDims   If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray min(INDArray x, INDArray keepDims, INDArray dimensions) {
    NDValidation.validateNumerical("min", "x", x);
    NDValidation.validateNumerical("min", "keepDims", keepDims);
    NDValidation.validateNumerical("min", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Min(x, keepDims, dimensions))[0];
  }

  /**
   * Element-wise minimum operation: out[i] = min(first[i], second[i])<br>
   * Supports broadcasting<br>
   * <br>
   * @param name   Name of the output variable<br>
   * @param first  First input array<br>
   * @param second Second input array<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param first  (NUMERIC type)
   * @param second  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray min(INDArray first, INDArray second) {
    NDValidation.validateNumerical("min", "first", first);
    NDValidation.validateNumerical("min", "second", second);
    return Nd4j.exec(new TODO.Min(first, second))[0];
  }

  /**
   * Matrix multiplication: out = mmul(x,y)<br>
   * Supports specifying a {@link MMulTranspose} argument to perform operation such as mmul(a^T, b), etc.<br>
   * <br>
   * @param name      Output variable name<br>
   * @param x         First input variable<br>
   * @param y         Second input variable<br>
   * @param transpose Transpose arguments<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @param transpose  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray mmul(INDArray x, INDArray y, INDArray transpose) {
    NDValidation.validateNumerical("mmul", "x", x);
    NDValidation.validateNumerical("mmul", "y", y);
    NDValidation.validateNumerical("mmul", "transpose", transpose);
    return Nd4j.exec(new TODO.Mmul(x, y, transpose))[0];
  }

  /**
   * Matrix multiplication: out = mmul(x,y)<br>
   * <br>
   * @param name Output variable name<br>
   * @param x    First input variable<br>
   * @param y    Second input variable<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray mmul(INDArray x, INDArray y) {
    NDValidation.validateNumerical("mmul", "x", x);
    NDValidation.validateNumerical("mmul", "y", y);
    return Nd4j.exec(new TODO.Mmul(x, y))[0];
  }

  /**
   * Not equals operation: elementwise x != y<br>
   * Returns an array with the same shape/size as the input, with values 1 where condition is satisfied, or<br>
   * value 0 otherwise<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input array<br>
   * @param y    Double value argument to use in operation<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray neq(INDArray x, INDArray y) {
    NDValidation.validateNumerical("neq", "x", x);
    NDValidation.validateNumerical("neq", "y", y);
    return Nd4j.exec(new TODO.Neq(x, y))[0];
  }

  /**
   * Not equal to operation: elementwise x != y<br>
   * If x and y arrays have equal shape, the output shape is the same as these inputs.<br>
   * Note: supports broadcasting if x and y have different shapes and are broadcastable.<br>
   * Returns an array with values 1 where condition is satisfied, or value 0 otherwise.<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input 1<br>
   * @param y    Input 2<br>
   * @return Output SDVariable with values 0 and 1 based on where the condition is satisfied<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray neq(INDArray x, INDArray y) {
    NDValidation.validateNumerical("neq", "x", x);
    NDValidation.validateNumerical("neq", "y", y);
    return Nd4j.exec(new TODO.Neq(x, y))[0];
  }

  /**
   * Norm1 (L1 norm) reduction operation: The output contains the L1 norm for each tensor/subset along the specified dimensions:<br>
   * out = sum_i abs(x[i])<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param dimensions dimensions to reduce over<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray norm1(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("norm1", "x", x);
    NDValidation.validateNumerical("norm1", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Norm1(x, dimensions))[0];
  }

  /**
   * Norm1 (L1 norm) reduction operation: The output contains the L1 norm for each tensor/subset along the specified dimensions:<br>
   * out = sum_i abs(x[i])<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param keepDims   If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions<br>
   * @param dimensions dimensions to reduce over<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray norm1(INDArray x, INDArray keepDims, INDArray dimensions) {
    NDValidation.validateNumerical("norm1", "x", x);
    NDValidation.validateNumerical("norm1", "keepDims", keepDims);
    NDValidation.validateNumerical("norm1", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Norm1(x, keepDims, dimensions))[0];
  }

  /**
   * Norm2 (L2 norm) reduction operation: The output contains the L2 norm for each tensor/subset along the specified dimensions:<br>
   * out = sqrt(sum_i x[i]^2)<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param dimensions dimensions to reduce over<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray norm2(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("norm2", "x", x);
    NDValidation.validateNumerical("norm2", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Norm2(x, dimensions))[0];
  }

  /**
   * Norm2 (L2 norm) reduction operation: The output contains the L2 norm for each tensor/subset along the specified dimensions:<br>
   * out = sqrt(sum_i x[i]^2)<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param keepDims   If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions<br>
   * @param dimensions dimensions to reduce over<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray norm2(INDArray x, INDArray keepDims, INDArray dimensions) {
    NDValidation.validateNumerical("norm2", "x", x);
    NDValidation.validateNumerical("norm2", "keepDims", keepDims);
    NDValidation.validateNumerical("norm2", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Norm2(x, keepDims, dimensions))[0];
  }

  /**
   * Max norm (infinity norm) reduction operation: The output contains the max norm for each tensor/subset along the<br>
   * specified dimensions<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param dimensions dimensions to reduce over<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray normmax(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("normmax", "x", x);
    NDValidation.validateNumerical("normmax", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Normmax(x, dimensions))[0];
  }

  /**
   * Max norm (infinity norm) reduction operation: The output contains the max norm for each tensor/subset along the<br>
   * specified dimensions:<br>
   * out = max(abs(x[i]))<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param keepDims   If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions<br>
   * @param dimensions dimensions to reduce over<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray normmax(INDArray x, INDArray keepDims, INDArray dimensions) {
    NDValidation.validateNumerical("normmax", "x", x);
    NDValidation.validateNumerical("normmax", "keepDims", keepDims);
    NDValidation.validateNumerical("normmax", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Normmax(x, keepDims, dimensions))[0];
  }

  /**
   * Convert the array to a one-hot array with walues {@code on} and {@code off} for each entry<br>
   * If input has shape [ a, ..., n] then output has shape [ a, ..., n, depth],<br>
   * with {@code out[i, ..., j, in[i,...,j]] = on} with other values being set to {@code off}<br>
   * <br>
   * @param name    Output variable name<br>
   * @param indices Indices - value 0 to depth-1<br>
   * @param depth   Number of classes<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param indices  (NUMERIC type)
   * @param depth  (NUMERIC type)
   * @param axis  (NUMERIC type)
   * @param on  (NUMERIC type)
   * @param off  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray oneHot(INDArray indices, INDArray depth, INDArray axis, INDArray on,
      INDArray off) {
    NDValidation.validateNumerical("oneHot", "indices", indices);
    NDValidation.validateNumerical("oneHot", "depth", depth);
    NDValidation.validateNumerical("oneHot", "axis", axis);
    NDValidation.validateNumerical("oneHot", "on", on);
    NDValidation.validateNumerical("oneHot", "off", off);
    return Nd4j.exec(new TODO.OneHot(indices, depth, axis, on, off))[0];
  }

  /**
   * As per {@link #oneHot(String, SDVariable, int, int, double, double)} but allows configuring the output datatype<br>
   *     <br>
   *
   * @param indices  (NUMERIC type)
   * @param depth  (NUMERIC type)
   * @param axis  (NUMERIC type)
   * @param on  (NUMERIC type)
   * @param off  (NUMERIC type)
   * @param dataType  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray oneHot(INDArray indices, INDArray depth, INDArray axis, INDArray on, INDArray off,
      INDArray dataType) {
    NDValidation.validateNumerical("oneHot", "indices", indices);
    NDValidation.validateNumerical("oneHot", "depth", depth);
    NDValidation.validateNumerical("oneHot", "axis", axis);
    NDValidation.validateNumerical("oneHot", "on", on);
    NDValidation.validateNumerical("oneHot", "off", off);
    NDValidation.validateNumerical("oneHot", "dataType", dataType);
    return Nd4j.exec(new TODO.OneHot(indices, depth, axis, on, off, dataType))[0];
  }

  /**
   * Convert the array to a one-hot array with walues 0 and 1 for each entry<br>
   * If input has shape [ a, ..., n] then output has shape [ a, ..., n, depth],<br>
   * with out[i, ..., j, in[i,...,j]] = 1 with other values being set to 0<br>
   * <br>
   * @param name    Output variable name<br>
   * @param indices Indices - value 0 to depth-1<br>
   * @param depth   Number of classes<br>
   * @return Output variable<br>
   * @see #oneHot(SDVariable, int, int, double, double)<br>
   *     <br>
   *
   * @param indices  (NUMERIC type)
   * @param depth  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray oneHot(INDArray indices, INDArray depth) {
    NDValidation.validateNumerical("oneHot", "indices", indices);
    NDValidation.validateNumerical("oneHot", "depth", depth);
    return Nd4j.exec(new TODO.OneHot(indices, depth))[0];
  }

  /**
   * Return a variable of all 1s, with the same shape as the input variable. Note that this is dynamic:<br>
   * if the input shape changes in later execution, the returned variable's shape will also be updated<br>
   * <br>
   * @param name  Name of the new SDVariable<br>
   * @param input Input SDVariable<br>
   * @return A new SDVariable with the same (dynamic) shape as the input<br>
   *     <br>
   *
   * @param input  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray onesLike(INDArray input) {
    NDValidation.validateNumerical("onesLike", "input", input);
    return Nd4j.exec(new TODO.OnesLike(input))[0];
  }

  /**
   * As per {@link #onesLike(String, SDVariable)} but the output datatype may be specified<br>
   *     <br>
   *
   * @param input  (NUMERIC type)
   * @param dataType  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray onesLike(INDArray input, INDArray dataType) {
    NDValidation.validateNumerical("onesLike", "input", input);
    NDValidation.validateNumerical("onesLike", "dataType", dataType);
    return Nd4j.exec(new TODO.OnesLike(input, dataType))[0];
  }

  /**
   * @see #stack(String, int, SDVariable...)<br>
   *     <br>
   *
   * @param values  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray parallel_stack(INDArray values) {
    NDValidation.validateNumerical("parallel_stack", "values", values);
    return Nd4j.exec(new TODO.Parallel_stack(values))[0];
  }

  /**
   * Array permutation operation: permute the dimensions according to the specified permutation indices.<br>
   * Example: if input has shape [a,b,c] and dimensions = [2,0,1] the output has shape [c,a,b]<br>
   * <br>
   * @param name Output variable name<br>
   * @param x    Input variable<br>
   * @return Output variable (permuted input)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray permute(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("permute", "x", x);
    NDValidation.validateNumerical("permute", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Permute(x, dimensions))[0];
  }

  /**
   * As per {@link #permute(String, SDVariable, int...)} but with SDVariable permute dimension<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray permute(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("permute", "x", x);
    NDValidation.validateNumerical("permute", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Permute(x, dimensions))[0];
  }

  /**
   * Product array reduction operation, optionally along specified dimensions<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Output variable: reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray prod(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("prod", "x", x);
    NDValidation.validateNumerical("prod", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Prod(x, dimensions))[0];
  }

  /**
   * Product array reduction operation, optionally along specified dimensions<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param keepDims   If true: keep the dimensions that are reduced on (as length 1). False: remove the reduction dimensions<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Output variable: reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray prod(INDArray x, INDArray keepDims, INDArray dimensions) {
    NDValidation.validateNumerical("prod", "x", x);
    NDValidation.validateNumerical("prod", "keepDims", keepDims);
    NDValidation.validateNumerical("prod", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Prod(x, keepDims, dimensions))[0];
  }

  /**
   * Create a new variable with a 1d array, where the values start at {@code from} and increment by {@code step}<br>
   * up to (but not including) limit.<br>
   * For example, {@code range(1.0, 3.0, 0.5)} will return {@code [1.0, 1.5, 2.0, 2.5]}<br>
   * <br>
   * @param name Name of the new variable<br>
   * @param from Initial/smallest value<br>
   * @param to   Largest value (exclusive)<br>
   * @param step Step size<br>
   * @return 1D SDVariable with the specified values<br>
   *     <br>
   *
   * @param from  (NUMERIC type)
   * @param to  (NUMERIC type)
   * @param step  (NUMERIC type)
   * @param dataType  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray range(INDArray from, INDArray to, INDArray step, INDArray dataType) {
    NDValidation.validateNumerical("range", "from", from);
    NDValidation.validateNumerical("range", "to", to);
    NDValidation.validateNumerical("range", "step", step);
    NDValidation.validateNumerical("range", "dataType", dataType);
    return Nd4j.exec(new TODO.Range(from, to, step, dataType))[0];
  }

  /**
   * As per {@link #range(String, double, double, double, DataType)} but with SDVariable arguments<br>
   *     <br>
   *
   * @param from  (NUMERIC type)
   * @param to  (NUMERIC type)
   * @param step  (NUMERIC type)
   * @param dataType  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray range(INDArray from, INDArray to, INDArray step, INDArray dataType) {
    NDValidation.validateNumerical("range", "from", from);
    NDValidation.validateNumerical("range", "to", to);
    NDValidation.validateNumerical("range", "step", step);
    NDValidation.validateNumerical("range", "dataType", dataType);
    return Nd4j.exec(new TODO.Range(from, to, step, dataType))[0];
  }

  /**
   * Returns the rank (number of dimensions, i.e., length(shape)) of the specified SDVariable as a 0D scalar variable<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param in   Input variable<br>
   * @return 0D (scalar) output variable with value equal to the rank of the input variable<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray rank(INDArray in) {
    NDValidation.validateNumerical("rank", "in", in);
    return Nd4j.exec(new TODO.Rank(in))[0];
  }

  /**
   * @see #repeat(String, SDVariable, int)<br>
   *     <br>
   *
   * @param df  (NUMERIC type)
   * @param axis  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray repeat(INDArray df, INDArray axis) {
    NDValidation.validateNumerical("repeat", "df", df);
    NDValidation.validateNumerical("repeat", "axis", axis);
    return Nd4j.exec(new TODO.Repeat(df, axis))[0];
  }

  /**
   * Element-wise replace where condition:<br>
   * out[i] = from[i] if condition(update[i]) is satisfied, or<br>
   * out[i] = update[i] if condition(update[i]) is NOT satisfied<br>
   * <br>
   * @param name      Name of the output variable<br>
   * @param update    Source array<br>
   * @param from      Replacement values array (used conditionally). Must be same shape as 'update' array<br>
   * @param condition Condition to check on update array elements<br>
   * @return New array with values replaced where condition is satisfied<br>
   *     <br>
   *
   * @param update  (NUMERIC type)
   * @param from  (NUMERIC type)
   * @param condition  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray replaceWhere(INDArray update, INDArray from, INDArray condition) {
    NDValidation.validateNumerical("replaceWhere", "update", update);
    NDValidation.validateNumerical("replaceWhere", "from", from);
    NDValidation.validateNumerical("replaceWhere", "condition", condition);
    return Nd4j.exec(new TODO.ReplaceWhere(update, from, condition))[0];
  }

  /**
   * Element-wise replace where condition:<br>
   * out[i] = value if condition(update[i]) is satisfied, or<br>
   * out[i] = update[i] if condition(update[i]) is NOT satisfied<br>
   * <br>
   * @param name      Name of the output variable<br>
   * @param update    Source array<br>
   * @param value     Value to set at the output, if the condition is satisfied<br>
   * @param condition Condition to check on update array elements<br>
   * @return New array with values replaced where condition is satisfied<br>
   *     <br>
   *
   * @param update  (NUMERIC type)
   * @param value  (NUMERIC type)
   * @param condition  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray replaceWhere(INDArray update, INDArray value, INDArray condition) {
    NDValidation.validateNumerical("replaceWhere", "update", update);
    NDValidation.validateNumerical("replaceWhere", "value", value);
    NDValidation.validateNumerical("replaceWhere", "condition", condition);
    return Nd4j.exec(new TODO.ReplaceWhere(update, value, condition))[0];
  }

  /**
   * Reshape the input variable to the specified (fixed) shape. The output variable will have the same values as the<br>
   * input, but with the specified shape.<br>
   * Note that prod(shape) must match length(input) == prod(input.shape)<br>
   * <br>
   * @param name  Output variable name<br>
   * @param x     Input variable<br>
   * @param shape New shape for variable<br>
   * @return Output variable<br>
   * @see #reshape(SDVariable, SDVariable)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param shape  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray reshape(INDArray x, INDArray shape) {
    NDValidation.validateNumerical("reshape", "x", x);
    NDValidation.validateNumerical("reshape", "shape", shape);
    return Nd4j.exec(new TODO.Reshape(x, shape))[0];
  }

  /**
   * Reshape the input variable to the specified (fixed) shape. The output variable will have the same values as the<br>
   * input, but with the specified shape.<br>
   * Note that prod(shape) must match length(input) == prod(input.shape)<br>
   * <br>
   * @param name  Output variable name<br>
   * @param x     Input variable<br>
   * @param shape New shape for variable<br>
   * @return Output variable<br>
   * @see #reshape(SDVariable, SDVariable)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param shape  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray reshape(INDArray x, INDArray shape) {
    NDValidation.validateNumerical("reshape", "x", x);
    NDValidation.validateNumerical("reshape", "shape", shape);
    return Nd4j.exec(new TODO.Reshape(x, shape))[0];
  }

  /**
   * Reshape the input variable to the specified (dynamic) shape. The output variable will have the same values as the<br>
   * input, but with the specified shape.<br>
   * Note that prod(shape) must match length(input) == prod(input.shape)<br>
   * <br>
   * @param name  Output variable name<br>
   * @param x     Input variable<br>
   * @param shape New shape for variable<br>
   * @return Output variable<br>
   * @see #reshape(SDVariable, int[])<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param shape  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray reshape(INDArray x, INDArray shape) {
    NDValidation.validateNumerical("reshape", "x", x);
    NDValidation.validateNumerical("reshape", "shape", shape);
    return Nd4j.exec(new TODO.Reshape(x, shape))[0];
  }

  /**
   * Reverse the values of an array for the specified dimensions<br>
   * If input is:<br>
   * [ 1, 2, 3]<br>
   * [ 4, 5, 6]<br>
   * then<br>
   * reverse(in, 0):<br>
   * [3, 2, 1]<br>
   * [6, 5, 4]<br>
   * reverse(in, 0):<br>
   * [4, 5, 6]<br>
   * [1, 2 3]<br>
   * <br>
   * @param x          Input variable<br>
   * @param dimensions Dimensions<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray reverse(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("reverse", "x", x);
    NDValidation.validateNumerical("reverse", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Reverse(x, dimensions))[0];
  }

  /**
   * Reverse sequence op: for each slice along dimension seqDimension, the first seqLength values are reversed<br>
   * <br>
   * @param name        Name of the output variable<br>
   * @param x           Input variable<br>
   * @param seq_lengths Length of the sequences<br>
   * @param seqDim      Sequence dimension<br>
   * @param batchDim    Batch dimension<br>
   * @return Reversed sequences<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param seq_lengths  (NUMERIC type)
   * @param seqDim  (NUMERIC type)
   * @param batchDim  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray reverseSequence(INDArray x, INDArray seq_lengths, INDArray seqDim,
      INDArray batchDim) {
    NDValidation.validateNumerical("reverseSequence", "x", x);
    NDValidation.validateNumerical("reverseSequence", "seq_lengths", seq_lengths);
    NDValidation.validateNumerical("reverseSequence", "seqDim", seqDim);
    NDValidation.validateNumerical("reverseSequence", "batchDim", batchDim);
    return Nd4j.exec(new TODO.ReverseSequence(x, seq_lengths, seqDim, batchDim))[0];
  }

  /**
   * @see #reverseSequence(String, SDVariable, SDVariable, int, int)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param seq_lengths  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray reverseSequence(INDArray x, INDArray seq_lengths) {
    NDValidation.validateNumerical("reverseSequence", "x", x);
    NDValidation.validateNumerical("reverseSequence", "seq_lengths", seq_lengths);
    return Nd4j.exec(new TODO.ReverseSequence(x, seq_lengths))[0];
  }

  /**
   * Element-wise scalar floor modulus operation: out = floorMod(in, value).<br>
   * i.e., returns the remainder after division by 'value'<br>
   * <br>
   * @param name  Name of the output variable<br>
   * @param in    Input variable<br>
   * @param value Scalar value to compare<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param value  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scalarFloorMod(INDArray in, INDArray value) {
    NDValidation.validateNumerical("scalarFloorMod", "in", in);
    NDValidation.validateNumerical("scalarFloorMod", "value", value);
    return Nd4j.exec(new TODO.ScalarFloorMod(in, value))[0];
  }

  /**
   * Element-wise scalar maximum operation: out = max(in, value)<br>
   * <br>
   * @param name  Name of the output variable<br>
   * @param in    Input variable<br>
   * @param value Scalar value to compare<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param value  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scalarMax(INDArray in, INDArray value) {
    NDValidation.validateNumerical("scalarMax", "in", in);
    NDValidation.validateNumerical("scalarMax", "value", value);
    return Nd4j.exec(new TODO.ScalarMax(in, value))[0];
  }

  /**
   * Element-wise scalar minimum operation: out = min(in, value)<br>
   * <br>
   * @param name  Name of the output variable<br>
   * @param in    Input variable<br>
   * @param value Scalar value to compare<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param value  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scalarMin(INDArray in, INDArray value) {
    NDValidation.validateNumerical("scalarMin", "in", in);
    NDValidation.validateNumerical("scalarMin", "value", value);
    return Nd4j.exec(new TODO.ScalarMin(in, value))[0];
  }

  /**
   * Return a variable with equal shape to the input, but all elements set to value 'set'<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param in   Input variable<br>
   * @param set  Value to set<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param set  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scalarSet(INDArray in, INDArray set) {
    NDValidation.validateNumerical("scalarSet", "in", in);
    NDValidation.validateNumerical("scalarSet", "set", set);
    return Nd4j.exec(new TODO.ScalarSet(in, set))[0];
  }

  /**
   * Scatter addition operation.<br>
   * If indices is rank 0 (a scalar), then out[index, ...] += updates[...]<br>
   * If indices is rank 1 (a vector), then for each position i, out[indices[i], ...] += updates[i, ...]<br>
   * If indices is rank 2+, then for each position (i,...,k), out[indices[i], ..., indices[k], ...] += updates[i, ..., k, ...]<br>
   * Note that if multiple indices refer to the same location, the contributions from each is handled correctly.<br>
   * <br>
   * @param name    Name of the output variable<br>
   * @param ref     Initial/source variable<br>
   * @param indices Indices array<br>
   * @param updates Updates to add to the initial/source array<br>
   * @return The updated variable<br>
   *     <br>
   *
   * @param ref  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @param updates  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scatterAdd(INDArray ref, INDArray indices, INDArray updates) {
    NDValidation.validateNumerical("scatterAdd", "ref", ref);
    NDValidation.validateNumerical("scatterAdd", "indices", indices);
    NDValidation.validateNumerical("scatterAdd", "updates", updates);
    return Nd4j.exec(new TODO.ScatterAdd(ref, indices, updates))[0];
  }

  /**
   * Scatter division operation.<br>
   * If indices is rank 0 (a scalar), then out[index, ...] /= updates[...]<br>
   * If indices is rank 1 (a vector), then for each position i, out[indices[i], ...] /= updates[i, ...]<br>
   * If indices is rank 2+, then for each position (i,...,k), out[indices[i], ..., indices[k], ...] /= updates[i, ..., k, ...]<br>
   * Note that if multiple indices refer to the same location, the contributions from each is handled correctly.<br>
   * <br>
   * @param name    Name of the output variable<br>
   * @param ref     Initial/source variable<br>
   * @param indices Indices array<br>
   * @param updates Updates to add to the initial/source array<br>
   * @return The updated variable<br>
   *     <br>
   *
   * @param ref  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @param updates  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scatterDiv(INDArray ref, INDArray indices, INDArray updates) {
    NDValidation.validateNumerical("scatterDiv", "ref", ref);
    NDValidation.validateNumerical("scatterDiv", "indices", indices);
    NDValidation.validateNumerical("scatterDiv", "updates", updates);
    return Nd4j.exec(new TODO.ScatterDiv(ref, indices, updates))[0];
  }

  /**
   * Scatter max operation.<br>
   * If indices is rank 0 (a scalar), then out[index, ...] = max(updates[...], in[index,...])<br>
   * If indices is rank 1 (a vector), then for each position i, out[indices[i], ...] = max(updates[i,...], in[indices[i],...])<br>
   * If indices is rank 2+, then for each position (i,...,k), out[indices[i], ..., indices[k], ...] = max(updates[i, ..., k, ...], in[indices[i], ..., indices[k], ...]<br>
   * Note that if multiple indices refer to the same location, the contributions from each is handled correctly.<br>
   * <br>
   * @param name    Name of the output variable<br>
   * @param ref     Initial/source variable<br>
   * @param indices Indices array<br>
   * @param updates Updates to add to the initial/source array<br>
   * @return The updated variable<br>
   *     <br>
   *
   * @param ref  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @param updates  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scatterMax(INDArray ref, INDArray indices, INDArray updates) {
    NDValidation.validateNumerical("scatterMax", "ref", ref);
    NDValidation.validateNumerical("scatterMax", "indices", indices);
    NDValidation.validateNumerical("scatterMax", "updates", updates);
    return Nd4j.exec(new TODO.ScatterMax(ref, indices, updates))[0];
  }

  /**
   * Scatter min operation.<br>
   * If indices is rank 0 (a scalar), then out[index, ...] = min(updates[...], in[index,...])<br>
   * If indices is rank 1 (a vector), then for each position i, out[indices[i], ...] = min(updates[i,...], in[indices[i],...])<br>
   * If indices is rank 2+, then for each position (i,...,k), out[indices[i], ..., indices[k], ...] = min(updates[i, ..., k, ...], in[indices[i], ..., indices[k], ...]<br>
   * Note that if multiple indices refer to the same location, the contributions from each is handled correctly.<br>
   * <br>
   * @param name    Name of the output variable<br>
   * @param ref     Initial/source variable<br>
   * @param indices Indices array<br>
   * @param updates Updates to add to the initial/source array<br>
   * @return The updated variable<br>
   *     <br>
   *
   * @param ref  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @param updates  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scatterMin(INDArray ref, INDArray indices, INDArray updates) {
    NDValidation.validateNumerical("scatterMin", "ref", ref);
    NDValidation.validateNumerical("scatterMin", "indices", indices);
    NDValidation.validateNumerical("scatterMin", "updates", updates);
    return Nd4j.exec(new TODO.ScatterMin(ref, indices, updates))[0];
  }

  /**
   * Scatter multiplication operation.<br>
   * If indices is rank 0 (a scalar), then out[index, ...] *= updates[...]<br>
   * If indices is rank 1 (a vector), then for each position i, out[indices[i], ...] *= updates[i, ...]<br>
   * If indices is rank 2+, then for each position (i,...,k), out[indices[i], ..., indices[k], ...] *= updates[i, ..., k, ...]<br>
   * Note that if multiple indices refer to the same location, the contributions from each is handled correctly.<br>
   * <br>
   * @param name    Name of the output variable<br>
   * @param ref     Initial/source variable<br>
   * @param indices Indices array<br>
   * @param updates Updates to add to the initial/source array<br>
   * @return The updated variable<br>
   *     <br>
   *
   * @param ref  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @param updates  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scatterMul(INDArray ref, INDArray indices, INDArray updates) {
    NDValidation.validateNumerical("scatterMul", "ref", ref);
    NDValidation.validateNumerical("scatterMul", "indices", indices);
    NDValidation.validateNumerical("scatterMul", "updates", updates);
    return Nd4j.exec(new TODO.ScatterMul(ref, indices, updates))[0];
  }

  /**
   * Scatter subtraction operation.<br>
   * If indices is rank 0 (a scalar), then out[index, ...] -= updates[...]<br>
   * If indices is rank 1 (a vector), then for each position i, out[indices[i], ...] -= updates[i, ...]<br>
   * If indices is rank 2+, then for each position (i,...,k), out[indices[i], ..., indices[k], ...] -= updates[i, ..., k, ...]<br>
   * Note that if multiple indices refer to the same location, the contributions from each is handled correctly.<br>
   * <br>
   * @param name    Name of the output variable<br>
   * @param ref     Initial/source variable<br>
   * @param indices Indices array<br>
   * @param updates Updates to add to the initial/source array<br>
   * @return The updated variable<br>
   *     <br>
   *
   * @param ref  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @param updates  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scatterSub(INDArray ref, INDArray indices, INDArray updates) {
    NDValidation.validateNumerical("scatterSub", "ref", ref);
    NDValidation.validateNumerical("scatterSub", "indices", indices);
    NDValidation.validateNumerical("scatterSub", "updates", updates);
    return Nd4j.exec(new TODO.ScatterSub(ref, indices, updates))[0];
  }

  /**
   * Scatter update operation.<br>
   * If indices is rank 0 (a scalar), then out[index, ...] = updates[...]<br>
   * If indices is rank 1 (a vector), then for each position i, out[indices[i], ...] = updates[i, ...]<br>
   * If indices is rank 2+, then for each position (i,...,k), out[indices[i], ..., indices[k], ...] = updates[i, ..., k, ...]<br>
   * Note that if multiple indices refer to the same location, the output at those locations is undefined - different<br>
   * updates may occur in different orders<br>
   * <br>
   * @param name    Name of the output variable<br>
   * @param ref     Initial/source variable<br>
   * @param indices Indices array<br>
   * @param updates Updates to add to the initial/source array<br>
   * @return The updated variable<br>
   *     <br>
   *
   * @param ref  (NUMERIC type)
   * @param indices  (NUMERIC type)
   * @param updates  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray scatterUpdate(INDArray ref, INDArray indices, INDArray updates) {
    NDValidation.validateNumerical("scatterUpdate", "ref", ref);
    NDValidation.validateNumerical("scatterUpdate", "indices", indices);
    NDValidation.validateNumerical("scatterUpdate", "updates", updates);
    return Nd4j.exec(new TODO.ScatterUpdate(ref, indices, updates))[0];
  }

  /**
   * Segment max operation.<br>
   * If data =     [3, 6, 1, 4, 9, 2, 8]<br>
   * segmentIds =  [0, 0, 1, 1, 1, 2, 2]<br>
   * then output = [6, 9, 8] = [max(3,6), max(1,4,9), max(2,8)]<br>
   * Note that the segment IDs must be sorted from smallest to largest segment.<br>
   * See {@link #unsortedSegmentMax(String, SDVariable, SDVariable, int)}<br>
   * for the same op without this sorted requirement<br>
   * <br>
   * @param name       Name of the output variable. May be null<br>
   * @param data       Data to perform segment max on<br>
   * @param segmentIds Variable for the segment IDs<br>
   * @return Segment max output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray segmentMax(INDArray data, INDArray segmentIds) {
    NDValidation.validateNumerical("segmentMax", "data", data);
    NDValidation.validateNumerical("segmentMax", "segmentIds", segmentIds);
    return Nd4j.exec(new TODO.SegmentMax(data, segmentIds))[0];
  }

  /**
   * Segment mean operation.<br>
   * If data =     [3, 6, 1, 4, 9, 2, 8]<br>
   * segmentIds =  [0, 0, 1, 1, 1, 2, 2]<br>
   * then output = [4.5, 4.666, 5] = [mean(3,6), mean(1,4,9), mean(2,8)]<br>
   * Note that the segment IDs must be sorted from smallest to largest segment.<br>
   * See {@link #unsortedSegmentMean(String, SDVariable, SDVariable, int)} for the same op without this sorted requirement<br>
   * <br>
   * @param name       Name of the output variable. May be null<br>
   * @param data       Data to perform segment max on<br>
   * @param segmentIds Variable for the segment IDs<br>
   * @return Segment mean output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray segmentMean(INDArray data, INDArray segmentIds) {
    NDValidation.validateNumerical("segmentMean", "data", data);
    NDValidation.validateNumerical("segmentMean", "segmentIds", segmentIds);
    return Nd4j.exec(new TODO.SegmentMean(data, segmentIds))[0];
  }

  /**
   * Segment min operation.<br>
   * If data =     [3, 6, 1, 4, 9, 2, 8]<br>
   * segmentIds =  [0, 0, 1, 1, 1, 2, 2]<br>
   * then output = [3, 1, 2] = [min(3,6), min(1,4,9), min(2,8)]<br>
   * Note that the segment IDs must be sorted from smallest to largest segment.<br>
   * See {@link #unsortedSegmentMin(String, SDVariable, SDVariable, int)} for the same op without this sorted requirement<br>
   * <br>
   * @param name       Name of the output variable. May be null<br>
   * @param data       Data to perform segment max on<br>
   * @param segmentIds Variable for the segment IDs<br>
   * @return Segment min output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray segmentMin(INDArray data, INDArray segmentIds) {
    NDValidation.validateNumerical("segmentMin", "data", data);
    NDValidation.validateNumerical("segmentMin", "segmentIds", segmentIds);
    return Nd4j.exec(new TODO.SegmentMin(data, segmentIds))[0];
  }

  /**
   * Segment product operation.<br>
   * If data =     [3, 6, 1, 4, 9, 2, 8]<br>
   * segmentIds =  [0, 0, 1, 1, 1, 2, 2]<br>
   * then output = [18, 36, 16] = [prod(3,6), prod(1,4,9), prod(2,8)]<br>
   * Note that the segment IDs must be sorted from smallest to largest segment.<br>
   * See {@link #unsortedSegmentProd(String, SDVariable, SDVariable, int)} for the same op without this sorted requirement<br>
   * <br>
   * @param name       Name of the output variable. May be null<br>
   * @param data       Data to perform segment max on<br>
   * @param segmentIds Variable for the segment IDs<br>
   * @return Segment product output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray segmentProd(INDArray data, INDArray segmentIds) {
    NDValidation.validateNumerical("segmentProd", "data", data);
    NDValidation.validateNumerical("segmentProd", "segmentIds", segmentIds);
    return Nd4j.exec(new TODO.SegmentProd(data, segmentIds))[0];
  }

  /**
   * Segment sum operation.<br>
   * If data =     [3, 6, 1, 4, 9, 2, 8]<br>
   * segmentIds =  [0, 0, 1, 1, 1, 2, 2]<br>
   * then output = [9, 14, 10] = [sum(3,6), sum(1,4,9), sum(2,8)]<br>
   * Note that the segment IDs must be sorted from smallest to largest segment.<br>
   * See {@link #unsortedSegmentSum(String, SDVariable, SDVariable, int)} for the same op without this sorted requirement<br>
   * <br>
   * @param name       Name of the output variable. May be null<br>
   * @param data       Data to perform segment max on<br>
   * @param segmentIds Variable for the segment IDs<br>
   * @return Segment sum output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray segmentSum(INDArray data, INDArray segmentIds) {
    NDValidation.validateNumerical("segmentSum", "data", data);
    NDValidation.validateNumerical("segmentSum", "segmentIds", segmentIds);
    return Nd4j.exec(new TODO.SegmentSum(data, segmentIds))[0];
  }

  /**
   * @see #sequenceMask(String, SDVariable, SDVariable, DataType)<br>
   *     <br>
   *
   * @param lengths  (NUMERIC type)
   * @param maxLen  (NUMERIC type)
   * @param dataType  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray sequenceMask(INDArray lengths, INDArray maxLen, INDArray dataType) {
    NDValidation.validateNumerical("sequenceMask", "lengths", lengths);
    NDValidation.validateNumerical("sequenceMask", "maxLen", maxLen);
    NDValidation.validateNumerical("sequenceMask", "dataType", dataType);
    return Nd4j.exec(new TODO.SequenceMask(lengths, maxLen, dataType))[0];
  }

  /**
   * @see #sequenceMask(String, SDVariable, SDVariable, DataType)<br>
   *     <br>
   *
   * @param lengths  (NUMERIC type)
   * @param dataType  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray sequenceMask(INDArray lengths, INDArray dataType) {
    NDValidation.validateNumerical("sequenceMask", "lengths", lengths);
    NDValidation.validateNumerical("sequenceMask", "dataType", dataType);
    return Nd4j.exec(new TODO.SequenceMask(lengths, dataType))[0];
  }

  /**
   * Generate a sequence mask (with values 0 or 1) based on the specified lengths<br>
   * Specifically, out[i, ..., k, j] = (j < lengths[i, ..., k] ? 1.0 : 0.0)<br>
   * <br>
   * @param name    Name of the output variable<br>
   * @param lengths Lengths of the sequences<br>
   * @param maxLen  Maximum sequence length<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param lengths  (NUMERIC type)
   * @param maxLen  (NUMERIC type)
   * @param dataType  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray sequenceMask(INDArray lengths, INDArray maxLen, INDArray dataType) {
    NDValidation.validateNumerical("sequenceMask", "lengths", lengths);
    NDValidation.validateNumerical("sequenceMask", "maxLen", maxLen);
    NDValidation.validateNumerical("sequenceMask", "dataType", dataType);
    return Nd4j.exec(new TODO.SequenceMask(lengths, maxLen, dataType))[0];
  }

  /**
   * Returns the shape of the specified SDVariable as a 1D SDVariable<br>
   * <br>
   * @param name  Name of the output variable<br>
   * @param input Input variable<br>
   * @return 1D output variable with contents equal to the shape of the input<br>
   *     <br>
   *
   * @param input  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray shape(INDArray input) {
    NDValidation.validateNumerical("shape", "input", input);
    return Nd4j.exec(new TODO.Shape(input))[0];
  }

  /**
   * Returns the size (number of elements, i.e., prod(shape)) of the specified SDVariable as a 0D scalar variable<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param in   Input variable<br>
   * @return 0D (scalar) output variable with value equal to the number of elements in the specified array<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray size(INDArray in) {
    NDValidation.validateNumerical("size", "in", in);
    return Nd4j.exec(new TODO.Size(in))[0];
  }

  /**
   * Returns a rank 0 (scalar) variable for the size of the specified dimension.<br>
   * For example, if X has shape [10,20,30] then sizeAt(X,1)=20. Similarly, sizeAt(X,-1)=30<br>
   * <br>
   * @param name      Name of the output variable<br>
   * @param in        Input variable<br>
   * @param dimension Dimension to get size of<br>
   * @return Scalar SDVariable for size at specified variable<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param dimension  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray sizeAt(INDArray in, INDArray dimension) {
    NDValidation.validateNumerical("sizeAt", "in", in);
    NDValidation.validateNumerical("sizeAt", "dimension", dimension);
    return Nd4j.exec(new TODO.SizeAt(in, dimension))[0];
  }

  /**
   * Get a subset of the specified input, by specifying the first element and the size of the array.<br>
   * For example, if input is:<br>
   * [a, b, c]<br>
   * [d, e, f]<br>
   * then slice(input, begin=[0,1], size=[2,1] will return:<br>
   * [b]<br>
   * [e]<br>
   * <br>
   * Note that for each dimension i, begin[i] + size[i] <= input.size(i)<br>
   * <br>
   * @param name  Output variable name<br>
   * @param input Variable to get subset of<br>
   * @param begin Beginning index. Must be same length as rank of input array<br>
   * @param size  Size of the output array. Must be same length as rank of input array<br>
   * @return Subset of the input<br>
   *     <br>
   *
   * @param input  (NUMERIC type)
   * @param begin  (NUMERIC type)
   * @param size  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray slice(INDArray input, INDArray begin, INDArray size) {
    NDValidation.validateNumerical("slice", "input", input);
    NDValidation.validateNumerical("slice", "begin", begin);
    NDValidation.validateNumerical("slice", "size", size);
    return Nd4j.exec(new TODO.Slice(input, begin, size))[0];
  }

  /**
   * Squared L2 norm: see {@link #norm2(String, SDVariable, boolean, int...)}<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray squaredNorm(INDArray x, INDArray keepDims, INDArray dimensions) {
    NDValidation.validateNumerical("squaredNorm", "x", x);
    NDValidation.validateNumerical("squaredNorm", "keepDims", keepDims);
    NDValidation.validateNumerical("squaredNorm", "dimensions", dimensions);
    return Nd4j.exec(new TODO.SquaredNorm(x, keepDims, dimensions))[0];
  }

  /**
   * Squared L2 norm: see {@link #norm2(String, SDVariable, int...)}<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray squaredNorm(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("squaredNorm", "x", x);
    NDValidation.validateNumerical("squaredNorm", "dimensions", dimensions);
    return Nd4j.exec(new TODO.SquaredNorm(x, dimensions))[0];
  }

  /**
   * Remove a single dimension of size 1.<br>
   * For example, if input has shape [a,b,1,c] then squeeze(input, 2) returns an array of shape [a,b,c]<br>
   * <br>
   * @param name Name of the output variable<br>
   * @param x    Input variable<br>
   * @param axis Size 1 dimension to remove<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param axis  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray squeeze(INDArray x, INDArray axis) {
    NDValidation.validateNumerical("squeeze", "x", x);
    NDValidation.validateNumerical("squeeze", "axis", axis);
    return Nd4j.exec(new TODO.Squeeze(x, axis))[0];
  }

  /**
   * Stack a set of N SDVariables of rank X into one rank X+1 variable.<br>
   * If inputs have shape [a,b,c] then output has shape:<br>
   * axis = 0: [N,a,b,c]<br>
   * axis = 1: [a,N,b,c]<br>
   * axis = 2: [a,b,N,c]<br>
   * axis = 3: [a,b,c,N]<br>
   * <br>
   * @param name   Name of the output variable<br>
   * @param axis   Axis to stack on<br>
   * @param values Input variables to stack. Must have the same shape for all inputs<br>
   * @return Output variable<br>
   * @see #unstack(String[], SDVariable, int, int)<br>
   *     <br>
   *
   * @param axis  (NUMERIC type)
   * @param values  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray stack(INDArray axis, INDArray values) {
    NDValidation.validateNumerical("stack", "axis", axis);
    NDValidation.validateNumerical("stack", "values", values);
    return Nd4j.exec(new TODO.Stack(axis, values))[0];
  }

  /**
   * Stardard deviation array reduction operation, optionally along specified dimensions<br>
   * <br>
   * @param name          Output variable name<br>
   * @param x             Input variable<br>
   * @param biasCorrected If true: divide by (N-1) (i.e., sample stdev). If false: divide by N (population stdev)<br>
   * @param dimensions    Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Output variable: reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param biasCorrected  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray standardDeviation(INDArray x, INDArray biasCorrected, INDArray dimensions) {
    NDValidation.validateNumerical("standardDeviation", "x", x);
    NDValidation.validateNumerical("standardDeviation", "biasCorrected", biasCorrected);
    NDValidation.validateNumerical("standardDeviation", "dimensions", dimensions);
    return Nd4j.exec(new TODO.StandardDeviation(x, biasCorrected, dimensions))[0];
  }

  /**
   * Stardard deviation array reduction operation, optionally along specified dimensions<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param x             Input variable<br>
   * @param biasCorrected If true: divide by (N-1) (i.e., sample stdev). If false: divide by N (population stdev)<br>
   * @param keepDims      If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions<br>
   * @param dimensions    Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Output variable: reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param biasCorrected  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray standardDeviation(INDArray x, INDArray biasCorrected, INDArray keepDims,
      INDArray dimensions) {
    NDValidation.validateNumerical("standardDeviation", "x", x);
    NDValidation.validateNumerical("standardDeviation", "biasCorrected", biasCorrected);
    NDValidation.validateNumerical("standardDeviation", "keepDims", keepDims);
    NDValidation.validateNumerical("standardDeviation", "dimensions", dimensions);
    return Nd4j.exec(new TODO.StandardDeviation(x, biasCorrected, keepDims, dimensions))[0];
  }

  /**
   * @see #stridedSlice(String, SDVariable, long[], long[], long[])<br>
   *     <br>
   *
   * @param input  (NUMERIC type)
   * @param begin  (NUMERIC type)
   * @param end  (NUMERIC type)
   * @param strides  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray stridedSlice(INDArray input, INDArray begin, INDArray end, INDArray strides) {
    NDValidation.validateNumerical("stridedSlice", "input", input);
    NDValidation.validateNumerical("stridedSlice", "begin", begin);
    NDValidation.validateNumerical("stridedSlice", "end", end);
    NDValidation.validateNumerical("stridedSlice", "strides", strides);
    return Nd4j.exec(new TODO.StridedSlice(input, begin, end, strides))[0];
  }

  /**
   * @see #stridedSlice(String, SDVariable, long[], long[], long[], int, int, int, int, int)<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param begin  (NUMERIC type)
   * @param end  (NUMERIC type)
   * @param strides  (NUMERIC type)
   * @param beginMask  (NUMERIC type)
   * @param endMask  (NUMERIC type)
   * @param ellipsisMask  (NUMERIC type)
   * @param newAxisMask  (NUMERIC type)
   * @param shrinkAxisMask  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray stridedSlice(INDArray in, INDArray begin, INDArray end, INDArray strides,
      INDArray beginMask, INDArray endMask, INDArray ellipsisMask, INDArray newAxisMask,
      INDArray shrinkAxisMask) {
    NDValidation.validateNumerical("stridedSlice", "in", in);
    NDValidation.validateNumerical("stridedSlice", "begin", begin);
    NDValidation.validateNumerical("stridedSlice", "end", end);
    NDValidation.validateNumerical("stridedSlice", "strides", strides);
    NDValidation.validateNumerical("stridedSlice", "beginMask", beginMask);
    NDValidation.validateNumerical("stridedSlice", "endMask", endMask);
    NDValidation.validateNumerical("stridedSlice", "ellipsisMask", ellipsisMask);
    NDValidation.validateNumerical("stridedSlice", "newAxisMask", newAxisMask);
    NDValidation.validateNumerical("stridedSlice", "shrinkAxisMask", shrinkAxisMask);
    return Nd4j.exec(new TODO.StridedSlice(in, begin, end, strides, beginMask, endMask, ellipsisMask, newAxisMask, shrinkAxisMask))[0];
  }

  /**
   * Get a subset of the specified input, by specifying the first element, last element, and the strides.<br>
   * For example, if input is:<br>
   * [a, b, c]<br>
   * [d, e, f]<br>
   * [g, h, i]<br>
   * then stridedSlice(input, begin=[0,1], end=[2,2], strides=[2,1]) will return:<br>
   * [b, c]<br>
   * [h, i]<br>
   * <br>
   * <br>
   * @param name    Output variable name<br>
   * @param input   Variable to get subset of<br>
   * @param begin   Beginning index. Must be same length as rank of input array<br>
   * @param end     End index. Must be same length as the rank of the array<br>
   * @param strides Stride ("step size") for each dimension. Must be same length as the rank of the array. For example,<br>
   *                stride of 2 means take every second element.<br>
   * @return Subset of the input<br>
   *     <br>
   *
   * @param input  (NUMERIC type)
   * @param begin  (NUMERIC type)
   * @param end  (NUMERIC type)
   * @param strides  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray stridedSlice(INDArray input, INDArray begin, INDArray end, INDArray strides) {
    NDValidation.validateNumerical("stridedSlice", "input", input);
    NDValidation.validateNumerical("stridedSlice", "begin", begin);
    NDValidation.validateNumerical("stridedSlice", "end", end);
    NDValidation.validateNumerical("stridedSlice", "strides", strides);
    return Nd4j.exec(new TODO.StridedSlice(input, begin, end, strides))[0];
  }

  /**
   * Get a subset of the specified input, by specifying the first element, last element, and the strides.<br>
   * Operates as described in {@link #stridedSlice(SDVariable, long[], long[], long[])} with some extra mask arrays<br>
   * as described below.<br>
   * <br>
   * @param name           Output variable name<br>
   * @param in             Variable to get subset of<br>
   * @param begin          Beginning index<br>
   * @param end            End index<br>
   * @param strides        Stride ("step size") for each dimension. For example,<br>
   *                       stride of 2 means take every second element.<br>
   * @param beginMask      Bit mask: If the ith bit is set to 1, then the value in the begin long[] is ignored,<br>
   *                       and a value of 0 is used instead for the beginning index for that dimension<br>
   * @param endMask        Bit mask: If the ith bit is set to 1, then the value in the end long[] is ignored,<br>
   *                       and a value of size(i)-1 is used instead for the end index for that dimension<br>
   * @param ellipsisMask   Bit mask: only one non-zero value is allowed here. If a non-zero value is set, then other<br>
   *                       dimensions are inserted as required at the specified position<br>
   * @param newAxisMask    Bit mask: if the ith bit is set to 1, then the begin/end/stride values are ignored, and<br>
   *                       a size 1 dimension is inserted at this point<br>
   * @param shrinkAxisMask Bit mask: if the ith bit is set to 1, then the begin/end/stride values are ignored, and<br>
   *                       a size 1 dimension is removed at this point. Note that begin/end/stride values must<br>
   *                       result in a size 1 output for these dimensions<br>
   * @return A subset of the input array<br>
   *     <br>
   *
   * @param in  (NUMERIC type)
   * @param begin  (NUMERIC type)
   * @param end  (NUMERIC type)
   * @param strides  (NUMERIC type)
   * @param beginMask  (NUMERIC type)
   * @param endMask  (NUMERIC type)
   * @param ellipsisMask  (NUMERIC type)
   * @param newAxisMask  (NUMERIC type)
   * @param shrinkAxisMask  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray stridedSlice(INDArray in, INDArray begin, INDArray end, INDArray strides,
      INDArray beginMask, INDArray endMask, INDArray ellipsisMask, INDArray newAxisMask,
      INDArray shrinkAxisMask) {
    NDValidation.validateNumerical("stridedSlice", "in", in);
    NDValidation.validateNumerical("stridedSlice", "begin", begin);
    NDValidation.validateNumerical("stridedSlice", "end", end);
    NDValidation.validateNumerical("stridedSlice", "strides", strides);
    NDValidation.validateNumerical("stridedSlice", "beginMask", beginMask);
    NDValidation.validateNumerical("stridedSlice", "endMask", endMask);
    NDValidation.validateNumerical("stridedSlice", "ellipsisMask", ellipsisMask);
    NDValidation.validateNumerical("stridedSlice", "newAxisMask", newAxisMask);
    NDValidation.validateNumerical("stridedSlice", "shrinkAxisMask", shrinkAxisMask);
    return Nd4j.exec(new TODO.StridedSlice(in, begin, end, strides, beginMask, endMask, ellipsisMask, newAxisMask, shrinkAxisMask))[0];
  }

  /**
   * Sum array reduction operation, optionally along specified dimensions<br>
   * <br>
   * @param x          Input variable<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Output variable: reduced array of rank (input rank - num dimensions) if keepDims = false, or<br>
   * of rank (input rank) if keepdims = true<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray sum(INDArray x, INDArray dimensions) {
    NDValidation.validateNumerical("sum", "x", x);
    NDValidation.validateNumerical("sum", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Sum(x, dimensions))[0];
  }

  /**
   * Sum array reduction operation, optionally along specified dimensions.<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name       Output variable name<br>
   * @param x          Input variable<br>
   * @param keepDims   If true: keep the dimensions that are reduced on (as length 1). False: remove the reduction dimensions<br>
   * @param dimensions Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Output variable: reduced array of rank (input rank - num dimensions) if keepDims = false, or<br>
   * of rank (input rank) if keepdims = true<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray sum(INDArray x, INDArray keepDims, INDArray dimensions) {
    NDValidation.validateNumerical("sum", "x", x);
    NDValidation.validateNumerical("sum", "keepDims", keepDims);
    NDValidation.validateNumerical("sum", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Sum(x, keepDims, dimensions))[0];
  }

  /**
   * @param x          Input variable x<br>
   * @param y          Input variable y<br>
   * @param dimensions dimensions<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param y  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray tensorMmul(INDArray x, INDArray y, INDArray dimensions) {
    NDValidation.validateNumerical("tensorMmul", "x", x);
    NDValidation.validateNumerical("tensorMmul", "y", y);
    NDValidation.validateNumerical("tensorMmul", "dimensions", dimensions);
    return Nd4j.exec(new TODO.TensorMmul(x, y, dimensions))[0];
  }

  /**
   * Repeat (tile) the input tensor the specified number of times.<br>
   * For example, if input is<br>
   * [1, 2]<br>
   * [3, 4]<br>
   * and repeat is [2, 3]<br>
   * then output is<br>
   * [1, 2, 1, 2, 1, 2]<br>
   * [3, 4, 3, 4, 3, 4]<br>
   * [1, 2, 1, 2, 1, 2]<br>
   * [3, 4, 3, 4, 3, 4]<br>
   * <br>
   * <br>
   * @param name   Output variable name<br>
   * @param x      Input variable<br>
   * @param repeat Number of times to repeat in each axis. Must have length equal to the rank of the input array<br>
   * @return Output variable<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param repeat  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray tile(INDArray x, INDArray repeat) {
    NDValidation.validateNumerical("tile", "x", x);
    NDValidation.validateNumerical("tile", "repeat", repeat);
    return Nd4j.exec(new TODO.Tile(x, repeat))[0];
  }

  /**
   * @see #tile(String, SDVariable, int...)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param repeat  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray tile(INDArray x, INDArray repeat) {
    NDValidation.validateNumerical("tile", "x", x);
    NDValidation.validateNumerical("tile", "repeat", repeat);
    return Nd4j.exec(new TODO.Tile(x, repeat))[0];
  }

  /**
   * Matrix transpose operation: If input has shape [a,b] output has shape [b,a]<br>
   * <br>
   * @param name Output variable name<br>
   * @param x    Input variable<br>
   * @return Output variable (transposed input)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray transpose(INDArray x) {
    NDValidation.validateNumerical("transpose", "x", x);
    return Nd4j.exec(new TODO.Transpose(x))[0];
  }

  /**
   * Unsorted segment max operation. As per {@link #segmentMax(String, SDVariable, SDVariable)} but without<br>
   * the requirement for the indices to be sorted.<br>
   * If data =     [1, 3, 2, 6, 4, 9, 8]<br>
   * segmentIds =  [1, 0, 2, 0, 1, 1, 2]<br>
   * then output = [6, 9, 8] = [max(3,6), max(1,4,9), max(2,8)]<br>
   * <br>
   * @param name        Name of the output variable<br>
   * @param data        Data (variable) to perform unsorted segment max on<br>
   * @param segmentIds  Variable for the segment IDs<br>
   * @param numSegments Number of segments<br>
   * @return Unsorted segment max output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @param numSegments  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray unsortedSegmentMax(INDArray data, INDArray segmentIds, INDArray numSegments) {
    NDValidation.validateNumerical("unsortedSegmentMax", "data", data);
    NDValidation.validateNumerical("unsortedSegmentMax", "segmentIds", segmentIds);
    NDValidation.validateNumerical("unsortedSegmentMax", "numSegments", numSegments);
    return Nd4j.exec(new TODO.UnsortedSegmentMax(data, segmentIds, numSegments))[0];
  }

  /**
   * Unsorted segment mean operation. As per {@link #segmentMean(String, SDVariable, SDVariable)} but without<br>
   * the requirement for the indices to be sorted.<br>
   * If data =     [1, 3, 2, 6, 4, 9, 8]<br>
   * segmentIds =  [1, 0, 2, 0, 1, 1, 2]<br>
   * then output = [4.5, 4.666, 5] = [mean(3,6), mean(1,4,9), mean(2,8)]<br>
   * <br>
   * @param name        Name of the output variable<br>
   * @param data        Data (variable) to perform unsorted segment mean on<br>
   * @param segmentIds  Variable for the segment IDs<br>
   * @param numSegments Number of segments<br>
   * @return Unsorted segment mean output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @param numSegments  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray unsortedSegmentMean(INDArray data, INDArray segmentIds, INDArray numSegments) {
    NDValidation.validateNumerical("unsortedSegmentMean", "data", data);
    NDValidation.validateNumerical("unsortedSegmentMean", "segmentIds", segmentIds);
    NDValidation.validateNumerical("unsortedSegmentMean", "numSegments", numSegments);
    return Nd4j.exec(new TODO.UnsortedSegmentMean(data, segmentIds, numSegments))[0];
  }

  /**
   * Unsorted segment min operation. As per {@link #segmentMin(String, SDVariable, SDVariable)} but without<br>
   * the requirement for the indices to be sorted.<br>
   * If data =     [1, 3, 2, 6, 4, 9, 8]<br>
   * segmentIds =  [1, 0, 2, 0, 1, 1, 2]<br>
   * then output = [3, 1, 2] = [min(3,6), min(1,4,9), min(2,8)]<br>
   * <br>
   * @param name        Name of the output variable<br>
   * @param data        Data (variable) to perform unsorted segment min on<br>
   * @param segmentIds  Variable for the segment IDs<br>
   * @param numSegments Number of segments<br>
   * @return Unsorted segment min output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @param numSegments  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray unsortedSegmentMin(INDArray data, INDArray segmentIds, INDArray numSegments) {
    NDValidation.validateNumerical("unsortedSegmentMin", "data", data);
    NDValidation.validateNumerical("unsortedSegmentMin", "segmentIds", segmentIds);
    NDValidation.validateNumerical("unsortedSegmentMin", "numSegments", numSegments);
    return Nd4j.exec(new TODO.UnsortedSegmentMin(data, segmentIds, numSegments))[0];
  }

  /**
   * Unsorted segment product operation. As per {@link #segmentProd(String, SDVariable, SDVariable)} but without<br>
   * the requirement for the indices to be sorted.<br>
   * If data =     [1, 3, 2, 6, 4, 9, 8]<br>
   * segmentIds =  [1, 0, 2, 0, 1, 1, 2]<br>
   * then output = [4.5, 4.666, 5] = [mean(3,6), mean(1,4,9), mean(2,8)]<br>
   * <br>
   * @param name       Name of the output variable<br>
   * @param data       Data (variable) to perform unsorted segment product on<br>
   * @param segmentIds Variable for the segment IDs<br>
   * @return Unsorted segment product output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @param numSegments  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray unsortedSegmentProd(INDArray data, INDArray segmentIds, INDArray numSegments) {
    NDValidation.validateNumerical("unsortedSegmentProd", "data", data);
    NDValidation.validateNumerical("unsortedSegmentProd", "segmentIds", segmentIds);
    NDValidation.validateNumerical("unsortedSegmentProd", "numSegments", numSegments);
    return Nd4j.exec(new TODO.UnsortedSegmentProd(data, segmentIds, numSegments))[0];
  }

  /**
   * Unsorted segment sqrtN operation. Simply returns the sqrt of the count of the number of values in each segment<br>
   * If data =     [1, 3, 2, 6, 4, 9, 8]<br>
   * segmentIds =  [1, 0, 2, 0, 1, 1, 2]<br>
   * then output = [1.414, 1.732, 1.414] = [sqrt(2), sqrtN(3), sqrtN(2)]<br>
   * <br>
   * @param name       Name of the output variable<br>
   * @param data       Data (variable) to perform unsorted segment sqrtN on<br>
   * @param segmentIds Variable for the segment IDs<br>
   * @return Unsorted segment sqrtN output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @param numSegments  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray unsortedSegmentSqrtN(INDArray data, INDArray segmentIds, INDArray numSegments) {
    NDValidation.validateNumerical("unsortedSegmentSqrtN", "data", data);
    NDValidation.validateNumerical("unsortedSegmentSqrtN", "segmentIds", segmentIds);
    NDValidation.validateNumerical("unsortedSegmentSqrtN", "numSegments", numSegments);
    return Nd4j.exec(new TODO.UnsortedSegmentSqrtN(data, segmentIds, numSegments))[0];
  }

  /**
   * Unsorted segment sum operation. As per {@link #segmentSum(String, SDVariable, SDVariable)} but without<br>
   * the requirement for the indices to be sorted.<br>
   * If data =     [1, 3, 2, 6, 4, 9, 8]<br>
   * segmentIds =  [1, 0, 2, 0, 1, 1, 2]<br>
   * then output = [9, 14, 10] = [sum(3,6), sum(1,4,9), sum(2,8)]<br>
   * <br>
   * @param name        Name of the output variable<br>
   * @param data        Data (variable) to perform unsorted segment sum on<br>
   * @param segmentIds  Variable for the segment IDs<br>
   * @param numSegments Number of segments<br>
   * @return Unsorted segment sum output<br>
   *     <br>
   *
   * @param data  (NUMERIC type)
   * @param segmentIds  (NUMERIC type)
   * @param numSegments  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray unsortedSegmentSum(INDArray data, INDArray segmentIds, INDArray numSegments) {
    NDValidation.validateNumerical("unsortedSegmentSum", "data", data);
    NDValidation.validateNumerical("unsortedSegmentSum", "segmentIds", segmentIds);
    NDValidation.validateNumerical("unsortedSegmentSum", "numSegments", numSegments);
    return Nd4j.exec(new TODO.UnsortedSegmentSum(data, segmentIds, numSegments))[0];
  }

  /**
   * Variance array reduction operation, optionally along specified dimensions<br>
   * <br>
   * @param name          Output variable name<br>
   * @param x             Input variable<br>
   * @param biasCorrected If true: divide by (N-1) (i.e., sample variable). If false: divide by N (population variance)<br>
   * @param dimensions    Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Output variable: reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param biasCorrected  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray variance(INDArray x, INDArray biasCorrected, INDArray dimensions) {
    NDValidation.validateNumerical("variance", "x", x);
    NDValidation.validateNumerical("variance", "biasCorrected", biasCorrected);
    NDValidation.validateNumerical("variance", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Variance(x, biasCorrected, dimensions))[0];
  }

  /**
   * Variance array reduction operation, optionally along specified dimensions<br>
   * Note that if keepDims = true, the output variable has the same rank as the input variable,<br>
   * with the reduced dimensions having size 1. This can be useful for later broadcast operations (such as subtracting<br>
   * the mean along a dimension).<br>
   * Example: if input has shape [a,b,c] and dimensions=[1] then output has shape:<br>
   * keepDims = true: [a,1,c]<br>
   * keepDims = false: [a,c]<br>
   * <br>
   * @param name          Output variable name<br>
   * @param x             Input variable<br>
   * @param biasCorrected If true: divide by (N-1) (i.e., sample variable). If false: divide by N (population variance)<br>
   * @param keepDims      If true: keep the dimensions that are reduced on (as size 1). False: remove the reduction dimensions<br>
   * @param dimensions    Dimensions to reduce over. If dimensions are not specified, full array reduction is performed<br>
   * @return Output variable: reduced array of rank (input rank - num dimensions)<br>
   *     <br>
   *
   * @param x  (NUMERIC type)
   * @param biasCorrected  (NUMERIC type)
   * @param keepDims  (NUMERIC type)
   * @param dimensions  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray variance(INDArray x, INDArray biasCorrected, INDArray keepDims,
      INDArray dimensions) {
    NDValidation.validateNumerical("variance", "x", x);
    NDValidation.validateNumerical("variance", "biasCorrected", biasCorrected);
    NDValidation.validateNumerical("variance", "keepDims", keepDims);
    NDValidation.validateNumerical("variance", "dimensions", dimensions);
    return Nd4j.exec(new TODO.Variance(x, biasCorrected, keepDims, dimensions))[0];
  }

  /**
   * Return a variable of all 0s, with the same shape as the input variable. Note that this is dynamic:<br>
   * if the input shape changes in later execution, the returned variable's shape will also be updated<br>
   * <br>
   * @param name  Name of the new SDVariable<br>
   * @param input Input SDVariable<br>
   * @return A new SDVariable with the same (dynamic) shape as the input<br>
   *     <br>
   *
   * @param input  (NUMERIC type)
   * @return output  (NUMERIC type)
   */
  public INDArray zerosLike(INDArray input) {
    NDValidation.validateNumerical("zerosLike", "input", input);
    return Nd4j.exec(new TODO.ZerosLike(input))[0];
  }
}
