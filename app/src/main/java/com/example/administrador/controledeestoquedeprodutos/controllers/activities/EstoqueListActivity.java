package com.example.administrador.controledeestoquedeprodutos.controllers.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.controledeestoquedeprodutos.R;
import com.example.administrador.controledeestoquedeprodutos.controllers.adapters.EstoqueListAdapter;
import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;
import com.example.administrador.controledeestoquedeprodutos.model.servicos.EstoqueBusinessServices;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrador on 25/09/2015.
 */
public class EstoqueListActivity extends AppCompatActivity{

    private static final String PARAM_ESTOQUE = "ESTOQUE";
    private ListView listEstoque;
    private Estoque estoque;
    private List<Estoque> values;
    private static int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque_list);
        bindList();

    }



    @Override
    protected void onResume() {
        updateEstoqueList();
        super.onResume();
    }

    public List<Estoque> manipulaAsync(){
        try {
            values = new FindAllAsync(){
                ProgressDialog dialog;
                @Override
                protected void onPreExecute() {
                    //count++;
                    //Toast.makeText(EstoqueListActivity.this, String.valueOf(count), Toast.LENGTH_LONG).show();
                    dialog = ProgressDialog.show(EstoqueListActivity.this, "Por favor espere", "Executando comandos");
                    super.onPreExecute();
                }


                @Override
                protected void onPostExecute(List<Estoque> estoques) {
                    dialog.dismiss();
                    super.onPostExecute(estoques);
                }
                //pega valores do sync PostExecute.
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return values;
    }


    private void updateEstoqueList(){
        values = manipulaAsync();
        EstoqueListAdapter adapter = (EstoqueListAdapter) listEstoque.getAdapter();
        adapter.setItens(values);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_estoque_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuEstoqueAdd:
                onMenuAddClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {
        Intent goToEstoqueForm = new Intent(EstoqueListActivity.this, EstoqueFormActivity.class);
        startActivity(goToEstoqueForm);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_excluir:
                onMenuDeleteClick();
                break;
            case R.id.menu_editar:
                onMenuEditClick();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void onMenuEditClick() {
        Intent goToEstoqueForm = new Intent(EstoqueListActivity.this, EstoqueFormActivity.class);
        goToEstoqueForm.putExtra(EstoqueListActivity.PARAM_ESTOQUE, estoque);
        startActivity(goToEstoqueForm);
    }

    private void onMenuDeleteClick() {
        new AlertDialog.Builder(EstoqueListActivity.this)
                .setTitle(getString(R.string.lbl_confirm))
                .setMessage(getString(R.string.msg_delete))
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EstoqueBusinessServices.delete(estoque);
                        estoque = null;
                        String message = (getString(R.string.msg_delete_succesful));
                        updateEstoqueList();
                        Toast.makeText(EstoqueListActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                })
                .setNeutralButton(getString(R.string.lbl_no), null)
                .create()
                .show();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_estoque_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    private void bindList(){
        values = manipulaAsync();
        listEstoque = (ListView) findViewById(R.id.listViewEstoque);
        registerForContextMenu(listEstoque);
        listEstoque.setAdapter(new EstoqueListAdapter(this, values));
        listEstoque.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EstoqueListAdapter adapter = (EstoqueListAdapter) listEstoque.getAdapter();
                estoque = adapter.getItem(position);
                return false;
            }
        });
    }

}
