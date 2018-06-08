package br.com.spolti.chuck.fact.helper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AgentHelper {

    public static List<String> before = Arrays.asList(
            "Hi, here is what I've got.",
            "Hello there, here is a joke, just for you.",
            "Did you know that.",
            "Here we go.",
            "Let's do it.",
            "Alright, listen to this one.");

    public static List<String> after = Arrays.asList(
            "Do you want to hear another joke?",
            "Can I tell you another joke?",
            "Did you like it?",
            "That joke was awesome, another one?",
            "That one was really funny, let's hear another one?",
            "What about a new joke?",
            "Just say Goodbye if you don't want to talk with me anymore.");

    public static String getRandomBeforeMessage() {
        return before.get(ThreadLocalRandom.current().nextInt(before.size()));
    }

    public static String getRandomAFterMessage() {
        return after.get(ThreadLocalRandom.current().nextInt(after.size()));
    }
}