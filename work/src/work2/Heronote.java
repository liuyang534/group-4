package work2;

public class Heronote {

    int id;
    String name ;
    String hp ;
    int damage;
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setHp(String hp) {
        this.hp = hp;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getHp() {
        return hp;
    }
    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return "Heronote{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hp='" + hp + '\'' +
                ", damage=" + damage +
                '}';
    }
}