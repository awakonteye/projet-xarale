package com.xarala.crud_xarala.mapper;

import com.xarala.crud_xarala.entity.Client;
import com.xarala.crud_xarala.model.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDto, Client> {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "actions", target = "actions")
    })
    @Override
    Client asEntity(ClientDto dto);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "actions", target = "actions")
    })
    @Override
    ClientDto asDto(Client entity);


}
