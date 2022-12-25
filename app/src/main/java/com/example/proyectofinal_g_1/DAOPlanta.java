package com.example.proyectofinal_g_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DAOPlanta extends SQLiteOpenHelper {

    // Nombre de la base y de la tabla

    private static final String base = "PLANTAS";
    private static final int version = 1;
    private static final String tabla = "PLANTA";

    // Declaración de las columnas

    private static final String id_planta = "ID_PLANTA";
    private static final String nom_planta="NOM_PLANTA";
    private static final String com_planta="COM_PLANTA";
    private static final String dia_planta="DIA_PLANTA";
    private static final String etapa_planta="ETAPA_PLANTA";

    // Creacion de la base

    public DAOPlanta(Context context) { super(context, base, null, version); }

    //Creacion de la tabla

    @Override
    public void onCreate(SQLiteDatabase db){

        String SQL="CREATE TABLE " + tabla + "(" + id_planta + " INTEGER PRIMARY KEY AUTOINCREMENT, " + nom_planta + " text, " + com_planta + " text, " + dia_planta + " text, " + etapa_planta + " text)";
        db.execSQL(SQL);

    }

    // Elimina la tabla si hay una existente para evitar duplicaciones

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ tabla);
        onCreate(db);

    }

    // Ingresar un registro

    public void nuevaPlanta(Planta objP){

        // Obtener valores del objeto Planta

        ContentValues values = new ContentValues();
        values.put(nom_planta,objP.getNombre());
        values.put(com_planta,objP.getComentario());
        values.put(dia_planta,objP.getDia());
        values.put(etapa_planta,objP.getEtapa());

        // Ingresar datos en la base de datos

        SQLiteDatabase db = getWritableDatabase();
        db.insert(tabla,null, values);
        db.close();
    }

    // Listar los registros de la tabla

    public ArrayList listaPlantas(){
        ArrayList listaPlantas = new ArrayList();
        SQLiteDatabase db = getWritableDatabase();

        String SQL="SELECT * FROM " + tabla;
        Cursor c = db.rawQuery(SQL, null);

        if(c.moveToFirst()){
            do{
                // Enlista una fila
                listaPlantas.add(c.getInt(0) + "-" + c.getString(1) + "-" + c.getString(2) + "-" + c.getString(3) + "-" + c.getString(4));
            }while (c.moveToNext());
        }

        return listaPlantas;
    }

    // Modificar un registro de la tabla

    public void actualizaPlantas(Planta objP){

        // Obtener valores del objeto Planta

        String id = String.valueOf(objP.getId_planta());
        String nom = objP.getNombre();
        String com = objP.getComentario();
        String dia = objP.getDia();
        String etapa = objP.getEtapa();

        // Modificar el registro cuyo ID coincida con el ingresado

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE " + tabla + " SET NOM_PLANTA='" + nom + "', COM_PLANTA='"+ com + "', DIA_PLANTA='"+ dia + "', ETAPA_PLANTA='" + etapa + "' WHERE ID_PLANTA='" + id + "'");
        db.close();
    }

    // Eliminar un registro de la tabla

    public void eliminarPlanta(Planta objP){

        int id = objP.getId_planta();

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM " + tabla + " WHERE ID_PLANTA="+ id);
        db.close();
    }
}
