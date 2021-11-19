package co.ex.frmwrk.gateway.persist;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Data
@Entity
public class EventEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long event_id;

  private String event_type; // created, updated
  private String entity_type; // Thing
  private Long entity_id; // ThingId
  private String event_data; // Thing

}
