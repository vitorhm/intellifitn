package com.prjvt.intellifitn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prjvt.intellifitn.adapters.AlimentoHoraAdapter;
import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.Dieta;
import com.prjvt.intellifitn.domain.DietaHorario;
import com.prjvt.intellifitn.domain.DietaHorarioLista;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;
import com.software.shell.fab.ActionButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DietaCadAcitivity extends AppCompatActivity implements Button.OnClickListener, RecyclerViewClick.RecyclerViewClickDetailListener {

    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private ActionButton mBtFloat;
    private List<DietaHorarioLista> listaDietaHorario;
    private AlimentoHoraAdapter tAdapter;
    private Integer idDieta;
    private Dieta mDieta;
    private EditText mEdtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_dieta);

        mToolBar = (Toolbar) findViewById(R.id.tb_caddieta);
        mToolBar.setTitle(R.string.title_activity_cad_dieta);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_diasdieta);
        mEdtNome = (EditText) findViewById(R.id.ed_nomedieta);

        mBtFloat = (ActionButton) findViewById(R.id.bt_float);
        mBtFloat.setOnClickListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);

        listaDietaHorario = new ArrayList<DietaHorarioLista>();

        idDieta = getIntent().getExtras().getInt("iddieta", 0);
    }

    private void recuperaDieta(int id) {
        DatabaseHelper db = new DatabaseHelper(this);

        try {
            mDieta = db.getDieta(id);

            if (mDieta == null) {
                mDieta = new Dieta();
            } else {
                mEdtNome.setText(mDieta.getDescricao());
            }

            listaDietaHorario.clear();
            listaDietaHorario.addAll(mDieta.getListaDietaHorario());
        } catch (SQLException e) {

        }
    }

    private Boolean insereDieta() {
        DatabaseHelper db = new DatabaseHelper(this);
        mDieta.setDescricao(mEdtNome.getText().toString());

        return db.salvaDieta(mDieta);
    }

    private void setListAdapter() {
        tAdapter = new AlimentoHoraAdapter(this, listaDietaHorario, this);
        mRecyclerView.setAdapter(tAdapter);
    }

    private void chamaDietaAlimento(int iddietahorario, int idDieta) {
        Intent myIntent = new Intent(this, DietaAlimentoActivity.class);
        myIntent.putExtra("iddieta", idDieta);
        myIntent.putExtra("iddietahorario", iddietahorario);
        this.startActivity(myIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.recuperaDieta(idDieta);

        if (tAdapter == null)
            this.setListAdapter();

        tAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dieta_cad, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.bt_float) {
            if (mEdtNome.getText().toString().equals("")) {
                Toast toast = Toast.makeText(this, "Preencha o nome da dieta.", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (insereDieta()) {
                    this.idDieta = mDieta.getId();
                    this.chamaDietaAlimento(0, idDieta);
                }
            }
        }
    }

    @Override
    public void recyclerViewListDetailClicked(View v, int positionMaster, int positionDetail) {
        DietaHorario dietaHorario = tAdapter.getDietaHorario(positionDetail, positionMaster);
        this.chamaDietaAlimento(dietaHorario.getId(), mDieta.getId());
    }
}
