package com.example.login;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.example.login.fragments.BebidasCalientes;
import com.example.login.fragments.BebidasFrias;
import com.example.login.fragments.Comida;
import com.example.login.fragments.Helados;
import com.example.login.fragments.Home;
import com.google.android.material.navigation.NavigationView;

public class Inicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inicio, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment MiFrag=null;
        boolean Seleccionador=false;

        if (id == R.id.Helados) {
            MiFrag=new Helados();
            Seleccionador=true;
        } else if (id == R.id.Bebidas_Frias) {
            MiFrag=new BebidasFrias();
            Seleccionador=true;
        } else if (id == R.id.Bebidas_Calientes) {
            MiFrag=new BebidasCalientes();
            Seleccionador=true;
        } else if (id == R.id.Comida) {
            MiFrag=new Comida();
            Seleccionador=true;
        } else if (id == R.id.home){
            MiFrag=new Home();
            Seleccionador=true;
        }

        if (Seleccionador){
            getSupportFragmentManager().beginTransaction().replace(R.id.Vista,MiFrag).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

