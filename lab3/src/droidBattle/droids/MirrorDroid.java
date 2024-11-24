package droidBattle.droids;

import java.util.Random;

public class MirrorDroid extends Droid {
    private Random random;

    public MirrorDroid(String name) {
        super(name, 90, 10);
        this.random = new Random();
    }

    // Встановлюємо останнього нападника
    public void setLastAttacker(Droid attacker) {
        this.lastAttacker = attacker;
    }

    // Особлива здібність відбиття атаки
    public boolean counterDamage(int damage) {
        if (lastAttacker == null) {
            System.out.println(name + " не може відбити атаку: нападник невідомий.");
            return false;
        }

        if (random.nextInt(100) < 20) { // 20% шанс відбиття
            System.out.println(name + " відбиває атаку та завдає " + damage + " шкоди нападнику " + lastAttacker.getName() + "!");
            lastAttacker.takeDamage(damage); // Відбиваємо шкоду на нападника
            return true; // Успішне відбиття
        } else {
            System.out.println(name + " не відбив атаку.");
            return false; // Не вдалося відбити
        }
    }

    public void attack(Droid target) {
        System.out.println(name + " завдає " + damage + " шкоди " + target.getName() + "!");

        if (target instanceof StealthDroid) {
            boolean attackLanded = ((StealthDroid) target).dodgeDamage(damage);
            if (attackLanded) {
                target.takeDamage(damage);
            } else {
                System.out.println(target.getName() + " не отримав шкоди.");
            }
        }
    }
}
