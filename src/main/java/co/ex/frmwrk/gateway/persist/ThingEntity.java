package co.ex.frmwrk.gateway.persist;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.java.UUIDJavaType;
import org.hibernate.type.descriptor.jdbc.UUIDJdbcType;

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
  @Column(name = "uuid", nullable = false, updatable = false, unique = true)
  // TODO
  //@Type(type = "uuid-char")
  private String uuid;

  // IDENTITY uses underlying DB id generation, AUTO uses hibernate id generation
  // SEQUENCE is not compatible between DB's
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false, unique = false)
  private Long thingNbr;

  @CreationTimestamp private Timestamp dttm;

  private String content_type; // data class

  @Column(length = 3000)
  private String entity_content; // Thing json
}
