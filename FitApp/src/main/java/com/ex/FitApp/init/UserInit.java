package com.ex.FitApp.init;

import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.entities.UserRoleEntity;
import com.ex.FitApp.models.entities.enums.BodyType;
import com.ex.FitApp.models.entities.enums.UserRole;
import com.ex.FitApp.repositories.UserRepository;
import com.ex.FitApp.repositories.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInit implements CommandLineRunner {
    
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInit(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.userRepository.count()==0){
            UserRoleEntity adminRole= new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole= new UserRoleEntity().setRole(UserRole.USER);
            
            this.userRoleRepository.save(adminRole);
            this.userRoleRepository.save(userRole);

            UserEntity admin= new UserEntity();
            admin.setFirstName("Ivan");
            admin.setLastName("Ivanov");
            admin.setBodyType(BodyType.ECTOMORPH);
            admin.setUsername("ivan");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setAge(12);
            admin.setEmail("a@afafaw");
            admin.setHeight(1.8);
            admin.setWeight(76);
            admin.getRoles().add(this.userRoleRepository.findByRole(UserRole.ADMIN));

            this.userRepository.save(admin);

            UserEntity user= new UserEntity();
            user.setFirstName("Dobromir");
            user.setLastName("Dobromir");
            user.setBodyType(BodyType.ECTOMORPH);
            user.setUsername("kiro");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setAge(12);
            user.setEmail("a@afa25325faw");
            user.setHeight(1.82);
            user.setWeight(74);
            user.getRoles().add(this.userRoleRepository.findByRole(UserRole.USER));

            this.userRepository.save(user);
        }
    }
}
