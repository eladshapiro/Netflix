import java.time.LocalDate;
import java.util.Scanner;

public class Series
{
  private String seriesName;
  private Episode[] episodes;

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Episode[] getEpisodes() {
        return episodes;
    }

    public void setEpisodes(Episode[] episodes) {
        this.episodes = episodes;
    }

    public Series(String seriesName, Episode[] episodes) {
        this.seriesName = seriesName;
        this.episodes = episodes;
    }

    public Series(String seriesName, int numberOfEpisodes)
    {
        this.seriesName = seriesName;
        this.episodes = new Episode[numberOfEpisodes];
        LocalDate episodeDebuted=LocalDate.now();
        episodes[0] = new Episode("firstEpisode", "fun one",episodeDebuted );
        episodes[1] = new Episode("secondEpisode", "very fun one",episodeDebuted );
        episodes[2] = new Episode("thirdEpisode", "very very fun one",episodeDebuted );
    }
    public void printSeries()
    {
        System.out.println("Series: "+ this.seriesName);
        System.out.println("");  //to separate between the series
    }
    public void printSeriesAndEpisodes()
    {
        int episodesSum=0;
        System.out.println("Series: "+ this.seriesName);
        System.out.println("it's episodes that you saw: ");
        for (int i=0;i<episodes.length;i++)
        {
            if (episodes[i].getSeen()==true)
            {
                episodesSum++;
            }
            if (episodesSum>0 && episodes[i].getSeen()==false)   // last episode you saw
            {
                System.out.println("this is the last episode you saw in this series: "+i); //it is one over becouse there isnt a 0 episode
            }
        }
        if (episodesSum==0)
        {
            System.out.println("you didn't sse any episode of this series");
        }
    }

    public boolean didYouEndTheSeries()
    {
        int episodesSum=0;
        for (int i=0;i<episodes.length;i++)
        {
            if (episodes[i].getSeen()==true)
            {
                episodesSum++;
            }
        }
        if (episodesSum==episodes.length)  //finish the series
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getNumOfEpisodesSeen()
    {
        int episodesSum=0;
        for (int i=0;i<episodes.length;i++)
        {
            if (episodes[i].getSeen()==true)
            {
                episodesSum++;
            }
        }
        return episodesSum;
    }

    public void printEpisodes()
    {
        for (int i=0; i<episodes.length;i++)
        {
            if (episodes[i]==null)
            {
                break;
            }
            else
            {
                System.out.println("Episode number: "+i);
                episodes[i].print();
            }
        }
    }

    public void chooseEpisode(String accountName)
    {
        Scanner scanner = new Scanner(System.in);
        boolean flag=false;
        for (int i=0;i<episodes.length;i++)   //cheking if the user saw the episodes
        {
            if (episodes[i].getSeen())
            {
                flag=true;     //even one episode was seen
            }
            else if (flag==true)
            {
                System.out.println("this is the last episode you saw: ");
                episodes[i-1].print();   //i-1, becouse i is in the episode that we didn't see
                break;  //stop! we found the last episode
            }
        }
        if (!flag)
        {
            System.out.println("\nYou didn't see any episodes");   //if you didn't see any episodes
        }
            System.out.println("please enter the episode you want to see now:");  // what you want to see now
            String episodeName = scanner.nextLine();
            int episodeIndex = doesTheEpisodeExist(episodeName);
            if (episodeIndex != -1)   //it exist
            {                                     // add the account name to the list of people that seen it
                episodes[episodeIndex].setSeen(accountName);
            }
        System.out.println("Thank you!");

    }


    public int doesTheEpisodeExist(String episodeName)
    {
        for (int i=0;i<episodes.length;i++)
        {
            if (episodes[i].getEpisodeName().equals(episodeName))
            {
                return i;   //return the place of the episode
            }
            else
            {
                return -1;
            }
        }
        return -1;
    }


}
