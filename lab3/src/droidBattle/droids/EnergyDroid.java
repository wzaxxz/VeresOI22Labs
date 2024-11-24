package droidBattle.droids;

public class EnergyDroid extends Droid {
    private double energyLevel;

    public EnergyDroid(String name) {
        super(name, 80, 10);
        this.energyLevel = 1; // Початковий рівень енергії
    }

    public void attack(Droid target) {
        int totalDamage = (int)(damage * energyLevel);
        System.out.println(name + " накопичив енергію і завдає " + totalDamage + " шкоди " + target.getName() + "!");

        if (target instanceof MirrorDroid) {
            ((MirrorDroid) target).setLastAttacker(this); // Дізнаємося останнього атакуючого дроїда
            boolean attackCountered = ((MirrorDroid) target).counterDamage(totalDamage);
            if (!attackCountered) {
                target.takeDamage(totalDamage);
                energyLevel *= 1.5;
            } else {
                System.out.println(target.getName() + " не отримав шкоди.");
            }
        } else if (target instanceof StealthDroid) {
            boolean attackLanded = ((StealthDroid) target).dodgeDamage(damage);
            if (attackLanded) {
                target.takeDamage(totalDamage);
                energyLevel *= 1.5;
            } else {
                System.out.println(target.getName() + " не отримав шкоди.");
            }
        } else {
            target.takeDamage(totalDamage);
            energyLevel *= 1.5;
        }
    }
}
