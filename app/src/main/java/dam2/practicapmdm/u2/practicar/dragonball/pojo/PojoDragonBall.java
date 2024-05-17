package dam2.practicapmdm.u2.practicar.dragonball.pojo;

import java.io.Serializable;

public class PojoDragonBall implements Serializable {

    private String id;
    private String name;
    private String ki;
    private String race;

    public PojoDragonBall(String id, String name, String ki, String race) {
        this.id = id;
        this.name = name;
        this.ki = ki;
        this.race = race;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getKi() {
        return ki;
    }

    public String getRace() {
        return race;
    }
}
