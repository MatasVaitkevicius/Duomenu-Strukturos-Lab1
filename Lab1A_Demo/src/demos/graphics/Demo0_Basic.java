/** **************************************************************
 * Šioje klasėje pateikamas įvadas į JavaFX grafiką
 *
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis atlikite sekdami nurodymus programinio kodo komentaruose
 * Gynimo metu atlikite dėstytojo nurodytas užduotis naujų metodų pagalba.
 *
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 ************************************************************************* */
package demos.graphics;

import extendsFX.BaseGraphics;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Demo0_Basic extends BaseGraphics {

    // pradžioje brėšime horizontalias ir vertikalias linijas per centrą
    private void drawHVtoCenter() {
        gc.setLineWidth(3);       // brėžimo linijos plotis
        gc.setStroke(Color.RED);  // ir tos linijos spalva
        gc.strokeLine(0, canvasH / 2, canvasW, canvasH / 2);
        gc.strokeLine(canvasW / 2, 0, canvasW / 2, canvasH);
    }

    // po to brėšime įstrižaines per centrą
    private void drawXtoCenter() {
        gc.setLineWidth(4);         // brėžimo linijos plotis
        gc.setStroke(Color.GREEN);  // ir tos linijos spalva
        gc.strokeLine(0, 0, canvasW, canvasH);
        gc.strokeLine(0, canvasH, canvasW, 0);
    }
// UŽDUOTIS_1: plonomis linijomis su žingsniu step=50 nubrėžkite tinklelį

    private void drawGrid1() {
        double step = 50;
        gc.setLineWidth(1);
        for (double u = step; u < Math.max(canvasW, canvasH); u += step) {
            gc.setStroke(Color.RED);
            gc.strokeLine(0, u, canvasW, u);   // horizontalios linijos
            gc.strokeLine(u, 0, u, canvasH);   // vertikalios linijos
        }

    }
// https://examples.javacodegeeks.com/desktop-java/javafx/javafx-canvas-example/    

    private void drawExamples1() {
        double lw = 3.0;
        gc.setLineWidth(lw);        // brėžimo linijos plotis
        gc.setStroke(Color.BLUE);   // ir tos linijos spalva
        gc.setFill(Color.RED);      // dažymo spalva figūroms
        int x = 10, y = 10, w = 80, h = 50,
                d = 20, ax = 10, ay = 20; // d-tarpas tarp elementų, ax,ay-apvalinimai
        gc.strokeRoundRect(x, y, w, h, ax, ay);
        x += w + d; // sekantis į dešinę
        gc.fillRoundRect(x, y, w, h, ax, ay);
        gc.setLineWidth(0.5);
        gc.strokeText("Wolf and Bear", x, y + h);
        //-------------------
        gc.setLineWidth(2 * lw);    // dvigubai pastoriname liniją      
        gc.setFill(Color.YELLOW);
        x = 10;    // grįžtame horizontaliai
        y += h + d;  // ir pereiname žemyn
        gc.strokeOval(x, y, w, h);
        x += w + d; // sekantis į dešinę
        gc.fillOval(x, y, h, w);
        x = 10;     // grįžtame horizontaliai
        y += h + 2 * d; // ir pereiname žemyn ir brėžiame lankus
        gc.strokeArc(x, y, w, w, 30, 90, ArcType.ROUND);
        gc.fillArc(x + w + d, y, w, w, 45, 180, ArcType.OPEN);
    }

    private void drawUnicode() {
        // išbandykite ir kitus simbolius
        // https://en.wikipedia.org/wiki/List_of_Unicode  skyrius 31
        StringBuilder sb = new StringBuilder();
        for (char ch = '\u2670'; ch <= '\u267F'; ch++) //Nuo kur iki kur
        {
            sb.append(ch);
        }
        for (char ch = '\u2669'; ch <= '\u267F'; ch++) //Nuo kur iki kur
        {
            sb.append(ch);
        }
        gc.setFont(Font.font("Lucida Console", 36));
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        gc.strokeText(sb.toString(), 50, 350);
    }
// UŽDUOTIS_2: nubrėžkite polilinijas ir poligonus   
// https://www.tutorialspoint.com/javafx/2dshapes_polygon    

    private void drawExamples2() {
        gc.setLineWidth(3);
        gc.setStroke(Color.RED);
        int x = 100, y = 100, w = 40, h = 40,
                roundedX = 0, roundedY = 0;
        gc.strokeRoundRect(x, y, w, h, roundedX, roundedY);

        gc.setStroke(Color.GREEN);
        double xTriangle[] = {150, 190, 230, 150}; //Triangle X points
        double yTriangle[] = {140, 100, 140, 140}; //Triangle Y points
        gc.strokePolyline(xTriangle, yTriangle, xTriangle.length); //Polyline automatically doesn't close

        gc.setStroke(Color.BLACK);
        double xPentagon[] = {260, 290, 300, 275, 250};
        double yPentagon[] = {140, 140, 110, 90, 110};
        gc.strokePolygon(xPentagon, yPentagon, xPentagon.length); //Pentagon
    }
// UŽDUOTIS_3: nubrėžkite taisyklingus 3, 4, 5, ..., 9-kampius  

    private void drawExamples3() {
        // Nurodymas: parašykite funkciją, kuri paskaičiuoja skaičių masyvus
        // kuriuose surašomos taisyklingo daugiakampio koordinatės
        gc.setStroke(Color.RED);
        gc.strokeRect(100, 150, 100, 100);
    }

// UŽDUOTIS_4: nubrėžkite žiedus https://en.wikipedia.org/wiki/Olympic_symbols
    private void drawOlympicRings() {
        double lineWeight = 10;
        gc.setLineWidth(lineWeight);
        gc.setStroke(Color.rgb(62, 118, 236)); // Blue
        gc.strokeOval(250, 120, 100, 100); //First two elements control x and y

        gc.setStroke(Color.rgb(255, 206, 1)); // Yellow
        gc.strokeOval(310, 180, 100, 100);

        gc.setStroke(Color.rgb(0, 0, 0)); // Black
        gc.strokeOval(365, 120, 100, 100);

        gc.setStroke(Color.rgb(23, 154, 19)); // Green
        gc.strokeOval(425, 180, 100, 100);

        gc.setStroke(Color.rgb(255, 0, 0)); // Red
        gc.strokeOval(485, 120, 100, 100);

    }
// UŽDUOTIS_5: pasirinktinai nubrėžkite savo tematiką:
// kelių valstybių sudėtingesnes vėliavas http://flagpedia.net/index
// pvz: Pietų Afrikos, Makedonijos, Norvegijos, Graikijos, Britanijos, ...
// arba futbolo, krepšinio ar ledo ritulio aikštes su žaidėjų pozicijomis  

    private void drawFreeThema() {
        gc.setLineWidth(2);
        gc.setStroke(Color.RED);
        gc.setFill(Color.RED);
        gc.strokeRect(150, 100, 300, 100);
        gc.fillRect(150, 100, 300, 100); // Raudonas vėliavos viršus 

        gc.setStroke(Color.YELLOW);
        gc.setFill(Color.YELLOW);
        gc.strokeRect(150, 200, 300, 100);
        gc.fillRect(150, 200, 300, 100); // Geltonas vėliavos viršus

        gc.setStroke(Color.GREEN);
        gc.setFill(Color.GREEN);
        gc.strokeRect(150, 300, 300, 100);
        gc.fillRect(150, 300, 300, 100); // Žalias vėliavos viršus

        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        double xStar[] = {280, 300, 320, 315, 340, 310, 300, 290, 260, 285};
        double yStar[] = {295, 280, 295, 270, 250, 250, 220, 250, 250, 270};
        
        gc.strokePolygon(xStar, yStar, xStar.length);
        gc.fillPolygon(xStar, yStar, xStar.length); //Ghana Flag            
    }
// kontrolinės užduotys gynimo metu:
// įvairios vėliavos, tiesiog tokios sudėtinės figūros kaip namukas,
// medis, eglė, sniego senio siluetas :-) ir t.t.    

    @Override
    public void createControls() {
        addButton("clear", e -> clearCanvas());
        addButton("grid", e -> baseGrid());
        addButton("HVC", e -> drawHVtoCenter());
        addButton("XC", e -> drawXtoCenter());
        addButton("pvz1", e -> drawExamples1());
        addButton("UniCode", e -> drawUnicode());
        addButton("drawGrid1", e -> drawGrid1());
        addButton("UniCode2", e -> drawUnicode());
        addButton("DrawExamples2", e -> drawExamples2());
        addButton("DrawExamples3", e -> drawExamples3());
        addButton("DrawOlympicRings", e -> drawOlympicRings());
        addButton("DrawFreeThema", e -> drawFreeThema());
        addNewHBox();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Braižymai Canvas lauke (KTU IF)");
        setCanvas(Color.WHITE, 1000, 500);
        super.start(stage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
