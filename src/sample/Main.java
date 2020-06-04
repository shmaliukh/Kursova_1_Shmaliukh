package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.Heroes.HeroLevel;
import sample.Heroes.Knight;
import sample.Heroes.Recruit;
import sample.Heroes.Soldier;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {
    public final int sceneWidth = 1000;
    public final int sceneHeight = 800;

    public static Scene scene;
    public static Scene add;

    public static Group group=new Group();
    public static Group group2=new Group();

    public static ArrayList<Recruit> heroes = new ArrayList<>();

    public static Random rnd= new Random();

    public static String[] heroNames={"Влад","Вася","Віталік","Валя","Толя","Саша","Топа","Іван","Юра","Афінна","Аврора","Алла",
            "Андрій","Олег","Богдана","Вероніка","Віка","Стас","Христя","Жанна","Лариса","Вова","Кирил","Дуся",
            "Іра","Рома","Маша","Каріна","Поліна","Єва","Адам","Джон","WOT)))","Ярик","Вітя","Валера"};



//    public static void addNewHero(String name,
//                                  double life,
//                                  double damage,
//                                  boolean isHeroActive,
//                                  double x, double y,
//                                  int side){
//        heroes.add(this));
//    }





    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tower Defender");
        primaryStage.getIcons().add(new Image("sample/images/icon.png"));

        Recruit.imgRecruit= new Image("/sample/images/imgHero1.png");
        Soldier.imgSoldier= new Image("/sample/images/imgHero2.png");
        Knight.imgKnight= new Image("/sample/images/imgHero3.png");


        Group group=new Group();
        Group group2=new Group();

        Rectangle world = new Rectangle(World.mapwx, World.mapwy, Color.BEIGE);
        group.getChildren().add(world);

        World WORLD =new World();

        group2.getChildren().add(group);


        Recruit first=new Recruit(heroNames[(int)(rnd.nextInt(heroNames.length))], 5, 100,false,200,200);


        heroes.add(first);


        group2.getChildren().add(group);


        scene = new Scene(group2, sceneWidth, sceneHeight, Color.RED);
        add = new Scene(root,700,500, Color.BEIGE);

        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){

                System.out.printf("Mouse event %f %f scene  %f %f screen %f %f\n",
                        event.getX(), event.getY(),
                        event.getSceneX(), event.getSceneY(),
                        event.getScreenX(), event.getScreenY());

//                for( Recruit r:heroes )
//                    r.tryToActive(event.getX(), event.getY(), r.getSide() );
//
                }
        });

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
