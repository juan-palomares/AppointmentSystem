/*
* Program Name: Appointment System
*
* Program Description: The objective of this program is to streamline the process of making, cancelling, and confirming appointments. Users are able to create, cancel, and confirm their own appointments in an efficient and concise manner. The intention is to create a system that is more user-friendly and less time-consuming than the typical appointment system. The menu provides five options: check-in for your appointment, create a new appointment, cancel your appointment, return to the menu, and exit the menu. If user’s select to create a new appointment, they will be asked to enter their first and last name, as well as their preferred date and time for an appointment. They will also be asked to enter whether it will be a dental, pediatric, or optometry appointment. After ensuring that all entered information is correct, the user will receive a confirmation number and a receipt that contains the date, time, confirmation number, and cost. This is an effective reminder of all important information should the user forget. The unique, six-digit confirmation number is used to easily confirm or cancel an appointment. Should the user select to check-in, he or she will be asked to submit both his or her last name and confirmation number. The program will be able to identify whether or not the user has an appointment in the system and if the entered last name and confirmation number match. If the user wants to cancel an appointment, he or she can simply enter the provided confirmation number. This appointment program is an efficient and functional way of streamlining a process that is often overcomplicated, while always keeping the user’s best interests in mind.
*
* Date Created:05/17/2021 
*
* Authors: Juan Palomares, Sierra Brandt, Katarina Cohen,  and Maria Kramer
* Email:  sierrabrandt1@gmail.com, katarina.cohen@berkeley.edu, jpalomares589@gmail.com, 7m.kramer@gmail.com
*
* Updated:  
*/
import java.util.*;
import java.io.*;
class Main {
  static int i = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char yesNo;
        String lastName, confirmationNum = null;
        String choice;
        File fileName = new File("Receipt.txt");
              
        Appointment patient1 = new Appointment();

        System.out.println("Welcome to Oceanside Community Clinic Appointment System.");
        System.out.println("Please press enter to continue to our menu.");
        in.nextLine();

        System.out.println("A: Check-In");
        System.out.println("B: Set Appointment");
        System.out.println("C: Cancel Appointment");
        System.out.println("D: Display");
        System.out.println("E: Exit");
        do
        {
        boolean leave = false;
        choice = in.nextLine().toLowerCase();
        switch (choice) {
        case "a":
        System.out.println("Please enter your last name: ");
        lastName = in.next().toLowerCase();
        
        int indexLastName = getIndexOf(Appointment.appointmentList, lastName);

        while(indexLastName < 0){
          System.out.println("This last name is not in our system. Please try again.");
          lastName = in.next().toLowerCase();
          indexLastName = getIndexOf(Appointment.appointmentList, lastName);
        }
        
        if(indexLastName >= 0)
          {
            if (lastName.equals(Appointment.appointmentList.get(indexLastName).getLastName())) 
				    {
              System.out.println("Please enter your confirmation number: ");
              confirmationNum = in.next();
              if (confirmationNum.equals(Appointment.appointmentList.get(indexLastName).getConfirmationNum())) 
						  {
          		System.out.println("You're all checked in! Please wait for your name to be called.");
          		System.out.println("Please press d to go back to the menu or e to exit");
              }                 
              else {
                while(!confirmationNum.equals(Appointment.appointmentList.get(indexLastName).getConfirmationNum())) 
					      {
                  System.out.println("This confirmation is not in our system. Please try again.");
                  confirmationNum = in.next();
                  if (confirmationNum.equals(Appointment.appointmentList.get(indexLastName).getConfirmationNum())) 
						      {
          		      System.out.println("You're all checked in! Please wait for your name to be called.");
          		      System.out.println("Please press d to go back to the menu or e to exit");
                  } 
                }
              }
            } 
          }
          
      	break;

        case "b":
          System.out.println("Please enter your first and last name: ");
          patient1.setFirstName(in.next().toLowerCase());
          patient1.setLastName(in.next().toLowerCase());
          System.out.println("Your name is " + patient1.getLastName() + ", " + patient1.getFirstName());
          System.out.println("Is this correct? Yes (y) or No (n)");
          yesNo = in.next().charAt(0);
          if (yesNo == 'y' || yesNo == 'Y') 
					{
            System.out.println("Great! Now please enter the month, day and year you would like to make" +
              " an appointment for (separate with spaces).");
            patient1.setMonth(in.nextInt());
            patient1.setDay(in.nextInt());
            patient1.setYear(in.nextInt());
            System.out.println("Now please enter the time at which you'd like to come (hour and minute" +
              " separated with a space).");
            patient1.setHour(in.nextInt());
            patient1.setMinute(in.nextInt());
            System.out.println("Your appointment is on " + patient1.getMonth() + "/" + patient1.getDay() +
              "/" + patient1.getYear() + " @ " + patient1.getHour() + ":" + patient1.getMinute());
            System.out.println("Is this correct? Yes(y) or No(n)");
            yesNo = in.next().charAt(0);

            while(yesNo == 'n' || yesNo == 'N'){
              System.out.println("Got it. Please re-enter date and time.");
							patient1.setMonth(in.nextInt());
              patient1.setDay(in.nextInt());
              patient1.setYear(in.nextInt());
							patient1.setHour(in.nextInt());
							patient1.setMinute(in.nextInt());
              
              System.out.println("Your appointment is on " + patient1.getMonth() + "/" + patient1.getDay() +
              "/" + patient1.getYear() + " @ " + patient1.getHour() + ":" + patient1.getMinute());
              
              System.out.println("Is this correct? Yes(y) or No(n)");
              yesNo = in.next().charAt(0);
            }

            if (yesNo == 'y' || yesNo == 'Y') 
						{
            	System.out.println("Will this be a dental (D), pediatric (P), or optometrist (O) appointment? Enter D, P, or O.");
              char typeAppt = in.next().charAt(0);
              System.out.println("Here is your confirmation number confirming your appointment: ");
              patient1.setConfirmationNum(generateConfNum());
              System.out.println(patient1.getConfirmationNum());
							try 
							{
                PrintWriter pw = new PrintWriter(fileName);
                pw.print(patient1.appointmentSetToString());
              
                if (typeAppt == 'D' || typeAppt == 'd')
								{
                  DentalAppt dental = new DentalAppt();
                  pw.println(dental.costToString());
                }
                else if (typeAppt == 'O' || typeAppt == 'o')
								{
                  OptometryAppt opt = new OptometryAppt();
                  pw.println(opt.costToString());
                }
                else
								{
                  PediatricAppt ped = new PediatricAppt();
                  pw.println(ped.costToString());
                }
                  pw.close();
                } 	
								catch (IOException e) 
								{
                  e.printStackTrace();
                }
                Appointment.appointmentList.add(new Appointment(patient1.getMonth(), patient1.getDay(), patient1.getYear(), patient1.getHour(),
									patient1.getMinute(), patient1.getFirstName(), patient1.getLastName(), patient1.getConfirmationNum()));
                System.out.println("Please press d to go back to the menu or e to exit");
              }    
						} 
					else 
					{
            System.out.println("Got it. Please press b to start again");
					}
          break;

          case "c": 
                    System.out.println("Please enter your confirmation number: ");
                    confirmationNum = in.next();
                    int indexConfNum = getIndexOf2(Appointment.appointmentList, confirmationNum);

                    while(indexConfNum < 0){
                    System.out.println("The confirmation number you entered is not in our system. Please try again.");
                    confirmationNum = in.next();
                    indexConfNum = getIndexOf2(Appointment.appointmentList, confirmationNum);
                    }

                    if(indexConfNum >=0){
										  if(confirmationNum.equals(Appointment.appointmentList.get(indexConfNum).getConfirmationNum()))
										  {
											  System.out.println("Your appointment has been cancelled. Press d to go back to the menu or e to exit");
										  }
                    }
            break;

          case "d": 
            System.out.println("A: Check-In");
            System.out.println("B: Set Appointment");
            System.out.println("C: Cancel Appointment");
            System.out.println("D: Display");
            System.out.println("E: Exit");
            break;

          case "e": 
						System.out.println("Thank you for visiting! Goodbye.");
						break;
            }
        }while(!choice.equals("e"));


    }

    public static String generateConfNum()
    {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }

    public static int getIndexOf(List<Appointment> list, String name) {
    int pos = 0;

    for(Appointment myObj : list) {
        if(name.equalsIgnoreCase(myObj.getLastName()))
            return pos;
        pos++;
    }

    return -1;
}
		public  static int getIndexOf2(List<Appointment> list, String confNum)
		{
			int pos = 0;

			for(Appointment myObj : list)
			{
				if(confNum.equalsIgnoreCase(myObj.getConfirmationNum()))
					return pos;
				pos++;
			}
			return -1;
		}

	// public static void deleteAppointment()
}