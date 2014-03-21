package it.brunoro.angelo.ticketcoop;

import java.io.File;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Get the intent that started this activity
        Intent intent = getIntent();

        if(intent.getType().equals("application/pdf")){
        	 try{
             File file = new File(intent.getData().getPath());
             Log.i(this.getClass().getName(), "I catch the pdf: ".concat(file.getName()));
        	 }catch(Exception e){
        		 Log.e(this.getClass().getName(), ("Error when reading pdf"));
        	 }
        	 
        }
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
