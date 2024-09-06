package com.example.simulado.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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

            Cursor cursor = altera.rawQuery(sql, new String[]{email, senha});

            if (cursor.moveToNext()) {

                very = true;

            }

            cursor.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return very;
    }

}
