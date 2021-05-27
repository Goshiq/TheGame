package main.java.com.thegame;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            MyMap   map = new MyMap();
            initMap(map);
            ProcessTheGame.start(map);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void    initMap(MyMap map) throws Exception {
        //Создаём все предметы, возможные в игре
        Item    boots = new Item("Сапоги", "Почти как берцы, только не берцы");
        Item    knife = new Item("Нож", "Тупой, как твой сосед по парте. Но помни: ч. 2 ст. 20.8 КоАП РФ, штраф от 500 до 2000 руб.");
        Item    rock = new Item("Камень", "Похож на камень", true);
        Item    sharpKnife = new Item("Острый нож", "Ай!");
        Item    cup = new Item("Кружка", "Металлическая, ей убить можно");
        Item    cupOfColdWater = new Item("Кружка воды", "Даже никто не плавает");
        Item    cupOfSnow = new Item("Кружка со снегом", "Холодная, зараза");
        Item    cupOfHotWater = new Item("Кружка с кипятком", "Можно заварить что-нибудь");
        Item    snow = new Item("Снег", "Не жёлтый");
        Item    pot = new Item("Кастрюлька", "Можно суп сварить");
        Item    potOfSnow = new Item("Кастрюля снега", "И что с этим будешь делать?");
        Item    potOfColdWater = new Item("Кастрюля воды", "Нужно как-то согреть");
        Item    potOfHotWater = new Item("Кастрюля с кипящей водой", "Можно варить!");
        Item    fish = new Item("Рыба", "Молчит");
        Item    fishSoup = new Item("Рыбный суп", "Ухой назвать язык не поворачивается");

        Item.addItem(new Item("Огонь", "Пахнет жареным", true));
        Item.addItem(new Item("Песок", "Видимо, бабуля здесь часто бывала", true));
        Item.addItem(new Item("Снег", "Не жёлтый", true));
        Item.addItem(new Item("Камень", "Похож на камень", true));
        Item.addItem(new Item("Вода", "Мокрая", true));

        Item.addItem(boots);
        Item.addItem(knife);
        Item.addItem(rock);
        Item.addItem(sharpKnife);
        Item.addItem(cup);
        Item.addItem(cupOfColdWater);
        Item.addItem(cupOfSnow);
        Item.addItem(cupOfHotWater);
        Item.addItem(pot);
        Item.addItem(potOfColdWater);
        Item.addItem(potOfHotWater);
        Item.addItem(potOfSnow);
        Item.addItem(fish);
        Item.addItem(fishSoup);
        Item.addItem(snow);

        // Заполняем рецепты
        Item.addRecipe(rock.getName(), knife.getName(), sharpKnife);
        Item.addRecipe(snow.getName(), cup.getName(), cupOfSnow);
        Item.addRecipe(snow.getName(), pot.getName(), potOfSnow);
        Item.addRecipe(potOfHotWater.getName(), fish.getName(), fishSoup);

        //пусть стартовая локация будет с травкой
        map.setTerrain(0, 0, Terrain.GRASS);

        //чтобы не было пусто
        map.addItem(0, 0, boots);
        map.addItem(0,0, knife);
    }
}