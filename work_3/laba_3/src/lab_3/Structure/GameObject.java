package lab_3.Structure;

abstract public class GameObject {
    private String name;
    public int id;
    public static int ID = 0;

    public GameObject(String name) {
        this.name = name;
        id = ID;
        ID += 1;
    }

    public String getName() {
        return name;
    }
    public String getOwnClassName() {
        return getClass().getSimpleName();
    }

    public void setName(String name) {
        this.name = name;
    }
}

