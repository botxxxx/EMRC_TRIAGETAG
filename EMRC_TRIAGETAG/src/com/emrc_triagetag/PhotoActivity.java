package com.emrc_triagetag;

import android.app.*;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class PhotoActivity extends Activity implements OnClickListener {
	private Uri image_uri;
	private LinearLayout item_1, item_2;
	private TextView item_0_bl, item_1_bl, item_2_bl, phot_no_top, phot_no_down;
	private static int count, select;
	public static boolean i00 = false, i01 = false, i10 = false;
	public static boolean i11 = false, i20 = false, i21 = false;
	public static ImageView ok, item_0_img_0, item_0_img_1;
	public static ImageView item_1_img_0, item_1_img_1, item_2_img_0, item_2_img_1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_4_phot);
		findViewById();
		reset();
		load();
	}

	protected void onStart() {
		super.onStart();
	}

	protected void onPause() {
		super.onPause();
	}

	protected void onResume() {
		super.onResume();
	}

	protected void onDestroy() {
		super.onDestroy();
	}

	@SuppressWarnings("deprecation")
	private void drawableBottom(TextView v, Boolean b) {
		Drawable drawable = getResources().getDrawable(R.drawable.gray_baseline2);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		if (b) {
			v.setCompoundDrawables(null, null, null, drawable);
		} else {
			v.setCompoundDrawables(null, null, null, null);
		}
	}

	private void reset() {
		item_1.setVisibility(View.GONE);
		item_2.setVisibility(View.GONE);
		drawableBottom(item_0_bl, true);
		item_0_img_0.setOnClickListener(this);
		item_0_img_1.setOnClickListener(this);
		item_1_img_0.setOnClickListener(this);
		item_1_img_1.setOnClickListener(this);
		item_2_img_0.setOnClickListener(this);
		item_2_img_1.setOnClickListener(this);
		ok.setOnClickListener(this);
	}

	private void findViewById() {
		phot_no_top = (TextView) findViewById(R.id.phot_no_top);
		phot_no_down = (TextView) findViewById(R.id.phot_no_down);
		item_1 = (LinearLayout) findViewById(R.id.phot_item_1);
		item_2 = (LinearLayout) findViewById(R.id.phot_item_2);
		item_0_bl = (TextView) findViewById(R.id.phot_item_0_bl);
		item_1_bl = (TextView) findViewById(R.id.phot_item_1_bl);
		item_2_bl = (TextView) findViewById(R.id.phot_item_2_bl);
		item_0_img_0 = (ImageView) findViewById(R.id.phot_item_0_img_0);
		item_0_img_1 = (ImageView) findViewById(R.id.phot_item_0_img_1);
		item_1_img_0 = (ImageView) findViewById(R.id.phot_item_1_img_0);
		item_1_img_1 = (ImageView) findViewById(R.id.phot_item_1_img_1);
		item_2_img_0 = (ImageView) findViewById(R.id.phot_item_2_img_0);
		item_2_img_1 = (ImageView) findViewById(R.id.phot_item_2_img_1);
		ok = (ImageView) findViewById(R.id.phot_check);
	}

	private void Dialog(boolean view) {
		int i = 0;
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		if (view) {
			i++;
			dialog.setNeutralButton(getString( R.string.s_ts_46), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					startActivity(new Intent(PhotoActivity.this, PhotoViewActivity.class));
				}
			});
		}
		if (Build.VERSION.SDK_INT >= 23) {
			i++;
			dialog.setPositiveButton(getString( R.string.s_ts_47), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					Intent intent_photo = new Intent(Intent.ACTION_PICK, null);
					intent_photo.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
					startActivityForResult(intent_photo, 0);
				}
			});
			dialog.setNegativeButton(getString( R.string.s_ts_48), new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					Intent intent_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// Ensure that there's a camera activity to handle the
					// intent
					if (intent_camera.resolveActivity(getPackageManager()) != null) {
						image_uri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());
						intent_camera.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
						startActivityForResult(intent_camera, 1);
					} else {
						MainActivity.toast("Camera not available", PhotoActivity.this);
					}
				}
			});
		} else {
			MainActivity.toast("Not in Service", this);
		}
		if (i != 0) {
			dialog.show();
		}
	}

	public void onClick(View v) {
		if (v == item_0_img_0) {
			Dialog(i00);
			select = 0;
		}
		if (v == item_0_img_1) {
			Dialog(i01);
			select = 1;
		}
		if (v == item_1_img_0) {
			Dialog(i10);
			select = 2;
		}
		if (v == item_1_img_1) {
			Dialog(i11);
			select = 3;
		}
		if (v == item_2_img_0) {
			Dialog(i20);
			select = 4;
		}
		if (v == item_2_img_1) {
			Dialog(i21);
			select = 5;
		}
		if (v == ok) {
			save();
			finish();
		}
	}

	private void load() {
		count = MainActivity.phot_count;
		if (count > 0) {
			int tmp = count;
			int max = MainActivity.phot_bitmap.size();
			if (count >= 3) {
				drawableBottom(item_0_bl, false);
				drawableBottom(item_1_bl, true);
				item_1.setVisibility(View.VISIBLE);
			}
			if (count >= 15) {
				drawableBottom(item_1_bl, false);
				drawableBottom(item_2_bl, true);
				item_2.setVisibility(View.VISIBLE);
			}
			while (tmp != 0) {
				if (tmp - 32 >= 0) {
					tmp -= 32;
					i21 = true;
					item_2_img_1.setImageBitmap(MainActivity.phot_bitmap.get(max - 1));
					max--;
				} else if (tmp - 16 >= 0) {
					tmp -= 16;
					i20 = true;
					item_2_img_0.setImageBitmap(MainActivity.phot_bitmap.get(max - 1));
					max--;
				} else if (tmp - 8 >= 0) {
					tmp -= 8;
					i11 = true;
					item_1_img_1.setImageBitmap(MainActivity.phot_bitmap.get(max - 1));
					max--;
				} else if (tmp - 4 >= 0) {
					tmp -= 4;
					i10 = true;
					item_1_img_0.setImageBitmap(MainActivity.phot_bitmap.get(max - 1));
					max--;
				} else if (tmp - 2 >= 0) {
					tmp -= 2;
					i01 = true;
					item_0_img_1.setImageBitmap(MainActivity.phot_bitmap.get(max - 1));
					max--;
				} else {
					tmp--;
					i00 = true;
					item_0_img_0.setImageBitmap(MainActivity.phot_bitmap.get(max - 1));
					max--;
				}
			}
		}
		String number = MainActivity.number;
		if (number.length() > 2) {
			phot_no_top.setText(number);
			phot_no_down.setText(number);
		}
	}

	private void save() {
		if (i00) {
			MainActivity.phot_bitmap.add(Tools.drawableToBitmap(item_0_img_0.getDrawable()));
		}
		if (i01) {
			MainActivity.phot_bitmap.add(Tools.drawableToBitmap(item_0_img_1.getDrawable()));
		}
		if (i10) {
			MainActivity.phot_bitmap.add(Tools.drawableToBitmap(item_1_img_0.getDrawable()));
		}
		if (i11) {
			MainActivity.phot_bitmap.add(Tools.drawableToBitmap(item_1_img_1.getDrawable()));
		}
		if (i20) {
			MainActivity.phot_bitmap.add(Tools.drawableToBitmap(item_2_img_0.getDrawable()));
		}
		if (i21) {
			MainActivity.phot_bitmap.add(Tools.drawableToBitmap(item_2_img_1.getDrawable()));
		}
		MainActivity.phot_count = count;
		if (count > 0) {
			int cc = count, set = 0;
			while (cc != 0) {
				if (cc - 32 >= 0) {
					cc -= 32;
					set++;
				} else if (cc - 16 >= 0) {
					cc -= 16;
					set++;
				} else if (cc - 8 >= 0) {
					cc -= 8;
					set++;
				} else if (cc - 4 >= 0) {
					cc -= 4;
					set++;
				} else if (cc - 2 >= 0) {
					cc -= 2;
					set++;
				} else {
					cc--;
					set++;
				}
			}
			MainActivity.menu_item.set(5, getString( R.string.s_Menu_6) + "\n<"
					+ getString( R.string.s_List_3) + set + getString( R.string.s_List_4) + ">");
			MainActivity.menu_upload(this);
		}
	}

	private void addview(Bitmap bmps) {
		boolean add = false;
		switch (select) {
		case 0:
			item_0_img_0.setImageBitmap(bmps);
			if (!i00) {
				i00 = true; // 可否檢視
				add = true; // 是否新增照片
				count++;
			}
			break;
		case 1:
			item_0_img_1.setImageBitmap(bmps);
			if (!i01) {
				i01 = true;
				add = true;
				count += 2;
			}
			break;
		case 2:
			item_1_img_0.setImageBitmap(bmps);
			if (!i10) {
				i10 = true;
				add = true;
				count += 4;
			}
			break;
		case 3:
			item_1_img_1.setImageBitmap(bmps);
			if (!i11) {
				i11 = true;
				add = true;
				count += 8;
			}
			break;
		case 4:
			item_2_img_0.setImageBitmap(bmps);
			if (!i20) {
				i20 = true;
				add = true;
				count += 16;
			}
			break;
		case 5:
			item_2_img_1.setImageBitmap(bmps);
			if (!i21) {
				i21 = true;
				add = true;
				count += 32;
			}
			break;
		}

		if (add) {
			if (count == 3) {
				drawableBottom(item_0_bl, false);
				drawableBottom(item_1_bl, true);
				item_1.setVisibility(View.VISIBLE);
			}
			if (count == 15) {
				drawableBottom(item_1_bl, false);
				drawableBottom(item_2_bl, true);
				item_2.setVisibility(View.VISIBLE);
			}
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			String mark = "";
			switch (select) {
			case 0:
				mark = "b1";
				break;
			case 1:
				mark = "b2";
				break;
			case 2:
				mark = "b3";
				break;
			case 3:
				mark = "b4";
				break;
			case 4:
				mark = "b5";
				break;
			case 5:
				mark = "b6";
				break;
			}
			if (MainActivity.link) {
				MainActivity.out(mark);
			}
			Bitmap bmps = null;
			// 6+版本需手動開啟權限
			switch (requestCode) {
			case 0:
				bmps = Tools.getbmp(this, data.getData());
				break;
			case 1:
				bmps = Tools.getbmp(this, image_uri);
				break;
			}
			if (MainActivity.link) {
				Tools.sandFile(this, bmps);
			} else {
				Toast.makeText(this, getString( R.string.s_ts_38), Toast.LENGTH_SHORT).show();
			}
			addview(bmps);
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			save();
			finish();
		}
		return false;
	}

	public static int getCount() {
		return count;
	}

	public static int getSelect() {
		return select;
	}

}