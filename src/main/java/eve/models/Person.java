package eve.models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

public class Person {
	
	@Id
	private String id;
	
	@Setter @Getter private String firstName;
	@Setter @Getter private String lastName;
	
	public Person(String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.lastName = lastName;
	}
		
}
