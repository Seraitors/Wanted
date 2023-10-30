package es.aitor.com.aitor.Servicios;

import es.aitor.com.aitor.Entidades.Persona;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class PersonasServices {

    private List<Persona> personas = new ArrayList<>();

    public List<Persona> findAll() {
        return personas;
    }

    public  Persona add(Persona p){
        personas.add(p);
        return p;
    }



    public Persona findById (Long id ){



        Persona persona =personas.stream()
                .filter(m-> id.equals(m.getId()))
                .findAny()
                .orElse(null);
        return  persona;
    }

    public  Persona edit (Persona p){

        log.info("Editar figura", p);
        Persona persona= findById(p.getId());
        log.info("Figura encontrada",p);
        if (persona!=null){
            personas.set(personas.indexOf(persona),p);
        }else {
            personas.add(p);

        }

        return p;
    }

    public void delete(Persona p) {
        personas.remove(p);
    }


    @PostConstruct
    public  void init(){

        personas.addAll(
                Arrays.asList(
                        Persona.builder()
                                .url("/Imagen/descarga.jpeg")
                                .nombre("Lufiie")
                                .arco("Wano")
                                .id(10L)
                                .precio(90)
                                .des("Figura de luffie de cuando llego a wano")
                                .build(),

                        Persona.builder()
                                .url("/Imagen/zoro.jpeg")
                                .nombre("Zoro")
                                .arco("Wano")
                                .id(305L)
                                .precio(100)
                                .des("Figura de zoro cuando se enfrento al villado de las espadas")
                                .build(),

                        Persona.builder()
                                .url("/Imagen/sanji.jpeg")
                                .nombre("Sanji")
                                .arco("Wano")
                                .id(3805L)
                                .precio(150)
                                .des("Figura de sanji salida de las termas ")
                                .build(),
                        Persona.builder()
                                .url("/Imagen/kaido.jpg")
                                .nombre( "Kaido" )
                                .arco("wano"  )
                                .id(23L)
                                .precio(100)
                                .des("Figura de kaido antes de pelear contra luffie")
                                .build()



                ));




    }

}
