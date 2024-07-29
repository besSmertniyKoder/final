package com.company.sukhachevfinalstep.service;

import com.company.sukhachevfinalstep.dto.ClientDto;
import com.company.sukhachevfinalstep.model.Client;
import com.company.sukhachevfinalstep.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{
    ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ClientDto getClientById(Long id) {
        return clientRepository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        Client client = convertToEntity(clientDto);
        clientRepository.save(client);
        return convertToDto(client);
    }

    @Override
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Client client = convertToEntity(clientDto);
        client.setId(clientDto.getId());
        clientRepository.save(client);
        return convertToDto(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);

    }
    /**
     * Конвертация сущности клиента в DTO.
     */
    private ClientDto convertToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        clientDto.setBalance(client.getBalance());
        clientDto.setStatus(client.getStatus());
        return clientDto;
    }

    /**
     * Конвертация DTO в сущность клиента.
     */
    private Client convertToEntity(ClientDto clientDto) {
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setEmail(clientDto.getEmail());
        client.setBalance(clientDto.getBalance());
        client.setStatus(clientDto.getStatus());
        return client;
    }


}
