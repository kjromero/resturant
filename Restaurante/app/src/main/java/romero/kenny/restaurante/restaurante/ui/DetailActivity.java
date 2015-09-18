package romero.kenny.restaurante.restaurante.ui;


import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;

import romero.kenny.restaurante.restaurante.R;

public class DetailActivity extends AppCompatActivity {

    private TextView nombre;
    private TextView email;
    private TextView genero;
    private TextView mobil;
    private TextView home;
    private TextView office;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nombre = (TextView) findViewById(R.id.txt_nombre);
        email = (TextView) findViewById(R.id.txt_email);
        genero = (TextView) findViewById(R.id.txt_genero);
        mobil = (TextView) findViewById(R.id.txt_mobil);
        home = (TextView) findViewById(R.id.txt_home);
        office = (TextView) findViewById(R.id.txt_ofice);

        Bundle bundle = getIntent().getExtras();
        mostrarContacto(bundle.getString("IDEXTRA"), bundle.getString("array"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

    public void mostrarContacto(String idContacto, String Array ){


        //for (int i = 0; i < contactos.l)
    }
}
