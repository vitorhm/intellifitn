package com.prjvt.intellifitn.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.adapters.ExercicioAdapter;
import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.DiasExercicio;
import com.prjvt.intellifitn.domain.Exercicio;
import com.prjvt.intellifitn.domain.ExercicioDetalhe;
import com.prjvt.intellifitn.enumerator.EDias;
import com.prjvt.intellifitn.enumerator.EGrupoMuscular;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExercicioBuscaFragment extends Fragment implements SearchView.OnQueryTextListener,
        SearchView.OnCloseListener, Button.OnClickListener {

    private Exercicio mExercicio;
    private Space mSpBuscaTop;
    private ImageView mIvGrupo;
    private TextView mTextExercicio;
    private EditText mEdSerie, mEdRepeticao;
    private CardView mCardExercicio;
    private RecyclerView mRecyclerView;
    private ListView listaExercicioBusca;
    private SearchView searchExercicio;
    private DatabaseHelper db;
    private ArrayList<DiasExercicio> mList;
    private Integer mIdTreino, mIdExercicioDetalhe;
    private Spinner mSpinDias;

    public ExercicioBuscaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        db = new DatabaseHelper(getActivity());
        mIdTreino = getActivity().getIntent().getExtras().getInt("idtreino", 0);
        mIdExercicioDetalhe = getActivity().getIntent().getExtras().getInt("idexerciciodetalhe", 0);

        View view = inflater.inflate(R.layout.fragment_exercicio_busca, container, false);

        searchExercicio = (SearchView) view.findViewById(R.id.sv_exercicio);
        searchExercicio.setIconifiedByDefault(false);
        searchExercicio.setOnQueryTextListener(this);
        searchExercicio.setOnCloseListener(this);

        listaExercicioBusca = (ListView) view.findViewById(R.id.lv_search_exercicio);
        listaExercicioBusca.setVisibility(View.GONE);

        mCardExercicio = (CardView) view.findViewById(R.id.cv_exercicio);
        mCardExercicio.setVisibility(View.GONE);

        mTextExercicio = (TextView) view.findViewById(R.id.tv_descr_exerc);
        mEdSerie = (EditText) view.findViewById(R.id.ed_series);
        mEdRepeticao = (EditText) view.findViewById(R.id.ed_repeticao);
        mIvGrupo = (ImageView) view.findViewById(R.id.iv_grupomusc);

        mSpinDias = (Spinner) view.findViewById(R.id.spn_dias);
        List<String> list = new ArrayList<String>();
        list.add(EDias.SEGUNDA.toString(getActivity()));
        list.add(EDias.TERCA.toString(getActivity()));
        list.add(EDias.QUARTA.toString(getActivity()));
        list.add(EDias.QUINTA.toString(getActivity()));
        list.add(EDias.SEXTA.toString(getActivity()));
        list.add(EDias.SABADO.toString(getActivity()));
        list.add(EDias.DOMINGO.toString(getActivity()));
        ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinDias.setAdapter(dataAdapter);

        mSpBuscaTop = (Space) view.findViewById(R.id.sp_busca_top);

        ImageButton mBtCancela = (ImageButton) view.findViewById(R.id.bt_cancela);
        mBtCancela.setOnClickListener(this);
        ImageButton mBtConfirma = (ImageButton) view.findViewById(R.id.bt_confirma);
        mBtConfirma.setOnClickListener(this);

        this.recuperaExercicio();

        return view;
    }

    public boolean onQueryTextChange(String newText) {
        showResults(newText);
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
        showResults(query);
        return false;
    }

    public boolean onClose() {
        showResults("");
        return false;
    }

    private Boolean insereExercicio() {
        DiasExercicio listaExercicio = new DiasExercicio();
        listaExercicio.setDia(EDias.fromString(mSpinDias.getSelectedItem().toString(), getActivity()));

        ExercicioDetalhe exercicioDetalhe = new ExercicioDetalhe();
        exercicioDetalhe.setId(mIdExercicioDetalhe);
        exercicioDetalhe.setRep(mEdRepeticao.getText().toString());
        exercicioDetalhe.setSeries(mEdSerie.getText().toString());

        exercicioDetalhe.setExercicio(mExercicio);

        listaExercicio.getExercicio().add(exercicioDetalhe);

        return db.salvaExercicio(listaExercicio, mIdTreino);
    }

    private void atualizaInfoExercicio(String series, String rep) {
        if (mExercicio != null) {
            mCardExercicio.setVisibility(View.VISIBLE);
            mTextExercicio.setText(mExercicio.getNome());
            mEdRepeticao.setText(rep);
            mEdSerie.setText(series);
            mIvGrupo.setImageResource(mExercicio.getGrupoMuscular().getImagem(getActivity()));

            searchExercicio.setQuery("", true);
            searchExercicio.setVisibility(View.GONE);
            mSpBuscaTop.setVisibility(View.GONE);
        }
    }

    private void recuperaExercicio() {
        if (mIdExercicioDetalhe > 0) {
            ExercicioDetalhe exercicioDetalhe = db.getExercicioDetalhe(mIdExercicioDetalhe);
            mExercicio = exercicioDetalhe.getExercicio();

            this.atualizaInfoExercicio(exercicioDetalhe.getSeries(), exercicioDetalhe.getRep());
        }
    }

    private void showResults(String query) {
        if (query != null && !query.isEmpty()) {
            Cursor cursor = db.buscaExercicio(query + "%");

            if (cursor != null) {
                listaExercicioBusca.setVisibility(View.VISIBLE);

                String[] from = new String[]{
                        DatabaseHelper.colExercNome
                };

                int[] to = new int[]{
                        R.id.tv_search_exerciciodescr
                };

                SimpleCursorAdapter exercicios = new SimpleCursorAdapter(getActivity(), R.layout.search_exercicio, cursor, from, to, 0);
                listaExercicioBusca.setAdapter(exercicios);

                listaExercicioBusca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Cursor cursor = (Cursor) listaExercicioBusca.getItemAtPosition(position);

                        mExercicio = new Exercicio();
                        mExercicio.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                        mExercicio.setDescricao(cursor.getString(cursor.getColumnIndex(DatabaseHelper.colExercDescr)));
                        mExercicio.setImagem(cursor.getString(cursor.getColumnIndex(DatabaseHelper.colExercImagem)));
                        mExercicio.setNome(cursor.getString(cursor.getColumnIndex(DatabaseHelper.colExercNome)));
                        mExercicio.setGrupoMuscular(EGrupoMuscular.fromInteger(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.colExercGrupo))));

                        atualizaInfoExercicio("", "");
                    }
                });
            }
        } else {
            listaExercicioBusca.setAdapter(null);
            listaExercicioBusca.setVisibility(View.GONE);

            searchExercicio.clearFocus();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.bt_cancela) {
            mExercicio = null;

            mCardExercicio.setVisibility(View.GONE);
            searchExercicio.setVisibility(View.VISIBLE);
            mSpBuscaTop.setVisibility(View.VISIBLE);
        } else {
            if (id == R.id.bt_confirma) {
                if (this.insereExercicio())
                    getActivity().finish();
            }
        }
    }
}
