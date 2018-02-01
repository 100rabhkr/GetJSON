---


---

<h1 id="getjson">GetJson</h1>
<p><a href="https://jitpack.io/#100rabhkr/GetJSON"><img src="https://jitpack.io/v/100rabhkr/GetJSON.svg" alt=""></a> <a href="http://paypal.me/100rabhkr"><img src="https://img.shields.io/badge/Donate-PayPal-green.svg" alt="Donate"></a> <a href="http://github.com/badges/stability-badges"><img src="http://badges.github.io/stability-badges/dist/stable.svg" alt="stable"></a></p>
<p>GetJson is the simplest HTTP library to Receive JSON Data from REST Service. It Gives data as a String which can be used to pass the data between activities which can then be converted to JsonObject to carry on further operations. It can also return data in JsonObject. It also offers an array of Internet related features.<br>
A more advanced version of this library is <a href="https://github.com/100rabhkr/DownZLibrary">DownZ</a>.</p>
<p>What does GetJson do :-</p>
<ul>
<li>Receives JSON Data from the REST Service with just the url.</li>
<li>Does all of this in a background thread rather than the main thread</li>
<li>Can check if Internet Connection is available or not.</li>
<li>Can also check Internet speed.</li>
<li>Can also check if the device time has been changed or not.</li>
</ul>
<blockquote>
<p>It uses help of a free web service provided by <a href="http://www.geonames.org">GeoNames</a>. A separate registration is required to get a username and to activate the web service (Instruction in Usage)</p>
</blockquote>
<h1 id="download">Download</h1>
<p>You can use Gradle</p>
<p><strong>Step 1.</strong> Add the JitPack repository to your build file , Add it in your root build.gradle at the end of repositories:</p>
<pre><code>allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
</code></pre>
<p><strong>Step 2.</strong> Add the dependency</p>
<pre><code>dependencies {
        compile 'com.github.100rabhkr:GetJSON:1.0'
}
</code></pre>
<h1 id="usage">Usage</h1>
<p><strong>Getting JSON data from the URL</strong></p>
<pre><code>JsonObject jsonObject = new GetJson().AsJSONObject("URL");
</code></pre>
<p>This will return the JsonObject which can then be used to get JsonArray, Strings, etc.<br>
<strong>Example:</strong></p>
<pre><code>try {
        JsonObject jsonObject2 = new GetJson().AsJSONObject("http://api.geonames.org/timezoneJSON?lat=47.01&amp;lng=10.2&amp;username=demo");
        String date = jsonObject2.get("time").getAsString();
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();

    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
</code></pre>
<blockquote>
<p>Some of the methods throws ExecutionException and InterruptedException so make sure to enclose them in a try and catch block.</p>
</blockquote>
<p><strong>Getting JsonObject as a String</strong></p>
<pre><code>String jsonString = new GetJson().AsString("URL");
//can be converted to JsonObject
JsonObject reader = new JsonParser().parse(jsonString).getAsJsonObject();
</code></pre>
<p><strong>Check if Internet is available</strong><br>
Another feature of this library is that it checks if Internet connection is available or not so that getting data from the internet can be handled smoothly.</p>
<pre><code> Boolean isConnected = new GetJson().isConnected(Context);
</code></pre>
<p><strong>Example:</strong></p>
<pre><code>if(new GetJson().isConnected(MainActivity.this)){
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
</code></pre>
<p><strong>Check Internet Speed</strong><br>
This Library also provides a nifty tool to check Internet Speed. It gives a basic Idea of users’ internet speed to make sure if the user has high speed internet connection or not. It returns the speed in Megabits format not MegaBytes (Mbps not MBps) in double format.</p>
<pre><code> double internetspeed = new GetJson().getInternetSpeed();
</code></pre>
<p><strong>Example:</strong></p>
<pre><code> try {
        double internetspeed = new GetJson().getInternetSpeed();
        String speed = internetspeed + " Megabits per second";
        Toast.makeText(this, speed, Toast.LENGTH_SHORT).show();
        
    } catch (ExecutionException e) {
        e.printStackTrace();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
</code></pre>
<p><strong>Check if device date and time is changed</strong><br>
User might change device time especially if it involves some kind of daily bonus. GetJson provides a little tool to check if the device’s time has been tampered with. It uses a free web service by <a href="http://geonames.org">geonames.org</a> to do this. In order for it to work following prerequisites must be completed :-</p>
<ol>
<li>Create account here : <a href="http://www.geonames.org/login">http://www.geonames.org/login</a> to get a username.</li>
<li>After signing up activate your account by clicking on the link sent to your email id.</li>
<li>Enable the free web service on your account page: <a href="http://www.geonames.org/manageaccount">http://www.geonames.org/manageaccount</a> and Click on click to enable</li>
</ol>
<p><strong>Usage:</strong></p>
<pre><code> boolean time = new GetJson().isTimeCorrect(int mode, String latitude, String longitude, String mUsername)
</code></pre>
<ul>
<li><em>mode:</em> It has two modes pass 0 for only checking the date and hours as some people deliberately keep their watches ahead by 5 - 10 minutes. Pass 1 to check time down to minutes.</li>
<li><em>Latitude and Longitude:-</em> Pass the Latitude and Longitude in String format of the place you want to check the time for.</li>
<li><em>mUsername:-</em> Pass the username you chose while signing up</li>
</ul>
<blockquote></blockquote>
<h1 id="getting-help">Getting Help</h1>
<p>To report a specific problem or feature request in GetJson, you can <a href="https://github.com/100rabhkr/GetJSON/issues/new">open a new issue on Github</a>. For questions, suggestions, or even to say ‘hi’, just drop an email at <a href="mailto:skkumar.sk94@gmail.com">skkumar.sk94@gmail.com</a></p>
<h1 id="contributing">Contributing</h1>
<p>If you like GetJSON, please contribute by writing at <a href="mailto:skkumar.sk94@gmail.com">skkumar.sk94@gmail.com</a>. Your help and support would make GetJSON even better. Before submitting pull requests, contributors must sign <a href="https://cla.developers.google.com/about/google-individual">Google’s individual contributor license agreement</a>.</p>
<p>If this project help you reduce time to develop, you can give me a cup of coffee :)</p>
<p><a href="https://www.paypal.me/100rabhkr"><img src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif" alt="paypal"></a></p>

