package at.simulevski.graphqldemo.persistence;

import at.simulevski.graphqldemo.domain.User;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Query("SELECT _class, username, items, META().id AS _ID, META().cas AS _CAS FROM `users` WHERE _class = \"at.simulevski.graphqldemo.domain.User\"")
    List<User> getAll();
}
