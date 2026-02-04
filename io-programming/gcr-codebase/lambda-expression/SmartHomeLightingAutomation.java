import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class SmartLight {
    private String name;

    SmartLight(String name) {
        this.name = name;
    }

    void activate(String pattern) {
        System.out.println(name + " Light -> " + pattern);
    }
}

public class SmartHomeLightingAutomation {
    public static void main(String[] args) {

        SmartLight livingRoom = new SmartLight("Living Room");

        Map<String, Consumer<SmartLight>> triggers = new HashMap<>();

        triggers.put("MOTION", light -> light.activate("Bright White"));
        triggers.put("NIGHT", light -> light.activate("Dim Warm"));
        triggers.put("VOICE_ON", light -> light.activate("Soft Yellow"));
        triggers.put("VOICE_OFF", light -> light.activate("Turned Off"));

        triggers.get("MOTION").accept(livingRoom);
        triggers.get("NIGHT").accept(livingRoom);
        triggers.get("VOICE_ON").accept(livingRoom);
        triggers.get("VOICE_OFF").accept(livingRoom);
    }
}
