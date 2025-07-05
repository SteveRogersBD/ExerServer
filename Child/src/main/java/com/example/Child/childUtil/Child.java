package com.example.Child.childUtil;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String dateOfBirth;
    private int age;
    private String avatar;
    @ElementCollection
    private List<Long> scoreId;
    @ElementCollection
    private List<Long> sessionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate()
    {
        this.createdAt = LocalDateTime.now();
        String temp = dateOfBirth;
        String[] arr = temp.split("/"); //format mm/dd/yyyy
        LocalDate dob = LocalDate.of(Integer.parseInt(arr[2]),Integer.parseInt(arr[0]),Integer.parseInt(arr[1]));
        this.age = Period.between(dob,LocalDate.now()).getYears();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }


}
