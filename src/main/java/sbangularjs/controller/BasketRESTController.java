package sbangularjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sbangularjs.model.*;
import sbangularjs.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BasketRESTController {
    @Autowired
    private CurrentOrderRepository currentOrderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private ReestrRepository reestrRepository;
    @Autowired
    private OrderDoneRepository orderDoneRepository;

    @RequestMapping(value = "/getCurrentOrder", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Medicine> getCurrentOrder(@AuthenticationPrincipal User user) {
        List<CurrentOrder> currentOrder = currentOrderRepository.findAllByUserId(user.getId());
        List<Medicine> listMedicine = new ArrayList<>();
        for(CurrentOrder order : currentOrder) {
            Optional<Medicine> optionalMedicine = medicineRepository.findById(order.getMedicineId());
            if(!optionalMedicine.isPresent())
                return null;
            Medicine medicine = optionalMedicine.get();
            medicine.setQuantity(order.getCount());
            listMedicine.add(medicine);
        }

        return listMedicine;
    }

    @RequestMapping(value = "/minus", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Medicine> minus(@AuthenticationPrincipal User user, @RequestBody Integer medicineId) {
        CurrentOrder currentOrder = currentOrderRepository.findByUserIdAndMedicineId(user.getId(), medicineId);
        if(currentOrder == null)
            return null;
        if (currentOrder.getCount() != 1) {
            currentOrder.setCount(currentOrder.getCount() - 1);
            currentOrderRepository.save(currentOrder);

            Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
            if(!optionalMedicine.isPresent())
                return null;
            Medicine medicine = optionalMedicine.get();
            medicine.setQuantity(medicine.getQuantity()+1);
            medicineRepository.save(medicine);
        }

        return getCurrentOrder(user);
    }

    @RequestMapping(value = "/plus", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Medicine> plus(@AuthenticationPrincipal User user, @RequestBody Integer medicineId) {
        CurrentOrder currentOrder = currentOrderRepository.findByUserIdAndMedicineId(user.getId(), medicineId);
        if(currentOrder != null) {
            Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
            if(!optionalMedicine.isPresent())
                return null;
            Medicine medicine = optionalMedicine.get();
            if (medicine.getQuantity() != currentOrder.getCount()) {
                currentOrder.setCount(currentOrder.getCount() + 1);
                currentOrderRepository.save(currentOrder);
                medicine.setQuantity(medicine.getQuantity()-1);
                medicineRepository.save(medicine);
            }
            return getCurrentOrder(user);
        }
        else
            return null;
    }


    @RequestMapping(value = "/deletePosition", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Medicine> deletePosition(@AuthenticationPrincipal User user, @RequestBody Integer medicineId) {
        CurrentOrder currentOrder = currentOrderRepository.findByUserIdAndMedicineId(user.getId(), medicineId);
        if(currentOrder == null)
            return null;
        Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
        if(!optionalMedicine.isPresent()) return null;
        Medicine medicine = optionalMedicine.get();
        medicine.setQuantity(medicine.getQuantity()+currentOrder.getCount());
        medicineRepository.save(medicine);

        currentOrderRepository.delete(currentOrder);
//        currentOrder.setCount(currentOrder.getCount() + 1);
//        currentOrderRepository.save(currentOrder);
        return getCurrentOrder(user);
    }

    @RequestMapping(value = "/makeOrder", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void makeOrder(@AuthenticationPrincipal User user) {
        List<CurrentOrder> currentOrder = currentOrderRepository.findAllByUserId(user.getId());
        if(!currentOrder.isEmpty()) {
            Integer total = 0;
            OrderDone newOrder = orderDoneRepository.save(new OrderDone());
            for(CurrentOrder order: currentOrder) {
                Optional<Medicine> optionalMedicine = medicineRepository.findById(order.getMedicineId());
                if(!optionalMedicine.isPresent()) return;
                Integer medicinePrice = optionalMedicine.get().getPrice();
                total += order.getCount() * medicinePrice;

                reestrRepository.save(new Reestr(order.getMedicineId(),order.getCount(), newOrder.getOrderId()));
            }
            currentOrderRepository.deleteAll(currentOrder);
//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            Date date = new Date();
//            newOrder.setDate(dateFormat.format(date));

            newOrder.setClientId(clientRepository.findByUsername(user.getUsername()).getId());
            newOrder.setSum(total);
            orderDoneRepository.save(newOrder);
        }
    }

}
