package com.ProftaakS34.Opinion.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private long id;
    private String username;
    private String password;
}
