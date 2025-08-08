package org.example.aletheiacares;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HelpRequestMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "category", source = "category.name")
    HelpRequestDto toDto(HelpRequest entity);

}
