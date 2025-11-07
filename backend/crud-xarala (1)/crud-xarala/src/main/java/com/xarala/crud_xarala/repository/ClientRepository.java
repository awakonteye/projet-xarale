package com.xarala.crud_xarala.repository;

import com.xarala.crud_xarala.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByNameContainingIgnoreCase (String name);
    Client findByEmail (String email);
    List<Client> findByStatus (String status);
}
