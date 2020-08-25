package com.starterkit.springboot.brs.config.auth;


import java.security.Principal;
import java.util.UUID;

public interface TokenInfoService {

    UUID getIdFromPrincipal(Principal principal);
}
