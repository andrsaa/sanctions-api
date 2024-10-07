package ee.asaarep.sanctions;

import ee.asaarep.sanctions.domain.User;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Getter
@SuperBuilder(toBuilder = true)
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

  @CreatedDate
  @Column(name = "created", nullable = false)
  protected OffsetDateTime created;

  @LastModifiedDate
  @Column(name = "modified", nullable = false)
  protected OffsetDateTime modified;

  @CreatedBy
  @Type(JsonBinaryType.class)
  @Column(name = "creator", nullable = false)
  protected User creator;

  @LastModifiedBy
  @Type(JsonBinaryType.class)
  @Column(name = "modifier", nullable = false)
  protected User modifier;
}
