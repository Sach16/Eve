package eve.repos;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import eve.models.Evnts;
import eve.models.User;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UsersRepository extends MongoRepository<User, Serializable>{

	User findByToken(@Param("token") String token);
	User findByPhone(@Param("phone") String phone);
	List<User> findAll();
}
