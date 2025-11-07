package com.xarala.crud_xarala.services.serviceImp;

import com.xarala.crud_xarala.entity.Client;
import com.xarala.crud_xarala.mapper.ClientMapper;
import com.xarala.crud_xarala.model.ClientDto;
import com.xarala.crud_xarala.repository.ClientRepository;
import com.xarala.crud_xarala.services.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImp implements IClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client entity = clientMapper.asEntity(clientDto);
        Client saved = clientRepository.save(entity);
        return clientMapper.asDto(saved);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        Client entity = clientMapper.asEntity(clientDto);
        Client saved = clientRepository.save(entity);
        return clientMapper.asDto(saved);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDto search(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::asDto)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::asDto)
                .collect(Collectors.toList());
    }
}
