# **Jazz - City of Edmonton Internal Application** 

<img src="https://raw.github.com/coe-google-apps-support/jazz-chrome/master/img/large-tile.png" height="70%" width="70%" align="center">

## **Description**
Jazz is an application wrapper for LiveChat's JS API widget, developed by Nathan Storm Kaefer and Jared Rewerts, for City of Edmonton internal executive employees. 
It is in development for iOS, Android, and Chrome as an extension.

By navigating to the application, you are able to easily receive support from a team of designated support agents, instantly.

Chrome Extension example:

<img src="https://raw.github.com/coe-google-apps-support/jazz-chrome/master/img/example.PNG" height="50%" width="50%">

## **Local Installation Instructions:**

1. Download repo off of Github.
2. Navigate to branch you are testing on.
3. Open up the project in Android Studio
4. Add in the google-services.js file.
5. Add in the Config.java file.
6. Run the app on a device with Android API 16+.

### **Important:**
### Config.java
To get the livechat service working, you need a Java file "Config.java" that defines a "Config" object, with properties license and group. This Config.java file is located with the other Java files.

For example: 
```java
public class Config {
    public static final String group = "GROUP";
    public static final String license = "LICENSE";
}
```

### CSS
LiveChat allows you to modify the look and feel of any given group/deployment by editing CSS in the LiveChat console.

