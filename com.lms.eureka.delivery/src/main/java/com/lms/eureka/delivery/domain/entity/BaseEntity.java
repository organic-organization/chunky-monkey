package com.lms.eureka.delivery.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name="created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @Column(name="deleted_by")
    private String deletedBy;

    public void setDeletedBy(String username) {
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = username;
    }

    public void setCreatedBy(String username) {
        this.createdBy = username;
        this.updatedBy = username;
    }

}
