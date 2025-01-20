package pt.jfmartins.calculator;

import config.KafkaTopics;
import lombok.extern.slf4j.Slf4j;
import model.CalcRequest;
import model.CalcResult;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CalculatorConsumer {

    @KafkaListener(topics = KafkaTopics.CALC_REQ, containerFactory = "requestListenerContainerFactory")
    @SendTo()
    public CalcResult receive(CalcRequest message) {
        String message_key = message.getKey();
        log.info("Processsing request ID: {}", message_key);
        try {
            MDC.put("requestId", message_key);

            double a = message.getA();
            double b = message.getB();

            log.info("Received Message: op= %s a= %f b= %f\n", "1", message.getOp().name(), a, b);
            double result = 0;

            switch (message.getOp()){
                case SUM -> result = CalcMethods.sum(a, b);
                case SUBTRACT -> result = CalcMethods.subtract(a, b);
                case MULTIPLY -> result = CalcMethods.multiply(a, b);
                case DIVIDE -> result = CalcMethods.divide(a, b);
            }
            return new CalcResult(result);
        } finally {
            MDC.clear();
        }

    }

}