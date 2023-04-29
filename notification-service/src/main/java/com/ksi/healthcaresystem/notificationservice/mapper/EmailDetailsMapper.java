package com.ksi.healthcaresystem.notificationservice.mapper;

import com.ksi.healthcaresystem.notificationservice.dto.EmailDetailsDto;
import com.ksi.healthcaresystem.notificationservice.entity.EmailDetails;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmailDetailsMapper {
    EmailDetails toEntity(EmailDetailsDto emailDetailsDto);
    EmailDetailsDto toDto(EmailDetails emailDetails);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmailDetails partialUpdate(
            EmailDetailsDto emailDetailsDto,
            @MappingTarget EmailDetails emailDetails
    );
}
