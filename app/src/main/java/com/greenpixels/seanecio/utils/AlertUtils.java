package com.greenpixels.seanecio.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.greenpixels.seanecio.R;

/**
 * Util to show alerts and toaster.
 */

public class AlertUtils {

	/**Toast dialog with R.String */
	public static void showToast(final int messageRes, final Activity activity) {
		showToast(activity.getString(messageRes),activity);
	}
	
	/**Toast dialog with string */
	public static void showToast(final String message,final Activity activity) {
		final Toast toast = Toast.makeText(activity, message, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	}

	/**Error dialog with R.String */
	public static void showErrorDialog(final int title,final int messageRes,final Activity activity) {


		showErrorDialog(activity.getString(title), activity.getString(messageRes), activity);
	}

	/**Error dialog with String */
	public static void showErrorDialog(final String title,final String message,final Activity activity) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message);
        builder.setTitle(title);
//        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton(activity.getString(R.string.accept), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

	}

	/**Info dialog with R.String */
	public static void showInfoDialog(final int title, final int message, final Activity activity) {
		showInfoDialog(activity.getString(title), activity.getString(message), activity);
	}
	
	/**Error dialog with String */
	public static void showInfoDialog(final String title, final String message, final Activity activity ) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(message);
		builder.setTitle(title);
//		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setPositiveButton(activity.getString(R.string.accept), new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	            
	        }
	    });
		builder.create().show();
	}


	public static void showSnackbar(View view, String text){
		Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
//				.setAction(R.string.snackbar_action_undo, clickListener)
				.show();
	}

}
