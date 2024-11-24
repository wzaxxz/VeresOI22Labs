package droidBattle.battle;

import droidBattle.droids.Droid;
import droidBattle.file.BattleRecorder;

public class OnevOne extends Battle {
    private Droid droid1;
    private Droid droid2;
    private BattleRecorder recorder;

    public OnevOne(Droid droid1, Droid droid2, String fileName) {
        this.droid1 = droid1;
        this.droid2 = droid2;
        this.recorder = new BattleRecorder(fileName);
    }

    public void start() {
        System.out.println("Бій між " + droid1.getName() + " та " + droid2.getName() + " почався!");

        while (droid1.isAlive() && droid2.isAlive()) {
            droid1.attack(droid2);
            recorder.record(droid1.getName() + " атакує " + droid2.getName() + " і залишає йому " + droid2.getHealth() + " здоров'я.");

            if (!droid2.isAlive()) {
                recorder.record(droid2.getName() + " загинув!");
                System.out.println(droid2.getName() + " загинув! || Переможцем є " + droid1.getName());
                break;
            }

            droid2.attack(droid1);
            recorder.record(droid2.getName() + " атакує " + droid1.getName() + " і залишає йому " + droid1.getHealth() + " здоров'я.");

            if (!droid1.isAlive()) {
                recorder.record(droid1.getName() + " загинув!");
                System.out.println(droid1.getName() + " загинув! || Переможцем є " + droid2.getName());
                break;
            }
        }

        recorder.save();
    }
}
