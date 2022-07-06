package com.example.exam.service;

import com.example.exam.model.dto.AddDto;
import com.example.exam.model.entity.Song;
import com.example.exam.model.entity.User;
import com.example.exam.model.enums.StyleNameEnum;
import com.example.exam.repository.SongRepository;
import com.example.exam.repository.UserRepository;
import com.example.exam.user.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final CurrentUser currentUser;
    private final StyleService styleService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    public SongService(SongRepository songRepository, CurrentUser currentUser, StyleService styleService, UserService userService, ModelMapper modelMapper, UserRepository userRepository) {
        this.songRepository = songRepository;
        this.currentUser = currentUser;
        this.styleService = styleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public boolean addSong(AddDto addDto) {

        Song songByPerformer = songRepository
                .findByPerformer(addDto.getPerformer());

        if (songByPerformer != null){
            return false;
        }
        Song song = modelMapper
                .map(addDto, Song.class);
        song.setStyle(styleService
                .findByName(addDto.getStyle()));
        User user = userService
                .findByUsername(currentUser.getUsername());

        user.getPlaylist().add(song);

        songRepository.save(song);

        return true;

    }


    public List<Song> getMyPlaylist() {

        User user = userService
                .findByUsername(currentUser.getUsername());

        return user.getPlaylist();
    }

    public List<Song> getAllPop() {

        return songRepository
                .findAll()
                .stream()
                .filter(s -> s.getStyle().getName().equals(StyleNameEnum.POP))
                .toList();
    }

    public List<Song> getAllRocks() {
        return songRepository
                .findAll()
                .stream()
                .filter(s -> s.getStyle().getName().equals(StyleNameEnum.ROCK))
                .toList();
    }

    public List<Song> getAllJazz() {
        return songRepository
                .findAll()
                .stream()
                .filter(s -> s.getStyle().getName().equals(StyleNameEnum.JAZZ))
                .toList();
    }

    public void addToMyPlaylist(Long id) {
        User user = userService
                .findByUsername(currentUser.getUsername());

        Song song = songRepository
                .findById(id)
                .orElse(null);

        List<Song> myPlaylist = user.getPlaylist();
        myPlaylist.add(song);
        user.setPlaylist(myPlaylist);
        userRepository.save(user);

    }

    public void deleteMyPlaylist() {

        User user = userService
                .findByUsername(currentUser.getUsername());

        List<Song> playList = user.getPlaylist();
        playList.clear();
        user.setPlaylist(playList);
        userRepository.save(user);

    }

    public Integer getTotal() {

        return userService
                .findByUsername(currentUser.getUsername())
                .getPlaylist()
                .stream()
                .map(song -> song.getDuration())
                .reduce(Integer::sum)
                .orElse(0);
    }
}
