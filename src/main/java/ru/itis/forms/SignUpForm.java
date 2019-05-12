package ru.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SignUpForm {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String email;
    private String hashPassword;
}


