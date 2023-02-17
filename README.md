# OCSAssessment
OCS Assessment project

Project code base is in "master" branch
Project is coded with Java in Maven
It consist of a Client and RESTful application triggered separetly by input provided from Console by client.
Jersey framework is implemented as RESTful framework to launch rest service
To be able to run itself Tomcat is embedded in project and it leverage inside the solution.


The requirement below is considered typo while changing the directions

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
