package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.Heroes.Knight;
import sample.Heroes.Recruit;
import sample.Heroes.Soldier;

import java.util.ArrayList;
import java.util.Random;

public class Main extends Application {
    public final int sceneWidth = 1000;
    public final int sceneHeight= 1000;

    public static final int imgSizeWidth = 91;////|@@@@@@
    public static final int imgSizeHeight = 112;////|@@@@@@

    public static Stage pStage;
    public static Scene scene;
    public static Scene input;

    public static Group group;


    public static ArrayList<Recruit> heroes = new ArrayList<>();
    public static Random rnd= new Random();

    public static String[] heroNames={"Влад","Вася","Віталік","Валя","Толя","Саша","Топа","Іван","Юра","Афінна",
            "Аврора","Алла","Андрій","Олег","Богдана","Вероніка","Віка","Стас","Христя","Жанна","Лариса","Вова",
            "Кирил","Дуся","Іра","Рома","Маша","Каріна","Поліна","Єва","Адам","Джон","Ярик","Вітя","Валера"};



    public static void addNewHero(String name,
                                  int health,
                                  int damage,
                                  boolean isActive,
                                  double x, double y,int type){
        Recruit NEW =new Recruit();
        switch (type){
            case 1:
                NEW=new Recruit(name,health, damage, isActive, x, y);
                break;
            case 2:
                NEW=new Soldier(name,health, damage, isActive, x, y);
                break;
            case 3:
                NEW=new Knight(name,health, damage, isActive, x, y);
                break;
        }

        heroes.add(NEW);
        System.out.println("\nNew hero created with:");
        System.out.println("name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", isActive=" + isActive+"\n");
    }


    public void java_util_Arrays(){
        System.out.println("java_util_Arrays:");
        Recruit buffArray[]=new Recruit[heroes.size()];
        for( int i = 0 ; i < heroes.size() ; i++ ) {
            Recruit buf =heroes.get(i);
            buffArray[i]=buf;
            System.out.println("Recruit{" +
                    "name='" + buffArray[i].getName() + '\'' +
                    ", health=" + buffArray[i].getHealth() +
                    ", damage=" + buffArray[i].getDamage() +
                    ", isActive=" + buffArray[i].getisActive() +
                    '}');
        }
        System.out.println("java_util_Arrays_END");

    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        sample.Main.pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Tower Defender");
        primaryStage.getIcons().add(new Image("sample/images/icon.png"));


        group = new Group();
        Group group2=new Group();
        scene = new Scene(group, sceneWidth, sceneHeight, Color.RED);
        Rectangle world = new Rectangle(World.mapWidth, World.mapHeight, Color.BEIGE);
        group.getChildren().add(world);

        try{
        Recruit.imgRecruit= new Image("/sample/images/imgHero1.png");
        Soldier.imgSoldier= new Image("/sample/images/imgHero2.png");
        Knight.imgKnight= new Image("/sample/images/imgHero3.png");
        }catch (Exception e){
            System.out.println("Не удалось загрузить изображение!");
        }



        heroes.add(new Recruit(heroNames[(int)(rnd.nextInt(heroNames.length))], 1, 100,true,200,200));
        heroes.add(new Soldier(heroNames[(int)(rnd.nextInt(heroNames.length))], 1, 100,true,100,200));
        System.out.println(heroes.toString());



        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){

                System.out.printf("Mouse event %f %f scene  %f %f screen %f %f\n",
                        event.getX(), event.getY(),
                        event.getSceneX(), event.getSceneY(),
                        event.getScreenX(), event.getScreenY());

                for( Recruit r:heroes )
                    r.tryToActive(event.getX(), event.getY());

            }
        });
        input = new Scene(root,700,500, Color.BEIGE);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                  @Override
                                  public void handle(KeyEvent event) {
                                      double x=0;
                                      double y=0;

                                      double delta=5.0;
                                      if(event.isShiftDown())delta*=10.0;

                                      switch (event.getCode()){
                                          case INSERT:

                                              primaryStage.setScene(input);

//                                          case PAGE_UP:
//                                              //addNewHeroRand();
//                                              break;
                                          case TAB:
                                              java_util_Arrays();
                                              break;
                                          case ENTER:
                                              for( int i = 0 ; i < heroes.size(); i++ ){
                                                  if(heroes.get(i).getisActive()!=true)
                                                      heroes.get(i).tryToActive(heroes.get(i).getImageView().getX(),heroes.get(i).getImageView().getY());
                                              }
                                              break;
                                          case ESCAPE:
                                              for( int i = 0 ; i < heroes.size(); i++ ){
                                                  if(heroes.get(i).getisActive()==true)
                                                      heroes.get(i).tryToActive(heroes.get(i).getImageView().getX(),heroes.get(i).getImageView().getY());
                                              }

                                              break;
                                          case DELETE:
                                              for(int i=heroes.size()-1; i>=0; --i )
                                              {
                                                  Recruit hero= heroes.get(i);
                                                  if( hero.getisActive() )
                                                  {
                                                      hero.removeFromScreen();
                                                      heroes.remove(i);
                                                  }
                                              }
                                              break;

                                          case SPACE:
                                              if(rnd.nextInt(4)==1)y-=delta;
                                              if(rnd.nextInt(4)==1)y+=delta;
                                              if(rnd.nextInt(4)==1)x-=delta;
                                              if(rnd.nextInt(4)==1)x+=delta;
                                              if(rnd.nextInt(4)==1)x+=delta;

                                              break;
                                          case DIGIT1:
                                              Recruit NEW1 = new Recruit(heroNames[(int)rnd.nextInt(heroNames.length)],
                                                      rnd.nextInt(100),
                                                      rnd.nextInt(100),
                                                      true,
                                                      (double)rnd.nextInt((int) (World.mapHeight-imgSizeHeight)),
                                                      (double)rnd.nextInt((int) (World.mapWidth-imgSizeWidth-20)));
                                              heroes.add(NEW1);
                                              break;
                                          case DIGIT2:
                                              Recruit NEW2 = new Soldier(heroNames[(int)rnd.nextInt(heroNames.length)],
                                                      rnd.nextInt(100),
                                                      rnd.nextInt(100),
                                                      true,
                                                      (double)rnd.nextInt((int) (World.mapHeight-imgSizeHeight)),
                                                      (double)rnd.nextInt((int) (World.mapWidth-imgSizeWidth-20)));
                                              heroes.add(NEW2);
                                              break;
                                          case DIGIT3:
                                              Recruit NEW3 = new Knight(heroNames[(int)rnd.nextInt(heroNames.length)],
                                                      rnd.nextInt(100),
                                                      rnd.nextInt(100),
                                                      true,
                                                      (double)rnd.nextInt((int) (World.mapHeight-imgSizeHeight)),
                                                      (double)rnd.nextInt((int) (World.mapWidth-imgSizeWidth-20)));
                                              heroes.add(NEW3);
                                              break;
                                          case UP:
                                              y-=delta;break;
                                          case DOWN:
                                              y+=delta;break;
                                          case LEFT:
                                              x-=delta; break;
                                          case RIGHT:
                                              x+=delta; break;
                                      }

                                      for( int i = 0 ; i < heroes.size(); i++ ) {

                                          if( heroes.get(i).getisActive() &&
                                                  heroes.get(i).getrectActive().getX() + x > 0 && heroes.get(i).getrectActive().getX() + x < World.mapHeight- imgSizeHeight &&
                                                  heroes.get(i).getrectActive().getY() + y > 0 && heroes.get(i).getrectActive().getY() + y < World.mapWidth- imgSizeWidth
                                          )

                                              heroes.get(i).move(x , y);
                                          //if( heroes.get(i).getCanvas().getBoundsInParent().intersects(heroes.get(j).rectActive()){


                                      }
                                      for( int i = 0 ; i < heroes.size() ; i++ ) {
                                          if(heroes.get(i).getHealth()!=0)

                                          for( int j = 0 ; j < heroes.size() ; j++ ) {
                                              if(i!=j && heroes.get(i).getImageView().intersects( heroes.get(j).getImageView().getX(),
                                                      heroes.get(j).getImageView().getY(),
                                                      imgSizeWidth,
                                                      imgSizeHeight))
                                              {
                                                  heroes.get(i).setHealth(heroes.get(i).getHealth()-heroes.get(j).getDamage());
                                                  //System.out.println(heroes.get(i).getHealth());

                                                  heroes.get(i).interactionHeroes(i,j);
                                              }
                                          }
                                      }


//                for( int i = 0 ; i < heroes.size(); i++ ) {
//                    for( int j = 0 ; j < heroes.size() ; j++ ) {
//                        if(i!=j){
//                            if((heroes.get(i).rectActive.getX()<=heroes.get(i).rectActive.getX() || heroes.get(i).rectActive.getX()>=heroes.get(i).rectActive.getX()) &&
//                               (heroes.get(i).rectActive.getX()+imgSizeHeight<=heroes.get(i).rectActive.getX()+imgSizeHeight || heroes.get(i).rectActive.getX()>=heroes.get(i).rectActive.getX()+imgSizeHeight) &&
//                               (heroes.get(i).rectActive.getY()<=heroes.get(i).rectActive.getY() || heroes.get(i).rectActive.getY()>=heroes.get(i).rectActive.getY()) &&
//                               (heroes.get(i).rectActive.getY()+imgSizeWidth<=heroes.get(i).rectActive.getY()+imgSizeWidth || heroes.get(i).rectActive.getY()>=heroes.get(i).rectActive.getY()+imgSizeWidth)
//                            )System.out.println(heroes.get(i).life +" "+ heroes.get(j).life);
//                                heroes.get(i).life-=heroes.get(j).damage;
//                                heroes.get(j).life-=heroes.get(i).damage;
//
//
//                            heroes.get(i).lifeLine.setEndX(heroes.get(i).lifeLineBack.getEndX() -186 + heroes.get(j).life);
//
//                            heroes.get(j).lifeLine.setEndX(heroes.get(i).lifeLineBack.getEndX() -186 + heroes.get(i).life);
//
//
//
//                        }
//                    }
//                }
//                    if( heroes.get(i).getBoundsInParent().intersects(heroes.getBoundsInParent()))
//                    {
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//                        alert.setTitle("УВАГА!!!");
//                        alert.setHeaderText("Зіткнення з айсбергом!");
//
//                        alert.showAndWait();
//                    }
                                  }
                              }
        );



        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
