package org.example.math;

import java.io.IOException;
import java.util.ArrayList;
import org.example.chart.charting;
public class dpr {

    /*
    DPR = Damage per round
     */

    public double dprOut(int modifier, int damage, int ac) {
        int d20 = 20;
        double average = 10;

        /*
        Math - calculate percentage to hit
         */
        double newAc = ac-average;
        double curr = (modifier+average)-newAc;
        System.out.println(curr);
        curr = curr/d20;
        System.out.println(curr*100+"%");

        return 0.0;
    }

    public double dpsOut(int modifier, int damage, int ac, int iterations, boolean halfdmg, int profBonus, int damageDie, int numSources, int hp, boolean savingThrow, boolean adv) {
        //ini
        double[] totals = new double[iterations];
        ArrayList<Integer> rolls = new ArrayList<>(iterations);
        double killChance = 0;
        double avg = 0.0;
        for (int i = 0; i < iterations; i++) {
            int d20 = randomizer(20);

            //keep track of the rolls
            rolls.add(d20);

            //calculate if a hit/miss
            boolean hit = hitCalc(d20,ac,modifier, savingThrow, adv);

            //instaniate && calculate the initial hit done
            int damageDone = 0;
            for (int j = 0; j < numSources; j++) {
                damageDone += damageCalc(halfdmg, hit, d20, damageDie, damage, profBonus);
            }
            if(damageDone >= hp) killChance+=1;
            
            //add up totals and add to average for the end
            totals[i] = (damageDone);
            avg += damageDone;
        }
        printer(rolls,totals ,avg);

        //print it out
        try {
            new charting().charter(totals, (avg/totals.length),ac,modifier, (killChance/totals.length)*100);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return avg/totals.length;

    }

    private boolean hitCalc(int d20, int ac, int modifier, boolean spell, boolean adv){
        boolean hit = false;
        if(spell) {hit = !((d20) >= (ac - modifier)) ;}
        else {
            hit = (d20+modifier) >= ac;
            if(adv && !hit){
                hit = (randomizer(20)+modifier) >= ac;
            }
        }
        return hit;
    }
    public int damageCalc(boolean halfdmg, boolean hit, int d20, int damageDie, int damage, int profBonus){
        int damageDone = 0;
        if(hit && d20 == 20 && !halfdmg){
            for (int j = 0; j < damageDie; j++) {
                damageDone+= randomizer(damage) + randomizer(damage) + profBonus;
            }
        }
        else if(hit){
            for (int j = 0; j < damageDie; j++) {
                damageDone += randomizer(damage)+profBonus;
            }
        } else if(halfdmg){
            for (int j = 0; j < damageDie; j++) {
                damageDone+= Math.ceil(Math.ceil(randomizer(damage)+profBonus)/2);
            }
        }
        return damageDone;
    }

    public int randomizer(int bound){
        int random = (int) Math.floor(Math.random() * bound) +1;
        return (random);
    }

    public void printer(ArrayList<Integer> rolls, double[] totals, double avg){
        System.out.println(avg/totals.length);
    }

}
