package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ThingDtoSaveToThingEntity {
  ThingDtoSaveToThingEntity INSTANCE = Mappers.getMapper(ThingDtoSaveToThingEntity.class);

  @Mappings({@Mapping(target = "id", expression = "java(null)")})
  ThingEntity thingDtoSaveToThingEntity(ThingDtoSave thingDtoSave);

  @InheritInverseConfiguration
  ThingDtoSave thingEntityToThingDtoSave(ThingEntity thingEntity);
}
