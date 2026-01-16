package hibernateCURD;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class mainClass {

    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure().addAnnotatedClass(student.class);
        SessionFactory sf = cfg.buildSessionFactory();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- STUDENT MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Marks");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            if (choice == 5) break;

            Session ss = sf.openSession();
            Transaction tr = ss.beginTransaction();

            try {
                switch (choice) {
                    case 1: // CREATE
                        System.out.println("Enter Id, Name, and Marks:");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Clear buffer
                        String name = scanner.nextLine();
                        int marks = scanner.nextInt();
                        ss.save(new student(id, name, marks));
                        System.out.println("Saved!");
                        break;

                    case 2: // READ
                        System.out.println("Enter ID to view:");
                        student sFetch = ss.get(student.class, scanner.nextInt());
                        System.out.println(sFetch != null ? sFetch : "Not Found!");
                        break;

                    case 3: // UPDATE
                        System.out.println("Enter ID to update marks:");
                        student sUpdate = ss.get(student.class, scanner.nextInt());
                        if (sUpdate != null) {
                            System.out.println("Enter new marks:");
                            sUpdate.setMarks(scanner.nextInt());
                            ss.update(sUpdate);
                            System.out.println("Updated!");
                        }
                        break;

                    case 4: // DELETE
                        System.out.println("Enter ID to delete:");
                        student sDelete = ss.get(student.class, scanner.nextInt());
                        if (sDelete != null) {
                            ss.delete(sDelete);
                            System.out.println("Deleted!");
                        }
                        break;
                }
                tr.commit();
            } catch (Exception e) {
                if (tr != null) tr.rollback();
                e.printStackTrace();
            } finally {
                ss.close();
            }
        }
        sf.close();
        scanner.close();
    }
}