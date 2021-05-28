package com.thegame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    @DisplayName("Checking return value of method Item.getName()")
    void getName() {
        Item item = new Item("Some name");
        Item item1 = new Item("Some name", "Some description");
        Assertions.assertNotNull(item);
        Assertions.assertNotNull(item1);
    }

    @Test
    @DisplayName("Checking return value of method Item.getDescription()")
    void findDescription() {
        Item item = new Item ("Some name", "Some description");
        Item.addItem(item);
        Assertions.assertEquals("Some description", Item.findDescription("Some name"));
    }

    @Test
    @DisplayName("Checking method Item.addRecipe()")
    void addRecipe() {
        Item.addRecipe("S1", "S2", new Item("Name", "Description"));
        Assertions.assertNotNull(Item.getResult("S1", "S2"));
    }

    @Test
    @DisplayName("Checking method Item.addItem()")
    void addItem() {
        Item.addItem(new Item("Kekek", "Lolo"));
        Assertions.assertNotNull(Item.findDescription("Kekek"));
    }

    @Test
    @DisplayName("Checking return value of method Item.getPairs()")
    void getPairs() {
        LinkedList<String> list = new LinkedList<>();
        LinkedList<String> answer = new LinkedList<>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        list.add("Item4");
        answer.add("Item1");
        answer.add("Item3");
        Item.addRecipe("Item1", "Item2", new Item("Res", "Des"));
        Item.addRecipe("Item2", "Item3", new Item("Res", "Des"));
        Assertions.assertEquals(Item.getPairs("Item2", list), answer);
        assertNull(Item.getPairs("Item4", list));
    }

    @Test
    @DisplayName("Checking method Item.getResult()")
    void getResult() {
        Item.addRecipe("S1", "S2", new Item("Name", "Description"));
        Assertions.assertNotNull(Item.getResult("S1", "S2"));
    }

    @Test
    @DisplayName("Checking method Item.getALot()")
    void getALot() {
        Item.addItem(new Item("Name", "Description", true));
        Item.addItem(new Item("Name1", "Description1", false));
        Assertions.assertEquals(Item.getALot("Name"), true);
        Assertions.assertEquals(Item.getALot("Name1"), false);
    }
}