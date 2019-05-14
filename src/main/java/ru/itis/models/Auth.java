package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Entity
//@Table(name = "auth")
public class Auth {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;
//    @Column(name = "cookie_value")
    private String cookieValue;
//    @Column(name = "user_id")
    private Long userId;

}

