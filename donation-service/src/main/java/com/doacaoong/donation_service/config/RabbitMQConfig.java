package com.doacaoong.donation_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig { // classe que define os componentes do RabbiMQ como beans gerenciados pelo Spring

    @Bean // cada metodo anotado com Bean cria um componente que o Spring registra automaticamente
    public Queue queue(){ // cria a fila onde as mensagens vão ficar esperando
        return new Queue("donation.queue");
    }

    @Bean
    public DirectExchange exchange(){ // cria o exchange que roteia as mensagens e define para irão
        return new DirectExchange("donation.exchange");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange){ // conecta a fila ao exchange usando a roting key
        return BindingBuilder.bind(queue).to(exchange).with("donation.created");
    }
    @Bean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
