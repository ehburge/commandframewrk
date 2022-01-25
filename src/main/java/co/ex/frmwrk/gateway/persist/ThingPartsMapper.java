package co.ex.frmwrk.gateway.persist;

import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThingPartsMapper {

  List<ThingPart> dtoToEntity(List<ThingDtoPart> parts);

  @InheritInverseConfiguration
  List<ThingDtoPart> entityToDto(List<ThingPart> parts);

}
