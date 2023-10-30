package es.aitor.com.aitor.Controladores;

import es.aitor.com.aitor.Entidades.Persona;
import es.aitor.com.aitor.Entidades.Usuario;
import es.aitor.com.aitor.Servicios.PersonasServices;
import es.aitor.com.aitor.Servicios.UsuariosServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@Controller

public class MainController {
    /**
     * Esto es importante
     */
    private  final PersonasServices  servicio;
    private  final UsuariosServices usuariosServices;



    /**
    @GetMapping({"/","inicio"})
    public  String inicio (Model model ){

        model.addAttribute("usuario",new Usuario());

        return "index";
    }*/


    @GetMapping( "/inicio")
    public  String inicio (){

        return "index";
    }


    @PostMapping("/sesion")
    public String inicioSesion(@ModelAttribute Usuario usuario, Model model) {
        if (usuario.getNombre().isEmpty() ) {
            model.addAttribute("errorNombre", "Credenciales inválidas");
        }

        if (usuario.getApellido().isEmpty()){
            model.addAttribute("errorApellido", "Credenciales inválidas");
        }
        if (usuario.getCorreo().isEmpty()){
            model.addAttribute("errorCorreo","Credenciales invalidas");
        }
        if (usuario.getContrasena().isEmpty()){
            model.addAttribute("errorContra","Credenciales invalidas");
        }

        if (model.containsAttribute("errorNombre") || model.containsAttribute("errorApellido") || model.containsAttribute("errorCorreo") || model.containsAttribute("errorContra") ) {

            return "index";
        } else {
            if (usuariosServices.validarusuario(usuario.getNombre(),usuario.getContrasena(), usuario.getCorreo())==true){
                return "redirect:/figuras/lista";
            } else {
                model.addAttribute("errorValidacion","Credenciales invalidas");
                return "index";
            }

        }
    }

    @GetMapping("figuras/lista")
    public  String lista( Model model ){
        model.addAttribute("listaFigura",servicio.findAll());
        return "lista";
    }





    @GetMapping("/registro")
    public  String registro() {
        log.info(" estoy ne el get mapping");

        return "registro";
    }

    @PostMapping("/registro/usuario")
    public String registrarUsuario(@ModelAttribute Usuario usuario, Model model) {
        if (usuario.getNombre().isEmpty() ) {
            model.addAttribute("errorNombre", "Credenciales inválidas");
        }

        if (usuario.getApellido().isEmpty()){
            model.addAttribute("errorApellido", "Credenciales inválidas");
        }
        if (usuario.getCorreo().isEmpty()){
            model.addAttribute("errorCorreo","Credenciales invalidas");
        }
        if (usuario.getContrasena().isEmpty()){
            model.addAttribute("errorContra","Credenciales invalidas");
        }

        if (model.containsAttribute("errorNombre") || model.containsAttribute("errorApellido") || model.containsAttribute("errorCorreo") || model.containsAttribute("errorContra") ) {

            return "registro";
        } else {
            //usuariosServices.addUser(usuario);
            usuariosServices.registarUSuario(usuario);
            return "index";
        }
    }





    @GetMapping("/pago")
    public String pagoo(){

        return "tarjeta";
    }

    @PostMapping("/pago")
    public  String pago(){

        return "redirect:/tarjeta";
    }



    @GetMapping("figuras/new")
    public String nuevaFigura(Model model) {
        log.info("Estoy en nuevaFigura");
        model.addAttribute("figuraDTO", new Persona()); // Cambio el nombre del objeto en el modelo
        return "agregarCosas";
    }

    @PostMapping("figuras/new/submit") // Cambio la URL de la anotación
    public String nuevaMascotaSubmit(@ModelAttribute("figuraDTO") Persona nuevaPersona) {
        servicio.add(nuevaPersona);
        return "redirect:/figuras/lista";
    }


    /**
     *Echar un ojo
     * @param id
     * @param moddel
     * @return
     */
    @GetMapping("figuras/edit/{id}")
    public  String editar(@PathVariable Long id , Model moddel){
        Persona persona = servicio.findById(id);
        if (persona!=null){

            moddel.addAttribute("figuraDTO",persona);
            moddel.addAttribute("modoEdicion",true);
            return "/agregarCosas";
        }else {

            // el fallo puede estar aqui de por qu ete crea cosas nuevas
            return "redirect:/figuras/new";
        }


    }

    /**
     * Echar un ojo
     * @param persona
     * @param bindingResult
     * @param model
     * @return
     */

    @PostMapping("figuras/edit/sudmit")
    public String editarSudbmit( @Valid @ModelAttribute ("figurasDTO")Persona persona,BindingResult bindingResult , Model model ){
        if (bindingResult.hasErrors()){
            model.addAttribute("modoEdicion",true);
            return "/agregarCosas";

        }else {

            servicio.edit(persona);
            // o aqui puede estar el fallo de que te cree cosas nuevas
            return "redirect:/figuras/lista";
        }


    }

    /**
     * Esto funciona bien
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/figuras/delete/{id}")
    public String borrarMascota(@PathVariable("id") Long id, Model model) {

        Persona persona = servicio.findById(id);
        if (persona != null)
            servicio.delete(persona);

        return "redirect:/figuras/lista";
    }

    @GetMapping("/caracteristicas")
    public  String caracteristicas (){
        return "caracteristicas";


    }

    @PostMapping("/caracteristicas")
    public  String caracteristica(){


        return "redirect:/figuras/lista";
    }

    @GetMapping("/pagoEnca")
    public  String encabezadoPago(){

        return  "precios";

    }
    @PostMapping("/pago/figura")
    public  String encabezadoPrecio(){


        return"redirect:/figuras/lista";
    }

    @GetMapping("/encabezado")
    public  String Encabezado(){

        return "acercaEnca";

    }

    @PostMapping("/encabezadoPost")
    public  String encabezadoPost(){
        return "redirect:/figuras/lista";

    }

    @GetMapping("/creador")
    public  String creador (){


        return "creador";
    }
    @PostMapping("/inicio/fifura")
    public  String volver(){

        return "redirect:/figuras/lista";
    }

    @GetMapping("/faqs")
    public  String factos(){


        return "faqs";
    }

    @PostMapping("/exit/faqs")
    public  String volverAtras(){


        return "redirect:/figuras/lista";
    }

}




