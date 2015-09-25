package com.example.administrador.controledeestoquedeprodutos.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.administrador.controledeestoquedeprodutos.R;
import com.example.administrador.controledeestoquedeprodutos.model.entidade.Estoque;
import com.example.administrador.controledeestoquedeprodutos.model.servicos.EstoqueBusinessServices;


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
        EstoqueBusinessServices.save(estoque);
        finish();
    }

    private void binEstoque() {
        estoque.setNome(editTextName.getText().toString());
        estoque.setImg(1);
        estoque.setDescricao(editTextDesc.getText().toString());
        estoque.setQuantidade(Integer.parseInt(editTextQtd.getText().toString()));
        estoque.setQtdMinimaEstoque(Integer.parseInt(editTextQtdMin.getText().toString()));
        estoque.setValorUni(Double.parseDouble(editTextValor.getText().toString()));
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
