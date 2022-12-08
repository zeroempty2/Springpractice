package com.sparta.homework.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {
    @CreatedDate
    private LocalDate createdAt;
}
