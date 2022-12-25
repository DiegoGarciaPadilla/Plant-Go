package com.example.proyectofinal_g_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerPlanta extends AppCompatActivity {

    // Declarar variables

    TextView tvTituloPlanta, tvDescripcionPlanta, tvFechaPlanta, tvEtapaPlanta;
    Button btModificarPlanta, btEliminarPlanta;
    DAOPlanta objDAO;

    int id_planta;
    String nombre, comentario, dia, etapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_planta);

        // Importar datos del intent

        Bundle b = getIntent().getExtras();
        if (b != null){
            id_planta = b.getInt("id_planta");
            nombre = b.getString("nombre");
            comentario = b.getString("comentario");
            dia = b.getString("dia");
            etapa = b.getString("etapa");
        }

        tvTituloPlanta = (TextView) findViewById(R.id.tvTituloPlanta);
        tvDescripcionPlanta = (TextView) findViewById(R.id.tvDescripcionPlanta);
        tvFechaPlanta = (TextView) findViewById(R.id.tvFechaPlanta);
        tvEtapaPlanta = (TextView) findViewById(R.id.tvEtapaPlanta);

        // Asignar los valores importados a los TextView

        tvTituloPlanta.setText(nombre);
        tvDescripcionPlanta.setText(comentario);
        tvFechaPlanta.setText(dia);
        tvEtapaPlanta.setText(etapa);

        btModificarPlanta = (Button) findViewById(R.id.btModificarPlanta);
        btModificarPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Exportar los valores a la activity de modificar mediante un intent

                Intent intent = new Intent(VerPlanta.this, Modifica_Plantas.class);
                intent.putExtra("id_planta", id_planta);
                intent.putExtra("nombre", nombre);
                intent.putExtra("comentario", comentario);
                intent.putExtra("dia", dia);
                intent.putExtra("etapa", etapa);
                startActivity(intent);

            }
        });

        btEliminarPlanta = (Button) findViewById(R.id.btEliminarPlanta);
        btEliminarPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Cuadro de dialogo que pide una confirmación antes de eliminar el registro

                AlertDialog.Builder builder = new AlertDialog.Builder(VerPlanta.this);
                builder.setTitle("Eliminar planta");
                builder.setMessage("Esta planta se eliminará permanentemente, ¿Desea continuar?");
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // En caso de que el usuario haya aceptado, dicho registro se eliminará
                        eliminar(id_planta);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    // Elimina el registro elegido y vuelve al listado

    public void eliminar(int id_planta){
        Planta objP = new Planta();
        objP.setId_planta(id_planta);

        new DAOPlanta(this).eliminarPlanta(objP);
        Toast.makeText(this, "Eliminado correctamente", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(VerPlanta.this, ListadoPlantas.class));
    }

    // Metodo para volver al listado

    public void regresarListado(View view){
        startActivity(new Intent(VerPlanta.this, ListadoPlantas.class));
    }
}