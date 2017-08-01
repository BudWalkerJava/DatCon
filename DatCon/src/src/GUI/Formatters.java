/* Formatters class

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that redistribution of source code include
the following disclaimer in the documentation and/or other materials provided
with the distribution.

THIS SOFTWARE IS PROVIDED BY ITS CREATOR "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE CREATOR OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

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
                new DecimalFormat("########", new DecimalFormatSymbols(
                        Locale.US)));
        NumberFormatter displayLongFormatter = new NumberFormatter(
                new DecimalFormat("########", new DecimalFormatSymbols(
                        Locale.US)));
        NumberFormatter editLongFormatter = new NumberFormatter(
                new DecimalFormat("#########", new DecimalFormatSymbols(
                        Locale.US)));
        defaultLongFormatter.setValueClass(Long.class);
        displayLongFormatter.setValueClass(Long.class);
        editLongFormatter.setValueClass(Long.class);

        return new DefaultFormatterFactory(defaultLongFormatter,
                displayLongFormatter, editLongFormatter);
    }

}
