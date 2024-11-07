import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


    class Car{
        private String CarId;
        private String model;
        private String Brand;
        private double BasedPricePerDay;
        private boolean isAvailable;

        public Car(String carId, String model, String brand, double basedPricePerDay) {
            this.CarId = carId;
            this.model = model;
            this.Brand = brand;
            this.BasedPricePerDay = basedPricePerDay;
            this.isAvailable = true;
        }

        public String getCarId() {
            return CarId;
        }

        public void setCarId(String carId) {
            CarId = carId;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getBrand() {
            return Brand;
        }

        public void setBrand(String brand) {
            Brand = brand;
        }



        public double calculatePrice(int rentalDays){
            return BasedPricePerDay * rentalDays;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

       public void rent(){
            isAvailable =false;
       }
       public void returnCar(){
            isAvailable = true;
       }
    }
    class Customer{
        private String customerId;
        private String customerName;

        public Customer(String customerId, String customerName) {
            this.customerId = customerId;
            this.customerName = customerName;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }
    }
    class Rental{
        private Car car;
        private Customer customer;
        private int days;

        public Rental(Car car,Customer customer,int days){
            this.car = car;
            this.customer =customer;
            this.days = days;
        }
        public Car getCar() {
            return car;
        }
        public Customer getCustomer() {
            return customer;
        }
        public int getDays() {
            return days;
        }
    }
    class CarRentalSystum{
        private List<Car> cars;
        private List<Customer> customers;
        private List<Rental> rentals;
        public CarRentalSystum(){
            cars = new ArrayList<>();
            customers = new ArrayList<>();
            rentals = new ArrayList<>();
        }
        public void addCar(Car car){
            cars.add(car);
        }
        public void addCustomer(Customer customer){
            customers.add(customer);
        }
        public void rentalCar(Car car, Customer customer, int days){
            if(car.isAvailable()){
                car.rent();
                rentals.add(new Rental(car,customer,days));
            }else{
                System.out.println("Car is not avilable for rent");
            }
        }
        public void returnCar(Car car){
            car.returnCar();
            Rental rentalToRemove = null;
            for (Rental rental : rentals){
                if(rental.getCar() == car){
                    rentalToRemove = rental;
                    break;
                }
            }
            if (rentalToRemove!=null){
                rentals.remove(rentalToRemove);
                System.out.println("Car is returned successfully.");
            }else{
                System.out.println("car was not rented");
            }

        }
        public void menu(){
            Scanner sc =new Scanner(System.in);
            while(true) {
                System.out.println("==== Car Rental System");
                System.out.println("1. Rent A Car");
                System.out.println("2. Return A Car");
                System.out.println("3. Exit");
                System.out.print("Enter your Choice");
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("\n== Rent a Car\n");
                    System.out.print("Enter your name");
                    String customerName = sc.nextLine();
                    System.out.println("\nAvailable Car");
                    for(Car car :cars){
                        if(car.isAvailable()){
                            System.out.println(car.getCarId() + "-" + car.getBrand() + " " + car.getModel());
                        }
                    }
                    System.out.println("Enter the car ID you want to rent");
                    String carID =sc.nextLine();
                    System.out.println("Enter the no. of days for rent ");
                    int rentalDays =sc.nextInt();
                    sc.nextLine();
                    Customer newCustomer = new Customer("CUS " + (customers.size() + 1), customerName);
                    addCustomer(newCustomer);
                    Car selectedCar = null;
                    for(Car car : cars){
                        if(car.getCarId().equals(carID) && car.isAvailable()){
                            selectedCar =car;
                            break;
                        }
                    }
                    if(selectedCar != null) {
                        double totalPrice = selectedCar.calculatePrice(rentalDays);
                        System.out.println("== Rental Information ==");
                        System.out.println("Customer ID: " + newCustomer.getCustomerId());
                        System.out.println("Customer Name" + newCustomer.getCustomerName());
                        System.out.println("Cars" + selectedCar.getBrand() + " " + selectedCar.getModel());
                        System.out.println("Rental Days " + rentalDays);
                        System.out.printf("Total Price $%.2f%n", totalPrice);
                        System.out.println("\nConfirm rental (Y/N) :");
                        String confirm = sc.nextLine();
                        if (confirm.equalsIgnoreCase("Y")) {
                            rentalCar(selectedCar, newCustomer, rentalDays);
                            System.out.println("\ncar rent successfully: ");
                        } else {
                            System.out.println("\n Rental canceled.");
                        }
                    }else{
                        System.out.println("Invalid car selction or car not available for rent. ");
                        }
                    } else if (choice==2) {
                    System.out.println("== Return a Car ==\n");
                    System.out.println("Enter the car ID you want to return:");
                    String carID = sc.nextLine();
                    Car carToReturn = null;
                    for (Car car : cars) {
                        if (car.getCarId().equals(carID) && !car.isAvailable()) {
                            carToReturn = car;
                            break;
                        }
                    }
                    if (carToReturn != null) {
                        Customer customer = null;
                        for (Rental rental : rentals) {
                            if (rental.getCar() == carToReturn) {
                                customer = rental.getCustomer();
                                break;
                            }
                        }
                        if (customer != null) {
                            returnCar(carToReturn);
                            System.out.println("car return successfully by " + customer.getCustomerName());
                        } else {
                            System.out.println("car was not rented or rental details is missing ");
                        }
                    } else {
                        System.out.println("Invalid car ID or car is not rented");
                    }
                }else if(choice == 3){
                        break;
                    }else{
                    System.out.println("Invalid choice. Please enter a valid option");
                }
            }
            System.out.println("\nThankyou for choosing Rental Car System!");
        }
    }
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        CarRentalSystum rentalSystum =new CarRentalSystum();
        Car car1 =new Car("C001","Toyoto", "Camry", 60.0);
        Car car2 =new Car("C002","Honda", "Accord", 70.0);
        Car car3 =new Car("C003","Thar", "Maruti", 40.0);
        rentalSystum.addCar(car1);
        rentalSystum.addCar(car2);
        rentalSystum.addCar(car3);
        rentalSystum.menu();
    }
}