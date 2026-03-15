public class Crop {
    String cropName;
    int resistancelevel;

    public Crop(String cropName, int resistancelevel){
        this.cropName = cropName;
        this.resistancelevel = resistancelevel;
    }

    public void displayCrop() {
        System.out.println("Enter the Crop Name:" + cropName);
        System.out.println("Resistance Level: " + resistancelevel);
    }
    }
