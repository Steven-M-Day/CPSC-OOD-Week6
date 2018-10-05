
public class VehicleImplementation implements Vehicle {

	String make;
	String model;
	String packages;
	int rating;
	int ratingCount = 0;
  
	public String getMake() {
		return make;	
	} 
  
	public String getModel() {
		return model;
	}
  
	public String getPackages() {
		return packages;
	}
   
	public int getRating() {
		if (ratingCount == 0) return 0;
		return (rating/ratingCount);
	}
  
 
	public void setMake(String make) {
		this.make = make;
	}
 
	public void setModel(String model) {
		this.model = model;
	} 
  
	public void setPackages(String packages) {
		this.packages = packages;
	} 
  
	public void setRating(int rating) {
		this.rating += rating;	
		ratingCount++;
	}
}
