/*
 * Course: CS1011-011
 * Fall 2020
 * Lab 7 - Battle Simulator 3000 - Mugwump
 * Name: Austin Miller
 * Created: 10/20/2020
 */
/**
 * Creates a Mugwump to battle our hero
 */
public class Mugwump {
    private static final int HUNDRED_SIDED_DIE = 100;
    private static final int TWENTY_SIDED_DIE = 20;
    private static final int TEN_SIDED_DIE = 10;
    private static final int SIX_SIDED_DIE = 6;
    private static final int FORTY_FIVE_PERCENT_CHANCE = 12;
    private static final int TWENTY_FIVE_PERCENT_CHANCE = 16;
    private static final int RUN_TEN_TIMES = 10;
    private static final int SEVENTY_FIVE_INTERVAL = 75;
    private static final int NINETY_INTERVAL = 90;
    private int hitPoints;
    private int maxHitPoints;
    private Die d100 = new Die(HUNDRED_SIDED_DIE);
    private Die d20 = new Die(TWENTY_SIDED_DIE);
    private Die d10 = new Die(TEN_SIDED_DIE);
    private Die d6 = new Die(SIX_SIDED_DIE);

    /**
     * Creates a Mugwump with hp and attack plans
     */
    public Mugwump(){
        this.hitPoints = setInitialHitPoints();
        this.maxHitPoints = hitPoints;
    }

    public int getHitPoints(){
        return hitPoints;
    }
    /**
     * Subtracts the damage taken from warrior
     * @param damage - damage from warrior
     */
    public void takeDamage(int damage){
        if (damage < 0){
            hitPoints += Math.abs(damage);
            if (hitPoints > maxHitPoints){
                hitPoints = maxHitPoints;
            }
        } else{
            hitPoints -= damage;
        }
    }

    /**
     * This method handles the attack logic
     * @return the amount of damage an attack has caused, 0 if the attack misses or
     *         a negative amount of damage if the Mugwump heals itself
     */
    public int attack() {
        int attackType = ai();
        if(attackType == 3){
            d6.roll();
            return -d6.getCurrentValue();
        }
        d20.roll();
        int damage = 0;
        if (attackType == 1){
            if (d20.getCurrentValue() >= FORTY_FIVE_PERCENT_CHANCE){
                for (int lcv = 0; lcv < 2; lcv++){
                    d6.roll();
                    damage += d6.getCurrentValue();
                }
            } else{
                return 0;
            }
        }
        if (attackType == 2){
            if (d20.getCurrentValue() >= TWENTY_FIVE_PERCENT_CHANCE){
                for (int lcv = 0; lcv < 3; lcv++){
                    d6.roll();
                    damage += d6.getCurrentValue();
                }
            } else{
                return 0;
            }
        }
        return damage;
    }

    private int setInitialHitPoints(){
        int totalHP = 0;
        for (int lcv = 0; lcv < RUN_TEN_TIMES; lcv++){
            d10.roll();
            totalHP += d10.getCurrentValue();
        }
        return totalHP;
    }

    /**
     * This method determines what action the Mugwump performs
     * @return 1 for a Claw attack, 2 for a Bite, and 3 if the Mugwump licks its wounds
     */
    private int ai() {
        d100.roll();
        int value = d100.getCurrentValue();
        int ans;
        if(value <= SEVENTY_FIVE_INTERVAL){
            ans = 1;
        } else if(value > SEVENTY_FIVE_INTERVAL && value <=NINETY_INTERVAL){
            ans = 2;
        } else{
            ans = 3;
        }
        return ans;
    }
}
