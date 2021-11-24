package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingComment;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingPart;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ThingDtoSaveToThingEntity {
  ThingDtoSaveToThingEntity INSTANCE = Mappers.getMapper(ThingDtoSaveToThingEntity.class);

  @Mappings({
          @Mapping(target = "id", expression = "java(null)")
          })

  ThingEntity thingDtoSaveToThingEntity(ThingDtoSave thingDtoSave);

  @InheritInverseConfiguration
  ThingDtoSave thingEntityToThingDtoSave(ThingEntity thingEntity);
}
