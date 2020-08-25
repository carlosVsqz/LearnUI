package com.starterkit.springboot.brs.config.auth;


public interface FirebaseService {

    TokenInfo parseToken(String idToken);

}
