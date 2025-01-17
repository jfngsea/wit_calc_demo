package pt.jfmartins.rest.route;

import lombok.RequiredArgsConstructor;
import model.CalcResult;
import model.CalculatorOpsEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;
import pt.jfmartins.rest.service.RestService;


@RestController
@RequiredArgsConstructor
public class CalcRoute {

    private final RestService service;

    @GetMapping("/sum")
    public CalcResult sum(@RequestParam("a") String a, @RequestParam("b") String b) {
        try{
            double ad = Double.parseDouble(a);
            double bd = Double.parseDouble(b);

            return new CalcResult(service.calculator_op(CalculatorOpsEnum.SUM, ad, bd));
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
