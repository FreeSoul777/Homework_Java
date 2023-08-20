package laba_2.Structure;

public class EquipmentCell<T> {

    private T value = null;

    public EquipmentCell(T value) {
        this.value = value;
    }

    public T getValue() { return value; }
}
