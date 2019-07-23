package at.simulevski.graphqldemo;

import at.simulevski.graphqldemo.domain.User;
import at.simulevski.graphqldemo.persistence.UserRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        var result = userRepository.getAll();
        return result;
    }
}
