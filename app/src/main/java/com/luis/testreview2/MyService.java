package com.luis.testreview2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        super.onStartCommand(intent, flags, startId);
        if (intent != null) {
            Bundle dataBundle = intent.getExtras();
            String comando = dataBundle.getString("comando");

            String mensaje = "Hapy said " + comando;

            enviarBroadcast(mensaje);




        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");

        return null;
    }

    private void enviarBroadcast(String mensajeParaUsuario) {
        Intent intent = new Intent();

        intent.setAction("my_action");

        Bundle misDatos = new Bundle();
        misDatos.putString("mensaje", mensajeParaUsuario);
        intent.putExtras(misDatos);

        sendBroadcast(intent);

        //NOTIFICACION
        Notification myNotification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("The Title")
                .setContentText(mensajeParaUsuario)
                .setAutoCancel(true)
                .build();

        myNotification.flags |= Notification.FLAG_AUTO_CANCEL;


        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(25, myNotification);


    }

}
