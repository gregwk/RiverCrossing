package river;

import river.GameEngine.Location;

/*
 * (1) Introduce a field named sound of type String into this super class.
 *     Change the getSound method so it simply returns the sound field. Set
 *     sound values in subclass constructors as appropriate.
 *
 * (2) Create a constructor in this class that sets the name and sound to the
 *     empty string and sets the location to Location.START. Modify the
 *     constructors of the subclasses to call the superclass constructor -- by
 *     invoking super() -- and then override values as appropriate.
 *
 * (3) There is no reason for the name of the object to change, so remove the
 *     method setName.
 */

public class GameObject {

    protected String name;
    protected Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location loc) {
        this.location = loc;
    }

    public String getSound() {
        switch (name) {
        case "Wolf":
            return "Howl";
        case "Goose":
            return "Honk";
        default:
            return "";
        }
    }

}
