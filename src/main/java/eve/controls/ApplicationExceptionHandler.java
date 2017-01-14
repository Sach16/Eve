package eve.controls;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseStatus;

import eve.models.Response;

@Controller
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Response resourceNotFound() {
		return new Response(false, 404, "Resource Not Found");
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Response unAuthorized() {
		return new Response(false, 401, "Resource Not Found");
	}
}
