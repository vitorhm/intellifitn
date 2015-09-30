package com.prjvt.intellifitn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prjvt.intellifitn.adapters.TreinoDiasAdapter;
import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.DiasExercicio;
import com.prjvt.intellifitn.domain.ExercicioDetalhe;
import com.prjvt.intellifitn.domain.Treino;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;
import com.software.shell.fab.ActionButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TreinoCadActivity extends ActionBarActivity implements Button.OnClickListener, RecyclerViewClick.RecyclerViewClickDetailListener {

    private EditText mEdtNome;
    private ActionButton btFloat, btFloat_teste;
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private ArrayList<DiasExercicio> mList;
    private Treino mTreino;
    private TreinoDiasAdapter tAdapter;
    List<DiasExercicio> tpExercicios;
    private int idTreino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_treino);

        mEdtNome = (EditText) findViewById(R.id.ed_nometreino);
        mEdtNome.setText(R.string.title_activity_cons_treino);

        mToolBar = (Toolbar) findViewById(R.id.tb_cadtreino);
        mToolBar.setTitle(R.string.title_activity_cad_treino);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_dias);
        btFloat = (ActionButton) findViewById(R.id.bt_float);
        btFloat.setOnClickListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);

        tpExercicios = new ArrayList<DiasExercicio>();

        idTreino = getIntent().getExtras().getInt("idtreino", 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.recuperaTreino(idTreino);

        if (tAdapter == null)
            this.setListAdapter();

        tAdapter.notifyDataSetChanged();
    }

    private void recuperaTreino(int id) {
        DatabaseHelper db = new DatabaseHelper(this);

        try {
            mTreino = db.getTreino(id);

            if (mTreino == null) {
                mTreino = new Treino();
            }

            tpExercicios.clear();
            tpExercicios.addAll(mTreino.getListaExercicio());
        } catch (SQLException e) {

        }
    }

    private Boolean insereTreino() {
        DatabaseHelper db = new DatabaseHelper(this);
        mTreino.setDescricao(mEdtNome.getText().toString());

        return db.salvaTreino(mTreino);
    }

    private void setListAdapter() {
        tAdapter = new TreinoDiasAdapter(this, tpExercicios, this);
        mRecyclerView.setAdapter(tAdapter);
    }

    private void chamaTreinoExercicio(int idexerciciodetail, int idtreino) {
        Intent myIntent = new Intent(this, TreinoExercicioActivity.class);
        myIntent.putExtra("idtreino", idtreino);
        myIntent.putExtra("idexerciciodetalhe", idexerciciodetail);
        this.startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cad_treino, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.bt_float) {
            if (mEdtNome.getText().toString().equals("")) {
                Toast toast = Toast.makeText(this, "Preencha o nome do treino.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (insereTreino()) {
                    this.chamaTreinoExercicio(0, mTreino.getId());
                }
            }
        }
    }

    @Override
    public void recyclerViewListDetailClicked(View v, int positionMaster, int positionDetail) {
        ExercicioDetalhe ex = tAdapter.getExercicio(positionDetail, positionMaster);

        this.chamaTreinoExercicio(ex.getId(), mTreino.getId());
    }
}
