package com.br.agile.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tomcat.util.codec.binary.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;

@Entity
@Table(name = "Event")
public class Event {
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private Integer id; 
    
    private String eventTitle;
    private String description; 
    private Timestamp date;   
	
    @Column(name = "id", nullable = false)
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) { 
		this.id = id;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getDescription() { 
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
    public String getQRCodeImage() {
    	try {
    		int width = 400; 
    		int height = 400; 
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        
	        BitMatrix bitMatrix = qrCodeWriter.encode("EVENT|" + String.valueOf(this.getId()) + "|" + this.getEventTitle(), BarcodeFormat.QR_CODE, width, height);
	        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	        MatrixToImageWriter.writeToStream(bitMatrix, "JPEG", pngOutputStream);
	        byte[] pngData = pngOutputStream.toByteArray(); 
	        return Base64.encodeBase64String(pngData);
    	} catch(Exception e) {
    		return "";  
    	}
    }
}
