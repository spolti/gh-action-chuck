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

package br.com.spolti.chuck.fact.pojo;

import br.com.spolti.chuck.fact.helper.AgentHelper;

/**
 * Created by fspolti on 6/8/17.
 */
public class SpeechCustomResponse {

    private String speech;
    private String displayText;
    private String data;
    private String[] contextOut;
    private String source;
    private FollowUpEvent followupEvent;
    private String fullResponse = AgentHelper.getRandomBeforeMessage() + " %s " + AgentHelper.getRandomAFterMessage();

    public SpeechCustomResponse(String speech) {

        this.speech = String.format(fullResponse, !speech.endsWith(".") ? speech + ". " : speech + "");
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public String getDisplayText() {
        return speech;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String[] getContextOut() {
        return contextOut;
    }

    public void setContextOut(String[] contextOut) {
        this.contextOut = contextOut;
    }

    public FollowUpEvent getFollowupEvent() {
        return followupEvent;
    }

    public void setFollowupEvent(FollowUpEvent followupEvent) {
        this.followupEvent = followupEvent;
    }

    public String getSource() {
        return "apiai-chuck-norris-fact-webhook";
    }
}