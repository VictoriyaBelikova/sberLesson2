package ru.sber;

import ru.sber.model.Generation;
import ru.sber.model.Model;
import ru.sber.view.ViewGeneration;

public class Runner {

    private static final int MAP_WIDTH = 10;
    private static final int MAP_HEIGHT = 10;

    public static void main(String[] args) {

        while (true) {
            Generation previousGeneration = new Generation(MAP_WIDTH, MAP_HEIGHT, null);
            Generation currentGeneration = new Generation(0, 0, previousGeneration);

            ViewGeneration.printGeneration(currentGeneration);
            previousGeneration.setGen(currentGeneration);

            Generation nextGeneration = Model.calculateNextGeneration(currentGeneration);
            currentGeneration.setGen(nextGeneration);
            ViewGeneration.printGeneration(currentGeneration);
        }
    }
}
