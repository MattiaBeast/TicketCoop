package it.brunoro.angelo.ticketcoop;
import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private final int PICKFILE_RESULT_CODE = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Get the intent that started this activity
        Intent intent = getIntent();
        File pdf = Logic.getPdf(this, intent);
        Logic.workFlow(this, pdf);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onStart(){
    	
    	super.onStart();
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	  // TODO WORK IN PROGESS
    	  switch(requestCode){
    	  case PICKFILE_RESULT_CODE:
    	   if(resultCode == RESULT_OK){
    		// Get the intent that started this activity
    	       
    	     if(("application/pdf").equals(data.getType())){
    		   try{
    	        File pdf = new File(data.getData().getPath());
    		    Logic.workFlow(this, pdf); 
    	       }catch(Exception e){
    	    	    Log.e(this.getClass().getName(), ("Error when reading pdf"));
    	            Util.toastMessage(this, "Error when reading pdf");
    	       }
    	     }
    	   }
    	   break; 
      }
    }
    // Call by button
    public void pickPdf(View view) {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, PICKFILE_RESULT_CODE);
    }
}
