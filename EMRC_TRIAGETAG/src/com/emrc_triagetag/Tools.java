package com.emrc_triagetag;

import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;

@SuppressLint("TrulyRandom")
public class Tools {
	public static String target(Context c, int x) {
		String msg = "";
		switch (x) {
		case 0:
			msg =  c.getResources().getString( R.string.s_List_23) + " "; // 50,22
			break;
		case 2:
			msg =  c.getResources().getString(R.string.s_List_24) + " "; // 50,34
			break;
		case 4:
			msg =  c.getResources().getString( R.string.s_List_25) + " "; // 50,42.45
			break;
		case 6:
			msg = c.getResources().getString( R.string.s_List_26) + " "; // 42.5,55
			break;
		case 8:
			msg =  c.getResources().getString( R.string.s_List_27) + " "; // 57.5,55
			break;
		case 10:
			msg =  c.getResources().getString( R.string.s_List_28) + " "; // 42.5,68
			break;
		case 12:
			msg = c.getResources().getString(R.string.s_List_29) + " "; // 57.5,68
			break;
		case 14:
			msg =  c.getResources().getString( R.string.s_List_30) + " "; // 32,34.5
			break;
		case 16:
			msg =  c.getResources().getString(R.string.s_List_31) + " "; // 68,34.5
			break;
		case 18:
			msg = c.getResources().getString( R.string.s_List_32) + " "; // 24,40
			break;
		case 20:
			msg =  c.getResources().getString( R.string.s_List_33) + " "; // 76,40
			break;
		case 22:
			msg =  c.getResources().getString( R.string.s_List_34) + " "; // 11,45
			break;
		case 24:
			msg = c.getResources().getString( R.string.s_List_35) + " "; // 89,45
			break;
		case 25:
			msg = c.getResources().getString(R.string.s_List_36) + " "; // 50,34
			break;
		case 26:
			msg = c.getResources().getString( R.string.s_List_37) + " "; // 50,42.45
			break;
		}
		return msg;
	}

	public static void setInjury(ArrayList<Integer> List) {
		int mWidth = MainActivity.metrics.widthPixels; // 螢幕寬
		int mHeight = MainActivity.metrics.heightPixels; // 螢幕長
		for (int j = 0; j < MainActivity.inju_target.size(); j += 2) {
			double dx = MainActivity.inju_target.get(j) * mWidth;
			double dy = MainActivity.inju_target.get(j + 1) * mHeight;
			List.add((int) dx);
			List.add((int) dy);
		}
	}

	public static String getInjury(Context c, double x, double y, int ba) {
		String z = "";
		int mWidth = MainActivity.metrics.widthPixels; // 螢幕寬
		int mHeight = MainActivity.metrics.heightPixels; // 螢幕長
		for (int j = 0; j < MainActivity.inju_target.size(); j += 2) {
			double dx = MainActivity.inju_target.get(j) * mWidth;
			double dy = MainActivity.inju_target.get(j + 1) * mHeight;
			double mx = x * mWidth;
			double my = y * mHeight;
			if (Math.abs((int) dx - mx) < 100 && Math.abs((int) dy - my) < 100) {
				if (ba == 0) {
					z += target(c, j);
					break;
				} else {
					if (j == 2) {
						z += target(c, 25);
						break;
					}
					if (j == 4) {
						z += target(c, 26);
						break;
					}
					if (j == 6) {
						z += target(c, 8);
						break;
					}
					if (j == 8) {
						z += target(c, 6);
						break;
					}
					if (j == 10) {
						z += target(c, 12);
						break;
					}
					if (j == 12) {
						z += target(c, 10);
						break;
					}
					if (j == 14) {
						z += target(c, 16);
						break;
					}
					if (j == 16) {
						z += target(c, 14);
						break;
					}
					if (j == 18) {
						z += target(c, 20);
						break;
					}
					if (j == 20) {
						z += target(c, 18);
						break;
					}
					if (j == 22) {
						z += target(c, 24);
						break;
					}
					if (j == 24) {
						z += target(c, 22);
						break;
					}
				}
			}
		}
		return z;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void getListString(String msg, ArrayList Data, int size) {
		if (msg.length() > 2) {
			int tmp = 0;
			Data.clear();
			if (size != 0) {
				for (int i = 0; i < size; i++) {
					String M;
					if (size == 3 && i == 2) {
						msg += '|';
						M = msg.substring(tmp + i).substring(0, msg.substring(tmp + i).indexOf('|'));
					} else {
						M = msg.substring(tmp + i).substring(0, msg.substring(tmp + i).indexOf('/'));
						tmp += M.length();
					}
					Data.add(M);
				}
			} else {
				int run = Integer.parseInt(msg.substring(tmp).substring(0, msg.substring(tmp).indexOf('=')));
				if (run != 0) {
					if (run != 10) {
						tmp += (run + "").length() + 1;
					}
					for (int i = 0; i < run; i++) {
						String M = msg.substring(tmp + i).substring(0, msg.substring(tmp + i).indexOf('/'));
						tmp += M.length();
						double d = Double.parseDouble(M);
						int mWidth = MainActivity.metrics.widthPixels; // 螢幕寬
						int mHeight = MainActivity.metrics.heightPixels; // 螢幕長
						if (d > 1) {
							int dd = (int) d;
							if (MainActivity.f) {
								d = getPercent(dd, mWidth);
								MainActivity.f = false;
							} else {
								d = getPercent(dd, mHeight);
								MainActivity.f = true;
							}
						}
						Data.add(d);
					}
				}
			}
		}
	}

	private static double getPercent(Integer num, Integer totalPeople) {
		String percent;
		Double p3 = 0.0;
		if (totalPeople == 0) {
			p3 = 0.0;
		} else {
			p3 = num * 0.01 / totalPeople;
		}
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);// 控制保留小数点后几位，2：表示保留2位小数点
		percent = nf.format(p3);
		percent = percent.substring(0, percent.length() - 1);
		return Double.parseDouble(percent);
	}

	public static String setReplace(String s) {
		String p = s;
		p = p.replace("|", " ");
		p = p.replace("║", " ");
		return p;
	}

	@SuppressWarnings("rawtypes")
	public static String getList(ArrayList Data, boolean bl) {
		String tmp = "", rep = "";
		if (Data.size() != 0) {
			for (int i = 0; i < Data.size(); i++) {
				if (bl && i == 2) {
					rep = (Data.get(i) + "").replace("\n", " ");
					tmp += rep;
				} else {
					rep = Data.get(i) + "/";
					tmp += setReplace(rep);
				}

			}
		} else {
			if (!bl) {
				tmp = "n/";
			} else {
				tmp = "//";
			}
		}
		return tmp;
	}

	public static Bitmap getbmp(Context context, Uri uri) {
			return compBitmap(positiveBitmap(new File(getRealPathFromURI(context, uri)).toString()));
	}

	public static void sandFile(Context context, Bitmap bmp) {
		new MainImageOutput(new File(getRealPathFromURI(context, getImageUri(context, bmp)))).start();
	}

	public static Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		int options = 100;
		while (((baos.toByteArray().length / 1024) > 100) && options != 0) { // 100kb
			baos.reset();
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
			if (options > 10) {
				options -= 10;
			} else {
				options--;
			}
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}

	public static Bitmap compBitmap(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 128) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 20, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;// 这里设置高度为800f
		float ww = 480f;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
	}

	// 從給定的路徑載入圖片，並指定是否自動旋轉方向
	public static Bitmap positiveBitmap(String path) {
		Bitmap bp = BitmapFactory.decodeFile(path);
		int digree = 0;
		ExifInterface exif = null;
		try {
			exif = new ExifInterface(path);
		} catch (IOException e) {
			e.printStackTrace();
			exif = null;
		}
		if (exif != null) {
			// 讀取圖片中相機方向資訊
			int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
			// 計算旋轉角度
			switch (ori) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				digree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				digree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				digree = 270;
				break;
			default:
				digree = 0;
				break;
			}
		}
		if (digree != 0) {
			// 旋轉圖片
			Matrix m = new Matrix();
			m.postRotate(digree);
			bp = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), m, true);
		}
		return bp;
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		// 取 drawable 的顏色格式
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}

	public static String getRealPathFromURI(Context inContext, Uri contentUri) { // 取得原始路徑
		String path = null;
		String[] proj = { MediaStore.MediaColumns.DATA };
		Cursor cursor = inContext.getContentResolver().query(contentUri, proj, null, null, null);
		if (cursor.moveToFirst()) {
			int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
			path = cursor.getString(column_index);
		}
		cursor.close();
		return path;
	}

	public static Uri getImageUri(Context inContext, Bitmap inImage) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		String path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
		return Uri.parse(path);
	}
}
