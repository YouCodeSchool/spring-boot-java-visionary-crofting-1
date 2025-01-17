package com.youcode.visionarycrofting.service;


import com.youcode.visionarycrofting.classes.PasserCommande;
import com.youcode.visionarycrofting.entity.Client;
import com.youcode.visionarycrofting.entity.Command;
import com.youcode.visionarycrofting.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final CommandService commandService;

    @Autowired
    public ClientService(ClientRepository clientRepository, CommandService commandService) {
        this.clientRepository = clientRepository;
        this.commandService = commandService;
    }

    public List<Client> getClients()
    {
        return  clientRepository.findAll();
    }

    public Optional<Client> getOneById(Long id){
        return clientRepository.findById(id);
    }

    public void addClient(Client client)
    {
        Optional<Client> clientOptional=clientRepository.findClientByEmail(client.getEmail());



        if (client.getAddress()==null || client.getEmail()==null || client.getPassword()==null ||  client.getName()==null || client.getPhone()==null)
        {
           throw new IllegalStateException("merci de remplir tous les informations du client  ");
        }
        if (clientOptional.isPresent())
        {
            throw new IllegalStateException("email déja exist");
        }


        clientRepository.save(client);
    }

    public void deleteClient(Long clientId) {
       Boolean exists=clientRepository.existsById(clientId);
       if(!exists)
       {
            throw new IllegalStateException("this client number:"+clientId+" does not exist");
       }

       clientRepository.deleteById(clientId);
    }





    public Client updateClient(Client client)
    {
        Client clientUpdated=clientRepository.findById(client.getId()).
                orElseThrow(()->new IllegalStateException("this client number:"+client.getId()+" does not exist"));


       if(client.getName()!=null) clientUpdated.setName(client.getName());
       if (client.getEmail()!=null)clientUpdated.setEmail(client.getEmail());
       if (client.getPassword()!=null)  clientUpdated.setPassword(client.getPassword());
       if (client.getPassword()!=null) clientUpdated.setPhone(client.getPhone());
       if (client.getAddress()!=null) clientUpdated.setAddress(client.getAddress());
        clientRepository.save(clientUpdated);

return clientUpdated;
    }



    public Client addCommand(Command command, Long id) {
        Optional<Client> clientOptional=clientRepository.findById(id);
        clientOptional.get().setCommand(command);
        clientRepository.save(clientOptional.get());
        return clientOptional.get();
    }

    public Client passerCommande(Long id, Collection<PasserCommande> productList) {
        Optional<Client> client=clientRepository.findById(id);
        Command command = commandService.createCommand(productList, client.get ());

        return addCommand(command, id);
    }

}
