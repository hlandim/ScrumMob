package br.com.scrummob.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.scrummob.model.Atividade;
import br.com.scrummob.model.Atividade.Status;

/**
 * Created by hlandim on 17/06/15.
 */
public class AtividadeDao {

    private static final String TABELA_NOME = "atividade";
    private static final String COLULNA_ID = "id";
    //public static final String COLULNA_ID_RESPONSAVEL = "id_responsavel";
    //public static final String COLULNA_ID_SPRINT = "id_sprint";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_STATUS = "status";
    private static final String COLUNA_PONTO = "ponto";
    private static final String COLUNA_PRIORIDADE = "prioridade";
    private static final String COLUNA_DESCRICAO = "descricao";
    private static final String COLUNA_DATA_CRIACAO = "dataCriacao";

    private ScrumMobSQLHelper helper;

    public AtividadeDao(Context context) {
        this.helper = new ScrumMobSQLHelper(context);
    }

    public static String getSqlCreate() {
        return "CREATE TABLE " + TABELA_NOME + " (" + COLULNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUNA_NOME + " TEXT NOT NULL," +
                COLUNA_STATUS + " TEXT NOT NULL," +
                COLUNA_PONTO + " INTEGER NOT NULL," +
                COLUNA_PRIORIDADE + " INTEGER NOT NULL," +
                COLUNA_DESCRICAO + " TEXT NOT NULL," +
                COLUNA_DATA_CRIACAO + " INTEGER NOT NULL"
                + ")";
    }

    public List<Atividade> list() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABELA_NOME, null);
        List<Atividade> retorno = montarAtividades(cursor);
        cursor.close();
        db.close();
        return retorno;
    }

    public long save(Atividade atividade){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLULNA_ID, atividade.getId());
        cv.put(COLUNA_NOME,atividade.getNome());
        cv.put(COLUNA_STATUS,atividade.getStatus().name());
        cv.put(COLUNA_PONTO,atividade.getPonto());
        cv.put(COLUNA_PRIORIDADE,atividade.getPrioridade());
        cv.put(COLUNA_DESCRICAO,atividade.getDescricao());
        cv.put(COLUNA_DATA_CRIACAO,atividade.getDataCriacao().getTime());
        long id = db.insertWithOnConflict(TABELA_NOME, COLULNA_ID, cv, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
        return id;

    }

    public List<Atividade> buscarPorNome(String nome){
        if(nome != null) {
            SQLiteDatabase db = helper.getReadableDatabase();
            String sql = "SELECT * FROM " + TABELA_NOME + " WHERE " + COLUNA_NOME + " LIKE ?";
            String []argumentos = new String[]{nome};
            sql += "ORDER BY " + COLUNA_NOME;
            Cursor cursor = db.rawQuery(sql, argumentos);
            List<Atividade> retorno = montarAtividades(cursor);
            cursor.close();
            db.close();
            return retorno;
        }
        return null;
    }

    private List<Atividade> montarAtividades(Cursor cursor) {
        List<Atividade> list = new ArrayList<Atividade>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String nome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME));
                Status status = Status.valueOf(cursor.getString(cursor.getColumnIndex(COLUNA_STATUS)));
                Integer ponto = cursor.getInt(cursor.getColumnIndex(COLUNA_PONTO));
                Integer prioridade = cursor.getInt(cursor.getColumnIndex(COLUNA_PRIORIDADE));
                String descricao = cursor.getString(cursor.getColumnIndex(COLUNA_DESCRICAO));
                Date data = new Date(cursor.getLong(cursor.getColumnIndex(COLUNA_DATA_CRIACAO)));
                Long id = cursor.getLong(cursor.getColumnIndex(COLULNA_ID));
                Atividade atividade = new Atividade(id, nome, status, ponto, prioridade, data, descricao);
                list.add(atividade);
            }
        }
        return list;
    }
}
