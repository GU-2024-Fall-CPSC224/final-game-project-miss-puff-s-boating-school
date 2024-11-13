package edu.gonzaga;

//literally just stores the name
public class Player{
    private String name;

    public Player(String userName){
        this.name = userName;
    }
    //get the player's name
    public String getName(){
        return name;
    }
}