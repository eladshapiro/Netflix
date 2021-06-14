

import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        Account[] allAccounts = new Account[100];
        Series[] series=new Series[100];
        Scanner scanner=new Scanner(System.in);
        series[0]=new Series("Fauda",3);          //for the demontration
        series[1]=new Series("Black mirror",3);
        series[2]=new Series("Friends",3);
        boolean flag = false;
        int menuStop=0;
        while (true)
        {
            try
            {

                switch (entry(allAccounts)) {
                    case 1: {
                        openAnAccount(allAccounts);
                        break;
                    }
                    case 2: {
                        int accountIndex = EnterAnAccount(allAccounts);
                        if (accountIndex != -1) {
                            while (menuStop != 5) {
                                menuStop = menu(allAccounts[accountIndex], series);
                            }
                            menuStop = 0;  //to initialize the selection
                        }
                        break;
                    }
                    default: {
                        System.out.println("you entered a wrong number try again");
                        break;
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("\nError! try again!\n");
            }
        }
    }







    public static int entry(Account[] allAccounts) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Netflix");
        System.out.println("To open an account enter 1\nTo enter to a existing account press 2 ");
        int entryNumber = scanner.nextInt();
        scanner.nextLine();
        return entryNumber;
    }



    public static Account openAnAccount(Account[] allAccounts)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your user name");
        String userName = scanner.nextLine();
        Account account=new Account();
        while (notOccupiedUserName(userName, allAccounts)!=-1)
        {
            System.out.println("please enter a new User name, it is taken already");
            userName = scanner.nextLine();
        }
        for (int i = 0; i < allAccounts.length; i++) {
            if (allAccounts[i] == null)
            {
                account = new Account(userName); //in the constractor it asks for the password
                allAccounts[i]=new Account(account);
                break;
            }
        }
        return account;
    }



    public static int notOccupiedUserName(String userName, Account[] allAccounts) {
        if (allAccounts[0] == null)   //empty array
        {
           return  -1;
        }
        for (int i = 0; i < allAccounts.length; i++) {
            if (allAccounts[i].getUserName().equals(userName)) {
                return i;
            }
        }
       return -2;   //if the user dosen't exist
    }



    public static int EnterAnAccount(Account[] allAccounts)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your user name");
        String userName = scanner.nextLine();
        System.out.println("please enter your PassWord");
        String passWord = scanner.nextLine();
        int occupiedNum=notOccupiedUserName(userName, allAccounts);
        if (occupiedNum==-1 || occupiedNum==-2 )   //empty array or there isnt a user name like that1
        {
            System.out.println("There isn't an account with with this user name ");
            return -1;
        }
        else
        {
            if ((allAccounts[occupiedNum].getPassWord()).equals(passWord))    //checks if the password is the same
            {
                return occupiedNum;
            }
            else
            {
                System.out.println("The password doesn't match the userName");
                return -1;
            }
        }
    }




    public static int menu(Account account,Series[] series)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You Entered the system!");
        System.out.println("please enter the thing you want to do: \n");
        System.out.println("1-Show all series");
        System.out.println("2-Show the series that you started seeing");
        System.out.println("3-Show the account details");
        System.out.println("4-Pick a series to play");
        System.out.println("5-Go back to the main menu");
        int pickFromMenu = scanner.nextInt();
        scanner.nextLine();
        boolean flag=true;
        switch (pickFromMenu) {
            case 1: {
                printSeries(series);
                return 1;
            }
            case 2: {
                printStartedSeries(account);
                return 2;
            }
            case 3: {
                printAccountDetails(account);
                return 3;
            }
            case 4: {
                chooseASeries(series, account);
                return 4;
            }
            case 5: {
                return 5;
            }
            default: {
                System.out.println("You entered an illegal number");
                return 0;
            }
        }

    }



    public static void printSeries(Series[] series)
    {
        System.out.println("Here is a list of all of the series: \n");
        for (int i=0;i<series.length;i++)
        {
            if (series[i]!=null)
            {
                series[i].printSeries();
            }
        }

    }



    public static void printStartedSeries(Account account)
    {
        System.out.println("this is the series you started watching:");
        if (account.isTheHadSeenIsEmpty()==false)
        {
            for (int i=0;i<account.getHadSeenSeries().length;i++)
            {
                if (account.getHadSeenSeries()[i]!=null)
                {
                    account.getHadSeenSeries()[i].printSeries();
                }
            }
        }
    }



    public static void printAccountDetails(Account account)
    {
        int seriesEnd=0;
        int seriesStart=0;
        int episodeSum=0;
        System.out.println("account details:");
        System.out.println("Subscription start: "+account.getSubscriptionStart());
        System.out.println("Subscription End: "+account.getSubscriptionEnd());
        for (int i=0;i<account.getHadSeenSeries().length;i++)
        {
            if (account.getHadSeenSeries()[i]!=null)
            {
                if (account.getHadSeenSeries()[i].didYouEndTheSeries())
                {
                    seriesEnd++;
                }
                else
                {
                    seriesStart++;
                }
                episodeSum+=account.getHadSeenSeries()[i].getNumOfEpisodesSeen();
            }
        }
        System.out.println("You Started "+seriesStart+" series");
        System.out.println("You Ended "+seriesEnd+" series");
        System.out.println("Over all you saw "+episodeSum+" episodes");
    }




    public static void chooseASeries(Series[] series,Account account)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the series name:");
        String seriesName = scanner.nextLine();
        int seriesIndex=doesTheSeriesExist(seriesName,series);
        if (seriesIndex==-1)
        {
            System.out.println("Sorry your series doesn't exist");
        }
        else
        {
            series[seriesIndex].printEpisodes();
            series[seriesIndex].chooseEpisode(account.getUserName());
        }
    }



    public static int doesTheSeriesExist(String checkSeries,Series[] series)
    {
        for (int i=0;i<series.length;i++)
        {
            if (series[i]==null)
            {
                return -1;                         //if there isnt- stop
            }
                if (series[i].getSeriesName().equals(checkSeries))
                {
                    return i;    //return the position of the series
                }
        }
        return -1;
    }





}




