package me.ads.lembretemedicamentos.medicamentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.ads.lembretemedicamentos.R;

public class MainFragment extends Fragment {

    public MainFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.medicamentos_fragment_main, container, false);

        if (savedInstanceState == null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_medicamentos, new ListarFragment()).commit();
        }

        Button btnAdicionar = v.findViewById(R.id.button_adicionar_medicamentos);
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_medicamentos, new AdicionarFragment()).commit();
            }
        });

        Button btnListar = v.findViewById(R.id.button_listar_medicamentos);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_medicamentos, new ListarFragment()).commit();
            }
        });


        // Inflate the layout for this fragment
        return v;
    }
}