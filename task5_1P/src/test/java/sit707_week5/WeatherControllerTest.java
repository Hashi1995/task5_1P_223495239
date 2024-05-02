package sit707_week5;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class WeatherControllerTest {
	 private static WeatherController wController;
	 private static int noHours;
	 private static double[] hourlyTemperaturesValues;
	 
	 @BeforeClass
	    public static void setUp() {
	        wController = WeatherController.getInstance();
	        noHours = wController.getTotalHours();
	        hourlyTemperaturesValues = new double[noHours];
	        for (int i = 0; i < noHours; i++) {
	            hourlyTemperaturesValues[i] = wController.getTemperatureForHour(i + 1);
	        }
	    }
	 
	 @AfterClass
	    public static void tearDown() {
	        wController.close();
	    }

	@Test
	public void testStudentIdentity() {
		//Arrange
		String studentId = "223495239";
		//Act and Assert
		Assert.assertNotNull("Student ID is null", studentId);
	}

	@Test
	public void testStudentName() {
		//Arrange
		String studentName = "Hashini";
		//Act and Assert
		Assert.assertNotNull("Student name is null", studentName);
	}

	@Test
	public void testTemperatureMin() {
	    // Arrange
	    System.out.println("+++ testTemperatureMin +++");
	    double minTemperature = hourlyTemperaturesValues[0]; 

	    // Act
	    for (int i = 1; i < hourlyTemperaturesValues.length; i++) {
	        double temperatureVal = hourlyTemperaturesValues[i];
	        minTemperature = Math.min(minTemperature, temperatureVal);
	    }

	    // Assert
	    Assert.assertEquals(minTemperature, wController.getTemperatureMinFromCache(), 0.01);
	}

	@Test
	public void testTemperatureMax() {
	    // Arrange
	    System.out.println("+++ testTemperatureMax +++");
	    double maxTemperature = hourlyTemperaturesValues[0]; 

	    // Act
	    for (int i = 1; i < hourlyTemperaturesValues.length; i++) {
	        double temperatureVal = hourlyTemperaturesValues[i];
	        maxTemperature = Math.max(maxTemperature, temperatureVal);
	    }

	    // Assert
	    Assert.assertEquals(maxTemperature, wController.getTemperatureMaxFromCache(), 0.01);
	}

	@Test
	public void testTemperatureAverage() {
	    // Arrange
	    System.out.println("+++ testTemperatureAverage +++");
	    double sumTemp = 0;

	    // Act
	    for (double temperatureVal : hourlyTemperaturesValues) {
	        sumTemp += temperatureVal;
	    }
	    double averageTemp = sumTemp / hourlyTemperaturesValues.length;

	    // Assert
	    Assert.assertEquals(averageTemp, wController.getTemperatureAverageFromCache(), 0.01);
	}

	@Test
	public void testTemperaturePersist() {
	    // Arrange
	    System.out.println("+++ testTemperaturePersist +++");
	    String persistTime = null;

	    // Act
	    persistTime = wController.persistTemperature(hourlyTemperaturesValues.length, hourlyTemperaturesValues[hourlyTemperaturesValues.length - 1]);

	    // Assert
	    Assert.assertNotNull(persistTime);
	}
}
