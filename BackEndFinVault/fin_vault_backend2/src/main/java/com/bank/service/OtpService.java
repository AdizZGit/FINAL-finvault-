package com.bank.service;
import com.bank.dto.OtpRequest;
import com.bank.dto.OtpResponse;

public interface OtpService {
    OtpResponse generateOtp(String email);
    OtpResponse verifyOtp(OtpRequest request);
    boolean sendOtp(String email);
}