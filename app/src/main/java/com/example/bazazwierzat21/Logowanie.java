package com.example.bazazwierzat21;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Logowanie extends AppCompatActivity {

    Button bntLogo;
    EditText Urzytkownik;
    EditText Tabela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);
        bntLogo = findViewById(R.id.button);
        Urzytkownik = findViewById(R.id.editText);
        Tabela = findViewById(R.id.editText2);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setBntLogo(View v){
        String urzytkownik = Urzytkownik.getText().toString();
        String tabela = Tabela.getText().toString();
        String result = "";
        if(urzytkownik.length() > 0 && tabela.length() > 0){
            String konto = urzytkownik + "_" + tabela;
            PobierzDane dane = new PobierzDane();
            dane.execute("http://www.gospodarstwo-balcerzak.pl/Baza zwierzat.php",konto);
            try {
                result = dane.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if(result.length() > 0){
                Modyfikuj.writeToFile(getApplicationContext(),konto,"nazwaTabeli.txt");
                finish();

            }else{
                Toast.makeText(getApplicationContext(),urzytkownik + " albo " + tabela + "\nTo nie jest prawidłowa nazwa",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
