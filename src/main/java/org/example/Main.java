package org.example;

import netscape.javascript.JSObject;
import org.example.math.dpr;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
        Atk Modifier | Damage die (1d3 = 3) | Savingthrow / AC | Iterations | Halfdamage? | ProfBonus added to damage| number of Damage Dice | Number of Sources / opponents hit|
        total hp| Saving Throw | Advantage
         */
        //skeleton
//        new dpr().dpsOut(4,6,13, 1000000, false, 2, 2, 2, 13);
        //fireball
//        new dpr().dpsOut(3,6,15, 1000000, true, 0, 8, 1, 35);
        //Generic Testing
        new dpr().dpsOut(11,12,19, 1000000, false, 6, 1, 4, 228, false, true);

    }
}