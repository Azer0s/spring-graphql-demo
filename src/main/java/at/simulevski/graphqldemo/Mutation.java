package at.simulevski.graphqldemo;

import at.simulevski.graphqldemo.domain.Item;
import at.simulevski.graphqldemo.domain.User;
import at.simulevski.graphqldemo.persistence.UserRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String username){
        var user = User
                .builder()
                .id(UUID.randomUUID().toString())
                .username(username)
                .build();

        userRepository.save(user);

        return user;
    }

    public Item createItemForUser(String userId, String name, String description){
        var user = userRepository.findById(userId).orElseThrow();
        var items = user.getItems();

        var item = Item
                .builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .description(description)
                .build();

        items.add(item);
        user.setItems(items);

        userRepository.save(user);

        return item;
    }
}
