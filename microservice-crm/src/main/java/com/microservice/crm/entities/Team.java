package com.microservice.crm.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Guardamos el código numérico en BD, por eso no usamos @Enumerated
    @Column(name = "status")
    private Integer statusCode;

    @OneToMany(mappedBy = "team")
    private List<Member> members;

    @OneToOne
    @JoinColumn(name = "coordinator_member_id")
    private Member coordinator;


    public TeamStatus getStatus() {
        return TeamStatus.fromCode(this.statusCode);
    }

    public void setStatus(TeamStatus status) {
        this.statusCode = status.getCode();
    }
}
