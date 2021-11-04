public class Potion extends Item{
    String name;
    int HPHeal;
    int EPHeal;
    int price;
    int level;

    public Potion(String name, int HPHeal, int EPHeal,int level, int price){
        this.name = name;
        this.HPHeal = HPHeal;
        this.EPHeal = EPHeal;
        this.price = price;
        this.level = level;
    }
}
