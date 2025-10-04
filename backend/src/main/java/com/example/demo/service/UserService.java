package com.example.demo.service;

import com.example.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users createUser(Users users) {
        return userRepository.save(users);
    }

    public Users updateUser(Long id, Users updatedUsers) {
        Users existingUsers = userRepository.findById(id).orElse(null);
        if (existingUsers != null) {
            existingUsers.setUsername(updatedUsers.getUsername());
            existingUsers.setEmail(updatedUsers.getEmail());
            return userRepository.save(existingUsers);
        }
        return null;
    }
    
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public Users authenticateUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
