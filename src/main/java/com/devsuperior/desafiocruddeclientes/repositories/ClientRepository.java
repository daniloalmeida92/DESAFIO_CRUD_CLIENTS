package com.devsuperior.desafiocruddeclientes.repositories;

import com.devsuperior.desafiocruddeclientes.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
