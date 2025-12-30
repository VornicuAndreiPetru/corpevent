package com.corpevent.corpevent.dto.event;


import com.corpevent.corpevent.enums.EventType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventCreateDto {
    @NotBlank(message = "Numele evenimentului este obligatoriu")
    @Size(min = 3, max = 100, message = "Numele trebuie să aibă între 3 și 100 caractere")
    private String name;
    @NotNull(message = "Tipul evenimentului este obligatoriu")
    private EventType type;
    @Size(max = 500, message = "Descrierea poate avea maxim 500 caractere")
    private String description;
    @NotNull(message = "Data evenimentului este obligatorie")
    @Future(message = "Data evenimentului trebuie să fie în viitor")
    private LocalDateTime eventDate;
    @NotNull(message = "Numărul de locuri este obligatoriu")
    @Min(value = 1, message = "Trebuie să existe cel puțin 1 loc")
    @Max(value = 10000, message = "Numărul maxim de locuri este 10.000")
    private Integer maxPlaces;
}
