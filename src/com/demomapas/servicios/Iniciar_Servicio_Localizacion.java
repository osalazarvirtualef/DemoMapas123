package com.demomapas.servicios;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Iniciar_Servicio_Localizacion extends BroadcastReceiver {
public static Context c;
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Log.i("soy inreceive", "soy inreceive");
		Log.i("Contexto del receive", context.toString());
		Log.i("intento", intent.toString());
		if (context.toString().contains("Receiver")) {
		
			Log.i("soy de receiver","soy de receiver");
			 Intent serviceIntent = new Intent(context,Servicio_Localizacion.class);
			 c=context;
		       // if(!Servicio_Localizacion.isRunning())context.startService(serviceIntent);
		    context.startService(serviceIntent);
		}
		else
		{
			
			 Log.i("soy de normal","soy de normal");
			 c=context;
			 context.startService(intent);
				
		}
		

		
	}
	
	public void stop(Intent intent, Context context){
		context.stopService(intent);
		Log.i("On stop en servicio","OnStop en servicio");
		 Intent serviceIntent = new Intent(context,Servicio_Localizacion.class);
		 c=context;
	       // if(!Servicio_Localizacion.isRunning())context.startService(serviceIntent);
	    context.startService(serviceIntent);
	
	}
	
	
}
