package it.brunoro.angelo.ticketcoop;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity{

  private final int PICKPDF_RESULT_CODE = 10110;
	
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
        
    // Get the intent that started this activity
    Intent intent = getIntent();
    if(Logic.isIntentForPdf(intent))
      Logic.workFlow(this, Logic.getPdfByIntent(this, intent));
  }
    
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
      return true;
  }
    
  protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    switch(requestCode){
      case PICKPDF_RESULT_CODE:
        if(resultCode == RESULT_OK){
    	  Logic.workFlow(this, Logic.getPdfByUrl(this, intent));
    	}
      break; 
    }
  }
   
  // Call by button
  public void pickPdf(View view) {
    startActivityForResult(Logic.pickButton(view), PICKPDF_RESULT_CODE);
  }  
}
