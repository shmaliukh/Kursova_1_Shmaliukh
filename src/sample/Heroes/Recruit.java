package sample.Heroes;


import sample.Towers.*;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import sample.Main;
import javafx.scene.text.Text;
import sample.World;

import static sample.Main.*;
import static sample.World.getMiniMap;
import static sample.World.heroes;

public class Recruit implements Cloneable{



    /////////////////////////////// характеристики персонажа (героя)
    protected String name;
    protected int health;
    protected int damage;

    protected double x;
    protected double y;
    protected int type;
    ///////////////////////////////
    //рух
    protected int direction;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public static Image getImgRecruit() {
        return imgRecruit;
    }

    public static void setImgRecruit(Image imgRecruit) {
        Recruit.imgRecruit = imgRecruit;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Rectangle getRectActive() {
        return rectActive;
    }

    public void setRectActive(Rectangle rectActive) {
        this.rectActive = rectActive;
    }

    public Text getNameText() {
        return nameText;
    }

    public void setNameText(Text nameText) {
        this.nameText = nameText;
    }

    public Line getLineHealth() {
        return lineHealth;
    }

    public void setLineHealth(Line lineHealth) {
        this.lineHealth = lineHealth;
    }

    public Line getLineDamage() {
        return lineDamage;
    }

    public void setLineDamage(Line lineDamage) {
        this.lineDamage = lineDamage;
    }

    public void setAllPictureOfHero(Group allPictureOfHero) {
        this.allPictureOfHero = allPictureOfHero;
    }


    ///////////////////////////////


    public int healthMax()
    {
        return 1000;
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
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
    public void setX(double x) {
        this.x = x;
    }   //делегування
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
        this(null,0,0,true,0,0);
    }
    public Recruit(String name, int health, int damage,boolean isActive,double x, double y){
        //System.out.println("Recruit_constructor");
        this.x=x;
        this.y=y;
        this.name=name;
        this.damage=damage;
        this.health=health;
        this.isActive=isActive;
        this.type = 1;
        this.direction = World.rnd.nextInt(8);

        //System.out.println("Printing");
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
        Main.pane.getChildren().add(pane.getChildren().size()-1, allPictureOfHero);

        //System.out.println("Printing end");

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

        this.lineDamage.setEndX(x+((double)(Math.abs(getDamage()))) / 3 * 91);
        this.lineDamage.setEndY(y-9);

        this.lineHealth.setEndX(x+(double) health/2000*91);
        this.lineHealth.setEndY(y-4);

        rectActive.setX(x);
        rectActive.setY(y);

        //System.out.println("dx"+dx+"dy"+dy);
    }
    public void freeRun(double delta) {
        delta = delta/2;
        if (!this.isActive()) {
            switch (this.direction) {
                case 0:
                    this.y -= delta;
                    break;
                case 1:
                    this.y -= delta;
                    this.x += delta;
                    break;
                case 2:
                    this.x += delta;
                    break;
                case 3:
                    this.x += delta;
                    this.y += delta;
                    break;
                case 4:
                    this.y += delta;
                    break;
                case 5:
                    this.y += delta;
                    this.x -= delta;
                    break;
                case 6:
                    this.x -= delta;
                    break;
                case 7:
                    this.x -= delta;
                    this.y -= delta;
                    break;
            }
        }
        if (this.imageView.getX() + 100 >= Main.pane.getWidth()) {
            this.direction = (byte) (World.rnd.nextInt(3) + 5);
        } else if (this.imageView.getX() <= 0) {
            this.direction = (byte) (World.rnd.nextInt(3) + 1);
        } else if (this.imageView.getY() <= 0 ) {
            this.direction = (byte) (World.rnd.nextInt(3) + 3);
        } else if (this.imageView.getY() + 170 >= Main.pane.getHeight()) {
            this.direction = (byte) World.rnd.nextInt(2);
        }
    }

    public void interactionHeroes(int i,int j) {
        if( heroes.get(i).getHealth() != 0 && heroes.get(j).getHealth() != 0
            //для Mag)    && heroes.get(i).getHealth() <= heroes.get(i).healthMax()
                                                                                ) {
            if( heroes.get(i).getHealth() <= 5 ) {
                //getMiniMap().deleteRecruit(heroes.get(i));
                heroes.get(i).setDamage(0);
                heroes.get(i).setHealth(1);
                //System.out.println(heroes.get(i).getName() +" damage is "+ heroes.get(i).getDamage());
            } else {
                heroes.get(i).setDamage(heroes.get(i).damageMax());
                if(heroes.get(i).getHealth()>heroes.get(i).healthMax()){        //для Mag
                    heroes.get(i).setHealth(heroes.get(i).healthMax());
                }
                heroes.get(i).lineDamage.setEndX(this.lineDamage.getStartX() + (((double) (Math.abs(heroes.get(i).getDamage()))) / 3 * 91));
                heroes.get(i).lineHealth.setEndX(this.lineHealth.getStartX() + (((double) heroes.get(i).getHealth()) / 2000 * 91));

                //System.out.println(heroes.get(i).getName() + " damage is " + heroes.get(i).getDamage());
                //System.out.println(heroes.get(i).getName() + " health is " + heroes.get(i).getHealth());
                //this.lineHealth.setStartY(this.lineHealth.getStartY() + dy);
            }
        }
    }

    public void interactionTower1(int i) {
        heroes.get(i).setDamage(heroes.get(i).damageMax());
            if( heroes.get(i) instanceof Mag &&
                    heroes.get(i).getHealth() < heroes.get(i).healthMax()){
                heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower2.heal());

            }
            else if( heroes.get(i) instanceof Knight &&
                    heroes.get(i).getHealth() < heroes.get(i).healthMax()){
                heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower1.heal());

            }
            else if( heroes.get(i) instanceof Soldier &&
                    heroes.get(i).getHealth() < heroes.get(i).healthMax()){
                heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower1.heal());

            }
            else if( heroes.get(i) instanceof Recruit &&
                    heroes.get(i).getHealth() < heroes.get(i).healthMax()){
                heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower1.heal());
            }
        if(heroes.get(i).getHealth()>heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).healthMax());
        }
    }
    public void interactionTower2(int i) {
        heroes.get(i).setDamage(heroes.get(i).damageMax());
        if( heroes.get(i) instanceof Mag &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower2.heal());

        }
        else if( heroes.get(i) instanceof Knight &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower2.heal());

        }
        else if( heroes.get(i) instanceof Soldier &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower2.heal());

        }
        else if( heroes.get(i) instanceof Recruit &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower2.heal());
        }
        if(heroes.get(i).getHealth()>heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).healthMax());
        }
    }

    public void interactionTower3(int i) {
        heroes.get(i).setDamage(heroes.get(i).damageMax());
        if( heroes.get(i) instanceof Mag &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower2.heal());

        }
        else if(heroes.get(i) instanceof Knight &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower3.heal());

        }
        else if(heroes.get(i) instanceof Soldier &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower3.heal());

        }
        else if(heroes.get(i) instanceof Recruit &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower3.heal());
        }
        if(heroes.get(i).getHealth()>heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).healthMax());
        }
    }
    public void interactionTower4(int i) {
        heroes.get(i).setDamage(heroes.get(i).damageMax());
        if( heroes.get(i) instanceof Mag &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower2.heal());

        }
        else if(heroes.get(i) instanceof Knight &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower4.heal());

        }
        else if(heroes.get(i) instanceof Soldier &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower4.heal());

        }
        else if(heroes.get(i) instanceof Recruit &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower4.heal());
        }
        if(heroes.get(i).getHealth()>heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).healthMax());
        }
    }
    public void interactionTower5(int i) {
        heroes.get(i).setDamage(heroes.get(i).damageMax());
        if( heroes.get(i) instanceof Mag &&
                heroes.get(i).getHealth() <= heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower2.heal());


        }
        else if(heroes.get(i) instanceof Knight &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower5.heal());

        }
        else if(heroes.get(i) instanceof Soldier &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower5.heal());

        }
        else if(heroes.get(i) instanceof Recruit &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower5.heal());
        }
        if(heroes.get(i).getHealth()>heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).healthMax());
        }
    }
    public void interactionTower6(int i) {
        heroes.get(i).setDamage(heroes.get(i).damageMax());
        if( heroes.get(i) instanceof Mag &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower2.heal());

        }
        else if(heroes.get(i) instanceof Knight &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
                heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower6.heal());

        }
        else if(heroes.get(i) instanceof Soldier &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower6.heal());

        }
        else if(heroes.get(i) instanceof Recruit &&
                heroes.get(i).getHealth() < heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).getHealth() + Tower6.heal());
        }
        if(heroes.get(i).getHealth()>heroes.get(i).healthMax()){
            heroes.get(i).setHealth(heroes.get(i).healthMax());
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
