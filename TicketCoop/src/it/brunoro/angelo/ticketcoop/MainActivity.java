package it.brunoro.angelo.ticketcoop;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onStart(){
    	// Do something
    	
    	/*///code for open an Excel with external app
    	Intent intent = new Intent(Intent.ACTION_VIEW);
    	File file = new File("/sdcard/download/prova.xlsx");
    	intent.setDataAndType(Uri.fromFile(file),"application/vnd.ms-excel");
    	startActivity(intent); 
    	*/
    	
    	// Recall parent
    	super.onStart();
    }
    
}
