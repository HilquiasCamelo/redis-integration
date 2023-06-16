package com.redis;

import com.redis.service.RedisService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQConsumer {
    private final RedisService redisService;

    public RabbitMQConsumer(RedisService redisService) {
        this.redisService = redisService;
    }

    @RabbitListener(queues = "nome_da_fila")
    public void receiveMessage(String message) {
        // Processar a mensagem recebida e salvá-la no Redis usando o RedisService
        redisService.saveMessage("str-topic", message);

        // Log para verificar se a mensagem foi recebida e enviada para o Redis
        log.info("Mensagem recebida do RabbitMQ: " + message);
        log.info("Mensagem enviada para o Redis");
    }
}
