package com.example.bazazwierzat21;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class OW extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ow);
        EditText result;
        result = findViewById(R.id.text_view_result);
        //result.setEnabled(false);
        String dane_z_servera = "";
        dane_z_servera = Modyfikuj.readFromFile(getApplicationContext(),"baza1.txt");
        if(dane_z_servera.length() > 0) {
            List<Krowa> ListaCala = Modyfikuj.StringNaListe(dane_z_servera);
            ListaCala = Modyfikuj.NieUbyte(ListaCala);
            List<Krowa> ListaOW = Modyfikuj.OW7(ListaCala);
            ListaOW = Modyfikuj.SortListToNumerOborowy(ListaOW);
            ListaOW = Modyfikuj.SortListToGrupa(ListaOW);
            String wynik = "OW\n";
            if (ListaOW.size() > 0) {
                for (int i = 0; i < ListaOW.size(); i++) {
                    wynik += " " + ListaOW.get(i).getNumerOborowy() +
                            "   " + ListaOW.get(i).getNumerIdZwierzecia() +  "  Gr. " + ListaOW.get(i).getGrupa() + "\n\n";
                   //"Ostatnia ins.: " + Modyfikuj.OstatniaInseminacja(ListaOW.get(i)); + "   Grupa:  " + ListaOW.get(i).getGrupa() + "\n\n";
                }
            } else {
                wynik += "Nic do wyświetlenia";
            }
            result.setText(wynik);
        }
    }
}
