package com.nari.sungang.legendof24.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;

import com.nari.sungang.legendof24.R;
import com.nari.sungang.legendof24.model.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by sungang on 2016/5/7.
 */
public class GameView extends View {
    private static final String TAG = "GameView";
    private static final Random random = new Random();
    private static final Map<Integer, String> pokerMap = new HashMap<Integer, String>();
    private static final int[] pokers = {
            R.drawable.clbace, R.drawable.clb02, R.drawable.clb03, R.drawable.clb04, R.drawable.clb05,
            R.drawable.clb06, R.drawable.clb07, R.drawable.clb08, R.drawable.clb09,
            R.drawable.clb10, R.drawable.clbjack, R.drawable.clbqueen, R.drawable.clbking,
            R.drawable.hrtace, R.drawable.hrt02, R.drawable.hrt03, R.drawable.hrt04, R.drawable.hrt05,
            R.drawable.hrt06, R.drawable.hrt07, R.drawable.hrt08, R.drawable.hrt09,
            R.drawable.hrt10, R.drawable.hrtjack, R.drawable.hrtqueen, R.drawable.hrtking,
            R.drawable.spdace, R.drawable.spd02, R.drawable.spd03, R.drawable.spd04, R.drawable.spd05,
            R.drawable.spd06, R.drawable.spd07, R.drawable.spd08, R.drawable.spd09,
            R.drawable.spd10, R.drawable.spdjack, R.drawable.spdqueen, R.drawable.spdking,
            R.drawable.dndace, R.drawable.dnd02, R.drawable.dnd03, R.drawable.dnd04, R.drawable.dnd05,
            R.drawable.dnd06, R.drawable.dnd07, R.drawable.dnd08, R.drawable.dnd09,
            R.drawable.dnd10, R.drawable.dndjack, R.drawable.dndqueen, R.drawable.dndking};
    private float mSquareHeight;
    private float mSquareWidth;

    private final Paint mPathPaint = new Paint();
    private final int mPathWidth;

    private int mRegularColor;
    private int mErrorColor;
    private int mSuccessColor;

    private final Interpolator mLinearOutSlowInInterpolator;

    private float mStartX = -1;
    private float mStartY = -1;

    private float mInProgressX = -1;
    private float mInProgressY = -1;

    private boolean mPatternInProgress = false;
    private List<Point> mInProgressPoints;

    private final ArrayList<Cell> mPattern = new ArrayList<>(4);
    private final boolean[][] mPatternDrawLookup = new boolean[2][2];
    private final CellState[][] mCellStates;

    private final Path mCurrentPath = new Path();

    static {
        for (int i = 0; i < pokers.length; i++) {
            if ((i + 1) % 13 == 0) {
                pokerMap.put(pokers[i], "13");
            } else {
                pokerMap.put(pokers[i], (i + 1) % 13 + "");
            }
        }
    }


    public GameView(Context context) {
        this(context, null);
    }

    public GameView(Context context, AttributeSet attrs) {
        this(context, null, R.attr.gameViewStyle);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GameView, defStyleAttr, 0);

        setClickable(true);

        //抗锯齿
        mPathPaint.setAntiAlias(true);
        //防抖动
        mPathPaint.setDither(true);

        mRegularColor = a.getColor(R.styleable.GameView_gv_regularColor, mRegularColor);
        mErrorColor = a.getColor(R.styleable.GameView_gv_errorColor, mErrorColor);
        mSuccessColor = a.getColor(R.styleable.GameView_gv_successColor, mSuccessColor);

        a.recycle();

        //绘制样式，STROKE为一条线
        mPathPaint.setStyle(Paint.Style.STROKE);
        //设置结合处的样式，ROUND为圆弧
        mPathPaint.setStrokeJoin(Paint.Join.ROUND);
        //笔刷样式，ROUND为圆形
        mPathPaint.setStrokeCap(Paint.Cap.ROUND);

        mPathWidth = getResources().getDimensionPixelSize(R.dimen.line_width);
        mPathPaint.setStrokeWidth(mPathWidth);

        mCellStates = new CellState[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                mCellStates[i][j] = new CellState();
                mCellStates[i][j].row = i;
                mCellStates[i][j].col = j;
            }
        }

        mLinearOutSlowInInterpolator = new LinearOutSlowInInterpolator();
    }


    public CellState[][] getmCellStates() {
        return mCellStates;
    }

    public void setPattern(List<Cell> pattern) {
        mPattern.clear();
        mPattern.addAll(pattern);

        clearPatternDrawLookup();
        for (Cell cell : pattern) {
            mPatternDrawLookup[cell.getRow()][cell.getColumn()] = true;
        }
    }

    private void clearPatternDrawLookup() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                mPatternDrawLookup[i][j] = false;
            }
        }
    }

    public void clearPattern() {
        resetPattern();
    }

    private void resetPattern() {
        mPattern.clear();
        clearPatternDrawLookup();
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        final ArrayList<Cell> pattern = mPattern;
        final int count = pattern.size();
        final boolean[][] drawLookup = mPatternDrawLookup;


        final Path currentPath = mCurrentPath;
        currentPath.rewind();

        // draw the circles
        for (int i = 0; i < 2; i++) {
            float centerY = getCenterYForRow(i);
            for (int j = 0; j < 2; j++) {
                float centerX = getCenterXForColumn(j);
                Cell cell = Cell.of(i, j);
                List<Integer> bitmaps = cell.getBitmaps();
                int cnt = bitmaps.size();
                int xOffset = 0;
                for (int k = 0; k < cnt; k++) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bitmaps.get(k));
                    Matrix matrix = new Matrix();
                    switch (cnt) {
                        case 1:
                            matrix.postScale(mSquareWidth / bitmap.getWidth(), mSquareHeight / bitmap.getHeight());
                            break;
                        case 2:
                            matrix.postScale(mSquareWidth / bitmap.getWidth() / 1.5f, mSquareHeight / bitmap.getHeight());
                            xOffset = (int) (mSquareWidth - mSquareWidth / 1.5f);
                            break;
                        case 3:
                            matrix.postScale(mSquareWidth / bitmap.getWidth() / 1.5f, mSquareHeight / bitmap.getHeight());
                            xOffset = (int) ((mSquareWidth - mSquareWidth / 1.5f) / 2);
                            break;
                        case 4:
                            matrix.postScale(mSquareWidth / bitmap.getWidth() / 1.5f, mSquareHeight / bitmap.getHeight());
                            xOffset = (int) ((mSquareWidth - mSquareWidth / 1.5f) / 3);
                            break;
                    }
                    Bitmap newBitMap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    canvas.drawBitmap(newBitMap, (int) centerX + xOffset * k, (int) centerY, null);
                }


               /* Drawable d = getResources().getDrawable(R.drawable.clbace);
                d.setBounds((int) centerX + 5, (int) centerY + 5, (int) (centerX + mSquareWidth / 2) - 5, (int) (centerY + mSquareHeight / 2) - 5);
                d.draw(canvas);*/
            }
        }

        mPathPaint.setColor(mRegularColor);
        mPathPaint.setAlpha(255);

        boolean anyCircles = false;
        float lastX = 0f;
        float lastY = 0f;

        for (int i = 0; i < count; i++) {
            Cell cell = pattern.get(i);
            if (!drawLookup[cell.row][cell.column]) {
                break;
            }

            anyCircles = true;

            float centerX = getCenterXForColumn(cell.column);
            float centerY = getCenterYForRow(cell.row);

            if (i != 0) {
                CellState state = mCellStates[cell.row][cell.column];
                currentPath.rewind();
                currentPath.moveTo(lastX, lastY);
                if (state.lineEndX != Float.MIN_VALUE && state.lineEndY != Float.MIN_VALUE) {
                    currentPath.lineTo(state.lineEndX, state.lineEndY);
                } else {
                    currentPath.lineTo(centerX, centerY);
                }
                canvas.drawPath(currentPath, mPathPaint);
            }
            lastX = centerX;
            lastY = centerY;
        }

        if (mPatternInProgress && anyCircles) {
            currentPath.rewind();
            currentPath.moveTo(lastX, lastY);
            currentPath.lineTo(mInProgressX, mInProgressY);
            canvas.drawPath(currentPath, mPathPaint);
        }

        /*if (mInProgressPoints != null && mInProgressPoints.size() > 0) {
            mStartX = mInProgressPoints.get(0).getX();
            mStartY = mInProgressPoints.get(0).getY();
            for (int i = 1; i < mInProgressPoints.size(); i++) {
                Point point = mInProgressPoints.get(i);
                mInProgressX = point.getX();
                mInProgressY = point.getY();
                currentPath.rewind();
                currentPath.moveTo(mStartX, mStartY);
                currentPath.lineTo(mInProgressX, mInProgressY);
                canvas.drawPath(currentPath, mPathPaint);
                mStartX = mInProgressX;
                mStartY = mInProgressY;
            }
        }*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Point point = null;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
               /* mInProgressPoints = new ArrayList<Point>();
                point = new Point();
                mStartX = event.getX();
                mStartY = event.getY();
                point.setX(mStartX);
                point.setY(mStartY);
                mInProgressPoints.add(point);*/
                handleActionDown(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                /*point = new Point();
                mInProgressX = event.getX();
                mInProgressY = event.getY();
                point.setX(mInProgressX);
                point.setY(mInProgressY);
                mInProgressPoints.add(point);*/
                return true;
            case MotionEvent.ACTION_UP:
               /* point = new Point();
                mInProgressX = event.getX();
                mInProgressY = event.getY();
                point.setX(mInProgressX);
                point.setY(mInProgressY);
                mInProgressPoints.add(point);
                invalidate();*/
                return true;
            case MotionEvent.ACTION_CANCEL:
                /*point = new Point();
                mInProgressX = event.getX();
                mInProgressY = event.getY();
                point.setX(mInProgressX);
                point.setY(mInProgressY);
                mInProgressPoints.add(point);
                invalidate();*/
                
                return true;
        }
        return false;
    }

    private float getCenterYForRow(int row) {
        return getPaddingTop() + row * mSquareHeight;
    }

    private float getCenterXForColumn(int column) {
        return getPaddingLeft() + column * mSquareWidth;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "onSizeChanged:width:" + w + ";" + "height:" + h);
        mSquareWidth = (w - getPaddingLeft() - getPaddingRight()) / 2.0f;
        mSquareHeight = (h - getPaddingTop() - getPaddingBottom()) / 2.0f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure");
        final int minimumWidth = getSuggestedMinimumWidth();
        final int minimumHeight = getSuggestedMinimumHeight();
        int viewWidth = resolveMeasured(widthMeasureSpec, minimumWidth);
        int viewHeight = resolveMeasured(heightMeasureSpec, minimumHeight);
        viewWidth = viewHeight = Math.min(viewWidth, viewHeight);
        setMeasuredDimension(viewWidth, viewHeight);
    }

    private int resolveMeasured(int measureSpec, int desired) {
        int result;
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (MeasureSpec.getMode(measureSpec)) {
            case MeasureSpec.UNSPECIFIED:
                result = desired;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.max(specSize, desired);
                break;
            case MeasureSpec.EXACTLY:
            default:
                result = specSize;
        }
        return result;
    }

    public static final class Cell {
        final int row;
        final int column;
        final List<Integer> bitmaps;
        private static final Cell[][] sCells = createCells();

        private static Cell[][] createCells() {
            Cell[][] res = new Cell[2][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    res[i][j] = new Cell(i, j);
                }
            }
            return res;
        }

        /**
         * @param row    The row of the cell.
         * @param column The column of the cell.
         */
        private Cell(int row, int column) {
            this.row = row;
            this.column = column;
            this.bitmaps = new ArrayList<Integer>();

            int rand = random.nextInt(52);
            this.bitmaps.add(pokers[rand]);
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public List<Integer> getBitmaps() {
            return bitmaps;
        }

        public static Cell of(int row, int column) {
            return sCells[row][column];
        }

        @Override
        public String toString() {
            return "(row=" + row + ",clmn=" + column + ")";
        }
    }

    public static class CellState {
        int row;
        int col;
        float radius;
        float translationY;
        float alpha = 1f;
        public float lineEndX = Float.MIN_VALUE;
        public float lineEndY = Float.MIN_VALUE;
        public ValueAnimator lineAnimator;
    }


}
