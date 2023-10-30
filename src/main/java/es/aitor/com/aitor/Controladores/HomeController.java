package es.aitor.com.aitor.Controladores;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {

    @GetMapping({"","/index"})
    public  String bienvenida(){

        return "indexSeguridad";

    }

    @GetMapping("/login")
    public  String login (){

        log.info("paso login");
        return "loginSeguridad";
    }




    @PostMapping("/logout")
    public String logout() {
        log.info("paso por el cierre");
        return "redirect:/indexSeguridad";
    }

}
