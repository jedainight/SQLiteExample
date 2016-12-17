package com.example.sqliteexample;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodosSQLiteHelper extends SQLiteOpenHelper {
	
	//Δήλωση ονόματος βάσης και έκδοσής της
	private static final String DATABASE_NAME = "todos.db";
	private static final int DATABASE_VERSION = 1;
	
	//Ανάθεση ονόματος του πίνακα σε μεταβλητή
	private static final String TABLE_TODOS = "todos";
	
	// Δήλωση ονομάτων πεδίων του πίνακα todos
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
	
	public TodosSQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Δημιουργία πίνακα με το όνομα todos και πεδία: _ID,title,description
		db.execSQL("CREATE TABLE " + TABLE_TODOS +"(_ID INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,description TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub			
	}
	

    // Μέθοδος για την προσθήκη μιας νέας εγγραφής "to-do"
    void addToDo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, todo.getTitle()); // Εισαγωγή τίτλου to-do
        values.put(KEY_DESCRIPTION, todo.getDescription()); // Εισαγωγή περιγραφής to-do
 
        // Εισαγωγή εγγραφής
        db.insert("todos", null, values);
        db.close(); // Αποσύνδεση με τη βάση
    }
 
    // Ανάγνωση μιας εγγραφής to-do
    public Todo getTodos(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query("todos", new String[] { KEY_ID,
        		KEY_TITLE, KEY_DESCRIPTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Todo todo = new Todo(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // επιστροφή εγγραφής
        return todo;
    }
     
    // Ανάγνωση όλων των υπενθυμίσεων
    public List<Todo> getAllTodos() {
        List<Todo> todosList = new ArrayList<Todo>();
        /* το παρακάτω ερώτημα SQL επιστρέφει όλες τις εγγραφές από τον πίνακα todos
           κάνοντας χρήση του αστερίσκου (*) δηλώνουμε ότι θέλουμε να επιστραφούν όλα τα πεδία του πίνακα.
           Σε αντίθετη περίπτωση θα πρέπει να δηλώσουμε το όνομα του πεδίου που θέλουμε να μας επιστραφεί
           π.χ: "SELECT title FROM todos"  */
        String selectQuery = "SELECT  * FROM " + TABLE_TODOS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // το αντικείμενο Cursor διαβάζει όλες τις εγγραφές που επιστράφηκαν
        if (cursor.moveToFirst()) {
            do {
            	Todo todos = new Todo();
            	todos.setID(Integer.parseInt(cursor.getString(0)));
            	todos.setTitle(cursor.getString(1));
            	todos.setDescription(cursor.getString(2));
                
            	todosList.add(todos);
            } while (cursor.moveToNext());
        }
 
        // επιστροφή όλων των υπενθυμίσεων
        return todosList;
    }
 
    // Επεξεργασία μιας εγγραφής του πίνακα
    public int updateTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, todo.getTitle());
        values.put(KEY_DESCRIPTION, todo.getDescription());
 
        // ενημέρωση εγγραφής
        return db.update(TABLE_TODOS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getID()) });
    }
 
    // Διαγραφή μιας εγγραφής από τον πίνακα
    public void deleteTodo(Todo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODOS, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getID()) });
        db.close();
    }
 
 
    // Εύρεση συνολικού αριθμού εγγραφών του πίνακα
    public int getTodoCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TODOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // επιστροφή αριθμού εγγραφών
        return cursor.getCount();
    }
}