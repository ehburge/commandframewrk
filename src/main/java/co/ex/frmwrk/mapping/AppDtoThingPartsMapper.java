package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import model.AppThingParts;
import org.mapstruct.Mapper;

@Mapper
public interface AppDtoThingPartsMapper {
    AppThingParts mapDtoToApp(ThingDtoParts dtoThingParts);
}
