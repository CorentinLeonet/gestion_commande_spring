package be.leonet.corentin.gestion_commande_spring.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
	    Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
	    model.addAttribute("exception",exception);
	    
	    Object code = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    model.addAttribute("code",code);
	    
	    return "erreurs/error";
	}
	
}
