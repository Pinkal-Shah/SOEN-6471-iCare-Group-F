server :  Tomcat v8.5

external jar : my-sql connectot-java-5.1.49.jar
	       servlet-api.jar	
database : 
doctor

<<table-name>>doctor_login
<<column-names>> 
<primary-key>username 
             password 

<<table-name>>doctor_schedule
<<column names>>
<primary-key> doctor-username
	      department
	      available slot
	      patientID
	      patient-name
              patient-age

  Available Slot	patient - Name   Button <Close Button>
9 - 9:30             	       pID
9:30 - 10:00
10:00 - 10:30
10:30 - 11:00
11:00 -11:30
11:30 - 12:00
2:00 - 2:30
2:30 - 3:00
3:00 - 3:30
3:30 - 4:00
4:00 - 4:30
4:30 - 5:00
	      
               