import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InformationManagementSystem {
    
    // Create an empty list to store individual data
    static ArrayList<Individual> individuals = new ArrayList<>();
    
    // Define a function to add individual information
    static void addIndividual() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();
        System.out.print("Enter date of birth: ");
        String dob = sc.nextLine();
        System.out.print("Enter email address: ");
        String email = sc.nextLine();
        Individual individual = new Individual(name, phone, dob, email);
        individuals.add(individual);
        System.out.println("Individual information added successfully.");
    }
    
    // Define a function to retrieve individual information
    private static void retrieveIndividuals() {
        if (individuals.isEmpty()) {
            System.out.println("No individual information available.");
            return;
        }
        System.out.println("Individuals:");
        for (Individual individual : individuals) {
            System.out.println(individual.toString());
        }
    }
    
    // Define a function to delete individual information
    static void deleteIndividual() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the index of the individual to delete: ");
        int index = sc.nextInt();
        if (index >= 0 && index < individuals.size()) {
            individuals.remove(index);
            System.out.println("Individual information deleted successfully.");
        } else {
            System.out.println("Invalid index.");
        }
    }
    
    // Define a function to update individual information
    static void updateIndividual() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the index of the individual to update: ");
        int index = sc.nextInt();
        if (index >= 0 && index < individuals.size()) {
            Individual individual = individuals.get(index);
            System.out.println("Enter new information (leave blank to keep the old value):");
            System.out.print("Name (" + individual.getName() + "): ");
            String name = sc.nextLine().trim();
            if (!name.isEmpty()) {
                individual.setName(name);
            }
            System.out.print("Phone number (" + individual.getPhone() + "): ");
            String phone = sc.nextLine().trim();
            if (!phone.isEmpty()) {
                individual.setPhone(phone);
            }
            System.out.print("Date of birth (" + individual.getDob() + "): ");
            String dob = sc.nextLine().trim();
            if (!dob.isEmpty()) {
                individual.setDob(dob);
            }
            System.out.print("Email address (" + individual.getEmail() + "): ");
            String email = sc.nextLine().trim();
            if (!email.isEmpty()) {
                individual.setEmail(email);
            }
            System.out.println("Individual information updated successfully.");
        } else {
            System.out.println("Invalid index.");
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filename = "individuals.ser";
        // Load individual information from file if it exists
        try (FileInputStream file = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(file)) {
            individuals = (ArrayList<Individual>) in.readObject();
            System.out.println("Individual information loaded successfully.");
        } catch (FileNotFoundException e) {
            // Ignore, file will be created when adding individuals
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading individual information: " + e.getMessage());
        }
        // Main program loop
        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add individual information");
            System.out.println("2. Retrieve individual information");
            System.out.println("3. Delete individual information");
            System.out.println("4. Update individual information");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addIndividual();
                    break;
                case 2:
                    retrieveIndividuals();
                    break;
                case 3:
                    deleteIndividual();
                    break;
                case 4:
                    updateIndividual();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
        // Save individual information to file
        try (FileOutputStream file = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(individuals);
            System.out.println("Individual information saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving individual information: " + e.getMessage());
        }
        System.out.println("Goodbye!");
    }
    
}

class Individual implements Serializable {
    
    private String name;
    private String phone;
    private String dob;
    private String email;
    
    public Individual(String name, String phone, String dob, String email) {
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.email = email;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getDob() {
        return dob;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Date of birth: " + dob + ", Email: " + email;
    }
    
}

