package com.example.bromessenger.security.xxs;

import com.example.bromessenger.web.request.auth.SignUpRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssValidatorSignUp {
    public static boolean checkForXss(SignUpRequest signUpRequest) {
        String[] xssPatterns = {
                "<script\\b[^<]*(?:(?!<\\/script>)<[^<]*)*<\\/script>",
                "onerror\\s*=\\s*[\"'][^\"']*['\"]",
                "onload\\s*=\\s*[\"'][^\"']*['\"]"
        };

        for (String pattern : xssPatterns) {
            Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher usernameMatcher = compiledPattern.matcher(signUpRequest.getUsername());
            Matcher emailMatcher = compiledPattern.matcher(signUpRequest.getEmail());

            if (usernameMatcher.find() || emailMatcher.find()) {
                return true;
            }
        }
        return false;
    }
}