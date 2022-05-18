import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Pokemon implements Comparable<Pokemon>
{
    // Constants
    private static final int MAX_HEALTH = 100;
    private static final int MOST_MOVES = 2;
    private static final int MAX_DAMAGE = 30;
    
    // Instance Variables
    private String name;
    private int health;
    private ArrayList<Move> bookOfMoves;
    private int numOfMoves;
    private String image;

    // Constructor
    public Pokemon (String name)
    {
        this.name = name;
        this.health = MAX_HEALTH;
        this.bookOfMoves = new ArrayList<Move>();
        this.numOfMoves = this.bookOfMoves.size();
        this.image = image;
    }
    
    PokemonImages images = new PokemonImages();
    
    // Gets the pokemon's move at an index in its book array
    public Move returnMove(int index)
    {
        return this.bookOfMoves.get(index);
    }
    
    // Returns the name of the pokemon
    public String getName()
    {
        return this.name;
    }
    
    // Returns the pokemon's health
    public int getHealth()
    {
        return this.health;
    }
    
    // Returns the pokemon's image
    public String getImage()
    {
        return this.image;
    }
    
    /* A method to set the health of a pokemon once it has
    been attacked*/
    public void setHealth(int health)
    {
        this.health = health;
    }
    
    // Sets the image of the pokemon based off of the PokemonImages class
    public void setImage(String image)
    {
        this.image = image;
    }
    // Returns the array list containing the book of moves
    public ArrayList<Move> getBook()
    {
        return this.bookOfMoves;
    }
    
    // Returns the number of the moves (the length of the book of moves array)
    public int getNumOfMoves()
    {
        return this.bookOfMoves.size();
    }
    
    // If the pokemon has already learned MOST_MOVES moves, then the pokemon may not learn any more moves
    public boolean canLearnMoreMoves()
    {
        if (this.bookOfMoves.size() >= MOST_MOVES)
        {
            System.out.println("Sorry, you may not be able to learn more moves!");
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /* Adds the move to the pokemon's list of moves given that
    it can still learn more moves*/
    /* The pokemon may only have one move of damage at MAX_DAMAGE, and
    the rest of its attacks must be less than/equal to 15 */

    

    public boolean learnMove(Move move)
    {
        ArrayList<Move> moves15 = new ArrayList<Move>();
        boolean permittedMove = true;

        if (this.numOfMoves < MOST_MOVES)
        {
            for (int i = 0; i < bookOfMoves.size(); i++)
            {
                Move currentMove = bookOfMoves.get(i);
                int currentMoveDamage = currentMove.getDamage();
                if (currentMoveDamage > 15 && currentMoveDamage <= MAX_DAMAGE)
                {
                    moves15.add(currentMove);
                }
            }
    
            if (move.getDamage() > 15)
            {
                if (moves15.size() == 0)
                {
                    permittedMove = true;
                    bookOfMoves.add(move);
                }
                
                else
                {
                    permittedMove = false;
                }
            }
            
            else
            {
                permittedMove = true;
                bookOfMoves.add(move);
            }
        }
        
        else
        {
            permittedMove = false;
        }

        return permittedMove;
    }
    
    
    // Prints out the pokemon's moves
    public void printMoves()
    {
        for (int i = 0; i < bookOfMoves.size(); i++)
        {
            System.out.println(bookOfMoves.get(i).toString());
        }
    }
    
    // Returns the pokemon's information
    public String toString()
    {
        return this.name + " (Health: " + this.health + "/" + MAX_HEALTH + ")";
    }
    
    /* Determines if a pokemon has fainted. A pokemon is classified
    as fainted if it's health is less than or equal to 0*/
    
    public boolean hasFainted()
    {
        if (this.health <= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /* Method Overloading. A move could either be called
    by the actual move or just the move name*/
    public void removeMove(Move move)
    {
        bookOfMoves.remove(move);
    }
    
    public void removeMove(String moveName)
    {
        for (int i = 0; i < this.bookOfMoves.size(); i++)
        {
            Move currentMove = this.bookOfMoves.get(i);
            String currentMoveName = currentMove.getName();
            
            if (moveName.equals(currentMoveName))
            {
                bookOfMoves.remove(this.bookOfMoves.get(i));
            }
        }
    }
    
    // Method overloading here
    // A move could be called by the user by the move itself or just the name of the move
    public boolean knowsMove(Move move)
    {
        boolean knownMove = false;
        int trueCounter = 0;
        
        for (int i = 0; i < this.bookOfMoves.size(); i++)
        {
            if (move.compareTo(this.bookOfMoves.get(i)) == 1)
            {
                trueCounter++;
            }
            else
            {
                trueCounter = trueCounter;
            }
        }
        
        if (trueCounter == 0)
        {
            knownMove = false;
        }
        else
        {
            knownMove = true;
        }
        return knownMove;
    }
    
    public boolean knowsMove(String moveName)
    {
        boolean result = true;
        for (int i = 0;  i < bookOfMoves.size(); i++)
        {
            Move currentMove = bookOfMoves.get(i);
            String currentMoveName = currentMove.getName();
            if (moveName.equals(currentMoveName))
            {
                result = true;
            }
            else
            {
                result = false;
            }
        }
        
        return result;
    }
    
    /* Given that the move is known and the opponent pokemon
    has not fainted, the pokemon is able to attack it's opponent
    with the desired move*/
    
    public boolean attack(Pokemon opponent, Move move)
    {
        if (knowsMove(move) == true && opponent.hasFainted() == false)
        {
            int newHealth = opponent.getHealth() - move.getDamage();
            opponent.setHealth(newHealth);
            System.out.println("Attack success!");
            return true;
        }
        else
        {
            System.out.println("Attack failed.");
            return false;
        }
    }
    
    // This method applies the comparision of moves method to determine if two pokemon are the same
    // This will be very useful for creating the Player class, which keep tracks of the actions that the player performs
    public int compareTo(Pokemon other)
    {
        ArrayList<Move> book1 = this.bookOfMoves;
        ArrayList<Move> book2 = other.getBook();
        int moveCounter1 = book1.size();
        int otherMoveCounter = book2.size();
        String otherName = other.getName();
        int otherHealth = other.getHealth();
    
        int result = 0;
        int falseCounter = 0;
        if (this.name.equals(otherName) && this.health == otherHealth && moveCounter1 == otherMoveCounter)
        {
            for (int i = 0; i < book1.size(); i++)
            {
                if (book1.get(i).compareTo(book2.get(i)) == 1)
                {
                    falseCounter = falseCounter;
                }
                else
                {
                    falseCounter++;
                }
            }
            
            if (falseCounter == 0)
            {
                result = 1;
            }
            else
            {
                result = -1;
            }
            
        }  
        else
        {
            result = -1;
        }
        return result;
    }
}