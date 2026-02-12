package basic;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Optional 最佳实践与常见用例演示
 *
 * <h2>Optional 核心方法说明</h2>
 * <ul>
 *   <li><b>get()</b> - 获取 value，如果是 null 则抛出 NoSuchElementException，使用前必须调用 isPresent() 判断</li>
 *   <li><b>isPresent()</b> - 判断 value 是否存在</li>
 *   <li><b>isEmpty()</b> - Java 11+ 方法，判断 value 是否为空</li>
 *   <li><b>orElse(T other)</b> - 如果 value 存在则返回，否则返回 other</li>
 *   <li><b>orElseGet(Supplier<? extends T> other)</b> - 如果 value 存在则返回，否则调用 other 以返回值</li>
 *   <li><b>orElseThrow()</b> - 如果 value 存在则返回，否则抛出 NoSuchElementException</li>
 *   <li><b>orElseThrow(Supplier<? extends X> exceptionSupplier)</b> - 如果 value 存在则返回，否则抛出指定异常</li>
 *   <li><b>map(Function<? super T,? extends U> mapper)</b> - 对值进行转换</li>
 *   <li><b>flatMap(Function<? super T,Optional<U>> mapper)</b> - 对值进行转换并展平</li>
 *   <li><b>filter(Predicate<? super T> predicate)</b> - 过滤值</li>
 * </ul>
 *
 * <h2>Optional 使用建议</h2>
 * <ul>
 *   <li><b>推荐使用场景</b>：
 *     <ul>
 *       <li>函数返回值，提醒调用者对 null 的处理</li>
 *       <li>连续调用链，减少 if(obj != null) 的判断</li>
 *       <li>集合操作中的空值处理</li>
 *     </ul>
 *   </li>
 *   <li><b>不推荐使用场景</b>：
 *     <ul>
 *       <li>字段声明（Optional 不能被序列化）</li>
 *       <li>方法参数（增加调用复杂度）</li>
 *       <li>在使用 isPresent() 后接着使用 get()（与 == 判断无异）</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * @author samin
 * @date 2022-08-18
 * @version 2.0
 */
public class OptionalUseCase {

    public static void main(String[] args) {
        System.out.println("=== Optional 最佳实践演示 ===\n");

        // 演示常见的空指针错误场景
        demonstrateNullPointerRisk();

        // 演示传统修复策略的代码臃肿问题
        demonstrateTraditionalNullCheck();

        // 演示 Optional 链式调用最佳实践
        demonstrateOptionalChain();

        // 演示 Map 数据结构中的 Optional 使用
        demonstrateMapOptionalUsage();

        // 演示更多 Optional 高级用法
        demonstrateAdvancedOptionalFeatures();

        // 演示集合操作中的 Optional 使用
        demonstrateCollectionOptional();

        // 演示完整 vs 不完整组织结构的 Optional 处理
        demonstrateOrganizationScenarios();
    }

    /**
     * 演示常见的空指针错误场景
     */
    private static void demonstrateNullPointerRisk() {
        System.out.println("1. 常见空指针错误场景演示：");
        System.out.println("   直接调用 getCompanyFromEmployee() 会抛出 NullPointerException");
        System.out.println("   因为 Employee -> Team -> Department -> Company 链中存在 null 值\n");
    }

    /**
     * 演示传统修复策略的代码臃肿问题
     */
    private static void demonstrateTraditionalNullCheck() {
        System.out.println("2. 传统 null 检查修复策略：");

        // 传统方式：多层嵌套 null 检查
        Employee employee = getEmployee();
        String companyName = "Unknown Company";

        if (employee != null) {
            Team team = employee.getTeam();
            if (team != null) {
                Department department = team.getDepartment();
                if (department != null) {
                    Company company = department.getCompany();
                    if (company != null) {
                        companyName = company.getName();
                    }
                }
            }
        }

        System.out.println("   传统方式结果: " + companyName);
        System.out.println("   代码臃肿，可读性差\n");
    }

    /**
     * 演示 Optional 链式调用最佳实践
     */
    private static void demonstrateOptionalChain() {
        System.out.println("3. Optional 链式调用最佳实践：");

        // 最佳实践：使用 Optional 链式调用
        String companyName = Optional.ofNullable(getEmployee())
                                     .map(Employee::getTeam)      // 方法引用更简洁
                                     .map(Team::getDepartment)
                                     .map(Department::getCompany)
                                     .map(Company::getName)
                                     .orElse("No Company");

        System.out.println("   Optional 方式结果: " + companyName);
        System.out.println("   代码简洁，可读性强，避免空指针\n");
    }

    /**
     * 演示 Map 数据结构中的 Optional 使用
     */
    private static void demonstrateMapOptionalUsage() {
        System.out.println("4. Map 数据结构中的 Optional 使用：");

        // 不知道是否为 null 的 Map
        Map<String, Map<String, Object>> map = null;

        // 传统方式：多层嵌套 null 检查
        Object traditionalResult = null;
        if (Objects.nonNull(map)) {
            Map<String, Object> tmp = map.get("k1");
            if (Objects.nonNull(tmp)) {
                traditionalResult = tmp.get("kk1");
            }
        }
        if (Objects.isNull(traditionalResult)) {
            traditionalResult = new Object();
        }

        // Optional 方式：简洁优雅
        Object optionalResult = Optional.ofNullable(map)
                                        .map(m -> m.get("k2"))
                                        .map(m -> m.get("kk2"))
                                        .orElseGet(Object::new);

        System.out.println("   传统方式: " + traditionalResult.getClass()
                                                              .getSimpleName());
        System.out.println("   Optional 方式: " + optionalResult.getClass()
                                                                .getSimpleName());
        System.out.println("   orElse vs orElseGet: orElse 总是创建对象，orElseGet 只在需要时创建\n");
    }

    /**
     * 演示更多 Optional 高级用法
     */
    private static void demonstrateAdvancedOptionalFeatures() {
        System.out.println("5. Optional 高级用法演示：");

        // 1. filter 方法：条件过滤
        System.out.println("   a) filter 方法 - 条件过滤：");
        Optional<String> filtered = Optional.of("Hello World")
                                            .filter(s -> s.length() > 5);
        System.out.println("      过滤长度>5的字符串: " + filtered.orElse("不符合条件"));

        // 2. flatMap 方法：展平嵌套 Optional
        System.out.println("   b) flatMap 方法 - 展平嵌套 Optional：");
        Optional<String> flatMapped = Optional.of("test")
                                              .flatMap(s -> Optional.of(s.toUpperCase()));
        System.out.println("      展平操作结果: " + flatMapped.orElse("空"));

        // 3. orElseThrow 方法：自定义异常
        System.out.println("   c) orElseThrow 方法 - 自定义异常：");
        try {
            Optional.empty()
                    .orElseThrow(() -> new IllegalArgumentException("值不能为空"));
        } catch (IllegalArgumentException e) {
            System.out.println("      抛出自定义异常: " + e.getMessage());
        }

        // 4. ifPresent 方法：存在时执行操作
        System.out.println("   d) ifPresent 方法 - 存在时执行操作：");
        Optional.of("存在值")
                .ifPresent(value -> System.out.println("      值存在: " + value));

        // 5. ifPresentOrElse 方法（Java 9+）
        System.out.println("   e) ifPresentOrElse 方法 - 存在/不存在分别处理：");
        Optional.empty()
                .ifPresentOrElse(value -> System.out.println("      值存在: " + value),
                                 () -> System.out.println("      值不存在，执行备用操作"));

        // 6. or 方法（Java 9+）：提供备选 Optional
        System.out.println("   f) or 方法 - 提供备选 Optional：");
        Optional<String> result = Optional.<String>empty()
                                          .or(() -> Optional.of("备选值"));
        System.out.println("      备选 Optional 结果: " + result.get());

        System.out.println();
    }

    // ofNullable 的实现，等于整合了 empty 和 of 方法，所以一般建议用 ofNullable
    // public static <T> Optional<T> ofNullable(T value) {
    //     return value == null ? (Optional<T>) EMPTY
    //                          : new Optional<>(value);
    // }

    /**
     * 演示直接调用链式方法会抛出空指针异常
     * 注意：此方法在实际运行时会被注释掉，仅用于演示风险
     */
    private static void getCompanyFromEmployee() {
        Employee employee = getEmployee();
        // 这里会抛出 NullPointerException，因为 Department 和 Company 为 null
        Company company = employee.getTeam()
                                  .getDepartment()
                                  .getCompany();
        System.out.println(company);
    }

    /**
     * 演示传统多层 null 检查的代码臃肿问题
     */
    private static void getCompanyFromEmployeeTraditional() {
        Employee employee = getEmployee();
        if (employee == null) {
            System.out.println("Employee is null");
            return;
        }
        Team team = employee.getTeam();
        if (team == null) {
            System.out.println("Team is null");
            return;
        }
        Department department = team.getDepartment();
        if (department == null) {
            System.out.println("Department is null");
            return;
        }
        Company company = department.getCompany();
        if (company != null) {
            System.out.println("Company: " + company.getName());
        } else {
            System.out.println("Company is null");
        }
    }

    /**
     * 获取测试用的 Employee 对象
     * 注意：Department 和 Company 为 null，模拟真实场景
     */
    private static Employee getEmployee() {
        Employee employee = new Employee();
        employee.setName("Samin");
        employee.setTeam(new Team("Dev"));
        // 不设置 Department 和 Company，模拟可能为 null 的场景
        return employee;
    }

    /**
     * 返回 Optional 包装的 Employee，替代直接返回 null
     * 这是 Optional 的最佳实践用法
     */
    private static Optional<Employee> getEmployeeOptional() {
        // 模拟有时返回空值的情况
        return Math.random() > 0.5 ? Optional.of(new Employee()) : Optional.empty();
    }

    /**
     * 演示 Optional 在集合操作中的使用
     */
    private static void demonstrateCollectionOptional() {
        System.out.println("6. 集合操作中的 Optional 使用：");

        // 从列表中查找第一个符合条件的元素
        java.util.List<String> names = java.util.Arrays.asList("Alice", "Bob", "Charlie");

        Optional<String> found = names.stream()
                                      .filter(name -> name.startsWith("C"))
                                      .findFirst();

        System.out.println("   查找以'C'开头的名字: " + found.map(String::toUpperCase)
                                                             .orElse("未找到"));

        // 使用 Optional 处理可能为空的集合
        java.util.List<String> emptyList = null;
        int size = Optional.ofNullable(emptyList)
                           .map(List::size)
                           .orElse(0);

        System.out.println("   空集合大小: " + size);
        System.out.println();
    }

    /**
     * 演示完整 vs 不完整组织结构的 Optional 处理
     */
    private static void demonstrateOrganizationScenarios() {
        System.out.println("7. 完整 vs 不完整组织结构的 Optional 处理：");

        // 场景1：完整组织结构
        Employee completeEmployee = OrganizationBuilder.buildCompleteOrganization("Alice", "Dev Team", "Engineering",
                                                                                  "Tech Corp");

        String completeResult = Optional.ofNullable(completeEmployee)
                                        .map(Employee::getTeam)
                                        .map(Team::getDepartment)
                                        .map(Department::getCompany)
                                        .map(Company::getName)
                                        .orElse("Unknown Company");

        System.out.println("   完整组织结构公司名: " + completeResult);

        // 场景2：不完整组织结构（Department 和 Company 为 null）
        Employee incompleteEmployee = OrganizationBuilder.buildIncompleteOrganization("Bob", "QA Team");

        String incompleteResult = Optional.ofNullable(incompleteEmployee)
                                          .map(Employee::getTeam)
                                          .map(Team::getDepartment)
                                          .map(Department::getCompany)
                                          .map(Company::getName)
                                          .orElse("Unknown Company");

        System.out.println("   不完整组织结构公司名: " + incompleteResult);

        System.out.println("   Optional 优雅处理了 null 值，避免了空指针异常\n");
    }

    /**
     * 员工类
     */
    static class Employee {

        private String name;
        private Team team;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Team getTeam() {
            return team;
        }

        public void setTeam(Team team) {
            this.team = team;
        }

        @Override
        public String toString() {
            return "Employee{name='" + name + "'}";
        }
    }

    /**
     * 团队类
     */
    static class Team {

        private String name;
        private Department department;

        public Team(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        @Override
        public String toString() {
            return "Team{name='" + name + "'}";
        }
    }

    /**
     * 部门类
     */
    static class Department {

        private String name;
        private Company company;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        @Override
        public String toString() {
            return "Department{name='" + name + "'}";
        }
    }

    /**
     * 公司类
     */
    static class Company {

        private String name = "Default Company";

        public Company() {}

        public Company(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Company{name='" + name + "'}";
        }
    }

    /**
     * 工具类：提供创建完整组织结构的便捷方法
     */
    static class OrganizationBuilder {

        /**
         * 创建完整的组织结构（包含所有层级）
         */
        public static Employee buildCompleteOrganization(String employeeName, String teamName, String deptName,
                                                         String companyName) {
            Company company = new Company(companyName);
            Department department = new Department();
            department.setName(deptName);
            department.setCompany(company);
            Team team = new Team(teamName);
            team.setDepartment(department);
            Employee employee = new Employee();
            employee.setName(employeeName);
            employee.setTeam(team);
            return employee;
        }

        /**
         * 创建不完整的组织结构（模拟真实场景中的null值）
         */
        public static Employee buildIncompleteOrganization(String employeeName, String teamName) {
            Team team = new Team(teamName);
            // 不设置 Department 和 Company，模拟可能为null的场景
            Employee employee = new Employee();
            employee.setName(employeeName);
            employee.setTeam(team);
            return employee;
        }
    }
}
