package es.aitor.com.aitor.Entidades;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @NotNull( message = "el tipo debe ir rellenado")
    private String url;

    @NotNull( message = "el tipo debe ir rellenado")
    private  String nombre;

    @NotNull( message = "el tipo debe ir rellenado")
    private  String arco;
    @Min(value=1, message = "{mascota.id.mayorquecero}")
    private  Long id;
    private int precio;
    private  String des;
}
