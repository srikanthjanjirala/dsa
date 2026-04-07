package com.dsa.practice.service;

import com.dsa.practice.enums.RoleName;
import com.dsa.practice.exception.ResourceNotFoundException;
import com.dsa.practice.model.Course;
import com.dsa.practice.model.Profile;
import com.dsa.practice.model.Role;
import com.dsa.practice.model.User;
import com.dsa.practice.repository.CourseRepository;
import com.dsa.practice.repository.ProfileRepository;
import com.dsa.practice.repository.RoleRepository;
import com.dsa.practice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;
    private final CourseRepository courseRepository;

    public User saveUser( User user){
        Role role = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new ResourceNotFoundException("Role Not Found"));
        user.setRole(role);
        User savedUser = userRepository.save(user);

        // create profile and link both sides
        Profile profile = new Profile();
        profile.setAddress("Mumbai");
        profile.setPhone("1234567");
        profile.setUser(user);       // profile -> user
        user.setProfile(profile);    // user -> profile

        // save user (cascade will save profile automatically)
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

    public Optional<User> getUserByName(String email){
        return userRepository.getUserByEmail(email);
    }

    public User assignCourses(Long userId, Set<Long> courseIds){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        Set<Course> courses = courseIds.stream()
                .map(id -> courseRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Course not found: " + id)))
                .collect(Collectors.toSet());

        System.out.println("courses");
        System.out.println(courses);
        user.setCourses(courses);
        return userRepository.save(user);
    }
}
