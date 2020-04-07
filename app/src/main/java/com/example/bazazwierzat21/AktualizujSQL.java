package com.example.bazazwierzat21;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AktualizujSQL extends AsyncTask <String,Integer,Boolean>{
    @Override
    protected Boolean doInBackground(String[] tablica) {
        String post="";
        post = post+"Tabela=" + tablica[0];
        post = post+"&NumerOborowy=" + tablica[1];
        post = post+"&NumerIdSztuki=" + tablica[2];
        post = post+"&NumerIdMatki=" + tablica[3];
        post = post+"&NazwaZwierzecia=" + tablica[4];
        post = post+"&NazwaMatki=" + tablica[5];
        post = post+"&NazwaOjca=" + tablica[6];
        post = post+"&DataUrodzenia=" + tablica[7];
        post = post+"&DataPrzybycia=" + tablica[8];
        post = post+"&DataUbycia=" + tablica[9];
        post = post+"&DataZacielenia=" + tablica[10];
        post = post+"&DataWycielenia=" + tablica[11];
        post = post+"&DataZasuszeia=" + tablica[12];
        post = post+"&DataBadania=" + tablica[13];
        post = post+"&WycielilaSie=" + tablica[14];
        post = post+"&Uwagi=" + tablica[15];
        post = post+"&Plec=" + tablica[16];
        post = post+"&Rasa=" + tablica[17];
        post = post+"&PoprzedniPosiadacz=" + tablica[18];
        post = post+"&MiejscePrzeznaczenia=" + tablica[19];
        post = post+"&PrzyczynaUbycia=" + tablica[20];
        post = post+"&CoSieUrodzilo=" + tablica[21];
        post = post+"&Grupa=" + tablica[22];
        post = post+"&DataInseminacja1=" + tablica[23];
        post = post+"&DataInseminacja2=" + tablica[24];
        post = post+"&DataInseminacja3=" + tablica[25];
        post = post+"&DataInseminacja4=" + tablica[26];
        post = post+"&DataInseminacja5=" + tablica[27];
        post = post+"&DataInseminacja6=" + tablica[28];
        post = post+"&DataInseminacja7=" + tablica[29];
        post = post+"&DataInseminacja8=" + tablica[30];
        post = post+"&Czym1=" + tablica[31];
        post = post+"&Czym2=" + tablica[32];
        post = post+"&Czym3=" + tablica[33];
        post = post+"&Czym4=" + tablica[34];
        post = post+"&Czym5=" + tablica[35];
        post = post+"&Czym6=" + tablica[36];
        post = post+"&Czym7=" + tablica[37];
        post = post+"&Czym8=" + tablica[38];
        post = post+"&Mloda=" + tablica[39];
        post = post+"&DoUbycia=" + tablica[40];
        post = post+"&Zaznacz1=" + tablica[41];
        post = post+"&Zaznacz2=" + tablica[42];
        post = post+"&NazwaOjcaCielaka=" + tablica[43];
        post = post+"&OstatniaDobowaWydajnosc=" + tablica[44];
        String response ="false";
        Boolean result = false;
        URL url = null;
        try {
            url = new URL("http://www.gospodarstwo-balcerzak.pl/BazaZwierzatUpdate.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection client = null;
        try {
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            // You need to specify the context-type.  In this case it is a
            // form submission, so use "multipart/form-data"
            //client.setRequestProperty("multipart/form-data", "https://eddn.usgs.gov/fieldtest.html;charset=UTF-8");
            client.setDoInput(true);
            client.setDoOutput(true);

            OutputStream os = client.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(post);
            writer.flush();
            writer.close();
            os.close();
            int responseCode = client.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                response = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
                String dd = response;
                result = true;
            }else{
                return false;
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(client != null) // Make sure the connection is not null.
                client.disconnect();
        }

        return result;
    }
}
