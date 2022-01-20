package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoComments;
import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import co.ex.frmwrk.gateway.impl.ThingDtoSave;
import co.ex.frmwrk.gateway.persist.ThingComment;
import co.ex.frmwrk.gateway.persist.ThingEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper // (componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ThingDtoSaveToThingEntity {
  // ThingDtoSaveToThingEntity INSTANCE = Mappers.getMapper(ThingDtoSaveToThingEntity.class);

  @Mappings({
    @Mapping(target = "id", expression = "java(null)"),
    @Mapping(target = "comments", qualifiedByName = "DtoCommentsToEntity"),
    @Mapping(target = "parts", qualifiedByName = "DtoPartsToEntity")
  })
  ThingEntity thingDtoSaveToThingEntity(ThingDtoSave thingDtoSave);

  @InheritInverseConfiguration
  ThingDtoSave thingEntityToThingDtoSave(ThingEntity thingEntity);

  @Named("DtoCommentsToEntity")
  default List<ThingComment> commentsMethod(ThingDtoComments comments) {
    List<ThingComment> thingComments = new ArrayList<>();
    List<String> commLst = comments.getComments();
    for (String commStr : commLst) {
      thingComments.add(new ThingComment(commStr));
    }
    return thingComments;
  }

  @Named("DtoPartsToEntity")
  default List<ThingDtoPart> commentsMethod(ThingDtoParts dtoParts) {
    List<ThingDtoPart> thingParts = new ArrayList<>(dtoParts.getParts());

    return thingParts;
  }
}
