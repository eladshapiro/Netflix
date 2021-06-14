import java.time.LocalDate;
import java.util.Random;

public class Episode
{
    private String episodeName;
    private String episodeSummery;
    private LocalDate episodeDebuted;
    private boolean seen; //true-seen, false-didn't see
    private String[] whoSeenTheEpisode;


    public boolean getSeen() {
        return seen;
    }

    public void setSeen(String accountName) {
        this.seen = seen;
        for (int i=0;i<whoSeenTheEpisode.length;i++)
        {
            if (whoSeenTheEpisode[i]==null)
            {
                this.whoSeenTheEpisode[i] = accountName;
            }
        }
    }

    public Episode(String episodeName, String episodeSummery, LocalDate episodeDebuted)
    {
        Random rnd=new Random();
        int dateRand=rnd.nextInt(366);
        int yearRand=rnd.nextInt(2022);
        while(dateRand==0 && yearRand==0)
        {
             dateRand = rnd.nextInt(366);
             yearRand = rnd.nextInt(2022);
        }
        this.episodeName = episodeName;
        this.episodeSummery = episodeSummery;
        this.episodeDebuted = LocalDate.ofYearDay(yearRand,dateRand);
        this.whoSeenTheEpisode=new String[100];
        this.seen=true;
        whoSeenTheEpisode[0]=new String("elad");    //for demonstration
        whoSeenTheEpisode[1]=new String("elad");
        whoSeenTheEpisode[2]=new String("elad");
    }

    public boolean isSeen() {
        return seen;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getEpisodeSummery() {
        return episodeSummery;
    }

    public void setEpisodeSummery(String episodeSummery) {
        this.episodeSummery = episodeSummery;
    }

    public LocalDate getEpisodeDebuted() {
        return episodeDebuted;
    }

    public void setEpisodeDebuted(LocalDate episodeDebuted) {
        this.episodeDebuted = episodeDebuted;
    }

    public void print()
    {
        System.out.println("Episode name: "+episodeName);
        System.out.println("Episode summery: "+episodeSummery);
        System.out.println("episodeDebuted: "+episodeDebuted);
    }

}
