package sample.Heroes;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import sample.Main;

public class Recruit {



    /////////////////////////////// характеристики персонажа (героя)
    protected String name;
    protected int health;
    protected int damage;


    ///////////////////////////////

    /////////////////////////////// зображення героя
    public static Image imgRecruit;
    protected ImageView imageView;

    protected Rectangle rectActive;
    protected boolean isActive;

    protected Label nameText;

    private Line lineHealth;
    private Line lineDamage;


    ///////////////////////////////

    ///////////////////////////////


    public int health()
    {
        return 100;
    }
    public int damage()
    {
        return 5;
    }
    public int speed(){return 5;}
    /////////////////////////////// getters
    public String getName()
    {
        return name;
    }
    public int getHealth()
    {
        return health;
    }
    public int getDamage()
    {
        return damage;
    }

    ///////////////////////////////
    /////////////////////////////// setters
    public void setName(String name) {
        this.name = name;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    ///////////////////////////////
    ///////////////////////////////
    ///////////////////////////////
    ///////////////////////////////
    ///////////////////////////////

    public Recruit() {

    }
    public Recruit(String name, int damage, int health,boolean isActive,double x, double y){
        this.name=name;
        this.damage=damage;
        this.health=health;
        this.isActive=isActive;

        imageView = new ImageView(imgRecruit);
        Main.group.getChildren().add(imageView);
        imageView.setX(x);
        imageView.setY(y);


        this.print(name,damage,health,x,y);
        Main.heroes.add(this);
    }

//    public void addHero(HeroLevel cl, String name, int damage, int health,boolean isActive)
//    {
//        Recruit NEW =new Recruit();
//
//        double  x=0;
//        double  y=0;
//
//        switch(cl){
//            case LIGHT:
//                NEW= new Recruit();
//                break;
//            case MEDIUM:
//                NEW= new Soldier();
//                break;
//            case HEAVY:
//                NEW= new Knight();
//                break;
//        }
//
//        this.print(name, damage, health, x, y);
//
//
//
//        Main.group.getChildren().add(nameText);
//        Main.heroes.add(new Recruit());
//    }
    public void print( String name, int damage, int health, double x, double y){



        nameText = new Label(name);
        nameText.setText(name);
        nameText.setFont(new Font(16));

        lineDamage = new Line(0, 0 , (double)damage/100*91 ,  0);
        lineDamage.setStrokeWidth(5);
        lineDamage.setStroke(Color.RED);

        lineHealth=new Line(0,5,(double)health/200*91,5); //!!!health
        lineHealth.setStrokeWidth(5);
        lineHealth.setStroke(Color.GREEN);

        rectActive=new Rectangle(91.0,112.0);
        rectActive.setFill(Color.TRANSPARENT);
        rectActive.setStrokeWidth(3);
        rectActive.setStroke(Color.TRANSPARENT);

        nameText.setLayoutX(x);
        nameText.setLayoutY(y-50);

        lineDamage.setLayoutX(x);
        lineDamage.setLayoutY(y-20.0);

        lineHealth.setLayoutX(x);
        lineHealth.setLayoutY(y-20.0);

        rectActive.setX(x);
        rectActive.setY(y);

        Main.group.getChildren().addAll(nameText,lineDamage,lineHealth,rectActive);

    }



    public String stringHeroType()
    {
        return "Recruit";
    }



}
