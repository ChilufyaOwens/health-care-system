package com.ksi.healthcaresystem.registration.event;

import com.ksi.healthcaresystem.commons.events.HealthCareSystemEvent;
import com.ksi.healthcaresystem.registration.dto.PatientDto;
import lombok.Getter;

@Getter
public class PatientRegistrationEvent extends HealthCareSystemEvent<PatientDto> {
    public PatientRegistrationEvent(Object source, PatientDto message) {
        super(source, message);
    }
}
