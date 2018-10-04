package eve.controls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import eve.models.Address;
import eve.models.City;
import eve.models.Person;
import eve.repos.PersonRepository;


@RestController
@RequestMapping("/people")
public class PersonController {
	
	@Autowired
	PersonRepository repo;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Person> people() {
		return repo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Person people(@RequestParam(value="firstname") String firstname, 
			@RequestParam(value="lastname") String lastname) {
		repo.save(new Person(firstname, lastname));
		return new Person(firstname, lastname);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Person peoplePut(@RequestParam(value="firstname") String firstname, 
			@RequestParam(value="lastname") String lastname, 
			@RequestParam(value="whereId") String whereId) {
		Person lPerson = (Person) repo.findByFirstName(whereId);
		lPerson.setFirstName(firstname);
		lPerson.setLastName(lastname);
		repo.save(lPerson);
		return new Person(firstname, lastname);
	}

	@RequestMapping(method = RequestMethod.PATCH)
	public Person peoplePatch(@RequestParam(value="firstname") String firstname, 
			@RequestParam(value="whereId") String whereId) {
		Person lPerson = (Person) repo.findByFirstName(whereId);
		lPerson.setFirstName(firstname);
		repo.save(lPerson);
		return lPerson;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String peoplePatch(@RequestParam(value="whereId") String whereId) {
		Person lPerson = (Person) (repo.findByFirstNameLikeIgnoreCase(whereId.toLowerCase()).get(0));
		repo.delete(lPerson);
		return String.format("%s are removed!", lPerson.getFirstName());
	}

	@RequestMapping(value = "/deletelastname", method = RequestMethod.DELETE)
	public String peopleDelete(@RequestParam(value="whereId") String whereId) {
		Person lPerson = (Person) (repo.findByLastNameLikeIgnoreCase(whereId.toLowerCase()).get(0));
		repo.delete(lPerson);
		return String.format("%s, you are removed!", lPerson.getLastName());
	}
}
