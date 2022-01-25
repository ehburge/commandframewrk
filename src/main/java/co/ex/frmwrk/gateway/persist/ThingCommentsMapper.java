package co.ex.frmwrk.gateway.persist;

import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThingCommentsMapper {

  List<ThingComment> dto2Entity(List<String> comments);
  List<String> entity2Dto(List<ThingComment> comments);

  ThingComment dto2EntityStr(String s);

  String entity2DtoTC(ThingComment thingComment);
}
