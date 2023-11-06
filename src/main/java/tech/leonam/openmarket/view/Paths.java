package tech.leonam.openmarket.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Paths {

    @GetMapping("/aaa")
    public String index(){
        return "index";
    }

}
