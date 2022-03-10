package co.ex.frmwrk.gateway.persist;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@ToString(includeFieldNames = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thing_entity", indexes = @Index(columnList = "thingNbr, dttm asc"))
public class ThingEntity {

  @Id
  @Column(name = "uuid", nullable = true, updatable = false, unique = false)
  @Type(type = "uuid-char")
  private UUID uuid;

  @Column(nullable = true, updatable = false, unique = false)
  private Long thingNbr;

  @CreationTimestamp private Timestamp dttm;

  private String content_type; // data class

  @Column(length = 2000)
  private String entity_content; // Thing json
}
