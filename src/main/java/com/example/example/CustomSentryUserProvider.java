package com.example.example;

import io.sentry.protocol.User;
import io.sentry.spring.SentryUserProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomSentryUserProvider implements SentryUserProvider {

    @Override
    public User provideUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return init(authentication);
    }

    private User init(Authentication authentication) {
        User user = new User();

        user.setUsername(authentication.getName());
        user.setEmail(authentication.getName() + "@gmail.com");
        user.setOthers(Collections.singletonMap("role", "admin"));

        Object details = authentication.getDetails();
        if (details instanceof WebAuthenticationDetails) {
            user.setIpAddress(((WebAuthenticationDetails) details).getRemoteAddress());
        }

        return user;
    }

}
