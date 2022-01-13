import java.util.ArrayList;
import java.util.Collections;

class Account{
    Information player;
    int gamesNumber;
    ArrayList<Character> characters;
    Account(Credentials credential, String playersName, int gamesNumber, ArrayList<Character> characters,String country,ArrayList<String> favouriteGames){
        this.player = new Information.InformationBuilder(credential, playersName)
                .country(country)
                .favouriteGames(favouriteGames)
                .build();
        this.gamesNumber = gamesNumber;
        this.characters = characters;
    }
    //builder design pattern
    public static class Information {
        private Credentials credentials;
        private ArrayList<String> favouriteGames;
        private String playersName;
        private String country;
        private Information(InformationBuilder builder){
            this.credentials = builder.credentials;
            this.favouriteGames = builder.favouriteGames;
            this.playersName = builder.playersName;
            this.country = builder.country;
        }
        public Credentials getCredentials(){
            return credentials;
        }
        public String getPlayersName(){
            return playersName;
        }
        public String getCountry(){
            return country;
        }
        public ArrayList<String> getFavouriteGames(){
            return favouriteGames;
        }
        void sortFavGames(){
            Collections.sort(favouriteGames);
        }
        public static class InformationBuilder{
            private Credentials credentials;
            private ArrayList<String> favouriteGames;
            private String playersName;
            private String country;
            public InformationBuilder(Credentials credential,String playersName){
                this.credentials = credential;
                this.playersName = playersName;
            }
            public InformationBuilder country(String country){
                this.country = country;
                return this;
            }
            public InformationBuilder favouriteGames(ArrayList<String> favouriteGames){
                this.favouriteGames = favouriteGames;
                return this;
            }
            public Information build() {
                Information user =  new Information(this);
                return user;
            }
        }
    }
}
class Credentials{
    private String email;
    private String password;
    Credentials(String email, String password){
        this.email = email;
        this.password = password;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String newEmail){
        email = newEmail;
    }
    public void setPassword(String newPassword){
        password = newPassword;
    }
}