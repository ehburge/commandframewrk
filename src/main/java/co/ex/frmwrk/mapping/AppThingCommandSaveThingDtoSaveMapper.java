package co.ex.frmwrk.mapping;

import cmd.impl.AppThingCommandSave;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingComment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface AppThingCommandSaveThingDtoSaveMapper {
  AppThingCommandSaveThingDtoSaveMapper INSTANCE =
      Mappers.getMapper(AppThingCommandSaveThingDtoSaveMapper.class);

  @Mappings({@Mapping(target = "comments", source = "comments", qualifiedByName = "comments")})
  ThingDtoSave appThingCommandSaveToThingDtoSave(AppThingCommandSave appThingCommandSave);

  @Named("comments")
  static List<ThingComment> mapComments(List<String> comments) {
    List<ThingComment> thingComments = new ArrayList<>();
    comments.forEach( s -> thingComments.add(new ThingComment(s)) );

    return thingComments;
  }
}
