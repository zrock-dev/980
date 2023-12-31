package com.fake_orgasm.flights_management.repository.schemas;

/**
 * This interface is for classes that represent a schema of a database document.
 */
public interface Schemable {

    /**
     * This method returns the name of the field.
     *
     * @return file name.
     */
    String getField();
}
