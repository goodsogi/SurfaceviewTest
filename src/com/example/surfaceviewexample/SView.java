package com.example.surfaceviewexample;

import android.content.*;
import android.util.*;
import android.view.*;

public class SView extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = "SView";
	private SThread thread;
	private Context context;

	public SView(Context context) {
		super(context);
		this.context = context;
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.d(TAG, "surfaceChanged");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(TAG, "surfaceCreated");
		thread = new SThread(getHolder(), context);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "surfaceDestroyed");

		boolean retry = true;
		thread.setLoop(false);

		try {
			thread.join();
			retry = false;
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}