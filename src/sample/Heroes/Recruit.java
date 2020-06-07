package sample.Heroes;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import sample.Main;
import javafx.scene.text.Text;
import sample.Tower1;

import static sample.Main.*;

public class Recruit implements Cloneable{



    /////////////////////////////// характеристики персонажа (героя)
    protected String name;
    protected int health;
    protected int damage;

    protected double x;
    protected double y;
    ///////////////////////////////

    /////////////////////////////// зображення героя
    public static Image imgRecruit;
    protected ImageView imageView;

    protected Rectangle rectActive;
    protected boolean isActive;

    protected Text nameText;

    protected Line lineHealth;
    protected Line lineDamage;
    protected Group allPictureOfHero;
    ///////////////////////////////






    ///////////////////////////////


    public int healthMax()
    {
        return 100;
    }
    public int damageMax()
    {
        return 1;
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
    public boolean isActive()
    {
        return isActive;
    }
    public ImageView getImageView() {
        return imageView;
    }
    public Rectangle getRectActive(){
        return rectActive;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
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
    public void setActive(boolean isActive ) {
        this.isActive = isActive;;
    }
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public Group getAllPictureOfHero() {
        return allPictureOfHero;
    }

    ///////////////////////////////
    ///////////////////////////////
    @Override
    public String toString() {
        return "Recruit{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", isActive=" + isActive +
                '}';
    }
    ///////////////////////////////
    ///////////////////////////////
    ///////////////////////////////
    ///////////////////////////////

    public Recruit() {

    }
    public Recruit(String name, int health, int damage,boolean isActive,double x, double y){
        System.out.println("Recruit_constructor");
        this.x=x;
        this.y=y;
        this.name=name;
        this.damage=damage;
        this.health=health;
        this.isActive=isActive;


        System.out.println("Printing");
        imageView = new ImageView(imgRecruit);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(120);
        nameText = new Text(name);
        nameText.setFont(new Font(16));

        lineDamage = new Line();
        lineDamage.setStrokeWidth(5);
        lineDamage.setStroke(Color.RED);

        lineHealth=new Line(); //!!!health
        lineHealth.setStrokeWidth(5);
        lineHealth.setStroke(Color.GREEN);


        rectActive=new Rectangle(91.0,112.0);
        rectActive.setFill(Color.TRANSPARENT);
        rectActive.setStrokeWidth(3);
        rectActive.setArcWidth(10);
        rectActive.setArcHeight(10);

        if(isActive==false){
            rectActive.setStroke(Color.LIGHTGREEN);
        }
        else {
            rectActive.setStroke(Color.ORANGE);
        }


        this.allPictureOfHero = new Group(imageView, nameText, lineDamage,lineHealth,rectActive);
        Main.pane.getChildren().add(allPictureOfHero);

        System.out.println("Printing end");

        this.toString();

        //Main.heroes.add(this);
        //Main.
    }
    public void removeFromScreen() {
        Main.pane.getChildren().remove(allPictureOfHero);
        System.out.println("Deleted "+ this.name);
    }


    public void move() {

        this.imageView.setX(x);
        this.imageView.setY(y);

        this.nameText.setX(x);
        this.nameText.setY(y-15);

        this.lineDamage.setStartX(x+2);
        this.lineDamage.setStartY(y-9);

        this.lineHealth.setStartX(x+2);
        this.lineHealth.setStartY(y-4);

        this.lineDamage.setEndX(x+(double)91*damage/3);//максимальна довжина лінії така як і прямокутник активації - 91, 200 -максимальний
        this.lineDamage.setEndY(y-9);

        this.lineHealth.setEndX(x+(double)91*health/200);
        this.lineHealth.setEndY(y-4);

        rectActive.setX(x);
        rectActive.setY(y);

        //System.out.println("dx"+dx+"dy"+dy);
    }

    public void interactionHeroes(int i,int j) {
        if(heroes.get(i).getHealth()!=0 && heroes.get(j).getHealth()!=0){
        if(heroes.get(i).getHealth()<=5){
            heroes.get(i).setDamage(0);
            heroes.get(i).setHealth(1);
            //System.out.println(heroes.get(i).getName() +" damage is "+ heroes.get(i).getDamage());
        }
        else{ heroes.get(i).setDamage(damageMax());}

        heroes.get(i).lineDamage.setEndX(this.lineDamage.getStartX() + (((double) getDamage())/3*91));
        heroes.get(i).lineHealth.setEndX(this.lineHealth.getStartX() + (((double) getHealth())/200*91));

        System.out.println(heroes.get(i).getName() +" damage is "+ heroes.get(i).getDamage());
        System.out.println(heroes.get(i).getName() +" health is "+ heroes.get(i).getHealth());
        //this.lineHealth.setStartY(this.lineHealth.getStartY() + dy);
    }
    }

    public void interactionTower1(int i,int counterOperateTower1) {


            heroes.get(i).setDamage(heroes.get(i).damageMax());
            if(heroes.get(i) instanceof Knight &&
                    heroes.get(i).getHealth() < heroes.get(i).healthMax()){
                heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower1.heal());

            }
            else if(heroes.get(i) instanceof Soldier &&
                    heroes.get(i).getHealth() < heroes.get(i).healthMax()){
                heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower1.heal());

            }
            else if(heroes.get(i) instanceof Recruit &&
                    heroes.get(i).getHealth() < heroes.get(i).healthMax()){
                heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower1.heal());

            }


    }

    public void tryToActive(double mx, double my) {

        if( imageView.boundsInParentProperty().get().contains(mx,my) )
        {
            //Main.group.getChildren().remove(imageView);
            isActive=!isActive;
            if(isActive)
            {
                //imageView=new ImageView(imageView);
                rectActive.setStroke(Color.ORANGE);
                System.out.println("Activated " + this.name);
            }
            else
            {
                //imageView=new ImageView(imgHeroActive);
                rectActive.setStroke(Color.LIGHTGREEN);
                System.out.println("De-activated " + this.name);
            }

            imageView.setX(this.rectActive.getX());
            imageView.setY(this.rectActive.getY());

        }
    }
    public void left(double delta) {
        if (isActive && x >= 0) {
            x -= delta;
        }
    }

    public void right(double delta) {
        if (isActive && x <= pane.getWidth() - 100) {
            x += delta;
        }
    }

    public void up(double delta) {
        if (isActive && y >= 0) {
            y -= delta;
        }
    }

    public void down(double delta) {
        if (isActive && y <= Main.pane.getHeight() - 200) {
            y += delta;
        }
    }


    public String stringHeroType()
    {
        return "Recruit";
    }



}
