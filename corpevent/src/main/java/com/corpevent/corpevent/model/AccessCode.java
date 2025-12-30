package com.corpevent.corpevent.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_codes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "access_code_seq")
    @SequenceGenerator(name = "access_code_seq", sequenceName = "ACCESS_CODE_SEQ", allocationSize = 1)
    private Long id;


    @OneToOne
    @JoinColumn(name = "request_id", nullable = false, unique = true)
    private Request request;

    @Column(nullable = false, length = 50, unique = true)
    private String code;

    @Column(name = "generated_at", nullable = false)
    private LocalDateTime generatedAt;
}
