package eve.controls;

import java.awt.Event;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.Mongo;

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
import eve.repos.UsersRepository;

@RestController
@RequestMapping("/events")
public class EvntsController {

	private final Logger log = LoggerFactory.getLogger(Application.class);

	String mAuth;

	@Autowired
	ApplicationExceptionHandler mAppExcepHandler;

	@Autowired
	UsersRepository mUserRepo;

	@Autowired
	EvntsRepository mEveRepo;

	@Autowired
	GsonHttp gsonHttp;
	
	public static final String TYPE_WEDDING = "typeWedding";
    public static final String TYPE_ENGAGEMENT = "typeEngagement";
    public static final String TYPE_BIRTHDAY = "typeBirthday";
    public static final String TYPE_NAMING = "typeNaming";
    public static final String TYPE_CORPORATE = "typeCorporate";
    public static final String TYPE_PARTY = "typeParty";

    public static final String FILTER_PLACE = "filterPlace";
    public static final String FILTER_DATE = "filterDate";
    public static final String FILTER_SESSION = "filterSession";

	@RequestMapping(method = RequestMethod.GET)
	public Object evnts(@RequestHeader("Authorization") String authorization,
			@RequestParam(value = "include", required = false) String[] include,
			@RequestParam(value = "place", required = false) String filterPlace) {
		mAuth = authorization.split(" ")[1];
		if (mUserRepo.findByToken(mAuth) != null){
			if (filterPlace != null)
				return mEveRepo.findByAddress_AlphaStreetLikeIgnoreCase(filterPlace);
			else
				return mEveRepo.findAll();
		}else{
			return mAppExcepHandler.unAuthorized();
		}

	}
	
	@RequestMapping(value = "/changeName", method = RequestMethod.GET)
	public Object evntChangeName(@RequestHeader("Authorization") String authorization,
			@RequestParam(value = "include", required = false) String[] include,
			@RequestParam(value = "from") String from,
			@RequestParam(value = "to") String to) {
		mAuth = authorization.split(" ")[1];
		if (mUserRepo.findByToken(mAuth) != null){
			return evntsOne(from, to);
		}else{
			return mAppExcepHandler.unAuthorized();
		}

	}
	
	public Evnts evntsOne(String pFrom, String pTo) {
		Query lquery = new Query(Criteria.where("firstName").is(pFrom));
		Update lupdate = new Update().update("firstName", pTo);
		MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new Mongo(), "eve"));
		return mongoOps.findAndModify(lquery, lupdate, Evnts.class);
	}
	
	/*@RequestParam(value=TYPE_WEDDING, required = false) String typeWedding,
	@RequestParam(value=TYPE_ENGAGEMENT, required = false) String typeEngagement,
	@RequestParam(value=TYPE_BIRTHDAY, required = false) String typeBirthday,
	@RequestParam(value=TYPE_NAMING, required = false) String typeNaming,
	@RequestParam(value=TYPE_CORPORATE, required = false) String typeCorporate,
	@RequestParam(value=TYPE_PARTY, required = false) String typeParty,
	@RequestParam(value=FILTER_PLACE, required = false) String filterPlace,
	@RequestParam(value=FILTER_DATE, required = false) String filterDate,
	@RequestParam(value=FILTER_SESSION, required = false) String filterSession*/

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
			for (Evnts lEvnts2 : mEveRepo.findByFirstNameLike(firstName)) {
				mEveRepo.delete(lEvnts2);
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
	public Object events(@RequestHeader("Authorization") String authorization,
			@RequestBody String json) {
		mAuth = authorization.split(" ")[1];
		if (mUserRepo.findByToken(mAuth) != null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			Evnts lEvnts = new Evnts();
			try {
				lEvnts = gsonHttp.getGson().fromJson(json, Evnts.class);
				mEveRepo.save(lEvnts);
				log.info(gson.toJson(lEvnts));
				return json;
			} catch (Exception e) {
				log.error(e.getMessage());
				return "Failure";
				// TODO: handle exception
			}
		} else {
			return mAppExcepHandler.unAuthorized();
		}

	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Object eventsByTheFirstName(@RequestHeader("Authorization") String authorization,
			@RequestParam(value = "firstName") String firstName) {
		mAuth = authorization.split(" ")[1];
		if (mUserRepo.findByToken(mAuth) != null) {
			try {
				//TODO : Create an instance of mongotemplate and query
				BasicQuery lquery = new BasicQuery("{ address.alphaStreet : { $concat : '%',address.alphaStreet,'%' }}");
				return mEveRepo.findAll();
			} catch (Exception e) {
				log.error(e.getMessage());
				return "Failed to retrieve Event!";
				// TODO: handle exception
			}
		} else {
			return mAppExcepHandler.unAuthorized();
		}

	}

}
