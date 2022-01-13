import java.util.List;

abstract class Entity implements Element{
    List<String> abilities;
    boolean fireProtection;
    boolean iceProtection;
    boolean earthProtection;
    int currentLife;
    int maxLife;
    int currentMana;
    int maxMana;
    //methods to regenerate life/mana with a regeneration
    //value received as parameter
    void regenerateLife(int newLife){
        if(currentLife + newLife <= maxLife)
            currentLife = currentLife + newLife;
        else{
            currentLife = 100;
        }
    }
    void regenerateMana(int newMana){
        if(newMana + currentMana <= maxMana)
            currentMana = newMana + currentMana;
        else{
            currentMana = 100;
        }
    }
    void useAbility(Spell spell, Entity enemy){
        if(spell.manaCost <= currentMana){
            currentMana = currentMana - spell.manaCost;
            enemy.currentLife = enemy.currentLife - spell.damage;
        }
    }
    abstract void receiveDamage(int damage);
    abstract int getDamage();
}
abstract class Character extends Entity{
    String characterName;
    Inventory potionInventory;
    int experience,level,Ox,Oy,strength,charisma,dexterity;
    Character(String characterName){
        this.characterName = characterName;
        this.potionInventory = new Inventory();
    }
    void buyPotion (Potion potion){
        if(potionInventory.money >= potion.price() &&
                potionInventory.spaceLeft() >= potion.weight())
            potionInventory.addPotion(potion);
    }
    //method to increase experience when defeating an enemy
    void plusExperience(){
        experience = experience +20;
        if(experience >= 100){
            level++;
            experience = 0;
        }
    }
}
//interfaces to implement Visitor design pattern
interface Visitor{
    void visitor(Entity entity);
}
interface Element{
    void acceptVisitor(Visitor visitor);
}