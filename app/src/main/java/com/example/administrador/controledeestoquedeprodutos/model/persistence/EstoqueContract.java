package com.example.administrador.controledeestoquedeprodutos.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 25/09/2015.
 */
public class EstoqueContract {
    public static final String TABLE = "estoque";
    public static final String ID = "id";
    public static final String WEB_ID = "web_id";
    public static final String IMG = "img";
    public static final String NOME = "nome";
    public static final String DESC = "descricao";
    public static final String QTD = "quantidade";
    public static final String QTD_MINIMA = "quantidade_minima";
    public static final String VALOR = "valor";
    public static final String DATA = "data";

    public static final String[] COLUMNS = {ID, WEB_ID, IMG, NOME, DESC, QTD, QTD_MINIMA, VALOR, DATA};

    private EstoqueContract(){super();}

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append("( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(WEB_ID + " INTEGER, ");
        create.append(IMG + " INTEGER, ");
        create.append(NOME + " TEXT, ");
        create.append(DESC + " TEXT, ");
        create.append(QTD + " INTEGER, ");
        create.append(QTD_MINIMA + " INTEGER ,");
        create.append(VALOR + " REAL, ");
        create.append(DATA + " INTEGER ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Estoque estoque) {
        ContentValues values = new ContentValues();
        values.put(EstoqueContract.ID, estoque.get_id());
        values.put(EstoqueContract.WEB_ID, estoque.getWeb_id());
        values.put(EstoqueContract.IMG, estoque.getImg());
        values.put(EstoqueContract.NOME, estoque.getNome());
        values.put(EstoqueContract.DESC, estoque.getDescricao());
        values.put(EstoqueContract.QTD, estoque.getQuantidade());
        values.put(EstoqueContract.QTD_MINIMA, estoque.getQtdMinimaEstoque());
        values.put(EstoqueContract.VALOR, estoque.getValorUni());
        values.put(EstoqueContract.DATA, estoque.getDate());
        return values;
    }

    public static Estoque getEstoque(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Estoque estoque = new Estoque();
            /* get column index pega o indice de acordo com o nome da coluna passado */
            estoque.set_id(cursor.getInt(cursor.getColumnIndex(EstoqueContract.ID)));
            estoque.setWeb_id(cursor.getLong(cursor.getColumnIndex(EstoqueContract.WEB_ID)));
            estoque.setImg(cursor.getString(cursor.getColumnIndex(EstoqueContract.IMG)));
            estoque.setNome(cursor.getString(cursor.getColumnIndex(EstoqueContract.NOME)));
            estoque.setDescricao(cursor.getString(cursor.getColumnIndex(EstoqueContract.DESC)));
            estoque.setQuantidade(cursor.getLong(cursor.getColumnIndex(EstoqueContract.QTD)));
            estoque.setQtdMinimaEstoque(cursor.getLong(cursor.getColumnIndex(EstoqueContract.QTD_MINIMA)));
            estoque.setValorUni(cursor.getDouble(cursor.getColumnIndex(EstoqueContract.VALOR)));
            estoque.setDate(cursor.getLong(cursor.getColumnIndex(EstoqueContract.DATA)));
            return estoque;
        }
        return null;
    }


    public static List<Estoque> getEstoques(Cursor cursor) {
        ArrayList<Estoque> estoques = new ArrayList<>();
        while (cursor.moveToNext()) {
            /* get colum index pega o indice de acordo com o nome da coluna passado */
            estoques.add(getEstoque(cursor));

        }
        return estoques;
    }






}
