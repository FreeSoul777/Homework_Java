package lab_3.Loot;

public class EquipmentCell<T> {

    private T value;

    public EquipmentCell(T value) {
        this.value = value;
    }

    public T getValue() { return value; }

    public void setValue(T value) {
        this.value = value;
    }
}
