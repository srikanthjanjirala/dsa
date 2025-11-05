package com.dsa.practice.service;

import com.dsa.practice.exception.ResourceNotFoundException;
import com.dsa.practice.model.User;
import com.dsa.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser( User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found id with "+ id));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll().stream()
//                .sorted(Comparator.comparing(User::getCity).reversed())
                .sorted(Comparator.comparing(User::getCity))
                .toList(); // thread safe // faster // java 16 + // immutable(not changable)
//                .collect(Collectors.toList()); // not thread safe // slower // java 8 // mutable(changable)
//                .collect(Collectors.toSet()); // immutable // java 8
    }

    public User updateUser(Long id, User updatedUser){
        return userRepository.findById(id).map(user -> {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setCity(updatedUser.getCity());
            user.setDepartment(updatedUser.getDepartment());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee Not found id "+ id));
    }

    public void deleteUser(Long id){
         User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+ id));
         userRepository.delete(user);
    }

//    first filter then sort
    public List<User> getUserByCity(String city){
        List<User> filteredByUser = userRepository.findAll()
                .stream()
                .filter((u -> u.getCity().toLowerCase().contains(city.toLowerCase())))
//                .filter(u -> u.getCity().toLowerCase().endsWith(city.toLowerCase()))
//                .filter(u -> u.getCity().toLowerCase().startsWith((city.toLowerCase())))
//                .filter(u -> u.getCity().toLowerCase().equals(city.toLowerCase()))
//                .sorted(Comparator.comparing((User u) -> u.getCity().toLowerCase(), Comparator.reverseOrder()))
//                .sorted(Comparator.comparing((User u) -> u.getCity().toLowerCase()))
//                .sorted(Comparator.comparing(User::getFirstName).reversed())
                .collect(Collectors.toList());

        if (filteredByUser.isEmpty()){
            throw new ResourceNotFoundException("No users found in city " + city);
        }
        return filteredByUser;
    }
}
