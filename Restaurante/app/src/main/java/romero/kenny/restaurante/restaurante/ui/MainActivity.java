package romero.kenny.restaurante.restaurante.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.melnykov.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;

import romero.kenny.restaurante.restaurante.R;
import romero.kenny.restaurante.restaurante.ui.modelos.Contacto;
import romero.kenny.restaurante.restaurante.ui.tools.Constantes;
import romero.kenny.restaurante.restaurante.ui.web.VolleySingleton;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    private String jsn = "json";
    /*
    adaptador del recycler view
     */
    private ContactoAdapter adapter;

    /*
    instancia global del recycler
     */
    private RecyclerView recyclerView;

    /*
    intancia global del administrador
     */
    private RecyclerView.LayoutManager layoutManager;

    private Gson gson = new Gson();

    private static JSONArray mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView)findViewById(R.id.reciclador);
        recyclerView.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Cargar datos en el adaptador
        cargarAdaptador();

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hola ;) ", Toast.LENGTH_LONG).show();
            }
        });

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Carga el adaptador con los contactos obtenidos
     * en la respuesta
     */
    public void cargarAdaptador(){
        //peticion GET
        VolleySingleton.
                getInstance(this).
                addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                Constantes.GET,
                                (String) null,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        // Procesar la respuesta Json
                                        procesarRespuesta(response);
                                    }
                                },
                                new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d(TAG, "Error Volley: " + error.getMessage());
                                    }
                                }
                        )
                );
    }

    /**
     * Interpreta los resultados de la respuesta y así
     * realizar las operaciones correspondientes
     *
     * @param response Objeto Json con la respuesta
     */
    public void procesarRespuesta(JSONObject response){
        try {

            // Obtener array "contacts" Json
            mensaje = response.getJSONArray("contacts");

            //parsear con gson
            Contacto[] contactos = gson.fromJson(mensaje.toString(), Contacto[].class);
            // inicializar el adaptador
            adapter = new ContactoAdapter(Arrays.asList(contactos), this);
            // setear adaptador a la lista
            recyclerView.setAdapter(adapter);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public  static  void launch(Activity activity, String idContacto){
        Intent intent = getLaunchIntent(activity, idContacto, mensaje);
        activity.startActivityForResult(intent, Constantes.CODIGO_DETALLE);
    }

    public static Intent getLaunchIntent(Context context, String idContacto, JSONArray contactos) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constantes.EXTRA_ID, idContacto);
        Bundle b = new Bundle();
        b.putString("array", contactos.toString());
        intent.putExtras(b);
        return intent;
    }


}
