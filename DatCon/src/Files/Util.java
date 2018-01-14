/* Util class

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

package src.Files;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Util {

	public static DecimalFormat degreesFormat = new DecimalFormat("###.000",
			new DecimalFormatSymbols(Locale.US));

	public static double distance(double lat1, double lon1, double lat2,
			double lon2) {

		final int R = 6371; // Radius of the earth
		if (lat1 == 0.0 || lon1 == 0.0 || lat2 == 0.0 || lon2 == 0.0)
			return 0.0;
		double latDistance = (lat2 - lat1);
		double lonDistance = (lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos((lat1)) * Math.cos((lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters
		return distance;
	}

	// computes true(not magnetic) bearing
	public static double bearing(double lat1, double lon1, double lat2,
			double lon2) {
		double longDiff = (lon2 - lon1);
		double y = Math.sin(longDiff) * Math.cos(lat2);
		double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
				* Math.cos(lat2) * Math.cos(longDiff);
		// return (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
		return Math.toDegrees(Math.atan2(y, x));
	}

	static long getUnsignedInt(byte[] header, int offset) {
		return (long) (0xff & header[offset])
				+ (256 * (long) (0xff & header[offset + 1]))
				+ (65536 * (long) (0xff & header[offset + 2]))
				+ (65536 * 256 * (long) (0xff & header[offset + 3]));
	}

	static String getString(byte[] header, int offset) {
		int length = 0;
		while (header[offset + length] != 0x00) {
			length++;
		}
		return new String(header, offset, length);
	}

}
