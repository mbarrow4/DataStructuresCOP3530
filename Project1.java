//IMPORTS
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Project1 
{
	//OBJECTS & CONSTRUCTORS
	private Country[] countries;
	private String csvFile;

	public static void main(String[] args) 
	{
    Project1 project = new Project1(); 

    /**CODE BLOCK FUNCTIONS
     * 1. Introduction
     * 2. Gets the number of records read from the CSV file,
     * 3. Prints the number of records
     * 4. Runs the menu method*/
    //INTROUDCTION
    System.out.println("ARRAY SEARCHES AND SORTS");
    System.out.println("COP3530 Project 1.");
    System.out.println("Instructor: Xudong Liu.");
    
    
    //READS CSV FILE
    int numRecords = project.readDataFromCSV();
    
    //OUTUPT THE NUMBER OF RECORDS
    System.out.printf("%n%d records contained in: Countries1.csv file.%n", numRecords);
    System.out.println();
    
    //RUNS PROGRAM
    project.runMenu();
    }
	
	   /**CODE BLOCK FUNCTIONS
	    * 1. Prompts the user to enter the name of the file
	    * 2. Reads the CSV file
	    * 3. Skips the first line of the CSV FILE
	    * 4. Runs the menu method
	    * @param Countries1.csv
	    * @return countries
	    * */
		 public int readDataFromCSV() 
		 {
		    // USER PROMPT
		    csvFile = getUserInput("\nPlease enter the name of the CSV file (e.g., Countries1.csv):");
		    
		    //READS CSV FILE
		    String line;
		    int lineCount = 0;
		    countries = new Country[200]; 
		
		    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		    {
		      while ((line = br.readLine()) != null) 
		      {
		    	// This line skips the first line of the file
		        if (lineCount > 0) 
		        { 
		        // Use comma as delimiter
		          String[] data = line.split(","); 
		          if (data.length == 6) 
		          {
		        	  // Create a new Country object and populate it with data from the CSV
		        	  countries[lineCount - 1] = new Country(data[0], data[1], Long.parseLong(data[2]),Double.parseDouble(data[3]), Long.parseLong(data[4]), Double.parseDouble(data[5]));
		          }
		        }//END IF
		        
		        lineCount++;
		        
		      }//END WHILE-LOOP
		      
		      // Resize the array to the actual number of countries
		      countries = Arrays.copyOf(countries, lineCount - 1);
		    }//END TRY
		    catch (IOException e) 
		    {
		      // Handle any IOException that may occur during file reading
		      System.err.println("Error reading CSV file: " + e.getMessage());
		    }
		    // Return the total number of line read from the CSV file
		    return lineCount - 1;
		
		  }//END readDataFromCSV METHOD
	
	 /**RUN MENU METHOD
	  * 1.Creates a scanner objects
	  * 2.Prints the menu
	  * 3.Obtains user's choice
	  * 4.Switch statement to facilitate Menu
	  * 5.Warning if user's choice is not between 1 and 7
	  * @param: none
	  * @return: none*/
	  public void runMenu() 
	  {
	    Scanner scanner = new Scanner(System.in);
	
	    //MENU
	    while (true) {
	      System.out.println("SEARCH AND SORT MENU:");
	      System.out.println("1) Print Country Report.");
	      System.out.println("2) Sort Countries By Name (Alphabetically).");
	      System.out.println("3) Sort Countries By Happiness Index (Ascending).");
	      System.out.println("4) Sort Country By GDP Per Capita (Ascending).");
	      System.out.println("5) Find & Print A Particular Country By Name.");
	      System.out.println("6) Print Kendall’s t Correlation Matrix.");
	      System.out.println("7) Quit");
	      System.out.printf("%nPlease enter your choice (1-7): ");
	     
	      //USER'S CHOICE
	      if (scanner.hasNextInt()) 
	      {
	        int choice = scanner.nextInt();
	        scanner.nextLine();
	
	        if (choice >= 1 && choice <= 7) 
	        {
	
	          switch (choice) 
	          {
	          	//PRINTS COUNTRY REPORT
	            case 1:
	              System.out.println("\nCOUNTRY REPORT:");
		          System.out.println("___________________________________________________________________________");
	              printCountriesReport();
	              break;
	              
	            //SORT COUNTRYBY NAME THEN PRINTS
	            case 2:
	              System.out.println("\n\nCOUNTRIES SORTED BY NAME:");
	              System.out.println("___________________________________________________________________________");
	              insertionSortByName(countries);
	              updateCSVFile(countries, csvFile);
	              printCountriesReport();
	              break;
	              
	            //SORT COUNTRY BY HAPPINESS INDEX
	            case 3:
	              System.out.println("\nCOUNTRIES SORTED BY HAPPINESS:");
	              System.out.println("___________________________________________________________________________");
	              selectionSortByHappinessIndex(countries);
	              updateCSVFile(countries, csvFile);
	              printCountriesReport();
	              break;
	            
	            //SORT COUNTRY BY GDP
	            case 4:
		              System.out.println("\nCOUNTRIES SORTED BY GDP PER CAPITA:");
		              System.out.println("___________________________________________________________________________");
		              bubbleSortByGDPPC(countries);
		              updateCSVFile(countries, csvFile);
		              printCountriesReport();
		              break;
	              
	            //SEARCH COUNTRY BY NAME  
	            case 5:
	            	System.out.println("\nCOUNTRY SEARCHED BY NAME:");
		            System.out.println("___________________________________________________________________________");
	                findCountryByName(scanner);
	                break;
	              
	            //KENDALL'S CORRELATION MATRIX  
	            case 6:
	              System.out.println("\nKENDALL'S CORRELATION MATRIX:");
	              System.out.println("---------------------------------------------");
	              System.out.println("|       | GDPPC | AreaPC |");
	              System.out.println("---------------------------------------------");
	
	              String[] variableArray = { "Happiness Index", "GDPPC", "AreaPC" };
	
	              for (int count = 0; count < variableArray.length; count++) 
	              {
	                System.out.printf("| %s |", variableArray[count]);
	
	                for (int count1 = 0; count1 < variableArray.length; count1++) 
	                {
	                  double correlationCoefficient = calculateKendallTau(variableArray[count], variableArray[count1], countries);
	                  System.out.printf(" %.3f |", correlationCoefficient);
	                }
	
	                System.out.println();
	                System.out.println("---------------------------------------------");
	             
	              }
	              break;
	            
	            //TERMINATION
	            case 7:
	              System.out.println("PROGRAM TERMINATED.");
	              scanner.close();
	              System.exit(0);
	              break;
	              
	            //INVALID CHOICE
	            default:
	              System.out.println("Invalid choice. Please select a valid option.");
	          }
	
	        } else {
	          System.out.println("Invalid choice! Enter 1-7.");
	        }
	      } else {
	        System.out.println("Invalid input! Enter a number between 1 and 7.");
	        scanner.next(); 
	      }
	    } // End of while loop
	  } // End of menu method
	
	  
	  
	  
	  
	  /**SORT BY NAME METHOD
		 * Sort a country array by name using
		 * Insertion alphabetically.
		 * Only sorts them in ascending
		 * A to Z.
		 * 
		 * @param Country[]
		 * @return Country[]
		 */
	  public static void insertionSortByName(Country[] countries) 
	  {
		// Get the number of countries in the array
	    int size = countries.length; 
	    for (int count = 1; count < size; count++) 
	    {
	      // Select the current country to be compared and inserted
	      Country key = countries[count]; 
	      int innerCounter = count - 1;
	      // Move elements of countries[0..i-1], that are greater than key.getName(),
	      // to one position ahead of their current position
	      while (innerCounter  >= 0 && countries[innerCounter ].getCountryName().compareTo(key.getCountryName()) > 0) 
	      {
	        countries[innerCounter  + 1] = countries[innerCounter ];
	        innerCounter  = innerCounter  - 1;
	      }
	      // Place the selected country (key) at its correct position
	      countries[innerCounter  + 1] = key;
	    }
	  }
	
	  /**SELECTION SORT BY HAPPINESS METHOD
		  * 1.Initializes the size of the array
		  * 2.Compares happiness by each country
		  * 
		  * @param: Country[] countries
		  * @return: void*/
	  public static void selectionSortByHappinessIndex(Country[] countries) 
	  {
		// Get the number of countries in the array
	    int size = countries.length; 
	    for (int count = 0; count < size - 1; count++) 
	    {
	      
	      int minIndex = count; 
	      for (int count1 = count + 1; count1 < size; count1++) 
	      {
	        // Check if the happiness index of the current country is less than the minimum
	        if (countries[count1].getHappiness() < countries[minIndex].getHappiness()) 
	        {
	          minIndex = count1; // Update the minimum index if a smaller happiness index is found
	        }
	      }
	      // Swap the country at the minimum index with the current country (if needed)
	      Country temp = countries[minIndex];
	      countries[minIndex] = countries[count];
	      countries[count] = temp;
	    }
	  }
	  /**SELECTION SORT BY GDP
		  * 1.Initializes the size of the array
		  * 2.Compares happiness by each country
		  * 3.Swaps elements
		  * @param: Country[] countries
		  * @return: void*/
	  static void bubbleSortByGDPPC(Country[] countries) 
	  {
		// Get the number of countries in the array
	    int size = countries.length; 
	    boolean swapped;
	    
	    // Traverse through all elements in the array
	    for (int count = 0; count < size - 1; count++) 
	    {
	      swapped = false; 
	
	      // Traverse the array from 0 to n-i-1
	      for (int innerCount = 0; innerCount < size - count - 1; innerCount++) 
	      {
	        // Compare GDP per capita of current and next countries
	        if (countries[innerCount].getGDP() / countries[innerCount].getPopulation() > countries[innerCount + 1].getGDP()
	            / countries[innerCount + 1].getPopulation()) 
	        {
	
	          // Swap the countries if the condition is met
	          Country temp = countries[innerCount];
	          countries[innerCount] = countries[innerCount + 1];
	          countries[innerCount + 1] = temp;
	          swapped = true; // Set swapped to true if a swap occurred
	        }
	      }
	      // If no two elements were swapped in the inner loop, the array is already
	      // sorted
	      if (!swapped) {
	        break; // Exit the loop early to optimize
	      }
	    }
	  }
	
	  
	  /**UPDATE CSV FILE METHOD
		  * 1.Initializes the size of the array
		  * 2.Compares happiness by each country
		  * 
		  * @param: Country[] countries, fileName
		  * @return: void*/ 
	  public static void updateCSVFile(Country[] countries, String fileName) 
	  {
	    try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) 
	    {
	      // Write the CSV header
	      writer.println("Country Name,Capitol City,Population,GDP,Area,Happiness Index");
	
	      // Write each country's data
	      for (Country country : countries) 
	      {
	        // Format and write each field of the country
	        writer.printf("%s,%s,%d,%.2f,%d,%.3f%n",
	            country.getCountryName(),
	            country.getCapital(),
	            country.getPopulation(),
	            country.getGDP(),
	            country.getArea(),
	            country.getHappiness());
	      }
	    } catch (IOException e) {
	      e.printStackTrace(); // Handle any IOException that may occur during file writing
	    }
	  }
	
	  /**PRINT COUNTRIES REPORT
		 * Print a report of what is currently
		 * stored in a country array.
		 * 
		 * @param Country[]
		 * @return void
		 */ 
	  private void printCountriesReport() 
	  {
		    System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "Name", "Capital", "GDPPC", "APC", "HappinessIndex");
		    System.out.println("---------------------------------------------------------------------------");
		    for (Country country : countries) 
		    {
		        System.out.printf("%-15s%-15s%-15.3f%-15.6f%-15.3f%n", country.getCountryName(), country.getCapital(),country.getGDP() / country.getPopulation(), (double) country.getArea() / country.getPopulation(), country.getHappiness());
		    }
		    System.out.println("---------------------------------------------------------------------------");
		    System.out.println("___________________________________________________________________________");
		    System.out.println();
		}
	
	
	  /**FIND COUNTRY BY NAME
		  * 1.Promot user to type the name of country
		  * 2.Searches for the country
		  * 
		  * @param: input
		  * @return: void*/
	  private void findCountryByName(Scanner scanner) 
	  {
	    System.out.print("Enter the name of the country to search for: ");
	    String searchName = scanner.nextLine();
	
	    for (Country country : countries) 
	    {
	      // Check if the current country's name matches the searchName (case-insensitive)
	      if (country.getCountryName().equalsIgnoreCase(searchName)) 
	      {
	        country.printCountry(); // Print the details of the found country
	        return; // Exit the method after finding and printing the country
	      }
	    }
	
	    System.out.println("Country not found."); // Print a message if the country is not found
	  }
	  /**GET USER INPNUT
		  * 1.User prompt
		  * 2.Reads input
		  * 
		  * @param: String prompt
		  * @return: input*/
	  // Get User Input with Prompt
	  private String getUserInput(String prompt) 
	  {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print(prompt);
	    return scanner.nextLine(); // Read and return user input as a string
	  }
	  /**CALCULATE KENDALL TAU
		  * 1.Get variables
		  * 2.Calls functions
		  * 
		  * @param: variable1, variable2, and Country[]
		  * @return: Kendall Tau*/
	  // Calculate Kendall's Tau Correlation
	  public static double calculateKendallTau(String variable1, String variable2, Country[] countries) 
	  {
	    int concordant = 0; // Count of concordant pairs
	    int discordant = 0; // Count of discordant pairs
	
	    for (int count = 0; count < countries.length - 1; count++) 
	    {
	      for (int innerCount = count + 1; innerCount < countries.length; innerCount++) 
	      {
	        double value1 = getVariableValue(variable1, countries[count]);
	        double value2 = getVariableValue(variable1, countries[innerCount]);
	        double value3 = getVariableValue(variable2, countries[count]);
	        double value4 = getVariableValue(variable2, countries[innerCount]);
	
	        // Check for concordant and discordant pairs
	        if ((value1 < value2 && value3 < value4) || (value1 > value2 && value3 > value4)) 
	        {
	          concordant++; // If the pair is concordant
	        } else {
	          discordant++; // If the pair is discordant
	        }
	      }
	    }
	
	    int size = countries.length;
	    // Calculate Kendall's Tau using the formula
	    double kendallTau = (concordant - discordant) / Math.sqrt((concordant + discordant) * (size * (size - 1) / 2));
	    return kendallTau; // Return the calculated Kendall's Tau value
	  }
	
	  // Get the Value of a Variable for a Country
	  private static double getVariableValue(String variable, Country country) 
	  {
	    switch (variable) 
	    {
	    	// Return the Happiness Index value for the country
	      case "Happiness Index":
	        return country.getHappiness(); 
	      case "GDPPC":
	    	// Calculate and return GDP per capita
	        return country.getGDP() / country.getPopulation(); 
	      case "AreaPC":
	    	// Calculate and return Area per capita
	        return (double) country.getArea() / country.getPopulation(); 
	      default:
	    	// Throw an exception for invalid variable
	        throw new IllegalArgumentException("Invalid variable: " + variable); 
	    }
	  }
	
	}//END OF CLASS
