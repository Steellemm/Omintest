package org.omintest.omintestextension.enviroment.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Data(
    @JsonProperty("environment")
    val environment: Map<String, Map<String, Any>>,
    @JsonProperty("scenarios")
    val scenarios: List<Scenario>,
    @JsonProperty("generators")
    val generators: Map<String, Any>
)

data class Scenario(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("steps")
    val steps: List<StepInfo>
)

data class StepInfo(
    @JsonProperty("uses")
    val uses: String,
    @JsonProperty("id")
    val id: String,
    @JsonProperty("with")
    val with: Map<String, StepData>
)

data class StepData(
    @JsonProperty("type")
    val type: String,
    @JsonProperty("value")
    val value: Any
)

