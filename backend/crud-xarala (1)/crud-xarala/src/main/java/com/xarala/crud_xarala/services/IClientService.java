package com.xarala.crud_xarala.services;

import com.xarala.crud_xarala.model.ClientDto;

import java.util.List;

public interface IClientService {

    ClientDto createClient(ClientDto clientDto);

    ClientDto updateClient(ClientDto clientDto);

    void deleteClient (Long id);

    ClientDto search (Long id);

    List<ClientDto> findAll();
}
