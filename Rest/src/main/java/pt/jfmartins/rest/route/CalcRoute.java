package pt.jfmartins.rest.route;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

;

@RestController
@RequiredArgsConstructor
public class CalcRoute {

    @GetMapping("/sum")
    public double sum(@RequestParam("a") String a, @RequestParam("b") String b) {
        try{
            double ad = Double.parseDouble(a);
            double bd = Double.parseDouble(b);

            return ad + bd;
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}
