package co.ex.frmwrk.mapping;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.gateway.impl.ThingDtoSave000;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ThingDtoSave_ThingEntity {

  @Mappings({
    @Mapping(target = "content_type", expression = "java(thingDtoSave000.getClass().getName())"),
    @Mapping(
        target = "entity_content",
        expression = "java(co.ex.frmwrk.config.JsonMapper.toJson(thingDtoSave000))"),
    @Mapping(
        target = "dttm",
        expression = "java(new java.sql.Timestamp(new java.util.Date().getTime()))")
  })
  public abstract ThingEntity thingDtoSaveToThingEntity(ThingDtoSave000 thingDtoSave000);

  public ThingDtoSave000 fromEntityContent(ThingEntity thingEntity) {
    ThingDtoSave000 thingDtoSave000 = null;
    try {
      thingDtoSave000 =
          (ThingDtoSave000)
              JsonMapper.fromJson(
                  thingEntity.getEntity_content(), Class.forName(thingEntity.getContent_type()));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return thingDtoSave000;
  }
}
