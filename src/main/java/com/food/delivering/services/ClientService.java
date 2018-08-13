package com.food.delivering.services;

import com.food.delivering.enteties.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.food.delivering.repositories.ClientRepository;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {
    ClientRepository clientRepository;
    public List<Client> getClients() {
     return clientRepository.findAll();}
}
