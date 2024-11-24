package droidBattle.droids;

public class Droid {
    protected String name;
    protected int health;
    protected int damage;
    protected Droid lastAttacker; // Дроїд, що востаннє атакував

    public Droid(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }

    public void attackedBy(Droid attacker) {
        this.lastAttacker = attacker;
        System.out.println(name + " був атакований " + attacker.getName());
    }

    public void attack(Droid target) {
        // Встановлюємо, що поточний дроїд є атакуючим для цілі
        target.attackedBy(this);
        target.takeDamage(damage);  // Завдаємо шкоду
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println(name + " отримує " + damage + " шкоди і вмирає.");
        } else {
            System.out.println(name + " отримує " + damage + " шкоди. Залишок здоров'я: " + health);
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }
}
