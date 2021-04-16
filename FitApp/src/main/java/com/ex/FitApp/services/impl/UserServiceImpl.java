package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.bindings.UserRegisterBindingModel;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.enums.UserRole;
import com.ex.FitApp.models.views.UserAboutViewModel;
import com.ex.FitApp.repositories.UserRepository;
import com.ex.FitApp.repositories.UserRoleRepository;
import com.ex.FitApp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    private final DBUserService dbUserService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, DBUserService dbUserService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.dbUserService = dbUserService;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository
                .findByUsername(username).orElse(null);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository
                .findByEmail(email).orElse(null);
    }

    @Override
    public boolean registerUser(UserRegisterBindingModel registerModel) {
        UserEntity user = this.modelMapper.map(registerModel, UserEntity.class);
        user.setPassword(this.passwordEncoder.encode(registerModel.getPassword()));
        user.addRole(userRoleRepository.findByRole(UserRole.USER));
        this.userRepository.saveAndFlush(user);

        UserDetails principal = dbUserService.loadUserByUsername(user.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }



}
