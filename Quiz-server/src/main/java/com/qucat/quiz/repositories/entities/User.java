package com.qucat.quiz.repositories.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class User {

    private int id;
    private String firstName;
    private String secondName;
    private String login;
    private String mail;
    private String password;
    private String profile;
    private Date registrationDate;
    private int score;
    private UserAccountStatus status;
    private Role role;
    private List<Achievement> achievements;
}
