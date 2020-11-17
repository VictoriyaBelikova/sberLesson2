package ru.sber.view;

import ru.sber.model.Generation;

public class ViewGeneration {

    private static final char ALIVE = '█';
    private static final char DEAD = ' ';

    /**
     * Отобразить поколение на экране
     * @param generation
     */
    public static void printGeneration(Generation generation) {

        StringBuilder stringBuilder = new StringBuilder();
        boolean[][] generationMap = generation.getGen();

        for (int i = 1; i < generationMap.length - 1; i++) {
            for (int j = 1; j < generationMap[0].length - 1; j++) {
                if (generationMap[i][j])
                    stringBuilder.append(ALIVE);
                else
                    stringBuilder.append(DEAD);
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }
}
