public class Job {

    private String companyName;
    private String role;
    private double minPercentage;
    private int maxBacklogs;
    private int minGraduationYear;
    private String[] requiredSkills;

    public Job(String companyName, String role, double minPercentage,
            int maxBacklogs, int minGraduationYear, String[] requiredSkills) {
        this.companyName = companyName;
        this.role = role;
        this.minPercentage = minPercentage;
        this.maxBacklogs = maxBacklogs;
        this.minGraduationYear = minGraduationYear;
        this.requiredSkills = requiredSkills;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public double getMinPercentage() {
        return minPercentage;
    }

    public int getMaxBacklogs() {
        return maxBacklogs;
    }

    public int getMinGraduationYear() {
        return minGraduationYear;
    }

    public String[] getRequiredSkills() {
        return requiredSkills;
    }
}