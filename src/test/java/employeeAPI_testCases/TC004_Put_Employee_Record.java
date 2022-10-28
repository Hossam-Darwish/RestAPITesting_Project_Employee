package employeeAPI_testCases;

import employeeAPI_base.TestBase;
import employeeAPI_utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;


/******************************************************
 Test Name:Update an employee record
 URI: http://dummy.restapiexample.com/api/v1/update/{id}
 Request Type: PUT
 Request Payload(Body): {"name":"XXXXX","salary":"XXXX","age":"XX"}
 ********* Validations **********
 Response Payload(Body) : {"name":"XXXXX","salary":"XXXX","age":"XX"}
 Status Code : 200
 Status Line : HTTP/1.1 200 OK
 Content Type : application/json
 Server Type :  Apache
 Content Encoding : null
 **********************************************************/


public class TC004_Put_Employee_Record extends TestBase {

    RequestSpecification httpRequest;
    Response response;
    String empName= RestUtils.empName();
    String empSalary=RestUtils.empSal();
    String empAge=RestUtils.empAge();


    @BeforeClass
    void updateEmployee() throws InterruptedException
    {
        logger.info("*********Started TC004_Put_Employee_Record **********");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", empName);
        requestParams.put("salary", empSalary);
        requestParams.put("age", empAge);

        // Add a header stating the Request body is a JSON
        httpRequest.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.PUT, "/update/"+empID);

        Thread.sleep(5000);

    }

    @Test
    void checkResposeBody()
    {
        String responseBody = response.getBody().asString();

        Assert.assertTrue(responseBody.contains(empName));
        Assert.assertEquals(responseBody.contains(empSalary), true);
        Assert.assertEquals(responseBody.contains(empAge), true);
    }

    @Test
    void checkStatusCode()
    {
        int statusCode = response.getStatusCode(); // Gettng status code
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void checkstatusLine()
    {
        String statusLine = response.getStatusLine(); // Gettng status Line
        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

    }

    @Test
    void checkContentType()
    {
        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json");
    }

    @Test
    void checkserverType()
    {
        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "Apache");
    }

    @Test
    void checkcontentEncoding()
    {
        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals(contentEncoding, null);

    }

    @AfterClass
    void tearDown()
    {
        logger.info("*********  Finished TC004_Put_Employee_Record **********");
    }

}
