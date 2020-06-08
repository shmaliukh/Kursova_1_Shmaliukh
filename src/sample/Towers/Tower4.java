package sample.Towers;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Main;
import sample.World;

public class Tower4 {


    public static Image imgTower4;
    public static ImageView imageView;

    protected static Text nameText;

    protected Group allPictureOfTower4;

    private static double x = 3000;
    private static double y = 200;

    private static int counterOperate;

    public static int heal(){
        return 1;
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

    public Group getAllPictureOfTower4() {
        return allPictureOfTower4;
    }

    public void setAllPictureOfTower4(Group allPictureOfTower4) {
        this.allPictureOfTower4 = allPictureOfTower4;
    }


    public static void setOperate(int counter){
        nameText.setText("Опрацьовує: "+counter);
        counterOperate = counter;
    }
    public int getOperate(){

        return counterOperate;
    }



    public Tower4(){
        imageView = new ImageView(imgTower4);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(355);

        imageView.setX(getX());
        imageView.setY(getY());

        nameText = new Text("Опрацьовує: "+ getOperate());
        nameText.setFont(new Font(16));

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

        this.allPictureOfTower4 = new Group(imageView, nameText, rect);
        Main.pane.getChildren().add(allPictureOfTower4);
        World.getMiniMap().addTower(imageView);

    };


}
