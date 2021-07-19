# MyDeliveryRobot
Experimental software for outdoor self driving delivery robot

The idea of this project is to connect an Android phone to Tetrix robot and experiment with self driving from one house to another house within my own community. The purpose of this project is to learn the concepts while doing the hands on trials. At high level the android app will be a FTC(FIRST Robotics ) controller app and will be customized to do following:

1. Take the destination house address
2. Call Google direction API (I will not be checking in the key here, you need to use your own google api key)
3. Process the google directions
4. Start reading direction one by one and feed into the robot once previous step is completed. The robot controlling program will convert the feed into robot specific instructions and metrics.
5. In addition to above directional navigation, use OpenCV to detect and keep inside the pavement so that robot does not go off the path .
6. Use OpenCV to detect street turns and apply direction even though direction api may not tell before hand.
7. Use OpenCV to detect street signs and understand the street to match with google direction apis.
8. Use ultra sonic sensors to detect obstacles and wait for it to clear. In future, this can be enhanced to navigate around the obstacle.

This project will be developed using Agile methodology with small demos every two weeks, recorded and kept in youtube.

Tentative demo plan
1. Show simple app on Android that 
   a. Read destination address
   b. Get the directions from current location to destination and show in list view
   c. Show current step on top
   d. Pressing next will show next step
   e. Pressing previous will show previous step
   
2. Show directions in number of steps customized to your step side
   a. Read your step calliberation
   b. Show directions using steps
   c. We can test how close is these directions matching with our steps by walking as per its instructions
   
3. Build a robot with all terrain wheels and drive with remote control outside. Test the battery capacity and understand how well is it turning or going in straight line. Identify any problems.
4. 
