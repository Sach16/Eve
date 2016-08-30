package eve.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by S.K. Pissay on 30/3/16.
 */

@Setter
@Getter
public class Address implements Serializable{

	@Id
	private String id;
	
    private String uuid;
    private String email;
    private String phone;
    private String alphaStreet;
    private String betaStreet;
    private City city;
    private Integer cityId;
    private String state;
    private String country;
    private Integer zip;
    
    public Address() {
		// TODO Auto-generated constructor stub
	}
    
    public Address(String uuid, String email, String phone, String alphaStreet, String betaStreet,
    		City city, int cityId, String state, String country, int zip){
    	this.uuid = uuid;
    	this.email = email;
    	this.phone = phone;
    	this.alphaStreet = alphaStreet;
    	this.betaStreet = betaStreet;
//    	this.city = city;
    	this.cityId = cityId;
    	this.state = state;
    	this.country = country;
    	this.zip = zip;
    }

}

