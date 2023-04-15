package com.ksi.healthcaresystem.commons.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

@Getter
@AllArgsConstructor
@ToString
public class HealthCareSystemEvent<T> implements ResolvableTypeProvider {
    private Object source;
    private T message;
    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(
                getClass(),
                ResolvableType.forInstance(getSource())
        );
    }
}
