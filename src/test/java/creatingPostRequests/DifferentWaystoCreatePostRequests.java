package creatingPostRequests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import io.restassured.specification.RequestSpecification;

/*Different ways of creating POST request body
1) Post request body using HashMap
2) Post request body creation using org.json
Post request body creation using POJO class
Post request using external json file data*/

public class DifferentWaystoCreatePostRequests {
	
	//Post request body using HashMap
	/*@Test(priority=1)
	public void  postRequestUsingHashMap()
	{
		HashMap hm = new HashMap();
		hm.put("name", "Scott");
		hm.put("location", "France");
		hm.put("phone", "1234567890");
		String [] courseArr = {"java","C#","python"};
		hm.put("courses", courseArr);
		
		given()
		.contentType("application/json")
		.body(hm)
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("1234567890"))
		.body("courses[0]", equalTo("java"))
		.body("courses[1]", equalTo("C#"))
		.body("courses[2]", equalTo("python"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();	
			
	}
	@Test(priority=2, dependsOnMethods={"postRequestUsingHashMap"})
	void deleteTest()
	{
		given()
		.when()
			.delete("http://localhost:3000/students/4")
		.then()
		.statusCode(200);
	}*/

	
	//2)Post request body using org.json library
	
	/*@Test(priority=2)
	public void testReqUsingJsonLibrary() throws JSONException
	{
	JSONObject jo = new JSONObject();	
	jo.put("name", "Scott");
	jo.put("location", "France");
	jo.put("phone", "1234567890");
	
	String [] courseArr = {"java","C#","python"};
	jo.put("courses", courseArr);
	
	given()
	.contentType("application/json")
	.body(jo.toString())
	.when()
	.post("http://localhost:3000/students")
	
	.then()
	.statusCode(201)
	.body("name", equalTo("Scott"))
	.body("location", equalTo("France"))
	.body("phone", equalTo("1234567890"))
	.body("courses[0]", equalTo("java"))
	.body("courses[1]", equalTo("C#"))
	.body("courses[2]", equalTo("python"))
	.header("Content-Type", "application/json; charset=utf-8")
	.log().all();	
	
	}*/
	
	//3)Create a Post request using POJO Class
	
	//@Test(priority=3)
	public void testReqUsingPOJO() 
	{
	//Create an object of the POJO class by using the POJO class name to create a POJO object
		
		POJO_PostRequest pr = new POJO_PostRequest();
		
		pr.setName("Scott");
		pr.setLocation("France");
		pr.setPhone("1234567890");
		String[] coursesArr = {"java","C#","python"};
		pr.setCourses(coursesArr );
		
	given()
	.contentType("application/json")
	.body(pr)
	.when()
	.post("http://localhost:3000/students")
	
	.then()
	.statusCode(201)
	.body("name", equalTo("Scott"))
	.body("location", equalTo("France"))
	.body("phone", equalTo("1234567890"))
	.body("courses[0]", equalTo("java"))
	.body("courses[1]", equalTo("C#"))
	.body("courses[2]", equalTo("python"))
	.header("Content-Type", "application/json; charset=utf-8")
	.log().all();	
	
	}
	
	
	//4)Create a Post request using external JSON file
	
	@Test(priority=4)
	public void testReqUsingExternalJsonFile() throws FileNotFoundException 
	{
		
	File f = new File(".\\Body.json");
	FileReader fr = new FileReader(f);
	JSONTokener jt = new JSONTokener(fr);
	
	//Extract the file using JSONObject format
	JSONObject jo = new JSONObject(jt);
	
		
	given()
	.contentType("application/json")
	.body(jo.toString())
	.when()
	.post("http://localhost:3000/students")
	
	.then()
	.statusCode(201)
	.body("name", equalTo("Samuel"))
	.body("location", equalTo("England"))
	.body("phone", equalTo("4567890123"))
	.body("courses[0]", equalTo("cucumber"))
	.body("courses[1]", equalTo("database"))
	.body("courses[2]", equalTo("math"))
	.header("Content-Type", "application/json; charset=utf-8")
	.log().all();	
	
	}
	
	
}


