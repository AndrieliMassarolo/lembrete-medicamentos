package me.ads.lembretemedicamentos.usuarios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import me.ads.lembretemedicamentos.R;
import me.ads.lembretemedicamentos.database.DatabaseHelper;

public class AdicionarFragment extends Fragment {

    private EditText etNome;
    private EditText etEmail;
    private EditText etCelular;
    private EditText etSexo;
    private EditText etDataNascimento;

    public AdicionarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.usuario_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editTextNomeUsuario);
        etEmail = v.findViewById(R.id.editTextEmailUsuario);
        etCelular = v.findViewById(R.id.editTextCelularUsuario);
        etSexo = v.findViewById(R.id.editTextSexoUsuario);
        etDataNascimento = v.findViewById(R.id.editTextDataNascimentoUsuario);

        Button btnAdicionar = v.findViewById(R.id.buttonAdicionarUsuario);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionar();
            }
        });

        return v;
    }

    private void adicionar () {
        // validação dos dados
        if (etNome.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (etEmail.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o e-mail!", Toast.LENGTH_LONG).show();
        } else if (etCelular.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do celular!", Toast.LENGTH_LONG).show();
        } else if (etSexo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o sexo!", Toast.LENGTH_LONG).show();
        } else if (etDataNascimento.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a data de nascimento!", Toast.LENGTH_LONG).show();
        } else {
            //criação do usuário
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Usuario u = new Usuario();
            u.setNome(etNome.getText().toString());
            u.setEmail(etEmail.getText().toString());
            u.setCelular(etCelular.getText().toString());
            u.setSexo(etSexo.getText().toString());
            u.setData_nascimento(etDataNascimento.getText().toString());
            databaseHelper.createUsuario(u);
            Toast.makeText(getActivity(), "Usuário salvo!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameUsuario, new ListarFragment()).commit();
        }
    }

}
