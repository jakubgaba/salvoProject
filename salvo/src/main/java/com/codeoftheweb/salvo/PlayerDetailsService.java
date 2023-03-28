package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PlayerDetailsService implements UserDetailsService {


    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private Collection<SimpleGrantedAuthority> getAuthorities(Set<String> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByUserName(username);
        if (player != null) {
            return new CustomUserDetails(player.getUserName(), player.getUserId(), passwordEncoder.encode(player.getPassword()), getAuthorities(player.getAuthorities()));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public class CustomUserDetails extends User {
        public final Long playerId;
        public CustomUserDetails(String username, Long playerId, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
            this.playerId = playerId;
        }
    }

}
