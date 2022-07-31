package cf.warriorcrystal.evo.event.events;

import cf.warriorcrystal.evo.event.EvoEvent;

public class PlayerJoinEvent extends EvoEvent {
    private final String name;

    public PlayerJoinEvent(String n){
        super();
        name = n;
    }

    public String getName(){
        return name;
    }
}
