package br.com.iteris.controllers;

import br.com.iteris.component.*;
import br.com.iteris.domain.dtos.*;
import br.com.iteris.domain.entities.*;
import br.com.iteris.services.*;
import io.jsonwebtoken.*;
import org.springframework.security.access.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.*;
import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    private final UserDetailsServiceImpl userDetailsService;


    public WishlistController(WishlistService wishlistService, UserDetailsServiceImpl userDetailsService) {
        this.wishlistService = wishlistService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    @Secured("ROLE_NORMAL_USER")
    public List<String> getWishlist(@AuthenticationPrincipal AuthenticatedUserDetails authenticatedUser){
        return this.wishlistService.getWishlistByUser(authenticatedUser.id());
    }

    @PostMapping
    @Secured("ROLE_NORMAL_USER")
    public void addWishlistItem(@RequestBody @Valid WishlistItemRequest wishlistItemRequest, @AuthenticationPrincipal AuthenticatedUserDetails authenticatedUser){
        this.wishlistService.save(wishlistItemRequest.description(), authenticatedUser.id());
    }
}
