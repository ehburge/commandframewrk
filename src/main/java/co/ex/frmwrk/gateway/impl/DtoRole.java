package co.ex.frmwrk.gateway.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class DtoRole implements Serializable {

  private final Long actorId;
  private final String groupId;
  private final String loyaltyCode;
}
