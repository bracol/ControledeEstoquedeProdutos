package com.example.administrador.controledeestoquedeprodutos.controllers.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrador.controledeestoquedeprodutos.R;
import com.example.administrador.controledeestoquedeprodutos.controllers.adapters.EstoqueListAdapter;
import com.example.administrador.controledeestoquedeprodutos.controllers.adapters.RecycleAdapter;
import com.example.administrador.controledeestoquedeprodutos.model.async.FindAllAsync;
import com.example.administrador.controledeestoquedeprodutos.model.async.FindWebProducts;
import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;
import com.example.administrador.controledeestoquedeprodutos.model.servicos.EstoqueBusinessServices;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 25/09/2015.
 */
public class EstoqueListActivity extends AppCompatActivity{

    private static final String PARAM_ESTOQUE = "ESTOQUE";
    private ListView listEstoque;
    private Estoque estoque;
    private RecyclerView myRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //anterior
        //setContentView(R.layout.activity_estoque_list);
        setContentView(R.layout.activity_product_list);
        bindList();
        //getTasksWEb();
    }



    @Override
    protected void onResume() {
        manipulaAsyncAtualizar();
        super.onResume();
    }

    public void manipulaAsyncAtualizar(){
            new FindAllAsync(){
                ProgressDialog dialog;
                @Override
                protected void onPreExecute() {
                    dialog = new ProgressDialog(EstoqueListActivity.this);
                    dialog.setMessage("Atualizando dados...");
                    dialog.show();
                    super.onPreExecute();
                }


                @Override
                protected void onPostExecute(List<Estoque> estoques) {
                    EstoqueListAdapter adapter = (EstoqueListAdapter) listEstoque.getAdapter();
                    adapter.setItens(estoques);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                    super.onPostExecute(estoques);
                }
            }.execute();
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
                        manipulaAsyncAtualizar();
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
        List<Estoque> values = new ArrayList<>();
         myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        myRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //mLayoutManager = new LinearLayoutManager(this);
        //myRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        RecycleAdapter adapter = new RecycleAdapter(values);
        myRecyclerView.setAdapter(adapter);
        /*listEstoque = (ListView) findViewById(R.id.listViewEstoque);
        registerForContextMenu(listEstoque);
        listEstoque.setAdapter(new EstoqueListAdapter(this, values));
        manipulaAsyncAtualizar();
        listEstoque.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EstoqueListAdapter adapter = (EstoqueListAdapter) listEstoque.getAdapter();
                estoque = adapter.getItem(position);
                return false;
            }
        });*/
    }

    public void getTasksWEb(){
        new FindWebProducts(){

            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(EstoqueListActivity.this);
                dialog.setMessage("Atualizando dados...");
                dialog.show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(List<Estoque> estoques) {
                for(Estoque e : estoques) {
                    EstoqueBusinessServices.save(e);
                }
                manipulaAsyncAtualizar();
                dialog.dismiss();
                super.onPostExecute(estoques);
            }
        }.execute();
    }



}
