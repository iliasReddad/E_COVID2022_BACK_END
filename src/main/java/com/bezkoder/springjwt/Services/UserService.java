package com.bezkoder.springjwt.Services;

import com.bezkoder.springjwt.exception.CustomException;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    private final JwtUtils jwtTokenProvider;



    public boolean delete(String username , HttpServletRequest req) {
        boolean deleated = false;
        if (whoami(req).get().getUsername().equals(username)) {
            System.out.println("cannot delete this USER");
            return deleated;
        }
        deleated = true;
        userRepository.deleteByUsername(username);
        return deleated;
    }

    public boolean changeStatue(String username , HttpServletRequest req) {
        boolean change = false;
        if (whoami(req).get().getUsername().equals(username)) {
            System.out.println("cannot block this USER");
            return change;
        }
        change = true;
        User user = userRepository.findByUsername(username).orElse(null);
        if (user.getStatus().equals("pending")){
            user.setStatus("suspended");
        }
        else if (user.getStatus().equals("suspended")){
            user.setStatus("pending");
        }
        userRepository.save(user);
        return change;
    }



    public Optional<User> whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUserNameFromJwtToken(jwtTokenProvider.resolveToken(req)));
    }
    public User search(String username) {
        User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return appUser;
    }

    public List<User> getall() {
        return userRepository.findAll();
    }
}
