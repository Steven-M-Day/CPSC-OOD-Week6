
import java.lang.reflect.*;
import java.util.*;

public class VehicleTest {

	HashMap<String, Vehicle> vehicleDB = new HashMap<String, Vehicle>();
 	
	public static void main(String[] args) {
		VehicleTest test = new VehicleTest();
		test.test();
	}
 
	public VehicleTest() {
		initializeDatabase();
	}

	public void test() {
		Vehicle joe = getVehicleFromDatabase("Chevrolet"); 
		Vehicle kelly = getVehicleFromDatabase("Ford");
		
		Vehicle accessProxy = getAccessProxy(joe);
		System.out.println("Make is " + accessProxy.getMake());
		System.out.println("Model is " + accessProxy.getModel());
		System.out.println("Package included: " + accessProxy.getPackages());
		accessProxy.setPackages("Off Road");
		System.out.println("Packages are set from access proxy");
		try {
			accessProxy.setRating(10);
		} catch (Exception e) {
			System.out.println("Cannot set rating from access proxy");
		}
		System.out.println("Rating is " + accessProxy.getRating());
		
		System.out.println("");
		
		Vehicle noAccessProxy = getNoAccessProxy(kelly);
		System.out.println("Make is " + noAccessProxy.getMake());
		System.out.println("Model is " + noAccessProxy.getModel());
		System.out.println("Packages included: " + noAccessProxy.getPackages());
		try {
			noAccessProxy.setPackages("Sport");
		} catch (Exception e) {
			System.out.println("Cannot set packages from no access proxy");
		}
		noAccessProxy.setRating(3);
		System.out.println("Rating set from no access proxy");
		System.out.println("Rating is " + noAccessProxy.getRating());
	}

	Vehicle getAccessProxy(Vehicle vehicle) {
 		
        return (Vehicle) Proxy.newProxyInstance( 
            	vehicle.getClass().getClassLoader(),
            	vehicle.getClass().getInterfaces(),
                new AccessInvocation(vehicle));
	}

	Vehicle getNoAccessProxy(Vehicle vehicle) {
		
        return (Vehicle) Proxy.newProxyInstance(
            	vehicle.getClass().getClassLoader(),
            	vehicle.getClass().getInterfaces(),
                new NoAccessInvocation(vehicle));
	}

	Vehicle getVehicleFromDatabase(String name) {
		return (Vehicle)vehicleDB.get(name);
	}

	void initializeDatabase() {
		Vehicle one = new VehicleImplementation();
		one.setMake("Chevrolet");
		one.setModel("Silverado");
		one.setPackages("Off Road");
		one.setRating(14);
		vehicleDB.put(one.getMake(), one);

		Vehicle two = new VehicleImplementation();
		two.setMake("Ford");
		two.setModel("Mustang");
		two.setPackages("Sport");
		two.setRating(6);
		vehicleDB.put(two.getMake(), two);
	}
}
