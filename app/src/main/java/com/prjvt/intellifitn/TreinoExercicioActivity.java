package com.prjvt.intellifitn;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.prjvt.intellifitn.domain.DiasExercicio;
import com.prjvt.intellifitn.domain.Exercicio;
import com.prjvt.intellifitn.enumerator.EDias;
import com.prjvt.intellifitn.fragments.ExercicioBuscaFragment;
import com.software.shell.fab.ActionButton;

import java.util.ArrayList;
import java.util.List;


public class TreinoExercicioActivity extends ActionBarActivity implements Button.OnClickListener {

    Toolbar mToolbar;
    ActionButton mBtLancaExerc;
    private ArrayList<DiasExercicio> mList;
    private ExercicioBuscaFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino_exercicio);

//        mToolbar = (Toolbar) findViewById(R.id.tb_treinoexercicio);
//        mToolbar.setTitle(R.string.title_activity_treino_exercicio);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Intent intent = getIntent();
//        mList = intent.getParcelableArrayListExtra("ListaExercicio");

//        mBtLancaExerc = (ActionButton) findViewById(R.id.bt_lanca_exercicio);
//        mBtLancaExerc.setOnClickListener(this);


        // Fragment
        frag = (ExercicioBuscaFragment) getSupportFragmentManager().findFragmentByTag("TreinoExercicio");
        if (frag == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            frag = new ExercicioBuscaFragment();

            ft.replace(R.id.rl_fragment_container, frag, "TreinoExercicio");
            ft.commit();
        }

//        ExercicioFragment exercfrag = (ExercicioFragment) getSupportFragmentManager().findFragmentByTag("ExercicioFragment");
//        if (exercfrag == null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            exercfrag = new ExercicioFragment();
//            ft.replace(R.id.rl_fragment_exercicio, exercfrag, "ExercicioFragment");
//            ft.commit();
//
//            ft.hide(exercfrag);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_treino_exercicio, menu);
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

//        if (id == R.id.bt_lanca_exercicio) {
//            if (frag != null) {
//                EDias dia = EDias.SEGUNDA;
//
//                int Posicao = -1;
//
//                for (int i = 0; i < mList.size(); i++) {
//                    if (mList.get(i).getDia() == EDias.SEGUNDA) {
//                        Posicao = i;
//                    }
//                }
//
//                if (Posicao >= 0) {
//                    for (int i = 0; i < frag.getListaExercicio().size(); i++) {
//                        mList.get(Posicao).getExercicio().add(frag.getListaExercicio().get(i));
//                    }
//                } else {
//                    DiasExercicio diasExercicio = new DiasExercicio();
//                    diasExercicio.setDia(dia);
//                    diasExercicio.setExercicio(frag.getListaExercicio());
//
//                    mList.add(diasExercicio);
//                }
//            }
//
//            finish();
//        }
    }

//    public void atualizaExercicio(Exercicio exercicio) {
//        ExercicioFragment exercfrag = (ExercicioFragment) getSupportFragmentManager().findFragmentByTag("ExercicioFragment");
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.setCustomAnimations(R.animator.fade_in, R.animator.fade_out);
//
//        if (exercfrag != null) {
//            exercfrag.atualizaExercicio(exercicio);
//
//            if (exercfrag.isHidden()) {
//                ft.show(exercfrag);
//                ft.commit();
//            }
//        }
//    }
}
