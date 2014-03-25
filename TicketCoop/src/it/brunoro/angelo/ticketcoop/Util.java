package it.brunoro.angelo.ticketcoop;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class Util {

  // Checks if external storage is available for read and write 
  public static boolean isExternalStorageWritable(Context context) {
    String state = Environment.getExternalStorageState();
	if (Environment.MEDIA_MOUNTED.equals(state)) {
	  return true;
	}
	toastMessage(context, "External storage is unavailable");
    return false;
  }
  
  // Show a toast message linked to the context
  public static void toastMessage(Context context, String message){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
  }
}
