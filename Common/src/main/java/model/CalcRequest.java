package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalcRequest {
    @JsonProperty("key")
    String key;

    @JsonProperty("op")
    CalculatorOpsEnum op;

    @JsonProperty("a")
    double a;

    @JsonProperty("b")
    double b;

    public CalcRequest() {}

    public CalcRequest(CalculatorOpsEnum op, double a, double b) {
        this.op = op;
        this.a = a;
        this.b = b;
    }

    public CalcRequest(String key, CalculatorOpsEnum op, double a, double b) {
        this.key = key;
        this.op = op;
        this.a = a;
        this.b = b;
    }
}
