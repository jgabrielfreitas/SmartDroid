package br.com.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by JGabrielFreitas on 11/11/14.
 */
public class AlarmService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
