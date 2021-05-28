package com.thegame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    static Player player = new Player("Hello");

    @Test
    @DisplayName("Checking method Player.getName()")
    void getName() {
        Assertions.assertEquals(player.getName(), "Hello");
    }

    @Test
    @DisplayName("Checking method Player.getX()")
    void getX() {
        Assertions.assertEquals(player.getX(), 0);
    }

    @Test
    @DisplayName("Checking method Player.setX()")
    void setX() {
        player.setX(10);
        Assertions.assertNotEquals(player.getX(), 1);
        player.setX(1);
        Assertions.assertEquals(player.getX(), 1);
    }

    @Test
    @DisplayName("Checking method Player.getY()")
    void getY() {
        Assertions.assertEquals(player.getY(), 0);
        player.setY(10);
        Assertions.assertEquals(player.getY(), 10);
    }

    @Test
    @DisplayName("Checking method Player.setY()")
    void setY() {
        Assertions.assertNotEquals(player.getY(), 1);
        player.setY(1);
        Assertions.assertEquals(player.getY(), 1);
    }

    @Test
    @DisplayName("Checking methods Player.getHealth() and Player.setHealth()")
    void getHealth() {
        Assertions.assertNotEquals(player.getHealth(), 0);
        Assertions.assertEquals(player.getHealth(), 100);
        player.setHealth(58);
        Assertions.assertEquals(player.getHealth(), 58);
    }

    @Test
    @DisplayName("Checking method Player.getMaxHealth()")
    void getMaxHealth() {
        Assertions.assertNotEquals(player.getMaxHealth(), 0);
        Assertions.assertEquals(player.getMaxHealth(), 100);
    }

    @Test
    @DisplayName("Checking method Player.getCurrentHealth()")
    void getCurrentHealth() {
        Assertions.assertEquals(player.getCurrentHealth(), "58/100");
    }

    @Test
    @DisplayName("Checking method Player.getCapacity()")
    void getCapacity() {
        Assertions.assertEquals(player.getCapacity(), 50D);
    }

    @Test
    @DisplayName("Checking methods Player.addItem() and Player.getInventory()")
    void getInventory() {
        LinkedList<String> answer= new LinkedList<>();

        answer.add("Item");
        answer.add("Item1");
        player.addItem("Item");
        player.addItem("Item1");
        Assertions.assertEquals(player.getInventory(), answer);
        answer.add("Item3");
        Assertions.assertNotEquals(player.getInventory(), answer);
    }

    @Test
    @DisplayName("Checking method Player.setInventory()")
    void setInventory() {
        LinkedList<String> answer= new LinkedList<>();

        answer.add("Item");
        answer.add("Item1");
        player.getInventory().clear();
        player.setInventory(answer);
        Assertions.assertEquals(player.getInventory(), answer);
    }

    @Test
    @DisplayName("Checking methods Player.getCurrentItem and Player.setCurrentItem()")
    void getCurrentItem() {
        assertNull(player.getCurrentItem());
        player.setCurrentItem("Item");
        Assertions.assertEquals(player.getCurrentItem(), "Item");
    }
}