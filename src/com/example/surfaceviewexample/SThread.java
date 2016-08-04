package com.example.surfaceviewexample;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class SThread extends Thread {

	private static final String TAG = "SThread";
	private SurfaceHolder holder;
	private int dX = -100;

	private boolean isLoop;
	private Context context;

	public SThread(SurfaceHolder _holder, Context context) {
		this.holder = _holder;
		this.isLoop = true;
		this.context = context;

		Log.d(TAG, "width " + MainActivity.deviceWidth + " // height "
				+ MainActivity.deviceHeight);
	}

	public void setLoop(boolean _isLoop) {
		this.isLoop = _isLoop;
	}

	@Override
	public void run() {
		super.run();

		while (isLoop) {
			Canvas mCanvas = null;
			try {
				try {

					Paint p = new android.graphics.Paint();
					p.setColor(android.graphics.Color.RED);
					p.setAntiAlias(true);
					mCanvas = holder.lockCanvas();

					Resources res = context.getResources();
					Bitmap bitmap = BitmapFactory.decodeResource(res,
							R.drawable.coffee);
					Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, mCanvas.getWidth(), mCanvas.getHeight(), false);
					mCanvas.drawBitmap(scaledBitmap, 0, 0, p);

				} finally {
					holder.unlockCanvasAndPost(mCanvas);
				}

			} catch (Exception e) {
				Log.e("fureun", e.toString());
			}

			// Canvas mCanvas = null;
			// try {
			// mCanvas = holder.lockCanvas(null);
			//
			// synchronized (holder) {
			// Paint mPaint = new Paint();
			// mPaint.setColor(Color.BLUE);
			// //mCanvas.drawRect(0, 0, MainActivity.deviceWidth,
			// // MainActivity.deviceHeight, mPaint);
			// mCanvas.drawCircle(360, 640, 20, mPaint);
			//
			// //mPaint.setColor(Color.RED);
			//
			// /*
			// * 1 : 완쪽 위치 2 : 윗변 위치 3 : 오른쪽 위치 4 : 밑변 위치 ( Top가 0일때를 기준으로
			// * 길이 지정. ex ) top == 100이라면 200을 해줘야 100 크기의 이미지가 보임.
			// */
			//
			// // mCanvas.drawRect(dX, dX + 100, dX + 100, dX + 200, mPaint);
			// // dX += 5;
			// // if (dX >= MainActivity.deviceWidth) {
			// // dX = -100;
			// // }
			// }
			// } finally {
			// if (mCanvas != null) {
			// holder.unlockCanvasAndPost(mCanvas);
			// }
			// }
		}
	}
}
