package co.ex.frmwrk.gateway.persist.impl;

import co.ex.app.model.JsonMapper;
import co.ex.frmwrk.gateway.impl.DtoCommandSave;
import co.ex.frmwrk.gateway.persist.CommandEntity;
import co.ex.frmwrk.gateway.persist.CommandEntityRepository;
import co.ex.frmwrk.mapping.DtoCommandSave_CommandEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersistCommandDto {
  Logger LOGGER = LoggerFactory.getLogger(PersistCommandDto.class);

  private final DtoCommandSave_CommandEntity commandDtoSaveCommandEntity;
  private final CommandEntityRepository commandRepository;

  public CommandEntity persist(DtoCommandSave dtoCommandSave) {
    LOGGER.debug(
        "PersistCommandDto.persist()".concat(System.lineSeparator()),
        JsonMapper.toJson(dtoCommandSave));

    CommandEntity commandEntity = commandDtoSaveCommandEntity.commandDtoSaveToCommandEntity(dtoCommandSave);

    return commandRepository.save(commandEntity);
  }
}
