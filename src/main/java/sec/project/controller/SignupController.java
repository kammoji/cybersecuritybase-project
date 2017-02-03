package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(Model model, @RequestParam String name, @RequestParam String address) {
        if (name.equals("") || address.equals("")){
            return "error";
        }
        signupRepository.save(new Signup(name, address));
        model.addAttribute("userdata", signupRepository.findByName(name));
        return "done";
    }
    
    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public String getParticipants(Model model){
        model.addAttribute("signups", signupRepository.findAll());
        return "participants";
    }
    
    @RequestMapping(value = "/done", method = RequestMethod.POST)
    public String backHomeFromDone(){
        return "form";
    }
    
    @RequestMapping(value = "/participants", method = RequestMethod.POST)
    public String backHome(){
        return "form";
    }
    


}
