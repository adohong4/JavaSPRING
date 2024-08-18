package com.an.identity_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
