package me.ads.lembretemedicamentos.medico;

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
import me.ads.lembretemedicamentos.medico.ListarFragment;
import me.ads.lembretemedicamentos.medico.Medico;

public class AdicionarFragment extends Fragment {

    private EditText etNome;
    private EditText etCRM;
    private EditText etTelefone;
    private EditText etEspecialidade;

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
        View v = inflater.inflate(R.layout.medico_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editTextNomeMedico);
        etCRM = v.findViewById(R.id.editTextCrmMedico);
        etTelefone = v.findViewById(R.id.editTextTelefoneMedico);
        etEspecialidade = v.findViewById(R.id.editTextEspecialidadeMedico);

        Button btnAdicionar = v.findViewById(R.id.buttonAdicionarMedico);

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
            Toast.makeText(getActivity(), "Por favor, informe o nome do médico!", Toast.LENGTH_LONG).show();
        } else if (etCRM.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o CRM do médico!", Toast.LENGTH_LONG).show();
        } else if (etTelefone.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe o número do telefone do médico!", Toast.LENGTH_LONG).show();
        } else if (etEspecialidade.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor, informe a especialidade do médico!", Toast.LENGTH_LONG).show();
        } else {
            //criação do usuário
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Medico d = new Medico();
            d.setNome(etNome.getText().toString());
            d.setCRM(etCRM.getText().toString());
            d.setTelefone(etTelefone.getText().toString());
            d.setEspecialidade(etEspecialidade.getText().toString());
            databaseHelper.createMedico(d);
            Toast.makeText(getActivity(), "Médico salvo!", Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
        }
    }

}

