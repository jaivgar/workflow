package org.hasder.workflowexecutor;

import java.util.ArrayList;

import org.hasder.workflowexecutor.SenMLPack;
import org.hasder.workflowexecutor.SenMLRecord;

import com.google.gson.Gson;

public class PresentationData {
	String id;
	String status;
	String text;
	String image;
	String progress;
	
	public PresentationData() {
		
	}
	
	public PresentationData(String d) {
		//this.fromSenMLPack(SenMLPack.fromJSON(d));
		this.fromJsonString(d);
	}
	
	//[{\"id\":\"uri:example\",\"status\": \"incomplete\",\"text\": \"Nutrunner\",\"count\": \"5\",\"image\": \"na\",\"progress\": \"0/5\"}, {\"id\":61,\"status\": \"incomplete\",\"text\": \"part assure\",\"count\": \"1\",\"image\": \"\",\"progress\": \"0/1\"}, {\"id\":56789,\"status\": \"incomplete\",\"text\": \"Work completed (Quality check)\",\"count\": \"1\",\"image\": \"na\",\"progress\": \"0/1\"}]
	
	public String toJsonString() {
		
		return new Gson().toJson(this);
		
	}
	
	public boolean fromJsonString(String json) {
		
		PresentationData temp = new Gson().fromJson(json, this.getClass());
		this.id = temp.id;
		this.status = temp.status;
		this.text = temp.text;
		this.image = temp.image;
		this.progress = temp.progress;
		
		return true;
		
	}
	
	
	
	@SuppressWarnings("unused")
	private String toSenMLString() {
		
		SenMLPack senMLPack = new SenMLPack();
		ArrayList<SenMLRecord> records = new ArrayList<SenMLRecord>();

		records.add(new SenMLRecord("id", id));
		records.add(new SenMLRecord("status", status));
		records.add(new SenMLRecord("text", text));
		records.add(new SenMLRecord("image", image));
		records.add(new SenMLRecord("progress", progress));		
		
		senMLPack.addRecords(records);
		
		return senMLPack.toJSON();

	}
	
	@SuppressWarnings("unused")
	private boolean fromSenMLPack(SenMLPack senMLPack) {
		try {
			if(senMLPack != null) {
				id = senMLPack.getRecordByName("id").getVs();
				status = senMLPack.getRecordByName("status").getVs();
				text = senMLPack.getRecordByName("text").getVs();
				image = senMLPack.getRecordByName("image").getVs();
				progress = senMLPack.getRecordByName("progress").getVs();
			} else {
				return false;
			}
			
		} catch(Exception e) {
			//e.printStackTrace();
			System.err.println("ERR: Invalid PresentationData!");
			return false;
		}
		
		return true;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	
	

	
}
