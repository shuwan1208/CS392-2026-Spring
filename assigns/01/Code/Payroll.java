
public class Payroll {
    public static final int INITIAL_MAXIMUM_SIZE = 1024;
    private Employee[] people;
    private int maximum_size;
    private  int current_size;

    public Payroll() {
	/* your code */
    this.maximum_size= INITIAL_MAXIMUM_SIZE;
    this.current_size=0;



    }
    public int size(){
        return current_size;
    }
    public void print(){
        if (current_size == 0) {
            System.out.println("Payroll is empty");
            return;
        }
        else{
            for (int i=0;i<current_size;i++){
                Employee emp=people[i];
                System.out.printf("Name: %s, ID: %d, Salary: %.2f\n",emp.name, emp.ID, emp.salary);


            }
        }

    }
    public void add_employee(Employee newbie) {
	/* your code */
    if (current_size >= maximum_size) {
        expandArray();
    }
    people[current_size] = newbie;
    current_size++;
    }

    private void expandArray(){
        int newSize = maximum_size * 2;
        Employee[] newArray = new Employee[newSize];
        System.arraycopy(people, 0, newArray, 0, current_size);
        people = newArray;
        maximum_size = newSize;
        System.out.println("Expanded array to size: " + maximum_size);

    }

    public void remove_employee(int i) throws EmployeeIndexException {
	/* your code */
    people[i] = people[current_size - 1];
    people[current_size - 1] = null;
    current_size--;

    }
    
    public int find_employee(String name) throws EmployeeNotFoundException {
	/* your code */
    for (int i = 0; i < current_size; i++) {
        if (people[i].name.equals(target_name)) {
            return i;
        }
    }
    throw new EmployeeNotFoundException("Employee with name '" + target_name + "' not found");
    }

    public void copy_payroll(Payroll source) {
	/* your code */
    for (int i = 0; i < current_size; i++) {
        people[i] = null;
    }
    current_size = 0;
    add_payroll(source);
    }

    public void add_payroll(Payroll source) {
	/* your code */
    while (current_size + source.current_size > maximum_size) {
        expandArray();
    }
    for (int i = 0; i < source.current_size; i++) {
        people[current_size] = source.people[i];
        current_size++;
    }
    }


}
