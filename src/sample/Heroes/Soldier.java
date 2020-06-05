package sample.Heroes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class Soldier extends Recruit {
    public static Image imgSoldier;

    public Soldier(){};
    public Soldier(String name, int damage, int health,boolean isActive,double x, double y){
        super();
        this.name=name;
        this.damage=damage;
        this.health=health;
        this.isActive=isActive;

        imageView = new ImageView(imgSoldier);
        Main.group.getChildren().add(imageView);
        imageView.setX(x);
        imageView.setY(y);


        this.print(name,damage,health,x,y);

        //Main.heroes.add(this);
    }



    public int health()
    {
        return 150;
    }
    public int damage()
    {
        return 7;
    }

}
