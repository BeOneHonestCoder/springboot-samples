package org.springboot.samples.activemq.listener;

import org.springframework.context.ApplicationEvent;

public class ApplicationClosedEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public ApplicationClosedEvent(Object source) {
        super(source);
    }
}
