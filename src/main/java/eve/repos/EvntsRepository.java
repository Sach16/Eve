package eve.repos;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import eve.models.Evnts;
import eve.models.Person;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface EvntsRepository extends MongoRepository<Evnts, Serializable>{
	
	List<Evnts> findByFirstName(@Param("firstName") String firstName);
	List<Evnts> findByLastName(@Param("lastName") String lastName);
	List<Evnts> findByFirstNameLike(@Param("firstName") String firstName);
	List<Evnts> findAll();
	
}
