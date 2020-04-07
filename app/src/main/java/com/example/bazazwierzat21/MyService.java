package com.example.bazazwierzat21;


import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.bazazwierzat21.App.KANAL_GLOWNY;
import static com.example.bazazwierzat21.App.KANAL_OW7;
import static com.example.bazazwierzat21.App.KANAL_PG;
import static com.example.bazazwierzat21.App.KANAL_SYNCHRONIZACJA;
import static com.example.bazazwierzat21.App.KANAL_WYCIELENIA;
import static com.example.bazazwierzat21.App.KANAL_ZASUSZENIA;


public class MyService extends Service {
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    BroadcastReceiver mReceiver;
    NotificationManagerCompat notificationManager;
    List<Krowa> Lista = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    @Override
    public void onCreate() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this,KANAL_GLOWNY)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Servis")
                .setContentText("Powiadomienie o uruchomieniu servisu")
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1339, notification);
        notificationManager = NotificationManagerCompat.from(this);
        //Powiadomienie("a","b");
        Log.d("BazaZwierzat","Jestem Servis w onCreate");
        super.onCreate();
    }
    public void PowiadomieniePG(){
        Intent i = new Intent(this,PG.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,i,0);
        Notification notification = new NotificationCompat.Builder(this, KANAL_PG)
                .setSmallIcon(R.drawable.ic_pg)
                .setContentTitle("PG")
                .setContentText("Nowe PG do Podania")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(333, notification);
    }
    public void PowiadomienieOW(){
        Intent i = new Intent(this,OW.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,i,0);
        Notification notification = new NotificationCompat.Builder(this, KANAL_OW7)
                .setSmallIcon(R.drawable.ic_ow)
                .setContentTitle("OW7 11")
                .setContentText("Trzeba podać ovarelin")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(332, notification);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        RegisterAlarmBroadcast();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*60*30 , pendingIntent);
        Log.d("BazaZwierzat","Jestem Servis w onStartCommand");
        return START_STICKY;
    }
    private void doBackgroundWork() {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {

                try {
                    String nazwaTabeli = Modyfikuj.readFromFile(getApplicationContext(),"nazwaTabeli.txt");
                    PobierzDane dane = new PobierzDane();
                    dane.execute("http://www.gospodarstwo-balcerzak.pl/Baza zwierzat.php", nazwaTabeli);
                    String str = dane.get();
                    if (str.length() > 0) {
                        Modyfikuj.writeToFile(getApplicationContext(), str, "baza1.txt");
                        Zdarzenia(str);
                    }
                }  catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean Synchronizacja(List<Krowa> Lista){
        String uwagi;
        int indexG6G;
        int indexG5G;
        int indexOV7;
        String strDataOV7;
        String strDataG6G;
        String strDataG5G;
        for(Krowa item:Lista) {
            uwagi = item.getUwagi().toUpperCase();
            indexOV7 = uwagi.indexOf("OV7");
            if (indexOV7 >= 0) {
                strDataOV7 = uwagi.substring(indexOV7 + 4, indexOV7 + 14);
                LocalDate dDataOV7 = Modyfikuj.StringNaDate(strDataOV7);
                if (dDataOV7 != null) {
                    if (dDataOV7.plusDays(7).toEpochDay() == LocalDate.now().toEpochDay()) {
                        return true;
                    }
                    if (dDataOV7.plusDays(14).toEpochDay() == LocalDate.now().toEpochDay()) {
                        return true;
                    }
                    if (dDataOV7.plusDays(16).toEpochDay() == LocalDate.now().toEpochDay()) {
                        return true;
                    }
                    if (dDataOV7.plusDays(17).toEpochDay() == LocalDate.now().toEpochDay()) {
                        return true;
                    }
                }
            }
            indexG5G = uwagi.indexOf("G5G");
            if(indexG5G >= 0) {
                strDataG5G = uwagi.substring(indexG5G + 4, indexG5G + 14);
                LocalDate dDataG5G = Modyfikuj.StringNaDate(strDataG5G);
                if (dDataG5G != null) {
                    if(dDataG5G.toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG5G.plusDays(1).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG5G.plusDays(2).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG5G.plusDays(7).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG5G.plusDays(14).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG5G.plusDays(16).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG5G.plusDays(17).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                }
            }
            indexG6G = uwagi.indexOf("G6G");
            if(indexG6G >= 0) {
                strDataG6G = uwagi.substring(indexG6G + 4, indexG6G + 14);
                LocalDate dDataG6G = Modyfikuj.StringNaDate(strDataG6G);
                if (dDataG6G != null) {
                    if(dDataG6G.toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG6G.plusDays(1).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG6G.plusDays(2).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG6G.plusDays(8).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG6G.plusDays(15).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG6G.plusDays(17).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                    if(dDataG6G.plusDays(18).toEpochDay() == LocalDate.now().toEpochDay()){
                        return true;
                    }
                }
            }
        }
      return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Zdarzenia(String str){
        String DataZapisana = Modyfikuj.readFromFile(getApplicationContext(),"dataZapisana.txt");
        String dataDzis = Modyfikuj.DataNaString(LocalDate.now());
        if(DataZapisana.length() > 0){
            if(DataZapisana.equals(dataDzis) == false){
            //if(true){
                List<Krowa> ListaCala = Modyfikuj.StringNaListe(str);
                ListaCala = Modyfikuj.NieUbyte(ListaCala);
                List<Krowa> ListaWycielenia = Modyfikuj.Wycielenia(ListaCala);
                List<Krowa> ListaZasuszenia = Modyfikuj.Zasuszone(ListaCala);
                List<Krowa> ListaOW = Modyfikuj.OW7(ListaCala);
                List<Krowa> ListaPG = Modyfikuj.PG(ListaCala);
                List<Krowa> ListaSynchronizacja = Modyfikuj.NieCielne(ListaCala);
                //ListaSynchronizacja = Modyfikuj.NieCielne(ListaSynchronizacja);
                ListaSynchronizacja = Modyfikuj.SortListToNumerOborowy(ListaSynchronizacja);
                ListaSynchronizacja = Modyfikuj.SortListToGrupa(ListaSynchronizacja);
                if(PorownanieListyZasuszenia(ListaZasuszenia)){
                    PowiadomienieZasuszenia();
                }
                if(PorownanieListyWycielen(ListaWycielenia)){
                    PowiadomienieWycielenia();
                }
                if(ListaPG.size() > 0){
                    PowiadomieniePG();
                }
                if(ListaOW.size() > 0) {
                    PowiadomienieOW();
                }
                if(Synchronizacja(ListaSynchronizacja)){
                    PowiadomienieSynchronizacja();
                }
                Modyfikuj.writeToFile(getApplicationContext(),dataDzis,"dataZapisana.txt");
            }


        }else{
            List<Krowa> ListaCala = Modyfikuj.StringNaListe(str);
            ListaCala = Modyfikuj.NieUbyte(ListaCala);
            List<Krowa> ListaWycielenia = Modyfikuj.Wycielenia(ListaCala);
            List<Krowa> ListaZasuszenia = Modyfikuj.Zasuszone(ListaCala);
            List<Krowa> ListaOW = Modyfikuj.OW7(ListaCala);
            List<Krowa> ListaPG = Modyfikuj.PG(ListaCala);
            List<Krowa> ListaSynchronizacja = Modyfikuj.Krowy(ListaCala);
            ListaSynchronizacja = Modyfikuj.NieCielne(ListaSynchronizacja);
            ListaSynchronizacja = Modyfikuj.SortListToNumerOborowy(ListaSynchronizacja);
            ListaSynchronizacja = Modyfikuj.SortListToGrupa(ListaSynchronizacja);
            if(PorownanieListyZasuszenia(ListaZasuszenia)){
                PowiadomienieZasuszenia();
            }
            if(PorownanieListyWycielen(ListaWycielenia)){
                PowiadomienieWycielenia();
            }
            if(ListaPG.size() > 0){
                PowiadomieniePG();
            }
            if(ListaOW.size() > 0) {
                PowiadomienieOW();
            }
            if(Synchronizacja(ListaSynchronizacja)){
                PowiadomienieSynchronizacja();
            }
            Modyfikuj.writeToFile(getApplicationContext(),dataDzis,"dataZapisana.txt");
        }
    }
    private void PowiadomienieZasuszenia() {
        Intent i = new Intent(this,zasuszenia.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,i,0);
        /*Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pi = PendingIntent.getActivity(this,0,i,0);*/
        Notification notification = new NotificationCompat.Builder(this, KANAL_ZASUSZENIA)
                .setSmallIcon(R.drawable.ikonka)
                .setContentTitle("ZASUSZENIA")
                .setContentText("Nowe zasuszenia, sprawdz")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(331, notification);
    }
    private void PowiadomienieSynchronizacja() {
        Intent i = new Intent(this,synchronizacja.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,i,0);
        Notification notification = new NotificationCompat.Builder(this, KANAL_SYNCHRONIZACJA)
                .setSmallIcon(R.drawable.ikonka)
                .setContentTitle("Synchronizacja")
                .setContentText("Nowa synchronizacja, sprawdz")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(334, notification);
    }
    private void PowiadomienieWycielenia() {
        Intent i = new Intent(this,wycielenia.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,i,0);
        /*Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pi = PendingIntent.getActivity(this,0,i,0);*/
        Notification notification = new NotificationCompat.Builder(this, KANAL_WYCIELENIA)
                .setSmallIcon(R.drawable.ikonka)
                .setContentTitle("WYCIELENIA")
                .setContentText("Nowe wycielenia, sprawdz")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(330, notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean PorownanieListyWycielen(List<Krowa> Lista){
        if(Lista.size() > 0){
            String WycieleniaZ_Pliku = Modyfikuj.readFromFile(getApplicationContext(),"wycieleniaZ_Pliku.txt");
            if(WycieleniaZ_Pliku.length() > 0) {
                List<Krowa> ListaZ_Pliku = Modyfikuj.StringNaListeWycieleniaZasuszenia(WycieleniaZ_Pliku);
                if(Lista.size() > ListaZ_Pliku.size()){
                    WycieleniaZ_Pliku = Modyfikuj.ListaWycieleniaZasuszeniaNaString(Lista);
                    Modyfikuj.writeToFile(getApplicationContext(), WycieleniaZ_Pliku, "wycieleniaZ_Pliku.txt");
                    return true;
                }else{
                    WycieleniaZ_Pliku = Modyfikuj.ListaWycieleniaZasuszeniaNaString(Lista);
                    Modyfikuj.writeToFile(getApplicationContext(), WycieleniaZ_Pliku, "wycieleniaZ_Pliku.txt");
                    return false;
                }
            }else{
                WycieleniaZ_Pliku = Modyfikuj.ListaWycieleniaZasuszeniaNaString(Lista);
                Modyfikuj.writeToFile(getApplicationContext(), WycieleniaZ_Pliku, "wycieleniaZ_Pliku.txt");
                return true;
            }
        }else{
            Modyfikuj.writeToFile(getApplicationContext(), "", "wycieleniaZ_Pliku.txt");
            return false;
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean PorownanieListyZasuszenia(List<Krowa> Lista){
        if(Lista.size() > 0){
            String ZasuszeniaZ_Pliku = Modyfikuj.readFromFile(getApplicationContext(),"ZasuszeniaZ_Pliku.txt");
            if(ZasuszeniaZ_Pliku.length() > 0) {
                List<Krowa> ListaZ_Pliku = Modyfikuj.StringNaListeWycieleniaZasuszenia(ZasuszeniaZ_Pliku);
                if(Lista.size() > ListaZ_Pliku.size()){
                    ZasuszeniaZ_Pliku = Modyfikuj.ListaWycieleniaZasuszeniaNaString(Lista);
                    Modyfikuj.writeToFile(getApplicationContext(), ZasuszeniaZ_Pliku, "ZasuszeniaZ_Pliku.txt");
                    return true;
                }else{
                    ZasuszeniaZ_Pliku = Modyfikuj.ListaWycieleniaZasuszeniaNaString(Lista);
                    Modyfikuj.writeToFile(getApplicationContext(), ZasuszeniaZ_Pliku, "ZasuszeniaZ_Pliku.txt");
                    return false;
                }
            }else{
                ZasuszeniaZ_Pliku = Modyfikuj.ListaWycieleniaZasuszeniaNaString(Lista);
                Modyfikuj.writeToFile(getApplicationContext(), ZasuszeniaZ_Pliku, "ZasuszeniaZ_Pliku.txt");
                return true;
            }
        }else{
            Modyfikuj.writeToFile(getApplicationContext(), "", "ZasuszeniaZ_Pliku.txt");
            return false;
        }
    }
    @Override
    public void onDestroy() {
        alarmManager.cancel(pendingIntent);
        getBaseContext().unregisterReceiver(mReceiver);
        Log.d("OLA","onDestroy");
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private void RegisterAlarmBroadcast() {
        mReceiver = new BroadcastReceiver() {
            // private static final String TAG = "Alarm Example Receiver";
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("BazaZwierzat","Jestem Servis w onReceive");
                doBackgroundWork();
                Toast.makeText(context, "Zaktualizowano Baze", Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(mReceiver, new IntentFilter("sample"));
        pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent("sample"), 0);
        alarmManager = (AlarmManager)(this.getSystemService(Context.ALARM_SERVICE));
    }
}
