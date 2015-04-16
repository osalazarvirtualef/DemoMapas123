 package com.demomapas;

import java.io.IOException;
import java.text.BreakIterator;

import android.location.LocationListener;
import android.location.LocationManager;

import com.demomapas.deviceinfoendpoint.Deviceinfoendpoint;
import com.demomapas.deviceinfoendpoint.model.DeviceInfo;
import com.demomapas.endpoints.EndPointsInicializacion;
import com.demomapas.messageEndpoint.MessageEndpoint;
import com.demomapas.messageEndpoint.model.CollectionResponseMessageData;
import com.demomapas.messageEndpoint.model.MessageData;
import com.demomapas.pjgviewpager.MainActivityPager;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.virtualef.pgj.service.agentService.AgentService;
import com.virtualef.pgj.service.agentService.AgentService.GetAgentByAlias;
import com.virtualef.pgj.service.agentService.model.AgentDto;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An activity that communicates with your App Engine backend via Cloud
 * Endpoints.
 * 
 * When the user hits the "Register" button, a message is sent to the backend
 * (over endpoints) indicating that the device would like to receive broadcast
 * messages from it. Clicking "Register" also has the effect of registering this
 * device for Google Cloud Messaging (GCM). Whenever the backend wants to
 * broadcast a message, it does it via GCM, so that the device does not need to
 * keep polling the backend for messages.
 * 
 * If you've generated an App Engine backend for an existing Android project,
 * this activity will not be hooked in to your main activity as yet. You can
 * easily do so by adding the following lines to your main activity:
 * 
 * Intent intent = new Intent(this, RegisterActivity.class);
 * startActivity(intent);
 * 
 * To make the sample run, you need to set your PROJECT_NUMBER in
 * GCMIntentService.java. If you're going to be running a local version of the
 * App Engine backend (using the DevAppServer), you'll need to toggle the
 * LOCAL_ANDROID_RUN flag in CloudEndpointUtils.java. See the javadoc in these
 * classes for more details.
 * 
 * For a comprehensive walkonthrough, check out the documentation at
 * http://developers.google.com/eclipse/docs/cloud_endpoints
 */
public class RegisterActivity extends Activity implements OnClickListener, LocationListener {
	DeviceInfo device = new DeviceInfo();
	Deviceinfoendpoint deviceiInfoendpoint = null;
//	Agenteendpoint agenteEndpoint = null;
//	Agente Agente = new Agente();
	AgentDto Agent = new AgentDto();
	AgentService agentEndpoint = null;
	private EditText usuariotext;
	private EditText password;
	private boolean userExist = false;
	Intent intent;
	static SharedPreferences.Editor editor;
	static SharedPreferences Preferences;
	public boolean agenteExiatente = false;
	ProgressDialog progressDialog;
	 private LocationManager mLocationManager;
	 LocationListener mlocListener; 

		double latitude = 0;
		double longitude = 0;
	 public Location location;

  enum State {
    REGISTERED, REGISTERING, UNREGISTERED, UNREGISTERING
  }

  private State curState = State.UNREGISTERED;
  private OnTouchListener registerListener = null;
  private OnTouchListener unregisterListener = null;
  private MessageEndpoint messageEndpoint = null;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
   // setTitle("POLICÍA DE INVESTIGACIÓN");
   // getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
    Preferences = getApplicationContext().getSharedPreferences(
			"settings", 0);
    boolean initialized = Preferences.getBoolean("FirstTime", false);
    if (initialized) {
    	finish();
    }
    setContentView(R.layout.activity_register2);
   // Intent intent = new Intent(this, MapView.class);
    Button regButton = (Button) findViewById(R.id.regButton2);

    registerListener = new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
    
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN:
          if (GCMIntentService.PROJECT_NUMBER == null
              || GCMIntentService.PROJECT_NUMBER.length() == 0) {
//            showDialog("Unable to register for Google Cloud Messaging. "
//                + "Your application's PROJECT_NUMBER field is unset! You can change "
//                + "it in GCMIntentService.java");
          } else {
         //   updateState(State.REGISTERING);
            try {
              GCMIntentService.register(getApplicationContext());
            } catch (Exception e) {
              Log.e(RegisterActivity.class.getName(),
                  "Exception received when attempting to register for Google Cloud "
                      + "Messaging. Perhaps you need to set your virtual device's "
                      + " target to Google APIs? "
                      + "See https://developers.google.com/eclipse/docs/cloud_endpoints_android"
                      + " for more information.", e);
//              showDialog("There was a problem when attempting to register for "
//                  + "Google Cloud Messaging. If you're running in the emulator, "
//                  + "is the target of your virtual device set to 'Google APIs?' "
//                  + "See the Android log for more details.");
       //       updateState(State.UNREGISTERED);
            }
          }
          return true;
        case MotionEvent.ACTION_UP:
          return true;
        default:
          return false;
        }
      }
    };

    unregisterListener = new OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN:
       //   updateState(State.UNREGISTERING);
          GCMIntentService.unregister(getApplicationContext());
          return true;
        case MotionEvent.ACTION_UP:
          return true;
        default:
          return false;
        }
      }
    };

   // regButton.setOnTouchListener(registerListener);
    regButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			  progressDialog = new ProgressDialog(RegisterActivity.this);  
		        //Set the progress dialog to display a horizontal bar .  
		        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
		        //Set the dialog title to 'Loading...'.  
		        progressDialog.setTitle("Validando Usuario...");  
		        //Set the dialog message to 'Loading application View, please wait...'.  
		        progressDialog.setMessage("Espere...");  
		        //This dialog can't be canceled by pressing the back key.  
		        progressDialog.setCancelable(false);  
		        //This dialog isn't indeterminate.  
		        progressDialog.setIndeterminate(false);  
		        //The maximum number of progress items is 100.  
		        progressDialog.setMax(100);  
		        //Set the current progress to zero.  
		        progressDialog.setProgress(0);  
		        //Display the progress dialog.  
		        progressDialog.show();
		new validarUsuario(getApplicationContext()).execute();
			
//			Intent intent = new Intent(RegisterActivity.this,MainActivityPager.class);
//			startActivity(intent);
//			finish();
			
		}
	});
    
    /*
     * build the messaging endpoint so we can access old messages via an endpoint call
     */
    MessageEndpoint.Builder endpointBuilder = new MessageEndpoint.Builder(
        AndroidHttp.newCompatibleTransport(),
        new JacksonFactory(),
        new HttpRequestInitializer() {
          public void initialize(HttpRequest httpRequest) { }
        });

    messageEndpoint = CloudEndpointUtils.updateBuilder(endpointBuilder).build();
    
    
    LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
	locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);    
	Criteria criteria = new Criteria();
	String provider = null;
	provider = locationManager.getBestProvider(criteria, false);
	if(provider!=null && !provider.equals("")){
		
		// Get the location from the given provider 
	    location = locationManager.getLastKnownLocation(provider);
		
	                
	    //locationManager.requestLocationUpdates(provider, 2000, 1, this);
	    

	    
	    
	    if(location!=null){
	    	//onLocationChanged(location);
	    	latitude = location.getLatitude();
	    	longitude = location.getLongitude();
	    }
	    else
	    	Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
	    
	}else{
		Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
	}
	// Getting the name of the provider that meets the criteria
if(latitude != 0 && longitude != 0){
	 latitude = location.getLatitude();
	 longitude = location.getLongitude();
	Log.i("latitud", latitude+"\n");
	Log.i("longitud", longitude+"\n");
    
}
    
    
    
  }
  @Override
protected void onStop() {
	// TODO Auto-generated method stub

	  super.onStop();
}

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);

    /*
     * If we are dealing with an intent generated by the GCMIntentService
     * class, then display the provided message.
     */
    if (intent.getBooleanExtra("gcmIntentServiceMessage", false)) {

     // showDialog(intent.getStringExtra("message"));

      if (intent.getBooleanExtra("registrationMessage", false)) {

        if (intent.getBooleanExtra("error", false)) {
          /*
           * If we get a registration/unregistration-related error,
           * and we're in the process of registering, then we move
           * back to the unregistered state. If we're in the process
           * of unregistering, then we move back to the registered
           * state.
           */
          if (curState == State.REGISTERING) {
        //    updateState(State.UNREGISTERED);
          } else {
        //    updateState(State.REGISTERED);
          }
        } else {
          /*
           * If we get a registration/unregistration-related success,
           * and we're in the process of registering, then we move to
           * the registered state. If we're in the process of
           * unregistering, the we move back to the unregistered
           * state.
           */
          if (curState == State.REGISTERING) {
      //      updateState(State.REGISTERED);
          } else {
       //     updateState(State.UNREGISTERED);
          }
        }
      }
      else {
        /* 
         * if we didn't get a registration/unregistration message then
         * go get the last 5 messages from app-engine
         */
        new QueryMessagesTask(this, messageEndpoint).execute();
      }
    }
  }
  
  private void updateState(State newState) {
    Button registerButton = (Button) findViewById(R.id.regButton);
    switch (newState) {
    case REGISTERED:
      registerButton.setText("Unregister");
      registerButton.setOnTouchListener(unregisterListener);
      registerButton.setEnabled(true);
      break;

    case REGISTERING:
      registerButton.setText("Registering...");
      registerButton.setEnabled(false);
      break;

    case UNREGISTERED:
      registerButton.setText("Register");
      registerButton.setOnTouchListener(registerListener);
      registerButton.setEnabled(true);
      break;

    case UNREGISTERING:
      registerButton.setText("Unregistering...");
      registerButton.setEnabled(false);
      break;
    }
    curState = newState;
  }

//  private void showDialog(String message) {
//    new AlertDialog.Builder(this)
//        .setMessage(message)
//        .setPositiveButton(android.R.string.ok,
//            new DialogInterface.OnClickListener() {
//              public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//              }
//            }).show();
//  }

  /*
   * Need to run this in background so we don't hold up the UI thread, 
   * this task will ask the App Engine backend for the last 5 messages
   * sent to it
   */
  private class QueryMessagesTask 
      extends AsyncTask<Void, Void, CollectionResponseMessageData> {
    Exception exceptionThrown = null;
    MessageEndpoint messageEndpoint;

    public QueryMessagesTask(Activity activity, MessageEndpoint messageEndpoint) {
      this.messageEndpoint = messageEndpoint;
    }
    
    @Override
    protected CollectionResponseMessageData doInBackground(Void... params) {
      try {
        CollectionResponseMessageData messages = 
            messageEndpoint.listMessages().setLimit(5).execute();
        return messages;
      } catch (IOException e) {
        exceptionThrown = e;
        return null;
        //Handle exception in PostExecute
      }            
    }
    
    protected void onPostExecute(CollectionResponseMessageData messages) {
      // Check if exception was thrown
      if (exceptionThrown != null) {
        Log.e(RegisterActivity.class.getName(), 
            "Exception when listing Messages", exceptionThrown);
//        showDialog("Failed to retrieve the last 5 messages from " +
//        		"the endpoint at " + messageEndpoint.getBaseUrl() +
//        		", check log for details");
      }
      else {
        TextView messageView = (TextView) findViewById(R.id.msgView);
        messageView.setText("Last 5 Messages read from " + 
            messageEndpoint.getBaseUrl() + ":\n");
        for(MessageData message : messages.getItems()) {
          messageView.append(message.getMessage() + "\n");
        }
      }
    }  
    
   
  }
  
	public class validarUsuario extends AsyncTask<Void, Void, Void> {

		Context context;
		ProgressDialog progress;
		DeviceInfo device = new DeviceInfo();
		Deviceinfoendpoint deviceiInfoendpoint = null;
//		Agenteendpoint agentEndpoint = null;
//		Agente Agent = new  Agente();
		
		private validarUsuario(Context context) {
	        this.context = context.getApplicationContext();
	    }
		@Override
		protected Void doInBackground(Void... params) {
			usuariotext = (EditText)findViewById(R.id.editTextUsuario);
			password = (EditText)findViewById(R.id.editTextContrasena);
			// TODO Auto-generated method stub
		
			
			//hacemos la inicializacion del endpoint de Agemte
			
		EndPointsInicializacion endpoints = new EndPointsInicializacion();
		agentEndpoint = endpoints.InicializacionAgent();
		try {

			AgentDto agent = agentEndpoint.getAgentByAlias(usuariotext.getText().toString(), password.getText().toString()).execute();
			if(agent != null) 
				{
				agenteExiatente = true;
	              editor = Preferences.edit();
	              //editor.putBoolean("FirstTime", true);
	              editor.putLong(Constants.idAgente, agent.getKey().getId());
	              
	              editor.commit();
	              if(latitude != 0 && longitude != 0 )
	              {
	            	  float lat = (float)latitude;
	            	  float longi = (float)longitude;
	            	  agent.setLatitude(lat);
	            	  agent.setLongitude(longi);
	            	  agentEndpoint.updatedAgent(agent).execute();
	            	  Log.i("Actualizacion de ubicacion", "se actualizo la ubicacion");
	               }
				}
			
			Log.i("", "");
			 
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			agenteExiatente = false;
			
		}
			

		

			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			progressDialog.dismiss();
			if(agenteExiatente){
			//startActivity(new Intent(RegisterActivity.this, ListaOpciones.class))
				startActivity(new Intent(RegisterActivity.this, ListaOpciones.class));
			finish();
			}else 
				Toast.makeText(getApplicationContext(), "Error en el usuario", Toast.LENGTH_LONG).show();
			// TODO Auto-generated method stub
			
//			if(!userExist){
//				Toast.makeText(getApplicationContext(), "El usuario no existe compruebe sus datos", Toast.LENGTH_LONG).show();
//			}
//			else{
//			  //  updateState(State.REGISTERING);
//	            try {
//	              GCMIntentService.register(getApplicationContext());
//	              Log.i("registrado","registrado");
//	           
//	      		
//	              startActivity(new Intent(RegisterActivity.this, MapView.class));
//	              editor = Preferences.edit();
//	              editor.putBoolean("FirstTime", true);
//	              editor.putLong("idAgente", Agente.getId());
//	              editor.commit();
//	              finish();
//	           
//	            } catch (Exception e) {
//	              Log.e(RegisterActivity.class.getName(),
//	                  "Exception received when attempting to register for Google Cloud "
//	                      + "Messaging. Perhaps you need to set your virtual device's "
//	                      + " target to Google APIs? "
//	                      + "See https://developers.google.com/eclipse/docs/cloud_endpoints_android"
//	                      + " for more information.", e);
////	              showDialog("There was a problem when attempting to register for "
////	                  + "Google Cloud Messaging. If you're running in the emulator, "
////	                  + "is the target of your virtual device set to 'Google APIs?' "
////	                  + "See the Android log for more details.");
//	            // updateState(State.UNREGISTERED);
//	            }
//				
//			}
			
			super.onPostExecute(result);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
  
}

