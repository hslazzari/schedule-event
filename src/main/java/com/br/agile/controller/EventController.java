package com.br.agile.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.agile.bean.Event;
import com.br.agile.bean.UserEvent;
import com.br.agile.repository.EventRepository;
import com.br.agile.repository.UserEventRepository;

@Controller
public class EventController {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserEventRepository userEventRepository;
	
	@GetMapping("/event")
    public String events(@RequestParam(name = "source", defaultValue = "all") String source, Map<String, Object> model) {
		Iterable<Event> eventLst;
		
		if(source.equalsIgnoreCase("all")) {
			eventLst = eventRepository.findAll();
		} else {
			eventLst = eventRepository.findByDate(today(true));
		}
		
		model.put("source", source);
		model.put("eventLst", eventLst);
		
		return "/eventLst";
    }
	
	public static Timestamp today(boolean start) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        
        if(!start) {
        	calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return new Timestamp(calendar.getTimeInMillis());
    }
	
	@GetMapping(value = "/event/{eventId}")
	public String eventDetails(@PathVariable String eventId, @RequestParam("source") String source, Map<String, Object> model) {
		Event evt;
		if(Integer.valueOf(eventId) > 0) {
			evt = eventRepository.findOne(Integer.valueOf(eventId));
			if(source.equalsIgnoreCase("next")) {
				evt.setId(0);
			}
		} else {
			evt = new Event();
		}
		
		model.put("event", evt);
		model.put("source", source);
		model.put("qrcode", evt.getQRCodeImage());
		
		return "/event";
	}
	
	@PostMapping(value = "/event") 
	public String saveEvent(@Valid Event evt, @RequestParam("acao") String acao, @RequestParam("source") String source, Map<String, Object> model) {
		Iterable<Event> eventLst;
		
		if(source.equalsIgnoreCase("all")) {
			if(acao.equalsIgnoreCase("excluir")) {
				eventRepository.delete(evt.getId());
			} else {
				eventRepository.save(evt);
			} 
			
			eventLst = eventRepository.findAll();
		} else {
			eventLst = eventRepository.findByDate(today(true));
		}		
		
		model.put("source", source);
		model.put("eventLst", eventLst);
		
		
		return "/eventLst";
	}
	
	@GetMapping("/myevents")
    public String events(Map<String, Object> model) {
		Iterable<UserEvent> myEvents = userEventRepository.myEvents(SecurityContextHolder.getContext().getAuthentication().getName());
		 
		ArrayList<Event> e = new ArrayList<Event>();
		
		for(UserEvent ue : myEvents) {
			e.add(ue.getEvent());
		}
		
		model.put("eventLst", e);
		
		return "/myevents";
    }
	
	
	
	
}
