package com.example.administrador.controledeestoquedeprodutos.controllers.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.administrador.controledeestoquedeprodutos.R;
import com.example.administrador.controledeestoquedeprodutos.model.async.SaveAsync;
import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;


/**
 * Created by Administrador on 25/09/2015.
 */
public class EstoqueFormActivity extends AppCompatActivity {
    private static final String PARAM_ESTOQUE = "ESTOQUE";
    private EditText editTextName;
    private EditText editTextDesc;
    private EditText editTextQtd;
    private EditText editTextQtdMin;
    private EditText editTextValor;
    private Estoque estoque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque_form);

        initEstoque();
        bindEditTextName();
        bindEditTextDesc();
        bindEditTextQtd();
        bindEditTextQtdMin();
        bindEditTextValor();
    }

    private void initEstoque() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            estoque = getIntent().getExtras().getParcelable(PARAM_ESTOQUE);
        }
        estoque = estoque == null ? new Estoque() : estoque;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_estoque_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void manipulaAsyncSave(){
        new SaveAsync(){
            ProgressDialog dialog;
            @Override
            protected void onPreExecute() {
                dialog = new ProgressDialog(EstoqueFormActivity.this);
                dialog.setMessage("Salvando dados...");
                dialog.show();
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                dialog.dismiss();
                super.onPostExecute(aVoid);
            }
        }.execute(estoque);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuEstoqueOk:
                onMenuOk();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuOk() {
        binEstoque();
        manipulaAsyncSave();
        finish();
    }

    private void binEstoque() {
        estoque.setNome(editTextName.getText().toString().equals("") ? "" : editTextName.getText().toString());
        estoque.setImg("");
        estoque.setDescricao(editTextDesc.getText().toString().equals("") ? "" : editTextDesc.getText().toString());
        estoque.setQuantidade(editTextQtd.getText().toString().equals("") ? 0 : Long.parseLong(editTextQtd.getText().toString()));
        estoque.setQtdMinimaEstoque(editTextQtdMin.getText().toString().equals("") ? 0 : Long.parseLong(editTextQtdMin.getText().toString()));
        estoque.setValorUni(editTextValor.getText().toString().equals("") ? 0 : Double.parseDouble(editTextValor.getText().toString()));
    }


    private void bindEditTextValor() {
        editTextValor = (EditText) findViewById(R.id.editTextValor);
        editTextValor.setText(estoque.getValorUni() == null ? "" : estoque.getValorUni().toString());
    }

    private void bindEditTextQtdMin() {
        editTextQtdMin = (EditText) findViewById(R.id.editTextQtdMinima);
        editTextQtdMin.setText(estoque.getQtdMinimaEstoque() == null ? "" : estoque.getQtdMinimaEstoque().toString());
    }

    private void bindEditTextQtd() {
        editTextQtd = (EditText) findViewById(R.id.editTextQtd);
        editTextQtd.setText(estoque.getQuantidade() == null ? "" : estoque.getQuantidade().toString());

    }

    private void bindEditTextDesc() {
        editTextDesc = (EditText) findViewById(R.id.editTextDesc);
        editTextDesc.setText(estoque.getDescricao() == null ? "" : estoque.getDescricao());
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editTextNome);
        editTextName.setText(estoque.getNome() == null ? "" : estoque.getNome());
    }
}
