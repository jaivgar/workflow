package org.hasder.nutrunner;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class ServiceRegistration {

	private final ScheduledExecutorService schedular = Executors.newScheduledThreadPool(1);
String ownip = "";
String serviceregistryip = "";
	public ServiceRegistration(String ownip, String serviceregistryip) {
		this.ownip = ownip;
		this.serviceregistryip = serviceregistryip;
	}

	public boolean doPeriodicRegistration() {

		schedular.scheduleWithFixedDelay(runnableRegister, 1, 30, TimeUnit.SECONDS);

		return false;

	}

	final Runnable runnableRegister = new Runnable() {
		public void run() {
			doRegistration();
		}
	};

	public boolean doRegistration() {

		StringBuilder serviceBuilder = new StringBuilder();
		serviceBuilder.append("{\"name\":\"nutrunner-abc123\",");
		serviceBuilder.append("\"type\":\"nutrunner._http-json._tcp\",");
		serviceBuilder.append("\"host\":\""+ownip+"\", \"port\":\"8085\",");
		//serviceBuilder.append("\"host\":\"192.168.0.37\", \"port\":\"8085\",");
		serviceBuilder.append("\"domain\":\"unknown\",");
		serviceBuilder.append("\"properties\":{");
		serviceBuilder.append("\"property\":");
		serviceBuilder.append("[");
		serviceBuilder.append("{");
		serviceBuilder.append("\"name\":\"version\",");
		serviceBuilder.append("\"value\":\"1.0\"");
		serviceBuilder.append("},");
		serviceBuilder.append("{");
		serviceBuilder.append("\"name\":\"path\",");
		serviceBuilder.append("\"value\":\"/job\",");
		serviceBuilder.append("\"loc\":\"Station-01\"");
		serviceBuilder.append("}");
		serviceBuilder.append("]");
		serviceBuilder.append("}}");

		String service = serviceBuilder.toString();
		System.out.println(service);

		// "http://130.240.234.222:1100/servicediscovery/service/";
		//String srurl = "http://192.168.0.37:1100/servicediscovery/publish/";
		String srurl = "http://"+serviceregistryip+":1100/servicediscovery/publish/";
		//String srurl = "http://192.168.0.33:1100/servicediscovery/publish/";

		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost regRequest = new HttpPost(srurl);
			regRequest.addHeader("content-type", "application/json");

			regRequest.setEntity(new StringEntity(service));

			HttpResponse response = httpClient.execute(regRequest);

			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return false;

	}
}
