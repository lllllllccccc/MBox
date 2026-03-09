
public class Main{
    public static void main(String[] args) {
        
        MusicBox mbox = new MusicBox("S34TG65", 'Y', "Raindrops", "Misty", 
        "The path less traveled", "Country", "BZEE Music", "Rhythm Divine");

        PlaySongs playsong = new PlaySongs();
        
        playsong.playSong(mbox.getSongID(), mbox.getPremiumSong(), 2);

        
    
        MusicBox mbox2 = new MusicBox("X789KL01", 'N', "Sunset", "Ocean Breeze", 
        "Morning Light", "Pop", "Blue Wave Band", "Coastal Vibes");
        
        playsong.playSong(mbox2.getSongID(), mbox2.getPremiumSong(), 1);
        

    }
}




