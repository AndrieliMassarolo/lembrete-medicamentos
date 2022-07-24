package me.ads.lembretemedicamentos.medicamentos;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import me.ads.lembretemedicamentos.R;
import me.ads.lembretemedicamentos.database.DatabaseHelper;
import me.ads.lembretemedicamentos.notification.NotificationHelper;


public class EditarFragment extends Fragment {

    private EditText etNome;
    private EditText etDosagem;
    private EditText etTipo;
    private EditText etPosologia;
    private EditText etHorario;
    private EditText etTempotratamento;
    private DatabaseHelper databaseHelper;
    private Medicamentos m;

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
        View v = inflater.inflate(R.layout.medicamentos_fragment_editar, container, false);

        etNome = v.findViewById(R.id.editText_nome_medicamentos);
        etDosagem = v.findViewById(R.id.editText_dosagem_medicamentos);
        etTipo = v.findViewById(R.id.editText_tipo_medicamentos);
        etPosologia = v.findViewById(R.id.editText_posologia_medicamentos);
        etHorario = v.findViewById(R.id.editText_horario_medicamentos);
        etTempotratamento = v.findViewById(R.id.editText_tempo_tratamento_medicamentos);

        Bundle b =  getArguments();
        int id_medicamentos = b.getInt("id");
        databaseHelper = new DatabaseHelper(getActivity());
        m = databaseHelper.getByIdMedicamentos(id_medicamentos);

        etNome.setText(m.getNome());
        etDosagem.setText(m.getDosagem());
        etTipo.setText(m.getTipo());
        etPosologia.setText(m.getPosologia());
        etHorario.setText(m.getHorario());
        etTempotratamento.setText(m.getTempotratamento());

        Button btnEditar = v.findViewById(R.id.button_editar_medicamentos);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(id_medicamentos);
            }
        });
        Button btnExcluir = v.findViewById(R.id.button_excluir_medicamentos);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.excluir_medicamentos_mensagem);
                builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        excluir(id_medicamentos);
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
            Toast.makeText(getActivity(),"Por favor informe o nome do medicamento!", Toast.LENGTH_LONG).show();
        } else if (etDosagem.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe a dosagem do medicamento!", Toast.LENGTH_LONG).show();
        } else if (etTipo.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe o tipo do medicamento (comprimido, cápsula, gotas, pomada, etc.)!", Toast.LENGTH_LONG).show();
        } else if (etPosologia.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe a quantidade do medicamento", Toast.LENGTH_LONG).show();
        } else if (etHorario.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe o horário do medicamento", Toast.LENGTH_LONG).show();
        } else if (etTempotratamento.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Por favor informe o tempo de tratamento do medicamento!", Toast.LENGTH_LONG).show();
        } else {
            // atualização do medicamento
            m = new Medicamentos();
            m.setId(id);
            m.setNome(etNome.getText().toString());
            m.setDosagem(etDosagem.getText().toString());
            m.setTipo(etTipo.getText().toString());
            m.setPosologia(etPosologia.getText().toString());
            m.setHorario(etHorario.getText().toString());
            m.setTempotratamento(etTempotratamento.getText().toString());
            databaseHelper.updateMedicamentos(m);
            Toast.makeText(getActivity(), "Medicamento atualizado!", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_medicamentos, new ListarFragment()).commit();

            String[] strings = etHorario.getText().toString().split(":");
            NotificationHelper.startAlarmBroadcastReceiver(getContext(), "Hora de tomar seu Medicamento",
                    etNome.getText().toString(),
                    Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
        }
    }

    private void excluir(int id) {
        // exclusão do medicamento
        m = new Medicamentos();
        m.setId(id);
        databaseHelper.deleteMedicamentos(m);
        Toast.makeText(getActivity(), "Medicamento excluído!", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_medicamentos, new ListarFragment()).commit();
    }
}