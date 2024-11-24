package droidBattle.droids;

import java.util.Random;

public class StealthDroid extends Droid {
    private Random random;

    public StealthDroid(String name) {
        super(name, 95, 20);
        this.random = new Random();
    }

    public void attack(Droid target) {
        System.out.println(name + " непомітно підкрадається і завдає " + damage + " шкоди " + target.getName() + "!");
        target.attackedBy(this);

        if (target instanceof MirrorDroid) {
            ((MirrorDroid) target).setLastAttacker(this);
            boolean attackCountered = ((MirrorDroid) target).counterDamage(damage);
            if (!attackCountered) {
                target.takeDamage(damage);
            } else {
                System.out.println(target.getName() + " не отримав шкоди.");
            }
        }
    }

    public boolean dodgeDamage(int damage) {
        if (random.nextInt(100) < 30) { // 30% шанс ухилитися
            System.out.println(name + " ухиляється від атаки!");
            return false;
        } else {
            return true;
        }
    }
}