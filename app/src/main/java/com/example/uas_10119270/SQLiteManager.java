package com.example.uas_10119270;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//10119270
//Albanna Rahmadani Sulistyo
//IF-7

public class SQLiteManager extends SQLiteOpenHelper {

    public static  SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "NoteDB";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "Note";
    private static final String COUNTER = "Counter";

    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "judul";
    private static final String DESC_FIELD = "catatan";
    private static final String MAKE_FIELD = "make";
    private static final String DELETED_FIELD = "deleted";

    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager DBInstance (Context context) {
        if (sqLiteManager==null)
            sqLiteManager=new SQLiteManager(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(DATABASE_TABLE)
                .append(" (")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(TITLE_FIELD)
                .append(" TEXT, ")
                .append(DESC_FIELD)
                .append(" TEXT, ")
                .append(MAKE_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");
        sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
//        switch (oldVer) {
//            case 1 :
//                sqLiteDatabase.execSQL("ALTER TABLE "+DATABASE_TABLE+" ADD COLUMN "+NEW_COLUMN+" TEXT");
//            case 2 :
//                sqLiteDatabase.execSQL("ALTER TABLE "+DATABASE_TABLE+" ADD COLUMN "+NEW_COLUMN+" TEXT");
//        }
    }

    public void addNoteToDB(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getId());
        contentValues.put(TITLE_FIELD, note.getJudul());
        contentValues.put(DESC_FIELD, note.getCatatan());
        contentValues.put(MAKE_FIELD, getStringFromDate(note.getMake()));
        contentValues.put(DELETED_FIELD, getStringFromDate(note.getDeleted()));

        sqLiteDatabase.insert(DATABASE_TABLE, null, contentValues);
    }

    public void updateNoteInDB(Note note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, note.getId());
        contentValues.put(TITLE_FIELD, note.getJudul());
        contentValues.put(DESC_FIELD, note.getCatatan());
        contentValues.put(MAKE_FIELD, getStringFromDate(note.getMake()));
        contentValues.put(DELETED_FIELD, getStringFromDate(note.getDeleted()));

        sqLiteDatabase.update(DATABASE_TABLE, contentValues, ID_FIELD+" =? ", new String[]{String.valueOf(note.getId())});
    }

    public void populateNoteListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE, null)) {
            if (result.getCount() != 0){
                while (result.moveToNext()){
                    int id = result.getInt(1);
                    String judul = result.getString(2);
                    String catatan = result.getString(3);
                    String stringMake = result.getString(4);
                    String stringDeleted = result.getString(5);
                    Date make = getDateFromString(stringMake);
                    Date deleted = getDateFromString(stringDeleted);
                    Note note = new Note(id, judul, catatan, make, deleted);
                    Note.noteArrayList.add(note);
                }
            }
        }
    }

    private String getStringFromDate(Date date) {
        if (date==null)
            return null;
        return dateFormat.format(date);
    }
    private Date getDateFromString(String string) {
        try {
            return dateFormat.parse(string);
        } catch (ParseException | NullPointerException e) {
            return null;
        }
    }
}
