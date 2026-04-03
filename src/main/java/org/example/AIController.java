package org.example;

import SafetyPackage.SafetyManager;

public class AIController {

    private SafetyManager safetyManager = new SafetyManager();

    public String handleUserInput(String input) {
        SafetyManager.ResponseType type = safetyManager.analyzeInput(input);

        switch (type) {
            case CRITICAL_REDIRECT:
                return getCrisisResponse();

            case SUPPORTIVE_CAUTION:
                return getSupportiveResponse(input);

            case STANDARD_AI:
                AINormalResponse ai = new AINormalResponse();
                return ai.getResponse(input);
        }

        return "Error";
    }

    private String getCrisisResponse() {
        return "I'm really sorry you're feeling this way. You're not alone, and there are people who want to help.\n\n"
                + "Please reach out to the Suicide & Crisis Lifeline: Call or text 988 (U.S.)\n"
                + "Or visit: https://988lifeline.org/\n\n"
                + "If you're in immediate danger, please call emergency services.";
    }

    private String getSupportiveResponse(String input) {
        return "I'm really sorry you're feeling this way. Do you want to talk more about what's been going on? "
                + "You're not alone in this.";
    }


}