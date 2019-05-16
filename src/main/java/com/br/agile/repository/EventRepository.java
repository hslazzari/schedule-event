package com.br.agile.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agile.bean.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {

    @Query("SELECT e FROM Event e WHERE e.date >= :startDate")
	public Iterable<Event> findByDate(@Param("startDate") Timestamp startDate);
	 
} 