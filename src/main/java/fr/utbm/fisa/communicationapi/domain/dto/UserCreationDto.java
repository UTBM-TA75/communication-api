package fr.utbm.fisa.communicationapi.domain.dto;

import fr.utbm.fisa.communicationapi.infrastructure.entities.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationDto {

    @NotBlank(message = "username is mandatory")
    private String username;

    @Email
    @NotBlank(message = "email is mandatory")
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotNull(message = "isAdmin is mandatory")
    private Boolean isAdmin;

    @NotNull(message = "type is mandatory")
    private UserType type;
}
