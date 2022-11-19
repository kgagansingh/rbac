package com.abtesting.rbac.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.engine.spi.PersistentAttributeInterceptor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "user", schema = "public")
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Transient
    private PersistentAttributeInterceptor interceptor;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "password", length = 64)
    private String password;

    @Column(name = "first_nm")
    private String firstNm;

    @Column(name = "last_nm")
    private String lastNm;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_status")
    private Integer userStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private Access access;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public String getTokenFromAccess() {
        return this.access.getToken();
    }

    public String getVersionFromAccess() {
        return this.access.getVersion();
    }

    public Integer getStatusFromAccess() {
        return this.access.getStatus();
    }

}