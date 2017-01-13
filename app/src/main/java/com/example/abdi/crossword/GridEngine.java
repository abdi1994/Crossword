package com.example.abdi.crossword;

/**
 * Created by abdimohammed on 03/12/2016.
 */

public class GridEngine {

    private final static int GRID_NUMBER = 9;
    private float gridSize;


    public GridEngine() {

    }

    public int getGridNumber() {
        return GRID_NUMBER;
    }

    public float getGridSize() {
        return gridSize;
    }

    public void setGridSize(float gridSize) {
        this.gridSize = gridSize;
    }


}
