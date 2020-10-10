import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextAreaBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Presentasjon extends Application implements EventHandler<ActionEvent>{
  Button b;
  public static void main(String[] args) {
      launch(args);
  }
  @Override
  public void handle(ActionEvent event){
    if (event.getSource() == b) {
      b.setStyle("-fx-border-color: RED; -fx-border-width: 1px;");
    }
  }


  @Override
  public void start(Stage stage) throws FileNotFoundException {
    FileChooser fc = new FileChooser();
    FileChooser.ExtensionFilter in = new FileChooser.ExtensionFilter("In", "*.in");
    fc.getExtensionFilters().add(in);
    fc.setTitle("Open File");
    File f = fc.showOpenDialog(stage);

    GridPane g = new GridPane();
    g.setPadding(new Insets(10, 10, 10, 10));


    Labyrint lab = Labyrint.lesFraFil(f);
    Button [][] buttons = new Button[lab.hentAntrad()][lab.hentAntKol()];
    for (int i =0 ;i < lab.hentAntrad() ;i++ ) {
      for (int j = 0; j< lab.hentAntKol() ;j++ ) {
        // lab.hentRute(i,j).tilTegn();
        // System.out.println(lab.hentRute(i,j).tilTegn());
        // Char c =
        buttons[i][j] = new Button();
        if(lab.hentRute(i,j).tilTegn() == '#'){
         buttons[i][j].setStyle("-fx-background-color: BLACK; -fx-border-width: 1px");
        }
        if(lab.hentRute(i,j).tilTegn() == '.'){
         buttons[i][j].setStyle("-fx-border-color: BLUE; -fx-border-width: 1px");
        }
        g.add((buttons[i][j]),j,i);
        final int fin = i;
        final int fin2 = j;

        buttons[i][j].setOnAction(e -> {
        List<String> utveier = lab.finnUtveiFra(fin,fin2);
        if (utveier.size() == 0) {
          return;
        }
        for (int d = 0; d < buttons.length ; d++ ) {
          for (int s = 0; s < buttons[d].length ;s++ ) {
            if(lab.hentRute(d,s).tilTegn() == '.'){
              buttons[d][s].setStyle("-fx-border-color: BLUE; -fx-border-width: 1px");
            }
          }
        }
        boolean[][] liste =  losningStringTilTabell(utveier.get(0),lab.hentAntKol(),lab.hentAntrad());
          for (int t =0 ;t <lab.hentAntKol()  ;t++ ) {
            for (int h = 0; h< lab.hentAntrad() ;h++ ) {
              if (liste[h][t] ) {
                buttons[h][t].setStyle("-fx-background-color: ORANGE; -fx-border-width: 0.5px;");
              }
            }
          }
        });
      }
    }
    stage.setScene(new Scene(g,300,300));
    stage.setTitle("LABYRINT");
    stage.show();
  }

  /**
 * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
 * av losningstien.
 * @param losningString String-representasjon av utveien
 * @param bredde        bredde til labyrinten
 * @param hoyde         hoyde til labyrinten
 * @return              2D-representasjon av rutene der true indikerer at
 *                      ruten er en del av utveien.
 */
  static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
      boolean[][] losning = new boolean[hoyde][bredde];
      java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
      java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
      while (m.find()) {
          int x = Integer.parseInt(m.group(1));
          int y = Integer.parseInt(m.group(2));
          losning[y][x] = true;
      }
      return losning;
  }
}
