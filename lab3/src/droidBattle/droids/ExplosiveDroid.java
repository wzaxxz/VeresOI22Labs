package droidBattle.droids;

public class ExplosiveDroid extends Droid {

    private int explosionDamage;

    public ExplosiveDroid(String name) {
        super(name, 120, 15);
        this.explosionDamage = 40; // Вибухова додаткова шкода
    }

    public void attack(Droid target) {
        System.out.println(name + " кидає бомбу і завдає " + damage + " шкоди!");

        if (target instanceof MirrorDroid) {
            ((MirrorDroid) target).setLastAttacker(this); // Останній атакуючий дроїд
            boolean attackCountered = ((MirrorDroid) target).counterDamage(damage);
            if (!attackCountered) {
                target.takeDamage(damage);
            } else {
                System.out.println(target.getName() + " не отримав шкоди.");
            }
        }
        if (target instanceof StealthDroid) {
            boolean attackLanded = ((StealthDroid) target).dodgeDamage(damage);
            if (attackLanded) {
                target.takeDamage(damage);
            } else {
                System.out.println(target.getName() + " не отримав шкоди.");
            }
        }

        if (target instanceof EnergyDroid) {
            target.takeDamage(damage);
        }

        if (target instanceof VampireDroid) {
            target.takeDamage(damage);
        }

        if (!target.isAlive()) {
            System.out.println(name + " вибухає, завдаючи додатково " + explosionDamage + " шкоди!");
            target.takeDamage(explosionDamage);
        }
    }
}
