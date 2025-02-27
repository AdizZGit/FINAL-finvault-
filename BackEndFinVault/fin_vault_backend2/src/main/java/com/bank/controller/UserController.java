package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.bank.dto.Signup;
import com.bank.dto.LoginRequest;
import com.bank.dto.SigninResponse;
import com.bank.model.Employee;
import com.bank.model.Role;
import com.bank.model.User;
import com.bank.security.JwtUtils;
import com.bank.service.EmployeeService;
import com.bank.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authMgr;

	@Autowired
    private UserService userService;
    
    @Autowired
    private EmployeeService employeeService;

   
    //http://localhost:3000/signup

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        User user = userService.getUserDetails(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUserDetails() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUserDetails(@RequestBody Signup dto) {
//        String response = userService.createUser(user);
//        return ResponseEntity.ok(response);
    	System.out.println("in sign up " + dto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userService.createUser(dto));
    	
    }
    
    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        System.out.println("in sign in: " + request); // Debug log

        // Authenticate user
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication verifiedToken = authMgr.authenticate(token);

        // Fetch user details from the database
        User user = userService.findByEmail(request.getEmail());

        // Default response fields
        String jwtToken = jwtUtils.generateJwtToken(verifiedToken);
        String message = "Successful Auth!";
        String designation = null;

        // If the user is an employee, fetch designation from Employee entity
        if (user.getRole() == Role.EMPLOYEE) {
            Employee employee = employeeService.findByUser(user); // Fetch employee record
            if (employee != null) {
                designation = employee.getDesignation().name(); // Get the designation
            }
        }

        // Create response object
        SigninResponse resp = new SigninResponse(jwtToken, message, designation);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    
    /*@PostMapping("/signup")
	public ResponseEntity<?> userSignup(@RequestBody @Valid Signup dto) {
		System.out.println("in sign up " + dto);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(userService.userRegistration(dto));
	}*/

//    @PostMapping("/signin")
//    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
//        boolean isAuthenticated = userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getRole());
//        if (isAuthenticated) {
//            return ResponseEntity.ok("Login successful");
//        } else {
//            return ResponseEntity.status(401).body("Invalid email, password, or role");
//        }
//    }
    
    
//	@PostMapping("/signin")
//    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
////        boolean isAuthenticated = userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getRole());
////        if (isAuthenticated) {
////            return ResponseEntity.ok("Login successful");
////        } else {
////            return ResponseEntity.status(401).body("Invalid email, password, or role");
////        }
//		 System.out.println("in sign in" + request);//=> email n password : valid(P.L)
//		
//		UsernamePasswordAuthenticationToken token = 
//				new UsernamePasswordAuthenticationToken(request.getEmail(),
//				request.getPassword());		
//		Authentication verifiedToken = authMgr.authenticate(token);
//			
//		SigninResponse resp = new SigninResponse
//				(jwtUtils.generateJwtToken(verifiedToken), "Successful Auth!");
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
// 
//    }

//    @PutMapping
//    public ResponseEntity<String> updateUserDetails(@RequestBody User user) {
//        String response = userService.createUser(user);
//        return ResponseEntity.ok(response);
//    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteUserDetails(@PathVariable Long userId) {
//        String response = userService.deleteUserDetails(userId);
//        return ResponseEntity.ok(response);
//    }
}
