package co.ex.frmwrk.mapping;

import co.ex.frmwrk.gateway.persist.ThingPart;
import model.AppThingPart;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AppDtoThingPartsMapper {
    List<AppThingPart> mapDtoToApp(List<ThingPart> thingParts);
}
