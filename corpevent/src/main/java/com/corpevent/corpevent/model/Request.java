package com.corpevent.corpevent.model;


import com.corpevent.corpevent.enums.ClientStatus;
import com.corpevent.corpevent.enums.DecisionType;
import com.corpevent.corpevent.enums.RequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq")
    @SequenceGenerator(name = "request_seq", sequenceName = "REQUEST_SEQ", allocationSize = 1)
    private Long id;


    @NotBlank
    @Column(name = "requester_name", nullable = false, length = 100)
    private String requesterName;

    @Column(name = "company", length = 100)
    private String company;

    @Column(length = 20)
    private String phone;

    @Email
    @Column(length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RequestStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "validator_exec_id")
    private User validatorExec;

    @ManyToOne
    @JoinColumn(name = "validator_sef_id")
    private User validatorSef;

    @Column(name = "taken_at")
    private LocalDateTime takenAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "preliminary_decision", length = 10)
    private DecisionType preliminaryDecision;

    @Column(name = "prelimiary_comment", columnDefinition = "CLOB")
    private String preliminaryComment;

    @Enumerated(EnumType.STRING)
    @Column(name = "final_decision", length = 10)
    private DecisionType finalDecision;

    @Column(name = "final_comment", columnDefinition = "CLOB")
    private String finalComment;

    @Column(name = "final_decision_at")
    private LocalDateTime finalDecisionAt;

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL)
    private AccessCode accessCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_status", nullable = false, length = 20)
    private ClientStatus clientStatus;



}
