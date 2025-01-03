package sbangularjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sbangularjs.model.Medicine;
import sbangularjs.repository.GroupRepository;
import sbangularjs.repository.MedicineRepository;

import java.util.Optional;

@RestController
public class MedicineRESTController {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    /*@RequestMapping(value = "/medicine/{medicineId}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public String getMedicine(@PathVariable Medicine medicine, Model model) {
        model.addAttribute("med", medicine);
        return "medicine";
    }*/

    @RequestMapping(value = "/getMedicine", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Medicine getMedicine(@RequestBody Integer medicineId) {
        Optional<Medicine> optionalMed = medicineRepository.findById(medicineId);
        if(!optionalMed.isPresent()) return null;
        return optionalMed.get();
    }

    @RequestMapping(value = "/deleteMedicine", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteMedicine(@RequestBody Integer medicineId) {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
        if(!optionalMedicine.isPresent()) return;
        medicineRepository.delete(optionalMedicine.get());
    }

    @RequestMapping(value = "/saveMedicine", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Medicine updateMedicine(@RequestBody Medicine medicine, Model model) {
        Medicine updatingMed = new Medicine();
        if(medicine.getId() != 0) {
            Optional<Medicine> optionalMed = medicineRepository.findById(medicine.getId());
            updatingMed = optionalMed.get();
        }
        updatingMed.setName(medicine.getName());
        updatingMed.setGroupId(medicine.getGroupId());
        updatingMed.setImg(medicine.getImg());
        updatingMed.setQuantity(medicine.getQuantity());
        updatingMed.setPrice(medicine.getPrice());
        medicineRepository.save(updatingMed);
//        model.addAttribute("success", "Изменения успешно сохранены!");
        return updatingMed;
    }



    /*@RequestMapping(value = "/medicine", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Medicine> newMedicine() {
        return medicineRepository.findAll();
    }*/

    /*@RequestMapping(value = "/findMedicines", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Medicine> findMedicines(@RequestBody Medicine medicine) {
        List<Medicine> medicines = new ArrayList<>();
        if(medicine.getGroupId() != null && medicine.getName() != null)
            medicines = medicineRepository.findAllByNameContainingIgnoreCaseAndGroupId(medicine.getName(), medicine.getGroupId());
        else
            if(medicine.getGroupId() != null)
                medicines = medicineRepository.findAllByGroupId(medicine.getGroupId());
              else
                if(medicine.getName() != null)
                    medicines = medicineRepository.findAllByNameContainingIgnoreCase(medicine.getName());
                else
                    medicines = medicineRepository.findAll();
        return medicines;
    }



    @RequestMapping(value = "/addToBasket", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Medicine> addToBasket(@AuthenticationPrincipal User user, @RequestBody Integer medicineId) {
        CurrentOrder currentOrder = currentOrderRepository.findByUserIdAndMedicineId(user.getId(), medicineId);
        if(currentOrder == null) {
            CurrentOrder tmp = new CurrentOrder(medicineId, 1, user.getId());
            currentOrderRepository.save(tmp);
        }
        else {
            currentOrder.setCount(currentOrder.getCount() + 1);
            currentOrderRepository.save(currentOrder);
        }
        Optional<Medicine> optionalMedicine = medicineRepository.findById(medicineId);
        if(!optionalMedicine.isPresent())
            return null;
        Medicine medicine = optionalMedicine.get();
        medicine.setQuantity(medicine.getQuantity()-1);
        medicineRepository.save(medicine);

        return medicineRepository.findAll();
    }*/
}
