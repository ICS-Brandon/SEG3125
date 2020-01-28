# Application Proposal
***
### Goal of Application

The goal of the application is to provide an android based app to users for a personal wishlist of items.

### Scope of Application

The main objective of the project is to provide a wishlist application where the user pastes a link to the item they want and the application will return an object that includes: the price of the objects, an image of the object, and the retailer of the object. The application has several tertiary goals in addition to its main objective, which will be listed below in order of possible addition to the application.

### Long Term Goals

There are several extra goals of the application which exist outside the MVP which are outlined here, these pose no use to the main goal of the application and are considered "extra goodies". The earlier goals can be attempted as single developer, however for further goals to be reach sustainably and effectively more developers would be recruited for it.

- Ability to filter wishlist items
  - Adds the option filter items by specific tags which the user can decide on (or edit after the fact) of adding an item to their wishlist, along with the ability the user will also be able to sort by the tags
- Ability to sort by identifiers
  - Would allow the user to sort their wishlist by certain identifiers such as price, retailer, type, etc.
- Compatibility with IOS devices
  - Add compatibility of the application with IOS devices
- Central database of wishlisted items
  - Would be used to expedite the process of adding items to your wishlist by creating a database of all users wishlisted items (including tags) to pick from
- Wishlist suggestions
  - With the addition of the database of items when a user adds an item to their wishlist they would be greeted with more similar suggestions based on retailer and tags
- More affordable suggestions
  - With the advent of suggestions and a database the application would be able to recommend more affordable and similar options to the item being wishlisted

These outline the most realistic goals to complete in a somewhat respectable time period, there are many more options such as searching for items based on pictures and other options. This application serves not as a long term development goal however and as such these items will not be listed.

### Target Demographic

This application's main target demographic will be younger online shoppers, more than likely ages ranging from about 14-28 years old. This user group has a chance narrowing further to those are considered "power users" that require a dedicated app rather than just memory or a note in their phone or computer. With a focus on simplicity, however the goal demographic remains younger online shoppers.

### Application Phases

There are several pieces to developing an application, you cannot reach your final product without taking steps through the ordeal, as such the main phases the application will develop through are outlined below

##### 1. User can create a secure account and login to the application
  - Simplistic but effective UI design, largely complete beside tweaking based on user feedback and analysis
  - "Rough Draft" style; functional and easy to use but not easy on the eyes
  - User will only have access to a handful of pages
    - Settings : A page where the user will be able to change their credentials: password, name, etc.
    - About : A page outlining the application: who built it, when it was created, the goal, etc.
    - Contact us : A page with contact information and contact info for users to get in contact with the developers

##### 2. User can add links to a product they want to keep track of
  - Add page for user to add or delete a link to a product
  - Once logged in the user will be greeted with a screen to show them their "Home Page", essentially their wishlist at this stage just displayed with the link to the item
  - In this stage the user will have access to a shortcut for their settings via a button on the screen
  - A navigation bar will be introduced in this phase to provide functionality to the transition and movement between pages

##### 3. User can add tags to their items
  - Predetermined tags will be created for widespread use
  - Possibility of user-defined tags for local items
  - Introduction of a tertiary screen for editing tags and links of items on the wishlist

##### 4. User Interface Refactoring
  - Changes to the user face to take place after User Testing and Analysis
  - Chance to review concept of UI and make changes where needed
  - Will also serve as a way to polish off "completed" UI fragments
  - Based on response and review a tutorial of the application may be implemented

##### 5. Picture of the item is displayed
  - Picture of the item will be displayed in a card style format
  - Item's card view will also display 3 tags
  - Tapping on item card will display more information about it (link and all tags)

##### 6. Price of item is displayed
  - Information will be scrubbed from website to find price of item
  - Will be displayed in the card view of the item

##### 7. Bug Fixing and Tweaking
  - Dedicated phase to seek out bugs in all layers of the program
  - Used to tweak methods and refactor code for better neatness and other Software Engineering practices

##### 8. Application Reflection
  - Phase used to reflect on the application
  - Designed to allow for critical analysis of finished product through more User Testing and analysis
  - Final chance to change large aspects of functionality in the application before "release"

### Resources

A goal of this application is to avoid using 3rd party resources where possible to remove dependencies on outside products and frameworks. Some areas of this application will not be completed without these though so they will be incorporated. A simple outline of the need for resources include:
##### 1. HTML scrubbing for product info
- JSoup: Relatively lightweight HTML parser using several methods to extract and manipulate HTML information , [Documentation here](https://jsoup.org/)

##### 2. De-centralized Database for the storage of user info
- Option 1 -  RealmDB: Lightweight database tool designed for easy use on mobile devices, [Documentation here](https://realm.io/docs)
- Option 2 - Firebase: Google based database that is well defined and easy to use, [Documentation here](https://firebase.google.com/docs)

##### 3. User Interface Tools
- Alerter: Library used to produce a better alert option than Toasts and other built in functions, [Documentation here](https://github.com/Tapadoo/Alerter)
- Spotlight: Library designed to light or highlight items for user tutorials or walkthroughs, [Documentation here](https://github.com/TakuSemba/Spotlight)
- Expansion Panel: Library used to add a UI element to allow for easier editing of elements, [Documentation here](https://github.com/florent37/ExpansionPanel)
- Shape Image View: Library used to easily change the shape of an image, [Documentation here](https://github.com/siyamed/android-shape-imageview)
- Android View Animations: Library to give access to common animations, [Documentation here](https://github.com/daimajia/AndroidViewAnimations)

##### 4. Serialization and De-Serialization of Java objects into JSON
- GSon: Java Serialization/Deserialization library to convert Java objects to JSON and back, [Documentation here](https://github.com/google/gson)
