package src.GUI;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class Formatters {
    static public DefaultFormatterFactory FileNameFormatter() {
        CFilenameFormatter defa = new CFilenameFormatter();
        CFilenameFormatter display = new CFilenameFormatter();
        CFilenameFormatter edit = new CFilenameFormatter();
        return (new DefaultFormatterFactory(defa, display, edit));
    }

    static public DefaultFormatterFactory DoubleFormatter() {
        NumberFormatter defaultDoubleFormatter = new NumberFormatter(
                new DecimalFormat("###.000",
                        new DecimalFormatSymbols(Locale.US)));
        NumberFormatter displayDoubleFormatter = new NumberFormatter(
                new DecimalFormat("###.000",
                        new DecimalFormatSymbols(Locale.US)));
        NumberFormatter editDoubleFormatter = new NumberFormatter(
                new DecimalFormat("###.000",
                        new DecimalFormatSymbols(Locale.US)));
        defaultDoubleFormatter.setValueClass(Double.class);
        displayDoubleFormatter.setValueClass(Double.class);
        editDoubleFormatter.setValueClass(Double.class);

        return new DefaultFormatterFactory(defaultDoubleFormatter,
                displayDoubleFormatter, editDoubleFormatter);
    }

    static public DefaultFormatterFactory LongFormatter() {
        NumberFormatter defaultLongFormatter = new NumberFormatter(
                new DecimalFormat("########",
                        new DecimalFormatSymbols(Locale.US)));
        NumberFormatter displayLongFormatter = new NumberFormatter(
                new DecimalFormat("########",
                        new DecimalFormatSymbols(Locale.US)));
        NumberFormatter editLongFormatter = new NumberFormatter(
                new DecimalFormat("#########",
                        new DecimalFormatSymbols(Locale.US)));
        defaultLongFormatter.setValueClass(Long.class);
        displayLongFormatter.setValueClass(Long.class);
        editLongFormatter.setValueClass(Long.class);

        return new DefaultFormatterFactory(defaultLongFormatter,
                displayLongFormatter, editLongFormatter);
    }

}
