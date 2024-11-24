package droidBattle.menu;

import java.io.Serializable;

import droidBattle.droids.MirrorDroid;
import droidBattle.droids.*;

public class DroidFactory implements Serializable{

    public static Droid createDroid(int type) {
        switch (type) {
            case 1:
                return new EnergyDroid("Енергетичний Дроїд");
            case 2:
                return new MirrorDroid("Дзеркальний Дроїд");
            case 3:
                return new StealthDroid("Дроїд-невидимка");
            case 4:
                return new VampireDroid("Дроїд-вампір");
            case 5:
                return new ExplosiveDroid("Вибуховий дроїд");
            default:
                System.out.println("Невідомий тип дроїда.");
                return null;
        }
    }
}
