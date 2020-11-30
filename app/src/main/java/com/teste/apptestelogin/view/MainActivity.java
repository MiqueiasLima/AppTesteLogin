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
import com.teste.apptestelogin.api.AppDataBase;
import com.teste.apptestelogin.controller.Login_Controller;

public class MainActivity extends AppCompatActivity {

    AppDataBase dataBase;

    EditText txtEmail;
    EditText txtSenha;
    Button btnAcessar;
    Button btnCadastrar;

    boolean isFormularioOk = false;
    boolean isAutenticavel = false;
    Login_Controller loginController;
    public String email;
    public String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariaveis();
        dataBase = new AppDataBase(this);

        btnAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isFormularioOk = verificaCampos()){

                     email = txtEmail.getText().toString();
                     senha = ApiUtil.gerarMD5Hash(txtSenha.getText().toString());


                    if(isAutenticavel = autenticaCliente(email,senha)){

                        Toast.makeText(getApplicationContext(),"Autenticando, aguarde...",Toast.LENGTH_LONG).show();
                        redirecionamento();
                        //Intent intent = new Intent(MainActivity.this,Acesso_Activity.class);
                        //startActivity(intent);
                        //finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email ou senha Inválidos",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Campos Inválidos",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Cadastro_Activity.class);
                startActivity(intent);

            }
        });

    }

    public void redirecionamento(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, Acesso_Activity.class);
                startActivity(intent);
                finish();

            }
        },2200);

    }

    public boolean autenticaCliente(String email, String senha) {

        return loginController.autenticacao(email,senha);

    }

    public void initVariaveis(){

        txtEmail = findViewById(R.id.txtHintEmail);
        txtSenha = findViewById(R.id.txtHintSenha);
        btnAcessar = findViewById(R.id.btnAcessar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        loginController = new Login_Controller(getApplicationContext());

    }

    private boolean verificaCampos(){

        boolean retorno = true;

        if(TextUtils.isEmpty(txtEmail.getText().toString())){
            txtEmail.setError("Campo Vazio");
            txtEmail.requestFocus();
            retorno = false;
        }
        if(TextUtils.isEmpty(txtSenha.getText().toString())){
            txtSenha.setError("Campo Vazio");
            txtSenha.requestFocus();
            retorno = false;
        }

        return retorno;
    }
}