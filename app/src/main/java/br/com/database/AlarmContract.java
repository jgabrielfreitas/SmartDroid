package br.com.database;

import android.provider.BaseColumns;

/**
 * Created by JGabrielFreitas on 10/11/2014.
 */
public class AlarmContract {

    public AlarmContract(){}

    public static abstract class Alarm implements BaseColumns{

        public static final String TABLE_NAME    = "Alarm";
        public static final String NAME          = "name";
        public static final String HOUR          = "hour";
        public static final String MINUTE        = "minute";
        public static final String DAYS          = "days";
        public static final String REPEAT_WEEKLY = "weekly";
        public static final String ENABLED       = "enabled";
        public static final String TYPE          = "type";
        public static final String STATUS        = "status";

       /*
        * NUMBER OF TYPES
        * +---+-----------------+
        * | N | INFORMATION     |
        * +---+-----------------+
        * | 1 | WIFI            |
        * | 2 | MOBILE INTERNET |
        * | 3 | BLUETOOTH       |
        * | 4 | AIRPLANE MODE   |
        * +---+-----------------+
        *
        * */

    }

}
