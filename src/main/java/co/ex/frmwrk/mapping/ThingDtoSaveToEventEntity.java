package co.ex.frmwrk.mapping;

import co.ex.frmwrk.event.jpa.EventEntity;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ThingDtoSaveToEventEntity {
  ThingDtoSaveToEventEntity INSTANCE = Mappers.getMapper(ThingDtoSaveToEventEntity.class);

  @Mappings({
    @Mapping(target = "event_id", expression = "java(null)"),
    @Mapping(target = "event_type", constant = "CREATED"),
    @Mapping(target = "entity_type", constant = "Thing"),
    @Mapping(target = "entity_id", source = "thingNbr"),
    @Mapping(target = "event_data", ignore = true)
  })
  EventEntity thingDtoSaveToThingEntity(ThingDtoSave thingDtoSave);

  @AfterMapping
  default void mapToEventData(
      @MappingTarget EventEntity.EventEntityBuilder eventEntityBuilder, ThingDtoSave thingDtoSave) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      eventEntityBuilder.event_data(objectMapper.writeValueAsString(thingDtoSave));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}