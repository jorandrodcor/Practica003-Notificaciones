package ud.example.practica003;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeTransform;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final int ID_MEN_BARRA_NOTIF = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void noti01(View v){
        String mess = getResources().getString(R.string.mensaje01);
        Toast toast1 = Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_LONG);
        toast1.show();
    }

    public void noti02(View v){
        String mess = getResources().getString(R.string.mensaje01);
        Toast toast1 = Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_LONG);
        toast1.setGravity(Gravity.CENTER|Gravity.LEFT, 10, 10);
        toast1.show();
    }

    public void noti03(View v){
        Toast toast1 = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.layout_toast));
        TextView txtmsg = (TextView) layout.findViewById(R.id.mensajelbl);
        txtmsg.setText(getResources().getString(R.string.mensaje02));
        toast1.setView(layout);
        toast1.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void noti04(View v){
        String ns = Context.NOTIFICATION_SERVICE;
        String CHANNEL_ID = "ud.com.ANDROID";
        NotificationManager notManager = (NotificationManager) getSystemService(ns);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "ANDROID CHANNEL";
            String descripcion = "Canal de Notificaciones de ANDROID para la UD";
            int  Importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,Importance);
            channel.setDescription(descripcion);
            notManager.createNotificationChannel(channel);
        }

        int icono = android.R.drawable.stat_sys_warning;
        CharSequence textEstado = getResources().getString(R.string.estado);
        CharSequence titulo = getResources().getString(R.string.titulo);
        CharSequence descripcion = getResources().getString(R.string.descripcion);
        long hora = System.currentTimeMillis();
        Context contexto = getApplicationContext();
        Intent notIntent = new Intent(contexto, MainActivity.class);
        PendingIntent conIntent = PendingIntent.getActivity(contexto, 0, notIntent, 0);

        NotificationCompat.Builder nBuilder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                .setSmallIcon(icono)
                .setLargeIcon((((BitmapDrawable)getResources().getDrawable(R.drawable.info)).getBitmap()))
                .setContentTitle(titulo)
                .setContentText(descripcion)
                .setContentInfo(textEstado)
                .setWhen(hora)
                .setContentIntent(conIntent)
                .setAutoCancel(true)
                .setColor(getResources().getColor(R.color.colorPrimaryDark))
                .setVibrate(new long[]{100,250,100,500})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        notManager.notify(ID_MEN_BARRA_NOTIF, nBuilder.build());
    }// cierre metodo noti04

    public void noti05(View v){
        String mess = getResources().getString(R.string.mensaje01);
        Snackbar.make(v, mess, Snackbar.LENGTH_LONG)
                .setAction(getResources().getString(R.string.aqui), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast1 = Toast.makeText(getApplicationContext(), getResources().getString(R.string.mensaje), Toast.LENGTH_LONG);
                        toast1.show();
                        Log.i("OJO ACA", "Aqui llegue");
                    }
                })
                //.setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }// Cierrew del metodo noti05
}//Cierre de la clase main activity