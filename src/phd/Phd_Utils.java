/*
Version 0.1
Includes methods used in main class. This is an attempt to separate code from logic, making code easier to debug 
and also making easier to focus on logic in main class.
Contains Public Methods:
Print_Predictions: Prints on screen all predictions for every user
Print_Similarities: Prints on screen all similar users, for every user.
Strict_Similarity: Manages the Arraylists holding the neighbors so as to contain only users having
                    rated the lastmovieID
 */
package phd;

/**
 *
 * @author Dennis
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Phd_Utils {
    
    
/**
 * 
 * Strict_Similarities: Method to keep only similar neighbors that have rated last movieID
 * 
 */

public static void Strict_Similarities (
int totalUsers, 
List<UserSimilarity>[] userSim, 
User[] Users, 
HashMap<CellCoor,UserMovie>  userMovies){

int  i;
int lastMovie;
List<UserSimilarity> UserList = new ArrayList<>();
Iterator<UserSimilarity> itr;
UserSimilarity io;
CellCoor cell = new CellCoor();

//System.out.println("Print Similarities");

for (i=0;i<=totalUsers;i++) 
{
    lastMovie=Users[i].lastMovieId;
    UserList=userSim[i];
    itr=UserList.iterator();

    if (UserList.size()>0) 
    {   
        while (itr.hasNext())
        {
            //io = new UserSimilarity();
            io = itr.next();
            cell.user=io.SUser_Id;cell.movie=lastMovie;
            if (userMovies.get(cell)==null)
                itr.remove();

        }//while
        
    }//if
                  
} //for i


} //END Strict_Similarities

public static void test() {
    int [] arr={1,2,3};
    double a = Arrays.stream(arr).summaryStatistics().getCount();
    
}

public static double Neg_Weight(int rating1, int rating2) {

int difference;
double weight=1.0;

difference=Math.abs(rating1-rating2);

switch(difference) {
    case 0: weight=0;break;
    case 1: weight=0.2;break;
    case 2: weight=0.4;break;
    case 3: weight=0.8;break;
    case 4: weight=1;break;
} 

return weight;

}

public static void Print_UserItems(
int totalUsers, 
User[] users, 
HashSet<Integer>[] usersRatingSet)
{
    
int i, j;

for (i=0; i<=totalUsers;i++) {

    System.out.println("\nUser: "+i+" Ratings: "+users[i].getRatingNum()+" Ratings Sum: "+users[i].getRatingSum()+" Ratings Inv Sum: "+users[i].invRatingSum+" Average: "+users[i].UserAverageRate()+" Inv Average: "+users[i].UserInvertedAverageRating()); 

    for (int k: usersRatingSet[i]) {
        //System.out.println("Size"+userMovies.size());
        System.out.print(" "+k);
    }    
        
}

}

public static void Print_Ratings(
int totalUsers, 
int totalMovies, 
User[] Users, 
Hashtable<CellCoor,UserMovie>  userMovies)
{
    
int i, j;
CellCoor cell;

Set<CellCoor> keys = userMovies.keySet();
for (i=0; i<=totalUsers;i++) 
     System.out.println("User: "+i+" Ratings: "+Users[i].getRatingNum()+" Ratings Sum: "+Users[i].getRatingSum()+" Ratings Inv Sum: "+Users[i].invRatingSum+" Average: "+Users[i].UserAverageRate()+" Inv Average: "+Users[i].UserInvertedAverageRating()); 

for (CellCoor key: keys)
{
        //System.out.println("Size"+userMovies.size());
        System.out.println("Value of:"+key.user+" "+key.movie+" is:"+userMovies.get(key).User_Id+" "+userMovies.get(key).Movie_Id + " Rating "+ userMovies.get(key).getRating()+" InvRating "+ userMovies.get(key).invRating);
}    
        

}



} //END class Phd_Utils
