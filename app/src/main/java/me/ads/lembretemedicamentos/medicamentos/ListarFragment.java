package me.ads.lembretemedicamentos.medicamentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import me.ads.lembretemedicamentos.R;
import me.ads.lembretemedicamentos.database.DatabaseHelper;

public class ListarFragment extends Fragment {

    public ListarFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // listagem dos medicamentos
        View v = inflater.inflate(R.layout.medicamentos_fragment_listar, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        ListView lv = v.findViewById(R.id.list_view_listar_medicamentos);
        databaseHelper.getAllMedicamentos(getActivity(), lv);

        // handler do botão listar
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvId = view.findViewById(R.id.textViewIdListarMedicamentos);
                Bundle b = new Bundle();
                b.putInt("id", Integer.parseInt(tvId.getText().toString()));

                EditarFragment editar = new EditarFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                editar.setArguments(b);
                ft.replace(R.id.frame_medicamentos, editar).commit();
            }
        });
        return v;
    }
}