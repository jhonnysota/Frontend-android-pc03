package com.cibertec.simulacropc03.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    private int idLibro;
    private String codigo;
    private String estado;
    private String tipo;
    private String titulo;
    private TipoLibro tipoLibro;

}
