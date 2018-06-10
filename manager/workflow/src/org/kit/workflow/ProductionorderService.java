package org.kit.workflow;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import it.eng.productunithubledgerclient.model.ProcessStep;

/*
 * ProductionorderService
 * Description: This service will consume the production order service of the intelligent product.
 * 
 */

public class ProductionorderService {

	String providerEndpoint = "";

	Consumer consumer = new Consumer();
	Thread consumerThread = new Thread(consumer);
	
	List<String> queueue = Collections.synchronizedList(new LinkedList<String>());
	
	TheBusiness theBusiness;
	

	public ProductionorderService(String endpoint) {
		this.providerEndpoint = endpoint;

	}

	public void startServiceConsumer() {
		consumerThread.start();
	}

	public void setProviderEndpoint(String endpoint) {
		this.providerEndpoint = endpoint;
	}

	public void setTheBusiness(TheBusiness business){
		this.theBusiness = business;
	}

	public boolean getProductionOrder(String chassiId) {

		System.out.println("getProductionOrder");
		// make a linkedlist queue
		queueue.add(chassiId);
		// add the get production order request ot he queue
		// make a separate thread that will dequeue the request and send the get
		// request to the intelligent product
		// it will then update the statemachineblablabla

		return true;

	}

	private class Consumer implements Runnable {

		@Override
		public void run() {

			while (true) {
				if (!queueue.isEmpty()) {
					System.out.println("Consumer !queueue.isEmpty()");
					String chassiId = queueue.remove(0);// remove the first one
					//String srurl = providerEndpoint;
					//String srurl = "http://127.0.0.1:8090/servicediscovery/type?chassiid=A   819631&stationid=CTPP-01A";

					try {
						URIBuilder uriBuilder = new URIBuilder("http://127.0.0.1:8090/servicediscovery/type");
						//uriBuilder.setParameter("chassiid", "A   819631");
						uriBuilder.setParameter("chassiid", chassiId);
						uriBuilder.setParameter("stationid", "CTPP-01A");
						HttpClient httpClient = HttpClientBuilder.create().build();
//						HttpGet getRequest = new HttpGet(srurl);
						System.out.println(uriBuilder.toString());
						HttpGet getRequest = new HttpGet(uriBuilder.toString());
						getRequest.addHeader("accept", "application/json");

						HttpResponse response = httpClient.execute(getRequest);

						System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

						if (response.getStatusLine().getStatusCode() != 200) {
							throw new RuntimeException(
									"Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
						}
						String body = EntityUtils.toString(response.getEntity());
												
						System.out.println("body: " + body);
						theBusiness.performProductionorder(body);

					} catch (ClientProtocolException e) {

						e.printStackTrace();

					} catch (IOException e) {

						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}

}
