package SafetyPackage;

public class SafetyManager {
    // Keywords for immediate danger
    private static final String[] RED_FLAGS = {
            "kill myself", "suicide", "end my life", "goodbye world",
            "i want to die", "i'm going to kill myself", "no reason to live"
    };

    private static final String[] YELLOW_FLAGS = {
            "hurt myself", "self harm", "hopeless", "can't go on",
            "i feel worthless", "nothing matters", "i'm tired of everything"
    };
    public ResponseType analyzeInput(String input) {
        String lowerInput = input.toLowerCase();

        int score = 0;

        for (String flag : RED_FLAGS) {
            if (lowerInput.contains(flag)) score += 10;
        }

        for (String flag : YELLOW_FLAGS) {
            if (lowerInput.contains(flag)) score += 3;
        }

        if (score >= 10) return ResponseType.CRITICAL_REDIRECT;
        if (score >= 3) return ResponseType.SUPPORTIVE_CAUTION;

        return ResponseType.STANDARD_AI;
    }

    public enum ResponseType {
        STANDARD_AI,        // Everything is fine, let the AI talk
        SUPPORTIVE_CAUTION, // User is sad/distressed; AI gives a nudge to help
        CRITICAL_REDIRECT   // Serious danger; Hard-stop the AI and show resources
    }

}