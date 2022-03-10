package co.ex.frmwrk.mapping;

import co.ex.app.model.AppThingParts;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import org.mapstruct.Mapper;

@Mapper
public interface AppDtoThingPartsMapper {
  AppThingParts mapDtoToApp(ThingDtoParts dtoThingParts);
}
