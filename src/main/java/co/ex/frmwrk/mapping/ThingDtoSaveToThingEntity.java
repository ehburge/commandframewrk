package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import co.ex.frmwrk.gateway.persist.ThingPart;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.*;

@Mapper
public interface ThingDtoSaveToThingEntity {
  ThingDtoSaveToThingEntity INSTANCE = Mappers.getMapper(ThingDtoSaveToThingEntity.class);

  @Mappings({
          @Mapping(target = "id", expression = "java(null)"),
          @Mapping(target = "comments", expression = "java(new java.util.HashSet(java.util.Arrays.asList(\"Larry\", \"Moe\", \"Curly\")))")
          })

  ThingEntity thingDtoSaveToThingEntity(ThingDtoSave thingDtoSave);

  @AfterMapping
  default void aftMap(@MappingTarget ThingEntity.ThingEntityBuilder thingEntity) {
    Set<ThingPart> partSet = new HashSet<>();
    for(int i=0; i<5; i++) {
      partSet.add(new ThingPart(i*2, "part desc "+(i*2), new BigDecimal((i*2)+.97)));
    }
    thingEntity.partSet(partSet);
  }

  @InheritInverseConfiguration
  ThingDtoSave thingEntityToThingDtoSave(ThingEntity thingEntity);
}
