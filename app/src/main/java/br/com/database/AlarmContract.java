package br.com.database;

import android.provider.BaseColumns;

/**
 * Created by JGabrielFreitas on 10/11/2014.
 */
public class AlarmContract {

    public AlarmContract(){}

    public static abstract class Alarm implements BaseColumns{

        public static final String TABLE_NAME = "alarm";
        public static final String COLUMN_NAME_ALARM_NAME = "name";
        public static final String COLUMN_NAME_ALARM_TIME_HOUR = "hour";
        public static final String COLUMN_NAME_ALARM_TIME_MINUTE = "minute";
        public static final String COLUMN_NAME_ALARM_REPEAT_DAYS = "days";
        public static final String COLUMN_NAME_ALARM_REPEAT_WEEKLY = "weekly";
        public static final String COLUMN_NAME_ALARM_TONE = "tone";
        public static final String COLUMN_NAME_ALARM_ENABLED = "enabled";
        public static final String COLUMN_NAME_EVENT_TYPE = "type";
        public static final String COLUMN_NAME_STATUS = "status";

    }

}
