package br.com.iteris.controllers;

import br.com.iteris.domain.dtos.*;
import br.com.iteris.domain.entities.User;
import br.com.iteris.services.UserService;
import org.springframework.security.access.annotation.*;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.*;
import javax.validation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
//@RolesAllowed("ROLE_ADMIN_USER")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    @Secured("ROLE_ADMIN_USER")
    public List<UserResponse> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/new")
//    @Secured("ROLE_ADMIN_USER")
    public UserResponse createNewUser(@RequestBody @Valid UserRequest request,  @AuthenticationPrincipal AuthenticatedUserDetails authenticatedUser){
        return userService.createUser(request, authenticatedUser.id());
    }
}
