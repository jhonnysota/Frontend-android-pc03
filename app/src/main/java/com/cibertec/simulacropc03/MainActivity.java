package com.cibertec.simulacropc03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.cibertec.simulacropc03.api.EditorialApi;
import com.cibertec.simulacropc03.connection.ConnectionApi;
import com.cibertec.simulacropc03.entity.Libro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    Button btnFiltrar;
    ListView lstData;
    EditorialApi api;

    ArrayAdapter<String> adapter;
    ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        btnFiltrar = (Button) findViewById(R.id.btnFiltrar);
        lstData = (ListView) findViewById(R.id.lstData);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lstData.setAdapter(adapter);

        api = ConnectionApi.getConnection().create(EditorialApi.class);

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filtro = txtNombre.getText().toString();
                lista(filtro);
            }
        });
    }

    public void lista(String filtro){
        mensaje("En la consulta");
        Call<List<Libro>>  call = api.listaPorNombre(filtro);
        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                   mensaje("-> onResponse");
                   if(response.isSuccessful()){
                       List<Libro> salida =  response.body();
                       String item = null;

                       data.clear();

                       for(Editorial x : salida){
                           item = x.getidLibro() + " - " + x.getcodigo();
                           data.add(item);
                       }
                       adapter.notifyDataSetChanged();

                   }else{
                       mensaje("ERROR en onResponse");
                   }
            }
          @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
              mensaje("-> onFailure");
                mensaje("ERROR" +   t.getMessage());
            }
        });
    }

    void mensaje(String msg){
        Toast toast1 =  Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG);
        toast1.show();
    }
}