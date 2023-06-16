package com.redis.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class MessageService {
    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName = "redis_respost";

    @Autowired
    public MessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessage(String message) {
        rabbitTemplate.convertAndSend(exchangeName, "", message);
        log.info("Mensagem publicada: " + message);
    }

    @RabbitListener (queues = "nome_da_fila")
    public void receiveMessage(String message) {
        log.info("Mensagem recebida: " + message);
    }
}