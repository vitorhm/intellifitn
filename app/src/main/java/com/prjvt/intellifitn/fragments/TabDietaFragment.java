package com.prjvt.intellifitn.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.adapters.AlimentoHoraAdapter;
import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.Dieta;
import com.prjvt.intellifitn.domain.DietaHorarioLista;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitor on 15/10/2015.
 */
public class TabDietaFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<DietaHorarioLista> listaDietaHorario;
    private AlimentoHoraAdapter tAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_dieta,container,false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_listadieta);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);

        listaDietaHorario = new ArrayList<DietaHorarioLista>();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.recuperaDieta(1);

        if (tAdapter == null)
            this.setListAdapter();

        tAdapter.notifyDataSetChanged();
    }

    private void setListAdapter() {
        tAdapter = new AlimentoHoraAdapter(getActivity(), listaDietaHorario, null);
        mRecyclerView.setAdapter(tAdapter);
    }

    private void recuperaDieta(int id) {
        DatabaseHelper db = new DatabaseHelper(getActivity());

        try {
            Dieta mDieta = db.getDieta(id);

            listaDietaHorario.clear();
            listaDietaHorario.addAll(mDieta.getListaDietaHorario());

        } catch (SQLException e) {

        }
    }
}
