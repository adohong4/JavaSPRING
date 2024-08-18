package com.an.identity_service.service;

import com.an.identity_service.dto.request.UserCreationRequest;
import com.an.identity_service.dto.request.UserUpdateRequest;
import com.an.identity_service.dto.response.UserResponse;
import com.an.identity_service.entity.User;
import com.an.identity_service.exception.AppException;
import com.an.identity_service.exception.ErrorCode;
import com.an.identity_service.mapper.UserMapper;
import com.an.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request){
     if(userRepository.existsByUsername(request.getUsername()))
        throw new AppException(ErrorCode.USER_EXISTED);

     User user = userMapper.toUser(request);
     PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
     user.setPassword(passwordEncoder.encode(request.getPassword()));

     return userRepository.save(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not found"));

        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getUsers(){
        return  userRepository.findAll();
    }

    public UserResponse getUser(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not found"));
        return  userMapper.toUserResponse(user);
    }


}

