package enums;

import lombok.Data;

/**
 * Created by Yuraga
 */

public enum UserRoleType {
    ADMIN("ADMIN"),
    USER("USER");

    String type;

    UserRoleType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

    public String getName() {
        return this.name();
    }

    public String getType() {
        return type;
    }}

