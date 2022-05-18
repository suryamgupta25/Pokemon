// The purpose of this class is to track the moves that the computer makes
// Purely based off of randomization

import java.util.ArrayList;

public class ComputerAI
{
    // Constants and instance variables
    private static final int MAX_POKEMON = 4;
    private ArrayList<Pokemon> compBackpack;
    private int numOfPokemon;
    
    // Constructor
    
    public ComputerAI()
    {
        compBackpack = new ArrayList<Pokemon>();
        numOfPokemon = compBackpack.size();
    }
    // Returns the size of the computer's pokemon bag
    public int compSize()
    {
        return compBackpack.size();
    }
    
    // Returns the computer's pokemon bag
    public ArrayList<Pokemon> getCompBag()
    {
        return compBackpack;
    }
    
    // Adds the 1st pokemon to the AI's backpack
    public void addPokemon1()
    {
        Pokemon pokemon1 = new Pokemon("Pikachu");
        Move bite = new Move("Bite", 30);
        pokemon1.learnMove(bite);
        Move scratch = new Move("Scratch", 10);
        pokemon1.learnMove(scratch);
        compBackpack.add(pokemon1);
    }
    
    // Adds the 2nd pokemon
    public void addPokemon2()
    {
        Pokemon pokemon2 = new Pokemon("Squirtle");
        Move tailSplash = new Move("Tail Splash", 10);
        pokemon2.learnMove(tailSplash);
        Move tailWhip = new Move("Tail Whip", 30);
        pokemon2.learnMove(tailWhip);
        compBackpack.add(pokemon2);
    }
    
    // Adds the 3rd pokemon
    
    public void addPokemon3()
    {
        Pokemon pokemon3 = new Pokemon("Charmander");
        Move intoFire = new Move("Into Fire", 30);
        pokemon3.learnMove(intoFire);
        Move smallBreath = new Move("Small breath", 10);
        pokemon3.learnMove(smallBreath);
        compBackpack.add(pokemon3);
        
    }
    
    // Adds the 4th pokemon
    public void addPokemon4()
    {
        Pokemon pokemon4 = new Pokemon("Bulbasaur");
        Move roll = new Move("Roll", 10);
        pokemon4.learnMove(roll);
        Move anotherOne = new Move("Another one", 30);
        pokemon4.learnMove(anotherOne);
        compBackpack.add(pokemon4);
    }
    
    // Prints out a list of computer pokemon
    public void printCompPokemon()
    {
        for (int i = 0; i < compBackpack.size(); i++)
        {
            System.out.println(compBackpack.get(i).toString());
        }
        
        System.out.println();
    }
    // Determines if the computer has lost or not
    public boolean hasLost()
    {
        int alivePokemon = 0;
        boolean result = false;
        for (int i = 0; i < this.compBackpack.size(); i++)
        {
            Pokemon curPokemon = this.compBackpack.get(i);
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
}    
