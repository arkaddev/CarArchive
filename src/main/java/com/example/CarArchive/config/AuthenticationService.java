package com.example.CarArchive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public String getInfoAboutUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
