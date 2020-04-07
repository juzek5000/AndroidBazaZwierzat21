package com.example.bazazwierzat21;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class PG extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg);
        EditText result;
        result = findViewById(R.id.text_view_result);
        //result.setEnabled(false);
        String dane_z_servera = "";
        dane_z_servera = Modyfikuj.readFromFile(getApplicationContext(),"baza1.txt");
        if(dane_z_servera.length() > 0){
            List<Krowa> ListaCala = Modyfikuj.StringNaListe(dane_z_servera);
            ListaCala = Modyfikuj.NieUbyte(ListaCala);
            List<Krowa> ListaPG = Modyfikuj.PG(ListaCala);
            ListaPG = Modyfikuj.SortListToNumerOborowy(ListaPG);
            ListaPG = Modyfikuj.SortListToGrupa(ListaPG);
            String wynik = "PG\n\n";
            if(ListaPG.size() > 0){
                for(int i = 0; i < ListaPG.size();i++){
                    wynik += " " + ListaPG.get(i).getNumerOborowy() +
                            "   " + ListaPG.get(i).getNumerIdZwierzecia() +  "  Gr. " + ListaPG.get(i).getGrupa() + "\n\n";
                }
            }else{
                wynik += "Nic do wyświetlenia";
            }
            result.setText(wynik);
        }
    }
}
