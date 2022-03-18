import java.util.*;
class Appointment
{
    private String confirmationNum;
    private String firstName, lastName;
    private int month, day, year;
    private int hour;
    private int minute;
    
    static List<Appointment> appointmentList = new ArrayList<Appointment>();

    public Appointment()
    {
        month = 1;
        day = 1;
        year = 0;
        hour = 0;
        minute = 0;
        firstName = "John";
        lastName = "Doe";
        confirmationNum = "000000";
    }

    public Appointment(int m, int d, int y, int hr, int min, String confNum)
    {
        month = m;
        day = d;
        year = y;
        hour = hr;
        minute = min;
        firstName = "John";
        lastName = "Doe";
        confirmationNum = confNum;
    }

    public Appointment(int m, int d,int y, int hr, int min, String fName, String lName, String confNum)
    {
        this.month = m;
        this.day = d;
        this.year = y;
        this.hour =  hr;
        this.minute = min;
        this.firstName = fName;
        this.lastName = lName;
        this.confirmationNum = confNum;
    }

    public void setMonth(int m)
    {
        this.month = m;
    }

    public void setDay(int d)
    {
        this.day = d;
    }

    public void setYear(int y)
    {
        this.year = y;
    }

    public void setHour(int hr)
    {
        this.hour = hr;
    }

    public  void setMinute(int min)
    {
        this.minute = min;
    }

    public void setFirstName(String fName)
    {
        this.firstName = fName;
    }

    public void setLastName(String lName)
    {
        this.lastName = lName;
    }

    public void setConfirmationNum(String confNum)
    {
        this.confirmationNum = confNum;
    }

    public String getConfirmationNum()
    {
        return confirmationNum;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getMonth()
    {
        return month;
    }

    public int getDay()
    {
        return day;
    }

    public int getYear()
    {
        return year;
    }

    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public String appointmentSetToString()
    {
        return "Your appointment for " + month + "/" + day + "/" + year + " @ " + hour + ":" + minute
                + " has been set.  Your confirmation number will be " + confirmationNum + ".";
    }

    public String appointmentCancelToString()
    {
        return "Your appointment for " + month + "/" + day + "/" + year + " @ " + hour + ":" + minute
                + " has been cancelled.";
    }




}

class DentalAppt extends Appointment {
  
  double cost = 60;

  public String costToString()
    {
    return String.format(" Your dental appointment will cost $%.2f.\n", cost);
    }
}

class PediatricAppt extends Appointment {
  
  double cost = 32.50;

  public String costToString()
    {
    return String.format(" Your pediatric appointment will cost $%.2f.\n", cost);
    }
}

class OptometryAppt extends Appointment {
  
  double cost = 45.67;

  public String costToString()
    {
    return String.format(" Your optometry appointment will cost $%.2f.\n", cost);
    }
}