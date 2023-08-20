package laba_2.Structure;

import java.util.List;

public interface Lootable {  //поведение существ, с которых после убийства падают предметы
    List<Item> dropLoot();
}
