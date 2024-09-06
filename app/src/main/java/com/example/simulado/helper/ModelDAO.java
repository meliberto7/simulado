package com.example.simulado.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.simulado.model.Materias;

import java.util.ArrayList;
import java.util.List;

public class ModelDAO {

    private SQLiteDatabase altera, le;

    public ModelDAO(Context context) {

        DBHelper helper = new DBHelper(context);

        altera = helper.getWritableDatabase();
        le = helper.getReadableDatabase();

    }

    public boolean logar(String email, String senha) {

        boolean very = false;

        try{

            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

            Cursor cursor = le.rawQuery(sql, new String[]{email, senha});

            if (cursor.moveToNext()) {

                very = true;

            }

            cursor.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return very;
    }

    public List<Materias> listar() {

        List<Materias> list = new ArrayList<>();

        try{

            String sql = "SELECT * FROM materias";

            Cursor cursor = le.rawQuery(sql, null);

            while (cursor.moveToNext()) {

                Materias materias = new Materias();

                materias.setId_materia(cursor.getInt(cursor.getColumnIndex("id_usuario")));
                materias.setNome(cursor.getString(cursor.getColumnIndex("nome")));

                list.add(materias);

            }

            cursor.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return list;
    }

}
