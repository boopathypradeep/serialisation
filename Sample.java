package org.serialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Sample extends BaseClass  {
	public static String logtoken;
	public static int address_id=1039;
	@Test(priority=1)
	private void login() throws IOException {
getHeader("Content-Type", "application/json", "");
authentication(readPropertyFile("username"), readPropertyFile("password"));
Response response = getMethods("POST", Endpoint.LOGIN);
Login_Output_Pojo as = response.as(Login_Output_Pojo.class);
 logtoken = as.getData().getLogtoken();
System.out.println(logtoken);
String message = as.getMessage();
System.out.println(message);
Assert.assertEquals(as.getMessage(), "Login successfully");

	}
	@Test(priority=2)
	private void loginWrite() throws IOException {
	List<Header>l= new ArrayList<Header>();
	Header head=new Header("accept", "application/json");
	Header head1=new Header("Content-Type", "application/json");
	Header head2=new Header("Authorization", "Bearer "+logtoken);
		l.add(head);
		l.add(head1);
		l.add(head2);
		Headers header=new Headers(l); 
		getHeaders(header);
		Add_Address_Input address=new Add_Address_Input("Boopathy", "pradeep", "987654321", "good appartments", 32, 3, 4, "636008", "address", "address type");
		getBodyObject(address);
	Response response = getMethods("POST", Endpoint.ADD_ADDRESS);
	System.out.println(getResponseCode(response));
	System.out.println(getPrettyString(response));
	Add_Address_Output out = response.as(Add_Address_Output.class);
	System.out.println(out.getAddress_id());
	Assert.assertEquals(out.getMessage(), "Address added successfully","address added successfully");
	}
	@Test(priority=3)
	private void putAddress() {
		List<Header>l= new ArrayList<Header>();
		Header head=new Header("accept", "application/json");
		Header head1=new Header("Content-Type", "application/json");
		Header head2=new Header("Authorization", "Bearer "+logtoken);
			l.add(head);
			l.add(head1);
			l.add(head2);
			Headers header=new Headers(l); 
			getHeaders(header);
			UpdateAddress_Input in=new UpdateAddress_Input(""+address_id+"", "hello", "hai", "9876543210", "hai buy", 32, 4, 5, "636009", "address", "address type");
			getBodyObject(in);
			
			Response response = getMethods("PUT", Endpoint.UPDATE_ADDRESS);
			System.out.println(getResponseCode(response));
			System.out.println(getPrettyString(response));
			PutAddress_Output output = response.as(PutAddress_Output.class);
			Assert.assertEquals(output.getMessage(), "Address updated successfully","address updated successfully");
			

	}
	@Test(priority=4)
	private void getAddress() {
		List<Header>l= new ArrayList<Header>();
		Header head=new Header("accept", "application/json");
		Header head1=new Header("Content-Type", "application/json");
		Header head2=new Header("Authorization", "Bearer "+logtoken);
			l.add(head);
			l.add(head1);
			l.add(head2);
			Headers header=new Headers(l); 
			getHeaders(header);
			Response response = getMethods("GET", Endpoint.GET_ADDRESS);
			System.out.println(getPrettyString(response)); 
			System.out.println(getResponseCode(response));
			



	}
	@Test(priority=5)
	private void deleteAddress() {
		List<Header>l= new ArrayList<Header>();
		Header head=new Header("accept", "application/json");
		Header head1=new Header("Content-Type", "application/json");
		Header head2=new Header("Authorization", "Bearer "+logtoken);
			l.add(head);
			l.add(head1);
			l.add(head2);
			Headers header=new Headers(l); 
			getHeaders(header);
		DeleteAddress_Input in=new DeleteAddress_Input(""+address_id+""); 
			getBodyObject(in);
		Response reponse = getMethods("DELETE",Endpoint.DELETE_ADDRESS);
		System.out.println(getResponseCode(reponse));
		System.out.println(getPrettyString(reponse));
		DeleteAddress_Output as = reponse.as(DeleteAddress_Output.class);
Assert.assertEquals(as.getMessage(), "Address deleted successfully","deleted address successfully");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
