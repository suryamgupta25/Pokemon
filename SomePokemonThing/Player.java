// The purpose of the player class is to track the moves that the player makes.
// Some form of abstraction to make the code inside the run method shorter and easier to understand
import java.util.ArrayList;

public class Player
{
    // Constants
    private static final int MAX_POKEMON = 1;
    
    
    // Instance Variables
    private ArrayList<Pokemon> backpack;
    private String name;
    private int numOfPokemon;

    
    // Constructor
    public Player (String name)
    {
        this.name = name;
        backpack = new ArrayList<Pokemon>();
        numOfPokemon = backpack.size();
    }
    
    // Returns player's name
    public String yourName()
    {
        return name;
    }
    
    // Adds the pokemon to the player's backpack if there are no alike pokemon
    public void addPokemon(Pokemon pokemon)
    {
        boolean alike = false;
        int alikeCounter = 0;
        
        for (int i = 0; i < backpack.size(); i++)
        {
            if (pokemon.compareTo(backpack.get(i)) == 1)
            {
                alikeCounter++;
            }
            else
            {
                alikeCounter = alikeCounter;
            }
        }
        
        if (alikeCounter!=0)
        {
            alike = true;
        }
        else
        {
            alike = false;
        }
        
        if (alike == false)
        {
            backpack.add(pokemon);
        }
    }
    // Remove a pokemon from the player's bag
    public void removePokemon (Pokemon pokemon)
    {
        backpack.remove(pokemon);
    }
    // Adds a move to the desired player pokemon
    public void addMove (Pokemon pokemon, Move move)
    {
        if (pokemon.learnMove(move) == true)
        {
            ArrayList<Move> pokemonbag = pokemon.getBook();
            pokemonbag.add(move);
            
        }
    }
    // Returns the player's bag
    public ArrayList<Pokemon> getBackpack()
    {
        return backpack;
    }
    // Returns the desired pokemon and it's moves based on it's name
    public Pokemon getPokemon(String pokemonName)
    {
        Pokemon desiredPokemon = null;
        for (int i = 0; i < numOfPokemon; i++)
        {
            Pokemon curPokemon = this.backpack.get(i);
            String curName = curPokemon.getName();
            if (pokemonName.equals(curName))
            {
                desiredPokemon = curPokemon;
            }
        }
        
        return desiredPokemon;
    }
    
    // Determines if the player has lost
    public boolean hasLost()
    {
        int alivePokemon = 0;
        boolean result = false;
        for (int i = 0; i < this.backpack.size(); i++)
        {
            Pokemon curPokemon = this.backpack.get(i);
            if (curPokemon.hasFainted() == false)
            {
                alivePokemon++;
            }
            else
            {
                alivePokemon = alivePokemon;
            }
        }
        if (alivePokemon == 0)
        {
            result = true;
        }
        else
        {
            result = false;
        }
        
        return result;
        
    }
    // Prints a list of the player's pokemon
    public void printPokemon()
    {
        for (int i = 0; i < backpack.size(); i++)
        {
            System.out.println(backpack.get(i).toString());
        }
        
        System.out.println();
    }
}
