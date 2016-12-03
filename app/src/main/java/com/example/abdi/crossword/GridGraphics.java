package com.example.abdi.crossword;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by abdimohammed on 03/12/2016.
 */

public class GridGraphics extends View {

    private GridEngine gameEngine;
    private Paint mPaint;

    public GridGraphics(Context context, GridEngine gameEngine) {
        super(context);

        this.gameEngine = gameEngine;
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(gameEngine.getGridSize()== 0) {
            gameEngine.setGridSize(canvas.getWidth());
        }

        drawgrid(canvas);

    }

    private void drawgrid(Canvas canvas) {
        int i;
        for(i = 1; i < gameEngine.getGridNumber() + 1; i++) {
            //Horizontal
            canvas.drawLine(0, gameEngine.getGridSize() / gameEngine.getGridNumber() * i, gameEngine.getGridSize(), gameEngine.getGridSize() / gameEngine.getGridNumber() * i, mPaint);

            //Vertical
            canvas.drawLine(gameEngine.getGridSize() / gameEngine.getGridNumber() * i ,0 , gameEngine.getGridSize() / gameEngine.getGridNumber() * i, gameEngine.getGridSize(), mPaint);

        }
    }

}
