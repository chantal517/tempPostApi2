##Timing
It is important that we are **consistant with our date and time formatting** to preserve the **integrity** of our data. The Raspberry Pi, Arduino, and server all have access to local time. Provided that the initial (and most important) time stamps will be created on the clients (Raspberry Pi and Arduino), we should model the time after their time stamps.

This project is being developed in the **central** time zone, so it makes most sense to store date and time using the central time zone.

##Sample Date Time Format Example
Sat Jul 9 17:59:59 EDT 2016
