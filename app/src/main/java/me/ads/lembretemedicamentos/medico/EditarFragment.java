package me.ads.lembretemedicamentos.medico;

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
//import me.ads.lembretemedicamentos.medico.ListarFragment;
//import me.ads.lembretemedicamentos.medico.Medico;

public class EditarFragment extends Fragment {

    private EditText etNome;
    private EditText etCRM;
    private EditText etTelefone;
    private EditText etEspecialidade;
    private DatabaseHelper databaseHelper;
    private Medico d;

    public EditarFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.medico_fragment_editar, container, false);

        etNome = v.findViewById(R.id.editTextNomeMedico);
        etCRM = v.findViewById(R.id.editTextCrmMedico);
        etTelefone = v.findViewById(R.id.editTextTelefoneMedico);
        etEspecialidade = v.findViewById(R.id.editTextEspecialidadeMedico);

        Bundle b =  getArguments();
        int id_medico = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        d = databaseHelper.getByIdMedico(id_medico);

        etNome.setText(d.getNome());
        etCRM.setText(d.getCRM());
        etTelefone.setText(d.getTelefone());
        etEspecialidade.setText(d.getEspecialidade());

        Button btnEditar = v.findViewById(R.id.buttonEditarMedico);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_medico);
            }
        });
        Button btnExcluir = v.findViewById(R.id.buttonExcluirMedico);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Deseja realmente excluir o médico?");
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_medico);
                    }
                });
                builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Não faz nada
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return v;
    }

    private void editar(int id){
        // validação dos dados
        if (etNome.getText().toString().equals("")){
            Toast.makeText(getActivity(),"Por favor informe o nome do médico!", Toast.LENGTH_LONG).show();
        } else if (etCRM.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe o CRM do médico!", Toast.LENGTH_LONG).show();
        } else if (etTelefone.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe o telefone de contato do médico!", Toast.LENGTH_LONG).show();
        } else if (etEspecialidade.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe a especialidade do médico!", Toast.LENGTH_LONG).show();
        } else {
            // atualização do medicamento
            d = new Medico();
            d.setId(id);
            d.setNome(etNome.getText().toString());
            d.setCRM(etCRM.getText().toString());
            d.setTelefone(etTelefone.getText().toString());
            d.setEspecialidade(etEspecialidade.getText().toString());
            databaseHelper.updateMedicos(d);
            Toast.makeText(getActivity(), "Médico atualizado!", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new me.ads.lembretemedicamentos.medico.ListarFragment()).commit();
        }
    }

    private void excluir(int id) {
        // exclusão do médico
        d = new Medico();
        d.setId(id);
        databaseHelper.deleteMedico(d);
        Toast.makeText(getActivity(), "Medicamento excluído!", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameMedico, new ListarFragment()).commit();
    }
}