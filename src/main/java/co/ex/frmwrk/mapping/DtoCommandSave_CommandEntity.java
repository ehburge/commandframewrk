package co.ex.frmwrk.mapping;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.gateway.persist.CommandEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class DtoCommandSave_CommandEntity {

  @Mappings({
    @Mapping(target = "content_type", expression = "java(dtoCommandSave.getClass().getName())"),
    @Mapping(
        target = "entity_content",
        expression = "java(co.ex.frmwrk.config.JsonMapper.toJson(dtoCommandSave))"),
    @Mapping(
        target = "dttm",
        expression = "java(new java.sql.Timestamp(new java.util.Date().getTime()))")
  })
  public abstract CommandEntity commandDtoSaveToCommandEntity(DtoCommandSave dtoCommandSave);

  public DtoCommandSave fromEntityContent(CommandEntity commandEntity) {
    DtoCommandSave dtoCommandSave = null;
    try {
      dtoCommandSave =
          (DtoCommandSave)
              JsonMapper.fromJson(
                  commandEntity.getEntity_content(), Class.forName(commandEntity.getContent_type()));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return dtoCommandSave;
  }
}
