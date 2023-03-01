/*
 * Course: CS1011-011
 * Fall 2020
 * Lab 7 - Battle Simulator 3000 - Driver
 * Name: Austin Miller
 * Created: 10/20/2020
 */
import java.util.Scanner;
/**
 * BattleSim Driver for Battle Simulator 3000
 */
public class Main {
    /**
     * Ten-sided die to be used for checking initiative by all classes
     */
    public static final Die INITIATIVE_DIE = new Die(10);

    public static void main(String[] args) {
        // Local variables
        Scanner in = new Scanner(System.in);

        // Include any variable that will need to be accessed throughout the program here


        // sentinel value for the game loop
        boolean play = true;
        // String used to determine the winner of the epic battle
        String victor;
        // game loop

        do {
            // print the introduction and rules
            intro();
            System.out.println("Enter 'begin' to start battle!");
            String b = in.next();
            while(!b.equalsIgnoreCase("begin")){
                b = in.next();
            }
            // initialize game
            Mugwump mugwump = new Mugwump();
            Warrior warrior = new Warrior();
            // Initialize the Warrior and Mugwump classes, set the current victor to "none"
            victor = "none";

            // while neither combatant has lost all of their hit points, report status and battle!
            while (victor.equalsIgnoreCase("none")) {
                System.out.println();
                report(warrior, mugwump);
                System.out.println();
                victor = battle(warrior, mugwump, in);
            }
            // declare the winner
            victory(victor);

            // ask to play again
            play = playAgain(in);
        } while(play);
        // Thank the user for playing your game
        System.out.println("Thanks for playing!");
    }

    /**
     * This method displays the introduction to the game and gives a description of the rules.
     */
    private static void intro() {
        // Write a suitable introduction to your game
        System.out.println("WELCOME TO BATTLE SIMULATOR 3000\n\n");
        System.out.println("You will be the clumsy, but faithful warrior. You are \n" +
                "armed with your trusty sword and shield of light. The sword is much\n" +
                "harder hitting, but less likely to attack. While your shield is more\n" +
                "likely to hit, but does less damage. Try your best to defeat the evil\n" +
                "mugwump! Good luck!\n");
    }

    /**
     * This method handles the battle logic for the game.
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     * @param in user input
     * @return The name of the victor, or "none", if the battle is still raging on
     */
    private static String battle(Warrior warrior, Mugwump mugwump, Scanner in) {
        // determine who attacks first (Roll! For! Initiative!) and store the result
        int whoIsFirst = initiative();

        if (whoIsFirst == 1){
            int type = attackChoice(in);
            int warriorDamage = warrior.attack(type);
            mugwump.takeDamage(warriorDamage);
            if (mugwump.getHitPoints() <= 0){
                return "warrior";
            }
            System.out.println("NOW THE MUGWUMP WILL ATTACK...");
            int damage = mugwump.attack();
            if (damage < 0){
                System.out.println("THE MUGWUMP HEALED ITSELF AN EXTRA " + -damage+ " HIT POINTS");
                mugwump.takeDamage(damage); // Mugwump healing, not being damaged
            } else{
                System.out.println("THE MUGWUMP ATTACKED AND DID " + damage + " DAMAGE!");
                warrior.takeDamage(damage);
            }
            if (warrior.getHitPoints() <= 0){
                return "mugwump";
            }
        } else{
            int damage = mugwump.attack();
            if (damage < 0){
                System.out.println("THE MUGWUMP HEALED ITSELF AN EXTRA " + -damage+ " HIT POINTS");
                mugwump.takeDamage(damage); // Mugwump healing, not being damaged
            } else{
                System.out.println("THE MUGWUMP ATTACKED AND DID " + damage + " DAMAGE!");
                warrior.takeDamage(damage);
            }
            if (warrior.getHitPoints() <= 0){
                return "mugwump";
            }
            System.out.println("NOW YOU WILL ATTACK...");
            System.out.println();
            int type = attackChoice(in);
            int warriorDamage = warrior.attack(type);
            mugwump.takeDamage(warriorDamage);
            if (mugwump.getHitPoints() <= 0){
                return "warrior";
            }
        }
        System.out.println("END OF THE ROUND");
        return "none";
    }

    /**
     * This method reports the status of the combatants
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     */
    private static void report(Warrior warrior, Mugwump mugwump) {
        System.out.println("Your health: " + warrior.getHitPoints());
        System.out.println("Mugwump health: " + mugwump.getHitPoints());
    }

    /**
     * This method asks the user what attack type they want to use and returns the result
     * @param in user unput
     * @return 1 for sword, 2 for shield
     */
    private static int attackChoice(Scanner in) {
        System.out.println("Enter (1) to attack with your trusty sword!");
        System.out.println("Enter (2) to attack with your shield of light!");
        return in.nextInt();
    }

    /**
     * Determines which combatant attacks first and returns the result. In the case of a tie,
     * re-roll.
     * @return 1 if the warrior goes first, 2 if the mugwump goes first
     */
    private static int initiative() {
        int ans;
        int warriorRoll = 0;
        int mugwumpRoll = 0;
        while(warriorRoll == mugwumpRoll){
            INITIATIVE_DIE.roll();
            warriorRoll = INITIATIVE_DIE.getCurrentValue();
            INITIATIVE_DIE.roll();
            mugwumpRoll = INITIATIVE_DIE.getCurrentValue();
        }
        if (Math.max(warriorRoll, mugwumpRoll) == warriorRoll){
            ans = 1;
            System.out.println("After rolling for initiative," +
                    " you are attacking first this round!\n");
        } else{
            ans = 0;
            System.out.println("After rolling for initiative, " +
                    "the mugwump is attacking first this round.\n");
        }
        return ans;
    }

    /**
     * This method declares the victor of the epic battle
     * @param victor the name of the victor of the epic battle
     */
    private static void victory(String victor) {
        if (victor.equalsIgnoreCase("mugwump")){
            System.out.println();
            System.out.println("You have been defeated by the mugwump" +
                    " and he has promptly burned down your village. GG bro.");
            System.out.println();
        }
        if (victor.equalsIgnoreCase("warrior")){
            System.out.println();
            System.out.println("You have defeated the mugwump. You are a legend. GG bro.");
            System.out.println();
        }
    }

    /**
     * This method asks the user if they would like to play again
     * @param in Scanner
     * @return true if yes, false otherwise
     */
    private static boolean playAgain(Scanner in) {
        boolean replay = false;
        System.out.println("Do you want to battle again? (1) for yes (2) for no");
        int play = in.nextInt();
        while (play > 2 && play < 1){
            System.out.println("Please enter a valid option.");
            play = in.nextInt();
        }
        if (play == 1){
            replay = true;
        }
        if (play == 2){
            replay = false;
        }
        return replay;
    }
}
