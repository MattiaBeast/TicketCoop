package it.brunoro.angelo.ticketcoop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

// Static class
public class Logic {
	 
  public static void workFlow(Context context, File pdf){
	  if(pdf != null){
	    File excel = saveExcelFile(context, pdf);
	    if(excel != null){
	      startExcelIntent(context, excel);
	    }else
		  Util.toastMessage(context, "Invalid Excel File! Parsing failure!");	
	  }else
	    Util.toastMessage(context, "Invalid PDF File! Please take a valid PDF");
  }
  
  // Save Excel File
  private static File saveExcelFile(Context context, File pdf) { 
        
    // Check if ExtenalStorage is avaliable
    if (!Util.isExternalStorageWritable(context)) { 
    	Log.e(context.getClass().getName(), ("Cannot write into the external storage!"));
        return null;
    } 
 
    boolean success = false; 
 
    // Logic for parsing
    Workbook wb = pdfToExcel(pdf);
 
    // Create a path where we will place our List of objects on external storage 
    File file = new File(pdf.getParent(), pdf.getName().replace(".pdf", ".xls")); 
    FileOutputStream os = null; 
 
    try { 
      os = new FileOutputStream(file);
      wb.write(os);
      Log.i("FileUtils", "Writing file" + file); 
      success = true; 
    } catch (IOException e) { 
      Log.w("FileUtils", "Error writing " + file, e); 
      Util.toastMessage(context, "Error writing " + file);
    } catch (Exception e) { 
      Log.w("FileUtils", "Failed to save file", e); 
      Util.toastMessage(context, "Failed to save file " + file);
    } finally { 
      try { 
        if (null != os) 
          os.close(); 
        } catch (Exception ex) { } 
    } 
    return file; 
  }
  
  // Method for get Pdf by intent
  public static File getPdfByIntent(Context context, Intent intent){
	File pdf = null;
 	try{
      pdf = new File(intent.getData().getPath());
      Log.i(context.getClass().getName(), "I catch the pdf: ".concat(pdf.getName()));
 	}catch(Exception e){
 	  Log.e(context.getClass().getName(), ("Error when reading pdf"));
      Util.toastMessage(context, "Error when reading pdf");
 	}
	return pdf;
  }
  
  // Method for get Pdf by url
  public static File getPdfByUrl(Context context, Intent intent){
	File pdf = null;
	Uri uri = intent.getData();

    if (uri.getScheme().toString().compareTo("content")==0){      
      Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
         if (cursor.moveToFirst()){
             int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
             Uri filePathUri = Uri.parse(cursor.getString(column_index));
             String file_path=filePathUri.getPath();
             if(file_path.contains(".pdf"))
               pdf = new File(file_path);
         }
     }
	return pdf;
 }
  
  // Method for read Excel by intent
  private static void startExcelIntent(Context context, File excel){
	  
  	Intent intent = new Intent(Intent.ACTION_VIEW);
  	intent.setDataAndType(Uri.fromFile(excel), "application/vnd.ms-excel");
  	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

  	try {
  	    context.startActivity(intent);
  	} 
  	catch (ActivityNotFoundException e) {
      Util.toastMessage(context, "No Application Available to View Excel");
  	}
  }
  
  public static boolean isIntentForPdf(Intent intent){
	  if(("application/pdf").equals(intent.getType()))
	    return true;
	  
	  return false;
  }
  
  public static Intent pickButton(View view){
	  Intent intent = new Intent();
      intent.setType("application/pdf");
      intent.setAction(Intent.ACTION_GET_CONTENT);
      intent.addCategory(Intent.CATEGORY_OPENABLE);
      return intent;
  }
  
  private static Workbook pdfToExcel(File pdf){
	// TODO Create a custom Excel, for now is implemented a simple example
	//New Workbook
	Workbook wb = new HSSFWorkbook();
	 
	Cell c = null;
	 
	//Cell style for header row
	CellStyle cs = wb.createCellStyle();
	cs.setFillForegroundColor(HSSFColor.LIME.index);
	cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    
	//New Sheet
	Sheet sheet1 = null;
	sheet1 = wb.createSheet("myOrder");
	 
	// Generate column headings
	Row row = sheet1.createRow(0);
	 
	c = row.createCell(0);
	c.setCellValue("Item Number");
	c.setCellStyle(cs);
	 
	c = row.createCell(1);
	c.setCellValue("Quantity");
	c.setCellStyle(cs);
	 
	c = row.createCell(2);
	c.setCellValue("Price");
	c.setCellStyle(cs);
	 
	sheet1.setColumnWidth(0, (15 * 500));
	sheet1.setColumnWidth(1, (15 * 500));
	sheet1.setColumnWidth(2, (15 * 500));
	
	return wb;
  }
}
