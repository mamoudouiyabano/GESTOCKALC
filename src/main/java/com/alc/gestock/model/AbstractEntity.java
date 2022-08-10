package com.alc.gestock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
//cette annnotation vas ecouter automatiquement cette classe pour assigner une valuer a creation date et modified date a chaque fois que ce necessaire
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(name= "creationDate", nullable = false, updatable = false)
     private Instant creationDate;

    @LastModifiedDate
    @Column (name= "lastModifiedDate")
    private Instant lastModifiedDate;

//    @PrePersist
//    void prePersist() {
//        creationDate = Instant.now();
//    }
//
//    @PreUpdate
//    void preUpdate() {
//        lastModifiedDate = Instant.now();
//    }


}
