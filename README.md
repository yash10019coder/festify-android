# festify-android
An app for easing managing and registering for fests, events and workshops.

#### Problem Statement:
-------------------------------------------------
Many institutions and organizations have been continuously facing issues while organizing fests and events for properly managing invitations and registrations and it has been equally painful for people willing to attend these events to find where to register and then filling those lengthy traditional forms.  It’s a tough job to collect the data of people who are interested in attending an event.
Sometimes due to unforeseen circumstances some people might need to cancel their registration for the event and then it becomes difficult for the organizers to update their systems and plan.
Possible interested attendees may miss out on some event due to lack of information or even may forget about it considering how busy life on an individual is these days.

#### Proposed Solution:
-------------------------------------------------
This project aims to minimize the hassle and ease the process for people willing to organize and attend some events. People willing to attend some events just have to sign up once filling their information and then not worry about filling different forms for different events. The application fetches list of updated events and presents them to the user category wise, where they can choose some event which interests them and register for with just touch of a button. The application always maintains a list of events for which the user has registered already so they can manage their time accordingly. User also has an option to cancel registration for an event by just another touch. This greatly eases the process for people attending and also for the people organizing the event as they also can just fetch out the details of people registering for the event.

#### Functionality & Concepts used:
-------------------------------------------------
- The App has an interactive interface which helps the user to register/unregister for an event .

- POST request :
For the Login and Signup screens user posts his/her data and with the help of a post request we send this data to a backend server and the server checks whether the details were good enough to go or need to be modified and provides an appropriate response according to it

- Constraint Layout : 
Most of the activities in the app uses a flexible constraint layout, which is easy to handle for different screen sizes

- SliderView:
To promote some events we have used a SliderView which slides automatically after 2 secs and gets updated with another event.

- RecyclerView:
To represent the different type of tech stacks and different events in a fest we use a RecyclerView which arranges them in an ordered scroll-able list.

- Room Database:
So when a user registers for an event a duplicate of that specific view goes into HOME fragment and we could see the events in an order of registered time in the HOME fragment. Even after closing and opening the app the events registered remains same with the help of Room Database

- ViewModels:
We have created Viewmodels for DataBase and API calls related fragments so the data survives configuration changes while also following a better architecture.

- LiveData:
For Room Database when the data gets updated i.e., added or deleted, the app gets notified that there was a change and changes the RecyclerView accordingly
For API calls we get a response whenever we post the data,so whenever we post a new data then app gets notified that there was a change and responds accordingly

- Navigation Component and BottomNavigation
The app uses Navigation component using a navGraph and a navHostFragment in pair with BottomNavigation to let user switch between various fragments with ease.


#### Application Link & Future Scope :
-------------------------------------------------
-applink-<br>

- The App is still in Alpha phase and we have to make much changes to it.
- We are working on a way to fetch the data of events from the backend server
- We are adding a “Teams” Fragment which is used to find teammates to participate together in a team-event.
We will introduce a new fragment for users to post pictures and show their excitement towards the event


#### To setup the project for starting developement
-------------------------------------------------
1. Make a folder named festify.
2. Fork the project and then Clone the project forked project inside festify.
3. cd to the festify-android then run `bash scripts/setup.sh` , if you are in windows then fire up the wsl and run the same command.
4. you are ready to contribute.
