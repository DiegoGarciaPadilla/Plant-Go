package com.example.proyectofinal_g_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registro_Plantas extends AppCompatActivity {

    // Declaracion de variables

    Button btRegistrar, btRegistrarCancelar;
    EditText etNombre, etComentario, etDiaPlantacion;
    Spinner spEtapa;
    DAOPlanta objDAO = new DAOPlanta(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__plantas);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etComentario = (EditText) findViewById(R.id.etComentario);
        etDiaPlantacion = (EditText) findViewById(R.id.etDiaPlantacion);
        spEtapa = (Spinner) findViewById(R.id.spEtapa);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.etapas, android.R.layout.simple_spinner_item);
        spEtapa.setAdapter(adaptador);

        btRegistrar = (Button) findViewById(R.id.btRegistrar);
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los datos ingresados a traves de los EditText y el Spinner y crea un nuevo registro en la base con ellos
                guardar(etNombre.getText().toString(), etComentario.getText().toString(), etDiaPlantacion.getText().toString(), spEtapa.getSelectedItem().toString());
                startActivity(new Intent(Registro_Plantas.this, ListadoPlantas.class));
            }
        });

        btRegistrarCancelar = (Button) findViewById(R.id.btRegistrarCancelar);
        btRegistrarCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Vuelve al listado
                startActivity(new Intent(Registro_Plantas.this, ListadoPlantas.class));
            }
        });

    }

    // Metodo que guarda los datos en un objeto Plantas y los ingresa en la base de datos

    public void guardar(String nombre, String comentario, String dia, String etapa){

        Planta objP = new Planta();
        objP.setNombre(nombre);
        objP.setComentario(comentario);
        objP.setDia(dia);
        objP.setEtapa(etapa);
        objDAO.nuevaPlanta(objP);

        if (objP != null) {
            Toast.makeText(this, "Planta registrada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }

    }

}