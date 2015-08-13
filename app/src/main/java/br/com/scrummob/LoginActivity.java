package br.com.scrummob;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;
import java.util.List;

import br.com.scrummob.dao.AtividadeDao;
import br.com.scrummob.model.Atividade;


public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AtividadeDao atividadeDao = new AtividadeDao(this);
        long id = atividadeDao.save(new Atividade("Atividade 1", Atividade.Status.ABERTO, 10, 10, new Date(), "Atividade Teste"));
        atividadeDao.save(new Atividade(id, "Atividade 1 Atualizada", Atividade.Status.ABERTO, 10, 10, new Date(), "Atividade Teste 12"));
        List<Atividade> atividades = atividadeDao.list();

        setContentView(br.com.scrummob.R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(br.com.scrummob.R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == br.com.scrummob.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
