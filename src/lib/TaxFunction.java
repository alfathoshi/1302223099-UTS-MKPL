package lib;

public class TaxFunction {

	// Membuat variabel constant untuk menampung nilai yang tidak jelas kegunaannya
	private static final int BASIC_NON_TAXABLE_INCOME = 54000000;
    private static final int MARRIED_ADDITION = 4500000;
    private static final int CHILD_ADDITION_PER_CHILD = 1500000;
    private static final int MAX_CHILDREN_COUNT = 3;
    private static final double TAX_RATE = 0.05;
	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	// Menganti variabel isMarried dengan isSingle karena kesalahan logic
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isSingle, int numberOfChildren) {
		
		// Melempar error agar fungsi berhenti
		if (numberOfMonthWorking > 12) {
            throw new IllegalArgumentException("Number of working months cannot exceed 12.");
        }

		// Membuat variabel untuk menampung nilai default
        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int nonTaxableIncome = BASIC_NON_TAXABLE_INCOME;

        // Mengecek isSingle, jika tidak maka akan akan ditambah pajaknya
        if (!isSingle) {
            nonTaxableIncome += MARRIED_ADDITION;
        }

        // Menambahkan pajak sesuai dengan jumlah anak
        nonTaxableIncome += Math.min(numberOfChildren, MAX_CHILDREN_COUNT) * CHILD_ADDITION_PER_CHILD;

        // Membuat final variabel untuk kalkulasi total
        int taxableIncome = totalIncome - deductible - nonTaxableIncome;
        int tax = (int) Math.round(TAX_RATE * Math.max(taxableIncome, 0));

        return tax;
			 
	}
	
}
