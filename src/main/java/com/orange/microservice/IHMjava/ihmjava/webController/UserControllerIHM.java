package com.orange.microservice.IHMjava.ihmjava.webController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.orange.microservice.IHMjava.model.User;

@Controller
public class UserControllerIHM {
	
	@Autowired
	private RestTemplate userRestTemplate;
	
	@Bean
	public RestTemplate userRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	@RequestMapping(value="/save", method=RequestMethod.POST)
    public String save (WebRequest request) {
    	User user = new User();
    	user.setNom(request.getParameter("firstname"));
    	user.setPrenom(request.getParameter("name"));
    	user.setLogin(request.getParameter("username"));
    	user.setMotDePasse(request.getParameter("password"));
    	user.setFonction(request.getParameter("function"));
    	user.setMail(request.getParameter("email"));
    	userRestTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", "pass"));
    	String result = userRestTemplate.postForObject("http://localhost:9090/Utilisateur", user, String.class);
    	System.out.println("Result : " + result);
    	System.out.println(request.getParameter("firstname"));
    	System.out.println(request.getParameter("name"));
    	System.out.println(request.getParameter("username"));
    	System.out.println(request.getParameter("password"));
    	System.out.println(request.getParameter("function"));
    	System.out.println(request.getParameter("email"));
    	if (result.equals("Un utilisateur à été crée")) {
    		return "redirect:TabListUser.html";
    	}else {
    		return"redirect:Erreur.html";
    	}
    	}
	
//	 @RequestMapping(value = { "/", "/home2" })
//     public @ResponseBody ModelAndView home() {
//         System.out.println("sdasasas");
//         ModelAndView modelAndView = new ModelAndView();
//         modelAndView.setViewName("home");
//         return modelAndView;
//     }

    	@RequestMapping(value="/Utilisateur", method=RequestMethod.GET)
        public ModelAndView ListUser (WebRequest request, Model model) {
    		userRestTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", "pass"));
        	List<User> result = userRestTemplate.getForObject("http://localhost:9090/Utilisateur", List.class);
        	ModelAndView modelAndView = new ModelAndView();
        	modelAndView.addObject("listeUtilisateur", result);
         	modelAndView.addObject("toto", "titi");
            
            modelAndView.setViewName("TabListUser");
            return modelAndView;
        	
        	
	}
}
