package com.corpevent.corpevent.dto.user;


import com.corpevent.corpevent.enums.RoleName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDto {

    @NotBlank(message = "Username-ul este obligatoriu")
    @Size(min = 3, max = 30, message = "Username-ul trebuie să aibă între 3 și 30 caractere")
    private String username;
    @NotBlank(message = "Parola este obligatorie")
    @Size(min = 6, message = "Parola trebuie să aibă minim 6 caractere")
    private String password;
    @NotBlank(message = "Prenumele este obligatoriu")
    private String firstname;
    @NotBlank(message = "Numele este obligatoriu")
    private String lastname;
    @NotBlank(message = "Email-ul este obligatoriu")
    @Email(message = "Email invalid")
    private String email;
    @NotNull(message = "Rolul este obligatoriu")
    private RoleName role;
}
