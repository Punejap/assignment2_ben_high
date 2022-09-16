import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
/*
AUTHOR: Ben High
9/13/2022
 */
public class ManageVideoGames {
    static List<VideoGame> gamesList = new LinkedList<>();          //instantiate list
    static Scanner scnr = new Scanner(System.in);                   //instantiate scanner for user input
    public static void main(String[] args) {
        //initiate menu and user interaction
        displayMenu();
    }

    //define other methods for modularization, samples are listed below.
    //method to display menu
    public static void displayMenu() {
        //add your code
        //display the menu like in Page 5 in Assign2-F21.pdf 
          String menu = "-----Menu--------  \n" +
                  "1. Add a new game  \n" +
                  "2. Remove an existing game  \n" +
                  "3. Display the games in the order they were inserted  \n" +
                  "4. Find games with latest release  \n" +
                  "5. Add a new game in the alphabetical order of game titles \n" +
                  "6. Exit  ";

        System.out.println(menu);   //output text menu
        getUserChoice();            //user intercaction
    }    
    
    //method to get user choice 
    public static void getUserChoice() {
        //add your code
        // keep reading user input until user enters correct input

        //switch case for menu items. bad inputs handled in default case
        //begin the user requested action, then restart menu
        int choice = Integer.parseInt(String.valueOf(scnr.nextLine()));
        switch (choice) {
            case 1:
                //boolean alphabetical
                addNewGame(false);
                displayMenu();
                break;
            case 2:
                removeGame();
                displayMenu();
                break;
            case 3:
                displayCatalog();
                displayMenu();
                break;
            case 4:
                displayNewest();
                displayMenu();
                break;
            case 5:
                //boolean alphabetical
                addNewGame(true);
                displayMenu();
                break;
            case 6:
                //ends program
                return;
            default:
                //if the user input is not valid, display error msg and use fancy recursion so program runs uninterrupted
                System.out.println("bad input. try again");
                getUserChoice();
                break;
        }
    }
    
    
    //method to get user input, create and return a video game
    public static VideoGame getNewGame() {
        //add your code here
        /*
        get new game based on user input:
        keyboard input
          game title:
             use nextLine() to avoid problems caused by newline character
          platforms: 
             get how-many: int
             use loop to get each platform.

          date value: 
            get 3 integers: month, day, year
            use LocaleDate.of(year, month, day) to create a date
            TestDate.java           
        */
        String title;               //game title
        String developer;           //game dev
        String platformInput = "0"; //initialize # of platform input now to handle invalid input later
        int numPlatforms = 0;       //platformInput will get converted from string to int
        LocalDate releaseDate;      //release date

        System.out.println("enter title: ");
        title = scnr.nextLine();
        System.out.println("enter developer: ");
        developer = scnr.nextLine();

        //while loop will only exit when user input is valid
        System.out.println("enter number of platforms: ");
        while(!scnr.hasNextInt()){
            System.out.println("invalid input. please enter an integer");
            scnr.nextLine();
        }
        platformInput = scnr.nextLine();        //input is valid
        numPlatforms = Integer.parseInt(String.valueOf(platformInput)); //input converted to int
        String[] platforms = new String[numPlatforms];  //string array of platforms created

        System.out.println("enter platform: ");     //loop to enter however many platforms
        for(int i=0; i<numPlatforms; i++){
            platforms[i] = scnr.nextLine();
        }

        releaseDate = userSetReleaseDate();         //release date is captured with its own function
        VideoGame game = new VideoGame(title, developer, platforms, releaseDate);   //game constructed

        return game;        //newly constructed game object returned
    }

    public static LocalDate userSetReleaseDate(){
        //capture release date, insure input is valid
        int year,month,day;
        LocalDate releaseDate;
        System.out.println("enter release date: \nenter year:");
        year = Integer.parseInt(String.valueOf(scnr.nextLine()));
        System.out.println("enter month:");
        month = Integer.parseInt(String.valueOf(scnr.nextLine()));
        System.out.println("enter day:");
        day = Integer.parseInt(String.valueOf(scnr.nextLine()));
        try {
            //insure input is valid
            releaseDate = LocalDate.of(year, month, day);
        }catch (DateTimeException e){
            //if input is invalid, display error msg and use fancy recursion until input is valid
            System.out.println("bad input! try again");
            releaseDate = userSetReleaseDate();
        }
        return releaseDate;
    }


    //method to add a video game without maintaining sorted order
    //add your own code
    public static void addNewGame(boolean alphabetical){
        VideoGame game = getNewGame();         //construct new video game object
        if((alphabetical==false) || (gamesList.size()==0)) {    //if its not added alphabetically, or if the list is empty
            gamesList.add(game);        //simply append the game to the list
            System.out.println(game.getTitle()+" non-alphabetically added to list");
        }
        else if(alphabetical==true){            //if game is to be added alphabetically
            for(VideoGame vg : gamesList){
                if(game.compareTo(vg) < 0){     //loop through the list and look for a game that is alphabetically after new game
                    gamesList.add(gamesList.indexOf(vg), game);     //put the game in the correct index
                    System.out.println(game.getTitle()+" added directly before "+vg.getTitle());    //friendly message
                    break;
                }
            }
            if(!gamesList.contains(game)) {     //if loop finishes and game hasnt been added then it is alphabetically last
                gamesList.add(game);
            }
        }
    }

    //method to remove a game based on user input
    //add your own code
    public static void removeGame(){
        //get the title, compare it to the titles in the list, remove when found
        String title;
        System.out.println("enter title of game to remove: ");
        title = scnr.nextLine();
        for(VideoGame vg : gamesList){
            if(vg.getTitle().equals(title)){
                gamesList.remove(vg);
                System.out.println(vg.getTitle()+" has been removed");
                return;     //end method when game has been removed
            }
        }
        //if the above process completes without finding the game, it is not in the list
        System.out.println("game not found!");
    }

    public static void displayCatalog(){
        System.out.println(gamesList.toString());   //displays title and all other info
    }

    public static void displayNewest(){
        //get the first game to compare release dates
        VideoGame newest = gamesList.get(0);
        //if multiple games share the latest date, display all of them
        List<VideoGame> newestGames = new ArrayList<>();
        newestGames.add(newest);
        //compare the first game to the rest in the list
        for(VideoGame vg : gamesList.subList(1, gamesList.size())){
            if(newest.getReleaseDate().isBefore(vg.getReleaseDate())){
                //if a game in the list is alphabetically before the game being checked, clear the list
                newestGames.clear();
                newest = vg;
                newestGames.add(newest);
            }
            if(newest.getReleaseDate().equals(vg.getReleaseDate())){
                newestGames.add(vg);
            }
        }
        System.out.println(newestGames);
    }





    //method to find the game with latest release date
    //add your own code    
    /*
    find game with latest release date
       simple assumption: only one game has the latest release date.
       need to loop through the collection and find the latest release date                                                        (largest)
            while looping, 
               record and update the current latest release date value 
                   and the corresponding VideoGame object.

       compare dates: isBefore, isAfter, or compareTo
           TestDate.java    
    */    
    
    
    //OPTIONAL BONUS:
    //  method to add a video game in alphabetical order of game titles
    //add your own code   
    /*    
     add new game in alphabetical order of game titles
        Do not append the new game to the current collection and then sort the entire collection. 
	Instead,
           start with the first game, 
           loop through the collection and 
               find the first game whose title is alphabetically larger than the new game. 
               Then insert the new game at the location of this target.
           If no existing game title is larger than the new game,
               append the new game to the end of game collection list.    
    
   */  
}
