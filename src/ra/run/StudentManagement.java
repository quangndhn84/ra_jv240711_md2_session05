package ra.run;

import ra.entity.Student;

import java.util.Scanner;

public class StudentManagement {
    //Khai báo 1 mảng sinh viên để lưu trữ các sinh viên
    Student[] arrStudents = new Student[100];
    //Khai báo chỉ số phần tử chưa chứa dữ liệu bé nhất
    int currentIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Khởi tao đối tượng StudentManagement
        StudentManagement studentManagement = new StudentManagement();
        //In menu và thực hiện các chức năng theo menu
        do {
            System.out.println("******************MENU*********************");
            System.out.println("1. Danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Sắp xếp sinh viên theo tuổi tăng dần");
            System.out.println("6. Tìm kiếm gần đúng sinh viên theo mã hoặc tên");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    studentManagement.displayListStudent();
                    break;
                case 2:
                    studentManagement.inputListStudent(scanner);
                    break;
                case 3:
                    studentManagement.updateStudent(scanner);
                    break;
                case 4:
                    studentManagement.deleteStudent(scanner);
                    break;
                case 5:
                    studentManagement.sortStudentByAgeASC();
                    break;
                case 6:
                    studentManagement.searchStudentByIdOrName(scanner);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-7");
            }
        } while (true);
    }

    public void displayListStudent() {
        for (int i = 0; i < currentIndex; i++) {
            arrStudents[i].displayData();
        }
    }

    public void inputListStudent(Scanner scanner) {
        System.out.println("Nhập vào số sinh viên cần nhập thông tin");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            //Khởi tạo đối tượng sinh viên tại chỉ số currentIndex
            arrStudents[currentIndex] = new Student();
            //Nhập dữ liệu cho đối tượng currentIndex
            arrStudents[currentIndex].inputData(scanner, arrStudents, currentIndex);
            //Tăng currentIndex lên 1 đơn vị
            currentIndex++;
        }
    }

    public void updateStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần cập nhật thông tin:");
        String studentIdUpdate = scanner.nextLine();
        int indexUpdate = getIndexById(studentIdUpdate);
        if (indexUpdate >= 0) {
            //Tồn tại mã sinh viên --> cập nhật
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhật tên sinh viên");
                System.out.println("2. Cập nhật giới tính");
                System.out.println("3. Cập nhật tên lớp");
                System.out.println("4. Cập nhật tuổi");
                System.out.println("5. Cập nhật địa chỉ");
                System.out.println("6. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập vào tên sinh viên cần cập nhật:");
                        arrStudents[indexUpdate].setStudentName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Nhập vào giới tính cần cập nhật:");
                        arrStudents[indexUpdate].setSex(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 3:
                        System.out.println("Nhập vào tên lớp cần cập nhật:");
                        arrStudents[indexUpdate].setClassName(scanner.nextLine());
                        break;
                    case 4:
                        System.out.println("Nhập vào tuổi sinh viên cần cập nhật:");
                        arrStudents[indexUpdate].setAge(Integer.parseInt(scanner.nextLine()));
                        break;
                    case 5:
                        System.out.println("Nhập vào địa chỉ sinh viên cần cập nhật:");
                        arrStudents[indexUpdate].setAddress(scanner.nextLine());
                        break;
                    case 6:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1-6");
                }
            } while (isExit);
        } else {
            System.err.println("Mã sinh viên không tồn tại");
        }
    }

    public int getIndexById(String studentId) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrStudents[i].getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần xóa:");
        String studentIdDelete = scanner.nextLine();
        int indexDelete = getIndexById(studentIdDelete);
        if (indexDelete >= 0) {
            //Thực hiện xóa
            for (int i = indexDelete; i < currentIndex - 1; i++) {
                arrStudents[i] = arrStudents[i + 1];
            }
            arrStudents[currentIndex - 1] = null;
            currentIndex--;
        } else {
            System.err.println("Mã sinh viên không tồn tại");
        }
    }

    public void sortStudentByAgeASC() {
        //Thuật toán gần với thuật toán selection sort
        for (int i = 0; i < currentIndex - 1; i++) {
            for (int j = i + 1; j < currentIndex; j++) {
                if (arrStudents[i].getAge() > arrStudents[j].getAge()) {
                    Student temp = arrStudents[i];
                    arrStudents[i] = arrStudents[j];
                    arrStudents[j] = temp;
                }
            }
        }
    }

    public void searchStudentByIdOrName(Scanner scanner) {
        System.out.println("Nhập vào mã hoặc tên sinh viên cần tìm:");
        String valueSearch = scanner.nextLine();
        int cntStudent = 0;
        System.out.println("Kết quả tìm kiếm:");
        for (int i = 0; i < currentIndex; i++) {
            if (arrStudents[i].getStudentId().toLowerCase().contains(valueSearch.toLowerCase()) || arrStudents[i].getStudentName().toLowerCase().contains(valueSearch.toLowerCase())) {
                arrStudents[i].displayData();
                cntStudent++;
            }
        }
        System.out.printf("Có %d sinh viên phù hợp với yêu cầu tìm kiếm\n", cntStudent);
    }
}
