package sbangularjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sbangularjs.model.Client;
import sbangularjs.model.OrderDone;
import sbangularjs.repository.ClientRepository;
import sbangularjs.repository.OrderDoneRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EditOrderRESTController {
    @Autowired
    private OrderDoneRepository orderDoneRepository;
    @Autowired
    private ClientRepository clientRepository;

    /*@RequestMapping(value = "/getAllOrders",method = RequestMethod.GET,produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
//    @GetMapping("/getAllOrders")
    public List<OrderDone> getAllOrders() {
        return orderDoneRepository.findAll();
    }

    @RequestMapping(value = "/getAllClients",method = RequestMethod.GET,produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @RequestMapping(value = "/findOrders", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<OrderDone> findOrders(@RequestBody Client client) {
        Integer sum = client.getId();
        List<Client> findClients = new ArrayList<>();
        if (client.getSurname() != null && client.getEmail()!= null)
            findClients = clientRepository.findAllBySurnameContainingIgnoreCaseAndEmailContainingIgnoreCase(client.getSurname(),client.getEmail());
        else
            if (client.getSurname() != null)
                findClients = clientRepository.findBySurnameContainingIgnoreCase(client.getSurname());
            else
                if (client.getEmail()!= null)
                    findClients = clientRepository.findByEmailContainingIgnoreCase(client.getEmail());
                else
                    if(sum != null)
                        return orderDoneRepository.findAllBySum(sum);
                    else
                        return getAllOrders();

//        if(findClients == null) // если мы не нашли клиента по заданным параметрам
//            return null;

        List<OrderDone> listOrders = new ArrayList<>();
        for(Client curClient: findClients) {
            if(sum != null)
                listOrders.addAll(orderDoneRepository.findAllByClientIdAndSum(curClient.getId(), sum));
            else
                listOrders.addAll(orderDoneRepository.findAllByClientId(curClient.getId()));
        }
        return listOrders;
    }*/
}
