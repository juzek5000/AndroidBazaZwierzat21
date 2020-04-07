package com.example.bazazwierzat21;


import android.os.AsyncTask;

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

public class PobierzDane extends AsyncTask<String,Integer,String> {
    @Override
    protected String doInBackground(String... strings) {
        String response ="";
        String post = "Tabela="+strings[1];

        URL url = null;
        try {
            url = new URL(strings[0]);
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
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
                if(response.toUpperCase().contains("ERROR") == true){
                    response = "";
                }
            }
            else {
                response = "";
            }
        }
        catch (MalformedURLException e){
            response = "";
        } catch (UnsupportedEncodingException e) {
            response = "";
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            response = "";
        } finally {
            if(client != null) // Make sure the connection is not null.
                client.disconnect();
        }
        return response;
    }
}
