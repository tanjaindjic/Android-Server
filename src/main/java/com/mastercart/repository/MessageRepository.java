package com.mastercart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercart.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
