package co.ex.frmwrk.mapping;

import co.ex.eventer.event.JsonMapper;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class ThingDtoSave_ThingEntity {

  @Mappings({
    @Mapping(target = "uuid", source = "uuid"),
    @Mapping(target = "content_type", expression = "java(thingDtoSave.getClass().getName())"),
    @Mapping(
        target = "entity_content",
        expression = "java(co.ex.eventer.event.JsonMapper.toJson(thingDtoSave))"),
    @Mapping(
        target = "dttm",
        expression = "java(new java.sql.Timestamp(new java.util.Date().getTime()))")
  })
  public abstract ThingEntity thingDtoSaveToThingEntity(ThingDtoSave thingDtoSave);

  public abstract ThingDtoSave thingEntityToThingDtoSave(ThingEntity thingEntity);

  public ThingDtoSave fromEntityContent(ThingEntity thingEntity) {
    ThingDtoSave thingDtoSave = null;
    try {
      thingDtoSave =
          (ThingDtoSave)
              JsonMapper.fromJson(
                  thingEntity.getEntity_content(), Class.forName(thingEntity.getContent_type()));
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return thingDtoSave;
  }
}
