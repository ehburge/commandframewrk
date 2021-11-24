package co.ex.frmwrk.gateway.persist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Clob;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EventEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long event_id;

  private String event_type; // created, updated
  private String entity_type; // Thing
  private Long entity_id; // ThingId
  @Lob
  private String event_data; // Thing
}
