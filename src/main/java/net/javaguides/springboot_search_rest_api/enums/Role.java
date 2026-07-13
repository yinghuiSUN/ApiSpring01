package net.javaguides.springboot_search_rest_api.enums;

public enum Role {

    //你可以把它脑补成： new Role("EMPLOYEE", DataScope.SELF)
    // enum 对象由 JVM 自动创建   不能手动 new   每个枚举值是单例

    EMPLOYEE(DataScope.SELF),
    LEAD(DataScope.DEPT),
    MANAGER(DataScope.DEPT_AND_CHILD),
    DIRECTOR(DataScope.ALL),
    ADMIN(DataScope.ALL);

    private final DataScope dataScope;

    Role(DataScope dataScope) {
        this.dataScope = dataScope;
    }

    public DataScope getDataScope() {
        return dataScope;
    }
}
