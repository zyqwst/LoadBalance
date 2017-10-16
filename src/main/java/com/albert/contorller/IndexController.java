/**
 * 
 */
package com.albert.contorller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
* @ClassName: IndexController 
* @Description: 
* @author albert
* @date 2017年10月16日 上午11:14:29 
*  
*/
@Controller
public class IndexController {
	@Resource
	HttpServletRequest request;
	@Resource
	HttpSession session;
	@RequestMapping({ "", "/index" })
	public String index(Model model) {
		Object user = session.getAttribute("curuser");
		
		if(user == null) return "redirect:login";
		model.addAttribute("port", request.getLocalPort());
		return "index";
	}
	@RequestMapping(value="/login",method = RequestMethod.GET )
	public String login(Model model){
		Object user = session.getAttribute("curuser");
		model.addAttribute("port", request.getLocalPort());
		
		if(user != null) return "index";
		
		return "login";
	}
	@RequestMapping(value="/login",method = RequestMethod.POST )
	public String loginForm(String username,Model model){
		session.setAttribute("sessionid", session.getId());
		session.setAttribute("curuser", username);
		model.addAttribute("port", request.getLocalPort());
		return "index";
	}
	@RequestMapping(value="/logout" )
	public String logout(){
		session.invalidate();
		return "redirect:index";
	}
}
