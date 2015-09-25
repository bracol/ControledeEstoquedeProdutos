package com.example.administrador.controledeestoquedeprodutos.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;

import java.util.List;

/**
 * Created by Administrador on 25/09/2015.
 */
public class EstoqueRepository {

    private EstoqueRepository() {
        super();
    }

    public static void save(Estoque estoque) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = EstoqueContract.getContentValues(estoque);
        if (estoque.getId() == null) {
            db.insert(EstoqueContract.TABLE, null, values);
        } else {
            String where = EstoqueContract.ID + " = ? ";
            String[] params = {estoque.getId().toString()};
            db.update(EstoqueContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static void delete(long id) {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String where = EstoqueContract.ID + " = ? ";
        String[] params = {String.valueOf(id)};
        db.delete(EstoqueContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static List<Estoque> getAll() {
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        /* ResultSet do Android */
        Cursor cursor = db.query(EstoqueContract.TABLE, EstoqueContract.COLUMNS, null, null, null, null, EstoqueContract.ID);

        List<Estoque> values = EstoqueContract.getEstoques(cursor);

        db.close();
        databaseHelper.close();

        return values;
    }
}
