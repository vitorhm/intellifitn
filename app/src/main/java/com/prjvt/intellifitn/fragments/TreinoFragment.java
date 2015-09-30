package com.prjvt.intellifitn.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.TreinoCadActivity;
import com.prjvt.intellifitn.adapters.TreinoAdapter;
import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.Treino;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;
import com.software.shell.fab.ActionButton;
import com.software.shell.fab.FloatingActionButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TreinoFragment extends Fragment implements RecyclerViewClick.RecyclerViewClickListener {

    private RecyclerView mRecyclerView;
    private List<Treino> mListTreino;
    private DatabaseHelper db;
    private TextView tvHint;
    private TreinoAdapter tAdapter;

    public TreinoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_treino, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        tvHint = (TextView) view.findViewById(R.id.tv_hint);

        ActionButton bt_float = (ActionButton) view.findViewById(R.id.bt_float);
        bt_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), TreinoCadActivity.class);
                myIntent.putExtra("idtreino", 0); //Optional parameters
                startActivity(myIntent);
            }
        });

        this.setListAdapter();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.setListAdapter();
    }

    private void setListAdapter() {
        db = new DatabaseHelper(getActivity());
        List<Treino> temp = null;

        try {
            temp = db.getListaTreino();
        } catch (SQLException e) {

        }

        if (mListTreino == null)
            mListTreino = new ArrayList<Treino>();

        mListTreino.clear();
        mListTreino.addAll(temp);

        if (mListTreino.size() > 0)
            tvHint.setVisibility(View.GONE);
        else
            tvHint.setVisibility(View.VISIBLE);

        if (tAdapter == null) {
            tAdapter = new TreinoAdapter(getActivity(), mListTreino, this);
            mRecyclerView.setAdapter(tAdapter);
        } else {
            tAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void recyclerViewListClicked(View v, int position) {
        TreinoAdapter treinoAdapter = (TreinoAdapter) mRecyclerView.getAdapter();
        Treino treino = treinoAdapter.getTreino(position);

        Intent myIntent = new Intent(getActivity(), TreinoCadActivity.class);
        myIntent.putExtra("idtreino", treino.getId()); //Optional parameters
        this.startActivity(myIntent);
    }
}
