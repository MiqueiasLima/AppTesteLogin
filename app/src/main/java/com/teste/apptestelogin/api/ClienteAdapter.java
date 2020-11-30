package com.teste.apptestelogin.api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.teste.apptestelogin.R;
import com.teste.apptestelogin.model.Cliente;

import java.util.List;


public class ClienteAdapter  extends RecyclerView.Adapter<ClienteAdapter.ViewHolder>{

    private List<Cliente> aClientes; // Lista de Clientes do Adapter - aClientes
    private Context aContext;

    public ClienteAdapter(List<Cliente> aClientes, Context aContext) {
        this.aClientes = aClientes;
        this.aContext = aContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View linhaView = inflater.inflate(R.layout.linha_detalhe_consultar_clientes, parent, false);

        ViewHolder viewHolder = new ViewHolder(linhaView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolder holder, int position) {


        Cliente objDaLinha = aClientes.get(position);

        TextView txtPrimeiroNome = holder.rvPrimeiroNome;
        txtPrimeiroNome.setText(objDaLinha.getPrimeiroNome());

        TextView rvEmail = holder.rvEmail;
        rvEmail.setText(objDaLinha.getEmail());


    }

    @Override
    public int getItemCount() {
        return aClientes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public Button rvPessoaFisica;
        public TextView rvPrimeiroNome;
        public TextView rvEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rvPrimeiroNome = itemView.findViewById(R.id.rvPrimeiroNome);
            rvEmail = itemView.findViewById(R.id.rvEmail);

            Log.i(AppDataBase.LOG_APP,"Nome: "+rvPrimeiroNome.getText().toString());

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            Cliente clienteSelecionado = aClientes.get(position);

            if(position != RecyclerView.NO_POSITION){

                Log.i(AppDataBase.LOG_APP,
                        "Cliente ID "+position+" Primeiro Nome: "+clienteSelecionado.getPrimeiroNome());

                Toast.makeText(aContext,
                        "Cliente ID " + position + " Primeiro Nome: " + clienteSelecionado.getPrimeiroNome(),
                        Toast.LENGTH_LONG).show();
            }

        }
    }

}
