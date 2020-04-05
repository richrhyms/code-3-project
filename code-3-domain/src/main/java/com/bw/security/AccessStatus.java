package com.bw.security;


public interface AccessStatus {

    boolean hasAccess();

    String reason();

    static AccessStatus denied() {
        return denied("Forbidden");
    }

    static AccessStatus denied(String reason) {
        return new AccessStatus() {
            @Override
            public boolean hasAccess() {
                return false;
            }

            @Override
            public String reason() {
                return reason;
            }
        };
    }

    static AccessStatus allowed() {
        return new AccessStatus() {
            @Override
            public boolean hasAccess() {
                return true;
            }

            @Override
            public String reason() {
                return null;
            }
        };
    }
}
