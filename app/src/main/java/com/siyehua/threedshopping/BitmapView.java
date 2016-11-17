package com.siyehua.threedshopping;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

public class BitmapView extends View {
    private Bitmap[] bitmaps = new Bitmap[32];

    public BitmapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapView(Context context) {
        super(context);
    }


    /**
     * 绘画
     */
    private synchronized void drawImage(Canvas canvas) {
        if (canvas == null || resArr == null) {
            return;
        }
        Rect rect = new Rect();
        rect.top = 0;
        rect.bottom = getHeight();
        rect.left = 0;
        rect.right = getWidth();
        for (int i = 0; i < 4; i++) {
            Bitmap bitmap = bitmaps[degress * 4 + i];
            if (bitmap == null || bitmap.isRecycled()) {
                bitmap = bitmaps[degress * 4 + i] = zoomImg(((BitmapDrawable) getResources()
                        .getDrawable(resArr[degress * 4 + i])).getBitmap(), getWidth(), getHeight
                        ());
            }
            canvas.drawBitmap(bitmap, rect, rect, new Paint());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        drawImage(canvas);
    }

    private int degress;
    float startX;
    float distanceX;
    int[] resArr;

    public void setDegress(int degress) {
        this.degress = degress % 8;
        postInvalidate();
    }

    public void setImage(int[] resArr) {
        this.resArr = resArr;
    }
    //    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startX = event.getRawX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                distanceX = event.getRawX() - startX;
//                degress = 360f * (distanceX / getWidth()) *5;
//                Log.e("siyehua", degress + "");
//                postInvalidate();
//                break;
//            case MotionEvent.ACTION_UP:
//                degress = 0;
//                startX = 0;
//                postInvalidate();
//                break;
//        }
//        return true;
//    }


    // 缩放图片
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }

}
