package com.example.proyectofinal_g_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListadoPlantas extends AppCompatActivity {

    DAOPlanta objDAO = new DAOPlanta(this);
    ListView lvPlantas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_plantas);

        // Asignar valores al ListView

        lvPlantas = (ListView) findViewById(R.id.lvPlantas);
        lvPlantas.setAdapter(new Adaptador(this, objDAO.listaPlantas()));

        // Método onClick para los items

        lvPlantas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Guardar en variables los valores de la base de datos del item seleccionado

                int id_planta = Integer.parseInt(objDAO.listaPlantas().get(position).toString().split("-")[0]);
                String nombre = objDAO.listaPlantas().get(position).toString().split("-")[1];
                String comentario = objDAO.listaPlantas().get(position).toString().split("-")[2];
                String dia = objDAO.listaPlantas().get(position).toString().split("-")[3];
                String etapa = objDAO.listaPlantas().get(position).toString().split("-")[4];

                // Exportarlos a otra activity mediante un intent

                Intent intent = new Intent(ListadoPlantas.this, VerPlanta.class);
                intent.putExtra("id_planta", id_planta);
                intent.putExtra("nombre", nombre);
                intent.putExtra("comentario", comentario);
                intent.putExtra("dia", dia);
                intent.putExtra("etapa", etapa);
                startActivity(intent);

            }
        });

    }

    public void registrarPlanta(View view){
        startActivity(new Intent(ListadoPlantas.this, Registro_Plantas.class));
    }
}