package com.starterkit.springboot.brs.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.starterkit.springboot.brs.dto.UsersByRoleDTO;
import com.starterkit.springboot.brs.models.FirebaseUser;
import com.starterkit.springboot.brs.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Optional<User> findByUsername(String username);

    UserDetails register(FirebaseUser firebaseUser);

    List<User> findByValidated(boolean b);

    User activate(User user);

    List<User> findAll();

    User findById(UUID uuid);

    User save(User user);

    List<UsersByRoleDTO> getUsersByRole(String roleName);

    void updateProfilePicture(String pictureUrl, UUID userId);
}
