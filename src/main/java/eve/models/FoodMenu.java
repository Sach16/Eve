package eve.models;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodMenu {
	
	@Id
	private String id;
	private Integer cost;
	private String cuisine;
	private String name;
}
