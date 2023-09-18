public class Country 
{
	//INSTANCE VARIABLES
    private String country_name;
    private String capital;
    private long population;
    private double GDP;
    private long area;
    private double happiness;

    //CONSTRUCTOR
    public Country(String country_name, String capital, long population, double GDP, long area, double happiness) 
    {
        this.country_name = country_name;
        this.capital = capital;
        this.population = population;
        this.GDP = GDP;
        this.area = area;
        this.happiness = happiness;
    }

    // GETTER METHODS
    public String getCountryName() 
    {
        return country_name;
    }
    public String getCapital() 
    {
        return capital;
    }
    public long getPopulation() 
    {
        return population;
    }
    public double getGDP() 
    {
        return GDP;
    }
    public long getArea() 
    {
        return area;
    }
    public double getHappiness() 
    {
        return happiness;
    }
    //SETTER METHODS
    public void setCountryName(String country_name) 
    {
        this.country_name = country_name;
    }
    public void setCapital(String capital) 
    {
        this.capital = capital;
    }
    public void setPopulation(long population) 
    {
        this.population = population;
    }
    public void setGDP(double GDP) 
    {
        this.GDP = GDP;
    }
    public void setArea(long area) 
    {
        this.area = area;
    }
    public void setHappinessIndex(double happiness) 
    {
        this.happiness = happiness;
    }

  // OUTPUT
  public void printCountry() 
  {
    System.out.printf("\n\nName: %s%n", country_name);
    System.out.printf("Capital: %s%n", capital);
    System.out.printf("GDPPC: %.3f%n", GDP / population);  
    System.out.printf("APC: %.6f%n", (double) area / population); 
    System.out.printf("Happiness: %.3f%n", happiness); 
    System.out.println("------------------------------------");
    System.out.println("____________________________________");
}

}
