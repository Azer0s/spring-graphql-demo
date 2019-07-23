package at.simulevski.graphqldemo;

import at.simulevski.graphqldemo.domain.Item;
import at.simulevski.graphqldemo.domain.User;
import at.simulevski.graphqldemo.persistence.UserRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
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

    public User getUser(String id, String username) throws Exception {
        if (id == null && username != null){
            return userRepository.findByUsername(username).orElseThrow();
        } else if (id != null && username == null){
            return userRepository.findById(id).orElseThrow();
        } else{
            throw new Exception("Can't have both an id and a username argument for this search");
        }
    }

    public Item getItemFromUser(String userId, String itemId){
        return userRepository
                .findById(userId)
                .orElseThrow()
                .getItems()
                .stream()
                .filter(a -> a.getId().equals(itemId))
                .findFirst().orElseThrow();
    }
}
