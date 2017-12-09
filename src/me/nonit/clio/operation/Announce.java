package me.nonit.clio.operation;

import java.util.ArrayList;
import java.util.Random;

public class Announce
{
    private ArrayList<String> messages;
    private Random randomGen;

    public Announce()
    {
        this.messages = new ArrayList<String>();
        this.randomGen = new Random();

        messages.add( "Drinking from water bottles helps with the hunger." );
        messages.add( "Sneaking activates you're cloaking shield." );
        messages.add( "People can tell when you fire you're gun..." );
        messages.add( "I was built in OkiCore Labs..." );
        messages.add( "Shift for a better aim when firing ;D" );
        messages.add( "Perhaps when you win we can have a lil personal time huh ;D" );
        messages.add( "When everyone is dead I will rule the world!" );
        messages.add( "Have you tried Melon's Server? Its the best for survival ;D" );
        messages.add( "Severed heads are a girls best friend :D" );
        messages.add( "If you kill everyone Ill send nudes.." );
        messages.add( "Don't worry about the mess, the world gets reset." );
    }

    public String getAnnouncement()
    {
        int index = randomGen.nextInt( messages.size() );
        return messages.get( index );
    }

}