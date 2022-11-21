package com.it.academy.library.listener.entity;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EntityListener {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    @EventListener
    public void acceptEntityRead(@NotNull EntityEvent entityEvent) {
        if (entityEvent.getAccessType() != AccessType.READ) {
            log.warn(ANSI_GREEN + "Entity type: " + ANSI_RED + entityEvent.getAccessType() + "\n" + ANSI_RESET +
                    entityEvent);
        } else {
            log.info(ANSI_GREEN + "Entity type: " + ANSI_BLUE + entityEvent.getAccessType() + "\n" + ANSI_RESET +
                    entityEvent);
        }
    }
}
