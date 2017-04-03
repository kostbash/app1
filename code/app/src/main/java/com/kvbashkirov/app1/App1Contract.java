package com.kvbashkirov.app1;

import android.provider.BaseColumns;

/**
 * Created by kvbashkirov on 03.04.2017.
 */

public class App1Contract {

    public App1Contract() {};

    public static abstract class StudentEntry implements BaseColumns {
        public static final String TABLE_NAME = "students";

        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SNAME = "sname";
        public static final String COLUMN_NAME_PHONE = "phone";
    }

    public static abstract class FeeEntry implements BaseColumns {
        public static final String TABLE_NAME = "fee";

        public static final String COLUMN_NAME_STUDENT = "student";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_DATE = "date";
    }

    public static abstract class PresenceEntry implements BaseColumns {
        public static final String TABLE_NAME = "presences";

        public static final String COLUMN_NAME_STUDENT = "student";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_COST = "cost";
    }

}
