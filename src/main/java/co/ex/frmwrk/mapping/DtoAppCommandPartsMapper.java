package co.ex.frmwrk.mapping;

import co.ex.app.model.AppPart;
import co.ex.frmwrk.gateway.impl.DtoPart;
import java.util.List;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DtoAppCommandPartsMapper {
  List<DtoPart> mapAppToDto(List<AppPart> appCommandParts);
}
