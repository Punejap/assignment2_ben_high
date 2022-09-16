//newer Java API for handling date values
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class VideoGame implements Comparable<VideoGame> {

    //2.2.1 Entity Class - VideoGame
    
//    private static final int DEFAULT_NUMBER_OF_PLATFORMS = 5;

    //data fields
    private String title;
    private String developer;     //lead developer 
    private String platforms[];
    private LocalDate releaseDate;  //LocalDate date1 = LocalDate.of(2017, 1, 13);


    
    @Override
    public String toString() {
        //add your code
        //return a string including all infor. about a game
        
        // See examples in: Animal class, Computer class        
        
        // date value included in format: 9/15/2020 for Sep. 15, 2020
        
        //display date values in US format:
        //    Get a DateTimeFormatter object with the specified pattern
        //    Use the DateTimeFormatter object to call format(..) method.

        String platformConcat = "";
        for(int i=0; i< platforms.length;i++){
            platformConcat += platforms[i] + " ";
        }
        String gameInfo = "Title: "+title+" || Developer: "+developer+" || Platforms: "+platformConcat
                +" || Release Date: "+ DateTimeFormatter.ofPattern("MM/dd/yyyy").format(releaseDate)
                +"\n";
        
        return gameInfo;
    }

    @Override
    public boolean equals(Object otherObject) {
        //add your code
        // comparing two VideoGame objects based only on title
        
        //see example in DirectoryEntryV2.java in pkg: arraylist
        if(title == ((VideoGame)otherObject).getTitle()){
            return true;
        }
        else return false;
    }  
    
    
    //******The following code don't need to be changed.*****//
    
    //You don't need to change this method.
    //This method is used in addVideoGameIn.
    @Override 
    public int compareTo(VideoGame other) {
        return this.title.compareTo(other.title);
    }   
    
    //no-argument constructor
    public VideoGame() {
    }    
    
    //constructor taking in values for all data fields
    public VideoGame(String title, String developer, String[] platforms, LocalDate releaseDate) {
        this.title = title;
        this.developer = developer;
        this.platforms = platforms;
        this.releaseDate = releaseDate;
    }

    //getters
    public String getTitle() {
        return title;
    }

    public String getDeveloper() {
        return developer;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public void setPlatforms(String[] platforms) {
        this.platforms = platforms;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }    
}
