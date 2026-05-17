package com.example.umc10th.users.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @ResponseBody
    @GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> loginPage() {
        return ResponseEntity.ok("""
                <!doctype html>
                <html lang="ko">
                <head>
                    <meta charset="UTF-8">
                    <title>Login</title>
                </head>
                <body>
                    <form method="post" action="/login">
                        <label>
                            Email
                            <input type="email" name="email" required>
                        </label>
                        <label>
                            Password
                            <input type="password" name="password" required>
                        </label>
                        <button type="submit">Login</button>
                    </form>
                </body>
                </html>
                """);
    }
}
