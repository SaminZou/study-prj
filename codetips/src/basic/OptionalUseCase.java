package basic;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Optional 最佳实践 & Optional 用例
 *
 * <p>get() 获取 value，如果是 null 则报 NoSuchElementException，使用之前一定要先调用 isPresent() 判断是否为空
 *
 * <p>isPresent() 判断 value 是否存在
 *
 * <p>orElse(T other) 如果 value 存在（不为 null ）则返回，否则返回 other
 *
 * <p>orElseGet(Supplier<? extends T> other) 如果value存在（不为null）则返回，否则调用other以返回值
 *
 * <p>
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

        /******************************************** Optional 用例 **********************************************/

        // 不建议做的事情
        // 1. 不能被序列化，所以不能用于字段声明
        // 2. 作为入参
        // 3. 不建议在使用 isPresent 后接着使用 get()，那将和用 == 判断无异，不能体现 Optional 的优越性，反而麻烦

        // 最佳实践
        // 1. 建议使用于函数的返回值，提醒调用者对 null 的处理，也使调用者也变得优雅起来
        // 2. 建议使用于连续调用，减少 if(obj != null) 的判断，也不失可读性，可谓优雅

        // 不知道是否为 null
        Map<String, Map<String, Object>> map = null;

        // 不使用 Optional
        Object ret1 = null;

        if (Objects.nonNull(map)) {
            Map<String, Object> tmp = map.get("k1");
            if (Objects.nonNull(tmp)) {
                ret1 = tmp.get("kk1");
            }
        }
        if (Objects.isNull(ret1)) {
            ret1 = new Object();
        }

        // 使用 Optional
        Object ret2 = Optional.ofNullable(map) // Optional<Map<String, Map<String, Object>>>
                .map(kkv -> kkv.get("k2")) // Optional<Map<String, Object>>
                .map(kv -> kv.get("kk2")) // Optional<Object>
                .orElseGet(
                        Object::new); // 或者 orElse(new Object())，所以 orElse 和 orElseGet 的区别在于是否可以使用 lambda 表达式
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
