package com.cibertec.simulacropc03.api;

import com.cibertec.simulacropc03.entity.Editorial;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EditorialApi {

    @GET("libro/TipoLibro/{param}")
    public Call<List<Libro>> listaPorNombre(@Path("param") String filtro);
}
