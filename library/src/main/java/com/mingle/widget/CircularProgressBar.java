package com.mingle.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.mingle.library.R;


public class CircularProgressBar extends CustomView {

    DeterminateProgressDrawable determinateProgressDrawable;



	public CircularProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		setAttributes(attrs);

        determinateProgressDrawable=new DeterminateProgressDrawable(getResources().getColor(R.color.circular_progress_color), Utils.dpToPx(ringWidth+1, getResources()),1);

	}




	@Override
	protected void onInitDefaultValues() {
		minWidth = 32;
		minHeight = 32;
		backgroundColor = Color.parseColor("#1E88E5");
	}

    boolean isDissmiss=false;
	@Override
	protected void setAttributes(AttributeSet attrs) {
		// TODO 自动生成的方法存根
		super.setAttributes(attrs);
		float size = 4;// default ring width
		String width = attrs.getAttributeValue(MATERIALDESIGNXML, "ringWidth");
		if (width != null) {
			size = Utils.dipOrDpToFloat(width);
		}
		ringWidth = size;
	}

	/**
	 * Make a dark color to ripple effect
	 * 
	 * @return
	 */
	protected int makePressColor() {
		int r = (this.backgroundColor >> 16) & 0xFF;
		int g = (this.backgroundColor >> 8) & 0xFF;
		int b = (this.backgroundColor >> 0) & 0xFF;
		// r = (r+90 > 245) ? 245 : r+90;
		// g = (g+90 > 245) ? 245 : g+90;
		// b = (b+90 > 245) ? 245 : b+90;
		return Color.argb(128, r, g, b);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);


        if(isDissmiss){
            drawLastAnimation(canvas);
        }else{

		if (firstAnimationOver == false) {
            drawFirstAnimation(canvas);// 从无到有慢慢扩大的动画
            invalidate();
        }else{
//		if (cont > 0) {

            determinateProgressDrawable.draw(canvas);
//      /      drawSecondAnimation(canvas);// 一直旋转的动画

//
        }
        }

	}

	private float radius1 = 0;
	private float radius2 = 0;
	private int cont = 0;
	private boolean firstAnimationOver = false;
	private float ringWidth = 4;


    public void dissmiss(){


        this.isDissmiss=true;
    }


    private void drawLastAnimation(Canvas canvas) {

        if (arcO == limite)
            arcD += 6;
        if (arcD >= 290 || arcO > limite) {
            arcO += 6;
            arcD -= 6;
        }
        if (arcO > limite + 290) {
            limite = arcO;
            arcO = limite;
            arcD = 1;
        }

        if(arcD == 1){
            return;
        }

        rotateAngle += 4;
        canvas.rotate(rotateAngle, getWidth() / 2, getHeight() / 2);

        Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas temp = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        paint.setColor(backgroundColor);

        temp.drawArc(new RectF(0, 0, getWidth(), getHeight()), arcO, arcD, true, paint);
        Paint transparentPaint = new Paint();
        transparentPaint.setAntiAlias(true);
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        temp.drawCircle(getWidth() / 2, getHeight() / 2, (getWidth() / 2) - Utils.dpToPx(ringWidth, getResources()), transparentPaint);

        canvas.drawBitmap(bitmap, 0, 0, new Paint());

        invalidate();

    }






    public void setProgressPecentage(float progress) {
        if ( progress > 1 || progress < 0) {
            return;
        }
        int  mProgress = (int)(progress*360);
        determinateProgressDrawable.setAngle(mProgress);
    }
    /**
	 * Draw first animation of view
	 * 
	 * @param canvas
	 */
	private void drawFirstAnimation(Canvas canvas) {
		if (radius1 < getWidth() / 2) {
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(makePressColor());
			radius1 = (radius1 >= getWidth() / 2) ? (float) getWidth() / 2 : radius1 + 1;
			canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius1, paint);
		} else {
			Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas temp = new Canvas(bitmap);
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(makePressColor());

            temp.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2, paint);

			Paint transparentPaint = new Paint();

			transparentPaint.setAntiAlias(true);
			transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
			transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
			if (cont >= 50) {
//				radius2 = (radius2 >= getWidth() / 2) ? (float) getWidth() / 2 : radius2 + 1;
			} else {
				radius2 = (radius2 >= getWidth() / 2 - Utils.dpToPx(ringWidth, getResources())) ? 
						(float) getWidth() / 2 - Utils.dpToPx(ringWidth, getResources()) : radius2 + 1;
			}
            temp.drawCircle(getWidth() / 2, getHeight() / 2, radius2, transparentPaint);
			canvas.drawBitmap(bitmap, 0, 0, new Paint());
            bitmap.recycle();
			if (radius2 >= getWidth() / 2 - Utils.dpToPx(ringWidth, getResources())) {
                firstAnimationOver = true;
                cont++;
            }
			if (radius2 >= getWidth() / 2)
				firstAnimationOver = true;
		}
	}

	private int arcD = 1;
	private int arcO = 0;
	private float rotateAngle = 0;
	private int limite = 0;

	/**
	 * Draw second animation of view
	 * 
	 * @param canvas
	 */
	private void drawSecondAnimation(Canvas canvas) {
		if (arcO == limite)
			arcD += 6;
		if (arcD >= 290 || arcO > limite) {
			arcO += 6;
			arcD -= 6;
		}
		if (arcO > limite + 290) {
			limite = arcO;
			arcO = limite;
			arcD = 1;
		}
		rotateAngle += 4;
		canvas.rotate(rotateAngle, getWidth() / 2, getHeight() / 2);

		Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas temp = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(backgroundColor);
		// temp.drawARGB(0, 0, 0, 255);
		temp.drawArc(new RectF(0, 0, getWidth(), getHeight()), arcO, arcD, true, paint);
		Paint transparentPaint = new Paint();
		transparentPaint.setAntiAlias(true);
		transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
		transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		temp.drawCircle(getWidth() / 2, getHeight() / 2, (getWidth() / 2) - Utils.dpToPx(ringWidth, getResources()), transparentPaint);

		canvas.drawBitmap(bitmap, 0, 0, new Paint());
	}

	// Set color of background
	public void setBackgroundColor(int color) {
		super.setBackgroundColor(getResources().getColor(android.R.color.transparent));
		if (isEnabled()) {
			beforeBackground = backgroundColor;
		}
		this.backgroundColor = color;
	}
	
	public void setRingWidth(float width) {
		ringWidth = width;
	}


    private class DeterminateProgressDrawable extends Drawable {

        private Paint mPaint;
        private Paint mbgPaint;
        private float mBorderWidth;
        private int mEndAngle=20;
        private final RectF mDrawableBounds = new RectF();

        public DeterminateProgressDrawable(int color, int borderWidth, int angle) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(borderWidth);
            mPaint.setColor(color);
            mBorderWidth = borderWidth;
            mbgPaint = new Paint();
            mbgPaint.setAntiAlias(true);


            mbgPaint.setColor(makePressColor());
            mbgPaint.setStyle(Paint.Style.STROKE);
            mbgPaint.setStrokeWidth(Utils.dpToPx(ringWidth, getResources()));

            mEndAngle = angle;
        }

        public void setAngle(int angle) {


            mEndAngle = angle;



            invalidate();
        }

        @Override
        public void draw(Canvas canvas) {
            mDrawableBounds.left = mBorderWidth / 2f + .5f;
            mDrawableBounds.right = getWidth() - mBorderWidth / 2f - .5f;
            mDrawableBounds.top =  mBorderWidth / 2f + .5f;
            mDrawableBounds.bottom = getHeight() - mBorderWidth / 2f - .5f;
            canvas.drawArc(mDrawableBounds, 0.f, 360, false, mbgPaint);
            canvas.drawArc(mDrawableBounds, 0.f, mEndAngle, false, mPaint);
        }

        @Override
        public void setAlpha(int alpha) {
            mPaint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            mPaint.setColorFilter(colorFilter);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSPARENT;
        }

        @Override
        protected void onBoundsChange(Rect bounds) {
            super.onBoundsChange(bounds);
            mDrawableBounds.left = bounds.left + mBorderWidth / 2f + .5f;
            mDrawableBounds.right = bounds.right - mBorderWidth / 2f - .5f;
            mDrawableBounds.top = bounds.top + mBorderWidth / 2f + .5f;
            mDrawableBounds.bottom = bounds.bottom - mBorderWidth / 2f - .5f;
        }
    }


}
