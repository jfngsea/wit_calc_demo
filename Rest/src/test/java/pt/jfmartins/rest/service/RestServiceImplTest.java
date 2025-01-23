package pt.jfmartins.rest.service;

import config.KafkaTopics;
import kafka.test.annotation.ClusterTests;
import model.CalcRequest;
import model.CalcResult;
import model.CalculatorOpsEnum;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.KafkaReplyTimeoutException;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class RestServiceImplTest {
    @Mock
    private ReplyingKafkaTemplate<String, CalcRequest, CalcResult> kafkaTemplate;


    private RestServiceImpl restService;


    @BeforeEach
    void setUp() {
        // Initialize mocks created above
        MockitoAnnotations.initMocks(this);
        restService = new RestServiceImpl(kafkaTemplate);
    }

    @Test
    void testCalculatorOp_Success() throws Exception {
        // Arrange
        CalcRequest request = new CalcRequest("1", CalculatorOpsEnum.SUM, 5.0, 3.0);
        CalcResult response = new CalcResult(8.0);

        ProducerRecord<String, CalcRequest> producerRecord = new ProducerRecord<>(KafkaTopics.CALC_REQ, request);
        producerRecord.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, KafkaTopics.CALC_RES.getBytes()));

        ConsumerRecord<String, CalcResult> consumerRecord = new ConsumerRecord<>(KafkaTopics.CALC_RES, 0, 0, null, response);

        RequestReplyFuture<String, CalcRequest, CalcResult> future = mock(RequestReplyFuture.class);
        CompletableFuture sendFuture = CompletableFuture.completedFuture(mock(SendResult.class));
        when(future.getSendFuture()).thenReturn(sendFuture);
        when(future.get()).thenReturn(consumerRecord);

        when(kafkaTemplate.sendAndReceive(any(ProducerRecord.class))).thenReturn(future);

        // Act
        double result = restService.calculator_op(CalculatorOpsEnum.SUM, 5.0, 3.0);

        // Assert
        assertEquals(8.0, result);
        verify(kafkaTemplate).sendAndReceive(any(ProducerRecord.class));
    }

    @Test
    void testCalculatorOp_ReplyTimeoutException() {
        // Arrange
        CalcRequest request = new CalcRequest("1", CalculatorOpsEnum.SUM, 5.0, 3.0);
        ProducerRecord<String, CalcRequest> producerRecord = new ProducerRecord<>(KafkaTopics.CALC_REQ, request);

        when(kafkaTemplate.sendAndReceive(any(ProducerRecord.class))).thenThrow(new KafkaReplyTimeoutException("Reply timeout"));

        // Act & Assert
        KafkaReplyTimeoutException exception = assertThrows(
                KafkaReplyTimeoutException.class,
                () -> restService.calculator_op(CalculatorOpsEnum.SUM, 5.0, 3.0)
        );

        assertEquals("Reply timeout", exception.getMessage());
    }

    @Test
    void testCalculatorOp_ExecutionException() throws Exception {
        // Arrange
        CalcRequest request = new CalcRequest("1", CalculatorOpsEnum.SUM, 5.0, 3.0);
        ProducerRecord<String, CalcRequest> producerRecord = new ProducerRecord<>(KafkaTopics.CALC_REQ, request);

        RequestReplyFuture<String, CalcRequest, CalcResult> future = mock(RequestReplyFuture.class);
        CompletableFuture<SendResult<String, CalcRequest>> sendFuture = mock(CompletableFuture.class);

        when(sendFuture.get()).thenThrow(new ExecutionException("Error sending message", null));
        when(future.getSendFuture()).thenReturn(sendFuture);
        when(kafkaTemplate.sendAndReceive(any(ProducerRecord.class))).thenReturn(future);

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> restService.calculator_op(CalculatorOpsEnum.SUM, 5.0, 3.0)
        );

        assertTrue(exception.getMessage().contains("Error sending message"));
    }
}
