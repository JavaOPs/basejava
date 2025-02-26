package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SectionOrganization extends Section {

  private static final long serialVersionUID = 1L;

  private List<Organization> organizations;

  public SectionOrganization() {
  }

  public SectionOrganization(Organization... organizations) {
    this(Arrays.asList(organizations));
  }

  public SectionOrganization(List<Organization> organizations) {
    Objects.requireNonNull(organizations, "Organization mustn't be null");
    this.organizations = organizations;
  }

  public List<Organization> getOrganizations() {
    return organizations;
  }

  @Override
  public String toString() {
    return "SectionOrganization{" +
        "organizations=" + organizations +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SectionOrganization that = (SectionOrganization) o;
    return organizations.equals(that.organizations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(organizations);
  }
}
