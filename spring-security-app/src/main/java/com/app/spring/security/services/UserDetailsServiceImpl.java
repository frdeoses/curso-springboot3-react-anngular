package com.app.spring.security.services;

import com.app.spring.security.dto.AuthCreateUserRequest;
import com.app.spring.security.dto.AuthLoginRequest;
import com.app.spring.security.dto.AuthResponse;
import com.app.spring.security.persistence.entity.RoleEntity;
import com.app.spring.security.persistence.entity.UserEntity;
import com.app.spring.security.persistence.repository.RoleRepository;
import com.app.spring.security.persistence.repository.UserRepository;
import com.app.spring.security.utils.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(final UserRepository userRepository,
                                  final RoleRepository roleRepository,
                                  final JwtUtils jwtUtils,
                                  final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuraio " + username + " no existe!!"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionEntities().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(),
                userEntity.isAccountNoLocked(),
                authorities
        );
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "User loged succesful", token, true);

    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);

        if (Objects.isNull(userDetails))
            throw new BadCredentialsException("Invalid username or password");

        if (!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Invalid username or password");

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest createUser) {
        String username = createUser.username();
        String password = createUser.password();

        List<String> roles = createUser.roleRequest().roles();

        Set<RoleEntity> roleEntities = roleRepository.findRoleEntityByRoleIn(roles).stream()
                .collect(Collectors.toSet());

        if (roleEntities.isEmpty())
            throw new IllegalArgumentException("The roles specified does not exist..");

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(roleEntities)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity userCreate = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();

        userCreate.getRoles().forEach(roleEntity -> authoritiesList.add(new SimpleGrantedAuthority("ROLE_".concat(roleEntity.getRole().name()))));

        userCreate.getRoles().stream()
                .flatMap(role -> role.getPermissionEntities().stream())
                .forEach(permission -> authoritiesList.add(new SimpleGrantedAuthority(permission.getName())));


        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreate.getUsername(), userCreate.getPassword(), authoritiesList);

        String token = jwtUtils.createToken(authentication);

        return new AuthResponse(userCreate.getUsername(), "User  created successful ", token, true);

    }
}
