package com.br.agile.bean;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User_Event")
public class UserEvent{
	@EmbeddedId
	private UserEventId id;

	@OneToOne(fetch = FetchType.LAZY)
	@MapsId("user_id")
    private User user;
    
    @OneToOne(fetch = FetchType.LAZY)
	@MapsId("event_id")
    private Event event; 
    
	public UserEvent() {
	}
	
	public UserEvent(Event event, User user) {
		this.event = event;
		this.user = user;
		this.id = new UserEventId(user.getUsername(), event.getId());
	}
    

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}