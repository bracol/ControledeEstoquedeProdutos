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
        if (estoque.get_id() == null) {
            db.insert(EstoqueContract.TABLE, null, values);
        } else {
            String where = EstoqueContract.ID + " = ? ";
            String[] params = {estoque.get_id().toString()};
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

    public static Integer getIdByWebId(Long webId){
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = EstoqueContract.WEB_ID + " = ?";
        String[] params = {String.valueOf(webId)};
        Cursor cursor = db.query(EstoqueContract.TABLE, EstoqueContract.COLUMNS, where, params, null, null, null);

        if (cursor.getCount() > 0){
            Estoque estoque= EstoqueContract.getEstoque(cursor);
            return estoque.get_id();
        }
        return 0;

    }
}
