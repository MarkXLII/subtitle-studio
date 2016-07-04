/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.swapnilbhoite.projects.subtitlestudio;

/**
 *
 * @author Swapnil Bhoite
 */
public class MyMapping {

    public long start, end, fadeIN, fadeOUT;
    String dialog;

    public MyMapping() {
        fadeIN = fadeOUT = start = end = 0;
        dialog = "";
    }

    public void setTime(String temp) {
        //00:00:03,000 --> 00:00:06,478

        String temp0[], temp1[], temp2[], temp3[], temp4[];
        temp0 = temp.split(" --> ");

        temp1 = temp0[0].split(",");
        start = new Long(temp1[1]);
        temp2 = temp1[0].split(":");
        start = start + 3600000 * (new Long(temp2[0]));
        start = start + 60000 * (new Long(temp2[1]));
        start = start + 1000 * (new Long(temp2[2]));

        temp3 = temp0[1].split(",");
        end = new Long(temp3[1]);
        temp4 = temp3[0].split(":");
        end = end + 3600000 * (new Long(temp4[0]));
        end = end + 60000 * (new Long(temp4[1]));
        end = end + 1000 * (new Long(temp4[2]));
    }

    public void setDialog(String temp) {
        dialog = temp;
    }

    public String getDialog() {
        return dialog;
    }

    public void reset() {
        dialog = "";
        fadeIN = fadeOUT = start = end = 0;
    }

    public void adjustFade(MyMapping dialogs[], String in, String out, int total) {
        long fadeIn = new Long(in);
        long fadeOut = new Long(out);
        for (int i = 0; i < total; i++) {
            dialogs[i].fadeIN = fadeIn;
            dialogs[i].fadeOUT = fadeOut;
            long diff = dialogs[i].end - dialogs[i].start;
            if (diff > 2 * (fadeIn + fadeOut)) {
                dialogs[i].fadeIN = fadeIn;
                dialogs[i].fadeOUT = fadeOut;
            } else {
                diff = diff / 4;
                dialogs[i].fadeIN = diff;
                dialogs[i].fadeOUT = diff;
            }
        }
    }

    @Override
    public String toString() {
        return "Start:" + start + " End:" + end + " " + dialog;
    }
}
