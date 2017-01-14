package eve.controls;

import java.security.GeneralSecurityException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eve.models.User;
import eve.repos.UsersRepository;
import eve.utils.CommonUtilities;

@RestController
@RequestMapping("/login")
public class AuthController {

	private String mAuthToken;
	
	@Autowired
	UsersRepository repo;

	@RequestMapping("/auth")
	public User login(@RequestParam("phone") String phone, 
			@RequestParam("otp") String otp) {
		if (repo.findByPhone(phone) != null) {
			String[] lstrp = null;
			try {
				String lstr = CommonUtilities.decrypt(CommonUtilities.stringToHex(repo.findByPhone(phone).getToken()));
				lstrp = lstr.split("#");
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return repo.findByPhone(phone) ;
		} else {
			try {
				mAuthToken = CommonUtilities.encrypt(String.format("%s#%s", UUID.randomUUID().toString(), phone));
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			User lUsers = new User();
			lUsers.setPhone(phone);
			lUsers.setToken(mAuthToken);
			repo.save(lUsers);
			return lUsers;
		}
	}

}
