package src.Files;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.swing.SwingWorker;

import src.GUI.HPElevation;
import src.GUI.KMLPanel;

public class GoogleElevation extends SwingWorker<Void, Void> {

    String elevationURL = "https://maps.googleapis.com/maps/api/elevation/json?locations=39.7391536,-104.9847034&key=";

    double latitude = 0.0;

    double longitude = 0.0;

    double elevation = 123.0;

    private KMLPanel kmlPanel;

    public GoogleElevation(KMLPanel kmlPanel, double lat, double longitude) {
        this.kmlPanel = kmlPanel;
        this.latitude = lat;
        this.longitude = longitude;
    }

    protected Void doInBackground() {
        try {
            String eUrl = "https://maps.googleapis.com/maps/api/elevation/json?locations="
                    + latitude + "," + longitude;
            //System.out.println(eUrl);
            URL elevationURL = new URL(eUrl);
            InputStream elevationIS = elevationURL.openStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = elevationIS.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            //System.out.println(result.toString("UTF-8"));
            elevation = getElevation(result.toString());
        } catch (IOException e) {
            DatConLog.Exception(e, "Google get elevation choked");
        }
        return null;
    }

    private double getElevation(String result) {
        double retv = Double.NaN;
        int elIndex = result.indexOf("elevation");
        if (elIndex > 0) {
            int commaIndex = result.indexOf(",", elIndex);
            String elString = result.substring(elIndex + 13, commaIndex);
            retv = Double.parseDouble(elString);
        }
        return retv;
    }

    protected void done() {
        try {
            super.done();
            if (!Double.isNaN(elevation)) {
                kmlPanel.setHPelevation(elevation);
            }
        } catch (Exception e) {
            DatConLog.Exception(e);
        }
    }
}
