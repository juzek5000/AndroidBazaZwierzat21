package com.example.bazazwierzat21;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class synchronizacja extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronizacja);
        EditText result;
        result = findViewById(R.id.text_view_result);
        String dane_z_servera = "";
        dane_z_servera = Modyfikuj.readFromFile(getApplicationContext(),"baza1.txt");
        if(dane_z_servera.length() > 0) {
            List<Krowa> ListaCala = Modyfikuj.StringNaListe(dane_z_servera);
            ListaCala = Modyfikuj.NieUbyte(ListaCala);
            //ListaCala = Modyfikuj.Krowy(ListaCala);
            ListaCala = Modyfikuj.NieCielne(ListaCala);
            ListaCala = Modyfikuj.SortListToNumerOborowy(ListaCala);
            ListaCala = Modyfikuj.SortListToGrupa(ListaCala);
            String strDataOV7;
            String strDataG6G;
            String strDataG5G;
            String uwagi;
            int indexG6G;
            int indexOV7;
            int indexG5G;
            String wynik = "";
            for(Krowa item:ListaCala) {
                uwagi = item.getUwagi().toUpperCase();
                indexG6G = uwagi.indexOf("G6G");
                indexG5G = uwagi.indexOf("G5G");
                indexOV7 = uwagi.indexOf("OV7");
                if(indexOV7 >= 0){
                    strDataOV7 = uwagi.substring(indexOV7 + 4,indexOV7 + 14);
                    LocalDate dDataOV7 = Modyfikuj.StringNaDate(strDataOV7);
                    if(dDataOV7 != null){
                        if(dDataOV7.toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Wieczorem OW 3ml\n\n";
                        }
                        if(dDataOV7.plusDays(7).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano OW 2ml\n\n";
                        }
                        if(dDataOV7.plusDays(14).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "    Gr.   " + item.getGrupa() +
                                    "\n Rano i Wieczorem PG 2ml\n\n";
                        }
                        if(dDataOV7.plusDays(16).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Wieczorem OW 2ml\n\n";
                        }
                        if(dDataOV7.plusDays(17).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano Inseminacja\n\n";
                        }
                    }
                }
                if(indexG6G >= 0){
                    strDataG6G = uwagi.substring(indexG6G + 4,indexG6G + 14);
                    LocalDate dDataG6G = Modyfikuj.StringNaDate(strDataG6G);
                    if(dDataG6G != null){
                        if(dDataG6G.toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano PG 2ml\n\n";
                        }
                        if(dDataG6G.plusDays(1).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano PG 2ml\n\n";
                        }
                        if(dDataG6G.plusDays(2).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano OW 2ml\n\n";
                        }
                        if(dDataG6G.plusDays(8).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano OW 2ml\n\n";
                        }
                        if(dDataG6G.plusDays(15).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "    Gr.   " + item.getGrupa() +
                                    "\n Rano i Wieczorem PG 2ml\n\n";
                        }
                        if(dDataG6G.plusDays(17).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Wieczorem OW 2ml\n\n";
                        }
                        if(dDataG6G.plusDays(18).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano Inseminacja\n\n";
                        }
                    }
                }
                if(indexG5G >= 0){
                    strDataG5G = uwagi.substring(indexG5G + 4,indexG5G + 14);
                    LocalDate dDataG5G = Modyfikuj.StringNaDate(strDataG5G);
                    if(dDataG5G != null){
                        if(dDataG5G.toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano PG 2ml\n\n";
                        }
                        if(dDataG5G.plusDays(1).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano PG 2ml\n\n";
                        }
                        if(dDataG5G.plusDays(2).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano OW 2ml\n\n";
                        }
                        if(dDataG5G.plusDays(7).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano OW 2ml\n\n";
                        }
                        if(dDataG5G.plusDays(14).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "    Gr.   " + item.getGrupa() +
                                    "\n Rano i Wieczorem PG 2ml\n\n";
                        }
                        if(dDataG5G.plusDays(16).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Wieczorem OW 2ml\n\n";
                        }
                        if(dDataG5G.plusDays(17).toEpochDay() == LocalDate.now().toEpochDay()){
                            wynik = wynik + " " + item.getNumerOborowy() + "    " + item.getNumerIdZwierzecia() +
                                    "  Gr.   " + item.getGrupa() +
                                    "\n Rano Inseminacja\n\n";
                        }
                    }
                }
            }
            result.setText(wynik);
        }
    }
}
