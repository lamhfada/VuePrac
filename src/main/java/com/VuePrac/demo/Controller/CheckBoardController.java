package com.VuePrac.demo.Controller;

//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckBoardController {

	@RequestMapping("/CheckBoard")
	public String InquireIndicator()
	{
		
		return "CheckBoard";
	}	
	
	
}
