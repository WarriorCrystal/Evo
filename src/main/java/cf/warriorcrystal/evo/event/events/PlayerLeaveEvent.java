package cf.warriorcrystal.evo.event.events;

import cf.warriorcrystal.evo.event.EvoEvent;
public class PlayerLeaveEvent extends EvoEvent {

    private final String name;

    public PlayerLeaveEvent(String n){
        super();
        name = n;
    }

    public String getName(){
        return name;
    }


}
