package com.xarala.crud_xarala.mapper;

import com.xarala.crud_xarala.entity.Client;
import com.xarala.crud_xarala.model.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper extends  EntityMapper<ClientDto,Client> {


    @Override
    Client asEntity(ClientDto dto);

    @Override
    ClientDto asDto(Client entity);
}
