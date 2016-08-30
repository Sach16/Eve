package eve.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eve.models.Users;
import eve.repos.UsersRepository;

@RestController
@RequestMapping("/login")
public class AuthController {
	
	@Autowired
	UsersRepository repo;
	
	@RequestMapping("/auth")
	public Users login(@RequestParam("phone") String phone,
			@RequestParam("otp") String otp){
		Users lUsers = new Users();
		lUsers.setPhone(phone);
		repo.save(lUsers);
		return lUsers;
	}

}
