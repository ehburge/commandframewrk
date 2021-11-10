package co.ex.frmwrk.gateway;

import java.util.Map;

public interface ThingDtoMapDelegate {
  ThingDtoSave get(Object key);

  ThingDtoSave put(Long key, ThingDtoSave value);

  Map<Long, ThingDtoSave> getThingDtoMap();
}
