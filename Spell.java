abstract class Spell implements Visitor{
    int damage, manaCost;
}
class Ice extends Spell{
    {
        damage = 3;
        manaCost =5;
    }
    public void visitor(Entity entity) {
        if(entity.iceProtection == false){
            int d = damage;
            entity.currentLife = entity.currentLife - d;
        }
    }
}
class Fire extends Spell{
    {
        damage = 7;
        manaCost =5;
    }
    int fireDamage(){ return 7; }
    public void visitor(Entity entity) {
        if(entity.fireProtection == false){
            int d = fireDamage();
            entity.currentLife = entity.currentLife - d;
        }
    }
}
class Earth extends Spell{
    {
        damage = 5;
        manaCost =5;
    }
    int earthDamage(){ return 5; }
    public void visitor(Entity entity) {
        if(entity.earthProtection == false){
            int d = earthDamage();
            entity.currentLife = entity.currentLife - d;
        }
    }
}
