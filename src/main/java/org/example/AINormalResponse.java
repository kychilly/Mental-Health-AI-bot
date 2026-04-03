package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;

import java.io.IOException;

public class AINormalResponse {

    private static final String GROQ_API_KEY = System.getenv("GROQ_API_KEY");

    private static final MediaType JSON = MediaType.get("application/json");

    private static OkHttpClient client = new OkHttpClient();

    public static String getResponse(String prompt) {

        System.out.println("KEY: " + GROQ_API_KEY); // debug

        JsonArray messages = new JsonArray();

        JsonObject systemMessage = new JsonObject();
        systemMessage.addProperty("role", "system");
        systemMessage.addProperty("content",
                "You are a supportive mental health assistant. " +
                        "You are NOT a therapist. " +
                        "You must be empathetic, calm, and never give medical advice. " +
                        "If the user expresses distress, encourage seeking help."
        );

        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", "user");
        userMessage.addProperty("content", prompt);

        messages.add(systemMessage);
        messages.add(userMessage);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", "llama-3.1-8b-instant");
        requestBody.add("messages", messages);

        RequestBody body = RequestBody.create(requestBody.toString(), JSON);

        Request request = new Request.Builder()
                .url("https://api.groq.com/openai/v1/chat/completions")
                .addHeader("Authorization", "Bearer " + GROQ_API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                String errorBody = response.body() != null ? response.body().string() : "No error body";
                throw new IOException("Groq API Error: " + response.code() + " " + errorBody);
            }

            if (response.body() == null) {
                return "No response from AI.";
            }

            String responseBody = response.body().string();

            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();

            return json.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, something went wrong.";
        }
    }
}