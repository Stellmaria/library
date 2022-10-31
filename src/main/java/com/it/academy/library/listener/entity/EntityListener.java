package com.it.academy.library.listener.entity;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EntityListener {

    @EventListener
    public void acceptEntityRead(@NotNull EntityEvent entityEvent) {
        if (entityEvent.getAccessType() != AccessType.READ) {
            log.warn("Entity type: " + entityEvent.getAccessType() + "\n" + entityEvent);
        } else {
            log.info("Entity type: " + entityEvent.getAccessType() + "\n" + entityEvent);
        }
    }
}
