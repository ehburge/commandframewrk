package co.ex.frmwrk.mapping;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class DtoCommandSave_ThingEntity {

  @Mappings({
    @Mapping(target = "content_type", expression = "java(dtoCommandSave.getClass().getName())"),
    @Mapping(
        target = "entity_content",
        expression = "java(co.ex.frmwrk.config.JsonMapper.toJson(dtoCommandSave))"),
    @Mapping(
        target = "dttm",
        expression = "java(new java.sql.Timestamp(new java.util.Date().getTime()))")
  })
  public abstract ThingEntity thingDtoSaveToThingEntity(DtoCommandSave dtoCommandSave);

  public DtoCommandSave fromEntityContent(ThingEntity thingEntity) {
    DtoCommandSave dtoCommandSave = null;
    try {
      dtoCommandSave =
          (DtoCommandSave)
              JsonMapper.fromJson(
                  thingEntity.getEntity_content(), Class.forName(thingEntity.getContent_type()));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return dtoCommandSave;
  }
}
