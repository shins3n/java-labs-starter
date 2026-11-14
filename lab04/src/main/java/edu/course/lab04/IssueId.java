package edu.course.lab04;

import java.util.Objects;

public final class IssueId {
    private final String projectCode;
    private final int number;

    public IssueId(String projectCode, int number) {
        if (projectCode == null || projectCode.isBlank()) {
            throw new IllegalArgumentException("код проекта не может быть пустым");
        }
        if (number <= 0) {
            throw new IllegalArgumentException("номер должен быть положительным");
        }
        this.projectCode = projectCode;
        this.number = number;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueId issueId = (IssueId) o;
        return number == issueId.number && Objects.equals(projectCode, issueId.projectCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectCode, number);
    }
}