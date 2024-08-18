package com.an.identity_service.dto.request;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

}
