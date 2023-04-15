package com.ksi.healthcaresystem.notificationservice.config.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksi.healthcaresystem.commons.dto.MessageDto;
import java.nio.charset.StandardCharsets;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.errors.SerializationException;
import java.util.Map;

@Log4j2
public class MessageDeserializer implements Deserializer<MessageDto>{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public MessageDto deserialize(String topic, byte[] data) {
        try{
            if(data == null){
                log.info("Null received at deserializing");
                return null;
            }
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), MessageDto.class);
        }catch (Exception exception){
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }


    @Override
    public void close() {
        Deserializer.super.close();
    }
}
