package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CalculatorOpsEnum {
    @JsonProperty("sum")
    SUM,
    @JsonProperty("subtract")
    SUBTRACT,
    @JsonProperty("multiply")
    MULTIPLY,
    @JsonProperty("divide")
    DIVIDE;

}
