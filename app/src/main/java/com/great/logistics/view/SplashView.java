package com.great.logistics.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.great.logistics.R;

import java.util.Random;

/**
 * Created by 洪彬 on 2017/2/6.
 */
public class SplashView extends View {

    private Context context;
    private int mViewWidth,mViewHeight;
    private Paint mPaint;

    private SplashIcon[] splashIcons;
    public Random random;

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    private ValueAnimator valueAnimator;
    private float value;
    private int count = 0;

    public SplashView(Context context) {
        super(context);
        init(context);
    }

    public SplashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        pos = new float[2];
        tan = new float[2];
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
        mMatrix = new Matrix();

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);

        random = new Random();

        valueAnimator = ValueAnimator.ofFloat(0,1.0f);
        valueAnimator.setDuration(300);
        valueAnimator.setRepeatCount(17);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                count = 17;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d("hhhh","count:" + count);
                count++;
            }
        });
    }

    public void start(){
        valueAnimator.start();
    }

    public void recycle(){
        for(SplashIcon icon:splashIcons){
            icon.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        splashIcons = new SplashIcon[18];
        splashIcons[0] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_0),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*1, -mViewHeight/2 + mViewHeight/2/12*1);
        splashIcons[1] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_1),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*3, -mViewHeight/2 + mViewHeight/2/12*1);
        splashIcons[2] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_2),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*5, -mViewHeight/2 + mViewHeight/2/12*1);
        splashIcons[3] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_3),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*7, -mViewHeight/2 + mViewHeight/2/12*1);
        splashIcons[4] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_4),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*1, -mViewHeight/2 + mViewHeight/2/12*3);
        splashIcons[5] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_5),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*3, -mViewHeight/2 + mViewHeight/2/12*3);
        splashIcons[6] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_6),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*5, -mViewHeight/2 + mViewHeight/2/12*3);
        splashIcons[7] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_7),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*7, -mViewHeight/2 + mViewHeight/2/12*3);
        splashIcons[8] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_8),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*1, -mViewHeight/2 + mViewHeight/2/12*5);
        splashIcons[9] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_17),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*3, -mViewHeight/2 + mViewHeight/2/12*5);
        splashIcons[10] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_10),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*5, -mViewHeight/2 + mViewHeight/2/12*5);
        splashIcons[11] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_11),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*7, -mViewHeight/2 + mViewHeight/2/12*5);
        splashIcons[12] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_12),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*2, -mViewHeight/2 + mViewHeight/2/12*7);
        splashIcons[13] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_13),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*4, -mViewHeight/2 + mViewHeight/2/12*7);
        splashIcons[14] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_14),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*6, -mViewHeight/2 + mViewHeight/2/12*7);
        splashIcons[15] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_15),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*3, -mViewHeight/2 + mViewHeight/2/12*9);
        splashIcons[16] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_16),random.nextFloat(),
                -mViewWidth/2 + mViewWidth/8*5, -mViewHeight/2 + mViewHeight/2/12*9);
        splashIcons[17] = new SplashIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_9),0,
                -mViewWidth/2 + mViewWidth/8*4, -mViewHeight/2 + mViewHeight/2/12*11);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        Path path = new Path();
        path.moveTo(-mViewWidth / 2,400);
        path.quadTo(200,400,200,0);
        path.cubicTo(200,-100,100,-200,0,-200);
        path.cubicTo(-100,-200,-200,-100,-200,0);
        path.cubicTo(-200,100,-100,200,0,200);
        path.cubicTo(300,200,400,0,mViewWidth / 2+50,-200);

        PathMeasure measure = new PathMeasure(path, false);

        measure.getPosTan(measure.getLength() * value, pos, tan);

        mMatrix.reset();
        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI) + 35;
        mMatrix.postScale(0.3f*value,0.3f*value,mBitmap.getWidth()/2,mBitmap.getHeight()/2);
        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);

//        canvas.drawPath(path, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);

        if(count >= 0 && count <= 17){
            for(int i = 0;i<count;i++){
                mMatrix.reset();
                mMatrix.postScale(0.2f,0.2f,splashIcons[i].bitmap.getWidth()/2,0+splashIcons[i].bitmap.getHeight()/2);
                mMatrix.postRotate(splashIcons[i].degrees, splashIcons[i].bitmap.getWidth() / 2, splashIcons[i].bitmap.getHeight() / 2);
                mMatrix.postTranslate(splashIcons[i].x - splashIcons[i].bitmap.getWidth() / 2,splashIcons[i].y - splashIcons[i].bitmap.getHeight() / 2);
                canvas.drawBitmap(splashIcons[i].bitmap, mMatrix, mPaint);
            }
            SplashIcon icon = splashIcons[count];
            path.reset();
            path.quadTo(0, -mViewHeight/4, icon.x, icon.y);

            measure.setPath(path, false);
            measure.getPosTan(measure.getLength() * value, pos, tan);
            mMatrix.reset();
            mMatrix.postScale(0.2f*value,0.2f*value,0+icon.bitmap.getWidth()/2,0+icon.bitmap.getHeight()/2);
            mMatrix.postRotate(icon.degrees*value, icon.bitmap.getWidth() / 2, icon.bitmap.getHeight() / 2);
            mMatrix.postTranslate(pos[0] - icon.bitmap.getWidth() / 2,pos[1] - icon.bitmap.getHeight() / 2);
//            canvas.drawPath(path, mPaint);
            canvas.drawBitmap(icon.bitmap, mMatrix, mPaint);
        }

    }

    class SplashIcon{
        public Bitmap bitmap;
        public int x,y;
        public float randomValue;
        public float degrees;

        public SplashIcon(Bitmap bitmap, float value, int x,int y) {
            this.bitmap = bitmap;
            this.randomValue = value;
            this.degrees = randomValue * 360;
            this.x= x;
            this.y = y;
        }

        public void recycle(){
            if(!bitmap.isRecycled()){
                bitmap.recycle();
            }
        }
    }
}
