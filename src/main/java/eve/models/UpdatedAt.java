package eve.models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatedAt {
	
	@Id
	private String id;
    private String date;
    private Integer timezoneType;
    private String timezone;
    
}
