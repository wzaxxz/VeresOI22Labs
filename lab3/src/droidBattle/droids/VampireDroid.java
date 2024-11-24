package droidBattle.droids;

public class VampireDroid extends Droid {

    public VampireDroid(String name) {
        super(name, 90, 15);
    }

    public void attack(Droid target) {
        System.out.println(name + " кусає і завдає " + damage + " шкоди!");

        if (target instanceof MirrorDroid) {
            ((MirrorDroid) target).setLastAttacker(this);
            boolean attackCountered = ((MirrorDroid) target).counterDamage(damage);
            if (!attackCountered) {
                target.takeDamage(damage);
                this.health += damage / 3;
                System.out.println(name + " випив крові та відновив " + damage / 3 + " здоров'я");
            } else {
                System.out.println(target.getName() + " не отримав шкоди.");
            }
        }
        if (target instanceof StealthDroid) {
            boolean attackLanded = ((StealthDroid) target).dodgeDamage(damage);
            if (attackLanded) {
                target.takeDamage(damage);
                this.health += damage / 3;
                System.out.println(name + " випив крові та відновив " + damage / 3 + " здоров'я");
            } else {
                System.out.println(target.getName() + " не отримав шкоди.");
            }
        }

        if (target instanceof EnergyDroid) {
            target.takeDamage(damage);
            this.health += damage / 3;
            System.out.println(name + " випив крові та відновив " + damage / 3 + " здоров'я");
        }

        if (target instanceof ExplosiveDroid) {
            target.takeDamage(damage);
            this.health += damage / 3;
            System.out.println(name + " випив крові та відновив " + damage / 3 + " здоров'я");
        }
    }
}
