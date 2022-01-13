import java.util.ArrayList;

class Shop implements CellElement{
    ArrayList<Potion> potions;
    Shop(){
        potions = new ArrayList<Potion>();
        Potion p1 = new HealthPotion();
        Potion p2 = new ManaPotion();
        Potion[] potionss = {p1, p2};
        //add 3 random potions at each shop
        java.util.Random random = new java.util.Random();
        int random1 = random.nextInt(potionss.length);
        int random2 = random.nextInt(potionss.length);
        int random3 = random.nextInt(potionss.length);
        potions.add(potionss[random1]);
        potions.add(potionss[random2]);
        potions.add(potionss[random3]);
    }
    public Potion deletePotion(int index){
        Potion p;
        p = potions.get(index);
        potions.remove(potions.get(index));
        return p;
    }
    public char toCharacter() {
        return 0;
    }
}
