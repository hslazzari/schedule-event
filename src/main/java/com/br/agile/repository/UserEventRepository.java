package com.br.agile.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agile.bean.UserEvent;

@Repository
public interface UserEventRepository extends CrudRepository<UserEvent, Integer> {

    @Query("SELECT ue FROM UserEvent ue WHERE ue.id.user_id = :userName")
	public Iterable<UserEvent> myEvents(@Param("userName") String userName);
    
    @Query("SELECT ue FROM UserEvent ue WHERE ue.id.user_id = :userName AND ue.id.event_id = :eventId")
	public Iterable<UserEvent> FindRelationship(@Param("userName") String userName, @Param("eventId") Integer eventId); 
	 
} 