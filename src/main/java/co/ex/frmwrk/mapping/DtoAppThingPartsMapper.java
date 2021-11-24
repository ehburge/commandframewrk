package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.persist.ThingPart;
import model.AppThingPart;
import model.DomThingPart;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface DtoAppThingPartsMapper {
    List<ThingPart> mapAppToDto(List<AppThingPart> appThingParts);
}
