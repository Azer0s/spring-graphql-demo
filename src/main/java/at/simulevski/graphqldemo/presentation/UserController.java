package at.simulevski.graphqldemo.presentation;

import at.simulevski.graphqldemo.domain.User;
import at.simulevski.graphqldemo.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{name}")
    public @ResponseBody User getUsers(@PathVariable("name") String userName){
        return userRepository.findByUsername(userName).orElseThrow();
    }

    @GetMapping("/user")
    public @ResponseBody List<User> getUsers(){
        return userRepository.getAll();
    }
}
