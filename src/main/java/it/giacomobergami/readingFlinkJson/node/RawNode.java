/*
 * This file is part of readingFlinkJson
 *
 * Copyright (C) 2017 - Giacomo Bergami
 *
 * readingFlinkJson is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * readingFlinkJson is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with readingFlinkJson. If not, see <http://www.gnu.org/licenses/>.
 */
package it.giacomobergami.readingFlinkJson.node;

import com.google.gson.annotations.SerializedName;
import it.giacomobergami.readingFlinkJson.node.fields.namevalues.JSONNameValue;
import it.giacomobergami.readingFlinkJson.node.fields.Predecessor;
import it.giacomobergami.readingFlinkJson.node.fields.OptimizerProperties;

import java.util.List;

/**
 * Raw informations to be associated to the node
 */
public class RawNode {

  /**
   * Representation of a node within the computation graph. Dependingly on the different
   * possible formats, it could be either a string or an integer
   */
  public String id;

  /**
   * Type of the task associated to the node. It could be either source, sink or
   * pact, meaning that it is an intermediate node.
   */
  public String type;

  /**
   * Defining the type of the task run by the element
   */
  String pact;

  /**
   * Defining the part of the code run by this node
   */
  String contents;

  /**
   * Parallelism level set at this stage
   */
  String parallelism;

  /**
   * Computational strategy adopted by Flink
   */
  @SerializedName("driver_strategy")
  String driverStrategy;

  /**
   * Dependingly on the representation and format, the optimizer properties
   * could be expressed within the current variable or within @code{globalProperties},
   * @code{localProperties}, @code{estimates}, @code{costs} and @code{compilerHints}
   */
  @SerializedName("optimizer_properties")
  private OptimizerProperties optimizerProperties;

  @SerializedName("global_properties")
  private List<JSONNameValue> globalProperties;

  @SerializedName("local_properties")
  private List<JSONNameValue> localProperties;

  private List<JSONNameValue> estimates;
  private List<JSONNameValue> costs;

  @SerializedName("compiler_hints")
  private List<JSONNameValue> compilerHints;

  @SerializedName(value = "predecessors", alternate = {"inputs"})
  Predecessor[] predecessors;

  public List<JSONNameValue> getGlobalProperties() {
    return globalProperties == null ? optimizerProperties.globalProperties : globalProperties;
  }

  public List<JSONNameValue> getLocalProperties() {
    return localProperties == null ? optimizerProperties.localProperties : localProperties;
  }

  public List<JSONNameValue> getEstimates() {
    return estimates == null ? optimizerProperties.estimates : estimates;
  }

  public List<JSONNameValue> getCosts() {
    return costs == null ? optimizerProperties.costs : costs;
  }

  public List<JSONNameValue> getCompilerHints() {
    return compilerHints == null ? optimizerProperties.compilerHints : compilerHints;
  }
}
