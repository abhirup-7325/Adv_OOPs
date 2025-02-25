package Assignment_2;

/*
 * A Planet Explorer routinely travels across the planets in the Solar System to discover life form,
minerals available, etc. However, the method of exploring is different on each planet, due to the
difference in atmosphere and surface composition. Every explorer should have an explore method
that is defined based on the type of the explorer and the planet where (s)he is exploring. Consider
three planets-Mars, Venus, and Saturn.
Implement it using interfaces, abstract class, inheritance.
 */


interface Explorer {
    void explore(Planet planet);
}

abstract class Planet {
    String name;

    Planet(String name) {
        this.name = name;
    }

    abstract void accept(Explorer explorer);
}

class Mars extends Planet {
    Mars() {
        super("Mars");
    }

    @Override
    void accept(Explorer explorer) {
        explorer.explore(this);
    }
}

class Venus extends Planet {
    Venus() {
        super("Venus");
    }

    @Override
    void accept(Explorer explorer) {
        explorer.explore(this);
    }
}

class Saturn extends Planet {
    Saturn() {
        super("Saturn");
    }

    @Override
    void accept(Explorer explorer) {
        explorer.explore(this);
    }
}

class RobotExplorer implements Explorer {
    @Override
    public void explore(Planet planet) {
        System.out.println("Robot Explorer is analyzing surface composition on " + planet.name);
    }
}

class HumanExplorer implements Explorer {
    @Override
    public void explore(Planet planet) {
        System.out.println("Human Explorer is collecting rock samples from " + planet.name);
    }
}

class DroneExplorer implements Explorer {
    @Override
    public void explore(Planet planet) {
        System.out.println("Drone Explorer is scanning the atmosphere of " + planet.name);
    }
}

public class p9 {
    public static void main(String[] args) {
        Planet mars = new Mars();
        Planet venus = new Venus();
        Planet saturn = new Saturn();

        Explorer robot = new RobotExplorer();
        Explorer human = new HumanExplorer();
        Explorer drone = new DroneExplorer();

        mars.accept(robot);
        mars.accept(human);
        mars.accept(drone);

        venus.accept(robot);
        venus.accept(human);
        venus.accept(drone);

        saturn.accept(robot);
        saturn.accept(human);
        saturn.accept(drone);
    }
}
