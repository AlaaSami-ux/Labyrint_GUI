public class SortRute extends Rute{
  public SortRute(int rader, int kolonner){
    super(rader,kolonner);
  }
  public char tilTegn(){
    return '#';
  }
  @Override
  public void gaa(Rute komFra,String utvei){}
}
