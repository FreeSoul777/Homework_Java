# laba_4
## Задание 1.
Задание состоит из двух частей.  
1.	Описать структуру данных класса Character, взяв за основу описание характеристик персонажей в Genshin Impact (или любой другой RPG, но не менее 5 базовых характеристик):  
●	Максимальное кол-во HP;  
●	Сила атаки;  
●	Защита;  
●	Мастерство стихий;  
●	Выносливость.  
●	Тип Архонта (для геншина) или специализация (для других RPG);  
Далее	взять	одну	произвольную	характеристику	из	списка второстепенных:  
●	Шанс крит.попадания;  
●	Крит.урон;  
●	Бонус лечения;  
●	Бонус получаемого лечения;  
●	Восст. энергии;  
●	Снижение времени отката;  
●	Прочность щита.  
После чего создать динамический массив из объектов данного класса, и заполнить не менее 15-20 элементами (имя, характерстики можно задавать рандомом или брать из офф.источников).  
2.	С имеющейся коллекцией объектов, при помощи методов Stream API произвести следующие действия:  
a.	Вывести персонажа с максимальным количеством HP среди всех;  
b.	Вывести персонажа с минимальным количеством HP среди всех;  
c.	Сортировать персонажей по силе атаки, выбрать только тех, у кого она меньше 100. Вывести на экран;  
d.	Тоже самое, что в (c), но убрать из выборки всех, у кого меньше 100;  
e.	Произвести выборку персонажей по одному типу архонта/специализации и для всех изменить произвольную характеристику умножив её значение на 2. Выборку собрать в коллекцию List и вывести на экран;  
f.	Найти персонажа со значением характеристики силы атаки в 1337, вывод функции .get() произвести в объект класса Optional<Character>. Если такой объект существует, то вывести на экран его и добавить перед его именем слово l33t. Если такого объекта нет, вывести на экран “No l33t”);  
g.	Посчитать для каждой специализации количество персонажей. Вывести на экран в виде таблички.  
