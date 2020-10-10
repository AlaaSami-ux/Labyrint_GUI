public class Aapning extends HvitRute{
  public Aapning(int rader, int kolonner){
    super(rader,kolonner);
  }
  @Override
  public void gaa(Rute komFra, String utvei){
    utvei+= this.toString();
    // System.out.println(utvei);
      labRuter.ut.add(utvei);
  }
}
