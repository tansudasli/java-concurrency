package org.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeGeneratorServiceTest {

    static int EMPLOYEE_COUNT = 122;
    static String EMPLOYEE_FIRST = "Ant";

    @Test
    @DisplayName("Test employee count == 122")
    void checkEmployeesCount() {
        assertEquals(EMPLOYEE_COUNT, IEmployeeGeneratorService.create().size());
    }

    @Test
    @DisplayName("Test 1st employee is Ali")
    void checkFirstEmployee() {
        assertEquals(EMPLOYEE_FIRST, IEmployeeGeneratorService.create().stream()
                .findFirst()
                .orElse(null));
    }
}