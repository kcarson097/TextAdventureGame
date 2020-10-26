package Game;
import java.util.Random;
import java.util.Scanner;

/**
* @author Kyle
* Text based adventure game
* an array of different enemeis is stored
* user is up against an enemy and can fight, run or heal
* user starts with 2 healing potions and there is a 50 % chance of an enemey dropping a potion when it dies
* enemy health and attack damage increases after each level
* tracks number of enemies killed
* tracks number of potions left
* tracks health and enemy health
 */
public class Game {

	public static void main(String[] args) {
		
		//initialise Scanner and Random classes
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		
		//enemies stats
		String[] enemies = {"Skeleton","Dragon","Warrior","Assasin","Zombie"};
		int maxEnemyHealth = 100; //max health an enemy can have
		int maxEnemyDamage = 40; //max damage enemy can inflict
		
		//players stats
		int healthPotions = 2;
		int remainingHealth = 100;
		int maxAttackDamage = 50;
		int healthPotionHealAmount = 30;
		int healthPotionDrop = 50; //percentage chance of enemy dropping potion upon death
		int score = 0; //amount of kills
		
		boolean running = true;
		
		System.out.println("WELCOME TO MY FIRST TEXT ADVENTURE GAME ! \n ");
		
		//game code
		while(running) {
				
				//generate enemy	
				int enemyIndex = random.nextInt(enemies.length);
				String enemy = enemies[enemyIndex];
				int enemyHealth = random.nextInt(maxEnemyHealth) + 1;
			
				System.out.println("A WILD " + enemy.toUpperCase() + " HAS APPEARED ");
				System.out.println("WHAT WILL YOU DO...............");
				System.out.println("------------------------------------------");
				
				while(enemyHealth > 0) { //while enemy is still alive
					System.out.println("-> HEALTH REMAINING : " + remainingHealth + "\n-> ENEMY HEALTH : " + enemyHealth);
					System.out.println("-> HEALTH POTIONS : " + healthPotions + "\n-> SCORE : " + score);
					System.out.println("------------------------------------------");
					System.out.println("CHOOSE YOUR MOVE : ");
					System.out.println("\t PRESS 1 TO ATTACK");
					System.out.println("\t PRESS 2 TO USE A HEALTH POTION");
					System.out.println("\t PRESS 3 TO FLEE LIKE A COWARD ");
					System.out.println("------------------------------------------");
					
					int move = scanner.nextInt();
					
					switch (move) {
					case 1:
						//generate random damage inflicted by enemy and user
						int userAttackDamage = random.nextInt(maxAttackDamage) + 1;
						int enemyAttackDamage = random.nextInt(maxEnemyDamage) + 1;
						//adjust enemy and user health
						enemyHealth-=userAttackDamage;
						remainingHealth-=enemyAttackDamage;
						//output outcome to user
						System.out.println("YOU ATTACKED ! " + userAttackDamage + " DAMAGE WAS DEALT ");
						System.out.println(enemy.toUpperCase() + "S COUNTER ATTACK DEALT " + enemyAttackDamage + " DAMAGE !");
						System.out.println("------------------------------------------");
						break;
					case 2:
						System.out.println("DRINKING POTION...........");
						//increment user health
						remainingHealth+=healthPotionHealAmount;
						System.out.println("REMAINING HEALTH : " + remainingHealth );
						break;
					case 3:
						//user runs - game over
						System.out.println("YOU FLED LIKE A COWARD ");
						System.out.println("SHAME SHAME SHAME");
						enemyHealth = 0; //breaks out of inner loop
						running = false;
						break;
					default:
						System.out.println("INVALID INPUT - TRY AGAIN !");
						}
					
					if(remainingHealth < 1) {
						System.out.println("YOU RETREAT FROM THE BATTLEFIELD, BROKEN AND WEAK FROM BATTLE");
						System.out.println("SCORE : " + score);
						System.out.println("GAME OVER");
						running = false;
						break;
					}
				}//enemy has been defeated
				
				System.out.println("------------------------------------------");
				System.out.println(enemy + " WAS DEFEATED");
				score++;
				
				if(random.nextInt(100) < healthPotionDrop) {
					healthPotions++;
					System.out.println("THEY DROPPED A HEALTH POTION HURRAY !!");
					System.out.println("------------------------------------------");
				}
				scanner.close();
			}
		
		}
}
