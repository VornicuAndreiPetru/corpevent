package com.corpevent.corpevent.model;


import com.corpevent.corpevent.enums.EventType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", sequenceName = "EVENT_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EventType type;

    @Column(columnDefinition = "CLOB")
    private String description;

    @FutureOrPresent
    @Column(nullable = false)
    private LocalDateTime eventDate;

    @Positive
    @Column(nullable = false)
    private Integer maxPlaces;

}
