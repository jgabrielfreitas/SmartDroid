package br.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.database.AlarmContract.*;

/**
 * Created by JGabrielFreitas on 10/11/2014.
 */

public class AlarmDBHelper extends SQLiteOpenHelper {

    public static final int    DATABASE_VERSION = 1;
    public static final String DATABASE_NAME    = "alarm.db";

    private static final String SQL_CREATE_ALARM = "CREATE TABLE "
                                                   + Alarm.TABLE_NAME + " ( "
                                                   + Alarm._ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                   + Alarm.NAME    + " TEXT, "
                                                   + Alarm.HOUR    + " INTEGER, "
                                                   + Alarm.MINUTE  + " INTEGER, "
                                                   + Alarm.DAYS    + " TEXT, "
                                                   + Alarm.STATUS  + " BOOLEAN, "
                                                   + Alarm.TYPE    + " INTEGER, "
                                                   + Alarm.ENABLED + " BOOLEAN, "
                                                   + Alarm.REPEAT_WEEKLY + " BOOLEAN "
                                                   + " )";

    private static final String SQL_DELETE_ALARM = "DROP TABLE IF EXISTS " + Alarm.TABLE_NAME;

    public AlarmDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ALARM);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(SQL_DELETE_ALARM);
        onCreate(sqLiteDatabase);
    }

    private AlarmModel populateModel(Cursor c){
        AlarmModel model = new AlarmModel();

        model.id     = c.getLong(c.getColumnIndex(Alarm._ID));
        model.name   = c.getString(c.getColumnIndex(Alarm.NAME));
        model.hour   = c.getInt(c.getColumnIndex(Alarm.HOUR));
        model.minute = c.getInt(c.getColumnIndex(Alarm.MINUTE));
        model.type   = c.getInt(c.getColumnIndex(Alarm.TYPE));

        model.repeatWeekly = c.getInt(c.getColumnIndex(Alarm.REPEAT_WEEKLY)) == 0 ? false : true;
        model.isEnabled    = c.getInt(c.getColumnIndex(Alarm.ENABLED))       == 0 ? false : true;
        model.status       = c.getInt(c.getColumnIndex(Alarm.STATUS))        == 0 ? false : true;

        String[] repeatingDays = c.getString(c.getColumnIndex(Alarm.REPEAT_WEEKLY)).split(",");

        for(int i = 0; i < repeatingDays.length; i++)
            model.setRepeatingDays(i, repeatingDays[i].equals("false") ? false : true);

        return model;
    }

    private ContentValues populateContent(AlarmModel model){

        ContentValues values = new ContentValues();

        values.put(Alarm.NAME, model.name);
        values.put(Alarm.HOUR, model.hour);
        values.put(Alarm.MINUTE, model.minute);
        values.put(Alarm.TYPE, model.type);
        values.put(Alarm.REPEAT_WEEKLY, model.repeatWeekly);
        values.put(Alarm.ENABLED, model.isEnabled);
        values.put(Alarm.STATUS, model.status);

        String repeatingDays = "";
        for(int i = 0; i < 7; i++)
            repeatingDays += model.getRepeatingDay(i) + ",";

        values.put(Alarm.DAYS, repeatingDays);

        return values;
    }

    public long createAlarm(AlarmModel model){

        ContentValues values = populateContent(model);
        return getWritableDatabase().insert(Alarm.TABLE_NAME, null, values);
    }

    public long updateAlarm(AlarmModel model){

        ContentValues values = populateContent(model);
        return getWritableDatabase().update(Alarm.TABLE_NAME, values, Alarm._ID + " = ?", new String[] {String.valueOf(model.id)});
    }

    public AlarmModel getAlarm(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Alarm.TABLE_NAME + " WHERE " + Alarm._ID + " = " + id;

        Cursor c = db.rawQuery(select, null);

        if (c.moveToNext())
            return populateModel(c);

        return null;
    }

    public List<AlarmModel> getAlarms(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        String select = "SELECT * FROM " + Alarm.TABLE_NAME;
        Cursor c = db.rawQuery(select, null);

        List<AlarmModel> alarmList = new ArrayList<AlarmModel>();

        while (c.moveToNext())
            alarmList.add(populateModel(c));

        if (!alarmList.isEmpty())
            return alarmList;

        return null;
    }

    public int deleteAlarm(long id){
        return getWritableDatabase().delete(Alarm.TABLE_NAME, Alarm._ID + " = ?", new String[] {String.valueOf(id)});
    }

}
