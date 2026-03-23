package com.doacaoong.notification_service.consumer;

import com.doacaoong.notification_service.event.DonationEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component // diz ao Spring para gerenciar essa classe
public class DonationConsumer {

    @RabbitListener(queues = "donation.queue")//fica escutando a fila, quando chega uma mensagem o spring chama esse metódo automaticamente
    public void consume(DonationEvent event){
        System.out.println("Notificação recebida! ");
        System.out.println("Doação ID: " + event.getDonationId());
        System.out.println("Usuáro ID: " + event.getUserId());
        System.out.println("Campanha ID: " + event.getCampaignId());
        System.out.println("Valor: R$ " + event.getAmount());
    }
}
