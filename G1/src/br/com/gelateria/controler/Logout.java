package br.com.gelateria.controler;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

// finaliza sessão 
@ManagedBean
@RequestMapping("logout") 
public class Logout {

	public String getlogout(HttpSession session) {  
	    session.invalidate();  
	    return "redirect:login";  
	}  

}
