package lab_5;

import lab_5.Log.Log;
import lab_5.Logic.Input;
import lab_5.Logic.Riddle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

public class Create {

    String[] nameNPC = {"Обычный Лис", "Пушистый Енот", "Красный Лис", "Пустынный Лис",
            "Лесной Лис", "Загодочный Лис", "Игривый Енот", "Зеленый Лис", "Волшеный Лис", "Редкий Лис",
            "Разбойник-Енот", "Вор-Енот", "Дружелюбный Енот", "Нахальный Енот", "Очаровательный Енот"};

    String[] descriptions = {"Пустыня морозов", "Пустыня солнца", "Пустыня пропавших душ",
            "Пустыня искателей золота", "Зимний лес", "Зеленый лес", "Лес загадок", "Волшебный лес",
            "Пустыня \"Тысяча ночей\"", "Лес некромантов", "Лес духов", "Лес кошмаров", "Лес криков",
            "Лес \"Вырвиглаз\"", "Пустыня злорадства", "Пустыня мертвецов", "Пустыня пиратов",
            "Пустыня разбойников", "Лес сказок", "Лес Бабы-Яги"};

    ArrayList<Riddle> riddles = new ArrayList<>();

    public Create() {
        createRiddle();
    }

    private void createRiddle() {
        Log.writeInto("lab_5.Create: " + "createRiddle()");
        Random rnd = new Random();
        riddles.add(new Riddle(rnd.nextInt(4 - 1) + 1,
                "В Полотняной стране\n" +
                        "По реке Простыне\n" +
                        "Плывет пароход\n" +
                        "То назад, то вперед,\n" +
                        "А за ним такая гладь —\n" +
                        "Ни морщинки не видать.",
                "Утюг",
                new String[]{"Кораблик", "Пароход"}));
        riddles.add(new Riddle(rnd.nextInt(4 - 1) + 1,
                "В брюшке — баня,\n" +
                        "В носу — решето,\n" +
                        "Нос — хоботок,\n" +
                        "На голове — пупок,\n" +
                        "Всего одна рука\n" +
                        "Без пальчиков,\n" +
                        "И та — на спине\n" +
                        "Калачиком.",
                "Чайник",
                new String[]{"Самовар", "Кувшин"}));
        riddles.add(new Riddle(rnd.nextInt(4 - 1) + 1,
                "Стоит дуб,\n" +
                        "В нем двенадцать гнезд,\n" +
                        "В каждом гнезде\n" +
                        "По четыре яйца,\n" +
                        "В каждом яйце\n" +
                        "По семи цыпленков.",
                "Год",
                new String[]{"Птицефабрика", "Дуб"}));
        riddles.add(new Riddle(rnd.nextInt(4 - 1) + 1,
                "В синем небе светляки —\n" +
                        "Не дотянешь к ним руки.\n" +
                        "А один большой светляк\n" +
                        "Изогнулся, как червяк.",
                "Звезды и месяц",
                new String[]{"Букашки", "Светлячки"}));
        riddles.add(new Riddle(rnd.nextInt(4 - 1) + 1,
                "Вдруг из черной темноты\n" +
                        "В небе выросли кусты.\n" +
                        "А на них-то голубые,\n" +
                        "Пунцовые, золотые\n" +
                        "Распускаются цветы\n" +
                        "Небывалой красоты.\n" +
                        "И все улицы под ними\n" +
                        "Тоже стали голубыми,\n" +
                        "Пунцовыми, золотыми,\n" +
                        "Разноцветными.",
                "Салют",
                new String[]{"Звезды", "Кусты малины"}));
        riddles.add(new Riddle(rnd.nextInt(4 - 1) + 1,
                "Вот иголки и булавки\n" +
                        "Выползают из-под лавки,\n" +
                        "На меня они глядят,\n" +
                        "Молока они хотят.",
                "Ёж",
                new String[]{"Иголки для шитья", "Булавки для шитья"}));
        riddles.add(new Riddle(rnd.nextInt(4 - 1) + 1,
                "Страну чудес откроем мы\n" +
                        "И встретимся с героями\n" +
                        "В строчках,\n" +
                        "На листочках,\n" +
                        "Где станции на точках.",
                "Книга",
                new String[]{"Кино", "Телефонная книга"}));
        riddles.add(new Riddle(rnd.nextInt(4 - 1) + 1,
                "Ёжик странный у Егорки\n" +
                        "На окне сидит в ведерке.\n" +
                        "День и ночь он дремлет,\n" +
                        "Спрятав ножки в землю.",
                "Кактус",
                new String[]{"Ёж", "Иголки для шитья"}));


        riddles.add(new Riddle(rnd.nextInt(7 - 5) + 5,
                "Стучит,\n" +
                        "Гремит,\n" +
                        "Вертится,\n" +
                        "Ходит весь\n" +
                        "Свой век,\n" +
                        "А не человек.",
                "Настенные часы",
                new String[]{"Корманные часы", "Наручные часы"}));
        riddles.add(new Riddle(rnd.nextInt(7 - 5) + 5,
                "Кафтан на мне зеленый,\n" +
                        "А сердце — как кумач;\n" +
                        "На вкус, как сахар, сладок,\n" +
                        "На вид — похож на мяч.",
                "Арбуз",
                new String[]{"Персик", "Дыня"}));
        riddles.add(new Riddle(rnd.nextInt(7 - 5) + 5,
                "Что загадка эта значит?\n" +
                        "Ничего я не пойму:\n" +
                        "По листве зайчонок скачет\n" +
                        "И рассеивает тьму.",
                "Солнечный свет",
                new String[]{"Зайчик", "Ветерок"}));
        riddles.add(new Riddle(rnd.nextInt(7 - 5) + 5,
                "Наш зверок\n" +
                        "С вершок.\n" +
                        "Носом шмыг, шмыг,\n" +
                        "Хвостиком дрыг, дрыг,\n" +
                        "А дело делает.",
                "Иголка с ниткой",
                new String[]{"Ёж", "Щенок"}));
        riddles.add(new Riddle(rnd.nextInt(7 - 5) + 5,
                "Загадка у меня к вам есть,\n" +
                        "Загадка непростая:\n" +
                        "Какую букву можно есть,\n" +
                        "Когда она пустая?",
                "Капуста",
                new String[]{"Арбуз", "Яблоко"}));
        riddles.add(new Riddle(rnd.nextInt(7 - 5) + 5,
                "Над нами кверху ногами.",
                "Таракан",
                new String[]{"Месяц", "Пол"}));
        riddles.add(new Riddle(rnd.nextInt(7 - 5) + 5,
                "Горит столб, а углей нет.",
                "Свеча",
                new String[]{"Загоревшийся дом", "Костер инквизиции"}));
        riddles.add(new Riddle(rnd.nextInt(7 - 5) + 5,
                "Под стеклом сижу, в одну сторону гляжу.",
                "Портрет",
                new String[]{"Таракан", "Пыль"}));


        riddles.add(new Riddle(rnd.nextInt(10 - 8) + 8,
                "В лесу без огня котел кипит.",
                "Муравейник",
                new String[]{"Потухший котел", "Пожар"}));
        riddles.add(new Riddle(rnd.nextInt(10 - 8) + 8,
                "Дом открыт\n" +
                        "со всех сторон.\n" +
                        "В доме —\n" +
                        "Тысячи колонн.\n" +
                        "Над колоннами —\n" +
                        "Шатры.\n" +
                        "Под колоннами —\n" +
                        "Ковры.\n" +
                        "Там живут —\n" +
                        "И в коврах,\n" +
                        "И в колоннах,\n" +
                        "И в шатрах.",
                "Лес",
                new String[]{"Цирк", "Многоэтажка"}));
        riddles.add(new Riddle(rnd.nextInt(10 - 8) + 8,
                "Нет ушей, а слышит. Нет рук, а пишет.",
                "Магнитофон",
                new String[]{"Стукач", "Инвалид"}));
        riddles.add(new Riddle(rnd.nextInt(10 - 8) + 8,
                "Бычок рогат, в руках зажат. Еду хватает, а сам голодает.",
                "Ухват",
                new String[]{"Рука", "Бычок"}));
        riddles.add(new Riddle(rnd.nextInt(10 - 8) + 8,
                "Что проходит большое пространство, не двигаясь с места?",
                "Дорога",
                new String[]{"Время", "Телефонный разговор"}));
        riddles.add(new Riddle(rnd.nextInt(10 - 8) + 8,
                "Чем больше отдаю, тем больше вырастаю. Величину свою отдачей измеряю.",
                "Яма",
                new String[]{"Карьерный рост", "Личностный рост"}));
        riddles.add(new Riddle(rnd.nextInt(10 - 8) + 8,
                "Сила не сила, а грязь убило.",
                "Мыло",
                new String[]{"Спирт", "Дезинфектор"}));
        riddles.add(new Riddle(rnd.nextInt(10 - 8) + 8,
                "Сделана из тряпок, а ниток не видно.",
                "Бумага",
                new String[]{"Кофта", "Салфетка"}));
    }

    public ArrayList<Location> createLocation() {
        Log.writeInto("lab_5.Create: " + "createLocation()");
        Random rnd = new Random();
        ArrayList<Location> locations = new ArrayList<>();
        for(int i = 0; i < riddles.size(); i++) {
            String des = descriptions[rnd.nextInt(descriptions.length)];
            NPC npc = createNPC(i);
            int level = npc.getRiddle().getComplexity();
            locations.add(new Location(npc, level, des));
        }
        return locations;
    }

    public Player createPlayer() {
        String name = Input.inputName();
        return new Player(name, 5);
    }

    private NPC createNPC(int i) {
        Random rnd = new Random();
        String name = nameNPC[rnd.nextInt(nameNPC.length)];
        Riddle riddle = riddles.get(i);
        return new NPC(name, riddle);
    }

    public ArrayList<Location> randomLocation(int limitSize, ArrayList<Location> list) {
        Log.writeInto("lab_5.Create: " + "randomLocation()");
        ArrayList<Location> locations = (ArrayList<Location>) list.stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
                    Collections.shuffle(collected);
                    return collected.stream();
                }))
                .limit(limitSize)
                .collect(Collectors.toList());
        return locations;
    }

    public ArrayList<Location> sortedLocation(ArrayList<Location> list) {
        Log.writeInto("lab_5.Create: " + "sortedLocation()");
        return (ArrayList<Location>)
                list.stream().sorted(Comparator.comparing(Location::getLevel)).collect(Collectors.toList());
    }
}

