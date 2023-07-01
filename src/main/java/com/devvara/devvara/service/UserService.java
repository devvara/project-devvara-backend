package com.devvara.devvara.service;

import com.devvara.devvara.domain.Role;
import com.devvara.devvara.domain.User;
import com.devvara.devvara.repository.RoleRepository;
import com.devvara.devvara.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
    }

    @Transactional
    public User addUser(User user) {
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
        user.addRole(userRole.get());
        User saveUser = userRepository.save(user);
        return saveUser;
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(Long userId) { return userRepository.findById(userId); }

    @Transactional(readOnly = true)
    public Optional<User> getUser(String email) { return userRepository.findByEmail(email); }
}
