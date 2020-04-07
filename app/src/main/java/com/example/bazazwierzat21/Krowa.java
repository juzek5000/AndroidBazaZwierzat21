package com.example.bazazwierzat21;

import java.io.Serializable;

public class Krowa implements Serializable {
    private String ID;
    private String NumerOborowy;
    private String NumerIdZwierzecia ;
    private String NumerIdMatki ;
    private String NazwaZwierzecia;
    private String NazwaMatki;
    private String NazwaOjca ;
    private String DataUrodzenia;
    private String DataPrzybycia ;
    private String DataUbycia;
    private String DataZacielenia;
    private String DataWycielenia;
    private String DataZasuszenia;
    private String DataBadania;
    private String WycielilaSie;
    private String Uwagi;
    private String Plec;
    private String Rasa;
    private String PoprzedniPosiadacz ;
    private String MiejscePrzeznaczenia ;
    private String PrzyczynaUbycia;
    private String CoSieUrodzilo;
    private String Grupa;
    private String DataInseminacji1;
    private String DataInseminacji2;
    private String DataInseminacji3;
    private String DataInseminacji4;
    private String DataInseminacji5;
    private String DataInseminacji6;
    private String DataInseminacji7;
    private String DataInseminacji8;
    private String Czym1;
    private String Czym2;
    private String Czym3 ;
    private String Czym4;
    private String Czym5;
    private String Czym6;
    private String Czym7;
    private String Czym8;
    private String Mloda;
    private String DoUbycia;
    private String Zaznacz1;
    private String Zaznacz2;
    private String NazwaOjcaCielaka;
    private String OstDobWydajnosc;

    public Krowa() {
        ID = "";
        NumerOborowy = "";
        NumerIdZwierzecia = "";
        NumerIdMatki = "";
        NazwaZwierzecia = "";
        NazwaMatki = "";
        NazwaOjca = "";
        DataUrodzenia = "";
        DataPrzybycia = "";
        DataUbycia = "";
        DataZacielenia = "";
        DataWycielenia = "";
        DataZasuszenia = "";
        DataBadania = "";
        WycielilaSie = "";
        Uwagi = "";
        Plec = "";
        Rasa = "";
        PoprzedniPosiadacz = "";
        MiejscePrzeznaczenia = "";
        PrzyczynaUbycia = "";
        CoSieUrodzilo = "";
        Grupa = "";
        DataInseminacji1 = "";
        DataInseminacji2 = "";
        DataInseminacji3 = "";
        DataInseminacji4 = "";
        DataInseminacji5 = "";
        DataInseminacji6 = "";
        DataInseminacji7 = "";
        DataInseminacji8 = "";
        Czym1 = "";
        Czym2 = "";
        Czym3 = "";
        Czym4 = "";
        Czym5 = "";
        Czym6 = "";
        Czym7 = "";
        Czym8 = "";
        Mloda = "";
        DoUbycia = "";
        Zaznacz1 = "";
        Zaznacz2 = "";
        NazwaOjcaCielaka = "";
        OstDobWydajnosc = "";
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getNumerOborowy() {
        return NumerOborowy;
    }
    public void setNumerOborowy(String numerOborowy) {
        NumerOborowy = numerOborowy;
    }
    public String getNumerIdZwierzecia() {
        return  NumerIdZwierzecia;
    }
    public void setNumerIdZwierzecia(String numerIdZwierzecia) {
        NumerIdZwierzecia = numerIdZwierzecia;
    }
    public String getNumerIdMatki() {
        return NumerIdMatki;
    }
    public void setNumerIdMatki(String numerIdMatki) {
        NumerIdMatki = numerIdMatki;
    }
    public String getNazwaZwierzecia() {
        return NazwaZwierzecia;
    }
    public void setNazwaZwierzecia(String nazwaZwierzecia) {
        NazwaZwierzecia = nazwaZwierzecia;
    }
    public String getNazwaMatki() {
        return NazwaMatki;
    }
    public void setNazwaMatki(String nazwaMatki) {
        NazwaMatki = nazwaMatki;
    }
    public String getNazwaOjca(){return NazwaOjca;}
    public void setNazwaOjca(String nazwaOjca) {
        NazwaOjca = nazwaOjca;
    }
    public String getDataUrodzenia(){return DataUrodzenia;}
    public void setDataUrodzenia(String dataUrodzenia) {
        DataUrodzenia = dataUrodzenia;
    }
    public String getDataPrzybycia(){return DataPrzybycia;}
    public void setDataPrzybycia(String dataPrzybycia) {
        DataPrzybycia = dataPrzybycia;
    }
    public String getDataUbycia(){return DataUbycia;}
    public void setDataUbycia(String dataUbycia) {
        DataUbycia = dataUbycia;
    }
    public String getDataZacielenia(){return DataZacielenia;}
    public void setDataZacielenia(String dataZacielenia) {
        DataZacielenia = dataZacielenia;
    }
    public String getDataWycielenia(){return DataWycielenia;}
    public void setDataWycielenia(String dataWycielenia) {
        DataWycielenia = dataWycielenia;
    }
    public String getDataZasuszenia(){return DataZasuszenia;}
    public void setDataZasuszenia(String dataZasuszenia) {
        DataZasuszenia = dataZasuszenia;
    }
    public String getDataBadania(){return DataBadania;}
    public void setDataBadania(String dataBadania) {
        DataBadania = dataBadania;
    }
    public String getWycielilaSie(){return WycielilaSie;}
    public void setWycielilaSie(String wycielilaSie) {
        WycielilaSie = wycielilaSie;
    }
    public String getUwagi(){return Uwagi;}
    public void setUwagi(String uwagi) {
        Uwagi = uwagi;
    }
    public String getPlec(){return Plec;}
    public void setPlec(String plec) {
        Plec = plec;
    }
    public String getRasa(){return Rasa;}
    public void setRasa(String rasa) {
        Rasa = rasa;
    }
    public String getPoprzedniPosiadacz(){return PoprzedniPosiadacz;}
    public void setPoprzedniPosiadacz(String poprzedniPosiadacz) {
        PoprzedniPosiadacz = poprzedniPosiadacz;
    }
    public String getMiejscePrzeznaczenia(){return MiejscePrzeznaczenia;}
    public void setMiejscePrzeznaczenia(String miejscePrzeznaczenia) {
        MiejscePrzeznaczenia = miejscePrzeznaczenia;
    }
    public String getPrzyczynaUbycia(){return PrzyczynaUbycia;}
    public void setPrzyczynaUbycia(String przyczynaUbycia) {
        PrzyczynaUbycia = przyczynaUbycia;
    }
    public String getCoSieUrodzilo(){return CoSieUrodzilo;}
    public void setCoSieUrodzilo(String coSieUrodzilo) {
        CoSieUrodzilo = coSieUrodzilo;
    }
    public String getGrupa(){return Grupa;}
    public void setGrupa(String grupa) {
        Grupa = grupa;
    }
    public String getDataInseminacji1(){return DataInseminacji1;}
    public void setDataInseminacji1(String dataInseminacji1) {
        DataInseminacji1 = dataInseminacji1;
    }
    public String getDataInseminacji2(){return DataInseminacji2;}
    public void setDataInseminacji2(String dataInseminacji2) {
        DataInseminacji2 = dataInseminacji2;
    }
    public String getDataInseminacji3(){return DataInseminacji3;}
    public void setDataInseminacji3(String dataInseminacji3) {
        DataInseminacji3 = dataInseminacji3;
    }
    public String getDataInseminacji4(){return DataInseminacji4;}
    public void setDataInseminacji4(String dataInseminacji4) {
        DataInseminacji4 = dataInseminacji4;
    }
    public String getDataInseminacji5(){return DataInseminacji5;}
    public void setDataInseminacji5(String dataInseminacji5) {
        DataInseminacji5 = dataInseminacji5;
    }
    public String getDataInseminacji6(){return DataInseminacji6;}
    public void setDataInseminacji6(String dataInseminacji6) {
        DataInseminacji6 = dataInseminacji6;
    }
    public String getDataInseminacji7(){return DataInseminacji7;}
    public void setDataInseminacji7(String dataInseminacji7) {
        DataInseminacji7 = dataInseminacji7;
    }
    public String getDataInseminacji8(){return DataInseminacji8;}
    public void setDataInseminacji8(String dataInseminacji8) {
        DataInseminacji8 = dataInseminacji8;
    }
    public String getCzym1(){return Czym1;}
    public void setCzym1(String czym1) {
        Czym1 = czym1;
    }
    public String getCzym2(){return Czym2;}
    public void setCzym2(String czym2) {
        Czym2 = czym2;
    }
    public String getCzym3(){return Czym3;}
    public void setCzym3(String czym3) {
        Czym3 = czym3;
    }
    public String getCzym4(){return Czym4;}
    public void setCzym4(String czym4) {
        Czym4 = czym4;
    }
    public String getCzym5(){return Czym5;}
    public void setCzym5(String czym5) {
        Czym5 = czym5;
    }
    public String getCzym6(){return Czym6;}
    public void setCzym6(String czym6) {
        Czym6 = czym6;
    }
    public String getCzym7(){return Czym7;}
    public void setCzym7(String czym7) {
        Czym7 = czym7;
    }
    public String getCzym8(){return Czym8;}
    public void setCzym8(String czym8) {
        Czym8 = czym8;
    }
    public String getMloda(){return Mloda;}
    public void setMloda(String mloda) {
        Mloda = mloda;
    }
    public String getDoUbycia(){return DoUbycia;}
    public void setDoUbycia(String doUbycia) {
        DoUbycia = doUbycia;
    }
    public String getZaznacz1(){return Zaznacz1;}
    public void setZaznacz1(String zaznacz1) {
        Zaznacz1 = zaznacz1;
    }
    public String getZaznacz2(){return Zaznacz2;}
    public void setZaznacz2(String zaznacz2) {
        Zaznacz2 = zaznacz2;
    }
    public String getNazwaOjcaCielaka(){return NazwaOjcaCielaka;}
    public void setNazwaOjcaCielaka(String nazwaOjcaCielaka) {
        NazwaOjcaCielaka = nazwaOjcaCielaka;
    }
    public String getOstDobWydajnosc(){return OstDobWydajnosc;}
    public void setOstDobWydajnosc(String OstDobWydajnosc_) {
        OstDobWydajnosc = OstDobWydajnosc_;
    }


}
