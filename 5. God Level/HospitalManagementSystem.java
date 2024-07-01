import java.io.*;
import java.util.*;

public class HospitalManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<User> users = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();
    private static final String USERS_FILE = "users.txt";
    private static final String PATIENTS_FILE = "patients.txt";
    private static final String DOCTORS_FILE = "doctors.txt";
    private static final String APPOINTMENTS_FILE = "appointments.txt";

    public static void main(String[] args) {
        loadUsers();
        loadPatients();
        loadDoctors();
        loadAppointments();

        while (true) {
            System.out.println("Hospital Management System");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    saveUsers();
                    savePatients();
                    saveDoctors();
                    saveAppointments();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = authenticate(username, password);
        if (user != null) {
            if (user instanceof Admin) {
                adminMenu((Admin) user);
            } else if (user instanceof Doctor) {
                doctorMenu((Doctor) user);
            } else if (user instanceof Patient) {
                patientMenu((Patient) user);
            }
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void register() {
        System.out.println("Register as:");
        System.out.println("1. Admin");
        System.out.println("2. Doctor");
        System.out.println("3. Patient");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        switch (choice) {
            case 1:
                users.add(new Admin(username, password));
                break;
            case 2:
                System.out.println("Enter doctor's specialization:");
                String specialization = scanner.nextLine();
                Doctor doctor = new Doctor(username, password, specialization);
                users.add(doctor);
                doctors.add(doctor);
                break;
            case 3:
                Patient patient = new Patient(username, password);
                users.add(patient);
                patients.add(patient);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        System.out.println("Registration successful.");
    }

    private static User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void adminMenu(Admin admin) {
        while (true) {
            System.out.println("Admin Menu");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Generate Reports");
            System.out.println("5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    managePatients();
                    break;
                case 2:
                    manageDoctors();
                    break;
                case 3:
                    manageAppointments();
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void doctorMenu(Doctor doctor) {
        while (true) {
            System.out.println("Doctor Menu");
            System.out.println("1. View Appointments");
            System.out.println("2. Manage Patients");
            System.out.println("3. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAppointments(doctor);
                    break;
                case 2:
                    managePatients();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void patientMenu(Patient patient) {
        while (true) {
            System.out.println("Patient Menu");
            System.out.println("1. View Doctors");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. View Appointments");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewDoctors();
                    break;
                case 2:
                    scheduleAppointment(patient);
                    break;
                case 3:
                    viewAppointments(patient);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void managePatients() {
        while (true) {
            System.out.println("Manage Patients");
            System.out.println("1. View Patients");
            System.out.println("2. Add Patient");
            System.out.println("3. Remove Patient");
            System.out.println("4. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewPatients();
                    break;
                case 2:
                    addPatient();
                    break;
                case 3:
                    removePatient();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageDoctors() {
        while (true) {
            System.out.println("Manage Doctors");
            System.out.println("1. View Doctors");
            System.out.println("2. Add Doctor");
            System.out.println("3. Remove Doctor");
            System.out.println("4. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewDoctors();
                    break;
                case 2:
                    addDoctor();
                    break;
                case 3:
                    removeDoctor();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageAppointments() {
        while (true) {
            System.out.println("Manage Appointments");
            System.out.println("1. View Appointments");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAppointments();
                    break;
                case 2:
                    scheduleAppointment();
                    break;
                case 3:
                    cancelAppointment();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewPatients() {
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private static void addPatient() {
        System.out.println("Enter patient username:");
        String username = scanner.nextLine();
        System.out.println("Enter patient password:");
        String password = scanner.nextLine();
        Patient patient = new Patient(username, password);
        patients.add(patient);
        users.add(patient);
        System.out.println("Patient added successfully.");
    }

    private static void removePatient() {
        System.out.println("Enter patient username to remove:");
        String username = scanner.nextLine();
        Patient patientToRemove = null;
        for (Patient patient : patients) {
            if (patient.getUsername().equals(username)) {
                patientToRemove = patient;
                break;
            }
        }
        if (patientToRemove != null) {
            patients.remove(patientToRemove);
            users.remove(patientToRemove);
            System.out.println("Patient removed successfully.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void viewDoctors() {
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }

    private static void addDoctor() {
        System.out.println("Enter doctor username:");
        String username = scanner.nextLine();
        System.out.println("Enter doctor password:");
        String password = scanner.nextLine();
        System.out.println("Enter doctor's specialization:");
        String specialization = scanner.nextLine();
        Doctor doctor = new Doctor(username, password, specialization);
        doctors.add(doctor);
        users.add(doctor);
        System.out.println("Doctor added successfully.");
    }

    private static void removeDoctor() {
        System.out.println("Enter doctor username to remove:");
        String username = scanner.nextLine();
        Doctor doctorToRemove = null;
        for (Doctor doctor : doctors) {
            if (doctor.getUsername().equals(username)) {
                doctorToRemove = doctor;
                break;
            }
        }
        if (doctorToRemove != null) {
            doctors.remove(doctorToRemove);
            users.remove(doctorToRemove);
            System.out.println("Doctor removed successfully.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private static void viewAppointments() {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    private static void viewAppointments(Doctor doctor) {
        for (Appointment appointment : appointments) {
            if (appointment.getDoctor().equals(doctor)) {
                System.out.println(appointment);
            }
        }
    }

    private static void viewAppointments(Patient patient) {
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().equals(patient)) {
                System.out.println(appointment);
            }
        }
    }

    private static void scheduleAppointment() {
        System.out.println("Enter patient username:");
        String patientUsername = scanner.nextLine();
        Patient patient = null;
        for (Patient p : patients) {
            if (p.getUsername().equals(patientUsername)) {
                patient = p;
                break;
            }
        }
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("Enter doctor username:");
        String doctorUsername = scanner.nextLine();
        Doctor doctor = null;
        for (Doctor d : doctors) {
            if (d.getUsername().equals(doctorUsername)) {
                doctor = d;
                break;
            }
        }
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.println("Enter appointment date (YYYY-MM-DD):");
        String date = scanner.nextLine();

        Appointment appointment = new Appointment(patient, doctor, date);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully.");
    }

    private static void scheduleAppointment(Patient patient) {
        System.out.println("Enter doctor username:");
        String doctorUsername = scanner.nextLine();
        Doctor doctor = null;
        for (Doctor d : doctors) {
            if (d.getUsername().equals(doctorUsername)) {
                doctor = d;
                break;
            }
        }
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.println("Enter appointment date (YYYY-MM-DD):");
        String date = scanner.nextLine();

        Appointment appointment = new Appointment(patient, doctor, date);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully.");
    }

    private static void cancelAppointment() {
        System.out.println("Enter appointment ID to cancel:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Appointment appointmentToRemove = null;
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) {
                appointmentToRemove = appointment;
                break;
            }
        }
        if (appointmentToRemove != null) {
            appointments.remove(appointmentToRemove);
            System.out.println("Appointment canceled successfully.");
        } else {
            System.out.println("Appointment not found.");
        }
    }

    private static void generateReports() {
        System.out.println("Generating reports...");
        System.out.println("Total patients: " + patients.size());
        System.out.println("Total doctors: " + doctors.size());
        System.out.println("Total appointments: " + appointments.size());
    }

    private static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous users found.");
        }
    }

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadPatients() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATIENTS_FILE))) {
            patients = (List<Patient>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous patients found.");
        }
    }

    private static void savePatients() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATIENTS_FILE))) {
            oos.writeObject(patients);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadDoctors() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DOCTORS_FILE))) {
            doctors = (List<Doctor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous doctors found.");
        }
    }

    private static void saveDoctors() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DOCTORS_FILE))) {
            oos.writeObject(doctors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadAppointments() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(APPOINTMENTS_FILE))) {
            appointments = (List<Appointment>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous appointments found.");
        }
    }

    private static void saveAppointments() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(APPOINTMENTS_FILE))) {
            oos.writeObject(appointments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "'}";
    }
}

class Admin extends User {
    private static final long serialVersionUID = 1L;

    public Admin(String username, String password) {
        super(username, password);
    }
}

class Patient extends User {
    private static final long serialVersionUID = 1L;

    public Patient(String username, String password) {
        super(username, password);
    }
}

class Doctor extends User {
    private static final long serialVersionUID = 1L;
    private String specialization;

    public Doctor(String username, String password, String specialization) {
        super(username, password);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor{username='" + getUsername() + "', specialization='" + specialization + "'}";
    }
}

class Appointment implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int count = 0;
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(Patient patient, Doctor doctor, String date) {
        this.id = ++count;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Appointment{id=" + id + ", patient=" + patient.getUsername() + ", doctor=" + doctor.getUsername() + ", date='" + date + "'}";
    }
}
