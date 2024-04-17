package com.geoshot.geoshotweb.classes;

public class CalculateAccuracy {

    public static Double getAccuracy(String correctValue, String userAnswer) {

        String[] correctValues = correctValue.split(",");
        String[] userAnswers   = userAnswer.split(",");

        Double correctLat  = Double.parseDouble(correctValues[0]);
        Double correctLong = Double.parseDouble(correctValues[1]);
        Double userLat     = Double.parseDouble(userAnswers[0]);
        Double userLong    = Double.parseDouble(userAnswers[1]);

        // Faz as contas!!
        //
        //

        return 50.0;

    }

}
