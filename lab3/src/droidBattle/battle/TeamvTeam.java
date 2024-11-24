package droidBattle.battle;

import droidBattle.droids.Droid;
import droidBattle.file.BattleRecorder;
import java.util.List;

public class TeamvTeam extends Battle {
    private List<Droid> team1;
    private List<Droid> team2;
    private BattleRecorder recorder;

    public TeamvTeam(List<Droid> team1, List<Droid> team2, String fileName) {
        this.team1 = team1;
        this.team2 = team2;
        this.recorder = new BattleRecorder(fileName);
    }

    public void start() {
        System.out.println("Починається командний бій!");
        recorder.record("Команда 1: " + team1 + " проти Команди 2: " + team2);

        int index1 = 0;
        int index2 = 0;

        while (team1HasAliveDroids() && team2HasAliveDroids()) {
            Droid droid1 = team1.get(index1);
            Droid droid2 = team2.get(index2);

            if (droid1.isAlive() && droid2.isAlive()) {
                droid1.attack(droid2);
                recorder.record(droid1.getName() + " атакує " + droid2.getName() + " і залишає йому " + droid2.getHealth() + " здоров'я.");

                if (!droid2.isAlive()) {
                    recorder.record(droid2.getName() + " загинув!");
                } else {
                    droid2.attack(droid1);
                    recorder.record(droid2.getName() + " атакує " + droid1.getName() + " і залишає йому " + droid1.getHealth() + " здоров'я.");

                    if (!droid1.isAlive()) {
                        recorder.record(droid1.getName() + " загинув!");
                    }
                }
            }

            if (!droid2.isAlive() && index2 < team2.size() - 1) {
                index2++;
            }

            if (!droid1.isAlive() && index1 < team1.size() - 1) {
                index1++;
            }
        }

        Droid winner;
        if (team1HasAliveDroids()) {
            winner = team1.get(index1); // Перший живий дроїд команди 1
            System.out.println("Команда 1 перемогла!");
        } else {
            winner = team2.get(index2); // Перший живий дроїд команди 2
            System.out.println("Команда 2 перемогла!");
        }

        recorder.record("Переможець: " + winner.getName());
        recorder.save();
    }

    private boolean team1HasAliveDroids() {
        return team1.stream().anyMatch(Droid::isAlive);
    }

    private boolean team2HasAliveDroids() {
        return team2.stream().anyMatch(Droid::isAlive);
    }
}
