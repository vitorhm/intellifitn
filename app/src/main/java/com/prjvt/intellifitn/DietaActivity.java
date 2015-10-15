package com.prjvt.intellifitn;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.prjvt.intellifitn.adapters.DietaAdapter;
import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.Dieta;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;
import com.software.shell.fab.ActionButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DietaActivity extends AppCompatActivity implements RecyclerViewClick.RecyclerViewClickListener {

    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private List<Dieta> mListaDieta;
    private TextView tvHint;
    private DietaAdapter mAdapterDieta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta);

        mToolBar = (Toolbar) findViewById(R.id.tb_dieta);
        mToolBar.setTitle(R.string.title_activity_cad_dieta);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_dieta);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        tvHint = (TextView) findViewById(R.id.tv_hint_dieta);

        ActionButton bt_float = (ActionButton) findViewById(R.id.bt_float_dieta);
        bt_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DietaCadAcitivity.class);
                myIntent.putExtra("iddieta", 0); //Optional parameters
                startActivity(myIntent);
            }
        });
    }

    private void setListAdapter() {
        DatabaseHelper db = new DatabaseHelper(this);
        List<Dieta> temp = null;

        try {
            temp = db.getListaDieta();
        } catch (SQLException e) {

        }

        if (mListaDieta == null)
            mListaDieta = new ArrayList<Dieta>();

        mListaDieta.clear();
        mListaDieta.addAll(temp);

        if (mListaDieta.size() > 0)
            tvHint.setVisibility(View.GONE);
        else
            tvHint.setVisibility(View.VISIBLE);

        if (mAdapterDieta == null) {
            mAdapterDieta = new DietaAdapter(this, mListaDieta, this);
            mRecyclerView.setAdapter(mAdapterDieta);
        } else {
            mAdapterDieta.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.setListAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dieta, menu);
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
    public void recyclerViewListClicked(View v, int position) {
        DietaAdapter dietaAdapter = (DietaAdapter) mRecyclerView.getAdapter();
        Dieta dieta = dietaAdapter.getDieta(position);

        Intent myIntent = new Intent(this, DietaCadAcitivity.class);
        myIntent.putExtra("iddieta", dieta.getId()); //Optional parameters
        this.startActivity(myIntent);
    }
}
