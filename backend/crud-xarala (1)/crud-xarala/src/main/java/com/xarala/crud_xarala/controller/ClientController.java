package com.xarala.crud_xarala.controller;

import com.xarala.crud_xarala.model.ClientDto;
import com.xarala.crud_xarala.model.Response;
import com.xarala.crud_xarala.services.IClientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final IClientService clientService;

    @Operation(summary = "Create client", description = "Cet endpoint permet de créer un client à partir des données envoyées")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Object> createClient(@RequestBody ClientDto clientDto) {
        try {
            var dto = clientService.createClient(clientDto);
            return Response.ok().setPayload(dto).setMessage("Client créé");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }

    @Operation(summary = "Update client", description = "Cet endpoint permet de modifier un client existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client modifié avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> updateClient(
            @PathVariable("id") Long id,
            @RequestBody ClientDto clientDto) {

        clientDto.setId(id);
        try {
            var dto = clientService.updateClient(clientDto);
            return Response.ok().setPayload(dto).setMessage("Client modifié");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }

    @Operation(summary = "Get client by id", description = "Récupère un client à partir de son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succès"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> getClient(
            @PathVariable Long id) {
        try {
            var dto = clientService.search(id);
            return Response.ok().setPayload(dto).setMessage("Client trouvé");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }

    @Operation(summary = "Get all clients", description = "Retourne la liste de tous les clients enregistrés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Succès"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Response<Object> getAllClients() {
        try {
            List<ClientDto> clients = clientService.findAll();
            return Response.ok().setPayload(clients).setMessage("Liste des clients récupérée");
        } catch (Exception ex) {
            return Response.badRequest().setMessage(ex.getMessage());
        }
    }

    @Operation(summary = "Delete client", description = "Supprime un client par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable("id") Long id) {
        try {
            clientService.deleteClient(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
