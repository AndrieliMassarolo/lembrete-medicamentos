package me.ads.lembretemedicamentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MenuFragment extends Fragment {

    public MenuFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.menu_medicamentos:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new me.ads.lembretemedicamentos.medicamentos.MainFragment()).commit();
                break;
            case R.id.menu_usuario:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new me.ads.lembretemedicamentos.usuarios.MainFragment()).commit();
                break;

            case R.id.menu_medico:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_main, new me.ads.lembretemedicamentos.medico.MainFragment()).commit();
                break;
//
//            case R.id.menu_receitas:
//                Toast.makeText(getActivity(),"Menu Receitas",Toast.LENGTH_LONG).show();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}