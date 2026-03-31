import java.util.*;
import java.util.function.Consumer;

public class SmartHomeLightingAutomation {
    
    enum TriggerType { MOTION, TIME_OF_DAY, VOICE_COMMAND }
    
    static class LightController {
        private Map<TriggerType, Consumer<String>> lightBehaviors = new HashMap<>();
        
        public void addBehavior(TriggerType trigger, Consumer<String> behavior) {
            lightBehaviors.put(trigger, behavior);
        }
        
        public void activateLight(TriggerType trigger, String context) {
            lightBehaviors.getOrDefault(trigger, ctx -> System.out.println("No behavior defined"))
                         .accept(context);
        }
    }
    
    public static void main(String[] args) {
        LightController controller = new LightController();
        
        // Define light behaviors using lambdas
        controller.addBehavior(TriggerType.MOTION, 
            context -> System.out.println("Motion detected in " + context + " - Turning on bright white lights"));
        
        controller.addBehavior(TriggerType.TIME_OF_DAY, 
            context -> System.out.println("Time: " + context + " - Adjusting to warm dim lighting"));
        
        controller.addBehavior(TriggerType.VOICE_COMMAND, 
            context -> System.out.println("Voice command: '" + context + "' - Executing custom lighting scene"));
        
        // Test scenarios
        controller.activateLight(TriggerType.MOTION, "Living Room");
        controller.activateLight(TriggerType.TIME_OF_DAY, "Evening");
        controller.activateLight(TriggerType.VOICE_COMMAND, "Movie Night");
    }
}