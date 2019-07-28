package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
