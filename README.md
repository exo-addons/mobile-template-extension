# Mobile Website Template
####Another way to leverage a Mobile UXP

 
## About

> Surely you have heard about the eXo mobile application, without a doubt you’ve admired the quality of this heavy mobile client which is intended to be used natively under the iOS and Android mobile operating systems. And of course, you have liked to embed your own applications with your navigation style and your design in a mobile context.
> 
> That’s why I thought about adding one more way to leverage another mobile capability and giving the hand to the end user to perform a set of actions such as creating his own HTML5 mobile website through a simple based template wizard, changing the look and feel of the website by customizing the logo or selecting the main colors using the data stream sent by Adobe Kuler, updating the content and the navigation menu based on the eXo Content Management System and finally configuring the redirection to the mobile website if using a mobile device.
> 
> During this tutorial, I will show you how to get all of these capabilities through simple actions and configurations:
> 
> * Template based creation of a HTML5 website for Mobile
> * Look&Feel Customization  – Theme & Logo
> * Content and navigation updating
> * Mobile Device Detection and Redirection Configuration

## 1. Installation

It is possible to get the source code from this location: 
`https://github.com/eghodbane/mobile-template`

In order to install the extension, you need to build the project. Run this command on the top of the project: `$mvn install`. This build will generate a JAR file which needs to be putted in the Tomcat `lib` folder, and two WARs; a `mobile-website.war` which contains the extension and the `mobile-apps.war` which is contains the applications. These WARs files need to be added to the `Tomcat Webapps folder`.
 
## 2. HTML5 Mobile Website

eXo Platform 3.5 offers the way to the end user to create his own website based on a set of website and intranet templates. The end user is also able to create his own website template in order to automate the creation and the configuration phases.
I had the idea to create a template which may be used in the purpose of the creation of a mobile website based on HTML5 standards.
![alt text] (https://github.com/eghodbane/mobile-template/blob/master/resources/screenshots/Snap1.png "Adding a Mobile website using the template mechanism")
The end user may select the mobile website template from the list of sites templates and then he will get a preconfigured website designed to be used in a mobile web browser. He needs to add also the other parameters such portal and permission settings.
 
This website will inject the viewport metadata in order to increase the ability to zoom web pages for easier reading in the mobile web browser.
`<meta name="viewport" content="width=device-width, initial-scale=1">`

## 3. The Look & Feel Customization

Once the website created, the end user will get a HTML5 mobile website which its content is based on the categories navigation.
 
The main page of the website contains a banner which displays the company logo, the search box, the menu and the related content which is based on the categories navigation.
The searching capability is performed on this mobile website and the related result page is also adapted to the mobile context.
 
Now, we go back to the main page and try to edit the layout, here he will find a  special portlet ‘Mobile Theme’ which is dedicated to customize the Look&Feel of the page by selecting one colors theme and changing the location of the logo.
 
So, the end user will have the opportunity to customize the Loof&Feel of the website through the edit mode of this portlet. 
 
By default, there is a default theme which is used at the creation of the website. The end user needs only to select one color among others in order to set the five main color of the theme.
The colors are coming from Adobe Kuler which is a web-hosted application for exploring, creating and sharing color themes. Each time the end user select one group of colors, the configuration menu will be updated and preferences will be stored after saving it. It’s possible to have three filters for colors: Random, Highest Rated and Popular.
It’s possible to configure the company logo, the end user may upload the logo file into the file system, get the location URL (which may be relative of absolute, or even deployed outside the platform) and past the link intro the related input box.

## 4- The Content Personalization

It’s possible to personalize the content of the website using the categories navigation mechanism. The end user may update the associated categories by adding and updating one category or its related content. 
The categories-based navigation will be updated easily without any additional coding.
  
## 5- The Redirection Mechanizm

One more feature of this add-ons, is the capability of configuring the redirection to the mobile website after an automatic detection of the device.
  
The end user needs only to indicate the URL of the mobile website already created, and then, each time a visitor arrives at the platform, the system will detect the device used for browsing, and if it’s a mobile, he will be redirected automatically to the indicated location. This information will be stored also in the preferences of the Mobile Switcher Portlet.
