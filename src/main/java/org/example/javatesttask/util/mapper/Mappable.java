package org.example.javatesttask.util.mapper;

import java.util.List;

public interface Mappable<Entity, DTO> {

    Entity toEntity(DTO dto);

    List<Entity> toEntityList(List<DTO> dto);

    DTO toDTO(Entity entity);

    List<DTO> toDTOList(List<Entity> entity);
}
