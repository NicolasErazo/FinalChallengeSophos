package com.FinalChallenge.FinalChallenge.security;

import com.FinalChallenge.FinalChallenge.entity.User;
import com.FinalChallenge.FinalChallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class UserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("The user with email " + email + " does not exist"));
        return new UserDetailImplementation(user);
    }

}
