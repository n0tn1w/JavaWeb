package com.ex.FitApp.services;


import com.ex.FitApp.models.bindings.UserRegisterBindingModel;
import com.ex.FitApp.models.entities.UserEntity;
import com.ex.FitApp.models.views.UserAboutViewModel;

import java.util.List;

public interface UserService {

    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);

    boolean registerUser(UserRegisterBindingModel userRegisterBindingModel);

}
