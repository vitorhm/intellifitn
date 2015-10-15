package com.prjvt.intellifitn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.prjvt.intellifitn.adapters.ExercicioDiaAdapter;
import com.prjvt.intellifitn.adapters.TreinoDiasAdapter;
import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.DiasExercicio;
import com.prjvt.intellifitn.enumerator.EDias;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private Toolbar mToolBar;
    private PrimaryDrawerItem item1;
    private SecondaryDrawerItem item2, itemDieta;
    private Drawer drawer;
    private List<DiasExercicio> mListExercicio;
    private ExercicioDiaAdapter exercicioDiaAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar) findViewById(R.id.tb_main);
        mToolBar.setTitle("IntelliFitn");

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_listaexerciciodia);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        this.createDrawer();
        this.setListAdapter();

        setSupportActionBar(mToolBar);

//        deleteDatabase("dbintellifitn");
    }

    private void setListAdapter() {
        DatabaseHelper db = new DatabaseHelper(this);
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
            exercicioDiaAdapter = new ExercicioDiaAdapter(this, mListExercicio, null);
            mRecyclerView.setAdapter(exercicioDiaAdapter);
        } else {
            exercicioDiaAdapter.notifyDataSetChanged();
        }
    }

    private void createDrawer() {
        IconicsDrawable icon1 = new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_home)
                .color(Color.GRAY)
                .sizeDp(18);

        IconicsDrawable icon2 = new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_assessment)
                .color(Color.GRAY)
                .sizeDp(18);

        IconicsDrawable iconDieta = new IconicsDrawable(this)
                .icon(GoogleMaterial.Icon.gmd_local_drink)
                .color(Color.GRAY)
                .sizeDp(18);

        item1 = new PrimaryDrawerItem()
                .withName("Ínicio")
                .withIcon(icon1)
                .withSelectedIconColorRes(R.color.colorPrimary)
                .withIconTintingEnabled(true);

        item2 = new SecondaryDrawerItem()
                .withName("Meu Treino")
                .withIcon(icon2)
                .withSelectedIconColorRes(R.color.colorPrimary)
                .withIconTintingEnabled(true);

        itemDieta = new SecondaryDrawerItem()
                .withName("Minha Dieta")
                .withIcon(iconDieta)
                .withSelectedIconColorRes(R.color.colorPrimary)
                .withIconTintingEnabled(true);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolBar)
                .withHeader(R.layout.header_navigation)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        itemDieta
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        if (iDrawerItem == item2) {
                            Intent intent = new Intent(view.getContext(), TreinoActivity.class);

                            drawer.setSelection(item1);
                            drawer.closeDrawer();
                            startActivity(intent);
                        } else {
                            if (iDrawerItem == itemDieta) {
                                Intent intent = new Intent(view.getContext(), DietaActivity.class);

                                drawer.setSelection(item1);
                                drawer.closeDrawer();
                                startActivity(intent);
                            }
                        }
                        return true;
                    }
                })
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    public void onResume() {
        super.onResume();
        drawer.setSelection(item1);
        this.setListAdapter();
    }
}
