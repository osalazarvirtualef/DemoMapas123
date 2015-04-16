package com.demomapas;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demomapas.endpoints.EndPointsInicializacion;
import com.demomapas.pjgviewpager.MainActivityPager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.virtualef.pgj.service.agentService.model.AgentDto;
import com.virtualef.pgj.service.commandmentService.CommandmentService;
import com.virtualef.pgj.service.commandmentService.model.CommandmentDto;
import com.virtualef.pgj.service.requireService.model.RequireDto;

import android.R.color;
import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;;

public class Pagina1 extends Fragment{
	
	LinearLayout ListaOrdenesAprehension;
	TextView elementos1;
	TextView elementos2;
	 ViewGroup rootView ;
	 ImageView mImageView;
	 ImageView ImagenAudio;
	 Button GrabarAudio;
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
	public boolean grabando = false;
	ProgressDialog progressDialog;
	 int tipo;
	 int position1;
	 long position2;
	 Button EnviarInfo;
	 MediaRecorder recorder;
	 File archivo;
	 MediaPlayer player;
	 String pathAudio;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	
	
	private final static String CAPTURED_PHOTO_PATH_KEY = "mCurrentPhotoPath";
	private final static String CAPTURED_PHOTO_URI_KEY = "mCapturedImageURI";
	//private String mCurrentPhotoPath = null;
	private Uri mCapturedImageURI = null;

	 private AlbumStorageDirFactory mAlbumStorageDirFactory = null;
	 
	 
	 
	 
	 //variables para la fotografia 
	 private static final int ACTION_TAKE_PHOTO_B = 1;
		private String mCurrentPhotoPath;
		private static final String JPEG_FILE_SUFFIX = ".jpg";
		Button picBtn;
		static Bitmap imagenphoto;
		public static String path;
		private Uri mVideoUri;		
		private static final int ACTION_TAKE_PHOTO_S = 2;
		private Bitmap mImageBitmap;
		public static CommandmentDto info = new CommandmentDto();
		
	
		public Pagina1() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onSaveInstanceState(Bundle savedInstanceState) {
		    if (mCurrentPhotoPath != null) {
		        savedInstanceState.putString(CAPTURED_PHOTO_PATH_KEY, mCurrentPhotoPath);
		    }
		    if (mCapturedImageURI != null) {
		        savedInstanceState.putString(CAPTURED_PHOTO_URI_KEY, mCapturedImageURI.toString());
		    }
		    super.onSaveInstanceState(savedInstanceState);
		}

		

	  @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
		  Bundle args = getArguments();
		  progressDialog = new ProgressDialog(getActivity());  
		  tipo = args.getInt("Tipo");
		  position1 = args.getInt("Position1");
		  position2 = args.getLong("Position2");
		  
		  
	         rootView = (ViewGroup) inflater.inflate(
	                R.layout.activity_pagina2, container, false);
	        ListaOrdenesAprehension = (LinearLayout) rootView.findViewById(R.id.ListaOrdenesAprehension);
	    
	       // ListaOrdenesAprehension.addView(elementos1);
	    	mImageView = (ImageView)rootView.findViewById(R.id.fotoEvidencia);
		picBtn = (Button) rootView.findViewById(R.id.TomarEvidencia2);
		
		ImagenAudio = (ImageView)rootView.findViewById(R.id.fotoAudio);
		GrabarAudio = (Button)rootView.findViewById(R.id.TomarAudio);
		ImagenAudio.setImageResource(R.drawable.microfono1);
		mImageView.setImageResource(R.drawable.camara);
		
		EnviarInfo = (Button) rootView.findViewById(R.id.EnviarInfo);

		
		
		
		juzgado = (TextView) rootView.findViewById(R.id.juzgadoText);
		expediente = (TextView) rootView.findViewById(R.id.expedienteText);
		oficio = (TextView) rootView.findViewById(R.id.oficioText);
		juez = (TextView) rootView.findViewById(R.id.juezText);
		delito = (TextView) rootView.findViewById(R.id.juzgadoText);
		requerido = (TextView) rootView.findViewById(R.id.requeridoText);
		alias = (TextView) rootView.findViewById(R.id.aliasText);
		edad = (TextView) rootView.findViewById(R.id.edadText);
		sexo = (TextView) rootView.findViewById(R.id.sexoText);
		domicilio = (TextView) rootView.findViewById(R.id.domicilioText);
	  		
		

	        return rootView;
	    }

	  	@Override
	  	public void onActivityCreated(Bundle savedInstanceState) {
	  		// TODO Auto-generated method stub
	  		super.onActivityCreated(savedInstanceState);

	  		
	  		if(tipo == Constants.OrdenesAprehension){
	  			info = MainActivityPager.aprehension.get(position1);
	  			System.out.println();
	  			
	  		}else if(tipo == Constants.OrdenesReaprehension){
	  			info = MainActivityPager.reaprehension.get(position1);
	  			System.out.println();
	  		}else if(tipo == Constants.OrdenesPresentacion){
	  			info = MainActivityPager.presentacion.get(position1);
	  			System.out.println();
	  		}else if(tipo == Constants.OrdenesComparecencia){
	  			info = MainActivityPager.comparecencia.get(position1);
	  			System.out.println();
	  		}else if(tipo == Constants.OficiosColaboracion){
	  			info = MainActivityPager.colaboracion.get(position1);
	  			System.out.println();
	  		}else if(tipo == Constants.Traslados){
	  			info = MainActivityPager.traslados.get(position1);
	  			System.out.println();
	  		}
	  		if(info.getCourt()!=null)
	  		juzgado.setText(info.getCourt()); 
	  		if(info.getRecord()!=null)
			expediente.setText(info.getRecord());
	  		if(info.getObservations()!=null)
			oficio.setText(info.getObservations());
			if(info.getJudge()!=null)
			juez.setText(info.getJudge());
			if(info.getOrder()!=null)
			delito.setText(info.getOrder());
			if((info.getRequire().getPerson().getName()!=null && info.getRequire().getPerson().getFirstName() != null && info.getRequire().getPerson().getName() != null))
			requerido.setText(info.getRequire().getPerson().getName()+" "+info.getRequire().getPerson().getFirstName()+" "+info.getRequire().getPerson().getLastName());
			if(info.getRequire().getAlias()!=null)
			alias.setText(info.getRequire().getAlias());
			if(info.getRequire().getPerson().getAge()!=null)
			edad.setText(info.getRequire().getPerson().getAge()+"");
			if(info.getRequire().getPerson().getSex()!=null)
			sexo.setText(info.getRequire().getPerson().getSex());
			if(info.getAddress()!=null)
			domicilio.setText(info.getAddress());
			
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
					new EnviarInformacion().execute();
				}
			});
	  		picBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					abrirCamara(true);
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
	  	
	  	
	  	//método para la grabacion de audio
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
		
		//detenemos la grabacion de audio
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
//			b1.setEnabled(true);
//			b2.setEnabled(false);
//			b3.setEnabled(true);
//			tv1.setText("Listo para reproducir");
			
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
//			edit = preferences.edit();
//			edit.putString(Utl_Constantes.PRE_PATH_EVIDENCIA, file.getPath());
//			edit.commit();
		}
		
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			switch (requestCode) {
			case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
				if(resultCode == Activity.RESULT_OK){
					System.out.println();
					handleBigCameraPhoto();
//					Intent intento = new Intent(getActivity(), Act_Descripcion_Evidencia.class);
//					intento.putExtra("idObra", idObra);
//					intento.putExtra("tipo", true);
//					intento.putExtra("tipoMultimedia", true);
//					intento.putExtra("pathImagen", preferences.getString(Utl_Constantes.PRE_PATH_EVIDENCIA, Utl_Constantes.PRE_DEFAULT));
//					intento.putExtra("descripcion", "");
//					startActivity(intento);
				}
				
				break;

			default:
				break;
			}
			
	//		this.dismiss();
		}
	  	////////////////////////////métodos de la fotografía
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
				if (isIntentAvailable(getActivity(), intentName)) {//////checar esto
					btn.setOnClickListener(onClickListener);        	
				} else {
					btn.setText( 
						 "can not " + btn.getText());
					btn.setClickable(false);
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
			public static boolean isIntentAvailable(Context context, String action) {
				final PackageManager packageManager = context.getPackageManager();
				final Intent intent = new Intent(action);
				List<ResolveInfo> list =
					packageManager.queryIntentActivities(intent,
							PackageManager.MATCH_DEFAULT_ONLY);
				return list.size() > 0;
			}
			private File setUpPhotoFile() throws IOException {
				
				File f = createImageFile();
				mCurrentPhotoPath = f.getAbsolutePath();
				
				return f;
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
			private String getAlbumName() {
				return "vfd";
			}
			private void handleBigCameraPhoto() {

				if (mCurrentPhotoPath != null) {
					setPic();
					galleryAddPic();
					mCurrentPhotoPath = null;
				}

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
			private void handleSmallCameraPhoto(Intent intent) {
				Bundle extras = intent.getExtras();
				mImageBitmap = (Bitmap) extras.get("data");
				mImageView.setImageBitmap(mImageBitmap);
				mVideoUri = null;
				mImageView.setVisibility(View.VISIBLE);
				//mVideoView.setVisibility(View.INVISIBLE);
			}
			private void galleryAddPic() {
			    Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
				File f = new File(mCurrentPhotoPath);
			    Uri contentUri = Uri.fromFile(f);
			    mediaScanIntent.setData(contentUri);
			  //  this.sendBroadcast(mediaScanIntent);
		}
			//ciclo de vida de el fragment
			
			@Override
			public void onCreate(Bundle savedInstanceState) {
				// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
			}
			@Override
			public void onResume() {
				// TODO Auto-generated method stub
				super.onResume();
				Log.i("ciclo de vida ", "onResume");
				if(path != null)
				Log.i("el valor de path", path );
			}
			
			@Override
			public void onPause() {
				// TODO Auto-generated method stub
				super.onPause();
				Log.i("ciclo de vida ", "onPause");
			}
			@Override
			public void onStop() {
				// TODO Auto-generated method stub
				super.onStop();
				Log.i("ciclo de vida ", "onStop");
			}
			@Override
			public void onDestroyView() {
				// TODO Auto-generated method stub
				super.onDestroyView();
				Log.i("ciclo de vida ", "onDestroyView");
			}
			@Override
			public void onDestroy() {
				// TODO Auto-generated method stub
				super.onDestroy();
				Log.i("ciclo de vida ", "onDestroy");
			}

			public class EnviarInformacion extends AsyncTask<Void, Void, Void> {

				
				private EnviarInformacion() {
			      
			    }
				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					 
				        //Set the progress dialog to display a horizontal bar .  
				        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
				        //Set the dialog title to 'Loading...'.  
				        progressDialog.setTitle("Enviando Información...");  
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
					super.onPreExecute();
				}
				@Override
				protected Void doInBackground(Void... params) {
					Long idUsuario = 0l;
					Long idDetenido = 0l;
					
					//Obtencion de el ultimo usuario registrado
//					try {
//						CollectionResponseUsuario lastUser = usuarioEndpoint.getLast().execute();
//						if(lastUser != null && lastUser.getItems().size() > 0 ){
//							idUsuario =  lastUser.getItems().get(0).getId();
//						}
//						
//						Usuario user2 = new Usuario();
//						user2.setApm(apm.getText().toString());
//						user2.setApp(app.getText().toString());
//						user2.setEdad(new Long(edad.getText().toString()));
//						user2.setId(user.getId());
//						user2.setName(nombre.getText().toString());
//						user2.setSexo(user.getSexo());
//						usuarioEndpoint.updateUsuario(user2).execute();
//						
//						
//						
//						
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					


					
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
						Toast.makeText(getActivity(), "Enviado Correctamente", Toast.LENGTH_LONG).show();
					getActivity().onBackPressed();
					
					
					
					super.onPostExecute(result);
				}

			}
}
