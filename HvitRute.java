public class HvitRute extends Rute{
  boolean besookt = false;
  public HvitRute(int rader, int kolonner){
    super(rader,kolonner);
  }
  @Override
  public void gaa(Rute komFra, String utvei){
    if (besookt) {
      return;
    }
  utvei+= this.toString() + "  --> ";
  // System.out.println(utvei);
  // System.out.print(this + "  --> ");
  besookt = true;
  if(ned() != null && ned() != komFra){
    ned().gaa(this,utvei);
  }
  if (oppe() != null && oppe() != komFra){
    oppe().gaa(this, utvei);
  }
  if(venstre() != null && venstre()!= komFra){
    venstre().gaa(this,utvei);
  }
  if(hoyre() != null && hoyre()!= komFra){
    hoyre().gaa(this,utvei);
  }
  besookt = false;
}
  public char tilTegn(){
    return '.';
  }
}
