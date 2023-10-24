package co.ex.frmwrk.mapping;

import co.ex.app.cmd.impl.AppCommandSave;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AppCommandSaveCommandDtoSaveMapper {

  DtoCommandSave appCommandSaveToCommandDtoSave(AppCommandSave appCommandSave);
}
