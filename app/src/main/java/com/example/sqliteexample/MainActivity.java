package com.example.sqliteexample;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TodosSQLiteHelper db = new TodosSQLiteHelper(this);
		
	    // Προσθήκη εγγραφών
        Log.d("Insert: ", "Inserting .."); 
        db.addToDo(new Todo("Παράδωση εργασίας", "Η εργασία πρέπει να παραδωθεί στο γραφείο Α3"));        
        db.addToDo(new Todo("Συνάντηση", "Συνάντηση για μελέτη"));
        
         
        // Ανάγνωση όλων των to-dos
        Log.d("Reading: ", "Reading.."); 
        List<Todo> todos_list = db.getAllTodos();       
         
        for (Todo cn : todos_list) {
            String log = "Id: "+cn.getID()+" ,Τίτλος: " + cn.getTitle() + " ,Περιγραφή: " + cn.getDescription();
                // Writing Contacts to log
        Log.d("Name: ", log);
    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
