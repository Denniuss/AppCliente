package com.newmantech.appcliente.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newmantech.appcliente.R;
import com.newmantech.appcliente.fragment.CarroFragment;
import com.newmantech.appcliente.fragment.DireccionFragment;
import com.newmantech.appcliente.fragment.ProductoFragment;
import com.newmantech.appcliente.fragment.SeguimientoFragment;

import moe.feng.common.stepperview.demo.fragment.VerticalStepperDemoFragment;

/*
import pe.edu.upc.practicat61_javs.fragments.OpcionesFragment;
import pe.edu.upc.practicat61_javs.model.Usuario;
*/
public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navView = navigationView.getHeaderView(0);
        TextView tvNombre = (TextView) navView.findViewById(R.id.tvNombre);
        TextView tvDescripcion = (TextView) navView.findViewById(R.id.tvDescripcion);
        LinearLayout llPerfil = (LinearLayout) navView.findViewById(R.id.llPerfil);



        /*
        Usuario usuario = getIntent().getParcelableExtra("item");
        if (usuario != null) {
            tvNombre.setText(usuario.getNombreCompleto());
            tvDescripcion.setText(usuario.getResumen());
            llPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MenuActivity.this, PerfilActivity.class);
                    startActivity(intent);
                }
            });
        }*/
        //Intent intent = new Intent(MenuActivity.this, PerfilActivity.class);
        //startActivity(intent);

        //navigationView.getMenu().performIdentifierAction(R.id.nav_habilidades, 0);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_bandeja) {
            toolbar.setTitle("Productos");

            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor, new ProductoFragment())
                    .commit();

            /*
            Intent intent = new Intent(MenuActivity.this, ListadoProductosNActivity.class);
            //intent.putExtra("item", result);
            System.out.print("intent  " + intent);
            startActivity(intent);
            */
            //fragment = new OpcionesFragment();
        /*} else if (id == R.id.nav_habilidades) {
            //fragment = new OpcionesFragment();
        } else if (id == R.id.nav_necesidades) {
            //fragment = new OpcionesFragment();
        } else if (id == R.id.nav_buscador) {*/

        } else if (id == R.id.nav_registrarDireccion) {
            toolbar.setTitle("Direccion");
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor, new DireccionFragment())
                    .commit();
            /*
            Intent intent = new Intent(MenuActivity.this, DireccionActivity.class);
            //intent.putExtra("item", result);
            System.out.print("intent  " + intent);
            startActivity(intent);
            */
        } else if (id == R.id.nav_carritoCompra) {
            toolbar.setTitle("Pedidos");

            //fragment = new VolverAlmacenFragment();
            //Intent newActivity = new Intent(MenuActivity.this,CarritoCompraActivity.class);
            //startActivity(newActivity);
            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor, new CarroFragment())
                    .commit();
            //} else if (id == R.id.nav_direcciones) {
            //fragment = new VolverAlmacenFragment();
            //Intent newActivity = new Intent(this,RegresarAlmacenActivity.class);
            //startActivity(newActivity);
        } else if (id == R.id.nav_cerrar_sesion) {
            Intent intent = new Intent(MenuActivity.this, Pre_LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.nav_seguimiento_pedido) {
            /*Intent intent = new Intent(MenuActivity.this, Pre_LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);*/

            toolbar.setTitle("Seguimiento de Pedidos");

            fragmentManager.beginTransaction()
                    .replace(R.id.contenedor, new SeguimientoFragment())
                    .commit();

            /*fragmentManager.beginTransaction()
                    .replace(R.id.contenedor, new VerticalStepperDemoFragment())
                    .commit();*/
        }

        /*
        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(R.id.contenedor, fragment)
                    .commit();
        }
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
