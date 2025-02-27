package com.bank.controller;

import com.bank.dto.OtpRequest;
import com.bank.dto.OtpResponse;
import com.bank.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@CrossOrigin(origins = "http://localhost:3000") // Allows requests from frontend
public class OtpController {

    @Autowired
    private OtpService otpService;

  @PostMapping("/generate")
   // @PostMapping("/signup")
    public ResponseEntity<OtpResponse> generateOtp(@RequestBody OtpRequest request) {
        return ResponseEntity.ok(otpService.generateOtp(request.getEmail()));
    }

    @PostMapping("/verify")
    public ResponseEntity<OtpResponse> verifyOtp(@RequestBody OtpRequest request) {
        return ResponseEntity.ok(otpService.verifyOtp(request));
    }

    @PostMapping("/send")
    public ResponseEntity<OtpResponse> sendOtp(@RequestBody OtpRequest request) {
        boolean isSent = otpService.sendOtp(request.getEmail());

        if (isSent) {
            return ResponseEntity.ok(new OtpResponse("OTP sent successfully!"));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new OtpResponse("Failed to send OTP"));
        }
    }
}
