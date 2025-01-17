package pt.jfmartins.rest.service;

import org.slf4j.MDC;
import lombok.extern.slf4j.Slf4j;
import model.CalcRequest;
import model.CalcResult;
import model.CalculatorOpsEnum;

import org.springframework.stereotype.Service;


import java.util.UUID;


@Slf4j
@Service
public class RestServiceImpl implements RestService {




    @Override
    public double calculator_op(CalculatorOpsEnum op, double a, double b) {

        try {
            String requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId);

            log.info("request id {}", requestId);

            if (op == CalculatorOpsEnum.SUM) {
                return a + b;
            }
        } finally {
            MDC.clear();
        }

        return 0;
    }

}
