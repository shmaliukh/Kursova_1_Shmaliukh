package sample;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import sample.Heroes.Knight;
import sample.Heroes.Mag;
import sample.Heroes.Recruit;
import sample.Heroes.Soldier;
import sample.Towers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static sample.Main.pane;

public class World {
    public static final double mapWidth= pane.getMinWidth();
    public static final double mapHeight= pane.getMinHeight();

    public static ArrayList<Recruit> heroes = new ArrayList<>();
    public static Random rnd = new Random(new Date().getTime());





    private static MiniMap miniMap;

    private Tower1 newTower1;
    private Tower2 newTower2;
    private Tower3 newTower3;
    private Tower4 newTower4;
    private Tower5 newTower5;
    private Tower6 newTower6;

    public static Label counterRecruits = new Label();
    public static Label counterSoldiers = new Label();
    public static Label counterKnights  = new Label();
    public static Label counterMag  = new Label();

    public static MiniMap getMiniMap() {
        return miniMap;
    }
    public static void setMiniMap(MiniMap miniMap) {
        World.miniMap = miniMap;
    }



    private static int counter1 = 0;

    public static int getCounter1() {
        return counter1;
    }

    public static void setCounter1(int counter1) {
        World.counter1 = counter1;
    }

    public static int getCounter2() {
        return counter2;
    }

    public static void setCounter2(int counter2) {
        World.counter2 = counter2;
    }

    public static int getCounter3() {
        return counter3;
    }

    public static void setCounter3(int counter3) {
        World.counter3 = counter3;
    }

    public static int getCounter4() {
        return counter4;
    }

    public static void setCounter4(int counter4) {
        World.counter4 = counter4;
    }

    private static int counter2 = 0;
    private static int counter3 = 0;
    private static int counter4 = 0;
    public World(){
        Rectangle worldRectangle = new Rectangle(World.mapWidth, World.mapHeight, Color.BEIGE);
        pane.getChildren().add(worldRectangle);

        miniMap = new MiniMap();

        newTower1 = new Tower1();
        newTower2 = new Tower2();
        newTower3 = new Tower3();
        newTower4 = new Tower4();
        newTower5 = new Tower5();
        newTower6 = new Tower6();

    }

    public static void addNewHero(String name,
                                  int health,
                                  int damage,
                                  boolean isActive,
                                  double x, double y, int type) {
        Recruit NEW = new Recruit();
        switch (type) {
            case 1:
                NEW = new Recruit(name, health, damage, isActive, x, y);
                break;
            case 2:
                NEW = new Soldier(name, health, damage, isActive, x, y);
                break;
            case 3:
                NEW = new Knight(name, health, damage, isActive, x, y);
                break;
            case 4:
                NEW = new Mag(name, health, damage, isActive, x, y);
                break;
        }


        World.miniMap.addRecruit(NEW);
        heroes.add(NEW);
        miniMap.updateMap();
       /* System.out.println("\nNew hero created with:");
        System.out.println("name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", isActive=" + isActive + "\n");*/
    }



}
