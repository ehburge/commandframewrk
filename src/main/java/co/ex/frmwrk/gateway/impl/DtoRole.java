package co.ex.frmwrk.gateway.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DtoRole {

  private final Long actorId;
  private final String groupId;
  private final String loyaltyCode;
}
