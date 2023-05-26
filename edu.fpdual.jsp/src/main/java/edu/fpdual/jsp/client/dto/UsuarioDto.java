package edu.fpdual.jsp.client.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
public class UsuarioDto implements Comparable<UsuarioDto> {

    private String id;
    private String nombre;
    private String correo;
    private String password;
    private int puntos;


    @Override
    public int compareTo(UsuarioDto user) {
        return Integer.compare(this.puntos, user.getPuntos());
    }
}

