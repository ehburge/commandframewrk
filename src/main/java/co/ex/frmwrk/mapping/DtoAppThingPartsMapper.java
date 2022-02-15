package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.impl.ThingDtoPart;
import com.ex.thing.model.AppThingPart;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface DtoAppThingPartsMapper {
  List<ThingDtoPart> mapAppToDto(List<AppThingPart> appThingParts);
}
