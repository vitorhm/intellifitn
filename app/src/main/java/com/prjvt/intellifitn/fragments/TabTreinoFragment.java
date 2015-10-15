package com.prjvt.intellifitn.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.adapters.ExercicioDiaAdapter;
import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.DiasExercicio;
import com.prjvt.intellifitn.enumerator.EDias;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitor on 15/10/2015.
 */
public class TabTreinoFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<DiasExercicio> mListExercicio;
    private ExercicioDiaAdapter exercicioDiaAdapter;

    public TabTreinoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_treino,container,false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_listaexerciciodia);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        return v;
    }

    private void setListAdapter() {
        DatabaseHelper db = new DatabaseHelper(getActivity());
        List<DiasExercicio> temp = null;

        try {
            temp = db.getExerciciosDia(EDias.fromDay());
        } catch (SQLException e) {

        }

        if (mListExercicio == null)
            mListExercicio = new ArrayList<DiasExercicio>();

        mListExercicio.clear();
        mListExercicio.addAll(temp);

        if (exercicioDiaAdapter == null) {
            exercicioDiaAdapter = new ExercicioDiaAdapter(getActivity(), mListExercicio, null);
            mRecyclerView.setAdapter(exercicioDiaAdapter);
        } else {
            exercicioDiaAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.setListAdapter();
    }
}
