package com.prjvt.intellifitn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.DietaHorario;
import com.prjvt.intellifitn.domain.DietaHorarioLista;
import com.prjvt.intellifitn.enumerator.ETipoAlimento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DietaAlimentoActivity extends AppCompatActivity implements Button.OnClickListener {

    private EditText mEdAlimento, mEdHora;
    private DatabaseHelper db;
    private Integer mIdDieta, mIdDietaAlimento;
    private RadioGroup mRgTipoAlimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta_alimento);

        db = new DatabaseHelper(this);
        mIdDieta = getIntent().getExtras().getInt("iddieta", 0);

        mEdAlimento = (EditText) findViewById(R.id.ed_alimento);
        mEdHora = (EditText) findViewById(R.id.ed_hora);
        mRgTipoAlimento = (RadioGroup) findViewById(R.id.rg_tipoalimento);

        ImageButton mBtCancela = (ImageButton) findViewById(R.id.bt_cancela);
        mBtCancela.setOnClickListener(this);
        ImageButton mBtConfirma = (ImageButton) findViewById(R.id.bt_confirma);
        mBtConfirma.setOnClickListener(this);
    }

    private void atualizaInfoAlimento(String alimento, String hora, ETipoAlimento tipoAlimento) {
        mEdAlimento.setText(alimento);
        mEdHora.setText(hora);

        if (tipoAlimento == ETipoAlimento.LIQUIDO)
            mRgTipoAlimento.check(R.id.rb_liquido);
        else
            mRgTipoAlimento.check(R.id.rb_solido);
    }

    private void recuperaAlimento() {
        if (mIdDietaAlimento > 0) {

        }
    }

    private Boolean insereAlimento() throws ParseException {
        DietaHorarioLista dietaHorarioLista = new DietaHorarioLista();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String dateInString = mEdHora.getText().toString();
        Date date = sdf.parse(dateInString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        dietaHorarioLista.setHorario(calendar.getTimeInMillis());

        DietaHorario dietaHorario = new DietaHorario();
        dietaHorario.setId(mIdDietaAlimento);
        dietaHorario.setAlimento(mEdAlimento.getText().toString());

        int id = mRgTipoAlimento.getCheckedRadioButtonId();
        ETipoAlimento tipoAlimento = ETipoAlimento.SOLIDO;
        if (id == R.id.rb_solido)
            tipoAlimento = ETipoAlimento.SOLIDO;
        else
            tipoAlimento = ETipoAlimento.LIQUIDO;

        dietaHorario.setTipoAlimento(tipoAlimento);
        dietaHorario.setIdDieta(mIdDieta);

        dietaHorarioLista.getDietaHorarioList().add(dietaHorario);

        return db.salvaAlimento(dietaHorarioLista, mIdDieta);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_dieta_alimento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.bt_confirma) {
            try {
                if (this.insereAlimento())
                    finish();
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        }

        if (id == R.id.bt_cancela) {
            finish();
        }
    }
}
