package sample.Heroes;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Main;

public class Mag extends Knight{
    public static Image imgMag;

    public int healthMax()
    {
        return 1800;
    }
    public int damageMax()
    {
        return -1;
    }

    public Mag(){};
    public Mag(String name, int health, int damage, boolean isActive, double x, double y){
        super();
        this.x=x;
        this.y=y;
        this.name=name;
        this.damage=damage;
        this.health=health;
        this.isActive=isActive;
        this.type = 4;


        //System.out.println("Printing");
        imageView = new ImageView(imgMag);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(120);
        nameText = new Text(name);
        nameText.setFont(new Font(16));

        lineDamage = new Line();
        lineDamage.setStrokeWidth(5);
        lineDamage.setStroke(Color.AQUA);

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

        //System.out.println("Printing end");

        this.toString();


        //Main.heroes.add(this);
    }



    public String stringHeroType()
    {
        return "Mag";
    }
}
