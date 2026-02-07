public class TestPayroll {
      /* your code */
        public static void main(String[] args) {
        try {
            // Test 1: Create Payroll and add employees
            System.out.println("=== Test 1: Creating Payroll and adding employees ===");
            Payroll payroll = new Payroll();
            System.out.println("Initial size: " + payroll.size());
            
            Employee emp1 = new Employee();
            emp1.name = "Alice";
            emp1.ID = 1001;
            emp1.salary = 50000.0;
            
            Employee emp2 = new Employee();
            emp2.name = "Bob";
            emp2.ID = 1002;
            emp2.salary = 60000.0;
            
            Employee emp3 = new Employee();
            emp3.name = "Charlie";
            emp3.ID = 1003;
            emp3.salary = 55000.0;
            
            payroll.add_employee(emp1);
            payroll.add_employee(emp2);
            payroll.add_employee(emp3);
            
            System.out.println("After adding 3 employees:");
            System.out.println("Size: " + payroll.size());
            System.out.println("Payroll contents:");
            payroll.print();
            
            // Test 2: Find employee
            System.out.println("\n=== Test 2: Finding employees ===");
            int index = payroll.find_employee("Bob");
            System.out.println("Bob found at index: " + index);
            
            // Test 3: Remove employee
            System.out.println("\n=== Test 3: Removing employee ===");
            payroll.remove_employee(1);
            System.out.println("After removing index 1:");
            System.out.println("Size: " + payroll.size());
            payroll.print();
            
            // Test 4: Test exceptions
            System.out.println("\n=== Test 4: Testing exceptions ===");
            try {
                payroll.remove_employee(10);
            } catch (EmployeeIndexException e) {
                System.out.println("Caught EmployeeIndexException: Invalid index");
            }
            
            try {
                payroll.find_employee("Nonexistent");
            } catch (EmployeeNotFoundException e) {
                System.out.println("Caught EmployeeNotFoundException: Employee not found");
            }
            
            // Test 5: Copy payroll
            System.out.println("\n=== Test 5: Copying payroll ===");
            Payroll payroll2 = new Payroll();
            Employee emp4 = new Employee();
            emp4.name = "David";
            emp4.ID = 1004;
            emp4.salary = 65000.0;
            payroll2.add_employee(emp4);
            
            System.out.println("Original payroll2:");
            payroll2.print();
            
            payroll2.copy_payroll(payroll);
            System.out.println("After copying from payroll:");
            payroll2.print();
            
            // Test 6: Add payroll
            System.out.println("\n=== Test 6: Adding payrolls ===");
            Payroll payroll3 = new Payroll();
            Employee emp5 = new Employee();
            emp5.name = "Eve";
            emp5.ID = 1005;
            emp5.salary = 70000.0;
            payroll3.add_employee(emp5);
            
            System.out.println("Before add_payroll:");
            System.out.println("payroll size: " + payroll.size());
            System.out.println("payroll3 size: " + payroll3.size());
            
            payroll.add_payroll(payroll3);
            System.out.println("After add_payroll:");
            System.out.println("payroll size: " + payroll.size());
            payroll.print();
            
            // Test 7: Test array expansion
            System.out.println("\n=== Test 7: Testing array expansion ===");
            Payroll largePayroll = new Payroll();
            for (int i = 0; i < 2000; i++) {
                Employee e = new Employee();
                e.name = "Employee" + i;
                e.ID = 2000 + i;
                e.salary = 40000 + i;
                largePayroll.add_employee(e);
            }
            System.out.println("Large payroll size: " + largePayroll.size());
            
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
