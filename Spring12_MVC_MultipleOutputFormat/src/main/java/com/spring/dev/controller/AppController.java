package com.spring.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dev.model.Pizza;

@Controller
public class AppController
{
	// Sample requests:
	// http://localhost:8070/Spring12_MVC_MultipleOutputFormat/pizzavalley/margherita.xml
	// http://localhost:8070/Spring12_MVC_MultipleOutputFormat/pizzavalley/margherita.json
	// http://localhost:8070/Spring12_MVC_MultipleOutputFormat/pizzavalley/margherita.pdf	
	// http://localhost:8070/Spring12_MVC_MultipleOutputFormat/pizzavalley/margherita.xls
	// http://localhost:8070/Spring12_MVC_MultipleOutputFormat/pizzavalley/margherita
	
	@RequestMapping(value = "/pizzavalley/{pizzaName}", method = RequestMethod.GET)
	public String getPizza(@PathVariable String pizzaName, ModelMap model)
	{

		Pizza pizza = new Pizza(pizzaName);
		model.addAttribute("pizza", pizza);

		return "pizza";

	}

}