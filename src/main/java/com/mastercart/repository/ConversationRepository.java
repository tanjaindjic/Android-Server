package com.mastercart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mastercart.model.Conversation;

public interface ConversationRepository  extends JpaRepository<Conversation, Long>{

}
