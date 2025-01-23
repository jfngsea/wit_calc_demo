package pt.jfmartins.rest.route;

import lombok.RequiredArgsConstructor;
import model.CalcResult;
import model.CalculatorOpsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;
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
        } catch (KafkaReplyTimeoutException e ){
            throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subtract")
    public CalcResult subtract(@RequestParam("a") String a, @RequestParam("b") String b) {
        try{
            double ad = Double.parseDouble(a);
            double bd = Double.parseDouble(b);

            return new CalcResult(service.calculator_op(CalculatorOpsEnum.SUBTRACT, ad, bd));

        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (KafkaReplyTimeoutException e ){
            throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/multiply")
    public CalcResult multiply(@RequestParam("a") String a, @RequestParam("b") String b) {
        try{
            double ad = Double.parseDouble(a);
            double bd = Double.parseDouble(b);

            return new CalcResult(service.calculator_op(CalculatorOpsEnum.MULTIPLY, ad, bd));

        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (KafkaReplyTimeoutException e ){
            throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/divide")
    public CalcResult divide(@RequestParam("a") String a, @RequestParam("b") String b) {
        try{
            double ad = Double.parseDouble(a);
            double bd = Double.parseDouble(b);

            return new CalcResult(service.calculator_op(CalculatorOpsEnum.DIVIDE, ad, bd));

        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (KafkaReplyTimeoutException e ){
            throw new ResponseStatusException(HttpStatus.REQUEST_TIMEOUT);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
