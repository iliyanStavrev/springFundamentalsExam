package com.example.exam.service;

import com.example.exam.model.dto.LoginDto;
import com.example.exam.model.dto.RegisterDto;
import com.example.exam.model.entity.Song;
import com.example.exam.model.entity.User;
import com.example.exam.repository.UserRepository;
import com.example.exam.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }


    public boolean register(RegisterDto registerDto) {

        User userByUsername = userRepository
                .findByUsername(registerDto.getUsername());
        if (userByUsername != null){
            return false;
        }

        User userByEmail = userRepository
                .findByEmail(registerDto.getEmail());
        if (userByEmail != null){
            return false;
        }

        userRepository.save(modelMapper
                .map(registerDto, User.class));

        return true;
    }

    public boolean login(LoginDto loginDto) {

        User user = userRepository
                .findByUsernameAndPassword(loginDto.getUsername(),
                        loginDto.getPassword())
                .orElse(null);

        if (user == null){
            return false;
        }

        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());

        return true;
    }

    public void logout() {

        currentUser.logout();
    }

    public User findByUsername(String username) {
       return userRepository
                .findByUsername(username);
    }
//    public void addSong(Song song){
//
//        userRepository
//                .findByUsername(currentUser.getUsername())
//                .getPlaylist()
//                .add(song);
//    }
}
