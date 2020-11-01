package com.junoyaya.jukebox.entities;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AuditableEntity {
  @JsonIgnore
  @CreatedBy
  private String createdBy;
  @JsonIgnore
  @LastModifiedBy
  private String lastModifiedBy;
  @JsonIgnore
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;
  @JsonIgnore
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;
  @JsonIgnore
  private Boolean archived;
}
