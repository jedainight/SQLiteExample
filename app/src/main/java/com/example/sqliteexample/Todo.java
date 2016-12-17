package com.example.sqliteexample;

public class Todo {
    
    //private variables
    int _id;
    String _title;
    String _description;
     
    // Empty constructor
    public Todo(){
         
    }
    
    // constructor
    public Todo(int id, String title, String _description){
        this._id = id;
        this._title = title;
        this._description = _description;
    }
     
    // constructor
    public Todo(String title, String _description){
        this._title = title;
        this._description = _description;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public String getTitle(){
        return this._title;
    }
     
    // setting name
    public void setTitle(String title){
        this._title = title;
    }
     
    // getting phone number
    public String getDescription(){
        return this._description;
    }
     
    // setting phone number
    public void setDescription(String description){
        this._description = description;
    }
}