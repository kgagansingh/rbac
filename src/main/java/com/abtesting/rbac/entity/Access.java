package com.abtesting.rbac.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "access", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Access {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "token", length = 1000)
    private String token;

    @Column(name = "version", length = 5)
    private String version;

    @Column(name = "status")
    private Integer status;

    @OneToOne(mappedBy = "access")
    private User user;
}

