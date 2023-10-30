package basic.q15;

import java.util.Optional;

/**
 * Optional 最佳实践
 *
 * @author samin
 * @date 2022-08-18
 */
public class OptionalUseCase {

    public static void main(String[] args) {
        // 开发常见错误，会出现空指针报错
        // getCompanyFromEmployee();

        // 常见的修复策略，导致代码臃肿可维护性差
        // getCompanyFromEmployee2();

        // 等价 employee = Optional.of(getEmployee()).isPresent() ? getEmployee() : new Employee();
        Employee employee = Optional.of(getEmployee())
                                    .orElse(new Employee());

        // 最佳实践
        employee = getEmployee();
        String companyName = Optional.ofNullable(getEmployee())
                                     .map(ele -> ele.getTeam())
                                     .map(team -> team.getDepartment())
                                     .map(department -> department.getCompany())
                                     .map(company -> company.getName())
                                     .orElse("No Company");
        System.out.println(companyName);
    }

    // ofNullable 的实现，等于整合了 empty 和 of 方法，所以一般建议用 ofNullable
    // public static <T> Optional<T> ofNullable(T value) {
    //     return value == null ? (Optional<T>) EMPTY
    //                          : new Optional<>(value);
    // }

    public static void getCompanyFromEmployee() {
        Employee employee = getEmployee();
        Company company = employee.getTeam()
                                  .getDepartment()
                                  .getCompany();
        System.out.println(company);
    }

    public static void getCompanyFromEmployee2() {
        Employee employee = getEmployee();
        if (employee == null) {
            // do something here...
            return;
        }
        Team team = employee.getTeam();
        if (team == null) {
            // do something here...
            return;
        }
        Department department = team.getDepartment();
        if (department == null) {
            // do something here...
            return;
        }
        Company company = department.getCompany();
        System.out.println(company.getName());
    }

    private static Employee getEmployee() {
        Employee employee = new Employee();
        employee.setName("Samin");
        employee.setTeam(new Team("Dev"));
        return employee;
    }

    private static Optional<Employee> getEmployeeOptional() {
        // 代替返回 null 的场景
        return Optional.empty();
    }

    static class Employee {

        String name;
        Team team;

        public void setName(String name) {
            this.name = name;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

        public Team getTeam() {
            return team;
        }
    }

    static class Team {

        String name;
        Department department;

        public Team(String name) {
            this.name = name;
        }

        public Department getDepartment() {
            return department;
        }
    }

    static class Department {

        String name;
        Company company;

        public Company getCompany() {
            return company;
        }
    }

    static class Company {

        String name = "default";

        public String getName() {
            return name;
        }
    }
}
