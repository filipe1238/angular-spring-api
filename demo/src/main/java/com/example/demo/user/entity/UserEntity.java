package com.example.demo.user.entity;

import com.example.demo.antiHero.entity.ParentEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends ParentEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO,
//            generator = "UUID")
//    @Column(nullable = false, updatable = false)
//    private UUID id;
    @Column(unique = true)
    private String email;
    private String mobileNumber;
    private byte[] storedHash;
    private byte[] storedSalt;
    public UserEntity(String email, String mobileNumber) {
        this.email = email;
        this.mobileNumber = mobileNumber;
    }
}