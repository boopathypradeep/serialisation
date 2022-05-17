package org.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	static RequestSpecification specification;
	static Response response;
	
	
	
	
	public void getHeader(String key, String value, String dummy) {
		 specification = RestAssured.given().header(key, value, dummy);

	}
	
public void getHeaders(Headers header) {
	
	specification = RestAssured.given().headers(header);
}

	
	public void getPathparameter(String key, String value) {
		specification= specification.pathParam(key, value);

	}
public void getQueryparameter(String key, String value) {
	specification=specification.queryParam(key, value);

}
public Response getMethods(String type,String endpoint) {
switch (type) {
case "GET":
	 response = specification.log().all().get(endpoint);
	break;
case "POST":
	 response = specification.log().all().post(endpoint);
	break;
case "PUT":
	 response = specification.log().all().put(endpoint);
	break;
case "DELETE":
	 response = specification.log().all().delete(endpoint);
	break;

default:
	break;
}
return response;
}
	public void getBody(String value) {
		specification= specification.body(value);
	}
	public void getBodyObject(Object value) {
		specification = specification.body(value);

	}
	public int getResponseCode(Response reponse) {
		int code = response.getStatusCode();
return code;
	}
	public ResponseBody getResponseBody(Response response) {
		ResponseBody body = response.getBody();
		return body;

	}
	public String getString(Response response) {
	String string = getResponseBody(response).asString();
	return string;
	}
	public String getPrettyString(Response response) {
	String string = getResponseBody(response).asPrettyString();
	return string;
	}
	
	public void authentication(String key, String value) {
		specification = specification.auth().preemptive().basic(key, value);
	}
	public String readPropertyFile(String key) throws IOException {
		FileInputStream stream=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resource\\ApiTesting.properties");
Properties properties=new Properties();
properties.load(stream);
Object object = properties.get(key);
String s=(String)object;
return s;
	}
	
	public String getJsonPath(Response response, String key) {
		JsonPath jsonPath = getResponseBody(response).jsonPath();
		Object object = jsonPath.get(key);
		String s=(String) object;
		return s;
	}
	public int getJsonPathNum(Response response, String key) {
		JsonPath jsonPath = getResponseBody(response).jsonPath();
		Object object = jsonPath.get(key);
		int s=(int) object;
		return s;
	}	
	
	
	
	
	
}
