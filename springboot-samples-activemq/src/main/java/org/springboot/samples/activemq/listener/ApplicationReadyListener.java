package org.springboot.samples.activemq.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyListener {

    @Autowired
    private JmsListenerEndpointRegistry registery;

    @Autowired
    private ApplicationContext context;

    @EventListener
    public void onReadyEvent(ApplicationReadyEvent event){
        System.out.println("Do something when application is ready");
    }

    @EventListener
    public void onClosedEvent(ApplicationClosedEvent event){
        System.out.println("Do something when application is closed");
        registery.getListenerContainers().stream().findFirst().ifPresent(
                messageListenerContainer -> messageListenerContainer.stop()
        );
        SpringApplication.exit(context, () -> 0);
        System.exit(0);
    }

}
