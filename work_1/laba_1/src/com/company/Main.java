package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class GameObject {
    protected GameObject() {}
    protected void show() {}
}

class Hero extends GameObject {
    private String name;
    private int hp, exp, level, atk, deff;

    public Hero(String name, int hp, int exp, int level, int atk, int deff) {
        this.name = name;
        this.hp = hp;
        this.exp = exp;
        this.level = level;
        this.atk = atk;
        this.deff = deff;
    }

    public Hero() {
        name = "Hero";
        hp = exp = level = atk = deff = 0;
    }

    public void takeAWeapon(int level_weapon) {

        if (level >= level_weapon) {
            System.out.println("Персонаж может взять оружие!\n");
        } else {
            System.out.println("Персонаж не может поднять оружие!\n");
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void show() {
        System.out.println(
                "\nHero: " + name + ", hp: " + hp + ", exp: " + exp + ", level: " + level + ", atk: " + atk + ", deff: " + deff
        );
    }
}

class Enemy extends GameObject {
    private String name;
    private int  hp, atk, deff;

    public Enemy(String name, int hp, int atk, int deff) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.deff = deff;
    }

    public Enemy() {
        name = "Enemy";
        hp = atk = deff = 0;
    }

    public void show() {
        System.out.println(
                "\nEnemy: " + name + ", hp: " + hp + ", atk: " + atk + ", deff: " + deff
        );
    }
}

class Weapon extends GameObject {
    private String name;
    private int attack, coast, need_level;

    public Weapon(String name, int attack, int coast, int need_level) {
        this.name = name;
        this.attack = attack;
        this.coast = coast;
        this.need_level = need_level;
    }

    public Weapon() {
        name = "Weapon";
        attack = coast = need_level = 0;
    }

    public void show() {
        System.out.println(
                "\nWeapon: " + name + ", attack: " + attack + ", coast: " + coast + ", need_level: " + need_level
        );
    }
}


public class Main {

    static Hero hero = new Hero();
    static ArrayList<Enemy> masEnemy = new ArrayList<>();
    static ArrayList<Weapon> masWeapon = new ArrayList<>();

    public static void main(String[] args) {

        hero = new Hero("Lexa", 77, 50, 77, 66, 40);
        int hp = hero.getHp();
        System.out.print("Здоровье персонажа - " + hp + '\n');
        hero.takeAWeapon(77);

        makeEnemy();
        makeWeapons();

        for(Enemy enemy: masEnemy) {
            enemy.show();
        }

        for(Weapon weapon: masWeapon) {
            weapon.show();
        }
    }

    public static void makeEnemy() {
        String[] names = {"Lexa", "Nika", "Katy", "Perry", "Miley", "Cyrus", "Sky", "Bloom", "Lisa", "Ksenia"};

        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            String name_enemys = names[rnd.nextInt(10)];
            int hp_enemys = rnd.nextInt((100) + 1);
            int atk_enemys = rnd.nextInt((100) + 1);
            int deff_enemys = rnd.nextInt((100) + 1);
            masEnemy.add(new Enemy(name_enemys, hp_enemys, atk_enemys, deff_enemys));
        }
    }

    public static void makeWeapons() {
        String[] names = {"Glock", "AK-47", "RX-22", "EP 22", "SPAS-12", "Stinger", "AT4", "RPG-7", "M9", "TMP"};
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            String name_weapon = names[rnd.nextInt(10)];
            int atack_weapon = rnd.nextInt((100) + 1);
            int coast_weapon = rnd.nextInt((100) + 1);
            int need_level_weapon = rnd.nextInt((100) + 1);
            masWeapon.add(new Weapon(name_weapon, atack_weapon, coast_weapon, need_level_weapon));
        }
    }
}
