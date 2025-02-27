package com.bank.service;

import com.bank.dto.OtpRequest;
import com.bank.dto.OtpResponse;
import com.bank.model.OtpVerification;
import com.bank.repository.OtpRepository;
import com.bank.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    private static final int OTP_EXPIRY_MINUTES = 5;
    private static final Random random = new Random();

    @Override
    public OtpResponse generateOtp(String email) {
        String otp = generateOtpCode();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);

        // Save or update OTP
        Optional<OtpVerification> existingOtp = otpRepository.findByEmail(email);
        OtpVerification otpVerification = existingOtp.orElse(new OtpVerification());
        otpVerification.setEmail(email);
        otpVerification.setOtp(otp);
        otpVerification.setOtpExpiry(expiryTime);
        otpRepository.save(otpVerification);

        // Send OTP via email
        emailService.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);

        return new OtpResponse("OTP sent successfully!");
    }

    @Override
    public OtpResponse verifyOtp(OtpRequest request) {
        Optional<OtpVerification> otpRecord = otpRepository.findByEmail(request.getEmail());

        if (otpRecord.isPresent()) {
            OtpVerification otpVerification = otpRecord.get();

            if (otpVerification.getOtpExpiry().isBefore(LocalDateTime.now())) {
                return new OtpResponse("OTP expired. Request a new one.");
            }

            if (!otpVerification.getOtp().equals(request.getOtp())) {
                return new OtpResponse("Invalid OTP. Please try again.");
            }

            otpRepository.delete(otpVerification); // Delete OTP after successful verification
            return new OtpResponse("OTP verified successfully!");
        }
        return new OtpResponse("No OTP found for this email. Request a new one.");
    }

    @Override
    public boolean sendOtp(String email) {
        String otp = generateOtpCode();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);

        OtpVerification otpVerification = new OtpVerification(email, otp, expiryTime);
        otpRepository.save(otpVerification);

        return emailService.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);
    }

    private String generateOtpCode() {
        return String.format("%06d", random.nextInt(999999));
    }
}
