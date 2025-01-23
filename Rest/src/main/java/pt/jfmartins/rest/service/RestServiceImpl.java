package pt.jfmartins.rest.service;

import config.KafkaTopics;
import lombok.extern.slf4j.Slf4j;
import model.CalcRequest;
import model.CalcResult;
import model.CalculatorOpsEnum;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class RestServiceImpl implements RestService {

    private  ReplyingKafkaTemplate<String, CalcRequest, CalcResult> kafkaTemplate; // note: not annotated

    @Autowired
    public RestServiceImpl(ReplyingKafkaTemplate<String, CalcRequest, CalcResult> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }



    @Override
    public double calculator_op(CalculatorOpsEnum op, double a, double b) {

        try {
            String requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId);

            log.info("request id {}", requestId);

            // create producer record
            ProducerRecord<String, CalcRequest> record = new ProducerRecord<>(KafkaTopics.CALC_REQ, new CalcRequest(requestId, op, a, b));
            // set reply topic in header
            record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, KafkaTopics.CALC_RES.getBytes()));

            // post in kafka topic
            RequestReplyFuture<String, CalcRequest, CalcResult> sendAndReceive = null;
            try{
                sendAndReceive = kafkaTemplate.sendAndReceive(record);

            }catch (KafkaReplyTimeoutException e){
                log.error(e.getMessage());
                throw e;
            }


            // confirm if producer produced successfully
            try {
                SendResult<String, CalcRequest> sendResult = sendAndReceive.getSendFuture().get();
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }

            // get consumer record
            ConsumerRecord<String, CalcResult> consumerRecord = null;
            try {
                consumerRecord = sendAndReceive.get();
                return consumerRecord.value().getResult();
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }

        finally {
            MDC.clear();
        }



    }

}
