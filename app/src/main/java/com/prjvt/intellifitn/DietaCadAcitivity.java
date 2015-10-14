package com.prjvt.intellifitn;

import android.app.Notification;
import android.support.v7.app.ActionBarActivity;
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

import com.prjvt.intellifitn.domain.DiasExercicio;
import com.prjvt.intellifitn.domain.DietaHorarioLista;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.List;


public class DietaCadAcitivity extends AppCompatActivity implements Button.OnClickListener {

    private EditText mEdtNome;
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private ActionButton mBtFloat;
    private List<DietaHorarioLista> listaDietaHorario;
    private Integer idDieta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_dieta);

        mToolBar = (Toolbar) findViewById(R.id.tb_caddieta);
        mToolBar.setTitle(R.string.title_activity_cad_dieta);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_dias);

        mBtFloat = (ActionButton) findViewById(R.id.bt_float);
        mBtFloat.setShowAnimation(ActionButton.Animations.FADE_IN);
        mBtFloat.setOnClickListener(this);
        mBtFloat.isRippleEffectEnabled();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setHasFixedSize(true);

        listaDietaHorario = new ArrayList<DietaHorarioLista>();

        idDieta = getIntent().getExtras().getInt("iddieta", 0);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
