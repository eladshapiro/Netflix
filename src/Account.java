
import java.util.Scanner;
import java.time.LocalDate;

public class Account
{
    private String userName;
    private String passWord;
    private Series[] hadSeenSeries;
    private LocalDate SubscriptionStart;
    private LocalDate SubscriptionEnd;


    public LocalDate getSubscriptionStart() {
        return SubscriptionStart;
    }

    public void setSubscriptionStart(LocalDate subscriptionStart) {
        SubscriptionStart = subscriptionStart;
    }

    public LocalDate getSubscriptionEnd() {
        return SubscriptionEnd;
    }

    public void setSubscriptionEnd(LocalDate subscriptionEnd) {
        SubscriptionEnd = subscriptionEnd;
    }

    public Series[] getHadSeenSeries() {
        return hadSeenSeries;
    }

    public void setHadSeenSeries(Series[] hadSeenSeries) {
        this.hadSeenSeries = hadSeenSeries;
    }

    public Account(Account account) {
        this.userName = account.getUserName();
        this.passWord = account.getPassWord();
        this.hadSeenSeries = account.getHadSeenSeries();
        this.SubscriptionStart=account.getSubscriptionStart();
        this.SubscriptionEnd=account.getSubscriptionEnd();

    }
    public Account(String userName)
    {
        this.userName = userName;
        this.SubscriptionStart=LocalDate.now();
        this.SubscriptionEnd=getSubscriptionStart().plusYears(1);  //the subscription ends in one year
        this.hadSeenSeries=new Series[100];
        boolean passFlag=setPassWord();
        while (passFlag)
        {
            passFlag=setPassWord();
        }
    }
    public Account()   //empty constructor
    {
        this.userName=null;
        this.passWord=null;
    }

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;

    }
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public boolean setPassWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your user password, it has to have a english letter a number and to be 6 chars long at list ");
        String password = scanner.nextLine();
        boolean isNumber = false;
        boolean isEnglishLetter = false;
        char[] charArray = password.toCharArray();
        for (int i = 0; i < charArray.length; i++)
        {
            char ch = charArray[i];
            if ((ch >= 'A' && ch <= 'z'))
            {
                isEnglishLetter = true;
            }
            if (ch >= '1' && ch <= '9')
            {
                isNumber = true;
            }
        }

        if (password.length() >= 6 && isEnglishLetter == true && isNumber == true)
        {
            this.passWord = password;
            return false;
        }
        else
        {
            System.out.println("please try again your password didn't mach the rules");
            return true;
        }
    }

    public  boolean isTheHadSeenIsEmpty()   //checks if you didnt see any series
    {
        if (this.hadSeenSeries[0]==null)
        {
            System.out.println("You didn't see any series");
            return true;
        }
        return false;
    }
}
