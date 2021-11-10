package co.ex.frmwrk.gateway.impl;

import co.ex.frmwrk.gateway.ThingDto;
import co.ex.frmwrk.gateway.ThingDtoMapDelegate;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class ThingDtoMapDelegateImpl implements ThingDtoMapDelegate {

  Map<Long, ThingDtoSave> thingDtoMap = new HashMap<>();

  @Override
  public ThingDtoSave get(Object key) {
    return thingDtoMap.get(key);
  }

  @Override
  public ThingDtoSave put(Long key, ThingDtoSave value) {
    return thingDtoMap.put(key, value);
  }

  @Override
  public Map<Long, ThingDtoSave> getThingDtoMap() {
    return thingDtoMap;
  }
}
