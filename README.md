# GetJson

[![](https://jitpack.io/v/100rabhkr/GetJSON.svg)](https://jitpack.io/#100rabhkr/GetJSON) [![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](http://paypal.me/100rabhkr) [![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)

GetJson is the simplest HTTP library to Receive JSON Data from REST Service. It Gives data as a String which can be used to pass the data between activities which can then be converted to JsonObject to carry on further operations. It can also return data in JsonObject. It also offers an array of Internet related features.
 A more advanced version of this library is [DownZ](https://github.com/100rabhkr/DownZLibrary). 

What does GetJson do :- 

 - Receives JSON Data from the REST Service with just the url.
 - Does all of this in a background thread rather than the main thread
 - Can check if Internet Connection is available or not.
 - Can also check Internet speed.
 - Can also check if the device time has been changed or not.

> It uses help of a free web service provided by [GeoNames](http://www.geonames.org). A separate registration is required to get a username and to activate the web service (Instruction in Usage)

# Download

You can use Gradle

**Step 1.** Add the JitPack repository to your build file , Add it in your root build.gradle at the end of repositories:

    allprojects {
    	repositories {
    		...
    		maven { url 'https://jitpack.io' }
    	}
    }

**Step 2.** Add the dependency

    dependencies {
            compile 'com.github.100rabhkr:GetJSON:1.0'
    }

# Usage
**Getting JSON data from the URL**

    JsonObject jsonObject = new GetJson().AsJSONObject("URL");

This will return the JsonObject which can then be used to get JsonArray, Strings, etc.
**Example:**

    try {
            JsonObject jsonObject2 = new GetJson().AsJSONObject("http://api.geonames.org/timezoneJSON?lat=47.01&lng=10.2&username=demo");
            String date = jsonObject2.get("time").getAsString();
            Toast.makeText(this, date, Toast.LENGTH_SHORT).show();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

> Some of the methods throws ExecutionException and InterruptedException so make sure to enclose them in a try and catch block.

**Getting JsonObject as a String**

    String jsonString = new GetJson().AsString("URL");
    //can be converted to JsonObject
    JsonObject reader = new JsonParser().parse(jsonString).getAsJsonObject();

**Check if Internet is available**
Another feature of this library is that it checks if Internet connection is available or not so that getting data from the internet can be handled smoothly.

     Boolean isConnected = new GetJson().isConnected(Context);

**Example:**

    if(new GetJson().isConnected(MainActivity.this)){
            try {
                JsonObject jsonObject = new GetJson().AsJSONObject("URL");
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            //handle if Internet is not available
        }
**Check Internet Speed**
This Library also provides a nifty tool to check Internet Speed. It gives a basic Idea of users' internet speed to make sure if the user has high speed internet connection or not. It returns the speed in Megabits format not MegaBytes (Mbps not MBps) in double format.

     double internetspeed = new GetJson().getInternetSpeed();
**Example:**

     try {
            double internetspeed = new GetJson().getInternetSpeed();
            String speed = internetspeed + " Megabits per second";
            Toast.makeText(this, speed, Toast.LENGTH_SHORT).show();
            
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
**Check if device date and time is changed**
User might change device time especially if it involves some kind of daily bonus. GetJson provides a little tool to check if the device's time has been tampered with. It uses a free web service by geonames.org to do this. In order for it to work following prerequisites must be completed :- 

 1. Create account here : http://www.geonames.org/login to get a username.
 2. After signing up activate your account by clicking on the link sent to your email id.
 3. Enable the free web service on your account page: http://www.geonames.org/manageaccount and Click on click to enable 

**Usage:**

     boolean time = new GetJson().isTimeCorrect(int mode, String latitude, String longitude, String mUsername)

 - *mode: * It has two modes pass 0 for only checking the date and hours as some people deliberately keep their watches ahead by 5 - 10 minutes. Pass 1 to check time down to minutes.
 - *Latitude and Longitude:-* Pass the Latitude and Longitude in String format of the place you want to check the time for.
 - *mUsername:- * Pass the username you chose while signing up

> 

# Getting Help
To report a specific problem or feature request in GetJson, you can [open a new issue on Github](https://github.com/100rabhkr/GetJSON/issues/new). For questions, suggestions, or even to say 'hi', just drop an email at skkumar.sk94@gmail.com

# Contributing

If you like GetJSON, please contribute by writing at skkumar.sk94@gmail.com. Your help and support would make DownZ even better. Before submitting pull requests, contributors must sign [Google's individual contributor license agreement](https://cla.developers.google.com/about/google-individual).

If this project help you reduce time to develop, you can give me a cup of coffee :)

[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.me/100rabhkr)


   

