import java.util.ArrayList;
import java.util.List;

class Inventory{
    List<Potion> potions;
    int money, maxSpace;
    Inventory(){
        this.potions = new ArrayList<>();
        this.maxSpace = 30;
        this.money = 25;
    }
    void addPotion(Potion potion){
        potions.add(potion);
        money = money - potion.price();
    }
    void removePotion(Potion potion){
        potions.remove(potion);
    }
    int spaceLeft(){
        int s = this.maxSpace;
        for (Potion potion : potions) {
            s = s - potion.weight();
        }
        return s;
    }
}
interface Potion{
    void potionUtility(Entity entity);
    int price();
    int regeneration();
    int weight();
}
//classes for each potion type
class HealthPotion implements Potion{
    int price, weight, regenerationValue;
    HealthPotion(){
        this.price = 10;
        this.weight = 4;
        this.regenerationValue = 30;
    }
    public void potionUtility(Entity entity) {
        entity.currentLife = entity.currentLife + regenerationValue;
        if( entity.currentLife > entity.maxLife ){
            entity.currentLife = entity.maxLife;
        }
    }
    public int price() {
        return price;
    }
    public int regeneration() {
        return regenerationValue;
    }
    public int weight() {
        return weight;
    }
}
class ManaPotion implements Potion{
    int price, weight, regenerationValue;
    ManaPotion(){
        this.price = 7;
        this.weight = 3;
        this.regenerationValue = 30;
    }
    public void potionUtility(Entity entity) {
        entity.currentMana = entity.currentMana + regenerationValue;
        if( entity.currentMana > entity.maxMana ){
            entity.currentMana = entity.maxMana;
        }
    }
    public int price() {
        return price;
    }
    public int regeneration() {
        return regenerationValue;
    }
    public int weight() {
        return weight;
    }
}