package com.demomapas.servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import com.demomapas.CloudEndpointUtils;
//import com.demomapas.model.tareaendpoint.Tareaendpoint;
//import com.demomapas.puntoendpoint.Puntoendpoint;
//import com.demomapas.puntoendpoint.model.CollectionResponsePunto;
//import com.demomapas.puntoendpoint.model.Punto;
import com.google.android.gcm.GCMBaseIntentService;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

public class Servicio_Localizacion extends Service implements LocationListener{

	public static Timer timer;
	public  static long UPDATE_INTERVAL=0;
	ContentValues valuesLocalizacion;
	SharedPreferences prefereces;
	Editor edit;
	LocationManager locationManager;
	public SQLiteDatabase db = null;
//	Puntoendpoint puntoEndpoint;
	String provider;
	String [] elementos;
	static SharedPreferences preferences;
	static SharedPreferences.Editor editor;
	public Location location;
	private final Handler _handler = new Handler();
	private static int DATA_INTERVAL =  1 * 1000;
	//fecha
	Calendar c;
	SimpleDateFormat df;
	String formattedDate;
	
	Cursor cursor;
	int idUsuario;
	int i=0;
	private boolean canGetLocation;
	public static long tiempo;
	public static long nuevotiempo;
	public static Context contexto;
	double latitude = 0;
	double longitude = 0;
	 
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("entre a oncreate", "entre a oncreate");
		
		//inicializaEndpoints();
		preferences = getApplicationContext().getSharedPreferences(
				"settings", 0);
		log(new Date().toString(), "entre al oncreate de el servicio");
		Log.d("gaurde en el log", "guarde en el log");
		Log.i("accedi al preferences", "accedi al preferences");
		int valor =preferences.getInt("timer", 1);//aqui configuramos los minutos
		Log.i("el valor del timer es", ""+valor);
		log(new Date().toString(), "el valor del timer es: "+valor);
		tiempo = 1000*60*valor;
		Log.i("el tiempo del timer es", "el tiempo del timer es"+ tiempo);
		//Log.d("el contexto sera:", "el contexto sera:");
		if(Iniciar_Servicio_Localizacion.c==null)
	//	Log.d("contexto es"+ Iniciar_Servicio_Localizacion.c.toString(), Iniciar_Servicio_Localizacion.c+"");
	//	contexto=Iniciar_Servicio_Localizacion.c;
		Log.i("el timer se creo con un valor en tiempo de: ", tiempo+"");
	//	Log.i("el contexto que me esta mandando es el siguiente", contexto.toString());
		Log.i("SERVICIO", "El servicio de Localizacion se ha creado");
		//log(new Date().toString(), "El servicio de Localizacion se ha creado");
		//Toast.makeText(getApplicationContext(), "servicio creado y obteniendo ubicaciones", Toast.LENGTH_LONG).show();
		contexto=Servicio_Localizacion.this;
		try {
			Log.i("el valor del contexto del this es ", contexto.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	
	
		timer=new Timer();

	
	

		
		//new insertOrdenNueva().execute(location.getLatitude()+"",location.getLongitude()+"");
	}

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

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
		super.onDestroy();
		if (timer != null) {
			timer.cancel();
			}
		Log.i(getClass().getSimpleName(), "Timer Localizacion stopped.");
//log(new Date().toString(),"El servicio se ha detenido");

	}
//	public class insertOrdenNueva extends AsyncTask<String,String,Void> {
//
//		IOException exceptionThrown;
//
//		@Override
//		protected Void doInBackground(String... params) {
//			// TODO Auto-generated method stub
//
//			Log.i("estoy en la insercion", "insercion"); 
//		//locationManager.removeUpdates(Servicio_Localizacion.this);
//		//Log.i("apague el location manager", "apaga");
//			return null;
//		}
//		
//		
//		
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
	public boolean checar_conexion(){
		//log(new Date().toString(), "verificar conexion");
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
        	//log(new Date().toString(), "existe conexion de red");    
        	return true;
                
        } else {
        	//log(new Date().toString(), "no existe conexion de red");
                return false;
        }
    }

	@Override
	public void onStart(final Intent intent, int startId) {
		
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		//log(new Date().toString(), "onStart del servicio");
		//preferences = getApplicationContext().getSharedPreferences(
		//"settings", 0);
		//locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		//locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, Servicio_Localizacion.this);//con este funciona para las 3 redes
		
		timer.scheduleAtFixedRate(new TimerTask() {
//
		private boolean canGetLocation;
			@Override
			public void run() {
				 Date date = new Date();
//				// TODO Auto-generated method stub
			 try {
//					 location = getLocation();
//					 Log.d("entre al timer: ", "la hora es: "+date.toString());
//					 Log.d("se obtuvo la ubicacion", "lat :"+location.getLatitude()+" long: "+location.getLongitude());
				 _handler.postDelayed(getData, DATA_INTERVAL);
				} catch (Exception e) {
//					// TODO: handle exception
//					Log.e("no se pudo obtener la ubicacion", e.getMessage());
				}
//				
//				
//				
//				
//
			}
//
		}, 0, (tiempo));
//		Date date = new Date();
//		location = getLocation();
//		 Log.d("entre al timer: ", "la hora es: "+date.toString());
//		 Log.d("se obtuvo la ubicacion", "lat :"+location.getLatitude()+" long: "+location.getLongitude());
//		
		try {
			location = getLocation();
			Date date = new Date();
			location = getLocation();
			 Log.d("entre al timer: ", "la hora es: "+date.toString());
			 //log(new Date().toString(), "entre al timer");
			 Log.d("se obtuvo la ubicacion", "lat :"+location.getLatitude()+" long: "+location.getLongitude());
			
		} catch (Exception e) {
			// TODO: handle exception
			log(new Date().toString(), "error: "+e.getMessage());
		}
	
		
	
	
//		    Timer time = new Timer();
//		    time.schedule(new TimerTask() {
//		            @Override
//		            public void run() {
//		                _locationManager.removeUpdates(_connector);
//		                this.cancel();
//		            }
//		        }, 5000, 5000);
		    
	
		
	}
	

	private final Runnable getData = new Runnable()
	{
	    @Override
	    public void run()
	    {
	    	try {
	    		Date date = new Date();
				location = getLocation();
				//log(new Date().toString(), "location: "+location.getLatitude()+" " +location.getLongitude());
				Log.d("gaurde en el log", "guarde en el log");
				 Log.d("entre al timer: ", "la hora es: "+date.toString());
				 Log.d("se obtuvo la ubicacion", "lat :"+location.getLatitude()+" long: "+location.getLongitude());
				 grabar(location.getLatitude(), location.getLongitude(), date.toString());
			} catch (Exception e) {
				// TODO: handle exception
				log(new Date().toString(), "error: "+e.getMessage());
			}
	    
	    	
	    }
	};
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void configurar_tiempo(int valor){
		if(preferences.getInt("timer", 5)!=valor){
		Log.i("llego un tiempo de :", valor+"");
		editor = preferences.edit();
		editor.putInt("timer",valor	);
		editor.commit();
		tiempo=1000*60*valor;
		Log.i("configuracion de timer realizada", "configuracion de timer realizada");

		if(timer!=null){
			timer.cancel();
	
		}
	
		Log.i("llego un valor diferente ",preferences.getInt("timer", 5)+"");
			//MainActivity.deten_servicio(contexto);
				
		}else Log.i("el valor que llego es igual",valor+"");
		
		
		
	}
	public String leerLog() {
		String nomarchivo = "logoswaldo.txt";
		String ret = null;
		File tarjeta = Environment.getExternalStorageDirectory();
		File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
		try {
			FileInputStream fIn = new FileInputStream(file);
			InputStreamReader archivo = new InputStreamReader(fIn);
			BufferedReader br = new BufferedReader(archivo);
			String linea = br.readLine();
			String todo = "";
			br.close();
			archivo.close();
			ret = linea;
		} catch (IOException e) {
			Log.e("error de lectura",e.toString());
			ret="";
		}
		return ret;
	}
	public String recuperar() {
		String nomarchivo = "ubicaciones.txt";
		String ret = null;
		File tarjeta = Environment.getExternalStorageDirectory();
		File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
		try {
			FileInputStream fIn = new FileInputStream(file);
			InputStreamReader archivo = new InputStreamReader(fIn);
			BufferedReader br = new BufferedReader(archivo);
			String linea = br.readLine();
			String todo = "";
//			while (linea != null) {
//				todo = todo + linea + "\n";
//				linea = br.readLine();
//			}
			br.close();
			archivo.close();
			ret = linea;
		} catch (IOException e) {
			Log.e("error de lectura",e.toString());
			ret="";
		}
		return ret;
	}
	public void grabar(double latitud,double longitud,String timestamp) {
		//log(new Date().toString(), "Metodo grabar");
		String contenido;
		String nomarchivo = "ubicaciones.txt";
		String history = recuperar();
		if(preferences.getBoolean("primera",true))
		{
			contenido="";
			editor = preferences.edit();
			editor.putBoolean("primera",false) ;
			editor.commit();
		}else {
			if(history==null)history="";		
			Geocoder geocoder;
			List<Address> addresses = null;
			geocoder = new Geocoder(this, Locale.getDefault());
			try {
				addresses = geocoder.getFromLocation(latitude, longitude, 1);
				//log(new Date().toString(), "direccion: "+addresses.get(0).getAddressLine(0));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//log(new Date().toString(), "error al obtener la direccion");
				e.printStackTrace();
			}
			
			

			String address = addresses.get(0).getAddressLine(0);
			String city = addresses.get(0).getAddressLine(1);
			String country = addresses.get(0).getAddressLine(2);
	 contenido = history +"lat: "+latitud+"long: "+longitud +"fecha: "+timestamp +"&"+"\n"+" "+address+" city: "+city+" country: "+country+"\n";
	 Log.i("ubicacion",latitud + longitud + "");
	 
		
		}
	
		try {
			File tarjeta = Environment.getExternalStorageDirectory();
			File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file));
			osw.write(contenido);
			osw.flush();
			osw.close();
			Log.i("grabacion de datos", "los datos se guardaron correctamente");
			//log(new Date().toString(), "los datos se guardaron correctamente");
		} catch (IOException ioe) {
			Log.e("error de escritura", ioe.toString());
			//log(new Date().toString(), "error de grabacion de escritura");
		}
	}
	

	public  void log (String date , String log){
		
		String contenido;
		String nomarchivo = "logoswaldo.txt";
		String history = leerLog();
		if(preferences.getBoolean("primeralog",true))
		{ 
			contenido="";
			editor = preferences.edit();
			editor.putBoolean("primeralog",false) ;
			editor.commit();
		}else {
			if(history==null)history="";		
	 contenido = history +"\n"+" "+date +" "+log;
		
		}
	
		try {
			File tarjeta = Environment.getExternalStorageDirectory();
			File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file));
			osw.write(contenido);
			osw.flush();
			osw.close();
			Log.i("grabacion de datos", "los datos se guardaron correctamente: "+ contenido);
		} catch (IOException ioe) {
			Log.e("error de escritura", ioe.toString());
		}
		
		
	}
	
	

	//LocationListener
//	public void onLocationChanged(Location location) {//actualiza la posicion
//		// TODO Auto-generated method stub
//		Log.i("Localidad ", ""+location);
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
//		Log.i("Localidad ", "cambio de posicion "+ provider);
//		
//	}
	  public Location getLocation() {
		    try {
		        LocationManager locationManager = (LocationManager) getApplicationContext()
		                .getSystemService(LOCATION_SERVICE);
		        //log(new Date().toString(), "entre al getlocation");
		        // getting GPS status
		        boolean isGPSEnabled = locationManager
		                .isProviderEnabled(LocationManager.GPS_PROVIDER);
		        
		        // getting network status
		        boolean isNetworkEnabled = locationManager
		                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

		        if (!isGPSEnabled && !isNetworkEnabled) {
		            // no network provider is enabled
		        	//log(new Date().toString(), "gps y red desactivados");
		        } else {
		            this.canGetLocation = true;
		            if (isNetworkEnabled) {
		            	//log(new Date().toString(), "red encendida");
		                locationManager.requestLocationUpdates(
		                        LocationManager.NETWORK_PROVIDER,
		                        1000,
		                        50, this);
		                Log.d("Network", "Network Enabled");
		                if (locationManager != null) {
		                	
		                    location = locationManager
		                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		                    if (location != null) {
		                        latitude = location.getLatitude();
		                        longitude = location.getLongitude();
		                    }
		                }
		            }
		            // if GPS Enabled get lat/long using GPS Services
		            if (isGPSEnabled) {
		                if (location == null) {
		                	//log(new Date().toString(), "gps encendida");
		                    locationManager.requestLocationUpdates(
		                            LocationManager.GPS_PROVIDER,
		                            1000,
		                            50, this);
		                    Log.d("GPS", "GPS Enabled");
		                    if (locationManager != null) {
		                        location = locationManager
		                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
		                        if (location != null) {
		                            latitude = location.getLatitude();
		                            longitude = location.getLongitude();
		                            
		                        }
		                        locationManager.removeUpdates(this);
		                    }
		                }
		            }
		        }

		    } catch (Exception e) {
		    	//log(new Date().toString(), "error en la ubicacion: "+e.getMessage());
		    	log(new Date().toString(), "error: "+e.getMessage());
		        e.printStackTrace();
		    }

		    return location;
		}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "cambio la ubicacion", Toast.LENGTH_LONG).show();
		Log.i("cambio la ubicacion", " cambio la ubicacion lat:"+location.getLatitude()+ " long: "+location.getLongitude());
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), "change status", Toast.LENGTH_LONG).show();
		Log.i("cambio status", provider + " status: "+status );
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), provider + "activado", Toast.LENGTH_LONG).show();
		Log.i("Provedor activado", provider  );
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),provider + "desaactivado", Toast.LENGTH_LONG).show();
		Log.i("Provedor desactivado", provider  );
	}
}
