package com.vqsxb.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * 根据资源的名字获取其ID值
 * @author lx
 */
public class ViewUtil {
    public static View getView(Context context, ViewGroup parent,int layoutID){
       return LayoutInflater.from(context).inflate(layoutID, parent,false);
    }
	@SuppressWarnings("unchecked")
	public static <T> T find(Activity activity, int resID) {
		T view = (T) activity.findViewById(resID);
		return view;
	}
	@SuppressWarnings("unchecked")
	public static <T> T find(View view, int resID) {
		T v = (T) view.findViewById(resID);
		return v;
	}

	@SuppressWarnings("unchecked")
	public static <T> T find(Activity activity, String className, String name) {
		T v = (T) activity.findViewById(getIdByName(activity, className, name));
		return v;
	}

	@SuppressWarnings("unchecked")
	public static <T> T find(View view, String className, String name) {
		T v = (T) view.findViewById(getIdByName(view.getContext(), className, name));
		return v;
	}
	@SuppressWarnings("rawtypes")
	public static int getIdByName(Context context, String className, String name) {
		String packageName = context.getPackageName();
		Class r = null;
		int id = 0;
		try {
			r = Class.forName(packageName + ".R");
			Class[] classes = r.getClasses();
			Class desireClass = null;
			for (int i = 0; i < classes.length; ++i) {
				if (classes[i].getName().split("\\$")[1].equals(className)) {
					desireClass = classes[i];
					break;
				}
			}
			if (desireClass != null)
				id = desireClass.getField(name).getInt(desireClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		return id;
	}

	@SuppressWarnings("rawtypes")
	public static int[] getIdsByName(Context context, String className, String name) {
		String packageName = context.getPackageName();
		Class r = null;
		int[] ids = null;
		try {
			r = Class.forName(packageName + ".R");
			Class[] classes = r.getClasses();
			Class desireClass = null;
			for (int i = 0; i < classes.length; ++i) {
				if (classes[i].getName().split("\\$")[1].equals(className)) {
					desireClass = classes[i];
					break;
				}
			}
			if ((desireClass != null) && (desireClass.getField(name).get(desireClass) != null) && (desireClass.getField(name).get(desireClass).getClass().isArray()))
				ids = (int[]) desireClass.getField(name).get(desireClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return ids;
	}

	public static void setTextData(TextView tv, Object data) {
		if (tv != null) {
			String value = String.valueOf(data != null ? data : "");
			if (!tv.getText().equals(value)) {
				tv.setText(String.valueOf(data != null ? data : ""));
			}
		}
	}
	public static void setIntTextData(TextView tv, int data) {
		setTextData(tv, String.valueOf(data));
	}

	public static void setTextData(TextView tv, int res, Object data) {
		if (tv != null && res != 0) {
			setTextData(tv, tv.getResources().getString(res, data != null ? data : ""));
		}
	}

	public static void setTextData(TextView tv, int res) {
		if (tv != null && res != 0) {
			setTextData(tv, tv.getResources().getString(res));
		}
	}


	public static void setAlpha(View v, float i) {
		try {
			if (Build.VERSION.SDK_INT >= 11) {
				v.setAlpha(i);

			} else {
				v.getBackground().setAlpha((int) i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void setVisibility(int visibility, View view) {
		if (view != null) {
			if (view.getVisibility() != visibility) {
				view.setVisibility(visibility);
			}
		}
	}

	public static void setVisibility(int visibility, View... views) {
		if (views != null) {
			for (int i = 0, len = views.length; i < len; i++) {
				View v = views[i];
				if (v != null && v.getVisibility() != visibility) {
					v.setVisibility(visibility);
				}
			}
		}
	}

	public static void setVisibility(int[] visibilitys, View... views) {
		if (views != null) {
			for (int i = 0, len = views.length; i < len; i++) {
				View v = views[i];
				if (v != null && v.getVisibility() != visibilitys[i]) {
					v.setVisibility(visibilitys[i]);
				}
			}
		}
	}
    public static <T> T getLayout(Context context, int resID) {
        T layout = (T) LayoutInflater.from(context).inflate(resID, null);
        return layout;
    }
}
