public class Move implements Comparable<Move>
{
    // Instance Variables and constants
    private String name;
    private int damage;
    private static final int MAX_DAMAGE_PER_MOVE = 30;
    
    // Move constructor
    public Move (String name, int damage)
    {
        this.name = name;
        this.damage = damage;
        if (this.damage > MAX_DAMAGE_PER_MOVE)
        {
            System.out.println("The max damage per move is " + MAX_DAMAGE_PER_MOVE);
            this.damage = MAX_DAMAGE_PER_MOVE;
            
        }
        
    }
    
    // Returns name of move
    public String getName()
    {
        return this.name;
    }
    
    /* Returns damage of move, should always be no more than
    MAX_DAMAGE_PER_MOVE */
    
    public int getDamage()
    {
        return this.damage;
    }
    
    
    // Converts move to a string just for future game tests
    public String toString()
    {
        return this.name + " (" + this.damage + " Damage)";
    }
    
    /* Compares two moves to each other. This will be very useful
    and will be utilized a lot in the creation of the Pokemon class */
    
    public int compareTo (Move other)
    {
        if (this.name.equals(other.getName()) && this.damage == other.getDamage())
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
