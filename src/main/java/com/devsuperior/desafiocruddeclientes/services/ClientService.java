package com.devsuperior.desafiocruddeclientes.services;

import com.devsuperior.desafiocruddeclientes.dto.ClientDTO;
import com.devsuperior.desafiocruddeclientes.entities.Client;
import com.devsuperior.desafiocruddeclientes.repositories.ClientRepository;
import com.devsuperior.desafiocruddeclientes.services.exceptions.DatabaseException;
import com.devsuperior.desafiocruddeclientes.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente inexistente"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        insertClientDto(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try {
            Client entity = repository.getReferenceById(id);
            insertClientDto(dto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Cliente inexistente para realizar atualização");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Cliente inexistente para realizar a exclusão");
        }else {
            repository.deleteById(id);
        }
    }

    private void insertClientDto(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
