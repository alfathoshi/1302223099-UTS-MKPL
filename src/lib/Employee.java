package lib;

import java.time.LocalDate;
import java.util.List;

public class Employee {

	// Membuat enum gender sebagai pengganti primitive tipe data boolean
	enum Gender {
		MALE, 
		FEMALE,
	}
	
	// Membuat enum grade untuk menampung nilai konstan salary
	enum Grade {
		grade1(3000000), 
		grade2(5000000), 
		grade3(7000000);
		
		private final int baseSalary;
			Grade(int baseSalary){
			this.baseSalary = baseSalary;
		}
			
		public int getBaseSalary() {
			return baseSalary;
		}
		
	}
	
	// Membuat class Spouse untuk menampung atribut spouseName dan spouseIdNumber
	class Spouse {
		private String name;
		private String idNumber;
		
		public Spouse(String name, String idNumber) {
			super();
			this.name = name;
			this.idNumber = idNumber;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getIdNumber() {
			return idNumber;
		}
		
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}
	}
	
	// Membuat class Child untuk menampung atribut childName dan childIdNumber
	class Child {
		private String name;
		private String idNumber;
		
		public Child(String name, String idNumber) {
			this.name = name;
			this.idNumber = idNumber;
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getIdNumber() {
			return idNumber;
		}
		
		
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}
	}
	
	// Membuat class Salary untuk menampung atribut monthlySalary, otherMonthlyIncome, dan annualDeductive
	 class Salary {
		
		private int monthlySalary;
		private int otherMonthlyIncome;
		private int annualDeductible;
		private Grade grade;
		
		
		public Salary(Grade grade, int otherMonthlyIncome, int annualDeductible) {
			super();
			this.grade = grade;
			this.otherMonthlyIncome = otherMonthlyIncome;
			this.annualDeductible = annualDeductible;
		}
		
		/**
		 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
		 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
		 */
		
		// Membuat lebih ringkas method setMonthlySalary
		public void setMonthlySalary(Grade grade) {	
			int baseSalary = grade.getBaseSalary();
	        salary.monthlySalary = isForeigner ? (int) (baseSalary * 1.5) : baseSalary;
		}
		
		public int getMonthlySalary() {
			return monthlySalary;
		}
		
		public void setAnnualDeductible(int deductible) {	
			salary.annualDeductible = deductible;
		}
		
		public int getAnnualDeductible() {
			return annualDeductible;
		}
		
		// Memperbaiki nama setter untuk monthlyIncome
		public void setOtherMonthlyIncome(int income) {
			salary.otherMonthlyIncome = income;
		}
		
		public int getOtherMonthlyIncome() {
			return otherMonthlyIncome;
		}
		
		
	}
	
	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	// Menggunakan tipe data objek Spouse 
	private Spouse spouse;
	
	// Menggunakan tipe data List<Objek> dari Child
	private List<Child> children;
	
	// Menggabungkan 4 atribut year, month, day, dan monthWorking dalam satu atribut
	// Menggabungkan dalam tipe data LocalDate
	private LocalDate joinedDate;
	
	private boolean isForeigner;
	
	// Mengubah tipe data primtive boolean dengan enum
	private Gender gender; 
	
	// Menggunakan tipe data objek Salary
	private Salary salary;
	
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate joinedDate, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.joinedDate = joinedDate;
		this.isForeigner = isForeigner;
		this.gender = gender;
	}
	
	
	public void setSpouse(Spouse spouse) {
		this.spouse = new Spouse(spouse.name, spouse.idNumber);
	}
	
	public void setSalary(Grade grade, int otherMonthlyIncome, int annualDeductive) {
		this.salary = new Salary(grade, otherMonthlyIncome, annualDeductive);
	}
	
	public void addChild(String childName, String childIdNumber) {
        this.children.add(new Child(childName, childIdNumber));
    }
	
	public int getAnnualIncomeTax() {
        int monthsWorked = calculateMonthsWorkedInCurrentYear();
        
        // Menyederhanakan parameter output dengan pembuatan variabel
        boolean isSingle = spouse == null;
        int numberOfChildren = children.size();

        return TaxFunction.calculateTax(
                salary.monthlySalary,
                salary.otherMonthlyIncome,
                monthsWorked,
                salary.annualDeductible,
                isSingle,
                numberOfChildren
        );
    }
	
	// Memisahkan method untuk menghitung lama waktu bekerja dalam bulan
	private int calculateMonthsWorkedInCurrentYear() {
        LocalDate today = LocalDate.now();
        if (joinedDate.getYear() == today.getYear()) {
            return today.getMonthValue() - joinedDate.getMonthValue() + 1;
        } else {
            return 12;
        }
    }
	
	
}
