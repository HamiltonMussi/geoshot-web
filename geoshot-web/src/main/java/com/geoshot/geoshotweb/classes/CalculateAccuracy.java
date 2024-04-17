package com.geoshot.geoshotweb.classes;

import org.apache.commons.math3.util.FastMath;

public class CalculateAccuracy {

    public static final double EARTH_RADIUS = 6371.0;

    public static Double getAccuracy(String correctValue, String userAnswer) {

        String[] correctValues = correctValue.split(",");
        String[] userAnswers   = userAnswer.split(",");

        double correctLat  = Double.parseDouble(correctValues[0]);
        double correctLong = Double.parseDouble(correctValues[1]);
        double userLat     = Double.parseDouble(userAnswers[0]);
        double userLong    = Double.parseDouble(userAnswers[1]);

        double radLat1 = Math.toRadians(correctLat);
        double radLon1 = Math.toRadians(correctLong);
        double radLat2 = Math.toRadians(userLat);
        double radLon2 = Math.toRadians(userLong);

        double dLat = radLat2 - radLat1;
        double dLon = radLon2 - radLon1;

        double a = FastMath.pow(FastMath.sin(dLat / 2), 2) +
                FastMath.cos(radLat1) * FastMath.cos(radLat2) *
                        FastMath.pow(FastMath.sin(dLon / 2), 2);
        double c = 2 * FastMath.atan2(FastMath.sqrt(a), FastMath.sqrt(1 - a));

        double distance = EARTH_RADIUS * c;

        if(distance < 0.05) {
            return 100.;
        } else {
            return 100.*(3.0598e-7 + FastMath.exp(-distance/200)*1.00025033731);
        }

    }

}
