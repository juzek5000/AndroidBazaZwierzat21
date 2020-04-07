package com.example.bazazwierzat21;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.time.LocalDate;
import java.util.List;

public class wycielenia extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wycielenia);
        EditText result;
        result = findViewById(R.id.text_view_result);
        //result.setEnabled(false);
        String dane_z_servera = "";
        dane_z_servera = Modyfikuj.readFromFile(getApplicationContext(),"baza1.txt");
        if(dane_z_servera.length() > 0) {
            List<Krowa> ListaCala = Modyfikuj.StringNaListe(dane_z_servera);
            ListaCala = Modyfikuj.NieUbyte(ListaCala);
            List<Krowa> Lista = Modyfikuj.Wycielenia(ListaCala);
            String wynik = "Wycielenia\n\n";
            if (Lista.size() > 0) {
                for (int i = 0; i < Lista.size(); i++) {
                    LocalDate dataZacielenia = Modyfikuj.StringNaDate(Lista.get(i).getDataZacielenia());
                    dataZacielenia = dataZacielenia.plusDays(280);
                    wynik += " " + Lista.get(i).getNumerOborowy() +
                            "   " + Lista.get(i).getNumerIdZwierzecia() +  "    Gr. " + Lista.get(i).getGrupa() + "\n ";
                    //wynik += Lista.get(i).getNazwaZwierzecia() +"\n ";
                    wynik += "Data wyć.: " + dataZacielenia + "\n\n";
                }
            } else {
                wynik += "Nic do wyświetlenia";
            }
            result.setText(wynik);
        }
    }
}
