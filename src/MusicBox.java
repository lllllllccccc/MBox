
public class MusicBox {

    // Variables
    private String songID;
    private char isSongPremium;
    public String songTitle;
    private String songArtists;
    private String songAlbum;
    public String songGenre;
    private String songProducer;
    public String songMusicLabel;
    public int noAds;

    //Constructor
    public MusicBox(String songID, char premiumSong, String title, String artists, String Album, String genre, String producer, String label) {
        this.songID = songID;
        this.isSongPremium = premiumSong;
        this.songArtists = artists;
        
        // -- TODO
MusicBox mbox2 = new MusicBox("A12BC34", 'N', "Sunshine", "Bright", 
    "Happy day", "Pop", "Star Music", "Sunny Vibes");

playsong.playSong(mbox2.getSongID(), mbox2.getPremiumSong(), 1);
        /* Initialise  the rest of the variables in this Constructor block*/
    }
    
    this.songTitle = title;       
        this.songAlbum = Album;       
        this.songGenre = genre;       
        this.songProducer = producer;
        this.songMusicLabel = label;  
    //Methods
    public String getSongID() {
        return songID;
    }

    public String getSongArtists() {
        return songArtists;
    }

    public char getPremiumSong() {
        return isSongPremium;
    }


    public String getSongTitle() {
        return songTitle;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public String getSongProducer() {
        return songProducer;
    }

    public String getSongMusicLabel() {
        return songMusicLabel;
    }

    public int getNoAds() {
        return noAds;
    }
    
}

