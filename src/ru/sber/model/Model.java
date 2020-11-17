package ru.sber.model;

public class Model {

    /**
     * вычисление следующего поколения
     * @param currentGeneration текущее поколение
     * @return
     */
    public static Generation calculateNextGeneration(Generation currentGeneration) {
        Generation nextGen = new Generation(0, 0, currentGeneration);

        for (int i = 1; i < currentGeneration.getGen().length - 1; i++) {
            for (int j = 1; j < currentGeneration.getGen().length - 1; j++) {
                if (currentGeneration.shouldDie(i, j)) {
                    nextGen.setValue(i, j, false);
                } else if (currentGeneration.shouldBorn(i, j)) {
                    nextGen.setValue(i, j, true);
                }
            }
        }

        return nextGen;
    }
}
