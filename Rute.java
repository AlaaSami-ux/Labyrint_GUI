public abstract class Rute{
Labyrint labRuter;
int kolonner;
int rader;
Rute oppe = null;
Rute ned = null;
Rute venstre = null;
Rute hoyre = null;

  public Rute(int rader, int kolonner){
    this.kolonner = kolonner;
    this.rader = rader;

  }
  abstract public char tilTegn();

  public Rute oppe(){return oppe;}
  public Rute ned(){return ned;}
  public Rute venstre(){return venstre;}
  public Rute hoyre(){return hoyre;}
  public Labyrint labyrinten(){return labRuter;}

// Lag en metode gaa i Rute. Denne metoden skal kalle gaa på alle naboruter unntatt den som
// er i den retningen kallet kom fra (for da ville vi gått tilbake til der vi nettopp var). Merk: Det
// forventes at du implementerer dette uten bruk av metoden instanceof.

public abstract void gaa(Rute komFra, String utvei);
// Lag så metoden void finnUtvei() i Rute som finner alle utveier fra ruten ved hjelp av kall på
// gaa.
public void finnUtvei(){
  gaa(null,"");
  }

public String toString(){
  return "( " + kolonner  + ", " + rader + ")";
}
}
