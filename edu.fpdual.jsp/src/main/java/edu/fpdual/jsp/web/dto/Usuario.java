package edu.fpdual.jsp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

  private String id;
  private String usuario;
  private String correo;
  private String password;
}
