package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import com.ex.thing.cmd.impl.AppThingCommandSave;
import com.ex.thing.model.AppThingComments;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AppThingCommandSaveThingDtoSaveMapper {

  ThingDtoSave appThingCommandSaveToThingDtoSave(AppThingCommandSave appThingCommandSave);
}
