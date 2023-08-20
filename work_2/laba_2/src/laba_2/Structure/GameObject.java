package laba_2.Structure;

abstract public class GameObject {
    public String name;
    public int id;
    public static int ID = 0;

    public GameObject() {
        id = ID;
        ID += 1;
    }
}
