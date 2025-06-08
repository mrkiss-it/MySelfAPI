package com.kiss.myselfapi.entity;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document("users")
@Data
public class User {
    @Id
    private String id;
    private String email;
    private String name;
    private String picture;
    private String password;
    private String authType;
    private List<String> roles = new ArrayList<String>();
}