package com.ksi.healthcaresystem.registration.event;

import com.ksi.healthcaresystem.registration.dto.PatientDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PatientRegistrationEvent extends ApplicationEvent {
    private final PatientDto message;

    public PatientRegistrationEvent(Object source, PatientDto message) {
        super(source);
        this.message = message;
    }
}
