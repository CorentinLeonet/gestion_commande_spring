package be.leonet.corentin.gestion_commande_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping("/home")
	String getIndex() {
		return "index";
	}
	
}
