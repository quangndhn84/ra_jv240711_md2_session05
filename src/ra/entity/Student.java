package ra.entity;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Student {
    //1. Fields/Attributes/Properties
    private String studentId;
    private String studentName;
    private boolean sex;
    private String className;
    private int age;
    private String address;

    //2. Constructors: default, all parameter
    public Student() {
    }

    public Student(String studentId, String studentName, boolean sex, String className, int age, String address) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.className = className;
        this.age = age;
        this.address = address;
    }
    //3. Methods
    //3.1. Getter/Setter

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //3.2. Method Behavior
    public void inputData(Scanner scanner, Student[] arrStudents, int currentIndex) {
        //Input data for Student
        this.studentId = inputStudentId(scanner);
        this.studentName = inputStudentName(scanner, arrStudents, currentIndex);
        this.sex = inputSex(scanner);
        this.className = inputClassName(scanner);
        this.age = inputAge(scanner);
        this.address = inputAddress(scanner);
    }

    public String inputStudentId(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên:");
        do {
            String studentId = scanner.nextLine();
            //validate dữ liệu đầu vào
            String studentIdRegex = "SV[\\w]{3}";
            if (Pattern.matches(studentIdRegex, studentId)) {
                return studentId;
            } else {
                System.err.println("Mã sinh viên không đúng định dạng, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputStudentName(Scanner scanner, Student[] arrStudents, int currentIndex) {
        System.out.println("Nhập vào tên sinh viên:");
        do {
            String studentName = scanner.nextLine();
            if (studentName.length() >= 6 && studentName.length() <= 50) {
                //Check trùng
                boolean isExist = false;//Không trùng
                for (int i = 0; i < currentIndex; i++) {
                    if (arrStudents[i].getStudentName().equals(studentName)) {
                        isExist = true;//Bị trùng
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên sinh viên đã tồn tại, vui lòng nhập lại");
                } else {
                    return studentName;
                }
            } else {
                System.err.println("Tên sinh viên phải có từ 6-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public boolean inputSex(Scanner scanner) {
        System.out.println("Nhâp vào giới tính sinh viên:");
        do {
            String sex = scanner.nextLine();
            if (sex.equals("true") || sex.equals("false")) {
                return Boolean.parseBoolean(sex);
            } else {
                System.err.println("Nhập đúng kiểu dữ liệu, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputClassName(Scanner scanner) {
        System.out.println("Nhập vào tên lớp của sinh viên:");
        do {
            String className = scanner.nextLine();
            if (className != null && !className.trim().equals("")) {
                return className;
            } else {
                System.err.println("Vui lòng nhập tên lớp của sinh viên");
            }
        } while (true);
    }

    public int inputAge(Scanner scanner) {
        System.out.println("Nhập vào tuổi sinh viên:");
        do {
            int age = Integer.parseInt(scanner.nextLine());
            if (age >= 18) {
                return age;
            } else {
                System.err.println("Sinh viên phải có tuổi lớn hơn hoặc bằng 18, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputAddress(Scanner scanner) {
        System.out.println("Nhập vào địa chỉ của sinh viên:");
        do {
            String address = scanner.nextLine();
            if (address != null && !address.trim().equals("")) {
                return address;
            } else {
                System.err.println("Vui lòng nhập địa chỉ của sinh viên");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã SV: %s - Tên SV: %s - Giới tính: %s\n", this.studentId, this.studentName, this.sex ? "Nam" : "Nữ");
        System.out.printf("Tên lớp: %s - Tuổi: %d - Địa chỉ: %s\n", this.className, this.age, this.address);
    }
}
