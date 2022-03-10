package co.ex.frmwrk.gateway.topic.impl;

import co.ex.eventer.model.ThingDtoEvent;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ThingDtoSave_EventMapper {

  ThingDtoEvent dtoSaveToDtoEvent(ThingDtoSave thingDtoSave);
}
