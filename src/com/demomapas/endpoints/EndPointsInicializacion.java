package com.demomapas.endpoints;
import com.demomapas.CloudEndpointUtils;
import com.demomapas.deviceinfoendpoint.Deviceinfoendpoint;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.virtualef.pgj.service.agentService.AgentService;
import com.virtualef.pgj.service.agentService.model.AgentDto;
import com.virtualef.pgj.service.commandmentService.CommandmentService;
import com.virtualef.pgj.service.locationService.LocationService;


public class EndPointsInicializacion {
	
	public EndPointsInicializacion() {
		// TODO Auto-generated constructor stub
	}
	
	public AgentService InicializacionAgent(){
		AgentService AgentEndpoint;
		AgentService.Builder agentEndpointbuilder = new AgentService.Builder(
				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
				new HttpRequestInitializer() {

					@Override
					public void initialize(HttpRequest arg0) {
						// TODO Auto-generated method stub
					}
				});
	
		AgentEndpoint = CloudEndpointUtils.updateBuilder(agentEndpointbuilder).build();
		return AgentEndpoint;
		
		
	}
	   
	public CommandmentService InicializacionMandamiento(){
		
		CommandmentService MandamientoEndpoint;

		CommandmentService.Builder mandamientoEndpointbuilder = new CommandmentService.Builder(
				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
				new HttpRequestInitializer() {

					@Override
					public void initialize(HttpRequest arg0) {
						// TODO Auto-generated method stub
					}
				});	
		MandamientoEndpoint = CloudEndpointUtils.updateBuilder(mandamientoEndpointbuilder).build();
		return MandamientoEndpoint;
		
		
	}
	public LocationService InicializacionLocation(){

		LocationService UbicacionesEndpoint;

		LocationService.Builder ubicacionesEndpointbuilder = new LocationService.Builder(
				AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
				new HttpRequestInitializer() {

					@Override
					public void initialize(HttpRequest arg0) {
						// TODO Auto-generated method stub
					}
				});	
		UbicacionesEndpoint = CloudEndpointUtils.updateBuilder(ubicacionesEndpointbuilder).build();
		return UbicacionesEndpoint;
		
		
	}
	
	

}
