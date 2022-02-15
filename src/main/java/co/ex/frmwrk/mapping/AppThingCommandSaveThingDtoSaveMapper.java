package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import com.ex.thing.cmd.impl.AppThingCommandSave;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AppThingCommandSaveThingDtoSaveMapper {

  ThingDtoSave appThingCommandSaveToThingDtoSave(AppThingCommandSave appThingCommandSave);
}
