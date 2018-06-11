package com.emrc_triagetag;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class EmrgnActivity extends Activity implements OnClickListener {
	public static ArrayList<View> viewlist = new ArrayList<View>();
	private ArrayList<String> treatment_list_string = new ArrayList<String>();
	private ArrayList<Boolean> treatment_list_click = new ArrayList<Boolean>();
	private ImageView ok;
	private ListView emrgnlist;
	private TextView emrgn_no_top, emrgn_un;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_5_emrgn);
		findViewById();
		setCount();
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

	private void load() {
		String number = MainActivity.number;
		if (number.length() > 2) {
			emrgn_no_top.setText(number);
		}
	}

	private void save() {
		ArrayList<String> tmp_title = new ArrayList<String>();
		ArrayList<String> tmp_string = new ArrayList<String>();
		for (int i = 0; i < treatment_list_click.size(); i++) {
			if (treatment_list_click.get(i)) {
				switch (i) {
				case 0:// --------------------------
					tmp_string.add(getString(R.string.s_EMRGN_00));
					break;
				case 1:
					tmp_string.add(getString(R.string.s_EMRGN_01));
					break;
				case 2:
					tmp_string.add(getString(R.string.s_EMRGN_02));
					break;
				case 3:
					tmp_string.add(getString(R.string.s_EMRGN_03));
					break;
				case 4:
					tmp_string.add(getString(R.string.s_EMRGN_041) + getViewString_0(i) + "L/Min");
					break;
				case 5:
					tmp_string.add(getString(R.string.s_EMRGN_051) + getViewString_0(i) + "L/Min");
					break;
				case 6:
					tmp_string.add(getString(R.string.s_EMRGN_06));
					break;
				case 7:
					tmp_string.add(getString(R.string.s_EMRGN_07));
					break;
				case 8:
					tmp_string.add(getString(R.string.s_EMRGN_08));
					break;
				case 9:
					tmp_string.add(getString(R.string.s_EMRGN_091) + getViewString_0(i) + getViewString_1(i)
							+ getString(R.string.s_ts_52));
					break;
				case 10:
					tmp_string.add(getString(R.string.s_EMRGN_10) + getViewString_0(i) + getViewString_1(i)
							+ getString(R.string.s_ts_52) + "\n fix " + getViewString_1(i) + getViewString_1(i)
							+ getString(R.string.s_ts_53));
					break;
				case 11:
					tmp_string.add(getString(R.string.s_EMRGN_11));
					break;
				case 12:// --------------------------
					tmp_string.add(getString(R.string.s_EMRGN_12));
					break;
				case 13:
					tmp_string.add(getString(R.string.s_EMRGN_13));
					break;
				case 14:
					tmp_string.add(getString(R.string.s_EMRGN_14));
					break;
				case 15:
					tmp_string.add(getString(R.string.s_EMRGN_15));
					break;
				case 16:
					tmp_string.add(getString(R.string.s_EMRGN_16));
					break;
				case 17:
					tmp_string.add(getString(R.string.s_EMRGN_17));
					break;
				case 18:
					tmp_string.add(getString(R.string.s_EMRGN_18));
					break;
				case 19:
					tmp_string.add(getString(R.string.s_EMRGN_19));
					break;
				case 20:// --------------------------
					tmp_string.add(getString(R.string.s_EMRGN_20));
					break;
				case 21:
					tmp_string.add(getString(R.string.s_EMRGN_211) + getViewString_0(i) + getViewString_1(i)
							+ getString(R.string.s_ts_54));
					break;
				case 22:
					tmp_string.add(getString(R.string.s_EMRGN_221) + getViewString_0(i) + getViewString_1(i)
							+ getString(R.string.s_ts_54));
					break;
				case 23:
					tmp_string.add(getString(R.string.s_EMRGN_23));
					break;
				case 24:
					tmp_string.add(getString(R.string.s_EMRGN_24));
					break;
				case 25:
					tmp_string.add(getString(R.string.s_EMRGN_251) + getViewString_0(i) + getViewString_1(i)
							+ getString(R.string.s_ts_55));
					break;
				case 26:
					tmp_string.add(getString(R.string.s_EMRGN_26));
					break;
				case 27:// --------------------------
					tmp_string.add(getString(R.string.s_EMRGN_27));
					break;
				case 28:
					String smg = getString(R.string.s_EMRGN_28) + " ";
					CheckBox cb2 = (CheckBox) viewlist.get(i).findViewById(R.id.cb_style_2);
					CheckBox cb3 = (CheckBox) viewlist.get(i).findViewById(R.id.cb_style_3);
					EditText ed2 = (EditText) viewlist.get(i).findViewById(R.id.ed_style_2);
					EditText ed3 = (EditText) viewlist.get(i).findViewById(R.id.ed_style_3);
					if (cb2.isChecked()) {
						smg += "0.9% NS" + ed2.getText().toString() + " ml";
					}
					if (cb3.isChecked()) {
						smg += "L.R" + ed3.getText().toString() + " ml";
					}
					tmp_string.add(smg);
					break;
				case 29:
					tmp_string.add(getString(R.string.s_EMRGN_29));
					break;
				case 30:
					tmp_string.add(getString(R.string.s_EMRGN_30));
					break;
				case 31:
					tmp_string.add(getString(R.string.s_EMRGN_31) + getViewString_1(i) + getString(R.string.s_ts_55));
					break;
				case 32:// --------------------------
					tmp_string.add(getString(R.string.s_EMRGN_32));
					break;
				case 33:
					tmp_string.add(getString(R.string.s_EMRGN_33));
					break;
				case 34:
					tmp_string.add(getString(R.string.s_EMRGN_34));
					break;
				case 35:
					tmp_string.add(getString(R.string.s_EMRGN_35));
					break;
				case 36:
					tmp_string.add(getString(R.string.s_EMRGN_36));
					break;
				case 37:
					tmp_string.add(getString(R.string.s_EMRGN_37));
					break;
				case 38:
					tmp_string.add(getString(R.string.s_EMRGN_38));
					break;
				}
				if (i < 12) {
					tmp_title.add(getString(R.string.s_EMRGN_00));
				} else if (i >= 12 && i < 20) {
					tmp_title.add(getString(R.string.s_EMRGN_12));
				} else if (i >= 20 && i < 27) {
					tmp_title.add(getString(R.string.s_EMRGN_20));
				} else if (i >= 27 && i < 32) {
					tmp_title.add(getString(R.string.s_EMRGN_27));
				} else {
					tmp_title.add(getString(R.string.s_EMRGN_32));
				}
			}
		}
		getListString(tmp_title, tmp_string);
		tmp_string.clear();
	}

	private void findViewById() {
		emrgnlist = (ListView) findViewById(R.id.emrgnlist);
		emrgn_no_top = (TextView) findViewById(R.id.emrgn_no_top);
		emrgn_un = (TextView) findViewById(R.id.emrgn_un);
		ok = (ImageView) findViewById(R.id.emrgn_check);
		emrgn_un.setOnClickListener(this);
		ok.setOnClickListener(this);
		emrgnlist.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
	}

	private void setCount() {
		treatment_list_string.clear();
		treatment_list_click.clear();
		viewlist.clear();
		/*
		 * 0/ 後面沒資料 1/ 一個Edit 2/ 特殊 3/ 特殊 4/ 特殊 5/ 都沒有 6/ 標題
		 * 
		 */

		// 呼吸道處置
		treatment_list_string.add("6/" + getString(R.string.s_EMRGN_00));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_01));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_02));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_03));
		treatment_list_string.add("1/" + getString(R.string.s_EMRGN_04));
		treatment_list_string.add("1/" + getString(R.string.s_EMRGN_05));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_06));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_07));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_08));
		treatment_list_string.add("1/" + getString(R.string.s_EMRGN_09));
		treatment_list_string.add("2/");
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_11));
		// 空白內容
		// treatment_list_string.add("5/");
		// 創傷處置
		treatment_list_string.add("6/" + getString(R.string.s_EMRGN_12));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_13));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_14));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_15));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_16));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_17));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_18));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_19));
		// 心肺復甦術
		treatment_list_string.add("6/" + getString(R.string.s_EMRGN_20));
		treatment_list_string.add("1/" + getString(R.string.s_EMRGN_21));
		treatment_list_string.add("1/" + getString(R.string.s_EMRGN_22));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_23));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_24));
		treatment_list_string.add("1/" + getString(R.string.s_EMRGN_25));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_26));
		// 藥物處置
		treatment_list_string.add("6/" + getString(R.string.s_EMRGN_27));
		treatment_list_string.add("4/");
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_29));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_30));
		treatment_list_string.add("3/");
		// 其他處置
		treatment_list_string.add("6/" + getString(R.string.s_EMRGN_32));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_33));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_34));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_35));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_36));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_37));
		treatment_list_string.add("0/" + getString(R.string.s_EMRGN_38));
		if (MainActivity.EN) {
			treatment_list_string.add("5/");
			treatment_list_string.add("5/");
		}
		for (int i = 0; i < treatment_list_string.size(); i++) {
			treatment_list_click.add(false);
			setMyAdapter(i);
		}
		emrgnlist.setAdapter(new MyListAdapter(viewlist));
	}

	@SuppressLint("InflateParams")
	private void setMyAdapter(final int position) {
		View vi = LayoutInflater.from(this).inflate(R.layout.style_checkbox, null);
		LinearLayout linearLayout = (LinearLayout) vi.findViewById(R.id.linearLayout);
		TableRow row_0 = (TableRow) vi.findViewById(R.id.row_0);
		TableRow row_1 = (TableRow) vi.findViewById(R.id.row_1);
		TableRow row_2 = (TableRow) vi.findViewById(R.id.row_2);
		TableRow row_3 = (TableRow) vi.findViewById(R.id.row_3);
		final CheckBox cb_style = (CheckBox) vi.findViewById(R.id.cb_style);
		final CheckBox cb_style_2 = (CheckBox) vi.findViewById(R.id.cb_style_2);
		final CheckBox cb_style_3 = (CheckBox) vi.findViewById(R.id.cb_style_3);
		final EditText ed_style = (EditText) vi.findViewById(R.id.ed_style);
		final EditText ed_style_1 = (EditText) vi.findViewById(R.id.ed_style_1);
		final EditText ed_style_2 = (EditText) vi.findViewById(R.id.ed_style_2);
		final EditText ed_style_3 = (EditText) vi.findViewById(R.id.ed_style_3);
		TextView tv_style = (TextView) vi.findViewById(R.id.tv_style);
		TextView tv_style_1 = (TextView) vi.findViewById(R.id.tv_style_1);
		TextView tv_style_1_2 = (TextView) vi.findViewById(R.id.tv_style_1_2);
		TextView tv_style_2 = (TextView) vi.findViewById(R.id.tv_style_2);
		TextView tv_style_3 = (TextView) vi.findViewById(R.id.tv_style_3);
		// showSoftKeyboard(ed_style);
		// showSoftKeyboard(ed_style_1);
		// showSoftKeyboard(ed_style_2);
		// showSoftKeyboard(ed_style_3);
		linearLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (cb_style.isChecked()) {
					cb_style.setChecked(false);
				} else {
					cb_style.setChecked(true);
				}
			}
		});
		cb_style.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				treatment_list_click.set(position, isChecked);
			}
		});
		String msg = treatment_list_string.get(position).toString() + '|';
		int type = Integer.parseInt(msg.substring(0, 1));
		switch (type) {
		case 0:
			cb_style.setText(msg.substring(2).substring(0, msg.substring(2).indexOf('|')));
			if (MainActivity.EN) {
				cb_style.setTextSize(20);
			}
			ed_style.setVisibility(View.GONE);
			tv_style.setVisibility(View.GONE);
			row_1.setVisibility(View.GONE);
			row_2.setVisibility(View.GONE);
			row_3.setVisibility(View.GONE);
			break;
		case 1:
			String m0 = msg.substring(2).substring(0, msg.substring(2).indexOf('|'));
			String m1 = msg.substring(3 + m0.length()).substring(0, msg.substring(3 + m0.length()).indexOf('|'));
			cb_style.setText(m0);
			ed_style.addTextChangedListener(new TextWatcher() {
				@Override
				public void afterTextChanged(Editable s) {
				}

				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start, int before, int count) {
					CheckBox cb = (CheckBox) viewlist.get(position).findViewById(R.id.cb_style);
					if (ed_style.getText().toString().length() > 0) {
						cb.setChecked(true);
					} else {
						cb.setChecked(false);
					}
				}
			});
			tv_style.setText(m1);
			if (MainActivity.EN) {
				cb_style.setTextSize(20);
				ed_style.setTextSize(20);
				tv_style.setTextSize(20);
			}
			row_1.setVisibility(View.GONE);
			row_2.setVisibility(View.GONE);
			row_3.setVisibility(View.GONE);
			break;
		case 2:
			// TODO 呼吸道處置 氣管內管..
			cb_style.setText(getString(R.string.s_EMRGN_10));
			ed_style.setFilters(new InputFilter[] { new InputFilter.LengthFilter(3) });
			ed_style.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
			ed_style.addTextChangedListener(new TextWatcher() {
				@Override
				public void afterTextChanged(Editable s) {
				}

				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start, int before, int count) {
					CheckBox cb = (CheckBox) viewlist.get(position).findViewById(R.id.cb_style);
					if (ed_style.getText().toString().length() > 0) {
						cb.setChecked(true);
					} else {
						cb.setChecked(false);
					}
				}
			});
			ed_style_1.addTextChangedListener(new TextWatcher() {
				@Override
				public void afterTextChanged(Editable s) {
				}

				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start, int before, int count) {
					CheckBox cb = (CheckBox) viewlist.get(position).findViewById(R.id.cb_style);
					if (ed_style_1.getText().toString().length() > 0) {
						cb.setChecked(true);
					} else {
						cb.setChecked(false);
					}
				}
			});
			if (MainActivity.EN) {
				cb_style.setTextSize(20);
				ed_style.setTextSize(20);
				ed_style_1.setTextSize(20);
				tv_style_1.setTextSize(20);
				tv_style_1_2.setTextSize(20);
			}
			row_2.setVisibility(View.GONE);
			row_3.setVisibility(View.GONE);
			break;
		case 3:
			// TODO 藥物處置 建議使用..
			cb_style.setText(getString(R.string.s_EMRGN_31));
			ed_style.setVisibility(View.GONE);
			tv_style.setVisibility(View.GONE);
			ed_style_1.addTextChangedListener(new TextWatcher() {
				public void afterTextChanged(Editable s) {
				}

				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start, int before, int count) {
					CheckBox cb = (CheckBox) viewlist.get(position).findViewById(R.id.cb_style);
					if (ed_style_1.getText().toString().length() > 0) {
						cb.setChecked(true);
					} else {
						cb.setChecked(false);
					}
				}
			});
			tv_style_1.setText("");
			tv_style_1_2.setText(getString(R.string.s_ts_55));
			if (MainActivity.EN) {
				cb_style.setTextSize(20);
				tv_style_1.setTextSize(20);
				tv_style_1_2.setTextSize(20);
				ed_style_1.setTextSize(20);
			}
			row_2.setVisibility(View.GONE);
			row_3.setVisibility(View.GONE);
			break;
		case 4:
			// TODO 藥物處置 靜脈輸液..
			TextWatcher ed2 = new TextWatcher() {
				int ssf = 0;

				public void afterTextChanged(Editable s) {
					ssf = ed_style_2.getText().toString().length();
				}

				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start, int before, int count) {
					CheckBox cb = (CheckBox) viewlist.get(position).findViewById(R.id.cb_style);
					CheckBox cb2 = (CheckBox) viewlist.get(position).findViewById(R.id.cb_style_2);
					if (ssf > 0) {
						if (ed_style_2.getText().toString().length() > 0) {
							cb.setChecked(true);
							cb2.setChecked(true);
						} else {
							cb2.setChecked(false);
						}
					}
				}
			};
			TextWatcher ed3 = new TextWatcher() {
				int ssf = 0;

				public void afterTextChanged(Editable s) {
					ssf = ed_style_2.getText().toString().length();
				}

				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				}

				public void onTextChanged(CharSequence s, int start, int before, int count) {
					CheckBox cb = (CheckBox) viewlist.get(position).findViewById(R.id.cb_style);
					CheckBox cb3 = (CheckBox) viewlist.get(position).findViewById(R.id.cb_style_3);
					if (ssf > 0) {
						if (ed_style_3.getText().toString().length() > 0) {
							cb.setChecked(true);
							cb3.setChecked(true);
						} else {
							cb3.setChecked(false);
						}
					}
				}
			};
			cb_style.setText(getString(R.string.s_EMRGN_28));
			ed_style.setVisibility(View.GONE);
			ed_style_2.addTextChangedListener(ed2);
			ed_style_3.addTextChangedListener(ed3);
			if (MainActivity.EN) {
				cb_style.setTextSize(20);
				cb_style_2.setTextSize(20);
				cb_style_3.setTextSize(20);
				ed_style_2.setTextSize(20);
				ed_style_3.setTextSize(20);
				tv_style_2.setTextSize(20);
				tv_style_3.setTextSize(20);
			}
			tv_style.setVisibility(View.GONE);
			row_1.setVisibility(View.GONE);
			break;
		case 5:
			// null
			row_0.setVisibility(View.INVISIBLE);
			row_1.setVisibility(View.GONE);
			row_2.setVisibility(View.GONE);
			row_3.setVisibility(View.GONE);
			if (MainActivity.EN) {
				cb_style.setTextSize(20);
			}
			break;
		case 6:
			// title
			row_0.setVisibility(View.GONE);
			tv_style_1.setVisibility(View.GONE);
			ed_style_1.setVisibility(View.GONE);
			tv_style_1_2.setText(msg.substring(2).substring(0, msg.substring(2).indexOf('|')));
			tv_style_1_2.setTextSize(40);
			if (MainActivity.EN) {
				tv_style_1_2.setTextSize(30);
			}
			tv_style_1_2.setTextColor(Color.DKGRAY);
			row_2.setVisibility(View.GONE);
			row_3.setVisibility(View.GONE);
			break;
		}
		viewlist.add(vi);
	}

	private void getListString(ArrayList<String> T, ArrayList<String> S) {
		if (S.size() > 0) {
			boolean data = false;
			String name, age, other;
			if (MainActivity.info_data.size() > 0) {
				name = MainActivity.info_data.get(0);
				age = MainActivity.info_data.get(1);
				other = MainActivity.info_data.get(2);
				MainActivity.info_data.clear();
				MainActivity.info_data.add(name);
				MainActivity.info_data.add(age);
				data = true;
			} else {
				other = "";
				MainActivity.info_data.add("");
				MainActivity.info_data.add("");
			}
			for (int i = 0; i < S.size(); i++) {
				if (i == 0) {
					if (data) {
						if(other.length()==0){
							other += "\n";
						}
						other += "(" + S.get(i) + ".";
					} else {
						other += "(" + S.get(i) + ".";
					}
				} else {
					other += " " + S.get(i) + ".";
				}
			}
			other += ")";
			MainActivity.info_data.add(other);
			Toast.makeText(this, getString(R.string.s_ts_51), Toast.LENGTH_LONG).show();
		}
	}

	private String getViewString_0(int i) {
		EditText ed_style = (EditText) viewlist.get(i).findViewById(R.id.ed_style);
		return ed_style.getText().toString();
	}

	private String getViewString_1(int i) {
		EditText ed_style = (EditText) viewlist.get(i).findViewById(R.id.ed_style_1);
		return ed_style.getText().toString();
	}

	public void onClick(View v) {
		if (v == ok) {
			save();
			finish();
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			save();
			finish();
		}
		return false;
	}

	@SuppressWarnings("static-access")
	public void showSoftKeyboard(View view) {
		if (view.requestFocus()) {
			InputMethodManager imm = (InputMethodManager) this.getSystemService(this.INPUT_METHOD_SERVICE);
			imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
		}
	}
}
