import java.util.Random;

public class Character {
    int health;
    private int agility;
    private int dexterity;

    private int randomValueGenerator(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public Character() {
        // Character's atrributes
        this.health = 100;
        this.agility = randomValueGenerator(1, 50);
        this.dexterity = randomValueGenerator(1, 50);
    }
    // Calculate disarm chance
    public boolean disarm() {
        int harmChance = this.dexterity;
        int randomValue = randomValueGenerator(1, 100);
        boolean trapDisarmed = randomValue > harmChance;

        if (!trapDisarmed) {
            this.health -= 10;
        }
        return trapDisarmed;
    }
    // Calculate avoid chance
    public boolean avoid() {
        int chanceOfAvoidance = this.agility;
        int randomValue = randomValueGenerator(1, 100);
        boolean trapAvoided = randomValue > chanceOfAvoidance;

        if (!trapAvoided) {
            this.health -= 10;
        }
        return trapAvoided;
    }

    public char[] generateLevel(String difficulty) {
        char[] level = new char[20];
        int minNumX = 0;
        int maxNumX = 0;
        // Determine number of traps based on difficulty
        switch (difficulty) {
            case "EASY":
                minNumX = 5;
                maxNumX = 10;
                break;
            case "MEDIUM":
                minNumX = 8;
                maxNumX = 13;
                break;
            case "HARD":
                minNumX = 11;
                maxNumX = 16;
                break;
        }

        Random random = new Random();
        int numOfX = random.nextInt(maxNumX - minNumX + 1) + minNumX;
        // Generate level with X (traps), safe spaces(s), and treasure room(t)
        for (int i = 0; i < level.length - 1; i++) {
            if (i == level.length - 2) {
                level[i] = 'T';
            } else if (random.nextBoolean() && numOfX > 0) {  
                level[i] = 'X';
                numOfX--;
            } else {
                if (numOfX > level.length - i) {
                    level[i] = 'X';
                } else {
                    level[i] = 'S';
                }
            }
        }

        return level;
    }
}
