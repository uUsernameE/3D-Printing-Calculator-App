# Assignmet 4: Android App

This app was created to calculate a quote for a 3D printing project based on labor costs and material costs. The layout consists of
*EditText boxes to enter information
*TextView boxes for instructions
*A Button to confirm and output the calculation into another TextView box.

# Assignment: Final Project
This app was designed to allow the user to enter in printer and material information, as well as some general information like wage and electricity price/kWh, and then calculate a quote of how much they would charge for a 3D Printing Project commission. The Printer and Materials tabs would be very similar where the user can edit/delete already existing options or add a new a new Printer/Material. There would also be a Cost or quote tab that calculates the costs based on the information of a selected printer and material from a drop-down menu or spinner. 

I orignialy tried to use a remote AWS database to store the information and access it using Jetbrains Exposed, but there were issues in connecting to the database, so I decided to try to store on the phone itself; however, there were still some issues. I did make much more progress, even if it is not functional. The application can store a printer and display it in a ListView, and it can calculate a cost given some input information, but the cost is incomplete as the Printer Activities are still being debugged.

The current issue lies with editing information of an existing printer, where I try to obtain the printer infomation given only the name that should be displayed on the ListView (it also does not display the name, but an array of the information). What usually happens is the application stops and I get an error.

Sometimes, though, I have been able to edit an existing Printer, but it doesn't replace the old one, it adds a new printer. Also, when a new printer is added, it doesn't always show up unless I click Save twice, but then two items populate on the listView; perhaps I need to stay on the activiy longer before clicking Back.