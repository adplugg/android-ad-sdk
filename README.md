# android-ad-sdk
Offical Android SDK for [AdPlugg](http://www.adplugg.com/)

# Getting started
This project contains both the AdPlugg Android SDK and a sample application. The SDK and sample app are documented inline. It is recommended to start by looking at MainActivity.java and SampleApplication.java in the sample app and going from there.

# Sample Application
~/android-ad-sdk/sample/

Main classes:
- SampleApplication - Initializes AdPlugg access code at application start
- MainActivity - Demonstrates AdRequest and AdView usage
- activity_main.xml - The main layout

#  SDK
~/android-ad-sdk/adplugg/

Main classes:
- AdPlugg - Global configuration for AdPlugg. This contains the global access code.
- AdRequest - Contains information of specific ads / zones to load
- AdView - View component that loads AdRequests and displays them
- AdRequest.Builder - Used to simplify construction of AdRequests