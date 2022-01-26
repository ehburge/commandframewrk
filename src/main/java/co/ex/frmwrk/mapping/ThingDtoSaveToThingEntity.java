package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingCommentsMapper;
import co.ex.frmwrk.gateway.persist.ThingPartsMapper;
import co.ex.frmwrk.gateway.persist.ThingComment;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ThingPartsMapper.class, ThingCommentsMapper.class})
public interface ThingDtoSaveToThingEntity {

  @Mappings({
    @Mapping(target = "id", expression = "java(null)"),
    @Mapping(target = "comments", source = "comments.comments"),
    @Mapping(target = "parts", source = "parts.parts")
  })
  ThingEntity thingDtoSaveToThingEntity(ThingDtoSave thingDtoSave);

  @InheritInverseConfiguration
  @Mappings({
    @Mapping(target = "comments.comments", source = "comments"),
    @Mapping(target = "parts.parts", source = "parts")
  })
  ThingDtoSave thingEntityToThingDtoSave(ThingEntity thingEntity);
}
