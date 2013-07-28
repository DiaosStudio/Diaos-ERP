package com.diaos_erp.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
    public static Date getCurTime()
    {
        return Calendar.getInstance().getTime();
    }

    public static Timestamp getCurrentTime()
    {
        return new Timestamp(getCurTime().getTime());
    }
}
