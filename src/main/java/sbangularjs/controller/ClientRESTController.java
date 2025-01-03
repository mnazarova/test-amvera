package sbangularjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sbangularjs.model.Client;
import sbangularjs.repository.ClientRepository;

import java.util.List;

@RestController
//@PreAuthorize("hasAuthority('MANAGER')")
public class ClientRESTController {
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(value = "/addClient",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
//    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client clientForm) {
        Client client = new Client(clientForm.getSurname(), clientForm.getName(),
                                   clientForm.getPhone(), clientForm.getEmail());
        return clientRepository.save(client);
    }

    /*@RequestMapping(value = "/employees", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }


    @RequestMapping(value = "/employees", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }

    @RequestMapping(value = "/employee/{empId}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee getEmployee(@PathVariable("empId") Long empId) {
        return employeeDAO.getEmployee(empId);
    }

    @RequestMapping(value = "/employee", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee addEmployee(@RequestBody EmployeeForm empForm) {

        System.out.println("(Service Side) Creating employee with empNo: " + empForm.getEmpNo());

        return employeeDAO.addEmployee(empForm);
    }

    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee updateEmployee(@RequestBody EmployeeForm empForm) {

        System.out.println("(Service Side) Editing employee with Id: " + empForm.getEmpId());

        return employeeDAO.updateEmployee(empForm);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empId}
    @RequestMapping(value = "/employee/{empId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("empId") Long empId) {

        System.out.println("(Service Side) Deleting employee with Id: " + empId);

        employeeDAO.deleteEmployee(empId);
    }*/

}
