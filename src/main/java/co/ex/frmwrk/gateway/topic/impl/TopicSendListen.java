package co.ex.frmwrk.gateway.topic.impl;

import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.ports.bus.DtoSenderBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class TopicSendListen {

  private final Map<Class<?>, DtoSenderBus> cbMap;

  public void sendThingDto(ThingDto thingDto) {
    cbMap.get(thingDto.getClass()).perform(thingDto);
  }
}
