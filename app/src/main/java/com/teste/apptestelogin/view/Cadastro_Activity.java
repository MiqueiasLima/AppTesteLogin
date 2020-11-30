package com.teste.apptestelogin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.teste.apptestelogin.R;
import com.teste.apptestelogin.api.ApiUtil;
import com.teste.apptestelogin.controller.Cadastro_Controller;
import com.teste.apptestelogin.model.Cliente;

public class Cadastro_Activity extends AppCompatActivity {

    EditText txtHintPrimeiroNome;
    EditText txtHintSegundoNome;
    EditText txtHintEmailCadastro;
    EditText txtHintSenhaCadastro;
    EditText txtHintRepitaSenhaCadastro;
    Button btnSalvarCadastro;
    Button btnVoltarCadastro;

    Cadastro_Controller cadastroController;
    Cliente cliente;
    boolean isFormnularioOk = false;
    boolean isEmailOk = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_);

        initVariaveis();

        btnSalvarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormnularioOk = verificaCampos()) {

                    String email = txtHintEmailCadastro.getText().toString();

                    if (isEmailOk = verificaEmailBD(email)) {

                        cliente = new Cliente();

                        cliente.setPrimeiroNome(txtHintPrimeiroNome.getText().toString());
                        cliente.setSegundoNome(txtHintSegundoNome.getText().toString());
                        cliente.setEmail(txtHintEmailCadastro.getText().toString());
                        cliente.setSenha(ApiUtil.gerarMD5Hash(txtHintSenhaCadastro.getText().toString()));

                        cadastroController.incluir(cliente);
                        Toast.makeText(getApplicationContext(),"Cadastro Realizado, aguarde...",Toast.LENGTH_LONG).show();
                        redirecionamento();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email já Cadastrado",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        btnVoltarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Cadastro_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });
    }

    public void redirecionamento(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(Cadastro_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        },2500);


    }

    public boolean verificaEmailBD(String email) {

        return cadastroController.validarEmail(email);
    }

    public void initVariaveis() {

        txtHintPrimeiroNome = findViewById(R.id.txtHintPrimeiroNome);
        txtHintSegundoNome = findViewById(R.id.txtHintSegundoNome);
        txtHintEmailCadastro = findViewById(R.id.txtHintEmailCadastro);
        txtHintSenhaCadastro = findViewById(R.id.txtHintSenhaCadastro);
        txtHintRepitaSenhaCadastro = findViewById(R.id.txtHintRepitaSenhaCadastro_1);
        btnSalvarCadastro = findViewById(R.id.btnSalvarCadastro);
        btnVoltarCadastro = findViewById(R.id.btnVoltarCadastro);

        cadastroController = new Cadastro_Controller(this);
    }

    private boolean verificaCampos() {

        boolean retorno = true;
        String senha = txtHintSenhaCadastro.getText().toString();
        String repitaSenha = txtHintRepitaSenhaCadastro.getText().toString();

        if (TextUtils.isEmpty(txtHintPrimeiroNome.getText().toString())) {
            txtHintPrimeiroNome.setError("Campo Inválido");
            txtHintPrimeiroNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(txtHintSegundoNome.getText().toString())) {
            txtHintSegundoNome.setError("Campo Inválido");
            txtHintSegundoNome.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(txtHintEmailCadastro.getText().toString())) {
            txtHintEmailCadastro.setError("Campo Inválido");
            txtHintEmailCadastro.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(txtHintSenhaCadastro.getText().toString())) {
            txtHintSenhaCadastro.setError("Campo Inválido");
            txtHintSenhaCadastro.requestFocus();
            retorno = false;
        }

        if (TextUtils.isEmpty(txtHintRepitaSenhaCadastro.getText().toString())) {
            txtHintRepitaSenhaCadastro.setError("Campo Inválido");
            txtHintRepitaSenhaCadastro.requestFocus();
            retorno = false;
        }
        if (!senha.equals(repitaSenha)) {
            txtHintRepitaSenhaCadastro.setError("Senhas Incompatíveis");
            txtHintRepitaSenhaCadastro.requestFocus();
            retorno = false;
        }


        return retorno;
    }

}