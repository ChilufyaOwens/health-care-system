package com.ksi.healthcaresystem.notificationservice.event;

import com.ksi.healthcaresystem.notificationservice.dto.EmailDetailsDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmailSenderEvent extends ApplicationEvent {
    private final EmailDetailsDto message;

    public EmailSenderEvent(Object source, EmailDetailsDto message) {
        super(source);
        this.message = message;
    }
}
