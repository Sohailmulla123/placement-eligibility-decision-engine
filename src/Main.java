import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    // Safe integer reader – prevents InputMismatchException crashes
    static int readInt(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("  >> Invalid! Enter a whole number.");
                sc.next(); // discard bad token
            }
        }
    }

    // Safe double reader
    static double readDouble(Scanner sc, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("  >> Invalid! Enter a valid number.");
                sc.next();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // ── 1. Candidate Details ──────────────────────────────
        System.out.print("Enter Name: ");
        String name = scanner.next();

        double percentage = readDouble(scanner, "Enter Percentage: ");
        int backlogs = readInt(scanner, "Enter Backlogs: "); // ← was crashing here
        int graduationYear = readInt(scanner, "Enter Graduation Year: ");

        int skillCount = readInt(scanner, "How many skills? ");
        String[] skills = new String[skillCount];
        for (int i = 0; i < skillCount; i++) {
            System.out.print("Skill " + (i + 1) + ": ");
            skills[i] = scanner.next();
        }

        Candidate candidate = new Candidate(name, percentage, backlogs, graduationYear, skills);

        // ── 2. Number of Jobs ─────────────────────────────────
        int jobCount = readInt(scanner, "Number of Jobs: ");
        Job[] jobs = new Job[jobCount];

        // ── 3. Read Every Job ─────────────────────────────────
        for (int i = 0; i < jobCount; i++) {
            System.out.println("\n--- Job " + (i + 1) + " ---");

            System.out.print("Company: ");
            String company = scanner.next();

            System.out.print("Role: ");
            String role = scanner.next();

            double minPct = readDouble(scanner, "Min Percentage: ");
            int maxBack = readInt(scanner, "Max Backlogs: ");
            int reqYear = readInt(scanner, "Required Grad Year: ");

            int reqSkillCount = readInt(scanner, "Required Skill Count: ");
            String[] requiredSkills = new String[reqSkillCount];
            for (int j = 0; j < reqSkillCount; j++) {
                System.out.print("Required Skill " + (j + 1) + ": ");
                requiredSkills[j] = scanner.next();
            }

            jobs[i] = new Job(company, role, minPct, maxBack, reqYear, requiredSkills);
        }
        System.out.println();
        System.out.println("AVAILABLE JOBS");

        for (int i = 0; i < jobs.length; i++) {
            System.out.println(
                    (i + 1) + ". " +
                            jobs[i].getCompanyName() +
                            " - " +
                            jobs[i].getRole());
        }

        // ── 4. Temporary Selection (first job only) ───────────
        int selectedJobNumber = readInt(scanner, "Please select which company you want: ");
        Job selectedJob = jobs[selectedJobNumber - 1];

        // ── 5. Evaluate & Print Result ────────────────────────
        EligibilityChecker checker = new EligibilityChecker();

        System.out.println("\n================================");
        System.out.println("PLACEMENT ELIGIBILITY RESULT");
        System.out.println("================================\n");
        System.out.println("Candidate : " + candidate.getName());
        System.out.println("Company   : " + selectedJob.getCompanyName());
        System.out.println("Role      : " + selectedJob.getRole());
        System.out.println();

        boolean eligible = checker.evaluate(candidate, selectedJob);

        System.out.println(eligible ? "Final Result : ELIGIBLE" : "Final Result : NOT ELIGIBLE");
        System.out.println("\n================================");

        scanner.close();
    }
}