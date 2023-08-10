package co.ex.frmwrk.mapping;

import co.ex.app.model.AppThingPart;
import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DtoAppThingPartsMapper {
  List<ThingDtoPart> mapAppToDto(List<AppThingPart> appThingParts);
}
