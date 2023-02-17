# OCSAssessment
OCS Assessment -Backend- project

Project is coded with Java (JRE: JavaSE 17) in Maven, with JUnit test framework. It consist of a Client and RESTful application triggered separetly by input provided from Console by client. Restful service is implemented with Jersey framework to launch rest service. Besides Tomcat is embedded in project and it leverage inside the solution to be able to run itself without needing any server to host. 

REMARQUE: The requirement below is considered typo while changing the directions

•	Turn Left (L):
  Consumes 2 battery units. 
  Changes the facing direction 90º to the right. 
• Turn Right (R): 
  Consumes 2 battery units. 
  Changes the facing direction 90º to the left. 

# How To Run the project:
> download "demo.rar" file 
> extract files into same name folder (it is really not important)
> click run.bat
- input for first scenario:
> obs_test test_run_1.json output.json

![image](https://user-images.githubusercontent.com/1770848/219590504-66e2120f-f619-40fd-b0d1-b8b94904ba52.png)

![image](https://user-images.githubusercontent.com/1770848/219590631-03a88cd2-a416-4666-bd3f-c683ea4bcb0d.png)

![image](https://user-images.githubusercontent.com/1770848/219590932-0fd5218d-65a4-4c5d-8a7e-21266a87f32c.png)

- input for second scenario:
> obs_test

![image](https://user-images.githubusercontent.com/1770848/219591222-94961ba9-67b0-472e-8796-b3b084edbbcd.png)

embedded tomcat should be up and displayed message: Server listening on localhost:8080

![image](https://user-images.githubusercontent.com/1770848/219591989-e3adad47-16d5-40c5-bb0e-e5351688181b.png)

The project will need "8080" port.
Available endpoint to test Restful service will be: http://localhost:8080/OCSAssessment/rest/sendInstructions
This api will accept JSON file in format shared with backend project assessment guideline.

Example of POSTMAN:

![image](https://user-images.githubusercontent.com/1770848/219597552-620a4ef2-aa66-4c41-a5d7-36b1ab02ede7.png)

