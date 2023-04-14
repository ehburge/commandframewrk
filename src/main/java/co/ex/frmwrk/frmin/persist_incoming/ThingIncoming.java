package co.ex.frmwrk.frmin.persist_incoming;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@ToString(includeFieldNames = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "thing_incoming", indexes = @Index(columnList = "thingNbr, dttm asc"))
public class ThingIncoming {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "uuid", nullable = false, updatable = false, unique = true)
  private String uuid;

  // IDENTITY uses underlying DB id generation, AUTO uses hibernate id generation
  @Column(nullable = false, updatable = false, unique = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "thing_incoming_seq")
  @SequenceGenerator(name = "thing_incoming_seq", allocationSize = 5)
  private Long thingNbr;

  @CreationTimestamp private Timestamp dttm;

  private String content_type; // data class

  @Column(length = 5000)
  private String entity_content; // Thing json
}
