package sample;

import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import sample.Towers.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.Heroes.Knight;
import sample.Heroes.Recruit;
import sample.Heroes.Soldier;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import static javafx.scene.input.KeyCode.HOME;
import static javafx.scene.paint.Color.BEIGE;
import static sample.World.*;

public class Main extends Application  {
    //public final int sceneWidth = 1520;
    //public final int sceneHeight = 880;




    public static final int imgSizeWidth = 91;////|@@@@@@
    public static final int imgSizeHeight = 112;////|@@@@@@

    public static Stage pStage;
    public static Scene input;

    public static Pane pane = new Pane();

    public static ScrollPane getScrollPane() {
        return scrollPane;
    }

    public static void setScrollPane(ScrollPane scrollPane) {
        Main.scrollPane = scrollPane;
    }

    public static ScrollPane scrollPane = new ScrollPane(pane);
    public static Scene scene = new Scene(scrollPane, 1920, 1080);

    public static World WORLD;





    private static double scrollX;
    private static double scrollY;


    private double delta = 5.0;

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;

    }



    public static double getScrollX() {
        return scrollX;
    }

    public static double getScrollY() {
        return scrollY;
    }

    public static String[] heroNames = {"Влад", "Вася", "Віталік", "Валя", "Толя", "Саша", "Топа", "Іван", "Юра", "Афінна",
            "Аврора", "Алла", "Андрій", "Олег", "Богдана", "Вероніка", "Віка", "Стас", "Христя", "Жанна", "Лариса", "Вова",
            "Кирил", "Дуся", "Іра", "Рома", "Маша", "Каріна", "Поліна", "Єва", "Адам", "Джон", "Ярик", "Вітя", "Валера"};




    public static void deleteHero(Recruit recruit) {
        World.getMiniMap().deleteRecruit(recruit);
        pane.getChildren().remove(recruit.getAllPictureOfHero());
        heroes.remove(recruit);
    }

    public void java_util_Arrays() {
        System.out.println("java_util_Arrays:");
        Recruit buffArray[] = new Recruit[heroes.size()];
        for (int i = 0; i < heroes.size(); i++) {
            Recruit buf = heroes.get(i);
            buffArray[i] = buf;
            System.out.println("Recruit{" +
                    "name='" + buffArray[i].getName() + '\'' +
                    ", health=" + buffArray[i].getHealth() +
                    ", damage=" + buffArray[i].getDamage() +
                    ", isActive=" + buffArray[i].isActive() +
                    '}');
        }
        // System.out.println("java_util_Arrays_END");

    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        sample.Main.pStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //World world =new World();

        pane.setMinWidth(4800);
        pane.setMinHeight(2700);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);


        try {
            Recruit.imgRecruit=new Image("/sample/images/imgHero1.png");
            Soldier.imgSoldier=new Image("/sample/images/imgHero2.png");
            Knight.imgKnight = new Image("/sample/images/imgHero3.png");
            Tower1.imgTower1 = new Image("/sample/images/imgTower1.png");
            Tower2.imgTower2 = new Image("/sample/images/imgTower2.png");
            Tower3.imgTower3 = new Image("/sample/images/imgTower3.png");
            Tower4.imgTower4 = new Image("/sample/images/imgTower4.png");
            Tower5.imgTower5 = new Image("/sample/images/imgTower5.png");
            Tower6.imgTower6 = new Image("/sample/images/imgTower6.png");
        } catch (Exception e) {
            System.out.println("Не удалось загрузить изображение!");
        }

        WORLD= new World();



        //group = new Group();
        //Group group2=new Group();


        counterRecruits.setFont(new Font("Times New Roman", 20));
        counterSoldiers.setFont(new Font("Times New Roman", 20));
        counterKnights.setFont(new Font("Times New Roman", 20));

        pane.getChildren().addAll(counterRecruits, counterSoldiers, counterKnights);


        World.addNewHero(heroNames[(int) (rnd.nextInt(heroNames.length))], 150, 1, false, 200, 200, 1);
        World.addNewHero(heroNames[(int) (rnd.nextInt(heroNames.length))], 200, 2, true, 100, 200, 2);



        pane.getChildren().add(World.getMiniMap().getPane());

        scrollPane.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds>
                                        observable, Bounds oldBounds,
                                Bounds bounds) {
                double scrollWidth;
                double scrollHeight;
                Main.scrollX = -1 * (int) bounds.getMinX();
                scrollWidth = -1 * (int) bounds.getMinX() + (int) bounds.getWidth();
                Main.scrollY = -1 * (int) bounds.getMinY();
                scrollHeight = -1 * (int) bounds.getMinY() + bounds.getHeight();

                // постійни здвиг карти при прокручуванні
                World.getMiniMap().getPane().setLayoutX(scrollX + 10);
                World.getMiniMap().getPane().setLayoutY(scrollY + scene.getHeight() - getMiniMap().getPane().getHeight() - 25);
                World.getMiniMap().getMainArea().setLayoutX(scrollX * MiniMap.getSCALE());
                World.getMiniMap().getMainArea().setLayoutY(scrollY * MiniMap.getSCALE());

                //просто показує координати в даний момент
//                System.out.println(" X from " + Main.scrollX + " to " +
//                        scrollWidth + "; Y from " + Main.scrollY + " to " +
//                        scrollHeight);
            }
        });

        primaryStage.setMaximized(true);
        primaryStage.setTitle("Tower Defender");
        primaryStage.getIcons().add(new Image("sample/images/icon.png"));
        primaryStage.setScene(scene);

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
             /*   System.out.printf("Mouse event %f %f scene  %f %f screen %f %f\n",
                        event.getX(), event.getY(),
                        event.getSceneX(), event.getSceneY(),
                        event.getScreenX(), event.getScreenY());*/
                for (Recruit r : heroes)
                    r.tryToActive(event.getX(), event.getY());
            }
        });
        input = new Scene(root, 700, 500, BEIGE);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                  @Override
                                  public void handle(KeyEvent event) {

                                      if (event.isShiftDown()) delta *= 10.0;

                                      switch (event.getCode()) {
                                          case Z:
                                              FileChooser fileChooser = new FileChooser();
                                              fileChooser.setTitle("Виберіть місце для збереження");
                                              fileChooser.getExtensionFilters().addAll(
                                                      new FileChooser.ExtensionFilter("XML-збереження", "*.xml"),
                                                      new FileChooser.ExtensionFilter("Усі файли","*.*"));
                                              File file = fileChooser.showSaveDialog(Main.scene.getWindow());
                                              if (file != null)
                                                  Saving.serializeNow(file);
                                          break;
                                          case X:
                                              fileChooser = new FileChooser();
                                              fileChooser.setTitle("Виберіть файл");
                                              fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML-збереження", "*.xml"),
                                                      new FileChooser.ExtensionFilter("TXT-збереження", "*.txt"),
                                                      new FileChooser.ExtensionFilter("Усі файли", "*.*"));
                                              file = fileChooser.showOpenDialog(Main.scene.getWindow());
                                              if (file != null)
                                                  Saving.deserializeNow(file);
                                          break;
                                          case INSERT:
                                              primaryStage.setScene(input);
                                          case Q:
                                              setDelta(getDelta() + 1);
                                              break;
                                          case E:
                                              setDelta(getDelta() - 1);
                                              break;
                                          case TAB:
                                              java_util_Arrays();
                                              break;
                                          case ENTER:
                                              for (int i = 0; i < heroes.size(); i++) {
                                                  if (!heroes.get(i).isActive())
                                                      heroes.get(i).tryToActive(heroes.get(i).getImageView().getX(), heroes.get(i).getImageView().getY());
                                              }
                                              break;
                                          case ESCAPE:
                                              for (int i = 0; i < heroes.size(); i++) {
                                                  if (heroes.get(i).isActive())
                                                      heroes.get(i).tryToActive(heroes.get(i).getImageView().getX(), heroes.get(i).getImageView().getY());
                                              }

                                              break;
                                          case DELETE:
                                              for (int i = 0; i < heroes.size(); i++) {
                                                  Recruit recruit = heroes.get(i);
                                                  if (recruit.isActive()) {
                                                      Main.deleteHero(recruit);
                                                      i--;
                                                  }
                                              }
                                              break;


                                          case DIGIT1:
                                              World.addNewHero(heroNames[(int) (rnd.nextInt(heroNames.length))],
                                                      rnd.nextInt(100), 1,
                                                      false,
                                                      (double) rnd.nextInt((int) (World.mapWidth - imgSizeWidth - 20)),
                                                      (double) rnd.nextInt((int) (World.mapHeight - imgSizeHeight)),
                                                      1);

                                              break;
                                          case DIGIT2:
                                              World.addNewHero(heroNames[(int) (rnd.nextInt(heroNames.length))],
                                                      rnd.nextInt(150), 2,
                                                      false,
                                                      (double) rnd.nextInt((int) (World.mapWidth - imgSizeWidth - 20)),
                                                      (double) rnd.nextInt((int) (World.mapHeight - imgSizeHeight)),
                                                      2);

                                              break;
                                          case DIGIT3:
                                              World.addNewHero(heroNames[(int) (rnd.nextInt(heroNames.length))],
                                                      rnd.nextInt(200), 3,
                                                      false,
                                                      (double) rnd.nextInt((int) (World.mapWidth - imgSizeWidth - 20)),
                                                      (double) rnd.nextInt((int) (World.mapHeight - imgSizeHeight)),
                                                      3);

                                              break;
                                          case W:
                                              for (Recruit r : heroes) {
                                                  r.up(delta);
                                              }
                                              break;
                                          case S:
                                              for (Recruit r : heroes) {
                                                  r.down(delta);
                                              }
                                              break;
                                          case A:
                                              for (Recruit r : heroes) {
                                                  r.left(delta);
                                              }
                                              break;
                                          case D:
                                              for (Recruit r : heroes) {
                                                  r.right(delta);
                                              }
                                              break;
                                      }


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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                int counter1=0;
                int counter2=0;
                int counter3=0;
                for (Recruit r : heroes) {
                    if (r instanceof Knight) World.setCounter3(++counter3);
                    else if (r instanceof Soldier)World.setCounter2(++counter2);
                    else if (r instanceof Recruit)World.setCounter1(++counter1);


                    /*if(r.isActive()==false){
                        r.setActive(true);
                        switch (rnd.nextInt(100)){
                            case 10:
                                r.up(delta);
                                break;
                            case 20:
                                r.down(delta);
                                break;
                            case 30:
                                r.left(delta);
                                break;
                            case 40:
                                r.right(delta);
                                break;
                            case 15:
                                r.up(delta);
                                r.right(delta);
                                break;
                            case 25:
                                r.down(delta);
                                r.right(delta);
                                break;
                            case 35:
                                r.up(delta);
                                r.left(delta);
                                break;
                            case 45:
                                r.down(delta);
                                r.left(delta);
                                break;
                        }
                        r.setActive(false);
                    }*/


                    int counterOperateTower1 = 0;
                    int counterOperateTower2 = 0;
                    int counterOperateTower3 = 0;
                    int counterOperateTower4 = 0;
                    int counterOperateTower5 = 0;
                    int counterOperateTower6 = 0;
                    for (int i = 0; i < heroes.size(); i++) {
                        if (heroes.get(i).getImageView().intersects(
                                Tower1.getImageView().getX(),
                                Tower1.getImageView().getY(),
                                Tower1.imageView.getImage().getWidth(),
                                Tower1.imageView.getImage().getHeight()
                        )) {
                            heroes.get(i).interactionTower1(i);
                            ++counterOperateTower1;
                        }
                        if (heroes.get(i).getImageView().intersects(
                                Tower2.getImageView().getX(),
                                Tower2.getImageView().getY(),
                                Tower2.imageView.getImage().getWidth(),
                                Tower2.imageView.getImage().getHeight()
                        )) {
                            heroes.get(i).interactionTower2(i);
                            ++counterOperateTower2;
                        }
                        if (heroes.get(i).getImageView().intersects(
                                Tower3.getImageView().getX(),
                                Tower3.getImageView().getY(),
                                Tower3.imageView.getImage().getWidth(),
                                Tower3.imageView.getImage().getHeight()
                        )) {
                            heroes.get(i).interactionTower3(i);
                            ++counterOperateTower3;
                        }
                        if (heroes.get(i).getImageView().intersects(
                                Tower4.getImageView().getX(),
                                Tower4.getImageView().getY(),
                                Tower4.imageView.getImage().getWidth(),
                                Tower4.imageView.getImage().getHeight()
                        )) {
                            heroes.get(i).interactionTower4(i);
                            ++counterOperateTower4;
                        }
                        if (heroes.get(i).getImageView().intersects(
                                Tower5.getImageView().getX(),
                                Tower5.getImageView().getY(),
                                Tower5.imageView.getImage().getWidth(),
                                Tower5.imageView.getImage().getHeight()
                        )) {
                            heroes.get(i).interactionTower5(i);
                            ++counterOperateTower5;
                        }
                        if (heroes.get(i).getImageView().intersects(
                                Tower6.getImageView().getX(),
                                Tower6.getImageView().getY(),
                                Tower6.imageView.getImage().getWidth(),
                                Tower6.imageView.getImage().getHeight()
                        )) {
                            heroes.get(i).interactionTower6(i);
                            ++counterOperateTower6;
                        }

                        for (int j = 0; j < heroes.size(); j++) {
                            if (i != j && heroes.get(i).getImageView().intersects(heroes.get(j).getImageView().getX(),
                                    heroes.get(j).getImageView().getY(),
                                    imgSizeWidth,
                                    imgSizeHeight)) {
                                heroes.get(i).setHealth(heroes.get(i).getHealth() - heroes.get(j).getDamage());
                                //System.out.println(heroes.get(i).getHealth());

                                heroes.get(i).interactionHeroes(i, j);
                            }
                        }
                        Tower1.setOperate(counterOperateTower1);
                        Tower2.setOperate(counterOperateTower2);
                        Tower3.setOperate(counterOperateTower3);
                        Tower4.setOperate(counterOperateTower4);
                        Tower5.setOperate(counterOperateTower5);
                        Tower6.setOperate(counterOperateTower6);
                    }
                    r.freeRun(delta);
                    r.move();
                }


                String text = "Кількість рекрутів: " + counter1;
                counterRecruits.setText(text);
                counter1 = 0;

                text = "Кількість солдатів: " + counter2;
                counterSoldiers.setText(text);
                counter2 = 0;

                text = "Кількість лицарів: " + counter3;
                counterKnights.setText(text);
                counter3 = 0;

                counterRecruits.setLayoutX(5);
                counterRecruits.setLayoutY(5);
                counterSoldiers.setLayoutX(5);
                counterSoldiers.setLayoutY(25);
                counterKnights.setLayoutX(5);
                counterKnights.setLayoutY(45);

                World.getMiniMap().updateMap();
            }
        };
        timer.start();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
