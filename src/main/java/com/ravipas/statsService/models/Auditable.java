package com.ravipas.statsService.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@EqualsAndHashCode
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    //I will keep the very basic data for auditing
    @Column(name = "created_date", updatable = false)
    @Temporal(TIMESTAMP)
    @CreatedDate
    protected Date creationDate;


    @Column(name = "lastMod_date")
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;

}