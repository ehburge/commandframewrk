package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import com.ex.thing.cmd.impl.AppThingCommandSave;
import model.AppThingComments;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = {AppDtoThingPartsMapper.class}, componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface AppThingCommandSaveThingDtoSaveMapper {

  @Mapping(target = "comments", source = "comments", qualifiedByName = "Comments")
  ThingDtoSave appThingCommandSaveToThingDtoSave(AppThingCommandSave appThingCommandSave);

  @Named("Comments")
  static ThingDtoComments mapComments(AppThingComments comments) {
    List<String> thingComments = new ArrayList<>();
    comments.getComments().forEach(s -> thingComments.add(s));

    return ThingDtoComments.builder().comments(thingComments).build();
  }
}
