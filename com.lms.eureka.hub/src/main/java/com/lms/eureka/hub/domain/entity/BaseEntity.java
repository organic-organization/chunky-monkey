package com.lms.eureka.hub.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name="created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @Column(name="deleted_by")
    private String deletedBy;

    public void setDeleted(LocalDateTime deletedAt, String deletedBy) {
        this.deletedAt = deletedAt;
        this.deletedBy = deletedBy;
    }
}
