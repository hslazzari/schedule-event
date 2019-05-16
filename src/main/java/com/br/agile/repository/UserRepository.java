package com.br.agile.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agile.bean.Event;
import com.br.agile.bean.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
	
    @Query("SELECT u FROM User u WHERE u.username = :name")
	public Iterable<User> findByUserName(@Param("name") String name);

}