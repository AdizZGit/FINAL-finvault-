package com.bank.service;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.dto.Signup;
import com.bank.model.Role;
import com.bank.model.User;
import com.bank.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
  //dep : dao layer i/f
  		@Autowired
  		private UserRepository userDao;
  		//dep
  		@Autowired
  		private ModelMapper mapper;
  		//dep 
  		@Autowired
  		private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public String createUser(User user) {
//        userRepository.save(user);
//        return "Created Successfully";
//    }
    
    @Override
	public Signup createUser(Signup user) {
		//dto --> entity
		User user1=mapper.map(user, User.class);
		if(userDao.existsByEmail(user1.getEmail()))
			throw new IllegalArgumentException("Email already exists !!!");
		
		user1.setPassword(encoder.encode(user1.getPassword()));//pwd : encrypted using SHA
		return mapper.map(userDao.save(user1), Signup.class);
	}
    

    @Override
    public String updateUser(User user) {
        userRepository.save(user);
        return "Updated Successfully";
    }

    @Override
    public User getUserDetails(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUserDetails(Long userId) {
        userRepository.deleteById(userId);
        return "Deleted Successfully";
    }

    @Override
    public boolean validateUser(String email, String password, Role role) {
        Optional<User> userOpt = userRepository.findByEmail(email); 
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPassword().equals(password) && user.getRole().equals(role);
        }
        return false;
    }

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}
}
