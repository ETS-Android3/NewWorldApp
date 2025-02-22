package com.newworld.newworldapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.newworld.newworldapp.db.DbHelper;
import com.newworld.newworldapp.utils.ToolBar;

import java.util.List;

public class AsentamientoActivity extends AppCompatActivity {
    private DbHelper dbHelper;
    private String asentamiento;
    private String events_in;
    private String no_events;
    private String inventory_in;
    private String no_objects;
    private final ToolBar aux = new ToolBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asentamiento);

        Resources res = getResources();
        events_in = res.getString(R.string.eventos_in_yes);
        no_events = res.getString(R.string.eventos_in_no);
        inventory_in = res.getString(R.string.objects_yes);
        no_objects = res.getString(R.string.objects_no);

        dbHelper = (DbHelper) SingletonMap.getInstance().get("dbh");

        asentamiento = (String)SingletonMap.getInstance().get("asentamiento");

        TextView textAsentamiento = (TextView) findViewById(R.id.textAsentamiento);
        textAsentamiento.setText(asentamiento);

        Toolbar toolbar = findViewById(R.id.tr_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayShowHomeEnabled(false);
        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(R.string.app_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.english: aux.cambiarIdioma(this,"en","Selected language: english");
                break;
            case R.id.spain: aux.cambiarIdioma(this,"es","Idioma seleccionado: español");
                break;
            //Idioma español por defecto
            default: aux.cambiarIdioma(this,"es","Idioma seleccionado: español");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickB_Eventos (View view) {
        if(dbHelper.EventosIsNotEmpty(asentamiento)){
            Toast.makeText(getApplicationContext(),events_in + " " + asentamiento,Toast.LENGTH_SHORT).show();
            Intent intento = new Intent(this, Eventos_Asentamiento.class);
            startActivity(intento);
        }else{
            Toast.makeText(getApplicationContext(),no_events + " " + asentamiento,Toast.LENGTH_SHORT).show();
        }
    }

    public void clickB_Inventario (View view) {
        if(dbHelper.InventariosIsNotEmpty(asentamiento)){
            Toast.makeText(getApplicationContext(),inventory_in + " " + asentamiento,Toast.LENGTH_SHORT).show();
            Intent intento = new Intent(this, Inventario_Asentamiento.class);
            startActivity(intento);
        }else{
            Toast.makeText(getApplicationContext(),no_objects + " " + asentamiento,Toast.LENGTH_SHORT).show();
        }
    }
}