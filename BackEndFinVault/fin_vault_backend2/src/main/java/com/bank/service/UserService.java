package com.bank.service;

import java.util.List;

import com.bank.dto.Signup;
import com.bank.model.Role;
import com.bank.model.User;

public interface UserService {
    Signup createUser(Signup user);
    String updateUser(User user);
    User getUserDetails(Long userId);
    List<User> getAllUsers();
    String deleteUserDetails(Long userId);
    boolean validateUser(String email, String password, Role role);
	User findByEmail(String email);
}
