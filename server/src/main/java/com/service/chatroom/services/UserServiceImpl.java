package com.service.chatroom.services;

import com.service.chatroom.entity.Group;
import com.service.chatroom.entity.User;
import com.service.chatroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService,UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = repository.findByUsername(username);

        if(user.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }else{
            List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
        }

    }

    @Override
    public boolean createUser(User user) {

        try{
            repository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {

        try{
            repository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteUserByUsername(String username) {
        try{
            repository.deleteUserByUsername(username);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Optional<User> getUserByUsername(String username) {

        return repository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<User> getOnlineUsers() {
        return null;
    }

    @Override
    public List<Group> getUserGroupByUsername(String username) {
        return null;
    }

    @Override
    public boolean isOnlineByUsername(String username) {
        return false;
    }


}
