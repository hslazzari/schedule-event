package com.br.agile.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserEventId implements Serializable {
	
    private String user_id;
    private Integer event_id;
    
    public UserEventId() {
	}
    
    public UserEventId(String user_id, Integer event_id) {
    	this.user_id = user_id;
    	this.event_id = event_id;
	}
    
	@Column(name = "user_username")
	public String getUser() {
		return this.user_id;
	}

	public void setUser(String user_id) {
		this.user_id = user_id;
	}
	
	@Column(name = "event_id")
	public Integer getEvent() {
		return this.event_id;
	}

	public void setEvent(Integer event_id) {
		this.event_id = event_id;
	}
	
	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        UserEventId that = (UserEventId) o;
        
        return that.event_id == this.event_id && that.user_id.equalsIgnoreCase(this.user_id);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(user_id, event_id);
    }
}