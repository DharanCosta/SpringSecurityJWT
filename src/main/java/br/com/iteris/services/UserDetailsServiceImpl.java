package br.com.iteris.services;

import br.com.iteris.domain.entities.*;
import br.com.iteris.domain.entities.User;
import br.com.iteris.exceptions.*;
import br.com.iteris.repositories.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long loadUserIdByUsername(String username) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent())
            throw AppException.of(AppError.USER_NOT_FOUND);

        return user.get().getId();
    }


    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(userName);
        if(!user.isPresent())
            throw AppException.of(AppError.USER_NOT_FOUND);
        UserDetailsImpl userDetails = new UserDetailsImpl(user.get());
        return new UserDetailsImpl();
    }
}
