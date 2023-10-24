package co.ex.frmwrk.mapping;

import co.ex.app.model.AppParts;
import co.ex.frmwrk.gateway.impl.DtoParts;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AppDtoCommandPartsMapper {
  AppParts mapDtoToApp(DtoParts dtoCommandParts);
}
