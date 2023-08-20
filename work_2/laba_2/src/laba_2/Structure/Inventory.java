package laba_2.Structure;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T extends Item> {

    private List<T> list = new ArrayList<>();

    public Inventory()
    {

    }
    public Inventory(T value) {
        list.add(value);
    }

    public List<T> getList() { return list; }

    public void add(T value) {
        list.add(value);
    }

    public void addAll(List<T> lst) {
        list.addAll(lst);
    }
}
