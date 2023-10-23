package co.ex.frmwrk.gateway.topic.impl;

import co.ex.framewrk.eventer.model.impl000.ThingDtoSaveEvent000;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ThingDtoSave_EventMapper {

  ThingDtoSaveEvent000 dtoSaveToDtoEvent(DtoCommandSave dtoCommandSave);
}
