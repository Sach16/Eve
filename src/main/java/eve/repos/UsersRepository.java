package eve.repos;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import eve.models.Evnts;
import eve.models.Users;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UsersRepository extends MongoRepository<Users, Serializable>{

	Users findByToken(@Param("token") String token);
	List<Users> findAll();
}
