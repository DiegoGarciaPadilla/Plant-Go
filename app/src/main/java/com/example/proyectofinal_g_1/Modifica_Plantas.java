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

public class Modifica_Plantas extends AppCompatActivity {

    // Declaración de variables

    Button btModificar;
    EditText etID, etNombre, etComentario, etDiaPlantacion;
    Spinner spEtapa;
    DAOPlanta objDAO = new DAOPlanta(this);

    int id_planta;
    String nombre, comentario, dia, etapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica__plantas);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etComentario = (EditText) findViewById(R.id.etComentario);
        etDiaPlantacion = (EditText) findViewById(R.id.etDiaPlantacion);
        spEtapa = (Spinner) findViewById(R.id.spEtapa);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.etapas, android.R.layout.simple_spinner_item);
        spEtapa.setAdapter(adaptador);

        // Importar datos del intent

        Bundle b = getIntent().getExtras();
        if (b != null){
            id_planta = b.getInt("id_planta");
            nombre = b.getString("nombre");
            comentario = b.getString("comentario");
            dia = b.getString("dia");
            etapa = b.getString("etapa");
        }

        // Asignar los datos a los EditText

        etNombre.setText(nombre);
        etComentario.setText(comentario);
        etDiaPlantacion.setText(dia);

        btModificar = (Button) findViewById(R.id.btModificar);
        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Invoca el metodo modificar
                modificar(id_planta, etNombre.getText().toString(), etComentario.getText().toString(), etDiaPlantacion.getText().toString(), spEtapa.getSelectedItem().toString());
            }
        });

    }

    // Modifica el registro cuyo ID coincida con el ingresado

    public void modificar(int id_planta, String nombre, String comentario, String dia, String etapa){

        Planta objP = new Planta();
        objP.setId_planta(id_planta);
        objP.setNombre(nombre);
        objP.setComentario(comentario);
        objP.setDia(dia);
        objP.setEtapa(etapa);

        new DAOPlanta(this).actualizaPlantas(objP);
        Toast.makeText(this, "Actualizado correctamente", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(Modifica_Plantas.this, ListadoPlantas.class));
    }

    // Metodo para volver al listado

    public void cancelar(View view){
        startActivity(new Intent(Modifica_Plantas.this, ListadoPlantas.class));
    }

}