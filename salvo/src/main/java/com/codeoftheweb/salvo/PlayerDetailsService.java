package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerDetailsService implements UserDetailsService {


    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByUserName(username);
        if (player == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("Found player with username: " + player.getUserName() + " and password: " + player.getPassword());
        return User.builder()
                .username(player.getUserName())
                .password(passwordEncoder.encode(player.getPassword()))
                .roles("USER")
                .build();
    }
}
