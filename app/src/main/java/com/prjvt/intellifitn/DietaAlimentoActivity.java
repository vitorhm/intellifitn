package com.prjvt.intellifitn;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.prjvt.intellifitn.database.DatabaseHelper;
import com.prjvt.intellifitn.domain.DietaHorario;
import com.prjvt.intellifitn.domain.DietaHorarioLista;
import com.prjvt.intellifitn.enumerator.ETipoAlimento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DietaAlimentoActivity extends AppCompatActivity implements Button.OnClickListener {

    private EditText mEdAlimento, mEdHora;
    private DatabaseHelper db;
    private Integer mIdDieta, mIdDietaHorario;
    private RadioGroup mRgTipoAlimento;
    private DietaHorarioLista dietaHorarioLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta_alimento);

        db = new DatabaseHelper(this);
        mIdDieta = getIntent().getExtras().getInt("iddieta", 0);
        mIdDietaHorario = getIntent().getExtras().getInt("iddietahorario", 0);

        mEdAlimento = (EditText) findViewById(R.id.ed_alimento);
        mEdHora = (EditText) findViewById(R.id.ed_hora);
        mEdHora.setOnClickListener(this);
        mRgTipoAlimento = (RadioGroup) findViewById(R.id.rg_tipoalimento);

        ImageButton mBtCancela = (ImageButton) findViewById(R.id.bt_cancela);
        mBtCancela.setOnClickListener(this);
        ImageButton mBtConfirma = (ImageButton) findViewById(R.id.bt_confirma);
        mBtConfirma.setOnClickListener(this);

        this.recuperaAlimento();
    }

    private void atualizaInfoAlimento() throws ParseException {
        if (dietaHorarioLista != null) {
            if (dietaHorarioLista.getDietaHorarioList().size() > 0) {
                mEdAlimento.setText(dietaHorarioLista.getDietaHorarioList().get(0).getAlimento());

                Long millis = dietaHorarioLista.getHorario();
                String Hora = String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));

                mEdHora.setText(Hora);

                if (dietaHorarioLista.getDietaHorarioList().get(0).getTipoAlimento() == ETipoAlimento.LIQUIDO)
                    mRgTipoAlimento.check(R.id.rb_liquido);
                else
                    mRgTipoAlimento.check(R.id.rb_solido);
            }
        }
    }

    private void recuperaAlimento() {
        if (mIdDietaHorario > 0) {
            dietaHorarioLista = db.getDietaHorario(mIdDietaHorario);

            try {
                this.atualizaInfoAlimento();
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        }
    }

    private Boolean insereAlimento() throws ParseException {
        DietaHorarioLista dietaHorarioLista = new DietaHorarioLista();

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String dateInString = mEdHora.getText().toString();
        Date date = sdf.parse(dateInString);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        dietaHorarioLista.setHorario(calendar.getTimeInMillis());

        DietaHorario dietaHorario = new DietaHorario();
        dietaHorario.setId(mIdDietaHorario);
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

        if (id == R.id.ed_hora) {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    mEdHora.setText( selectedHour + ":" + String.format("%02d", selectedMinute));
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Selecione um Horário");
            mTimePicker.show();
        }
    }
}
