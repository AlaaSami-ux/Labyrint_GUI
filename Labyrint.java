import java.io.*;
import java.util.*;

public class Labyrint{
  static Rute[][] ruter;
  int antKolonner;
  int antRader;
  ArrayList<String> ut;

  public int hentAntKol(){
    return antKolonner;
  }
  public int hentAntrad(){
    return antRader;
  }
  public Rute hentRute(int rad, int kol){
    return ruter[rad][kol];
  }

  public Labyrint(Rute[][] ruter, int antRader , int antKolonner){
    this.antKolonner = antKolonner;
    this.antRader = antRader;
    this.ruter = ruter;
    this.ut = new ArrayList<String>();
    finnNabo();
    leggTil();
  }
  static Labyrint lesFraFil(File fil)throws FileNotFoundException{
    Scanner scan = new Scanner(fil);

    String[] str =  scan.nextLine().split(" ");
    int antR = Integer.parseInt(str[0]);
    int antK = Integer.parseInt(str[1]);
    // System.out.println(antR +""+ antK);
    Rute[][] rutenett = new Rute[antR][antK];

    for(int rad = 0; rad < antR; rad++){
      String linje = scan.nextLine();
      String[] tegn = linje.split("");
      // System.out.print(rad);
      for(int kol = 0; kol < antK; kol++){
        // System.out.print(kol);
        if(tegn[kol].equals(".") && (kol ==  0 || kol == antK-1 || rad == 0 || rad == antR-1)){
          rutenett[rad][kol] =  new Aapning(rad,kol);
          // System.out.print(hvit.tilTegn());
        }else if(tegn[kol].equals("#")){
          SortRute sort = new SortRute(rad,kol);
          rutenett[rad][kol] = sort;
          // sort.tilTegn();
          // System.out.print(sort.tilTegn());
        }else{
          HvitRute hvit = new HvitRute(rad,kol);
          rutenett[rad][kol] = hvit;
          // char f = hvit.tilTegn();
        }
      }
      // System.out.println("");
    }
    Labyrint labyrint = new Labyrint(rutenett,antR,antK);
    return labyrint;
  }

  public void finnNabo(){
    for(int i = 0; i<ruter.length;  i++){
      for(int j = 0; j < ruter[i].length; j++){
        if(j < ruter[i].length-1)
          ruter[i][j].hoyre = ruter[i][j+1];
        if( j > 0)
          ruter[i][j].venstre = ruter[i][j-1];
        if(i > 0)
          ruter[i][j].oppe = ruter[i-1][j];
        if(i < ruter.length-1)
          ruter[i][j].ned = ruter[i+1][j];
      }
    }
  }
  public List<String> finnUtveiFra(int rad, int kol){
    ut = new ArrayList<String>(); 
    ruter[rad][kol].finnUtvei();
    return ut;
  }

  public void leggTil(){
    for(int i = 0; i < ruter.length; i++){
      for(int j = 0; j < ruter[i].length; j++)
        ruter[i][j].labRuter = this;
    }
  }

  public static void skrivUt(){
    for(int i = 0; i < ruter.length; i++){
      for(int j = 0; j < ruter[i].length; j++)
        System.out.print(ruter[i][j].tilTegn());
        System.out.println();
    }
  }
}
