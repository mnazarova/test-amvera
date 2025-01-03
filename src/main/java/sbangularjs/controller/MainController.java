package sbangularjs.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sbangularjs.model.User;

@Controller
public class MainController {

    @RequestMapping("/")
    public String welcome() {
        return "home";//"index";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "message", required = false) String message, Model model) {
        model.addAttribute("message", null);
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }

    @RequestMapping("/privateAccount")
    public String privateAccount(@AuthenticationPrincipal User user,
                                 @RequestParam(name = "message", required = false) String message_privateAccount,
                                 Model model) {
        model.addAttribute("message_privateAccount", user.getUsername());
        return "privateAccount";
    }

    @RequestMapping("/medicine")
    public String medicine() {
//        model.addAttribute("message_medicine", user.getUsername());
        return "medicine";
    }

    /*@RequestMapping("/medicine/{medicineId}")
    public String medicineWithId(@PathVariable Medicine medicine, Model model) {
        model.addAttribute("med", medicine);
        return "medicine";
    }*/

    @RequestMapping("/{medicineId}")
    public String transitionMedicineId(@PathVariable Integer medicineId, Model model) {
        model.addAttribute("med", medicineId);
//        medicine();
        return "medicine";
//        return "redirect:/medicine";
    }

    @RequestMapping("/orders")
    public String orders() {
        return "orders";
    }


    @RequestMapping("/search")
    public String search() {
        return "search";
    }

    @RequestMapping("/basket")
    public String basket() {
        return "basket";
    }

    @RequestMapping("/help")
    public String help() {
        return "help";
    }


}