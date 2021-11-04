public class Armor {
    String type;
    String name;
    int level;
    int defensePoint;
    int priceB;
    int priceS;

    public Armor(String name, String type, int level, int defensePoint, int priceB, int priceS){
        this.name = name;
        this.type = type;
        this.level = level;
        this.defensePoint = defensePoint;
        this.priceB = priceB;
        this.priceS = priceS;
    }
}
