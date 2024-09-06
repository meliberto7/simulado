package com.example.simulado.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DBNOME = "aprendaS";
    private static final int VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBNOME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE usuarios(" +
                "id_usuario int primary key autoincrement," +
                "nome varchar(100)," +
                "cidade varchar(50)," +
                "curso varchar(100)," +
                "email varchar(100)," +
                "senha varchar(100))";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS usuarios";

        db.execSQL(sql);
        onCreate(db);

    }
}
