package eve.models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class City {
	
	@Id
	private String id;
    private String uuid;
    private String name;
    private String latitude;
    private String longitude;
    
}
