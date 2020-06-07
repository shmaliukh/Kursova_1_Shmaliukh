package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import sample.Heroes.Knight;
import sample.Heroes.Recruit;
import sample.Heroes.Soldier;

import java.util.HashMap;

public class MiniMap {
    final static private double SCALE = 0.1;
    private Pane pane;
    private double xChord;
    private double yChord;
    private HashMap<Recruit, ImageView> recruits;
    private Rectangle mainArea;

    public MiniMap() {
        this.pane = new Pane();
        this.pane.setMinWidth(Main.pane.getMinWidth() * MiniMap.SCALE);
        this.pane.setMinHeight(Main.pane.getMinHeight() * MiniMap.SCALE);
        recruits = new HashMap<>();


        Rectangle rectangle = new Rectangle(0, 0, pane.getMinWidth(), pane.getMinHeight());
        rectangle.setFill(Color.LIGHTGREY);
        Rectangle border = new Rectangle(0, 0, pane.getMinWidth(), pane.getMinHeight());
        border.setFill(Color.TRANSPARENT);
        border.setStrokeWidth(2);
        border.setStroke(Color.BLACK);
        Label label = new Label("Map");
        label.setFont(new Font(16));
        label.setLayoutX(pane.getMinWidth() / 2.1);

        mainArea = new Rectangle(0, 0, Main.scene.getWidth() * MiniMap.SCALE, Main.scene.getHeight() * MiniMap.SCALE - 10);
        mainArea.setFill(Color.TRANSPARENT);
        mainArea.setStrokeWidth(2);
        mainArea.setStroke(Color.YELLOW);
        this.pane.getChildren().addAll(rectangle, border, label, mainArea);

      /*  this.pane.setOnMousePressed(event -> {
            this.moveTo(event.getX(), event.getY());
        });*/
    }

    /*public void moveTo(double x, double y) {
        if (x < mainArea.getWidth() / 2) {
            Main.getScrollPane().setHvalue(0);
        } else if (x > pane.getWidth() - mainArea.getWidth() / 2) {
            Main.getScrollPane().setHvalue(1);
        } else Main.getScrollPane().setHvalue(x / pane.getWidth());

        if (y < mainArea.getHeight() / 2) {
            Main.getScrollPane().setVvalue(0);
        } else if (y > pane.getHeight() - mainArea.getHeight() / 2) {
            Main.getScrollPane().setVvalue(1);
        } else Main.getScrollPane().setVvalue(y / pane.getHeight());
    }*/

    public Pane getPane() {
        return pane;
    }

    public Rectangle getMainArea() {
        return mainArea;
    }

    public void setXChord(double xChord) {
        this.xChord = xChord;
    }

    public void setYChord(double yChord) {
        this.yChord = yChord;
    }

    public static double getSCALE() {
        return SCALE;
    }

    public void addRecruit(Recruit recruit) {
        ImageView imageView;
        if (recruit instanceof Knight){
            imageView = new ImageView(new Image("/sample/images/imgHero3.png"));
        } else if (recruit instanceof Soldier){
            imageView = new ImageView(new Image("/sample/images/imgHero2.png"));
        } else {
            imageView = new ImageView(new Image("/sample/images/imgHero1.png"));

        }

        imageView.setLayoutX(recruit.getX() * MiniMap.SCALE);
        imageView.setLayoutY(recruit.getY() * MiniMap.SCALE);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight((recruit.getImageView().getFitHeight()+70) * MiniMap.SCALE);
        recruits.put(recruit, imageView);
        pane.getChildren().add(imageView);
    }

    public void deleteRecruit(Recruit recruit) {
        pane.getChildren().remove(recruits.get(recruit));
        recruits.remove(recruit);
    }


    public void updateMap() {

        for (Recruit recruit : Main.heroes) {
            ImageView imageView = recruits.get(recruit);
            imageView.setLayoutX(recruit.getX() * MiniMap.SCALE);
            imageView.setLayoutY(recruit.getY() * MiniMap.SCALE);
        }
    }
}
