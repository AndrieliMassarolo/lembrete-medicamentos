package me.ads.lembretemedicamentos.usuarios;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class EditarFragment extends Fragment {

    private DatabaseHelper databaseHelper;
    private Usuario u;
    private EditText etNome;
    private EditText etEmail;
    private EditText etCelular;
    private EditText etSexo;
    private EditText etDataNascimento;

    public EditarFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.usuario_fragment_editar, container, false);
        Bundle b = getArguments();
        int id_usuario = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        u = databaseHelper.getByIdUsuario(id_usuario);

        etNome = v.findViewById(R.id.editTextNomeUsuario);
        etEmail = v.findViewById(R.id.editTextEmailUsuario);
        etCelular = v.findViewById(R.id.editTextCelularUsuario);
        etSexo = v.findViewById(R.id.editTextSexoUsuario);
        etDataNascimento = v.findViewById(R.id.editTextDataNascimentoUsuario);

        etNome.setText(u.getNome());
        etEmail.setText(u.getEmail());
        etCelular.setText(u.getCelular());
        etSexo.setText(u.getSexo());
        etDataNascimento.setText(u.getData_nascimento());

        Button btnEditar = v.findViewById(R.id.buttonEditarUsuario);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id_usuario);
            }
        });

        Button btnExcluir = v.findViewById(R.id.buttonExcluirUsuario);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.excluir_usuario_mensagem);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_usuario);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Não faz nada
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return v;
    }

    private void editar (int id) {
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
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Usuario u = new Usuario();
            u.setId(id);
            u.setNome(etNome.getText().toString());
            u.setEmail(etEmail.getText().toString());
            u.setCelular(etCelular.getText().toString());
            u.setSexo(etSexo.getText().toString());
            u.setData_nascimento(etDataNascimento.getText().toString());
            databaseHelper.updateUsuario(u);
            Toast.makeText(getActivity(), "Usuário atualizado!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameUsuario, new ListarFragment()).commit();
        }
    }

    private void excluir(int id) {
        u = new Usuario();
        u.setId(id);
        databaseHelper.deleteUsuario(u);
        Toast.makeText(getActivity(), "Usuário excluído!", Toast.LENGTH_LONG).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameUsuario, new ListarFragment()).commit();
    }
}
