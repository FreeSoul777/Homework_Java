package laba_2.Logic;

import laba_2.Structure.Attacker;
import laba_2.Structure.Damageable;

public class GameLogic {
    static void fight(Attacker attacker, Damageable target) {
        attacker.attack(target);
    }
}
