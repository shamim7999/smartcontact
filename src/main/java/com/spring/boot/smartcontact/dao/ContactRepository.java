package com.spring.boot.smartcontact.dao;

import com.spring.boot.smartcontact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    public List<Contact> getContactsByUserId(int userId);

    //@Query("select * from ")
    //public Contact getContactsByCId(int contactId);
}
