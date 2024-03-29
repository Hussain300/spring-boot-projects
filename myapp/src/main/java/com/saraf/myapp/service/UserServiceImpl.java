package com.saraf.myapp.service;

import com.saraf.myapp.entity.User;
import com.saraf.myapp.exception.EntityNotFoundException;
import com.saraf.myapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(Long id){
        Optional<User> user = userRepository.findById(id);
        return unwrapUser(user, id);
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return unwrapUser(user, 404l);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    static User unwrapUser(Optional<User> entity, Long id){
        if(entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, User.class);
    }
}
