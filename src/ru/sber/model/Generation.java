package ru.sber.model;

import java.util.Random;

public class Generation {

    private static final int FIELD_WIDTH = 10;
    private static final int FIELD_HEIGHT = 10;

    private static final int NEIGHBOURS_COUNT_IF_UNDERPOPULATED = 1;
    private static final int NEIGHBOURS_COUNT_IF_OVERPOPULATED = 4;
    private static final int NEIGHBOURS_COUNT_IF_NEW_SHOULD_BORN = 3;

    /** поколение */
    private boolean[][] gen = new boolean[FIELD_WIDTH][FIELD_HEIGHT];

    /** соседи заданной ячейки */
    private static boolean[] cellNeighbours = new boolean[8];

    /** конструктор класса */
    public Generation(int width, int height, Generation generation) {
        if (generation == null)
            initGen(width, height);
        else setGen(generation);
    }

    /** сформировать начальное поколение */
    private void initGen(int width, int height) {
        gen = new boolean[width][height];

        Random random = new Random();
        for (int i = 1; i < gen.length - 1; i++)
        {
            for (int j = 1; j < gen[0].length - 1; j++)
            {
                gen[i][j] = random.nextBoolean();
            }
        }
    }

    /** перенести значения другого поколения в это */
    public void setGen(Generation gen) {
        for (int i = 0; i < gen.getGen().length; i++) {
            for (int j = 0; j < gen.getGen()[i].length; j++) {
                setValue(i, j, gen.getGen()[i][j]);
            }
        }
    }

    /** получить значения текущего поколения */
    public boolean[][] getGen() {
        return this.gen;
    }

    /**
     *  установить значение в ячейку
     * @param i - координата по Х
     * @param j - координата по У
     * @param val - значение, передаваемое в ячейку
     */
    public void setValue(int i, int j, boolean val) {
        this.gen[i][j] = val;
    }

    /** получить количество "живых" соседей ячейки */
    public int getAliveNeighboursCount(int i, int j) {
        setCellNeighbours(i, j);

        int aliveNeighbourCount = 0;

        for (int k = 0; k < cellNeighbours.length; k++)
        {
            if (cellNeighbours[k]) {
                aliveNeighbourCount++;
            }
        }

        return aliveNeighbourCount;
    }

    /** получить массив соседей ячейки с заданными координатами */
    private void setCellNeighbours(int x, int y) {
        cellNeighbours[0] = getNeighbour(x - 1, y - 1);
        cellNeighbours[1] = getNeighbour(x, y - 1);
        cellNeighbours[2] = getNeighbour(x + 1, y - 1);
        cellNeighbours[3] = getNeighbour(x - 1, y);
        cellNeighbours[4] = getNeighbour(x + 1, y);
        cellNeighbours[5] = getNeighbour(x - 1, y + 1);
        cellNeighbours[6] = getNeighbour(x, y + 1);
        cellNeighbours[7] = getNeighbour(x + 1, y + 1);
    }

    /** получить значение ячейки-соседа по заданной позиции */
    private boolean getNeighbour(int x, int y) {
        x = (x < 0) ? (this.getGen().length - 1) : x;
        x = (x > this.getGen().length - 1) ? 0 : x;
        y = (y < 0) ? (this.getGen()[0].length - 1) : y;
        y = (y > this.getGen()[0].length - 1) ? 0 : y;

        return this.getGen()[x][y];
    }

    /**
     * Проверка, должна ли ячейка быть уничтожена
     * @param x - координата по Х
     * @param y - координата по У
     * @return уничтожить ли ячейку
     */
    public boolean shouldDie(int x, int y) {
        return (isAlone(x, y) || isOverpopulated(x, y));
    }

    /**
     * Одинока ли ячейка
     * @param x - координата по Х
     * @param y - координата по У
     * @return
     */
    private boolean isAlone(int x, int y) {
        return ((getAliveNeighboursCount(x, y) <= NEIGHBOURS_COUNT_IF_UNDERPOPULATED) &&
                (isAlive(x, y)));
    }

    /**
     * Существует ли перенаселение
     * @param x - координата по Х
     * @param y - координата по У
     * @return
     */
    private boolean isOverpopulated(int x, int y) {
        return ((getAliveNeighboursCount(x, y) >= NEIGHBOURS_COUNT_IF_OVERPOPULATED)&&
                (isAlive(x, y)));
    }

    /**
     * Проверка, должна ли в ячейке "зародиться жизнь"
     * @param x - координата по Х
     * @param y - координата по У
     * @return нужно ли "оживить" ячейку
     */
    public boolean shouldBorn(int x, int y) {
        return ((!(isAlive(x, y))) && (getAliveNeighboursCount(x, y) == NEIGHBOURS_COUNT_IF_NEW_SHOULD_BORN));
    }

    /**
     * Жива ли ячейка
     * @param x - координата по Х
     * @param y - координата по У
     * @return
     */
    private boolean isAlive(int x, int y) {
        return this.getGen()[x][y];
    }
}
