package sample.Heroes;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import sample.Main;
import javafx.scene.text.Text;

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

    private Rectangle rectActive;
    protected boolean isActive;

    private Text nameText;

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
    public boolean getisActive()
    {
        return isActive;
    }
    public ImageView getImageView() {
        return imageView;
    }
    public Rectangle getrectActive(){
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
    public void setisActive(boolean isActive ) {
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
    public Recruit(String name, int damage, int health,boolean isActive,double x, double y){
        System.out.println("Recruit_constructor");
        this.x=x;
        this.y=y;
        this.name=name;
        this.damage=damage;
        this.health=health;
        this.isActive=isActive;

        imageView = new ImageView(imgRecruit);
        imageView.setX(x);
        imageView.setY(y);
        Main.group.getChildren().add(imageView);

        this.print(name,damage,health,x,y);

        this.toString();

        //Main.heroes.add(this);
        //Main.
    }
    public void removeFromScreen() {
        Main.group.getChildren().removeAll(imageView,nameText,lineDamage,lineHealth);
        Main.group.getChildren().remove(rectActive);
        System.out.println("Deleted "+ this.name);
    }

    public void move(double dx, double dy ) {
        this.imageView.setX(this.imageView.getX() +dx);
        this.imageView.setY(this.imageView.getY() +dy);

        this.nameText.setX(this.nameText.getX()+dx);
        this.nameText.setY(this.nameText.getY()+dy);

        this.lineDamage.setStartX(this.lineDamage.getStartX() + dx);
        this.lineDamage.setStartY(this.lineDamage.getStartY() + dy);

        this.lineHealth.setStartX(this.lineHealth.getStartX() + dx);
        this.lineHealth.setStartY(this.lineHealth.getStartY() + dy);

        this.lineDamage.setEndX(this.lineDamage.getEndX() + dx);
        this.lineDamage.setEndY(this.lineDamage.getEndY() + dy);

        this.lineHealth.setEndX(this.lineHealth.getEndX() + dx);
        this.lineHealth.setEndY(this.lineHealth.getEndY() + dy);

        rectActive.setX(rectActive.getX()+dx);
        rectActive.setY(rectActive.getY()+dy);
        System.out.println("dx"+dx+"dy"+dy);
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
                rectActive.setStroke(Color.BEIGE);
                System.out.println("De-activated " + this.name);
            }

            imageView.setX(this.rectActive.getX());
            imageView.setY(this.rectActive.getY());


            Main.group.getChildren().add(imageView);
        }
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
        System.out.println("Printing");
        nameText = new Text(name);
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
        if(isActive==false){
            rectActive.setStroke(Color.TRANSPARENT);
        }
        else {
            rectActive.setStroke(Color.YELLOW);
        }
        nameText.setLayoutX(x);
        nameText.setLayoutY(y-50);

        lineDamage.setLayoutX(x);
        lineDamage.setLayoutY(y-20.0);

        lineHealth.setLayoutX(x);
        lineHealth.setLayoutY(y-20.0);

        rectActive.setX(x);
        rectActive.setY(y);

        Main.group.getChildren().addAll(nameText,lineDamage,lineHealth,rectActive);

        System.out.println("Printing end");
    }



    public String stringHeroType()
    {
        return "Recruit";
    }



}
