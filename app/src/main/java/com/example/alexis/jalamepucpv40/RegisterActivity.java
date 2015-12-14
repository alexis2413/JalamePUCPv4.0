package com.example.alexis.jalamepucpv40;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by samoel on 13/12 /2015.
 */
public class RegisterActivity extends Activity {

    @Override
    public void onCreate(Bundle x) {
        super.onCreate(x);
        setContentView(R.layout.register_activity);
        final Context context = this;
        Button btnRegresar = (Button) findViewById(R.id.btnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        final EditText etUsername = (EditText) findViewById(R.id.etUsuario);
        final EditText etPass = (EditText) findViewById(R.id.etPass);
        final EditText etNombre = (EditText) findViewById(R.id.etNombre);
        final EditText etcodPucp = (EditText) findViewById(R.id.etCodPucp);
        final EditText etCorreo = (EditText) findViewById(R.id.etCorreo);
        final EditText etDistrito = (EditText) findViewById(R.id.etDistrito);
        final EditText etTelf = (EditText) findViewById(R.id.etTelf);

        //Para Registrarse
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString();
                user = user.replace(" ", "%20");
                if (!LogInController.VerificaRepetido(user)) {
                    String nombre = etNombre.getText().toString();
                    nombre = nombre.replace(" ", "%20");
                    String pass = etPass.getText().toString();
                    pass = pass.replace(" ", "%20");
                    String codPucp = etcodPucp.getText().toString();
                    codPucp = codPucp.replace(" ", "%20");
                    String correo = etCorreo.getText().toString();
                    correo = correo.replace(" ", "%20");
                    String dist = etDistrito.getText().toString();
                    dist = dist.replace(" ", "%20");
                    String telf = etTelf.getText().toString();
                    telf = telf.replace(" ", "%20");
                    String respuesta = LogInController.RegisterUser(nombre, user, pass, codPucp, correo, dist, telf);
                    Toast.makeText(getApplicationContext(), respuesta, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario ya existente", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
