package com.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class SimulatorView extends SurfaceView {
	
	
	private Bitmap bmp;
	private SurfaceHolder holder;
	Rect rec;
	private int width;
	private int height;
	private int xpos;
	private int ypos;
	private int pass;
	
	public SimulatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
			holder = getHolder();
	        holder.addCallback(new Callback() {
				
				public void surfaceDestroyed(SurfaceHolder holder) {
					// TODO Auto-generated method stub
					
				}
				
				public void surfaceCreated(SurfaceHolder holder) {
					Canvas canvas = holder.lockCanvas(null);
	                onDraw(canvas);
	                holder.unlockCanvasAndPost(canvas);
					
				}
				
				public void surfaceChanged(SurfaceHolder holder, int format, int width,
						int height) {
					// TODO Auto-generated method stub
					
				}
			});

	        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
			// bmp = BitmapFactory.decodeResource(getResources(), R.drawable.plants1);
		
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		
//		Paint paint = new Paint();
//        paint.setColor(Color.WHITE);
        canvas.drawBitmap(bmp,0,0, null);
//        canvas.drawLine(0, 0, 500, 500, paint);
        
        
        
        
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        // make the entire canvas white
        paint.setColor(Color.TRANSPARENT);
        canvas.drawPaint(paint);
      
     
        width = bmp.getWidth();//start
        height = bmp.getHeight();//end

      
        xpos = width / 7;
        ypos = height/7;
        for (int i = 0; i < 7; i++) {                  
                   
            paint.setColor(Color.WHITE);
            canvas.drawLine(0, 0, xpos +(xpos*i), height, paint);                           

        }                  
         paint.setStyle(Style.STROKE);
            for (int i = 0; i < 7; i++) {                                  
                paint.setColor(Color.WHITE);
                canvas.drawLine(0, (ypos*pass)+ 5, width, (ypos*pass)+5, paint);      
                pass++;
                              
            }               
        
	}
}
