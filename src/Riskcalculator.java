public class Riskcalculator {
    public double calculateRisk(Weather weather, Crop crop){
        double rainfalldev= weather.rainfalldeviation();

        double riskScore=0;

        riskScore+= Math.abs(rainfalldev)*0.5;
        riskScore+= weather.temperaturedeviation*10;
        riskScore+=weather.windspeed*5;
        riskScore-=crop.resistancelevel*3;

        return riskScore;
    }
    public void showRisklevel(double Score) {
        if (Score < 30) {
            System.out.println("Risk Level: Low");
            System.out.println("Advice: Crop conditions are safe. Continue normal Farming Practices!");
        } else if (Score < 60) {
            System.out.println("Risk Level: Moderate");
            System.out.println("Advice: Consider Irrigation Management System and Crop health regularly!");
        } else {
            System.out.println("Risk Level: High");
            System.out.println("Recommendation: Consider a local Agricultural Advisories for best practices!");
        }
    }
}
