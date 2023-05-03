package com.company.restaurant.Services;

import com.company.restaurant.Configuration.MyUserPrincipal;
import com.company.restaurant.DTO.UsernameAndPasswordDTO;
import com.company.restaurant.Models.Usr;
import com.company.restaurant.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersDetailService implements UserDetailsService {
    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private SessionRegistry sessionRegistry;

    public static final String KEY = "123";


    public static final String USER = "ROLE_USER";

    public UsersDetailService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usr user = usersRepository.findUsersByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }

    public Usr saveUser(Usr usr) {
        return usersRepository.save(usr);
    }

    public Usr createUser(String username) {
        Usr usr = new Usr();
        usr.setRoles(USER);
        usr.setActive(1);
        usr.setUsername(username);
        usr.setPassword(passwordEncoder.encode(usr.getUsername() + KEY));
        usr.setPermissions(USER);
        return saveUser(usr);
    }


    public UsernameAndPasswordDTO authWithoutPassword(String username) {
        UserDetails userDetails;
        try {
            userDetails = loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            Usr user = createUser(username);
            userDetails = loadUserByUsername(user.getUsername());
        }
        return new UsernameAndPasswordDTO(userDetails.getUsername(), userDetails.getUsername() + KEY);

    }

    public List<String> getUsersFromSessionRegistry() {
        return sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}