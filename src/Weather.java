public class Weather {
    double expectedRainfall;
    double actualRainfall;
    double temperaturedeviation;
    int windspeed;

    public Weather(double expectedRainfall, double actualRainfall,
                   double temperaturedeviation, int windspeed){
        this.expectedRainfall = expectedRainfall;
        this.actualRainfall = actualRainfall;
        this.temperaturedeviation = temperaturedeviation;
        this.windspeed = windspeed;
    }
    public double rainfalldeviation(){
        return actualRainfall - expectedRainfall;
    }
}
