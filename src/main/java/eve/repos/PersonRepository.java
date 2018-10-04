package eve.repos;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import eve.models.Person;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String>{
	
	List<Person> findByLastName(@Param("name") String name);
	Person findByFirstName(@Param("firstName") String firstName);
	Person findByFirstNameIgnoreCase(@Param("firstName") String firstName);
	List<Person> findByFirstNameLikeIgnoreCase(@Param("firstName") String firstName);
	List<Person> findByLastNameLikeIgnoreCase(@Param("lastname") String lastname);
	List<Person> findByLastNameLike(@Param("lastName") String lastName);
	List<Person> findAll();
	
}
