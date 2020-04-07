package com.example.bazazwierzat21;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@TargetApi(Build.VERSION_CODES.O)
@RequiresApi(api = Build.VERSION_CODES.O)
public class Modyfikuj {
    public static String OstatniaInseminacja(Krowa item){
    List<String> ListaZacielen = new ArrayList<>();
        ListaZacielen.add(item.getDataInseminacji1());
        ListaZacielen.add(item.getDataInseminacji2());
        ListaZacielen.add(item.getDataInseminacji3());
        ListaZacielen.add(item.getDataInseminacji4());
        ListaZacielen.add(item.getDataInseminacji5());
        ListaZacielen.add(item.getDataInseminacji6());
        ListaZacielen.add(item.getDataInseminacji7());
        ListaZacielen.add(item.getDataInseminacji8());
        for(int i = 7;i >= 0;i--){
            if(ListaZacielen.get(i).length() > 0)
                return ListaZacielen.get(i);
        }
        return  "";
    }
    public static List<Krowa> StringNaListe(String data){
        List<Krowa> Lista = new ArrayList();
        String[] pom = data.split("%");
        Krowa nowa;
        for(int i =0;i<pom.length;i++)
        {
            String[] pom2 = pom[i].split("&");
            nowa = new Krowa();
            if(pom2[0].equals(" ") == false)
                nowa.setID(pom2[0]);
            else
                nowa.setID("");

            if(pom2[1].equals(" ") == false)
                nowa.setNumerOborowy(pom2[1]);
            else
                nowa.setNumerOborowy("");

            if(pom2[2].equals(" ") == false)
                nowa.setNumerIdZwierzecia(pom2[2]);
            else
                nowa.setNumerIdZwierzecia("");
            if(pom2[3].equals(" ") == false)
                nowa.setNumerIdMatki(pom2[3]);
            else
                nowa.setNumerIdMatki("");
            if(pom2[4].equals(" ") == false)
                nowa.setNazwaZwierzecia(pom2[4]);
            else
                nowa.setNazwaZwierzecia("");
            if(pom2[5].equals(" ") == false)
                nowa.setNazwaMatki(pom2[5]);
            else
                nowa.setNazwaMatki("");

            if(pom2[6].equals(" ") == false)
                nowa.setNazwaOjca(pom2[6]);
            else
                nowa.setNazwaOjca("");
            if(pom2[7].equals(" ") == false)
                nowa.setDataUrodzenia(pom2[7]);
            else
                nowa.setDataUrodzenia("");
            if(pom2[8].equals(" ") == false)
                nowa.setDataPrzybycia(pom2[8]);
            else
                nowa.setDataPrzybycia("");
            if(pom2[9].equals(" ") == false)
                nowa.setDataUbycia(pom2[9]);
            else
                nowa.setDataUbycia("");
            if(pom2[10].equals(" ") == false)
                nowa.setDataZacielenia(pom2[10]);
            else
                nowa.setDataZacielenia("");
            if(pom2[11].equals(" ") == false)
                nowa.setDataWycielenia(pom2[11]);
            else
                nowa.setDataWycielenia("");
            if(pom2[12].equals(" ") == false)
                nowa.setDataBadania(pom2[12]);
            else
                nowa.setDataBadania("");
            if(pom2[13].equals(" ") == false)
                nowa.setDataZasuszenia(pom2[13]);
            else
                nowa.setDataZasuszenia("");
            if(pom2[14].equals(" ") == false)
                nowa.setWycielilaSie(pom2[14]);
            else
                nowa.setWycielilaSie("");
            if(pom2[15].equals(" ") == false)
                nowa.setUwagi(pom2[15]);
            else
                nowa.setUwagi("");
            if(pom2[16].equals(" ") == false)
                nowa.setPlec(pom2[16]);
            else
                nowa.setPlec("");
            if(pom2[17].equals(" ") == false)
                nowa.setRasa(pom2[17]);
            else
                nowa.setRasa("");
            if(pom2[18].equals(" ") == false)
                nowa.setPoprzedniPosiadacz(pom2[18]);
            else
                nowa.setPoprzedniPosiadacz("");
            if(pom2[18].equals(" ") == false)
                nowa.setPoprzedniPosiadacz(pom2[18]);
            else
                nowa.setPoprzedniPosiadacz("");
            if(pom2[19].equals(" ") == false)
                nowa.setMiejscePrzeznaczenia(pom2[19]);
            else
                nowa.setMiejscePrzeznaczenia("");
            if(pom2[20].equals(" ") == false)
                nowa.setPrzyczynaUbycia(pom2[20]);
            else
                nowa.setPrzyczynaUbycia("");
            if(pom2[21].equals(" ") == false)
                nowa.setCoSieUrodzilo(pom2[21]);
            else
                nowa.setCoSieUrodzilo("");
            if(pom2[22].equals(" ") == false)
                nowa.setGrupa(pom2[22]);
            else
                nowa.setGrupa("");
            if(pom2[23].equals(" ") == false)
                nowa.setDataInseminacji1(pom2[23]);
            else
                nowa.setDataInseminacji1("");
            if(pom2[24].equals(" ") == false)
                nowa.setDataInseminacji2(pom2[24]);
            else
                nowa.setDataInseminacji2("");
            if(pom2[25].equals(" ") == false)
                nowa.setDataInseminacji3(pom2[25]);
            else
                nowa.setDataInseminacji3("");
            if(pom2[26].equals(" ") == false)
                nowa.setDataInseminacji4(pom2[26]);
            else
                nowa.setDataInseminacji4("");
            if(pom2[27].equals(" ") == false)
                nowa.setDataInseminacji5(pom2[27]);
            else
                nowa.setDataInseminacji5("");
            if(pom2[28].equals(" ") == false)
                nowa.setDataInseminacji6(pom2[28]);
            else
                nowa.setDataInseminacji6("");
            if(pom2[29].equals(" ") == false)
                nowa.setDataInseminacji7(pom2[29]);
            else
                nowa.setDataInseminacji7("");
            if(pom2[30].equals(" ") == false)
                nowa.setDataInseminacji8(pom2[30]);
            else
                nowa.setDataInseminacji8("");
            if(pom2[31].equals(" ") == false)
                nowa.setCzym1(pom2[31]);
            else
                nowa.setCzym1("");
            if(pom2[32].equals(" ") == false)
                nowa.setCzym2(pom2[32]);
            else
                nowa.setCzym2("");
            if(pom2[33].equals(" ") == false)
                nowa.setCzym3(pom2[33]);
            else
                nowa.setCzym3("");
            if(pom2[34].equals(" ") == false)
                nowa.setCzym4(pom2[34]);
            else
                nowa.setCzym4("");
            if(pom2[35].equals(" ") == false)
                nowa.setCzym5(pom2[35]);
            else
                nowa.setCzym5("");
            if(pom2[36].equals(" ") == false)
                nowa.setCzym6(pom2[36]);
            else
                nowa.setCzym6("");
            if(pom2[37].equals(" ") == false)
                nowa.setCzym7(pom2[37]);
            else
                nowa.setCzym7("");
            if(pom2[38].equals(" ") == false)
                nowa.setCzym8(pom2[38]);
            else
                nowa.setCzym8("");
            if(pom2[39].equals(" ") == false)
                nowa.setMloda(pom2[39]);
            else
                nowa.setMloda("");
            if(pom2[40].equals(" ") == false)
                nowa.setDoUbycia(pom2[40]);
            else
                nowa.setDoUbycia("");
            if(pom2[41].equals(" ") == false)
                nowa.setZaznacz1(pom2[41]);
            else
                nowa.setZaznacz1("");
            if(pom2[42].equals(" ") == false)
                nowa.setZaznacz2(pom2[42]);
            else
                nowa.setZaznacz2("");
            if(pom2[43].equals(" ") == false)
                nowa.setNazwaOjcaCielaka(pom2[43]);
            else
                nowa.setNazwaOjcaCielaka("");

            if(pom2[44].equals(" ") == false)
                nowa.setOstDobWydajnosc(pom2[44]);
            else
                nowa.setOstDobWydajnosc("");
            Lista.add(nowa);
        }
        return Lista;
    }
    public static List<Krowa> NieUbyte(List<Krowa> lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getDataUbycia().length() <= 0){
                Result.add(item);
            }
        }
        return Result;
    }
    public static String ListaWycieleniaZasuszeniaNaString(List<Krowa> Lista){
        String result = "";
        try {
            for(int i = 0; i < Lista.size();i++){
                result += Lista.get(i).getNumerIdZwierzecia() + "%";
            }
            return  result.substring(0,result.length()-1);
        }catch (Exception e){

        }
        return result;

    }
    public static List<Krowa> StringNaListeWycieleniaZasuszenia(String str){
        List<Krowa> result = new ArrayList<>();
        Krowa nowa;
        String[] pom = str.split("%");
        for(int i = 0; i < pom.length;i++){
           nowa = new Krowa();
           nowa.setNumerIdZwierzecia(pom[i]);
           result.add(nowa);
        }

        return result;
    }
    public static List<Krowa> NieUbyteParelle(List<Krowa> data){
        List<Krowa> Result = new ArrayList<>();
       /* List<Krowa> result = data.parallelStream()
                .filter(new Predicate<Krowa>() {
                    @Override
                    public boolean test(Krowa a) {
                        return Objects.equals(a.getDataUbycia().toUpperCase(), "");
                    }
                })
                .collect(Collectors.toList());*/
        return Result;
    }
    public static List<Krowa> SortListToNumerOborowy(List<Krowa> dane) {
        Collections.sort(dane, new Comparator<Krowa>(){
            public int compare(Krowa obj1, Krowa obj2) {
                // ## Ascending order
                //return obj1.getNumerOborowy().compareToIgnoreCase(obj2.getNumerOborowy()); // To compare string values
                return Integer.valueOf(obj1.getNumerOborowy()).compareTo(Integer.valueOf(obj2.getNumerOborowy())); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
            }
        });
        return dane;
    }
    public static List<Krowa> SortListToDataZacielenia(List<Krowa> dane) {
        Collections.sort(dane, new Comparator<Krowa>(){
            public int compare(Krowa obj1, Krowa obj2) {
                // ## Ascending order
                //return obj2.getWycielilaSie().compareToIgnoreCase(obj1.getWycielilaSie()); // To compare string values
                return Integer.valueOf((int) Modyfikuj.StringNaDate(obj1.getDataZacielenia()).toEpochDay())
                        .compareTo(Integer.valueOf((int) Modyfikuj.StringNaDate(obj2.getDataZacielenia()).toEpochDay())); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
            }
        });
        return dane;
    }
    public static List<Krowa> SortListToDataUbycia(List<Krowa> dane) {
        Collections.sort(dane, new Comparator<Krowa>(){
            public int compare(Krowa obj1, Krowa obj2) {
                // ## Ascending order
                //return obj2.getDataUbycia().compareToIgnoreCase(obj1.getDataUbycia()); // To compare string values
                //return Integer.valueOf(obj1.getNumerOborowy()).compareTo(Integer.valueOf(obj2.getNumerOborowy())); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                return Integer.valueOf((int)StringNaDate(obj2.getDataUbycia()).toEpochDay())
                        .compareTo(Integer.valueOf((int)StringNaDate(obj1.getDataUbycia()).toEpochDay())); // To compare integer values
            }
        });
        return dane;
    }
    public static List<Krowa> SortListToDataUrodzenia(List<Krowa> dane) {
        Collections.sort(dane, new Comparator<Krowa>(){
            public int compare(Krowa obj1, Krowa obj2) {
                // ## Ascending order
                return obj1.getDataUrodzenia().compareToIgnoreCase(obj2.getDataUrodzenia()); // To compare string values
                //return Integer.valueOf(obj1.getNumerOborowy()).compareTo(Integer.valueOf(obj2.getNumerOborowy())); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
            }
        });
        return dane;
    }
    public static List<Krowa> SortListToWycielilaSie(List<Krowa> dane) {
        Collections.sort(dane, new Comparator<Krowa>(){
            public int compare(Krowa obj1, Krowa obj2) {
                // ## Ascending order
                //return obj2.getWycielilaSie().compareToIgnoreCase(obj1.getWycielilaSie()); // To compare string values
                return Integer.valueOf((int) Modyfikuj.StringNaDate(obj2.getWycielilaSie()).toEpochDay())
                        .compareTo(Integer.valueOf((int) Modyfikuj.StringNaDate(obj1.getWycielilaSie()).toEpochDay())); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
            }
        });
        return dane;
    }
    public static List<Krowa> SortListToGrupa(List<Krowa> dane) {
        Collections.sort(dane, new Comparator<Krowa>(){
            public int compare(Krowa obj1, Krowa obj2) {
                // ## Ascending order
                return obj2.getGrupa().compareToIgnoreCase(obj1.getGrupa()); // To compare string values
                //return Integer.valueOf((int) Modyfikuj.StringNaDate(obj2.getWycielilaSie()).toEpochDay())
                //        .compareTo(Integer.valueOf((int) Modyfikuj.StringNaDate(obj1.getWycielilaSie()).toEpochDay())); // To compare integer values

                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values
                // return Integer.valueOf(obj2.empId).compareTo(Integer.valueOf(obj1.empId)); // To compare integer values
            }
        });
        return dane;
    }
    public static LocalDate StringNaDate(String data){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.parse(data, formatter);
            return localDate;
        }catch (Exception e){

        }
        return null;
    }
    public static String DataNaString(LocalDate data){
        String dzien,miesiac;
        if(data.getDayOfMonth() < 10){
            dzien = "0"+String.valueOf(data.getDayOfMonth());
        }else {
            dzien = String.valueOf(data.getDayOfMonth());
        }
        if(data.getMonthValue() < 10){
            miesiac = "0"+String.valueOf(data.getMonthValue());
        }else{
            miesiac = String.valueOf(data.getMonthValue());
        }
        String localdata = dzien + "." + miesiac + "." + String.valueOf(data.getYear());
        return localdata;
    }
    public static int szukajIndexuOborowego(List<Krowa> krowy,String element){
        if(krowy.size() > 0)
            for(int i = 0;i < krowy.size();i++)
                if(krowy.get(i).getNumerOborowy().equals(element))
                    return i;
        return -1;
    }
    public static long RoznicaDat(String wycielilaSie){
        LocalDate pom = StringNaDate(wycielilaSie);
        LocalDate data = LocalDate.now();
        try {
            return (data.toEpochDay()-pom.toEpochDay());
        }catch (Exception e){}
        return -1;
    }
    public static List<Krowa> PG(List<Krowa> Lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:Lista){
            if(item.getWycielilaSie().length() > 0){
                long dni = RoznicaDat(item.getWycielilaSie());
                if(dni == 5 || dni == 15 || dni ==25){
                    Result.add(item);
                }
            }
        }
        return Result;
    }
    public static List<Krowa> OW7(List<Krowa> Lista){
        List<Krowa> Result = new ArrayList<>();
        String ostatniainseminacja;
        for(Krowa item:Lista){
            ostatniainseminacja = Modyfikuj.OstatniaInseminacja(item);
            if(ostatniainseminacja.length() > 0){
                long dni = RoznicaDat(ostatniainseminacja);
                if(dni == 7){
                    Result.add(item);
                }
            }
        }
        return Result;
    }
    public static void writeToFile(Context context, String data, String nazwaPliku) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(nazwaPliku, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
    public static String readFromFile(Context context,String nazwaPliku) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(nazwaPliku);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.d("michal", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.d("michal", "Can not read file: " + e.toString());
        }
        Log.d("michal",ret);
        return ret;
    }
    public static List<Krowa> SzukajSztukiPoKolczyku(List<Krowa> lista,String kolczyk){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getNumerIdZwierzecia().toUpperCase().contains(kolczyk.toUpperCase())){
                Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> SzukajSztukiPoMatce(List<Krowa> lista,String kolczyk){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getNumerIdMatki().toUpperCase().contains(kolczyk.toUpperCase())){
                Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> SzukajSztukiPoGrupie(List<Krowa> lista,String kolczyk){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getGrupa().toUpperCase().equals(kolczyk.toUpperCase())){
                Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> SzukajSztukiPoUwagi(List<Krowa> lista,String szukanaFraza){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getUwagi().toUpperCase().contains(szukanaFraza.toUpperCase())){
                Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> Zasuszone(List<Krowa> lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getDataZasuszenia().length() <= 0 &&
                    item.getDataZacielenia().length() > 0 &&
                    item.getWycielilaSie().length() > 0){
                LocalDate dataZacielenia = StringNaDate(item.getDataZacielenia());
                LocalDate datawycielenia = dataZacielenia.plusDays(280);
                LocalDate dataTeraz = LocalDate.now();
                long dniDataWycielenia = datawycielenia.toEpochDay();
                long dniteraz = dataTeraz.toEpochDay();
                long roznica = dniDataWycielenia - dniteraz;
                if(roznica <= 60)
                    Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> Wycielenia(List<Krowa> lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getDataZacielenia().length() > 0){
                LocalDate dataZacielenia = StringNaDate(item.getDataZacielenia());
                LocalDate datawycielenia = dataZacielenia.plusDays(280);
                LocalDate dataTeraz = LocalDate.now();
                long dniDataWycielenia = datawycielenia.toEpochDay();
                long dniteraz = dataTeraz.toEpochDay();
                long roznica = dniDataWycielenia - dniteraz;
                if((dniteraz + 18) > dniDataWycielenia)
                    Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> Wycielone(List<Krowa> lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getWycielilaSie().length() > 0 &&
                    item.getDataUbycia().length() <= 0){
                Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> Jalowki(List<Krowa> lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getWycielilaSie().length() <= 0 &&
                    item.getDataUbycia().length() <= 0){
                Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> Ubyte(List<Krowa> lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista){
            if(item.getDataUbycia().length() > 0){
                Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> SztukiG6G(List<Krowa> lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista) {
            long dniPoWycieleniu = RoznicaDat(item.getWycielilaSie());
            if (item.getUwagi().length() <= 0 &&
                    item.getDoUbycia().toUpperCase().equals("FALSE") &&
                    item.getDataUbycia().length() <= 0 &&
                    item.getDataZacielenia().length() <= 0 &&
                    dniPoWycieleniu > 40 &&
                    item.getDataInseminacji1().length() <= 0) {
                Result.add(item);
            }
        }
        for(Krowa item:lista) {
            long dniZycia = Modyfikuj.RoznicaDat(item.getDataUrodzenia());
            if (item.getUwagi().length() <= 0 &&
                    item.getDoUbycia().toUpperCase().equals("FALSE") &&
                    item.getDataZacielenia().length() <= 0 &&
                    item.getWycielilaSie().length() <= 0 &&
                    item.getDataUbycia().length() <= 0 &&
                    item.getDataInseminacji1().length() <= 0 &&
                    dniZycia > 490) {
                Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> NieCielne(List<Krowa> lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista) {
            if(item.getDataZacielenia() == ""){
                Result.add(item);
            }
        }
        return Result;
    }
    public static List<Krowa> Krowy(List<Krowa> lista){
        List<Krowa> Result = new ArrayList<>();
        for(Krowa item:lista) {
            if(item.getWycielilaSie().length() > 0){
                Result.add(item);
            }
        }
        return Result;
    }
}
