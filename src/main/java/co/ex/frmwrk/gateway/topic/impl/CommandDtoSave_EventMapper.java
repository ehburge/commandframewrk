package co.ex.frmwrk.gateway.topic.impl;

import co.ex.framewrk.eventer.model.DtoSaveEvent;
import co.ex.framewrk.eventer.model.impl.DtoSaveEventImpl;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommandDtoSave_EventMapper {

  DtoSaveEventImpl dtoSaveToDtoEvent(DtoCommandSave dtoCommandSave);
}
