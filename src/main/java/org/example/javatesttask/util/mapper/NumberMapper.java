package org.example.javatesttask.util.mapper;

import org.example.javatesttask.model.Number;
import org.example.javatesttask.web.dto.NumberDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NumberMapper extends Mappable<Number, NumberDto> {
}
