package sample.Towers;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.Main;
import sample.World;

public class Tower5 {
//    public boolean intersects( double x, double y, double wx, double wy ) {
//        if( image.intersects(x,y,wx,wy) )return true;
//
//        return false;
//    }

    public static Image imgTower5;
    public static ImageView imageView;

    protected static Text nameText;

    protected Group allPictureOfTower5;

    private static double x = 1800;
    private static double y = 1270;

    private static int counterOperate;

    public static int heal(){
        return 2;
    }

    public static double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public static double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }



    public static ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Text getNameText() {
        return nameText;
    }

    public void setNameText(Text nameText) {
        this.nameText = nameText;
    }

    public Group getAllPictureOfTower5() {
        return allPictureOfTower5;
    }

    public void setAllPictureOfTower5(Group allPictureOfTower5) {
        this.allPictureOfTower5 = allPictureOfTower5;
    }


    public static void setOperate(int counter){
        nameText.setText("Опрацьовує: "+counter);
        counterOperate = counter;
    }
    public int getOperate(){

        return counterOperate;
    }



    public Tower5(){
        imageView = new ImageView(imgTower5);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(280);//Розкоментірував і підібрав число 280, виглядає як і тоді, але тепер для мінікарти краще1

        imageView.setX(getX());
        imageView.setY(getY());

        nameText = new Text("Опрацьовує: "+ getOperate());

        nameText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        nameText.setStrokeWidth(1);
        nameText.setStroke(Color.AQUA);

        nameText.setX(getX());
        nameText.setY(getY()-10);

        Rectangle rect=new Rectangle(imageView.getImage().getWidth(),imageView.getImage().getHeight());
        rect.setFill(Color.TRANSPARENT);
        rect.setStrokeWidth(5);
        rect.setArcWidth(10);
        rect.setArcHeight(10);
        rect.setStroke(Color.BROWN);

        rect.setX(getX());
        rect.setY(getY());

        this.allPictureOfTower5 = new Group(imageView, nameText, rect);
        Main.pane.getChildren().add(allPictureOfTower5);
        World.getMiniMap().addTower(imageView);

    };


}
