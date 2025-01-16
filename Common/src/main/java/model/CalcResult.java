package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalcResult {
    @JsonProperty("result")
    double result;

    public CalcResult() {}
    public CalcResult(double result) {
        this.result = result;
    }
}
