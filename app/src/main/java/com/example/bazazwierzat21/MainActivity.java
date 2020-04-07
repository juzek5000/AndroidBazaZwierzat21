package com.example.bazazwierzat21;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@TargetApi(Build.VERSION_CODES.O)
@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,View.OnClickListener,View.OnLongClickListener{

    int KtoryPrzycisk;
    String before;
    NotificationManagerCompat notificationManager;
    EditText numerOborowySztuki;
    EditText numerKolczykaSztuki;
    EditText nazwaSztuki;
    EditText dniWycielenia;
    EditText coSieUrodzilo;
    EditText grupaSztuki;
    EditText ojciecCielaka;
    EditText uwagi;
    EditText czym1;
    EditText czym2;
    EditText czym3;
    EditText czym4;
    EditText czym5;
    EditText czym6;
    EditText czym7;
    EditText czym8;
    EditText numerKolczykaMatki;
    EditText nazwaOjca;
    EditText nazwaMatki;
    EditText plec;
    EditText rasa;
    EditText miejscePrzeznaczenia;
    EditText poprzedniPosiadacz;
    EditText przyczynaUbycia;
    EditText ostatniaDobowaWydajnosc;
    EditText wiek;

    CheckBox doUbycia;
    CheckBox filtr1;
    CheckBox filtr2;

    Button wycielilaSie;
    Button dataZacielenia;
    Button dataWycielenia;
    Button dataZasuszenia;
    Button dataBadania;
    Button inseminacja1;
    Button inseminacja2;
    Button inseminacja3;
    Button inseminacja4;
    Button inseminacja5;
    Button inseminacja6;
    Button inseminacja7;
    Button inseminacja8;
    Button dataUrodzenia;
    Button dataPrzybycia;
    Button dataUbycia;
    Button bnt_Edytuj;
    Button bnt_ZnajdzOborowy;
    Button bnt_KrowyG6G;
    Button bnt_Wszystko;
    Button bnt_PierwszyWstecz;
    Button bnt_Szukaj;
    Button bnt_Zasuszenia;
    Button bnt_Wycielenia;
    Button bnt_OstatniDalej;
    Button bnt_Wyciele;
    Button bnt_Anuluj;
    Button bnt_Wycielone;
    Button bnt_Ubyte;
    Button bnt_Jalowki;

    EditText znajdzOborowyEdit;
    List<Krowa> ListaCala;
    List<Krowa> ListaGlowna;
    List<Krowa> ListaPom;
    String nazwaTabeli = "";
    int indexGlowny;

    @Override
    protected void onStart() {
        super.onStart();
        if(nazwaTabeli.length() <= 0) {
            nazwaTabeli =  Modyfikuj.readFromFile(getApplicationContext(),"nazwaTabeli.txt");
        }
        if(nazwaTabeli.length() <= 0){
            Intent i = new Intent(this,Logowanie.class);
            startActivity(i);
        }else {
            Intent i = new Intent(this, MyService.class);
            this.stopService(i);
            indexGlowny = 0;
            String dane_z_servera;
            dane_z_servera = Modyfikuj.readFromFile(this, "baza1.txt");
            if (dane_z_servera.length() <= 0) {
                dane_z_servera = Pobierz_z_servera("http://www.gospodarstwo-balcerzak.pl/Baza zwierzat.php", nazwaTabeli);
            }
            Log.d("TAG", "Jestem w Start");
            if (dane_z_servera.length() > 0) {
                ListaCala = Modyfikuj.StringNaListe(dane_z_servera);
                ListaGlowna = Modyfikuj.NieUbyte(ListaCala);
                ListaGlowna = Modyfikuj.SortListToNumerOborowy(ListaGlowna);
                ListaPom = ListaGlowna;
                wypiszDoKontrolek(ListaGlowna, indexGlowny);
                WylaczKontrolki();
            } else {

                Toast.makeText(this, "Baza nie wczytana", Toast.LENGTH_LONG).show();
            }
        }
    }
    public String Pobierz_z_servera(String adres_servera,String tabela) {
        String str = "";
        PobierzDane dane = new PobierzDane();
        dane.execute(adres_servera,tabela);
        try {
            str = dane.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return str;
    }
    @Override
    protected void onStop() {
        if(nazwaTabeli.length() > 0) {
            Intent i = new Intent(this, MyService.class);
            //Intent i2 = new Intent(this, MyService2.class);
            startForegroundService(i);
        }
        Log.d("MICHAL","Jestem w Stop");
        super.onStop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.moje_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ow:
                Intent ow = new Intent(this,OW.class);
                startActivity(ow);
                return true;
            case R.id.pg:
                Intent pg = new Intent(this,PG.class);
                startActivity(pg);
                return true;
            case R.id.wycielenia:
                Intent Wycielenia = new Intent(this,wycielenia.class);
                startActivity(Wycielenia);
                return true;
            case R.id.zasuszenia:
                Intent Zasuszenia = new Intent(this,zasuszenia.class);
                startActivity(Zasuszenia);
                return true;
            case R.id.synchronizacja:
                Intent Synchronizacja = new Intent(this,synchronizacja.class);
                startActivity(Synchronizacja);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public List<Krowa> Szukaj(){
        bnt_Wszystko.setBackgroundResource(R.color.szaryslaby);
        bnt_Zasuszenia.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielenia.setBackgroundResource(R.color.szaryslaby);
        bnt_KrowyG6G.setBackgroundResource(R.color.szaryslaby);
        bnt_Ubyte.setBackgroundResource(R.color.szaryslaby);
        bnt_Jalowki.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielone.setBackgroundResource(R.color.szaryslaby);
        List<Krowa> Result = new ArrayList<>();
        ListaGlowna = ListaPom;
        Spinner spinerGdzieSzukac;
        EditText szukajEdit = findViewById(R.id.szukajEdit);
        spinerGdzieSzukac = findViewById(R.id.spinerGdzieSzukac);
        int spinner_pos = spinerGdzieSzukac.getSelectedItemPosition();
        String czegoSzukac = szukajEdit.getText().toString();
        if(spinner_pos == 0){
            Result = Modyfikuj.SzukajSztukiPoKolczyku(ListaGlowna,czegoSzukac);
            if(Result.size() > 0){
                indexGlowny = 0;
                ListaGlowna = Modyfikuj.SortListToNumerOborowy(Result);
                //Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
                wypiszDoKontrolek(ListaGlowna,indexGlowny);
            }else{
                Toast.makeText(this,"Pusto",Toast.LENGTH_LONG).show();
                setTitle("Lista wyszukiwania jest pusta");
                WyczyscKontrolki();
            }
        }

        if(spinner_pos == 1){
            Result = Modyfikuj.SzukajSztukiPoGrupie(ListaGlowna,czegoSzukac);
            if(Result.size() > 0){
                indexGlowny = 0;
                ListaGlowna = Modyfikuj.SortListToNumerOborowy(Result);
                Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
                wypiszDoKontrolek(ListaGlowna,indexGlowny);
            }else{
                setTitle("Lista wyszukiwania jest pusta");
                //Toast.makeText(this,"Pusto",Toast.LENGTH_LONG).show();
            }
        }
        if(spinner_pos == 2){
            Result = Modyfikuj.SzukajSztukiPoUwagi(ListaGlowna,czegoSzukac);
            if(Result.size() > 0){
                indexGlowny = 0;
                ListaGlowna = Modyfikuj.SortListToNumerOborowy(Result);
                Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
                wypiszDoKontrolek(ListaGlowna,indexGlowny);
            }else{
                setTitle("Lista wyszukiwania jest pusta");
                //Toast.makeText(this,"Pusto",Toast.LENGTH_LONG).show();
            }
        }
        if(spinner_pos == 3){
            Result = Modyfikuj.SzukajSztukiPoMatce(ListaGlowna,czegoSzukac);
            if(Result.size() > 0){
                indexGlowny = 0;
                ListaGlowna = Modyfikuj.SortListToDataUrodzenia(Result);
                Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
                wypiszDoKontrolek(ListaGlowna,indexGlowny);
            }else{
                setTitle("Lista wyszukiwania jest pusta");
                //Toast.makeText(this,"Pusto",Toast.LENGTH_LONG).show();
            }
        }
        ChowanieKlawiatury();
        return Result;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = NotificationManagerCompat.from(this);
        Inicjalizacja();
        wiek.setEnabled(false);
        //bnt_Edytuj.setEnabled(false);
        nazwaTabeli =  Modyfikuj.readFromFile(getApplicationContext(),"nazwaTabeli.txt");
        before = "";
        uwagi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("TAG","before   " + charSequence.toString());
                before = charSequence.toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("TAG","Text   " + charSequence.toString());
                try {
                    String strUwagi = charSequence.toString();
                    Pattern wzorG6G = Pattern.compile("G6G [0-9][0-9].[0-9][0-9].[0-9][0-9][0-9][0-9]");
                    Pattern wzorG5G = Pattern.compile("G5G [0-9][0-9].[0-9][0-9].[0-9][0-9][0-9][0-9]");
                    Pattern wzorOV7 = Pattern.compile("OV7 [0-9][0-9].[0-9][0-9].[0-9][0-9][0-9][0-9]");
                    Matcher wynik_G6G = wzorG6G.matcher(strUwagi);
                    Matcher wynik_G5G = wzorG5G.matcher(strUwagi);
                    Matcher wynik_OV7 = wzorOV7.matcher(strUwagi);
                    boolean boolenWynikG6G = wynik_G6G.find();
                    boolean boolenWynikG5G = wynik_G5G.find();
                    boolean boolenWynikOV7 = wynik_OV7.find();
                    int indexIns = strUwagi.indexOf("Ins");
                    if(before.length() < strUwagi.length()) {
                        if (boolenWynikG6G && indexIns < 0) {
                            int indexG6G = strUwagi.indexOf("G6G");
                            String strData = strUwagi.substring(indexG6G + 4, indexG6G + 14);
                            LocalDate data = Modyfikuj.StringNaDate(strData);
                            if (data != null) {
                                data = data.plusDays(18);
                                if (data.toEpochDay() >= LocalDate.now().toEpochDay()) {
                                    String pom = strUwagi.substring(0, indexG6G + 14);
                                    pom += " Ins " + Modyfikuj.DataNaString(data);
                                    String pom2 = strUwagi.substring(indexG6G + 14);
                                    if (pom2.length() > 0) {
                                        pom += pom2;
                                    }
                                    final String[] qq = {pom};
                                    uwagi.setText(qq[0]);
                                    uwagi.setSelection(pom.length());
                                }
                            }
                        }
                        if (boolenWynikG5G && indexIns < 0) {
                            int indexG5G = strUwagi.indexOf("G5G");
                            String strData = strUwagi.substring(indexG5G + 4, indexG5G + 14);
                            LocalDate data = Modyfikuj.StringNaDate(strData);
                            if (data != null) {
                                data = data.plusDays(17);
                                if (data.toEpochDay() >= LocalDate.now().toEpochDay()) {
                                    String pom = strUwagi.substring(0, indexG5G + 14);
                                    pom += " Ins " + Modyfikuj.DataNaString(data);
                                    String pom2 = strUwagi.substring(indexG5G + 14);
                                    if (pom2.length() > 0) {
                                        pom += pom2;
                                    }
                                    final String[] qq = {pom};
                                    uwagi.setText(qq[0]);
                                    uwagi.setSelection(pom.length());
                                }
                            }
                        }
                        if (boolenWynikOV7 && indexIns < 0) {
                            int indexOV7 = strUwagi.indexOf("OV7");
                            String strData = strUwagi.substring(indexOV7 + 4, indexOV7 + 14);
                            LocalDate data = Modyfikuj.StringNaDate(strData);
                            if (data != null) {
                                data = data.plusDays(17);
                                if (data.toEpochDay() >= LocalDate.now().toEpochDay()) {
                                    String pom = strUwagi.substring(0, indexOV7 + 14);
                                    pom += " Ins " + Modyfikuj.DataNaString(data);
                                    String pom2 = strUwagi.substring(indexOV7 + 14);
                                    if (pom2.length() > 0) {
                                        pom += pom2;
                                    }
                                    final String[] qq = {pom};
                                    uwagi.setText(qq[0]);
                                    uwagi.setSelection(pom.length());
                                }
                            }
                        }
                    }
                    if (boolenWynikG6G == false && boolenWynikG5G == false && boolenWynikOV7 == false) {
                        int index = strUwagi.indexOf("Ins");
                        if (index >= 0) {
                            String pom = strUwagi.substring(0, index);
                            pom += strUwagi.substring(index + 14);
                            final String[] qq = {pom};
                            uwagi.setText(qq[0]);
                            uwagi.setSelection(pom.length());
                            //uwagi.setSelection();
                        }
                    }
                }catch (Exception e){}
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    public void Inicjalizacja(){
        numerOborowySztuki  = findViewById(R.id.idSztuki);
        numerKolczykaSztuki = findViewById(R.id.numerKolczykaSztuki);
        nazwaSztuki = findViewById(R.id.nazwaSztuki);
        dniWycielenia = findViewById(R.id.dniWycielenia);
        coSieUrodzilo = findViewById(R.id.coSieUrodzilo);
        grupaSztuki = findViewById(R.id.grupa);
        ojciecCielaka = findViewById(R.id.ojciecCielaka);
        doUbycia = findViewById(R.id.doUbycia);
        filtr1 = findViewById(R.id.filtr1);
        filtr2 = findViewById(R.id.filtr2);

        wycielilaSie = findViewById(R.id.wycielilaSie);
        dataZacielenia = findViewById(R.id.dataZacielenia);
        dataWycielenia = findViewById(R.id.dataWycielenia);
        dataZasuszenia = findViewById(R.id.dataZasuszenia);
        dataBadania = findViewById(R.id.dataBadania);
        uwagi = findViewById(R.id.uwagi);
        znajdzOborowyEdit = findViewById(R.id.znajdzOborowyEdit);
        inseminacja1 = findViewById(R.id.Inseminacja1);
        inseminacja2 = findViewById(R.id.Inseminacja2);
        inseminacja3 = findViewById(R.id.Inseminacja3);
        inseminacja4 = findViewById(R.id.Inseminacja4);
        inseminacja5 = findViewById(R.id.Inseminacja5);
        inseminacja6 = findViewById(R.id.Inseminacja6);
        inseminacja7 = findViewById(R.id.Inseminacja7);
        inseminacja8 = findViewById(R.id.Inseminacja8);
        czym1 = findViewById(R.id.czym1);
        czym2 = findViewById(R.id.czym2);
        czym3 = findViewById(R.id.czym3);
        czym4 = findViewById(R.id.czym4);
        czym5 = findViewById(R.id.czym5);
        czym6 = findViewById(R.id.czym6);
        czym7 = findViewById(R.id.czym7);
        czym8 = findViewById(R.id.czym8);
        numerKolczykaMatki = findViewById(R.id.numerKolczykaMatki);
        nazwaOjca = findViewById(R.id.nazwaOjca);
        nazwaMatki = findViewById(R.id.nazwaMatki);
        plec = findViewById(R.id.plec);
        rasa = findViewById(R.id.rasa);
        miejscePrzeznaczenia = findViewById(R.id.miejscePrzeznaczenia);
        poprzedniPosiadacz = findViewById(R.id.poprzedniPosiadacz);
        dataUrodzenia = findViewById(R.id.dataUrodzenia);
        dataPrzybycia = findViewById(R.id.dataPrzybycia);
        dataUbycia = findViewById(R.id.dataUbycia);
        przyczynaUbycia = findViewById(R.id.przyczynaUbycia);
        ostatniaDobowaWydajnosc = findViewById(R.id.OstDobWydajnosc);
        wiek = findViewById(R.id.Wiek);

        bnt_Edytuj = findViewById(R.id.bnt_Edytuj);
        bnt_ZnajdzOborowy = findViewById(R.id.bnt_znajdzOborowy);
        bnt_KrowyG6G = findViewById(R.id.bnt_KrowyG6G);
        bnt_Wszystko = findViewById(R.id.bnt_Wszystko);
        bnt_PierwszyWstecz = findViewById(R.id.bnt_PierwszyWstecz);
        bnt_Szukaj = findViewById(R.id.bnt_Szukaj);
        bnt_Zasuszenia = findViewById(R.id.bnt_Zasuszenia);
        bnt_Wycielenia = findViewById(R.id.bnt_Wycielenia);
        bnt_OstatniDalej = findViewById(R.id.bnt_DalejOstatni);
        bnt_Wyciele = findViewById(R.id.bnt_Wyciel);
        bnt_Anuluj = findViewById(R.id.bnt_Anuluj);
        bnt_Wycielone = findViewById(R.id.bnt_Wycielone);
        bnt_Ubyte = findViewById(R.id.bnt_Ubyte);
        bnt_Jalowki = findViewById(R.id.bnt_Jalowki);

        bnt_Wyciele.setEnabled(false);
        bnt_Wyciele.setBackgroundResource(R.color.czerwonyslaby);
        bnt_Anuluj.setEnabled(false);
        bnt_Anuluj.setBackgroundResource(R.color.czerwonyslaby);
        bnt_Edytuj.setBackgroundResource(R.color.czerwonyslaby);
        bnt_Wszystko.setBackgroundResource(R.color.szarymocniejszy);
        bnt_Zasuszenia.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielenia.setBackgroundResource(R.color.szaryslaby);
        bnt_KrowyG6G.setBackgroundResource(R.color.szaryslaby);

        wycielilaSie.setOnClickListener(this);
        wycielilaSie.setOnLongClickListener(this);
        dataZacielenia.setOnClickListener(this);
        dataZacielenia.setOnLongClickListener(this);
        dataZasuszenia.setOnClickListener(this);
        dataZasuszenia.setOnLongClickListener(this);
        dataBadania.setOnClickListener(this);
        dataBadania.setOnLongClickListener(this);
        inseminacja1.setOnClickListener(this);
        inseminacja1.setOnLongClickListener(this);
        inseminacja2.setOnClickListener(this);
        inseminacja2.setOnLongClickListener(this);
        inseminacja3.setOnClickListener(this);
        inseminacja3.setOnLongClickListener(this);
        inseminacja4.setOnClickListener(this);
        inseminacja4.setOnLongClickListener(this);
        inseminacja5.setOnClickListener(this);
        inseminacja5.setOnLongClickListener(this);
        inseminacja6.setOnClickListener(this);
        inseminacja6.setOnLongClickListener(this);
        inseminacja7.setOnClickListener(this);
        inseminacja7.setOnLongClickListener(this);
        inseminacja8.setOnClickListener(this);
        inseminacja8.setOnLongClickListener(this);
        bnt_Edytuj.setOnClickListener(this);
        bnt_Edytuj.setOnLongClickListener(this);
        dataUrodzenia.setOnClickListener(this);
        dataUrodzenia.setOnLongClickListener(this);
        dataPrzybycia.setOnClickListener(this);
        dataPrzybycia.setOnLongClickListener(this);
        dataUbycia.setOnClickListener(this);
        dataUbycia.setOnLongClickListener(this);
        bnt_ZnajdzOborowy.setOnClickListener(this);
        bnt_ZnajdzOborowy.setOnLongClickListener(this);
        bnt_KrowyG6G.setOnClickListener(this);
        bnt_KrowyG6G.setOnLongClickListener(this);
        bnt_Wszystko.setOnClickListener(this);
        bnt_Wszystko.setOnLongClickListener(this);
        bnt_PierwszyWstecz.setOnClickListener(this);
        bnt_PierwszyWstecz.setOnLongClickListener(this);
        bnt_OstatniDalej.setOnClickListener(this);
        bnt_OstatniDalej.setOnLongClickListener(this);
        bnt_Szukaj.setOnClickListener(this);
        bnt_Szukaj.setOnLongClickListener(this);
        bnt_Zasuszenia.setOnClickListener(this);
        bnt_Zasuszenia.setOnLongClickListener(this);
        bnt_Wycielenia.setOnClickListener(this);
        bnt_Wycielenia.setOnLongClickListener(this);
        bnt_Ubyte.setOnClickListener(this);
        bnt_Ubyte.setOnLongClickListener(this);
        bnt_Jalowki.setOnClickListener(this);
        bnt_Jalowki.setOnLongClickListener(this);

        bnt_Wyciele.setOnClickListener(this);
        bnt_Anuluj.setOnClickListener(this);
        bnt_Wycielone.setOnClickListener(this);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String dzien,miesiac,rok;
        if(dayOfMonth < 10)
            dzien = "0"+String.valueOf(dayOfMonth);
        else
            dzien = String.valueOf(dayOfMonth);
        if((month + 1) < 10)
            miesiac = "0"+String.valueOf(month+1);
        else
            miesiac = String.valueOf(month+1);

        String data = dzien+"."+miesiac+"."+String.valueOf(year);
        if(KtoryPrzycisk == 1) {
            wycielilaSie.setText(data);
            dniWycielenia.setText(String.valueOf(Modyfikuj.RoznicaDat(wycielilaSie.getText().toString())));
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 2) {
            dataZacielenia.setText(data);
            LocalDate localData = Modyfikuj.StringNaDate(dataZacielenia.getText().toString());

            localData = localData.plusDays(280);
            dataWycielenia.setText(Modyfikuj.DataNaString(localData));
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 3) {
            dataZasuszenia.setText(data);
            grupaSztuki.setText("STR");
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 4) {
            dataBadania.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 5) {
            inseminacja1.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 6) {
            inseminacja2.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 7) {
            inseminacja3.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 8) {
            inseminacja4.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 9) {
            inseminacja5.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 10) {
            inseminacja6.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 11) {
            inseminacja7.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 12) {
            inseminacja8.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 13) {
            dataUrodzenia.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 14) {
            dataPrzybycia.setText(data);
            KtoryPrzycisk = 0;
        }
        if(KtoryPrzycisk == 15) {
            dataUbycia.setText(data);
            KtoryPrzycisk = 0;
        }
    }
    @Override
    public boolean onLongClick(View v) {
        switch(v.getId()) {
            case R.id.wycielilaSie:
                wycielilaSie.setText("");
                dniWycielenia.setText("");
                break;
            case R.id.dataZacielenia:
                dataZacielenia.setText("");
                dataWycielenia.setText("");
                break;
            case R.id.dataZasuszenia:
                dataZasuszenia.setText("");
                break;
            case R.id.dataBadania:
                dataBadania.setText("");
                break;
            case R.id.Inseminacja1:
                inseminacja1.setText("");
                czym1.setText("");
                break;
            case R.id.Inseminacja2:
                inseminacja2.setText("");
                czym2.setText("");
            case R.id.Inseminacja3:
                inseminacja3.setText("");
                czym3.setText("");
                break;
            case R.id.Inseminacja4:
                inseminacja4.setText("");
                czym4.setText("");
                break;
            case R.id.Inseminacja5:
                inseminacja5.setText("");
                czym5.setText("");
                break;
            case R.id.Inseminacja6:
                inseminacja6.setText("");
                czym6.setText("");
                break;
            case R.id.Inseminacja7:
                inseminacja7.setText("");
                czym7.setText("");
                break;
            case R.id.Inseminacja8:
                inseminacja8.setText("");
                czym8.setText("");
                break;
            case R.id.dataUrodzenia:
                dataUrodzenia.setText("");
                break;
            case R.id.dataPrzybycia:
                dataPrzybycia.setText("");
                break;
            case R.id.dataUbycia:
                dataUbycia.setText("");
                przyczynaUbycia.setText("");
                break;
            case R.id.bnt_PierwszyWstecz:
                pierwszyRekord();
                break;
            case R.id.bnt_DalejOstatni:
                ostatniRekord();
                break;
        }
        return true;
    }
    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.wycielilaSie:
                KtoryPrzycisk = 1;
                UruchomDatePicker();
                break;
            case R.id.dataZacielenia:
                KtoryPrzycisk = 2;
                UruchomDatePicker();
                break;
            case R.id.dataZasuszenia:
                KtoryPrzycisk = 3;
                UruchomDatePicker();
                break;
            case R.id.dataBadania:
                KtoryPrzycisk = 4;
                UruchomDatePicker();
                break;
            case R.id.Inseminacja1:
                KtoryPrzycisk = 5;
                UruchomDatePicker();
                break;
            case R.id.Inseminacja2:
                KtoryPrzycisk = 6;
                UruchomDatePicker();
                break;
            case R.id.Inseminacja3:
                KtoryPrzycisk = 7;
                UruchomDatePicker();
                break;
            case R.id.Inseminacja4:
                KtoryPrzycisk = 8;
                UruchomDatePicker();
                break;
            case R.id.Inseminacja5:
                KtoryPrzycisk = 9;
                UruchomDatePicker();
                break;
            case R.id.Inseminacja6:
                KtoryPrzycisk = 10;
                UruchomDatePicker();
                break;
            case R.id.Inseminacja7:
                KtoryPrzycisk = 11;
                UruchomDatePicker();
                break;
            case R.id.Inseminacja8:
                KtoryPrzycisk = 12;
                UruchomDatePicker();
                break;
            case R.id.dataUrodzenia:
                KtoryPrzycisk = 13;
                UruchomDatePicker();
                break;
            case R.id.dataPrzybycia:
                KtoryPrzycisk = 14;
                UruchomDatePicker();
                break;
            case R.id.dataUbycia:
                KtoryPrzycisk = 15;
                UruchomDatePicker();
                break;
            case R.id.bnt_Edytuj:
                try {
                    Edytuj();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bnt_DalejOstatni:
                dalejRekord();
                break;
            case R.id.bnt_PierwszyWstecz:
                wsteczRekord();
                break;
            case R.id.bnt_Wszystko:
                Wszystkie();
                break;
            case R.id.bnt_znajdzOborowy:
                try {
                    ZnajdzOborowy();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bnt_Szukaj:
                Szukaj();
                break;
            case R.id.bnt_Wyciel:
                Wyciel();
                break;
            case R.id.bnt_Anuluj:
                Anuluj();
                break;
            case R.id.bnt_Zasuszenia:
                Zasuszenia();
                break;
            case R.id.bnt_Wycielenia:
                Wycielenia();
                break;
            case R.id.bnt_KrowyG6G:
                KrowyG6G();
                break;
            case R.id.bnt_Wycielone:
                Wycielone();
                break;
            case R.id.bnt_Jalowki:
                Jalowki();
                break;
            case R.id.bnt_Ubyte:
                Ubyte();
                break;
        }
    }
    public void Zasuszenia(){
        bnt_Wszystko.setBackgroundResource(R.color.szaryslaby);
        bnt_Zasuszenia.setBackgroundResource(R.color.szarymocniejszy);
        bnt_Wycielenia.setBackgroundResource(R.color.szaryslaby);
        bnt_KrowyG6G.setBackgroundResource(R.color.szaryslaby);
        bnt_Ubyte.setBackgroundResource(R.color.szaryslaby);
        bnt_Jalowki.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielone.setBackgroundResource(R.color.szaryslaby);
        ListaGlowna = Modyfikuj.NieUbyte(ListaCala);
        List<Krowa> Result;
        Result = Modyfikuj.Zasuszone(ListaGlowna);
        if(Result.size() > 0){
            indexGlowny = 0;
            ListaGlowna = Modyfikuj.SortListToDataZacielenia(Result);
            ListaPom = ListaGlowna;
            //Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
            wypiszDoKontrolek(ListaGlowna,indexGlowny);
        }else{
            setTitle("Lista zasuszonych jest pusta");
            WyczyscKontrolki();
            //Toast.makeText(this,"Pusto",Toast.LENGTH_LONG).show();
        }
    }
    public void Wycielenia(){
        bnt_Wszystko.setBackgroundResource(R.color.szaryslaby);
        bnt_Zasuszenia.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielenia.setBackgroundResource(R.color.szarymocniejszy);
        bnt_KrowyG6G.setBackgroundResource(R.color.szaryslaby);
        bnt_Ubyte.setBackgroundResource(R.color.szaryslaby);
        bnt_Jalowki.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielone.setBackgroundResource(R.color.szaryslaby);
        ListaGlowna = Modyfikuj.NieUbyte(ListaCala);
        List<Krowa> Result;
        Result = Modyfikuj.Wycielenia(ListaGlowna);
        if(Result.size() > 0){
            indexGlowny = 0;
            ListaGlowna = Modyfikuj.SortListToDataZacielenia(Result);
            ListaPom = ListaGlowna;
            //Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
            wypiszDoKontrolek(ListaGlowna,indexGlowny);
        }else{
            setTitle("Lista wycielenia jest pusta");
            WyczyscKontrolki();
            //Toast.makeText(this,"Pusto",Toast.LENGTH_LONG).show();
        }
    }
    public void KrowyG6G(){
        bnt_Wszystko.setBackgroundResource(R.color.szaryslaby);
        bnt_Zasuszenia.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielenia.setBackgroundResource(R.color.szaryslaby);
        bnt_KrowyG6G.setBackgroundResource(R.color.szarymocniejszy);
        bnt_Ubyte.setBackgroundResource(R.color.szaryslaby);
        bnt_Jalowki.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielone.setBackgroundResource(R.color.szaryslaby);
        List<Krowa> Result;
        ListaGlowna = Modyfikuj.NieUbyte(ListaCala);
        Result = Modyfikuj.SztukiG6G(ListaGlowna);
        if(Result.size() > 0){
            indexGlowny = 0;
            //ListaGlowna = Modyfikuj.SortListToWycielilaSie(Result);
            ListaGlowna = Result;
            ListaPom = ListaGlowna;
            //Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
            wypiszDoKontrolek(ListaGlowna,indexGlowny);
            //PrzesylanieListyDoAktyvitySzczegoly(ListaGlowna);
        }else{
            setTitle("Lista G6G jest pusta");
            WyczyscKontrolki();
        }
    }
    public void PrzesylanieListyDoAktyvitySzczegoly(List<Krowa> lista){
        Intent i = new Intent(this, PG.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("value", (Serializable) ListaGlowna);
        i.putExtras(bundle);
        startActivity(i);
    }
    public void Wycielone(){
        bnt_Wszystko.setBackgroundResource(R.color.szaryslaby);
        bnt_Zasuszenia.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielenia.setBackgroundResource(R.color.szaryslaby);
        bnt_KrowyG6G.setBackgroundResource(R.color.szaryslaby);
        bnt_Ubyte.setBackgroundResource(R.color.szaryslaby);
        bnt_Jalowki.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielone.setBackgroundResource(R.color.szarymocniejszy);
        ListaGlowna = Modyfikuj.NieUbyte(ListaCala);
        List<Krowa> Result;
        Result = Modyfikuj.Wycielone(ListaGlowna);
        if(Result.size() > 0){
            indexGlowny = 0;
            ListaGlowna = Modyfikuj.SortListToWycielilaSie(Result);
            ListaPom = ListaGlowna;
            //Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
            wypiszDoKontrolek(ListaGlowna,indexGlowny);
        }else{
            setTitle("Lista wycielone jest pusta");
            WyczyscKontrolki();
            //Toast.makeText(this,"Pusto",Toast.LENGTH_LONG).show();
        }
    }
    public void Jalowki(){
        bnt_Wszystko.setBackgroundResource(R.color.szaryslaby);
        bnt_Zasuszenia.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielenia.setBackgroundResource(R.color.szaryslaby);
        bnt_KrowyG6G.setBackgroundResource(R.color.szaryslaby);
        bnt_Ubyte.setBackgroundResource(R.color.szaryslaby);
        bnt_Jalowki.setBackgroundResource(R.color.szarymocniejszy);
        bnt_Wycielone.setBackgroundResource(R.color.szaryslaby);
        ListaGlowna = Modyfikuj.NieUbyte(ListaCala);
        List<Krowa> Result;
        Result = Modyfikuj.Jalowki(ListaCala);
        if(Result.size() > 0){
            indexGlowny = 0;
            ListaGlowna = Modyfikuj.SortListToDataUrodzenia(Result);
            ListaPom = ListaGlowna;
            //Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
            wypiszDoKontrolek(ListaGlowna,indexGlowny);
        }else{
            setTitle("Lista jałówki jest pusta");
            WyczyscKontrolki();
            //Toast.makeText(this,"Pusto",Toast.LENGTH_LONG).show();
        }
    }
    public void Ubyte(){
        bnt_Wszystko.setBackgroundResource(R.color.szaryslaby);
        bnt_Zasuszenia.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielenia.setBackgroundResource(R.color.szaryslaby);
        bnt_KrowyG6G.setBackgroundResource(R.color.szaryslaby);
        bnt_Ubyte.setBackgroundResource(R.color.szarymocniejszy);
        bnt_Jalowki.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielone.setBackgroundResource(R.color.szaryslaby);
        List<Krowa> Result;
        Result = Modyfikuj.Ubyte(ListaCala);
        if(Result.size() > 0){
            indexGlowny = 0;
            ListaGlowna = Modyfikuj.SortListToDataUbycia(Result);
            ListaPom = ListaGlowna;
            //Toast.makeText(this,"Znaleziono "+String.valueOf(ListaGlowna.size()),Toast.LENGTH_LONG).show();
            wypiszDoKontrolek(ListaGlowna,indexGlowny);
        }else{
            setTitle("Lista jałówki jest pusta");
            WyczyscKontrolki();
            //Toast.makeText(this,"Pusto",Toast.LENGTH_LONG).show();
        }
    }
    public void Wszystkie(){
        bnt_Wszystko.setBackgroundResource(R.color.szarymocniejszy);
        bnt_Zasuszenia.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielenia.setBackgroundResource(R.color.szaryslaby);
        bnt_KrowyG6G.setBackgroundResource(R.color.szaryslaby);
        bnt_Ubyte.setBackgroundResource(R.color.szaryslaby);
        bnt_Jalowki.setBackgroundResource(R.color.szaryslaby);
        bnt_Wycielone.setBackgroundResource(R.color.szaryslaby);
        ListaGlowna = Modyfikuj.NieUbyte(ListaCala);
        indexGlowny = 0;
        ListaGlowna = Modyfikuj.SortListToNumerOborowy(ListaGlowna);
        ListaPom = ListaGlowna;
        wypiszDoKontrolek(ListaGlowna,indexGlowny);
        WylaczKontrolki();
    }
    public void Anuluj(){
        bnt_Anuluj.setBackgroundResource(R.color.czerwonyslaby);
        bnt_Edytuj.setBackgroundResource(R.color.czerwonyslaby);
        bnt_Wyciele.setBackgroundResource(R.color.czerwonyslaby);
        bnt_Wyciele.setEnabled(false);
        bnt_Anuluj.setEnabled(false);
        bnt_Edytuj.setText("EDYTUJ");
        WlaczPrzyciski();
        WylaczKontrolki();
        wypiszDoKontrolek(ListaGlowna,indexGlowny);
    }
    public void Wyciel(){
        KtoryPrzycisk = 1;
        UruchomDatePicker();
        ojciecCielaka.setText("");
        if(dataZacielenia.getText().toString().equals(inseminacja1.getText().toString()))
            ojciecCielaka.setText(czym1.getText());
        else
        if(dataZacielenia.getText().toString().equals(inseminacja2.getText().toString()))
            ojciecCielaka.setText(czym2.getText());
        else
        if(dataZacielenia.getText().toString().equals(inseminacja3.getText().toString()))
            ojciecCielaka.setText(czym3.getText());
        else
        if(dataZacielenia.getText().toString().equals(inseminacja4.getText().toString()))
            ojciecCielaka.setText(czym4.getText());
        else
        if(dataZacielenia.getText().toString().equals(inseminacja5.getText().toString()))
            ojciecCielaka.setText(czym5.getText());
        else
        if(dataZacielenia.getText().toString().equals(inseminacja6.getText().toString()))
            ojciecCielaka.setText(czym6.getText());
        else
        if(dataZacielenia.getText().toString().equals(inseminacja7.getText().toString()))
            ojciecCielaka.setText(czym7.getText());
        else
        if(dataZacielenia.getText().toString().equals(inseminacja8.getText().toString()))
            ojciecCielaka.setText(czym8.getText());
        dataZacielenia.setText("");
        dataWycielenia.setText("");
        dataZasuszenia.setText("");
        inseminacja1.setText("");
        czym1.setText("");
        inseminacja2.setText("");
        czym2.setText("");
        inseminacja3.setText("");
        czym3.setText("");
        inseminacja4.setText("");
        czym4.setText("");
        inseminacja5.setText("");
        czym5.setText("");
        inseminacja6.setText("");
        czym6.setText("");
        inseminacja7.setText("");
        czym7.setText("");
        inseminacja8.setText("");
        czym8.setText("");
        grupaSztuki.setText("K");
        coSieUrodzilo.setText("");
        bnt_Wyciele.setBackgroundResource(R.color.czerwonyslaby);
    }
    public void Edytuj() throws ExecutionException, InterruptedException {
        if(bnt_Edytuj.getText().toString().equals("EDYTUJ")){
            WlaczKontrolki();
            if(dataZacielenia.getText().toString().length() > 0) {
                bnt_Wyciele.setEnabled(true);
                bnt_Wyciele.setBackgroundResource(R.color.zielonyslaby);
            }
            bnt_Anuluj.setEnabled(true);
            bnt_Anuluj.setBackgroundResource(R.color.zielonyslaby);
            WylaczPrzyciski();
            bnt_Edytuj.setText("ZAPISZ");
            bnt_Edytuj.setBackgroundResource(R.color.zielonyslaby);
        }else{
            if(wycielilaSie.getText().length() > 0) {
                if (coSieUrodzilo.getText().toString().length() > 2 &&
                        ojciecCielaka.getText().toString().length() > 0) {
                    bnt_Anuluj.setEnabled(false);
                    bnt_Anuluj.setBackgroundResource(R.color.czerwonyslaby);
                    bnt_Edytuj.setBackgroundResource(R.color.czerwonyslaby);
                    bnt_Wyciele.setBackgroundResource(R.color.czerwonyslaby);
                    WylaczKontrolki();
                    WlaczPrzyciski();
                    final Handler mHandler = new Handler() {
                        @Override public void handleMessage(Message msg) {
                            String mString=(String)msg.obj;
                            Toast.makeText(getApplicationContext(), mString, Toast.LENGTH_LONG).show();

                        }
                    };
                    new Thread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void run() {
                            boolean wynik = false;
                            try {
                                wynik = AktualizujRekord();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(!wynik) {
                                Message msg = new Message();
                                msg.obj = "Nie powiodło sie\nspróbój jescze raz";
                                mHandler.sendMessage(msg);
                            }
                        }
                    }).start();
                    bnt_Wyciele.setEnabled(false);
                    bnt_Edytuj.setText("EDYTUJ");
                } else {
                    Toast.makeText(this, "Uzupełnij pola \nCo sie urodziło \ni Ojciec cielaka", Toast.LENGTH_LONG).show();
                }
            }else{
                bnt_Anuluj.setEnabled(false);
                bnt_Anuluj.setBackgroundResource(R.color.czerwonyslaby);
                bnt_Edytuj.setBackgroundResource(R.color.czerwonyslaby);
                bnt_Wyciele.setBackgroundResource(R.color.czerwonyslaby);
                WylaczKontrolki();
                WlaczPrzyciski();
                final Handler mHandler = new Handler() {
                    @Override public void handleMessage(Message msg) {
                        String mString=(String)msg.obj;
                        Toast.makeText(getApplicationContext(), mString, Toast.LENGTH_LONG).show();
                    }
                };
                new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run() {
                        boolean wynik = false;
                        try {
                            wynik = AktualizujRekord();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(!wynik) {
                            Message msg = new Message();
                            msg.obj = "Nie powiodło sie\nspróbój jescze raz";
                            mHandler.sendMessage(msg);
                        }
                    }
                }).start();
                bnt_Wyciele.setEnabled(false);
                bnt_Edytuj.setText("EDYTUJ");
            }

        }
    }
    public void UruchomDatePicker(){
        DialogFragment data = new DialogPickerDate();
        data.show(getSupportFragmentManager(), "Data");

    }
    public void ZnajdzOborowy() throws ExecutionException, InterruptedException {
        ListaGlowna = ListaPom;
        indexGlowny = Modyfikuj.szukajIndexuOborowego(ListaGlowna,znajdzOborowyEdit.getText().toString());
        if(indexGlowny >= 0) {
            wypiszDoKontrolek(ListaGlowna, indexGlowny);
            ChowanieKlawiatury();
        }else {
            bnt_Wszystko.setBackgroundResource(R.color.szaryslaby);
            bnt_Zasuszenia.setBackgroundResource(R.color.szaryslaby);
            bnt_Wycielenia.setBackgroundResource(R.color.szaryslaby);
            bnt_KrowyG6G.setBackgroundResource(R.color.szaryslaby);
            bnt_Ubyte.setBackgroundResource(R.color.szaryslaby);
            bnt_Jalowki.setBackgroundResource(R.color.szaryslaby);
            bnt_Wycielone.setBackgroundResource(R.color.szaryslaby);
            setTitle("Index nie został znaleziony");
            WyczyscKontrolki();
        }
    }
    public void WylaczKontrolki(){
        numerOborowySztuki.setEnabled(false);
        numerKolczykaSztuki.setEnabled(false);
        nazwaSztuki.setEnabled(false);
        dniWycielenia.setEnabled(false);
        coSieUrodzilo.setEnabled(false);
        grupaSztuki.setEnabled(false);
        ojciecCielaka.setEnabled(false);
        doUbycia.setEnabled(false);
        filtr1.setEnabled(false);
        filtr2.setEnabled(false);

        wycielilaSie.setEnabled(false);
        dataZacielenia.setEnabled(false);
        dataWycielenia.setEnabled(false);
        dataZasuszenia.setEnabled(false);
        dataBadania.setEnabled(false);
        uwagi.setEnabled(false);
        inseminacja1.setEnabled(false);
        inseminacja2.setEnabled(false);
        inseminacja3.setEnabled(false);
        inseminacja4.setEnabled(false);
        inseminacja5.setEnabled(false);
        inseminacja6.setEnabled(false);
        inseminacja7.setEnabled(false);
        inseminacja8.setEnabled(false);
        czym1.setEnabled(false);
        czym2.setEnabled(false);
        czym3.setEnabled(false);
        czym4.setEnabled(false);
        czym5.setEnabled(false);
        czym6.setEnabled(false);
        czym7.setEnabled(false);
        czym8.setEnabled(false);
        numerKolczykaMatki.setEnabled(false);
        nazwaMatki.setEnabled(false);
        nazwaOjca.setEnabled(false);
        plec.setEnabled(false);
        rasa.setEnabled(false);
        miejscePrzeznaczenia.setEnabled(false);
        poprzedniPosiadacz.setEnabled(false);
        dataUrodzenia.setEnabled(false);
        dataPrzybycia.setEnabled(false);
        dataUbycia.setEnabled(false);
        przyczynaUbycia.setEnabled(false);
        ostatniaDobowaWydajnosc.setEnabled(false);
    }
    public void WlaczKontrolki(){
        numerOborowySztuki.setEnabled(true);
        numerKolczykaSztuki.setEnabled(true);
        nazwaSztuki.setEnabled(true);
        //dniWycielenia.setEnabled(true);
        coSieUrodzilo.setEnabled(true);
        grupaSztuki.setEnabled(true);
        ojciecCielaka.setEnabled(true);
        doUbycia.setEnabled(true);
        filtr1.setEnabled(true);
        filtr2.setEnabled(true);

        wycielilaSie.setEnabled(true);
        dataZacielenia.setEnabled(true);
        //dataWycielenia.setEnabled(true);
        dataZasuszenia.setEnabled(true);
        dataBadania.setEnabled(true);
        uwagi.setEnabled(true);
        inseminacja1.setEnabled(true);
        inseminacja2.setEnabled(true);
        inseminacja3.setEnabled(true);
        inseminacja4.setEnabled(true);
        inseminacja5.setEnabled(true);
        inseminacja6.setEnabled(true);
        inseminacja7.setEnabled(true);
        inseminacja8.setEnabled(true);
        czym1.setEnabled(true);
        czym2.setEnabled(true);
        czym3.setEnabled(true);
        czym4.setEnabled(true);
        czym5.setEnabled(true);
        czym6.setEnabled(true);
        czym7.setEnabled(true);
        czym8.setEnabled(true);
        numerKolczykaMatki.setEnabled(true);
        nazwaMatki.setEnabled(true);
        nazwaOjca.setEnabled(true);
        plec.setEnabled(true);
        rasa.setEnabled(true);
        miejscePrzeznaczenia.setEnabled(true);
        poprzedniPosiadacz.setEnabled(true);
        dataUrodzenia.setEnabled(true);
        dataPrzybycia.setEnabled(true);
        dataUbycia.setEnabled(true);
        przyczynaUbycia.setEnabled(true);
        ostatniaDobowaWydajnosc.setEnabled(true);
    }
    public void ChowanieKlawiatury() {
        ((InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE))
                .toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);
    }
    public void pierwszyRekord(){

        indexGlowny = 0;
        wypiszDoKontrolek(ListaGlowna,indexGlowny);
    }
    public void wypiszDoKontrolek(List<Krowa> Lista, int index){
        setTitle("Lista jest pusta");
        if((Lista.size() > 0) && (index >= 0) && (index < Lista.size())){
            setTitle("Rek nr " + (index +1) + " z " + Lista.size() + "  Nr ob. " + Lista.get(index).getNumerOborowy());
            numerOborowySztuki.setText(Lista.get(index).getNumerOborowy());
            numerKolczykaSztuki.setText(Lista.get(index).getNumerIdZwierzecia());
            nazwaSztuki.setText(Lista.get(index).getNazwaZwierzecia());
            long dni = Modyfikuj.RoznicaDat(Lista.get(index).getWycielilaSie());
            if(dni < 0){
                dniWycielenia.setText("");
            }else{
                dniWycielenia.setText(String.valueOf(dni));
            }

            coSieUrodzilo.setText(Lista.get(index).getCoSieUrodzilo());
            grupaSztuki.setText(Lista.get(index).getGrupa());
            ojciecCielaka.setText(Lista.get(index).getNazwaOjcaCielaka());
            if(Lista.get(index).getDoUbycia().toUpperCase().equals("TRUE"))
                doUbycia.setChecked(true);
            else
                doUbycia.setChecked(false);
            if(Lista.get(index).getZaznacz1().toUpperCase().equals("TRUE"))
                filtr1.setChecked(true);
            else
                filtr1.setChecked(false);
            if(Lista.get(index).getZaznacz2().toUpperCase().equals("TRUE"))
                filtr2.setChecked(true);
            else
                filtr2.setChecked(false);

            wycielilaSie.setText(Lista.get(index).getWycielilaSie());
            dataZacielenia.setText(Lista.get(index).getDataZacielenia());
            LocalDate data = Modyfikuj.StringNaDate(Lista.get(index).getDataZacielenia());
            if(data != null) {
                data = data.plusDays(280);
                String data_pom = Modyfikuj.DataNaString(data);
                dataWycielenia.setText(data_pom);
            }
            else {dataWycielenia.setText("");}
            dataZasuszenia.setText(Lista.get(index).getDataZasuszenia());
            dataBadania.setText(Lista.get(index).getDataBadania());
            uwagi.setText(Lista.get(index).getUwagi());
            inseminacja1.setText(Lista.get(index).getDataInseminacji1());
            inseminacja2.setText(Lista.get(index).getDataInseminacji2());
            inseminacja3.setText(Lista.get(index).getDataInseminacji3());
            inseminacja4.setText(Lista.get(index).getDataInseminacji4());
            inseminacja5.setText(Lista.get(index).getDataInseminacji5());
            inseminacja6.setText(Lista.get(index).getDataInseminacji6());
            inseminacja7.setText(Lista.get(index).getDataInseminacji7());
            inseminacja8.setText(Lista.get(index).getDataInseminacji8());
            czym1.setText(Lista.get(index).getCzym1());
            czym2.setText(Lista.get(index).getCzym2());
            czym3.setText(Lista.get(index).getCzym3());
            czym4.setText(Lista.get(index).getCzym4());
            czym5.setText(Lista.get(index).getCzym5());
            czym6.setText(Lista.get(index).getCzym6());
            czym7.setText(Lista.get(index).getCzym7());
            czym8.setText(Lista.get(index).getCzym8());
            numerKolczykaMatki.setText(Lista.get(index).getNumerIdMatki());
            nazwaOjca.setText(Lista.get(index).getNazwaOjca());


            //for(Krowa item:ListaCala){
            //    if(item.getNumerIdZwierzecia().toUpperCase().equals(numerKolczykaMatki.toString().toUpperCase())){
            //        nazwaMatki.setText(item.getNazwaZwierzecia());
            //   }
            // }
            nazwaMatki.setText(Lista.get(index).getNazwaMatki());
            plec.setText(Lista.get(index).getPlec());
            rasa.setText(Lista.get(index).getRasa());
            miejscePrzeznaczenia.setText(Lista.get(index).getMiejscePrzeznaczenia());
            poprzedniPosiadacz.setText(Lista.get(index).getPoprzedniPosiadacz());
            dataUrodzenia.setText(Lista.get(index).getDataUrodzenia());
            dataPrzybycia.setText(Lista.get(index).getDataPrzybycia());
            dataUbycia.setText(Lista.get(index).getDataUbycia());
            przyczynaUbycia.setText(Lista.get(index).getPrzyczynaUbycia());
            ostatniaDobowaWydajnosc.setText(Lista.get(index).getOstDobWydajnosc());
            long dniUrodzenia = Modyfikuj.RoznicaDat(Lista.get(index).getDataUrodzenia());
            if(dniUrodzenia > -1) {
                int lata = (int) dniUrodzenia / 365;
                int pełnelata = lata * 365;
                int pozostaleDni = (int) (dniUrodzenia - pełnelata);
                int miesiace = (int) (pozostaleDni / 30.42);
                String wynik = lata + "." + miesiace;
                wiek.setText(wynik);
            }else{
                wiek.setText("");
            }
        }
    }
    public void ostatniRekord(){

        indexGlowny = ListaGlowna.size()-1;
        wypiszDoKontrolek(ListaGlowna,indexGlowny);
    }
    public void wsteczRekord(){
        indexGlowny--;
        if(indexGlowny < 0)
            indexGlowny = 0;
        wypiszDoKontrolek(ListaGlowna, indexGlowny);

    }
    public void dalejRekord(){
        indexGlowny++;
        if(indexGlowny > (ListaGlowna.size()-1))
            indexGlowny = ListaGlowna.size()-1;
        wypiszDoKontrolek(ListaGlowna,indexGlowny);
    }
    public Krowa ZczytajZKontrolek(){
        Krowa krowa = new Krowa();
        krowa.setNumerOborowy(numerOborowySztuki.getText().toString());
        krowa.setNazwaZwierzecia(nazwaSztuki.getText().toString());
        krowa.setNumerIdZwierzecia(numerKolczykaSztuki.getText().toString());
        krowa.setCoSieUrodzilo(coSieUrodzilo.getText().toString());
        krowa.setGrupa(grupaSztuki.getText().toString());
        krowa.setOstDobWydajnosc(ostatniaDobowaWydajnosc.getText().toString());
        krowa.setNazwaOjcaCielaka(ojciecCielaka.getText().toString());

        if(doUbycia.isChecked() == true)
            krowa.setDoUbycia("true");
        else
            krowa.setDoUbycia("false");
        if(filtr1.isChecked() == true)
            krowa.setZaznacz1("true");
        else
            krowa.setZaznacz1("false");
        if(filtr2.isChecked() == true)
            krowa.setZaznacz2("true");
        else
            krowa.setZaznacz2("false");
        krowa.setMloda("FALSE");
        krowa.setWycielilaSie(wycielilaSie.getText().toString());
        krowa.setDataZacielenia(dataZacielenia.getText().toString());
        krowa.setDataZasuszenia(dataZasuszenia.getText().toString());
        krowa.setDataBadania(dataBadania.getText().toString());
        krowa.setUwagi(uwagi.getText().toString());
        krowa.setDataInseminacji1(inseminacja1.getText().toString());
        krowa.setDataInseminacji2(inseminacja2.getText().toString());
        krowa.setDataInseminacji3(inseminacja3.getText().toString());
        krowa.setDataInseminacji4(inseminacja4.getText().toString());
        krowa.setDataInseminacji5(inseminacja5.getText().toString());
        krowa.setDataInseminacji6(inseminacja6.getText().toString());
        krowa.setDataInseminacji7(inseminacja7.getText().toString());
        krowa.setDataInseminacji8(inseminacja8.getText().toString());
        krowa.setCzym1(czym1.getText().toString());
        krowa.setCzym2(czym2.getText().toString());
        krowa.setCzym3(czym3.getText().toString());
        krowa.setCzym4(czym4.getText().toString());
        krowa.setCzym5(czym5.getText().toString());
        krowa.setCzym6(czym6.getText().toString());
        krowa.setCzym7(czym7.getText().toString());
        krowa.setCzym8(czym8.getText().toString());
        krowa.setNumerIdMatki(numerKolczykaMatki.getText().toString());
        krowa.setNazwaOjca(nazwaOjca.getText().toString());
        krowa.setNazwaMatki(nazwaMatki.getText().toString());
        krowa.setPlec(plec.getText().toString());
        krowa.setRasa(rasa.getText().toString());
        krowa.setMiejscePrzeznaczenia(miejscePrzeznaczenia.getText().toString());
        krowa.setPoprzedniPosiadacz(poprzedniPosiadacz.getText().toString());
        krowa.setDataUrodzenia(dataUrodzenia.getText().toString());
        krowa.setDataPrzybycia(dataPrzybycia.getText().toString());
        krowa.setDataUbycia(dataUbycia.getText().toString());
        krowa.setPrzyczynaUbycia(przyczynaUbycia.getText().toString());
        return krowa;
    }
    public boolean AktualizujRekord() throws ExecutionException, InterruptedException {
        boolean wynik = false;
        Krowa krowa = ZczytajZKontrolek();
        char[] d = krowa.getCoSieUrodzilo().toCharArray();
        if (d.length > 2) {
            if (d[2] == '+') {
                d[2] = '!';
                String qq = String.valueOf(d);
                krowa.setCoSieUrodzilo(qq);
            }
        }
        String nazwaTabeli =  Modyfikuj.readFromFile(getApplicationContext(),"nazwaTabeli.txt");
        if(nazwaTabeli.length() > 0) {
            AktualizujSQL aaa = new AktualizujSQL();
            aaa.execute(nazwaTabeli,
                    krowa.getNumerOborowy(),
                    krowa.getNumerIdZwierzecia(),
                    krowa.getNumerIdMatki(),
                    krowa.getNazwaZwierzecia(),
                    krowa.getNazwaMatki(),
                    krowa.getNazwaOjca(),
                    krowa.getDataUrodzenia(),
                    krowa.getDataPrzybycia(),
                    krowa.getDataUbycia(),
                    krowa.getDataZacielenia(),
                    krowa.getDataWycielenia(),
                    krowa.getDataZasuszenia(),
                    krowa.getDataBadania(),
                    krowa.getWycielilaSie(),
                    krowa.getUwagi(),
                    krowa.getPlec(),
                    krowa.getRasa(),
                    krowa.getPoprzedniPosiadacz(),
                    krowa.getMiejscePrzeznaczenia(),
                    krowa.getPrzyczynaUbycia(),
                    krowa.getCoSieUrodzilo(),
                    krowa.getGrupa(),
                    krowa.getDataInseminacji1(),
                    krowa.getDataInseminacji2(),
                    krowa.getDataInseminacji3(),
                    krowa.getDataInseminacji4(),
                    krowa.getDataInseminacji5(),
                    krowa.getDataInseminacji6(),
                    krowa.getDataInseminacji7(),
                    krowa.getDataInseminacji8(),
                    krowa.getCzym1(),
                    krowa.getCzym2(),
                    krowa.getCzym3(),
                    krowa.getCzym4(),
                    krowa.getCzym5(),
                    krowa.getCzym6(),
                    krowa.getCzym7(),
                    krowa.getCzym8(),
                    krowa.getMloda(),
                    krowa.getDoUbycia(),
                    krowa.getZaznacz1(),
                    krowa.getZaznacz2(),
                    krowa.getNazwaOjcaCielaka(),
                    krowa.getOstDobWydajnosc());
            Boolean result = aaa.get();
            if (result == true) {
                wynik = true;
                ListaGlowna.set(indexGlowny, krowa);
                PobierzDane dane = new PobierzDane();
                dane.execute("http://www.gospodarstwo-balcerzak.pl/Baza zwierzat.php",nazwaTabeli);
                String str = dane.get();
                Modyfikuj.writeToFile(getApplicationContext(), str, "baza1.txt");
                str = Modyfikuj.readFromFile(getApplicationContext(), "baza1.txt");
                ListaCala = Modyfikuj.StringNaListe(str);
                List<Krowa> ListaPom = Modyfikuj.NieUbyte(ListaCala);
                ListaPom = Modyfikuj.Wycielenia(ListaPom);
                if(ListaPom.size() > 0) {
                    String ListaWycielenia = Modyfikuj.ListaWycieleniaZasuszeniaNaString(ListaPom);
                    Modyfikuj.writeToFile(getApplicationContext(),ListaWycielenia,"wycieleniaZ_Pliku.txt");
                }else{
                    Modyfikuj.writeToFile(getApplicationContext(),"","wycieleniaZ_Pliku.txt");
                }
            } else {
                wynik = false;
                //wypiszDoKontrolek(ListaGlowna,indexGlowny);
            }
        }
        return wynik;
    }
    public void WylaczPrzyciski(){
        bnt_ZnajdzOborowy.setEnabled(false);
        bnt_KrowyG6G.setEnabled(false);
        bnt_Wszystko.setEnabled(false);
        bnt_PierwszyWstecz.setEnabled(false);
        bnt_Szukaj.setEnabled(false);
        bnt_Zasuszenia.setEnabled(false);
        bnt_Wycielenia.setEnabled(false);
        bnt_Wycielone.setEnabled(false);
        bnt_Jalowki.setEnabled(false);
        bnt_Ubyte.setEnabled(false);
        bnt_OstatniDalej.setEnabled(false);
        bnt_OstatniDalej.setEnabled(false);
        bnt_OstatniDalej.setEnabled(false);
    }
    public void WlaczPrzyciski(){
        bnt_ZnajdzOborowy.setEnabled(true);
        bnt_KrowyG6G.setEnabled(true);
        bnt_Wszystko.setEnabled(true);
        bnt_PierwszyWstecz.setEnabled(true);
        bnt_Szukaj.setEnabled(true);
        bnt_Zasuszenia.setEnabled(true);
        bnt_Wycielenia.setEnabled(true);
        bnt_Wycielone.setEnabled(true);
        bnt_Jalowki.setEnabled(true);
        bnt_Ubyte.setEnabled(true);
        bnt_OstatniDalej.setEnabled(true);

    }
    public void WyczyscKontrolki(){
        numerOborowySztuki.setText("");
        numerKolczykaSztuki.setText("");
        nazwaSztuki.setText("");
        dniWycielenia.setText("");
        coSieUrodzilo.setText("");
        grupaSztuki.setText("");
        ojciecCielaka.setText("");
        doUbycia.setChecked(false);
        filtr1.setChecked(false);
        filtr2.setChecked(false);

        wycielilaSie.setText("");
        dataZacielenia.setText("");
        dataWycielenia.setText("");
        dataZasuszenia.setText("");
        dataBadania.setText("");
        uwagi.setText("");
        inseminacja1.setText("");
        inseminacja2.setText("");
        inseminacja3.setText("");
        inseminacja4.setText("");
        inseminacja5.setText("");
        inseminacja6.setText("");
        inseminacja7.setText("");
        inseminacja8.setText("");
        czym1.setText("");
        czym2.setText("");
        czym3.setText("");
        czym4.setText("");
        czym5.setText("");
        czym6.setText("");
        czym7.setText("");
        czym8.setText("");
        numerKolczykaMatki.setText("");
        nazwaMatki.setText("");
        nazwaOjca.setText("");
        plec.setText("");
        rasa.setText("");
        miejscePrzeznaczenia.setText("");
        poprzedniPosiadacz.setText("");
        dataUrodzenia.setText("");
        dataPrzybycia.setText("");
        dataUbycia.setText("");
        przyczynaUbycia.setText("");
        ostatniaDobowaWydajnosc.setText("");
    }
}