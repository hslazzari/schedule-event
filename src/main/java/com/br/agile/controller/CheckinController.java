package com.br.agile.controller;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.agile.bean.Event;
import com.br.agile.bean.User;
import com.br.agile.bean.UserEvent;
import com.br.agile.repository.EventRepository;
import com.br.agile.repository.UserEventRepository;
import com.br.agile.repository.UserRepository;
import com.google.gson.Gson;

@Controller
public class CheckinController {
	@Autowired
	private UserEventRepository userEventRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private EventRepository eventRepository;
	
	private static final Gson gson = new Gson();	
	
    @GetMapping("/checkin")
    public String checkin(Map<String, Object> model) {
    	
    	return "/checkin";
    	
    }
    
    @GetMapping("/post")
    public String checkinRelat(Map<String, Object> model) {
    	
    	return "ok";
    	
    }
    
	@PostMapping(value = "/checkin")
	public ResponseEntity<?> eventDetails(@RequestParam("id") String id, Map<String, Object> model) {
		
		
		User u = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).iterator().next();
		Event e = eventRepository.findOne(Integer.valueOf(id));
		 
		UserEvent user_evt = new UserEvent(e, u);
		boolean hasEvent;
		
		try {
			Iterable<UserEvent> ue = userEventRepository.FindRelationship(u.getUsername(), e.getId());
			hasEvent = ue.iterator().hasNext();
		} catch(Exception err) {
			hasEvent = false;
		}
		
		if(hasEvent) {
			return new ResponseEntity<String>(gson.toJson("Já participa do evento"), HttpStatus.OK);
		} else {
			userEventRepository.save(user_evt);
			return new ResponseEntity<String>(gson.toJson("OK"), HttpStatus.OK);
		}
	}
}
