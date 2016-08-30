package eve.models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

public class Person {
	
	@Id
	private String id;
	
	private String firstName;
	private String lastName;
	
	
	
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Person(String firstName, String lastName) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.lastName = lastName;
	}
		
}
