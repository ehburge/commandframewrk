package co.ex.frmwrk.gateway.topic.impl;

import co.ex.framewrk.eventer.model.ThingDtoSaveEvent;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ThingDtoSave_EventMapper {

  ThingDtoSaveEvent dtoSaveToDtoEvent(ThingDtoSave thingDtoSave);
}
