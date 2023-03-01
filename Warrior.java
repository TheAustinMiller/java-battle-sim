/*
 * Course: CS1011-011
 * Fall 2020
 * Lab 7 - Battle Simulator 3000 - Warrior
 * Name: Austin Miller
 * Created: 10/20/2020
 */
/**
 * This method creates a warrior and set health points
 */
public class Warrior {
    private int hitPoints;
    private static final int TWENTY_SIDED_DIE = 20;
    private static final int TEN_SIDED_DIE = 10;
    private static final int EIGHT_SIDED_DIE = 8;
    private static final int FOUR_SIDED_DIE = 4;
    private Die d20 = new Die(TWENTY_SIDED_DIE);
    private Die d10 = new Die(TEN_SIDED_DIE);
    private Die d8 = new Die(EIGHT_SIDED_DIE);
    private Die d4 = new Die(FOUR_SIDED_DIE);
    private static final int RUN_SIX_TIMES = 6;
    private static final int FORTY_PERCENT_CHANCE = 12;
    private static final int SIXTY_PERCENT_CHANCE = 8;

    /**
     * Sets health points to each warrior
     */
    public Warrior(){
        this.hitPoints = setInitialHitPoints();
    }

    public int getHitPoints(){
        return hitPoints;
    }

    /**
     * Removes health for opponent attack
     * @param damage - attack from enemy
     */
    public void takeDamage(int damage){
        hitPoints -= damage;
    }

    /**
     * Determines attack strength from warrior
     * @param type - type of attack chosen by user
     * @return damage - damage done
     */
    public int attack(int type){
        int damage = 0;
        d20.roll();
        int value = d20.getCurrentValue();
        if (type == 1){
            if (value >= FORTY_PERCENT_CHANCE){
                for(int lcv = 0; lcv < 2; lcv++){
                    d8.roll();
                    damage += d8.getCurrentValue();
                }
                System.out.println("YOU SWUNG YOUR SWORD AT THE BEAST " +
                        "AND DID " + damage + " DAMAGE!");
            } else{
                System.out.println("YOU SWUNG YOUR SWORD AND " +
                        "MISSED WILDLY DOING " + damage + " DAMAGE!");
            }
        } else if(type == 2){
            if (value >= SIXTY_PERCENT_CHANCE){
                d4.roll();
                damage = d4.getCurrentValue();
                System.out.println("YOU BONKED YOUR SHIELD ON THE " +
                        "MUGWUMP AND DID " + damage + " DAMAGE!");
            } else{
                System.out.println("THE MUGWUMP DIDN'T EVEN FLINCH AT YOUR " +
                        "SHIELD ATTACK... YOU DID " + damage + " DAMAGE!");
            }
        } else{
            System.out.println("WARRIOR MISUNDERSTANDS DIRECTIONS AND SKIPS TURN");
        }
        return damage;
    }

    private int setInitialHitPoints(){
        int totalHP = 0;
        for (int lcv = 0; lcv < RUN_SIX_TIMES; lcv++){
            d10.roll();
            totalHP += d10.getCurrentValue();
        }
        return totalHP;
    }
}
