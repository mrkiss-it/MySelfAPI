package com.kiss.myselfapi.bo;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
