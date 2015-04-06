//package com.demomapas.servicios;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.security.Timestamp;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import com.demomapas.CloudEndpointUtils;
////import com.demomapas.model.tareaendpoint.Tareaendpoint;
////import com.demomapas.puntoendpoint.Puntoendpoint;
////import com.demomapas.puntoendpoint.model.CollectionResponsePunto;
////import com.demomapas.puntoendpoint.model.Punto;
//import com.google.android.gcm.GCMBaseIntentService;
//import com.google.api.client.extensions.android.http.AndroidHttp;
//import com.google.api.client.http.HttpRequest;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.json.jackson2.JacksonFactory;
//
//import android.app.Service;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.location.Criteria;
//import android.location.Location;
//import android.location.LocationListener;
//import android.location.LocationManager;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Environment;
//import android.os.IBinder;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.widget.Toast;
//
//public class Servicio_Localizacion extends Service implements LocationListener{
//
//	public static Timer timer;
//	public  static long UPDATE_INTERVAL=0;
//	ContentValues valuesLocalizacion;
//	SharedPreferences prefereces;
//	Editor edit;
//	LocationManager locationManager;
//	public SQLiteDatabase db = null;
//	Puntoendpoint puntoEndpoint;
//	String provider;
//	String [] elementos;
//	static SharedPreferences preferences;
//	static SharedPreferences.Editor editor;
//	public Location location;
//	 
//	//fecha
//	Calendar c;
//	SimpleDateFormat df;
//	String formattedDate;
//	
//	Cursor cursor;
//	int idUsuario;
//	int i=0;
//	public static long tiempo;
//	public static long nuevotiempo;
//	public static Context contexto;
//	
//	
//	
//	
//	@Override
//	public void onCreate() {
//		// TODO Auto-generated method stub
//		super.onCreate();
//		Log.i("entre a oncreate", "entre a oncreate");
//		inicializaEndpoints();
//		preferences = getApplicationContext().getSharedPreferences(
//				"settings", 0);
//		Log.i("accedi al preferences", "accedi al preferences");
//		int valor =preferences.getInt("timer", 1);//aqui configuramos los minutos
//		Log.i("el valor del timer es", ""+valor);
//		tiempo = 1000*60*valor;
//		Log.i("el tiempo del timer es", "el tiempo del timer es"+ tiempo);
//		//Log.d("el contexto sera:", "el contexto sera:");
//		if(Iniciar_Servicio_Localizacion.c==null)
//	//	Log.d("contexto es"+ Iniciar_Servicio_Localizacion.c.toString(), Iniciar_Servicio_Localizacion.c+"");
//	//	contexto=Iniciar_Servicio_Localizacion.c;
//		Log.i("el timer se creo con un valor en tiempo de: ", tiempo+"");
//	//	Log.i("el contexto que me esta mandando es el siguiente", contexto.toString());
//		Log.i("SERVICIO", "El servicio de Localizacion se ha creado");
//		Toast.makeText(getApplicationContext(), "servicio creado y obteniendo ubicaciones", Toast.LENGTH_LONG).show();
//		contexto=Servicio_Localizacion.this;
//		try {
//			Log.i("el valor del contexto del this es ", contexto.toString());
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//	
//	
//		timer=new Timer();
//
//	
//	
//
//		
//		//new insertOrdenNueva().execute(location.getLatitude()+"",location.getLongitude()+"");
//	}
//
//	private void inicializaEndpoints() {
//		// TODO Auto-generated method stub
//		Puntoendpoint.Builder puntobuilder = new Puntoendpoint.Builder(
//				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
//				new HttpRequestInitializer() {
//
//					@Override
//					public void initialize(HttpRequest arg0) {
//						// TODO Auto-generated method stub
//					}
//				});
//				puntoEndpoint = CloudEndpointUtils.updateBuilder(
//				puntobuilder).build();
//	}
//
//	@Override
//	public void onDestroy() {
//		// TODO Auto-generated method stub
//		
//		super.onDestroy();
//		if (timer != null) {
//			timer.cancel();
//			}
//		Log.i(getClass().getSimpleName(), "Timer Localizacion stopped.");
//
//	}
//	public class insertOrdenNueva extends AsyncTask<String,String,Void> {
//
//		IOException exceptionThrown;
//
//		@Override
//		protected Void doInBackground(String... params) {
//			// TODO Auto-generated method stub
//			String latitud = params[0];
//			String longitud = params[1];
//			
//			Log.i("estoy en la insercion", "insercion");
//			Punto p = new Punto();
//			Long idPunto = 0l;
//			CollectionResponsePunto puntoid;
//			try {
//				puntoid = puntoEndpoint.getLast().execute();
//				if(puntoid!=null )
//					if(puntoid.getItems()!=null)
//					idPunto = puntoid.getItems().get(0).getId();
//				
//				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//				Date date = new Date();
//				p.setId(idPunto+1);
//				p.setLatidude(latitud);
//				p.setLongitude(longitud);
//				p.setFecha(date.toString());
//				p.setIdAgente(preferences.getLong("idAgente", 0l));
//				puntoEndpoint.insertPunto(p).execute();
//				
//				System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
//				Log.i("punto insertado correctamente a las ",date.toString());
//				publishProgress("1","latitud: "+latitud + "longitud: "+longitud);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				publishProgress("0",e.getMessage());
//				
//				e.printStackTrace();
//			}
//			
//		
//			
//			 
//		locationManager.removeUpdates(Servicio_Localizacion.this);
//		Log.i("apague el location manager", "apaga");
//			return null;
//		}
//		@Override
//		protected void onProgressUpdate(String... values) {
//			// TODO Auto-generated method stub
//			if(values[0].equalsIgnoreCase("1"))
//			Toast.makeText(getApplicationContext(), "insercion correcta:" +values[1], Toast.LENGTH_LONG).show();
//			else 
//				if(values[0].equalsIgnoreCase("0"))
//					Toast.makeText(getApplicationContext(), "insercion erronea: "+values[1], Toast.LENGTH_LONG).show();
//			super.onProgressUpdate(values);
//		}
//
//	}
//	public boolean checar_conexion(){
//    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//        if (netInfo != null && netInfo.isConnected()) {
//                return true;
//        } else {
//                return false;
//        }
//    }
//
//	@Override
//	public void onStart(final Intent intent, int startId) {
//		// TODO Auto-generated method stub
//		super.onStart(intent, startId);
//		preferences = getApplicationContext().getSharedPreferences(
//		"settings", 0);
//		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//		//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Servicio_Localizacion.this);//con este funciona para las 3 redes
//		
//		timer.scheduleAtFixedRate(new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//					//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Servicio_Localizacion.this);//con este funciona para las 3 redes
//					locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);     
//
//
//					// Creating an empty criteria object
//					Criteria criteria = new Criteria();
//
//					// Getting the name of the provider that meets the criteria
//					provider = locationManager.getBestProvider(criteria, false);
//
//					        
//					if(provider!=null && !provider.equals("")){
//						
//						// Get the location from the given provider 
//					    location = locationManager.getLastKnownLocation(provider);
//					                
//					   
//
//					    
//					    
//					    if(location!=null)
//					    	onLocationChanged(location);
//					    else
//					    	Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
//					    
//					}else{
//						Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
//					}
//				
//					c=Calendar.getInstance();
//					df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					formattedDate = df.format(c.getTime());
//					
//
//					boolean conexion=checar_conexion();
//
//
//
//					
//					Log.i("location", "latitud: "+location.getLatitude() + "longitud: "+ location.getLongitude());	
//					//Log.i("Servicio","Ejecundo la tarea del servicio de Localizacion (Unicreih), intevalo: "+UPDATE_INTERVAL +"   "+ "i = "+(i=i+1));
//					
//					 location = locationManager.getLastKnownLocation(provider);
//				
//					 new insertOrdenNueva().execute(location.getLatitude()+"",location.getLongitude()+"");
//					
//					
//
//				} catch (Exception e) {
//					// TODO: handle exception
//
//					Log.i("Error de Localizacion Automatica", "No hay conexion a Internet");
//					
//				}
//			}
//
//		}, 0, (tiempo));
//		
//	}
//	
//
//	
//	@Override
//	public IBinder onBind(Intent intent) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public static void configurar_tiempo(int valor){
//		if(preferences.getInt("timer", 5)!=valor){
//		Log.i("llego un tiempo de :", valor+"");
//		editor = preferences.edit();
//		editor.putInt("timer",valor	);
//		editor.commit();
//		tiempo=1000*60*valor;
//		Log.i("configuracion de timer realizada", "configuracion de timer realizada");
//
//		if(timer!=null){
//			timer.cancel();
//	
//		}
//	
//		Log.i("llego un valor diferente ",preferences.getInt("timer", 5)+"");
//			//MainActivity.deten_servicio(contexto);
//				
//		}else Log.i("el valor que llego es igual",valor+"");
//		
//		
//		
//	}
//	public String recuperar() {
//		String nomarchivo = "ubicaciones";
//		String ret = null;
//		File tarjeta = Environment.getExternalStorageDirectory();
//		File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
//		try {
//			FileInputStream fIn = new FileInputStream(file);
//			InputStreamReader archivo = new InputStreamReader(fIn);
//			BufferedReader br = new BufferedReader(archivo);
//			String linea = br.readLine();
//			String todo = "";
////			while (linea != null) {
////				todo = todo + linea + "\n";
////				linea = br.readLine();
////			}
//			br.close();
//			archivo.close();
//			ret = linea;
//			
//			
//			
//			
//
//		} catch (IOException e) {
//			Log.e("error de lectura",e.toString());
//			ret="";
//		}
//		return ret;
//	}
//	public void grabar(double latitud,double longitud,String timestamp, String imei) {
//		String contenido;
//		String nomarchivo = "ubicaciones.txt";
//		String history = recuperar();
//		if(preferences.getBoolean("primervez",true))
//		{
//			contenido="";
//			editor = preferences.edit();
//			editor.putBoolean("primervez",false) ;
//			editor.commit();
//		}else {
//			if(history==null)history="";				
//	 contenido = history +latitud+longitud +timestamp + "imei:"+imei+"&";
//	 Log.i("ubicacion",latitud + longitud + "");
//		
//		}
//	
//		try {
//			File tarjeta = Environment.getExternalStorageDirectory();
//			File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
//			OutputStreamWriter osw = new OutputStreamWriter(
//					new FileOutputStream(file));
//			osw.write(contenido);
//			osw.flush();
//			osw.close();
//			Log.i("grabacion de datos", "los datos se guardaron correctamente");
//		} catch (IOException ioe) {
//			Log.e("error de escritura", ioe.toString());
//		}
//	}
//
//	//LocationListener
//	public void onLocationChanged(Location location) {//actualiza la posicion
//		// TODO Auto-generated method stub
//	//	Log.i("Localidad ", ""+location);
//		
//	//	grabar(location.getLatitude(),location.getLongitude());
//	//	Toast.makeText(getApplicationContext(), "cambio la posicion lat:"+ location.getLatitude() +" long: "+location.getLongitude() ,Toast.LENGTH_LONG).show();
//		
//	}
//
//	public void onProviderDisabled(String provider) {//
//		// TODO Auto-generated method stub
//		Log.i("Localidad ", "YA NO HAY CONEXION" +provider);
//		
//	}
//
//	public void onProviderEnabled(String provider) {//
//		// TODO Auto-generated method stub
//		Log.i("Localidad ", "Si hay internet "+ provider);
//		
//	}
//
//	public void onStatusChanged(String provider, int status, Bundle extras) {
//		// TODO Auto-generated method stub
//		
//	//	Log.i("Localidad ", "cambio de posicion "+ provider);
//		
//	}
//}
