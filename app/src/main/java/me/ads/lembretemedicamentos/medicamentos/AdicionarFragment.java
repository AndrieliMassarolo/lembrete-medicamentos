package me.ads.lembretemedicamentos.medicamentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.ads.lembretemedicamentos.R;
import me.ads.lembretemedicamentos.database.DatabaseHelper;

public class AdicionarFragment extends Fragment {

    private EditText etNome;
    private EditText etDosagem;
    private EditText etTipo;
    private EditText etPosologia;
    private EditText etTempotratamento;

    public AdicionarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.medicamentos_fragment_adicionar, container, false);

        etNome = v.findViewById(R.id.editText_nome_medicamentos);
        etDosagem = v.findViewById(R.id.editText_dosagem_medicamentos);
        etTipo = v.findViewById(R.id.editText_tipo_medicamentos);
        etPosologia = v.findViewById(R.id.editText_posologia_medicamentos);
        etTempotratamento = v.findViewById(R.id.editText_tempo_tratamento_medicamentos);

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_medicamentos);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionar();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    private void adicionar() {
        // validação dos dados
        if (etNome.getText().toString().equals("")){
            Toast.makeText(getActivity(),"Por favor informe o nome do medicamento", Toast.LENGTH_LONG).show();
        } else if (etDosagem.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe a dosagem do medicamento", Toast.LENGTH_LONG).show();
        } else if (etTipo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe o tipo do medicamento (comprimido, cápsula, gotas, pomada, etc.)", Toast.LENGTH_LONG).show();
        } else if (etPosologia.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe a frequência diária do medicamento (quantas vezes ao dia ou a cada quantas horas)", Toast.LENGTH_LONG).show();
        } else if (etTempotratamento.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe o tempo de tratamento do medicamento", Toast.LENGTH_LONG).show();
        } else {
            // criação do medicamento
            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
            Medicamentos m = new Medicamentos();
            m.setNome(etNome.getText().toString());
            m.setDosagem(etDosagem.getText().toString());
            m.setTipo(etTipo.getText().toString());
            m.setPosologia(etPosologia.getText().toString());
            m.setTempotratamento(etTempotratamento.getText().toString());
            databaseHelper.createMedicamentos(m);
            Toast.makeText(getActivity(), "Medicamento Salvo", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_medicamentos, new ListarFragment()).commit();
        }
    }

}