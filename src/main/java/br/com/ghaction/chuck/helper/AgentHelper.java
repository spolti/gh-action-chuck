/*
 MIT License

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

package br.com.ghaction.chuck.helper;

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