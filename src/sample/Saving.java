package sample;

import sample.Heroes.Recruit;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Saving {
    public static void serializeNow(File file) {
        XMLEncoder encoder;
        try {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file)));
            encoder.writeObject(World.heroes);
            encoder.close();
        } catch (FileNotFoundException e) {
            System.out.println("Помилка відкриття файлу");
        }
    }
    public static void deserializeNow(File file){
        XMLDecoder decoder;
        try {
            decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));

            for (int i = 0 ; i<World.heroes.size();i++){
                Recruit recruit = World.heroes.get(i--);
                Main.deleteHero(recruit);
            }

            for (Recruit recruit:(ArrayList<Recruit>)decoder.readObject()){
                World.addNewHero(recruit.getName(),recruit.getHealth(),recruit.getDamage(),recruit.isActive(),recruit.getX(),recruit.getY(),recruit.getType());
            }
            decoder.close();
        } catch (FileNotFoundException e) {
            System.out.println("Помилка відкриття файлу");
        }
    }
}
