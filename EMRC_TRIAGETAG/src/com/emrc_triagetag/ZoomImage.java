package com.emrc_triagetag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

public class ZoomImage extends ImageView {
	/** 记录是拖拉照片模式还是放大缩小照片模式 */
	private int mode = 0;// 初始状态
	/** 拖拉照片模式 */
	private static final int MODE_DRAG = 1;
	/** 放大缩小照片模式 */
	private static final int MODE_ZOOM = 2;

	/** 用于记录开始时候的坐标位置 */
	private PointF startPoint = new PointF();
	/** 用于记录拖拉图片移动的坐标位置 */
	private Matrix matrix = new Matrix();
	/** 用于记录图片要进行拖拉时候的坐标位置 */
	private Matrix currentMatrix = new Matrix();

	/** 两个手指的开始距离 */
	private double startDis;
	/** 两个手指的中间点 */
	private PointF midPoint;

	private boolean type = false;
	/**
	 * 初始化时的缩放比例，如果图片宽或高大于屏幕，此值将小于0
	 */
	private boolean isCanDrag;

	private boolean isCheckTopAndBottom = true;
	private boolean isCheckLeftAndRight = true;
	private int mTouchSlop;
	/**
	 * 缩放的手势检测
	 */
	@SuppressWarnings("unused")
	private ScaleGestureDetector mScaleGestureDetector = null;

	public ZoomImage(Context context, AttributeSet attrs) {
		super(context, attrs);

		bulid_image();
	}
	//
	// public ScaleImage(Context context ,AttributeSet attrs) {
	// super(context,attrs);
	// BitmapDrawable mBitmapDrawable=(BitmapDrawable)this.getDrawable();
	// if(mBitmapDrawable!=null){
	// mBitmap=mBitmapDrawable.getBitmap();
	// bulid_image();
	// }
	// }
	// //圖片縮放層級設定
	// private void Scale()
	// {
	// //取得圖片縮放的層級
	// float level[] = new float[9];
	// mMatrix.getValues(level);
	//
	// //狀態為縮放時進入
	// if (mState == STATE_ZOOM)
	// {
	// //若層級小於1則縮放至原始大小
	// if (level[0] < mMinScale)
	// {
	// mMatrix.setScale(mMinScale, mMinScale);
	// mMatrix.postTranslate(mCenterX,mCenterY);
	// }
	//
	// //若縮放層級大於最大層級則顯示最大層級
	// if (level[0] > mMaxScale) mMatrix.set(mChangeMatrix);
	// }
	// }

	/** 计算两个手指间的距离 */
	private double distance(MotionEvent event) {
		float dx = event.getX(1) - event.getX(0);
		float dy = event.getY(1) - event.getY(0);
		/** 使用勾股定理返回两点之间的距离 */
		return Math.sqrt(dx * dx + dy * dy);
	}

	/** 计算两个手指间的中间点 */
	private PointF mid(MotionEvent event) {
		float midX = (event.getX(1) + event.getX(0)) / 2;
		float midY = (event.getY(1) + event.getY(0)) / 2;
		return new PointF(midX, midY);
	}
	// ---------

	private RectF getMatrixRectF() {
		Matrix matrix1 = matrix;
		RectF rect = new RectF();
		Drawable d = getDrawable();
		if (null != d) {
			rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
			matrix1.mapRect(rect);
		}
		return rect;
	}

	@SuppressLint("ClickableViewAccessibility")
	private void bulid_image() {
		if (!type) {
			setScaleType(ScaleType.MATRIX);
			type = true;
		}
		// 設置Touch觸發的Listener動作
		this.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {

				/** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */
				switch (event.getAction() & MotionEvent.ACTION_MASK) {
				// 手指压下屏幕
				case MotionEvent.ACTION_DOWN:
					mode = MODE_DRAG;
					// 记录ImageView当前的移动位置
					currentMatrix.set(ZoomImage.this.getImageMatrix());
					startPoint.set(event.getX(), event.getY());
					break;
				// 手指在屏幕上移动，改事件会被不断触发
				case MotionEvent.ACTION_MOVE:
					// 拖拉图片
					if (mode == MODE_DRAG) {
						float dx = (event.getX() - startPoint.x) / 10; // 得到x轴的移动距离
						float dy = (event.getY() - startPoint.y) / 10; // 得到y轴的移动距离
						if (!isCanDrag) {
							isCanDrag = isCanDrag(dx, dy);
						}
						if (isCanDrag) {
							RectF rectF = getMatrixRectF();
							if (getDrawable() != null) {
								isCheckLeftAndRight = isCheckTopAndBottom = true;
								// 如果宽度小于屏幕宽度，则禁止左右移动
								if (rectF.width() < getWidth()) {
									dx = 0;
									isCheckLeftAndRight = false;
								}
								// 如果高度小雨屏幕高度，则禁止上下移动
								if (rectF.height() < getHeight()) {
									dy = 0;
									isCheckTopAndBottom = false;
								}
								matrix.postTranslate(dx, dy);
								checkMatrixBounds();
								setImageMatrix(matrix);
							}
						}
						// 在没有移动之前的位置上进行移动
						// matrix.set(currentMatrix);
						// matrix.postTranslate(dx, dy);
					}
					// 放大缩小图片
					else if (mode == MODE_ZOOM) {
						double endDis = distance(event);// 结束距离
						RectF rect = getMatrixRectF();
						// float deltaX = 0;
						// float deltaY = 0;
						//
						// int width = getWidth();
						// int height = getHeight();
						if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
							float scale = (float) endDis / (float) startDis;// 得到缩放倍数
							matrix.set(currentMatrix);
							if (rect.width() < getWidth() && rect.height() < getHeight()) {
								setScaleType(ScaleType.FIT_CENTER);
								mode = 0;
							} else {
								setScaleType(ScaleType.MATRIX);
								matrix.postScale(scale, scale, midPoint.x, midPoint.y);
							}

						}
					}
					break;
				// 手指离开屏幕
				case MotionEvent.ACTION_UP:
					// 当触点离开屏幕，但是屏幕上还有触点(手指)
				case MotionEvent.ACTION_POINTER_UP:
					mode = 0;
					break;
				// 当屏幕上已经有触点(手指)，再有一个触点压下屏幕
				case MotionEvent.ACTION_POINTER_DOWN:
					mode = MODE_ZOOM;
					/** 计算两个手指间的距离 */
					startDis = distance(event);
					/** 计算两个手指间的中间点 */
					if (startDis > 5f) { // 两个手指并拢在一起的时候像素大于10
						midPoint = mid(event);
						// 记录当前ImageView的缩放倍数
						currentMatrix.set(ZoomImage.this.getImageMatrix());
					}
					break;
				}
				ZoomImage.this.setImageMatrix(matrix);
				return true;
			}
		});

	}

	private void checkMatrixBounds() {
		RectF rect = getMatrixRectF();

		float deltaX = 0, deltaY = 0;
		final float viewWidth = getWidth();
		final float viewHeight = getHeight();
		// 判断移动或缩放后，图片显示是否超出屏幕边界
		if (rect.top > 0 && isCheckTopAndBottom) {
			deltaY = -rect.top;
		}
		if (rect.bottom < viewHeight && isCheckTopAndBottom) {
			deltaY = viewHeight - rect.bottom;
		}
		if (rect.left > 0 && isCheckLeftAndRight) {
			deltaX = -rect.left;
		}
		if (rect.right < viewWidth && isCheckLeftAndRight) {
			deltaX = viewWidth - rect.right;
		}
		matrix.postTranslate(deltaX, deltaY);
	}

	/**
	 * 是否是推动行为
	 *
	 * @param dx
	 * @param dy
	 * @return
	 */
	private boolean isCanDrag(float dx, float dy) {
		return Math.sqrt((dx * dx) + (dy * dy)) >= mTouchSlop;
	}

}