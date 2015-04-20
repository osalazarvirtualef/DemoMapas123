package com.demomapas;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;




































import com.demomapas.Pagina1.EnviarInformacion;
import com.demomapas.endpoints.EndPointsInicializacion;
import com.demomapas.pjgviewpager.MainActivityPager;
//import com.demomapas.model.agenteendpoint.Agenteendpoint;
//import com.demomapas.model.detenidoendpoint.Detenidoendpoint;
//import com.demomapas.model.detenidoendpoint.model.CollectionResponseDetenido;
//import com.demomapas.model.detenidoendpoint.model.Detenido;
//import com.demomapas.model.rutaendpoint.Rutaendpoint;
//import com.demomapas.model.tareaendpoint.Tareaendpoint;
//import com.demomapas.model.tareaendpoint.model.Tarea;
//import com.demomapas.model.usuarioendpoint.Usuarioendpoint;
//import com.demomapas.model.usuarioendpoint.model.CollectionResponseUsuario;
//import com.demomapas.model.usuarioendpoint.model.Usuario;
//import com.demomapas.model.zonaendpoint.Zonaendpoint;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.virtualef.pgj.service.commandmentService.CommandmentService;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;

import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class LevantarInformacion2 extends Activity{
	
	ProgressDialog progressDialog;
	int tipo;
	 int position1;
	 long position2;
	 commandmentdto2 mandamientorecibido;
	LinearLayout ListaOrdenesAprehension;
	ImageView mImageView;
	static Bitmap imagenphoto;
	Button picBtn;
    ImageView ImagenAudio;
    MediaRecorder recorder;
	Button GrabarAudio;
	 File archivo;
	 MediaPlayer player;
	 private Uri mVideoUri;
	Button EnviarInfo;
	private Bitmap mImageBitmap;
	 String pathAudio;
	 public static String path;
	public boolean grabando = false;
	private String mCurrentPhotoPath;
	public boolean enviado = false;
	public static TextView juzgado;
	public static TextView expediente;
	public static TextView oficio;
	public static TextView juez;
	public static TextView delito;
	public static TextView requerido;
	public static TextView alias;
	public static TextView edad;
	public static TextView sexo;
	public static TextView domicilio;
	public String mandamientoscadena;
	
	
	public CommandmentDto mandamientoItem;
	
	private static final String VIDEOVIEW_VISIBILITY_STORAGE_KEY = "videoviewvisibility";
	private static final String IMAGEVIEW_VISIBILITY_STORAGE_KEY = "imageviewvisibility";
	private static final String VIDEO_STORAGE_KEY = "viewvideo";
	private static final String BITMAP_STORAGE_KEY = "viewbitmap";
	private static final int ACTION_TAKE_PHOTO_B = 1;
	private static final int ACTION_TAKE_PHOTO_S = 2;
	private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
	 private static final String JPEG_FILE_SUFFIX = ".jpg";
	public static CommandmentDto info = new CommandmentDto();
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	static final int REQUEST_IMAGE_CAPTURE = 1;
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable(BITMAP_STORAGE_KEY, mImageBitmap);
		outState.putParcelable(VIDEO_STORAGE_KEY, mVideoUri);
		outState.putBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY, (mImageBitmap != null) );
		outState.putBoolean(VIDEOVIEW_VISIBILITY_STORAGE_KEY, (mVideoUri != null) );
		outState.putInt("Tipo", tipo);
		outState.putInt("Position1", position1);
		outState.putLong("Position2", position2);
		//outState.putParcelable("mandamiento", info);
//		outState.putLong("id", );
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		//super.onRestoreInstanceState(savedInstanceState);
		mImageBitmap = savedInstanceState.getParcelable(BITMAP_STORAGE_KEY);
		mVideoUri = savedInstanceState.getParcelable(VIDEO_STORAGE_KEY);
		mImageView.setImageBitmap(mImageBitmap);
		mImageView.setVisibility(
				savedInstanceState.getBoolean(IMAGEVIEW_VISIBILITY_STORAGE_KEY) ? 
						ImageView.VISIBLE : ImageView.INVISIBLE
		);
		tipo = savedInstanceState.getInt("Tipo");
		position1 = savedInstanceState.getInt("Position1");
		position2 = savedInstanceState.getLong("Position2");
		
		Log.i("entre al onrestore", "entre al onrestore");
		
//		mVideoView.setVideoURI(mVideoUri);
//		mVideoView.setVisibility(
//				savedInstanceState.getBoolean(VIDEOVIEW_VISIBILITY_STORAGE_KEY) ? 
//						ImageView.VISIBLE : ImageView.INVISIBLE
//		);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pagina2);
//		try {
//			info = savedInstanceState.getParcelable("mandamiento");
//			Log.i("recupere info en el try", "recupere info en el try");
//		} catch (Exception e) {
//			// TODO: handle exception
//			Log.i("no recupere nada y ando en el catch","no recupere nada y ando en el catch");
//		}
//		
//		if(savedInstanceState!=null){
//			
//			tipo = savedInstanceState.getInt("Tipo");
//			position1 = savedInstanceState.getInt("Position1");
//			position2 = savedInstanceState.getLong("Position2");
//			
//		}
		try {
			 tipo = getIntent().getIntExtra("Tipo", 0);
			  position1 = getIntent().getIntExtra("Position1",0);
			  position2 = getIntent().getIntExtra("Position2",0);
			  mandamientoscadena = getIntent().getStringExtra("mandamientoscadena");
			   mandamientorecibido = new Gson().fromJson(mandamientoscadena, commandmentdto2.class);

			  System.out.println();
			  
		} catch (Exception e) {
			// TODO: handle exception
			Log.i("error en el parse", e.getMessage());
		}

	  Log.i("el valor de tipo es", tipo+"");
	  Log.i("el valor de position1 es", position1+"");
	  Log.i("el valor de position2 es", position2+"");
	  
	  
	 
	 
		//Toast.makeText(getApplicationContext(), "el tipo es: "+tipo, Toast.LENGTH_LONG).show();
		  progressDialog = new ProgressDialog(LevantarInformacion2.this);
		  ListaOrdenesAprehension = (LinearLayout)findViewById(R.id.ListaOrdenesAprehension);
		  mImageView = (ImageView) findViewById(R.id.fotoEvidencia);
		  picBtn = (Button) findViewById(R.id.TomarEvidencia2);
			setBtnListenerOrDisable( 
					picBtn, 
					mTakePicOnClickListener,
					MediaStore.ACTION_IMAGE_CAPTURE
					);
		  ImagenAudio = (ImageView)findViewById(R.id.fotoAudio);
		  GrabarAudio = (Button)findViewById(R.id.TomarAudio);
		  ImagenAudio.setImageResource(R.drawable.microfono1);
		  mImageView.setImageResource(R.drawable.camara);
		  
		  EnviarInfo = (Button)findViewById(R.id.EnviarInfo);
			juzgado = (TextView) findViewById(R.id.juzgadoText);
			expediente = (TextView) findViewById(R.id.expedienteText);
			oficio = (TextView) findViewById(R.id.oficioText);
			juez = (TextView) findViewById(R.id.juezText);
			delito = (TextView) findViewById(R.id.juzgadoText);
			requerido = (TextView) findViewById(R.id.requeridoText);
			alias = (TextView) findViewById(R.id.aliasText);
			edad = (TextView) findViewById(R.id.edadText);
			sexo = (TextView) findViewById(R.id.sexoText);
			domicilio = (TextView) findViewById(R.id.domicilioText);
			
	
			
//			if(MainActivityPager.Mandamientos==null)
//			{
//				if(info==null)
//				Log.i("info es nulo","");
//				else
//					Log.i("info no es nulo","");
//				
//				
//			}
	//		else{
			
			
//	  		if(tipo == Constants.OrdenesAprehension){
//	  			info = MainActivityPager.aprehension.get(position1);
//	  			System.out.println();
//	  			
//	  		}else if(tipo == Constants.OrdenesReaprehension){
//	  			info = MainActivityPager.reaprehension.get(position1);
//	  			System.out.println();
//	  		}else if(tipo == Constants.OrdenesPresentacion){
//	  			info = MainActivityPager.presentacion.get(position1);
//	  			System.out.println();
//	  		}else if(tipo == Constants.OrdenesComparecencia){
//	  			info = MainActivityPager.comparecencia.get(position1);
//	  			System.out.println();
//	  		}else if(tipo == Constants.OficiosColaboracion){
//	  			info = MainActivityPager.colaboracion.get(position1);
//	  			System.out.println();
//	  		}else if(tipo == Constants.Traslados){
//	  			info = MainActivityPager.traslados.get(position1);
//	  			System.out.println();
//	  		}
			
	  		if(  mandamientorecibido.getCourt()!=null)
	  		juzgado.setText(mandamientorecibido.getCourt()); 
	  		if(mandamientorecibido.getRecord()!=null)
			expediente.setText(mandamientorecibido.getRecord());
	  		if(mandamientorecibido.getObservations()!=null)
			oficio.setText(mandamientorecibido.getObservations());
			if(mandamientorecibido.getJudge()!=null)
			juez.setText(mandamientorecibido.getJudge());
			if(mandamientorecibido.getOrder()!=null)
			delito.setText(mandamientorecibido.getOrder());
			//if((mandamientorecibido.getRequire().getPerson().getName()!=null && mandamientorecibido.getRequire().getPerson().getFirstName() != null && mandamientorecibido.getRequire().getPerson().getLastName() != null))
			//requerido.setText(info.getRequire().getPerson().getName()+" "+info.getRequire().getPerson().getFirstName()+" "+info.getRequire().getPerson().getLastName());
			if(info.getRequire().getAlias()!=null)
			alias.setText(info.getRequire().getAlias());
			if(info.getRequire().getPerson().getAge()!=null)
			edad.setText(info.getRequire().getPerson().getAge()+"");
			if(info.getRequire().getPerson().getSex()!=null)
			sexo.setText(info.getRequire().getPerson().getSex());
			if(info.getAddress()!=null)
			domicilio.setText(info.getAddress());
	//		}
//			mImageView.setVisibility(View.GONE);
//			ImagenAudio.setVisibility(View.GONE);
			ImagenAudio.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
				//	public void reproducir(View v) {
						player.start();
//						b1.setEnabled(false);
//						b2.setEnabled(false);
//						b3.setEnabled(false);
//						tv1.setText("Reproduciendo");
				//	}
				}
			});
			ImagenAudio.setEnabled(false);
			
			GrabarAudio.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(!grabando){
						 GrabarAudio.setText("detener grabacion");
						 grabando = true;
						 grabar();
					}else{
						grabando = false;
						GrabarAudio.setText("iniciar Grabacion");
						detener();
					}
				}
			});
			
			EnviarInfo.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
			        //Set the dialog title to 'Loading...'.  
			        progressDialog.setTitle("Enviando Informaci��n...");  
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
					new EnviarInformacion().execute();
				}
			});
	  	
//	  		setBtnListenerOrDisable( 
//					picBtn, 
//					mTakePicOnClickListener,
//					MediaStore.ACTION_IMAGE_CAPTURE
//					);
	  		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
				mAlbumStorageDirFactory = new FroyoAlbumDirFactory();
			} else {
				mAlbumStorageDirFactory = new BaseAlbumDirFactory();
			}
		  
	}

	Button.OnClickListener mTakePicOnClickListener = 
			new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				dispatchTakePictureIntent(ACTION_TAKE_PHOTO_B);
			}
		};
		private void setBtnListenerOrDisable( 
				Button btn, 
				Button.OnClickListener onClickListener,
				String intentName
		) {
			if (isIntentAvailable(this, intentName)) {
				btn.setOnClickListener(onClickListener);        	
			} else {
				btn.setText( 
					 "can not " + btn.getText());
				btn.setClickable(false);
			}
		}
		public static boolean isIntentAvailable(Context context, String action) {
			final PackageManager packageManager = context.getPackageManager();
			final Intent intent = new Intent(action);
			List<ResolveInfo> list =
				packageManager.queryIntentActivities(intent,
						PackageManager.MATCH_DEFAULT_ONLY);
			return list.size() > 0;
		}
		private void dispatchTakePictureIntent() {
		    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
		        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		    }
		}
		private void dispatchTakePictureIntent(int actionCode) {

			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

			switch(actionCode) {
			case ACTION_TAKE_PHOTO_B:
				File f = null;
				
				try {
					f = setUpPhotoFile();
					mCurrentPhotoPath = f.getAbsolutePath();
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
				} catch (IOException e) {
					e.printStackTrace();
					f = null;
					mCurrentPhotoPath = null;
				}
				break;

			default:
				break;			
			} // switch

			startActivityForResult(takePictureIntent, actionCode);
		}
	public void grabar() {
		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		//recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		//recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
		
		// mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		 //   mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
	

		recorder.setAudioChannels(1);
		recorder.setAudioSamplingRate(44100);
		    recorder.setAudioEncodingBitRate(96000);
		    
		
		
		
		File pathGrabacion = new File(Environment.getExternalStorageDirectory()
				.getPath());
		try {
			archivo = File.createTempFile("temporal", ".m4a", pathGrabacion);
			pathAudio = archivo.getPath();
		} catch (IOException e) {
		}
		recorder.setOutputFile(archivo.getAbsolutePath());
		try {
			recorder.prepare();
		} catch (IOException e) {
		}
		recorder.start();
	
	}
	public void detener() {
		recorder.stop();
		recorder.release();
		player = new MediaPlayer();
		//player.setOnCompletionListener(this);
		try {
			player.setDataSource(archivo.getAbsolutePath());
		} catch (IOException e) {
		}
		try {
			player.prepare();
		} catch (IOException e) {
		}
		ImagenAudio.setImageResource(R.drawable.reproducir);
		ImagenAudio.setEnabled(true);
//		b1.setEnabled(true);
//		b2.setEnabled(false);
//		b3.setEnabled(true);
//		tv1.setText("Listo para reproducir");
		
	}
	public void abrirCamara(boolean tipoEvidencia){
		Intent intento = null;
		File file = null;
		
		if(tipoEvidencia){
			try {
				file = createImageFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mCurrentPhotoPath = file.getAbsolutePath();
			path = file.getPath();
			intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intento.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			startActivityForResult(intento, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		}
//		
//		edit = preferences.edit();
//		edit.putString(Utl_Constantes.PRE_PATH_EVIDENCIA, file.getPath());
//		edit.commit();
	}
	private File createImageFile() throws IOException {
		// Create an image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		//String imageFileName = JPEG_FILE_PREFIX + timeStamp;
		String imageFileName = "vfdc";
		File albumF = getAlbumDir();
		File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
		return imageF;
	}
	private File setUpPhotoFile() throws IOException {
		
		File f = createImageFile();
		mCurrentPhotoPath = f.getAbsolutePath();
		
		return f;
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		System.out.println();
		switch (requestCode) {
		case ACTION_TAKE_PHOTO_B: {
			if (resultCode == RESULT_OK) {
				handleBigCameraPhoto();
			}
			break;
		} // ACTION_TAKE_PHOTO_B

		case ACTION_TAKE_PHOTO_S: {
			if (resultCode == RESULT_OK) {
				handleSmallCameraPhoto(data);
			}
			break;
		} // ACTION_TAKE_PHOTO_S

//		case ACTION_TAKE_VIDEO: {
//			if (resultCode == RESULT_OK) {
//				handleCameraVideo(data);
//			}
//			break;
//		} // ACTION_TAKE_VIDEO
		} // switch
	}
	private void handleBigCameraPhoto() {

		if (mCurrentPhotoPath != null) {
			setPic();
			galleryAddPic();
			mCurrentPhotoPath = null;
		}

	}
	private void galleryAddPic() {
	    Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		File f = new File(mCurrentPhotoPath);
	    Uri contentUri = Uri.fromFile(f);
	    mediaScanIntent.setData(contentUri);
	    this.sendBroadcast(mediaScanIntent);
}
	private void handleSmallCameraPhoto(Intent intent) {
		Bundle extras = intent.getExtras();
		mImageBitmap = (Bitmap) extras.get("data");
		mImageView.setImageBitmap(mImageBitmap);
		mVideoUri = null;
		mImageView.setVisibility(View.VISIBLE);
		//mVideoView.setVisibility(View.INVISIBLE);
	}
	private File getAlbumDir() {
		File storageDir = null;

		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			String name = getAlbumName();
			
			storageDir = mAlbumStorageDirFactory.getAlbumStorageDir(name);

			if (storageDir != null) {
				if (! storageDir.mkdirs()) {
					if (! storageDir.exists()){
						Log.d("CameraSample", "failed to create directory");
						return null;
					}
				}
			}
			
		} else {
			Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
		}
		
		return storageDir;
	}
	private void setPic() {

		/* There isn't enough memory to open up more than a couple camera photos */
		/* So pre-scale the target bitmap into which the file is decoded */

		/* Get the size of the ImageView */
		int targetW = mImageView.getWidth();
		int targetH = mImageView.getHeight();

		/* Get the size of the image */
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;
		
		/* Figure out which way needs to be reduced less */
		int scaleFactor = 1;
		if ((targetW > 0) || (targetH > 0)) {
			scaleFactor = Math.min(photoW/targetW, photoH/targetH);	
		}

		/* Set bitmap options to scale the image decode target */
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		/* Decode the JPEG file into a Bitmap */
		Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
		imagenphoto = bitmap;
		path = mCurrentPhotoPath;
		
		/* Associate the Bitmap to the ImageView */
		
		mImageView.setImageBitmap(bitmap);
		mVideoUri = null;
		mImageView.setVisibility(View.VISIBLE);
		//mVideoView.setVisibility(View.INVISIBLE);
	}
	
	private String getAlbumName() {
		return "vfd";
	}
	public class EnviarInformacion extends AsyncTask<Void, Void, Void> {

		
		private EnviarInformacion() {
	      
	    }
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			 
		        //Set the progress dialog to display a horizontal bar .  
		
			super.onPreExecute();
		}
		@Override
		protected Void doInBackground(Void... params) {
			Long idUsuario = 0l;
			Long idDetenido = 0l;
			
			//Obtencion de el ultimo usuario registrado
//			try {
//				CollectionResponseUsuario lastUser = usuarioEndpoint.getLast().execute();
//				if(lastUser != null && lastUser.getItems().size() > 0 ){
//					idUsuario =  lastUser.getItems().get(0).getId();
//				}
//				
//				Usuario user2 = new Usuario();
//				user2.setApm(apm.getText().toString());
//				user2.setApp(app.getText().toString());
//				user2.setEdad(new Long(edad.getText().toString()));
//				user2.setId(user.getId());
//				user2.setName(nombre.getText().toString());
//				user2.setSexo(user.getSexo());
//				usuarioEndpoint.updateUsuario(user2).execute();
//				
//				
//				
//				
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			


			
			Utl_HttpClient resposeGson = new Utl_HttpClient();
			Utl_HttpClient resposeGson2 = new Utl_HttpClient();
			String urlBlobStore = resposeGson.getUrlBlobStore("http://1-dot-civic-athlete-851.appspot.com/blob/androidserveurl");
			String urlBlobStoreAudio = resposeGson.getUrlBlobStore("http://1-dot-civic-athlete-851.appspot.com/blob/androidserveurl");
			String blobkey = null ;
			String blobkeyAudio = null;
			ArrayList<String> evidencias = new ArrayList<String>();
			ArrayList<String> audios = new ArrayList<String>();
			Log.i("la url para insertar es: +",urlBlobStore);
			if(urlBlobStore != null){
				try {
	//				int formato = Utl_Imagen.getFormatoInt(imagen.getFormato());
				
				
					if(path!=null && !path.equalsIgnoreCase(""))
					{
					Utl_Imagen.procesarImagen(path);
					//blobkey =  resposeGson2.setMultimedia(urlBlobStore, path,Constants.Audio);
					blobkey = resposeGson2.setMultimedia(urlBlobStore, path, Constants.Imagen);
					Log.i("blobkey", blobkey);
					evidencias.add(blobkey);
					info.setEvidence(evidencias);
					}
					if(pathAudio!=null && !
							pathAudio.equalsIgnoreCase("")){
						 blobkeyAudio =  resposeGson.setMultimedia(urlBlobStoreAudio, pathAudio,Constants.Audio);
						 Log.i("blobkeyAudio", blobkeyAudio);
							audios.add(blobkeyAudio);
							info.setAudio(audios);
					
					}
			
					info.setStatus(true);
					com.virtualef.pgj.service.commandmentService.model.RequireDto requerido = info.getRequire();
					com.virtualef.pgj.service.commandmentService.model.AgentDto agente = info.getAgent();
					info.setAgent(null);
					info.setRequire(null);
					
				
					EndPointsInicializacion endpoints = new EndPointsInicializacion();
					//agentEndpoint = endpoints.InicializacionAgent();
					CommandmentService MandamientoEndpoint = endpoints.InicializacionMandamiento();
					try {
						MandamientoEndpoint.updateCommandments(info).execute();
						info.setAgent(agente);
						info.setRequire(requerido);
						Log.i("Envio correcto", "Envio correcto");
						enviado = true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.i("Error en el update", e.getMessage());
					}
					
			
					// Actualizar BD
				} catch (UnsupportedEncodingException e) {
				
				}
			}
		

		

			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			
			progressDialog.dismiss();
			if(enviado)
				Toast.makeText(getApplicationContext(), "Enviado Correctamente", Toast.LENGTH_LONG).show();
			onBackPressed();
			
			
			
			super.onPostExecute(result);
		}

	}
}
