package br.com.iteris.domain.dtos;

import br.com.iteris.domain.entities.*;
import lombok.*;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
public class UserRequest {

    @NotEmpty(message = "Name can't be empty")
    @Size(max = 50, message = "Name can't have more than 50 characters")
    private String name;

    @NotEmpty(message = "Username can't be empty")
    @Size(max = 50, message = "Username can't have more than 50 characters")
    private String username;

    @NotEmpty(message = "Password can't be empty")
    @Size( min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}\\Q[\\Q]:;<>,.?\\E/~_+-=|]).{8,100}$",
            message = "Password must have uppercase, lowecase, number and a special character" )
    private String password;

    @NotEmpty(message = "Role can't be empty")
    private List<Role> roles;
}
