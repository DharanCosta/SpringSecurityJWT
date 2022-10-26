package br.com.iteris.services;

import antlr.*;
import br.com.iteris.domain.dtos.*;
import br.com.iteris.domain.entities.Role;
import br.com.iteris.domain.entities.User;
import br.com.iteris.exceptions.*;
import br.com.iteris.repositories.UserRepository;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request, Long id){
        List<Role> userRole = userRepository.findById(id).get().getRoles();
        for (Role role : userRole) {
            if(role.getName().equals("ROLE_NORMAL_USER"))
                throw AppException.of(AppError.INVALID_FOR_NORMAL_ROLE);
        }

        List<Role> roles = request.getRoles();
        for (Role role : roles) {
            if(role.getId()==1) role.setName("ROLE_NORMAL_USER");
            else if(role.getId()==2) role.setName("ROLE_ADMIN_USE");
            else AppException.of(AppError.INVALID_ROLE_ID);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedString = encoder.encode(request.getPassword());
        User user = new User(request.getName(), request.getUsername(), encodedString, roles);
        userRepository.save(user);
       return new UserResponse(user);
    }

    public List<UserResponse> getAllUsers(){
        List<User> all = userRepository.findAll();
        return all.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {return  userRepository.findById(id);}


}
