package br.com.iteris.services;

import br.com.iteris.domain.entities.*;
import br.com.iteris.exceptions.*;
import br.com.iteris.repositories.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    private final UserService userService;
    private final WhislistRepository whislistRepository;

    public WishlistService(UserService userService, WhislistRepository whislistRepository) {
        this.userService = userService;
        this.whislistRepository = whislistRepository;
    }

    public List<String> getWishlistByUser(Long userId){
        Optional<User> user =userService.findById(userId);

        if(!user.isPresent())
            throw AppException.of(AppError.USER_NOT_FOUND);
        if(user.get().getRoles().equals("ROLE_NORMAL_USER"))
            throw AppException.of(AppError.INVALID_FOR_ADM_ROLE);

        return whislistRepository.findByUser_id(userId).stream().
                map(Wishlist::getDescription).collect(Collectors.toList());
    }

    public void save(String description, Long userId){
        whislistRepository.save(new Wishlist(description, userId));
    }

}
