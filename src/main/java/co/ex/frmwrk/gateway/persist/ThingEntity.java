package co.ex.frmwrk.gateway.persist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
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
