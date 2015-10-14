package com.prjvt.intellifitn.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.prjvt.intellifitn.domain.DiasExercicio;
import com.prjvt.intellifitn.domain.Dieta;
import com.prjvt.intellifitn.domain.DietaHorario;
import com.prjvt.intellifitn.domain.DietaHorarioLista;
import com.prjvt.intellifitn.domain.Exercicio;
import com.prjvt.intellifitn.domain.ExercicioDetalhe;
import com.prjvt.intellifitn.domain.Objetivo;
import com.prjvt.intellifitn.domain.Treino;
import com.prjvt.intellifitn.enumerator.EDias;
import com.prjvt.intellifitn.enumerator.EGrupoMuscular;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitor on 18/05/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String dbName = "dbintellifitn";

    // CAD_DIETA
    public static final String tabCadDieta      = "CAD_DIETA";
    public static final String colDietaId       = "DIETAID";
    public static final String colDietaNome     = "DIETANOME";
    public static final String colDietaDataLanc = "DIETADATA";

    // DIETA_HORARIO
    public static final String tabDietaHorario          = "DIETA_HORARIO";

    public static final String colDietaHorarioId        = "DIETAHOR_ID";
    public static final String colDietaHorarioIdDieta   = "DIETA_IDDIETA";
    public static final String colDietaHorarioAlimento  = "DIETAHOR_ALIMENTO";
    public static final String colDietaHorario          = "DIETAHOR_HORARIO";

    // CAD_EXERCICIO
    public static final String tabCadExercicio     = "CAD_EXERCICIO";

    public static final String colExercId          = "EXERCID";
    public static final String colExercNome        = "EXERCNOME";
    public static final String colExercDescr       = "EXERCDESC";
    public static final String colExercImagem      = "EXERCIMAGEM";
    public static final String colExercGrupo       = "EXERCGRUPO";

    // CAD_OBJETIVO
    public static final String tabCadObjetivo      = "CAD_OBJETIVO";

    public static final String colObjetivoId       = "OBJID";
    public static final String colObjetivoDescr    = "OBJDESCR";

    // CAD_TREINO
    public static final String tabCadtreino      = "CAD_TREINO";

    public static final String colTreinoId       = "TREINOID";
    public static final String colTreinoDescr    = "TEINODESC";
    public static final String colTreinoDataLanc = "TREINODATA";
    public static final String colTreinoObjetivo = "TREINOOBJ";

    // TREINO_EXERC
    public static final String tabTreinoExerc      = "TREINO_EXERC";

    public static final String colTreinoExId       = "TREINOEX_ID";
    public static final String colTreinoExIdExerc  = "TREINOEX_IDEXERC";
    public static final String colTreinoExIdTreino = "TREINOEX_IDTREINO";
    public static final String colTreinoExDia      = "TREINOEX_DIA";
    public static final String colTreinoExSeries   = "TREINOEX_SERIES";
    public static final String colTreinoExRep      = "TREINOEX_REP";

    public DatabaseHelper(Context context) {
        super(context, dbName, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i("Banco", "OnCreate()");

        db.execSQL("CREATE TABLE " + tabCadExercicio + " (" +
                        colExercId + " INTEGER PRIMARY KEY, " +
                        colExercNome + " TEXT NOT NULL, " +
                        colExercImagem + " TEXT NOT NULL, " +
                        colExercDescr + " TEXT NOT NULL, " +
                        colExercGrupo + " INTEGER NOT NULL)"
        );

        db.execSQL("CREATE TABLE " + tabCadtreino + " (" +
                colTreinoId + " INTEGER PRIMARY KEY, " +
                colTreinoDescr + " TEXT NOT NULL, " +
                colTreinoObjetivo + " INTEGER NOT NULL, " +
                colTreinoDataLanc + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)");

        db.execSQL("CREATE TABLE " + tabCadObjetivo + " (" +
                colObjetivoId + " INTEGER PRIMARY KEY, " +
                colObjetivoDescr + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + tabTreinoExerc + " (" +
                colTreinoExId + " INTEGER PRIMARY KEY, " +
                colTreinoExIdExerc + " INTEGER NOT NULL, " +
                colTreinoExIdTreino + " INTEGER NOT NULL, " +
                colTreinoExDia + " INTEGER NOT NULL, " +
                colTreinoExSeries + " TEXT NULL, " +
                colTreinoExRep + " TEXT NULL)");

        db.execSQL("CREATE TABLE " + tabCadDieta + " (" +
                colDietaId + " INTEGER PRIMARY KEY, " +
                colDietaDataLanc + " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                colDietaNome + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + tabDietaHorario + " (" +
                colDietaHorarioId + " INTEGER PRIMARY KEY, " +
                colDietaHorarioIdDieta + " INTEGER NOT NULL, " +
                colDietaHorarioAlimento + " TEXT NOT NULL, " +
                colDietaHorario + " BIGINT NOT NULL)");

        ContentValues cv = new ContentValues();

        cv.clear();
        cv.put(colObjetivoDescr, "Ganho de Massa");
        db.insert(tabCadObjetivo, colObjetivoDescr, cv);

        cv.clear();
        cv.put(colObjetivoDescr, "Emagrecimento");
        db.insert(tabCadObjetivo, colObjetivoDescr, cv);

        EGrupoMuscular grupo = EGrupoMuscular.CHEST;
        cv.clear();
        cv.put(colExercNome, "Supino Reto com Barra");
        cv.put(colExercDescr, "Descer a barra até a área média do tórax. Subir a barra até estender totalmente os braços.");
        cv.put(colExercImagem, "http://exrx.net/AnimatedEx/PectoralSternal/BBBenchPress.gif");
        cv.put(colExercGrupo, grupo.getGrupo());
        db.insert(tabCadExercicio, colExercDescr, cv);

        cv.clear();
        cv.put(colExercNome, "Supino Inclinado com Barra");
        cv.put(colExercDescr, "");
        cv.put(colExercImagem, "");
        cv.put(colExercGrupo, grupo.getGrupo());
        db.insert(tabCadExercicio, colExercDescr, cv);

        cv.clear();
        cv.put(colExercNome, "Supino Declinado com Barra");
        cv.put(colExercDescr, "");
        cv.put(colExercImagem, "");
        cv.put(colExercGrupo, grupo.getGrupo());
        db.insert(tabCadExercicio, colExercDescr, cv);

        cv.clear();
        cv.put(colExercNome, "Crucifixo com Halters");
        cv.put(colExercDescr, "");
        cv.put(colExercImagem, "");
        cv.put(colExercGrupo, grupo.getGrupo());
        db.insert(tabCadExercicio, colExercDescr, cv);

        grupo = EGrupoMuscular.BICEPS;
        cv.clear();
        cv.put(colExercNome, "Rosca Direta");
        cv.put(colExercDescr, "");
        cv.put(colExercImagem, "");
        cv.put(colExercGrupo, grupo.getGrupo());
        db.insert(tabCadExercicio, colExercDescr, cv);

        cv.clear();
        cv.put(colExercNome, "Rosca Concentrada");
        cv.put(colExercDescr, "");
        cv.put(colExercImagem, "");
        cv.put(colExercGrupo, grupo.getGrupo());
        db.insert(tabCadExercicio, colExercDescr, cv);

        grupo = EGrupoMuscular.BACK;
        cv.clear();
        cv.put(colExercNome, "Remada Unilateral");
        cv.put(colExercDescr, "");
        cv.put(colExercImagem, "");
        cv.put(colExercGrupo, grupo.getGrupo());
        db.insert(tabCadExercicio, colExercDescr, cv);

        cv.clear();
        cv.put(colExercNome, "Puxada na Frente com Polia Alta");
        cv.put(colExercDescr, "");
        cv.put(colExercImagem, "");
        cv.put(colExercGrupo, grupo.getGrupo());
        db.insert(tabCadExercicio, colExercDescr, cv);
}

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Exercicio getExercicio(int idexercicio) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + tabCadExercicio +
                        " WHERE " + colExercId + " = " + idexercicio,
                new String[]{});
        Exercicio exercicio = new Exercicio();
        if (cur != null) {
            boolean hasRecord = cur.moveToFirst();

            if (hasRecord) {
                if (!cur.isNull(cur.getColumnIndex(colExercDescr)))
                    exercicio.setDescricao(cur.getString(cur.getColumnIndex(colExercDescr)));
                if (!cur.isNull(cur.getColumnIndex(colExercNome)))
                    exercicio.setNome(cur.getString(cur.getColumnIndex(colExercNome)));
                if (!cur.isNull(cur.getColumnIndex(colExercImagem)))
                    exercicio.setImagem(cur.getString(cur.getColumnIndex(colExercImagem)));
                if (!cur.isNull(cur.getColumnIndex(colExercId)))
                    exercicio.setId(cur.getInt(cur.getColumnIndex(colExercId)));
                if (!cur.isNull(cur.getColumnIndex(colExercGrupo)))
                    exercicio.setGrupoMuscular(EGrupoMuscular.fromInteger(cur.getInt(cur.getColumnIndex(colExercGrupo))));
            }
        }

        return exercicio;
    }

    public ExercicioDetalhe getExercicioDetalhe(int idExercicioDetalhe) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT * FROM " + tabTreinoExerc +
                        " WHERE " + colTreinoExId + " = " + idExercicioDetalhe,
                new String[]{});

        ExercicioDetalhe exercicioDetalhe = new ExercicioDetalhe();
        if (cur != null) {
            boolean hasRecord = cur.moveToFirst();

            if (hasRecord) {
                Exercicio exercicio = getExercicio(cur.getInt(cur.getColumnIndex(colTreinoExIdExerc)));

                exercicioDetalhe.setExercicio(exercicio);
                if (!cur.isNull(cur.getColumnIndex(colTreinoExId)))
                    exercicioDetalhe.setId(cur.getInt(cur.getColumnIndex(colTreinoExId)));
                if (!cur.isNull(cur.getColumnIndex(colTreinoExRep)))
                    exercicioDetalhe.setRep(cur.getString(cur.getColumnIndex(colTreinoExRep)));
                if (!cur.isNull(cur.getColumnIndex(colTreinoExSeries)))
                    exercicioDetalhe.setSeries(cur.getString(cur.getColumnIndex(colTreinoExSeries)));
            }
        }

        return exercicioDetalhe;
    }

    public List<DiasExercicio> getExerciciosDia(EDias diaAtual) throws SQLException {
        Log.i("Banco", "getExerciciosDia");
        List<DiasExercicio> listaExercicios = new ArrayList<DiasExercicio>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + tabTreinoExerc +
                " WHERE " + colTreinoExDia + " = " + diaAtual.ordinal() +
                " ORDER BY " + colTreinoExId;
        Cursor cur = db.rawQuery(query,
                new String [] {});

        if (cur != null) {
            while (cur.moveToNext()) {
                DiasExercicio diasExercicio = new DiasExercicio();
                EDias dia;

                dia = EDias.fromInteger(cur.getInt(cur.getColumnIndex(colTreinoExDia)));
                diasExercicio.setDia(dia);
                int idtreino = cur.getInt(cur.getColumnIndex(colTreinoExIdTreino));
                boolean notFinish = true;
                while (notFinish && idtreino == cur.getInt(cur.getColumnIndex(colTreinoExIdTreino))) {
                    Exercicio exercicio = getExercicio(cur.getInt(cur.getColumnIndex(colTreinoExIdExerc)));
                    ExercicioDetalhe exercicioDetalhe = new ExercicioDetalhe();

                    exercicioDetalhe.setExercicio(exercicio);

                    if (!cur.isNull(cur.getColumnIndex(colTreinoExId)))
                        exercicioDetalhe.setId(cur.getInt(cur.getColumnIndex(colTreinoExId)));
                    if (!cur.isNull(cur.getColumnIndex(colTreinoExRep)))
                        exercicioDetalhe.setRep(cur.getString(cur.getColumnIndex(colTreinoExRep)));
                    if (!cur.isNull(cur.getColumnIndex(colTreinoExSeries)))
                        exercicioDetalhe.setSeries(cur.getString(cur.getColumnIndex(colTreinoExSeries)));

                    diasExercicio.getExercicio().add(exercicioDetalhe);
                    notFinish = cur.moveToNext();
                }

                cur.moveToPrevious();
                listaExercicios.add(diasExercicio);
            }

            cur.close();
        }

        db.close();
        return listaExercicios;
    }

    public List<DietaHorarioLista> getDietaHorarioLista(int iddieta) {
        Log.i("Banco", "getDietaHorarioLista()");
        List<DietaHorarioLista> listaDietaHorario = new ArrayList<DietaHorarioLista>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + tabDietaHorario +
                " WHERE " + colDietaHorarioIdDieta + " = " + iddieta +
                " ORDER BY " + colDietaHorario;
        Cursor cur = db.rawQuery(query,
                new String [] {});

        if (cur != null) {
            while (cur.moveToNext()) {
                DietaHorarioLista dietaHorarioLista = new DietaHorarioLista();
                Long horario;

                horario = cur.getLong(cur.getColumnIndex(colDietaHorario));
                dietaHorarioLista.setHorario(horario);
                boolean notFinish = true;
                while (notFinish && horario == cur.getLong(cur.getColumnIndex(colDietaHorario))) {
                    DietaHorario dietaHorario = new DietaHorario();

                    if (!cur.isNull(cur.getColumnIndex(colDietaHorarioId)))
                        dietaHorario.setId(cur.getInt(cur.getColumnIndex(colDietaHorarioId)));
                    if (!cur.isNull(cur.getColumnIndex(colDietaHorarioAlimento)))
                        dietaHorario.setAlimento(cur.getString(cur.getColumnIndex(colDietaHorarioAlimento)));
                    if (!cur.isNull(cur.getColumnIndex(colDietaHorarioIdDieta)))
                        dietaHorario.setIdDieta(cur.getInt(cur.getColumnIndex(colDietaHorarioIdDieta)));

                    dietaHorarioLista.getDietaHorarioList().add(dietaHorario);
                    notFinish = cur.moveToNext();
                }

                cur.moveToPrevious();
                listaDietaHorario.add(dietaHorarioLista);
            }

            cur.close();
        }

        db.close();
        return listaDietaHorario;
    }

    public List<DiasExercicio> getExerciciosTreino(int idtreino) throws SQLException {
        Log.i("Banco", "getExerciciosTreino()");
        List<DiasExercicio> listaExercicios = new ArrayList<DiasExercicio>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + tabTreinoExerc +
                " WHERE " + colTreinoExIdTreino + " = " + idtreino +
                " ORDER BY " + colTreinoExDia;
        Cursor cur = db.rawQuery(query,
                new String [] {});

        if (cur != null) {
            while (cur.moveToNext()) {
                DiasExercicio diasExercicio = new DiasExercicio();
                EDias dia;

                dia = EDias.fromInteger(cur.getInt(cur.getColumnIndex(colTreinoExDia)));
                diasExercicio.setDia(dia);
                boolean notFinish = true;
                while (notFinish && dia == EDias.fromInteger(cur.getInt(cur.getColumnIndex(colTreinoExDia)))) {
                    Exercicio exercicio = getExercicio(cur.getInt(cur.getColumnIndex(colTreinoExIdExerc)));
                    ExercicioDetalhe exercicioDetalhe = new ExercicioDetalhe();

                    exercicioDetalhe.setExercicio(exercicio);

                    if (!cur.isNull(cur.getColumnIndex(colTreinoExId)))
                        exercicioDetalhe.setId(cur.getInt(cur.getColumnIndex(colTreinoExId)));
                    if (!cur.isNull(cur.getColumnIndex(colTreinoExRep)))
                        exercicioDetalhe.setRep(cur.getString(cur.getColumnIndex(colTreinoExRep)));
                    if (!cur.isNull(cur.getColumnIndex(colTreinoExSeries)))
                        exercicioDetalhe.setSeries(cur.getString(cur.getColumnIndex(colTreinoExSeries)));

                    diasExercicio.getExercicio().add(exercicioDetalhe);
                    notFinish = cur.moveToNext();
                }

                cur.moveToPrevious();
                listaExercicios.add(diasExercicio);
            }

            cur.close();
        }

        db.close();
        return listaExercicios;
    }

    public Treino getTreino(int id) throws SQLException{
        Treino treino = null;

        if (id > 0) {
            List<Treino> listaTreino = getListaTreino(id);
            if (listaTreino.size() > 0) {
                treino = listaTreino.get(0);
            }
        }

        return treino;
    }

    public List<Treino> getListaTreino() throws SQLException {
        return getListaTreino(0);
    }

    public Objetivo getObjetivo(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur;

        String sql;
        sql = "SELECT * FROM " + tabCadObjetivo + " WHERE " + colObjetivoId + " = ?";
        cur = db.rawQuery(sql, new String [] {id.toString()});

        Objetivo objetivo = new Objetivo();
        if (cur != null) {
            while (cur.moveToNext()) {
                if (!cur.isNull(cur.getColumnIndex(colObjetivoDescr)))
                    objetivo.setDescr(cur.getString(cur.getColumnIndex(colObjetivoDescr)));

                if (!cur.isNull(cur.getColumnIndex(colObjetivoId)))
                    objetivo.setId(cur.getInt(cur.getColumnIndex(colObjetivoId)));
            }

            cur.close();
        }

        db.close();
        return objetivo;
    }

    public List<Dieta> getListaDieta(Integer id) throws  SQLException {
        Log.i("Banco", "getListaDieta()");
        List<Dieta> listaDieta = new ArrayList<Dieta>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur;

        String sql;
        sql = "SELECT * FROM " + tabCadDieta;

        if (id > 0) {
            sql = sql + " WHERE " + colDietaId + " = ?";
            cur = db.rawQuery(sql, new String [] {id.toString()});
        } else
            cur = db.rawQuery(sql, new String [] {});

        if (cur != null) {
            while (cur.moveToNext()) {
                String Descr = "";
                int Id = 0;
                String DataLanc = "";

                if (!cur.isNull(cur.getColumnIndex(colDietaNome)))
                    Descr = cur.getString(cur.getColumnIndex(colDietaNome));

                if (!cur.isNull(cur.getColumnIndex(colDietaId)))
                    Id = cur.getInt(cur.getColumnIndex(colDietaId));

                if (!cur.isNull(cur.getColumnIndex(colDietaDataLanc)))
                    DataLanc = cur.getString(cur.getColumnIndex(colDietaDataLanc));

                Dieta dieta = new Dieta();
                dieta.setDescricao(Descr);
                dieta.setId(Id);
                dieta.setDataLanc(DataLanc);
                dieta.setListaDietaHorario(getDietaHorarioLista(dieta.getId()));

                listaDieta.add(dieta);
            }

            cur.close();
        }

        db.close();
        return listaDieta;
    }

    private List<Treino> getListaTreino(Integer id) throws SQLException {
        Log.i("Banco", "getTreino()");
        List<Treino> listaTreino = new ArrayList<Treino>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur;

        String sql;
        sql = "SELECT * FROM " + tabCadtreino;

        if (id > 0) {
            sql = sql + " WHERE " + colTreinoId + " = ?";
            cur = db.rawQuery(sql, new String [] {id.toString()});
        } else
            cur = db.rawQuery(sql, new String [] {});

        if (cur != null) {
            while (cur.moveToNext()) {
                String Descr = "";
                int Id = 0;
                String DataLanc = "";
                Objetivo obj = getObjetivo(cur.getInt(cur.getColumnIndex(colTreinoObjetivo)));

                if (!cur.isNull(cur.getColumnIndex(colTreinoDescr)))
                    Descr = cur.getString(cur.getColumnIndex(colTreinoDescr));

                if (!cur.isNull(cur.getColumnIndex(colTreinoId)))
                    Id = cur.getInt(cur.getColumnIndex(colTreinoId));

                if (!cur.isNull(cur.getColumnIndex(colTreinoDataLanc)))
                    DataLanc = cur.getString(cur.getColumnIndex(colTreinoDataLanc));

                Treino treino = new Treino(Id, Descr, DataLanc, obj);
                treino.setListaExercicio(getExerciciosTreino(treino.getId()));
                listaTreino.add(treino);
            }

            cur.close();
        }

        db.close();
        return listaTreino;
    }

    public Boolean salvaExercicio(DiasExercicio exercicio, int idtreino) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            ContentValues cv = new ContentValues();

            for (int i = 0; i < exercicio.getExercicio().size(); i++) {
                cv.clear();

                cv.put(colTreinoExIdExerc, exercicio.getExercicio().get(i).getExercicio().getId());
                cv.put(colTreinoExIdTreino, idtreino);
                cv.put(colTreinoExDia, exercicio.getDia().ordinal());
                cv.put(colTreinoExRep, exercicio.getExercicio().get(i).getRep());
                cv.put(colTreinoExSeries, exercicio.getExercicio().get(i).getSeries());

                if (exercicio.getExercicio().get(i).getId() > 0) {
                    String whereClause = colTreinoExId + " = ?";
                    String[] whereArgs = new String[]{exercicio.getExercicio().get(i).getId().toString()};

                    int hasUpdated = db.update(tabTreinoExerc, cv, whereClause, whereArgs);
                    if (hasUpdated == 0) {
                        db.insert(tabTreinoExerc, null, cv);
                    }
                } else {
                    db.insert(tabTreinoExerc, null, cv);
                }
            }

        } finally {
            db.close();
        }

        return true;
    }

    public Boolean salvaTreino(Treino treino) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(colTreinoDescr, treino.getDescricao());
        cv.put(colTreinoDataLanc, treino.getDataLanc());
        cv.put(colTreinoObjetivo, 0);
//        cv.put(colTreinoObjetivo, treino.getObjetivo().getId());

        try {
            if (treino.getId() > 0) {
                String whereClause = colTreinoId + " = ?";
                String[] whereArgs = new String[]{treino.getId().toString()};

                int i = db.update(tabCadtreino, cv, whereClause, whereArgs);
                if (i == 0) {
                    treino.setId((int) db.insert(tabCadtreino, null, cv));
                }
            } else {
                treino.setId((int) db.insert(tabCadtreino, null, cv));
            }

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            db.close();
        }
    }

    public Boolean salvaTreinoExercicio(Treino treino) {
        this.salvaTreino(treino);

        for (int i = 0; i < treino.getListaExercicio().size() - 1; i++) {
            this.salvaExercicio(treino.getListaExercicio().get(i), treino.getId());
        }

        return true;
    }

    public int getLastInsertId(SQLiteDatabase db) {
        String sql;
        sql = String.format("select last_insert_rowid() as lastid");

        Cursor mCursor = db.rawQuery(sql, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
            return mCursor.getInt(mCursor.getColumnIndex("lastid"));
        } else
            return 0;
    }

    public Cursor buscaExercicio(String inputText) {
        Log.w("DataBaseHelper", inputText);
        String query = "select " + colExercId + " as _id," + "*" +
                " from " + tabCadExercicio +
                " where " +  colExercNome + " LIKE '" + inputText + "';";

        Log.w("DataBaseHelper(Query)", query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery(query,null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        db.close();

        return mCursor;
    }
}
