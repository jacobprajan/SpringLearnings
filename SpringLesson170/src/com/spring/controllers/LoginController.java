package com.spring.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dao.FormValidationGroup;
import com.spring.dao.Message;
import com.spring.dao.User;
import com.spring.service.UsersService;

@Controller
public class LoginController
{

	private UsersService usersService;
	
	@Autowired
	private MailSender mailSender;

	@Autowired
	public void setUsersService(UsersService usersService)
	{
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin()
	{
		return "login";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model)
	{
		// To find out which class is throwing access denial exception
		/*
		 * try { List<User> users = usersService.getAllUsers();
		 * model.addAttribute("users",users); } catch(Exception e) {
		 * System.out.println(e.getClass()); }
		 */

		List<User> users = usersService.getAllUsers();
		model.addAttribute("users", users);

		return "admin";
	}

	@RequestMapping("/denied")
	public String showDenied()
	{
		return "denied";
	}

	@RequestMapping("/messages")
	public String showMessages()
	{
		return "messages";
	}

	@RequestMapping("/loggedout")
	public String showLoggedOut()
	{
		return "loggedout";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model)
	{
		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	// public String createAccount(@Valid User user, BindingResult result)
	public String createAccount(
			@Validated(FormValidationGroup.class) User user,
			BindingResult result)
	{
		if (result.hasErrors())
		{
			return "newaccount";
		}

		user.setEnabled(true);
		user.setAuthority("ROLE_USER");

		/*
		 * 
		 * try { usersService.create(user); } catch (DuplicateKeyException e) {
		 * // For finding out which class is throwing exception. // Here while
		 * username is duplicated in users table, DuplicateKeyException will be
		 * thrown // System.out.println(e.getClass());
		 * result.rejectValue("username", "DuplicateKey.user.username"); return
		 * "newaccount"; }
		 */

		// Best way to check if username already exists

		if (usersService.exists(user.getUsername()))
		{
			System.out.println("Caught duplicate username");
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}

		usersService.create(user);

		return "accountcreated";
	}

	@RequestMapping(value = "/getmessages", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal)
	{
		List<Message> messages = null;
		if (principal == null)
		{
			messages = new ArrayList<Message>();
		} else
		{
			String username = principal.getName();

			// another method of getting username
			// Authentication auth =
			// SecurityContextHolder.getContext().getAuthentication();
			// String username = auth.getName(); //get logged in username

			messages = usersService.getMessages(username);
		}

		System.out.println("messages.size() : " + messages.size());

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());

		return data;
	}
	
	
	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String, Object> data)
	{

		String text = (String) data.get("text");
		String name = (String) data.get("name");
		String email = (String) data.get("email");
		Integer target = (Integer) data.get("target");
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("rexrichu@gmail.com");
		//mail.setTo(email);
		mail.setTo("jjacobprajan@gmail.com");
		mail.setSubject("Re: " + name);
		mail.setText(text);
		try
		{
			mailSender.send(mail);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Can't send message");
		}
		
		
		//System.out.println(name + " : " + email + " : " + text);
		
		Map<String, Object> rval = new HashMap<String, Object>();
		
		rval.put("success", true);
		
		rval.put("target", target);

		return rval;
	}
}
