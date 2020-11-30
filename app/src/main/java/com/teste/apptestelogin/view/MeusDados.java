package com.teste.apptestelogin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.teste.apptestelogin.R;
import com.teste.apptestelogin.controller.Cadastro_Controller;
import com.teste.apptestelogin.controller.Login_Controller;
import com.teste.apptestelogin.model.Cliente;

public class MeusDados extends AppCompatActivity {

    EditText txtHintPrimeiroNome;
    EditText txtHintSegundoNome;
    EditText txtHintEmailCadastro;
    EditText txtHintSenhaCadastro;
    Button btnVoltarMeusDados;
    Login_Controller loginController;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_dados);

        initFormulario();
        cliente = loginController.getClienteById();
        txtHintPrimeiroNome.setText(cliente.getPrimeiroNome());
        txtHintSegundoNome.setText(cliente.getSegundoNome());
        txtHintEmailCadastro.setText(cliente.getEmail());
        txtHintSenhaCadastro.setText(cliente.getSenha());

    }

    private void initFormulario() {

        txtHintPrimeiroNome = findViewById(R.id.txtHintPrimeiroNome);
        txtHintSegundoNome = findViewById(R.id.txtHintSegundoNome);
        txtHintEmailCadastro = findViewById(R.id.txtHintEmailCadastro);
        txtHintSenhaCadastro = findViewById(R.id.txtHintSenhaCadastro);
        loginController = new Login_Controller(getApplicationContext());
    }

    public void voltarMeusDados(View view) {

        Intent intent = new Intent(MeusDados.this,Acesso_Activity.class);
        startActivity(intent);

    }
}