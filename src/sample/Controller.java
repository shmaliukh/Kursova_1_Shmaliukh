package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import sample.Heroes.Recruit;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.SplittableRandom;

import static sample.Main.*;

public class Controller implements Initializable {
    public TextField txtX;
    public TextField txtY;
    public TextField txtName;
    public TextField txtHealth;
    public TextField txtDamage;
    public CheckBox isActive;
    public TextField txtCounter;
    public RadioButton Side1;
    public RadioButton Side2;
    //    public TextField txtSide;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void pressOK(ActionEvent actionEvent) {

        String name= txtName.getText();
        int life;
        int damage;
        boolean isHeroActive=isActive.isSelected();
        //int side=1;

        double x;
        double y;

        int counter;

        if(txtName.getText().isEmpty()){
            name = heroNames[(int)rnd.nextInt(heroNames.length)];
        }
        else {
            name =txtName.toString();
        }


        if(txtCounter.getText().isEmpty()){
            counter =1;
        }
        else {
            counter =(int)Double.parseDouble(txtCounter.getText());
        }


        if(txtHealth.getText().isEmpty()){
            life =rnd.nextInt(100);
        }
        else {
            life= Math.abs(Integer.parseInt(txtHealth.getText()));
        }
        if(txtDamage.getText().isEmpty()){
            damage =rnd.nextInt(100);
        }
        else {
            damage= Math.abs(Integer.parseInt(txtDamage.getText()));
        }
        if(txtX.getText().isEmpty()){
            x =(double)rnd.nextInt((int)(World.mapHeight- imgSizeHeight));
        }
        else {
            x= Double.parseDouble(txtX.getText());
        }
        if(txtY.getText().isEmpty()){
            y= (double)rnd.nextInt((int)(World.mapWidth- imgSizeWidth-20));
        }
        else {
            y= Double.parseDouble(txtY.getText());
        }


        for( int i = 0 ; i < counter ; i++ ) {
            if(i==0)Main.addNewHero(name,life,damage,isHeroActive, x, y);
            else Main.addNewHero(heroNames[(int)rnd.nextInt(heroNames.length)],
                    rnd.nextInt(100),
                    rnd.nextInt(100),
                    isHeroActive,
                    (double)rnd.nextInt((int) (World.mapHeight-imgSizeHeight)),
                    (double)rnd.nextInt((int) (World.mapWidth-imgSizeWidth-20)));

        }

        Main.pStage.setScene(scene);

    }
}
