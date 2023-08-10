package co.ex.frmwrk.mapping;

import co.ex.app.model.AppThingParts;
import co.ex.frmwrk.gateway.impl.ThingDtoParts;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AppDtoThingPartsMapper {
  AppThingParts mapDtoToApp(ThingDtoParts dtoThingParts);
}
