package lab_4;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Main {

    private static ArrayList<Character> heroes = new ArrayList<>();

    public static void main(String[] args) {

        generateCharacters();
        heroes.stream().forEach(System.out::println);

        //A
        Optional<Character> character = heroes.stream().max(Comparator.comparing(Character::getHp));
        System.out.println("Задание A, Вывести персонажа с максимальным количеством HP среди всех\n" + character.get());

        //B
        character = heroes.stream().min(Comparator.comparing(Character::getHp));
        System.out.println("Задание B, Вывести персонажа с минимальным количеством HP среди всех;\n" + character.get());

        //C
        System.out.println("Задание C, Сортировать персонажей по силе атаки, выбрать только тех, у кого " +
                "она меньше 100. Вывести на экран;\n");
        heroes.stream().sorted(Comparator.comparing(Character::getAtk)).filter(x -> x.getAtk() < 100)
                .forEach(System.out::println);

        //D
        System.out.println("Задание D, Сортировать персонажей по силе атаки, выбрать только тех, у кого " +
                "она больше 100. Вывести на экран;\n");
        heroes.stream().sorted(Comparator.comparing(Character::getAtk)).filter(x -> x.getAtk() > 100)
                .forEach(System.out::println);

        //E
        List<Character> list = heroes.stream().filter(x -> Objects.equals(x.getArchon(), "Лучник"))
                .peek(x -> x.setAtk(x.getAtk() * 2))
                .collect(Collectors.toList());
        System.out.println("Задание E, Произвести выборку персонажей по одному типу " +
                "архонта/специализации и для всех изменить произвольную\n" +
                "характеристику умножив её значение на 2. Выборку собрать в " +
                "коллекцию List и вывести на экран;\n");
        list.stream().forEach(System.out::println);

        //F
//        heroes.get(5).setAtk(1337);
        character = heroes.stream().filter(x -> x.getAtk() == 1337).findFirst();
        System.out.println("Задание F, Найти персонажа со значением характеристики силы атаки в 1337; \n" +
                (character.isEmpty() ? "No 133t\n" : "133t " + character.get()));

        //G
        Map<String, Long> map = heroes.stream().collect(groupingBy(x -> x.getArchon(), Collectors.counting()));
        System.out.println("Задание G, Посчитать для каждой специализации количество персонажей. " +
                "Вывести на экран в виде таблички;\n");
        map.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        });
    }


    private static void generateCharacters() {
        Random rnd = new Random();
        int count = rnd.nextInt(30) + 15;
        String[] names = {"Элой", "Кокоми", "Баал", "Сара", "Саю", "Тома", "Розария", "Альбедо",
                "Барбара", "Бэй Доу", "Беннет", "Чун Юнь", "Дилюк", "Диона", "Фишль", "Гань Юй",
                "Джинн", "Ху Тао", "Кэйа", "Кэ Цин", "Кли", "Лиза", "Мона", "Нин Гуан", "Ноэлль",
                "Ци Ци", "Рэйзор", "Сахароза", "Путешественник (Гео)", "Путешественник (Анемо)", "Тарталья",
                "Венти", "Сян Лин", "Сяо", "Син Цю"};
        String[] archons = {"Лучник", "Боец", "Страж", "Бродяга", "Защитник"};
        while(count-- > 0) {
            String name = names[rnd.nextInt(names.length)];
            int hp = rnd.nextInt(500) + 1;
            int atk = rnd.nextInt(500) + 1;
            int def = rnd.nextInt(500) + 1;
            int elementalMastery = rnd.nextInt(500) + 1;
            int stamina = rnd.nextInt(500) + 1;
            String archon = archons[rnd.nextInt(archons.length)];
            int energyRecharge = rnd.nextInt(500) + 1;
            heroes.add(new Character(name, hp, atk, def, elementalMastery, stamina, archon, energyRecharge));
        }
    }
}
