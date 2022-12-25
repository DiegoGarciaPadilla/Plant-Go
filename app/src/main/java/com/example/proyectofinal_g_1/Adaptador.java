package com.example.proyectofinal_g_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    ArrayList datos;

    public Adaptador (Context context, ArrayList datos){
        // Inicializa las variables
        this.context = context;
        this.datos = datos;
        inflater = (LayoutInflater) context. getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return datos.size(); }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.item, null);

        // Obtiene los TextView de la actividad item.xml

        TextView tvTituloItem = (TextView) vista.findViewById(R.id.tvTituloItem);
        TextView tvDescripcionItem = (TextView) vista.findViewById(R.id.tvDescripcionItem);
        TextView tvDiaItem = (TextView) vista.findViewById(R.id.tvDiaItem);

        // Divide en varias partes cada item de un arraylist y los guarda en variables

        int id_planta = Integer.parseInt(datos.get(position).toString().split("-")[0]);
        String nombre = datos.get(position).toString().split("-")[1];
        String comentario = datos.get(position).toString().split("-")[2];
        String dia = datos.get(position).toString().split("-")[3];
        String etapa = datos.get(position).toString().split("-")[4];

        // Asigna los valores necesarios en los TextView

        tvTituloItem.setText(nombre);
        tvDescripcionItem.setText(comentario);
        tvDiaItem.setText(dia);

        return vista;

    }
}
