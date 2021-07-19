# MyDeliveryRobot
## Experimental software for outdoor self driving delivery robot

The idea of this project is to connect an Android phone to Tetrix robot and experiment with self driving from one house to another house within my own community. The purpose of this project is to learn the concepts while doing the hands on trials. 

### At high level the android app will be a FTC(FIRST Robotics ) controller app and will be customized to do following:  

1. Take the destination house address
2. Call Google direction API (I will not be checking in the key here, you need to use your own google api key)
3. Process the google directions
4. Start reading direction one by one and feed into the robot once previous step is completed. The robot controlling program will convert the feed into robot specific instructions and metrics.
5. In addition to above directional navigation, use OpenCV to detect and keep inside the pavement so that robot does not go off the path .
6. Use OpenCV to detect street turns and apply direction even though direction api may not tell before hand.
7. Use OpenCV to detect street signs and understand the street to match with google direction apis.
8. Use ultra sonic sensors to detect obstacles and wait for it to clear. In future, this can be enhanced to navigate around the obstacle.

### This project will be developed using Agile methodology with small demos every two weeks, recorded and kept in youtube. 

#### Demo 1 . Google API integration and display of instructions
1. Show simple app on Android that read destination address
2. Get the directions from current location to destination and show in list view
3. Show current step on top
4. Pressing next will show next step
5. Pressing previous will show previous step
   
#### Demo 2. Convert metrics to steps and display instructions from Google API
1. Read your step calliberation
2. Show directions using steps
3. We can test how close is these directions matching with our steps by walking as per its instructions
   
#### Demo 3 . Build robot
1. Build a robot with all terrain wheels and drive with remote control outside.
2. Test the battery capacity and understand how well is it turning or going in straight line. 
3. Identify any problems.
4. Download and build open source FTC Android app
5. Build FTC app with motor control classes and demo simple autonomous actions like forward, back, stop, turns etc. Demo

#### Demo 4. Integrate FTC app with Google API and demo the self driving behavior of robot using directions
6. Integrate FTC app with google apis and modify UI to take destination, on start will take instructions from google directions, control the motors.
7. 

#### Demo 5. Use ultrasonic and maybe touch sensor to detect obstacle, wait and go during navigation

#### Demo 6. Use OpenCV to detect street turning and stop

#### Demo 7. Use OpenCV to detect street turning and turn as per google direction

More demos to be added...

