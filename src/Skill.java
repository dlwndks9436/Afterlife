public class Skill {

    String name;
    String type;
    String info;
    int level;
    int intensity;
    int energy;
    int price;

    public Skill(){}

    public Skill(String name, String type, int level, int intensity, int energy, int price){
        this.name = name;
        this.type = type;
        this.level = level;
        this.intensity = intensity;
        this.energy = energy;
        this.price = price;
    }

}
