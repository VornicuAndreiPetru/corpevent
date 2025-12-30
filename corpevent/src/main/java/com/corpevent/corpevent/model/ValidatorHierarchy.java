package com.corpevent.corpevent.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "validator_hierarchy")
@Getter
@Setter
@Builder
public class ValidatorHierarchy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "validator_hierarchy_seq")
    @SequenceGenerator(name = "validator_hierarchy_seq", sequenceName = "VALIDATOR_HIERARCHY_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exec_id", nullable = false)
    private User exec;


    @ManyToOne
    @JoinColumn(name = "sef_id", nullable = false)
    private User sef;

}
