package eve.controls;

import java.awt.Event;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eve.Application;
import eve.models.Address;
import eve.models.Attachments;
import eve.models.City;
import eve.models.CreatedAt;
import eve.models.Evnts;
import eve.models.FoodMenu;
import eve.models.UpdatedAt;
import eve.repos.EvntsRepository;
import eve.repos.GsonHttp;

@RestController
@RequestMapping("/events")
public class EvntsController {

	private final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	EvntsRepository repo;

	@Autowired
	GsonHttp gsonHttp;

	@RequestMapping(method = RequestMethod.GET)
	public List<Evnts> events() {
		return repo.findAll();
	}

	@RequestMapping("/address")
	public Address eventsAddress() {
		return new Address();
	}

	@RequestMapping("/city")
	public City eventsCity() {
		return new City();
	}

	@RequestMapping("/foodmenu")
	public FoodMenu eventsFoodMenu() {
		return new FoodMenu();
	}

	@RequestMapping("/createdat")
	public CreatedAt eventsCreatedAt() {
		return new CreatedAt();
	}
	
	@RequestMapping("/attachments")
	public Attachments eventsAttachments() {
		return new Attachments();
	}

	@RequestMapping("/delete")
	public String eventsDelete(@RequestParam(value = "firstName") String firstName) {
		Evnts lEvnts = new Evnts();
		try {
			for (Evnts lEvnts2 : repo.findByFirstNameLike(firstName)){
				repo.delete(lEvnts2);
			}
			return "Success";
		} catch (Exception e) {
			// TODO: handle exception
			return "Failure";
		}
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST)
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public String events(
	 * 
	 * @RequestParam(value = "firstName") String firstName,
	 * 
	 * @RequestParam(value = "lastName") String lastName,
	 * 
	 * @RequestParam(value = "cost") Integer cost,
	 * 
	 * @RequestParam(value = "cuisine") String cuisine,
	 * 
	 * @RequestParam(value = "cuisinename") String cuisinename,
	 * 
	 * @RequestParam(value = "crowd") Integer crowd,
	 * 
	 * @RequestParam(value = "parking") Integer parking,
	 * 
	 * @RequestParam(value = "createdAt") String createdAt,
	 * 
	 * @RequestParam(value = "updatedAt") String updatedAt){ return "Success";
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(method = RequestMethod.POST)
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public String events(
	 * 
	 * @RequestParam(value = "firstName") String firstName,
	 * 
	 * @RequestParam(value = "lastName") String lastName,
	 * 
	 * @RequestParam(value = "address") Address address,
	 * 
	 * @RequestParam(value = "cost") Integer cost,
	 * 
	 * @RequestParam(value = "cuisine") String cuisine,
	 * 
	 * @RequestParam(value = "cuisinename") String cuisinename,
	 * 
	 * @RequestParam(value = "crowd") Integer crowd,
	 * 
	 * @RequestParam(value = "parking") Integer parking,
	 * 
	 * @RequestParam(value = "createdAt") String createdAt,
	 * 
	 * @RequestParam(value = "updatedAt") String updatedAt){ return "Success";
	 * 
	 * }
	 */

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String events(@RequestBody String json) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Evnts lEvnts = new Evnts();
		try {
			lEvnts = gsonHttp.getGson().fromJson(json, Evnts.class);
			repo.save(lEvnts);
			log.info(gson.toJson(lEvnts));
			return json;
		} catch (Exception e) {
			log.error(e.getMessage());
			return "Failure";
			// TODO: handle exception
		}
	}

}
