package eve.repos;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.connection.Stream;

import eve.models.Evnts;
import eve.models.Person;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface EvntsRepository extends MongoRepository<Evnts, Serializable>{
	
	List<Evnts> findByFirstName(@Param("firstName") String firstName);
	List<Evnts> findByLastName(@Param("lastName") String lastName);
	List<Evnts> findByFirstNameLike(@Param("firstName") String firstName);
	List<Evnts> findByAddress_AlphaStreetLikeIgnoreCase(@Param("alphaStreet") String alphaStreet);
	List<Evnts> findByAddress_AlphaStreetOrAddress_AlphaStreetLikeIgnoreCase(@Param("alphaStreet") String alphaStreet,
			@Param("betaStreet") String betaStreet);
	List<Evnts> findByFoodMenu_CuisineLike(@Param("cuisine") String cuisine);
	List<Evnts> findByFoodMenu_Attachments_NameLike(@Param("name") String name);
	List<Evnts> findAll();
//	@Query("{ 'firstName' : ?0 }")
//	List<Evnts> findByTheEvntssFirstName(String firstname);
	
	@Query(value="{ 'firstName' : ?0 }", fields="{ 'firstName' : 1, 'lastName' : 1}")
	List<Evnts> findByTheEvntssFirstName(@Param(value = "firstName") String firstName);
	
}
 