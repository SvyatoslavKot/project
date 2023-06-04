package ru.personal_coach.project.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    String mail;
    String password;
}
