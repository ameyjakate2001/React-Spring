package com.amey.jakate.security;

import com.amey.jakate.models.UserEntity;
import com.amey.jakate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Email Not Found"));
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String[] roles = user.getAdmin() ? new String[]{"ADMIN"} : new String[]{"USER"};
       return new User( user.getUsername(),
               user.getPassword(),
               AuthorityUtils.createAuthorityList(roles));
    }

}