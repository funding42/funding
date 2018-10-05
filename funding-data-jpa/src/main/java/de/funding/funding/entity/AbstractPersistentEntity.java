package de.funding.funding.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractPersistentEntity {

  private UUID id;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private LocalDateTime deletedAt;

  @Id
  @Column(name = "id", nullable = false, unique = true)
  public UUID getId() {
    return id;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  @Column(name = "created_at")
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(final LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @PrePersist
  void onCreate() {
    setCreatedAt(LocalDateTime.now());
  }

  @Column(name = "updated_at")
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(final LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @PreUpdate
  void onUpdate() {
    setUpdatedAt(LocalDateTime.now());
  }

  @Column(name = "deleted_at")
  public LocalDateTime getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(final LocalDateTime deletedAt) {
    this.deletedAt = deletedAt;
  }

  @PreRemove
  void onRemove() {
    setDeletedAt(LocalDateTime.now());
  }
}
