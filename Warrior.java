import java.util.ArrayList;
import java.util.Random;
//classes for each character type
class Warrior extends Character{
    Warrior(String name,int level, int experience){
        super(name);
        super.currentLife = 100;
        super.maxLife = 100;
        super.currentMana = 50;
        super.maxMana = 50;
        super.earthProtection = false;
        super.iceProtection = false;
        super.fireProtection = true;
        super.strength = 5;
        super.charisma = 2;
        super.dexterity = 3;
        super.potionInventory.maxSpace = 15;
        super.level = level;
        super.experience = experience;
    }
    void receiveDamage(int damage) {
        currentLife = currentLife - (3 * damage) / level * (charisma + dexterity -1);
    }
    int getDamage() {
        Random rand = new Random();
        int damage;
        int luck = rand.nextInt(2);
        if(luck == 1){
            damage = strength * 2 * level;
        }
        else{
            damage = strength * level;
        }
        return damage;
    }
    void plusExperience(){
        experience = experience +20;
        if(experience >= 100){
            level++;
            experience = 0;
        }
    }
    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitor(this);
    }
}
class Mage extends Character{
    Mage(String name,int level, int experience){
        super(name);
        super.currentLife = 100;
        super.maxLife = 100;
        super.currentMana = 50;
        super.maxMana = 50;
        super.earthProtection = false;
        super.iceProtection = true;
        super.fireProtection = false;
        super.strength = 2;
        super.charisma = 6;
        super.dexterity = 3;
        super.potionInventory.maxSpace = 5;
        super.level = level;
        super.experience = experience;
    }
    void receiveDamage(int damage) {
        currentLife = currentLife - (3 * damage) / level * (strength + dexterity -1);
    }
    int getDamage() {
        int damage;
        Random rand2 = new Random();
        int luck = rand2.nextInt(2);
        if(luck == 1){
            damage = charisma * 2 * level;
        }
        else{
            damage = charisma * level;
        }
        return damage;
    }
    void plusExperience(){
        experience = experience +20;
        if(experience >= 100){
            level++;
            experience = 0;
        }
    }
    public void acceptVisitor(Visitor visitor) {
        visitor.visitor(this);
    }
}
class Rogue extends Character{
    Rogue(String name,int level, int experience){
        super(name);
        super.currentLife = 100;
        super.maxLife = 100;
        super.currentMana = 50;
        super.maxMana = 50;
        super.earthProtection = true;
        super.iceProtection = false;
        super.fireProtection = false;
        super.strength = 2;
        super.charisma = 3;
        super.dexterity = 7;
        super.potionInventory.maxSpace = 10;
        super.level = level;
        super.experience = experience;
    }
    void receiveDamage(int damage) {
        currentLife = currentLife - (3 * damage) / level * (charisma + dexterity -1);
    }
    int getDamage() {
        int damage;
        Random rand3 = new Random();
        int luck = rand3.nextInt(2);
        if(luck == 1){
            damage = dexterity * dexterity * 2 * level;
        }
        else{
            damage = dexterity * dexterity * level;
        }
        return damage;
    }
    void plusExperience(){
        experience = experience +20;
        if(experience >= 100){
            level++;
            experience = 0;
        }
    }
    public void acceptVisitor(Visitor visitor) {
        visitor.visitor(this);
    }
}
class Enemy extends Entity implements CellElement{
    Enemy(){
        super.earthProtection = false;
        super.iceProtection = true;
        super.fireProtection = false;
        super.currentLife = (int) ((Math.random() * (30 - 10)) + 10);
        super.maxLife = 30;
        super.currentMana = (int) ((Math.random() * (30 - 10)) + 10);
        super.maxMana = 30;
        String[] abilities2 = {"Fire","Earth","Ice"};
        java.util.Random random = new java.util.Random();
        int random_ability = random.nextInt(abilities2.length);
        int random_ability2 = random.nextInt(abilities2.length);
        int random_ability3 = random.nextInt(abilities2.length);
        super.abilities = new ArrayList<>();
        super.abilities.add(abilities2[random_ability]);
        super.abilities.add(abilities2[random_ability2]);
        super.abilities.add(abilities2[random_ability3]);
    }
    void receiveDamage(int damage) {
        Random rand3 = new Random();
        int luck = rand3.nextInt(2);
        if(luck == 0){
            currentLife = currentLife - damage;
        }
    }
    int getDamage() {
        int damage = 10;
        Random rand4 = new Random();
        int luck = rand4.nextInt(2);
        if(luck == 0){ damage = damage * 2; }
        return damage;
    }
    public char toCharacter() {
        return 'E';
    }
    public void acceptVisitor(Visitor visitor) {
        visitor.visitor(this);
    }
}
