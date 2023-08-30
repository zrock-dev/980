package com.fake_orgasm.flights_management.data_handler.schemas;

/**
 * This interface is for classes that represent a schema of a database document.
 */
public interface Schemable {

    /**
     * This is the method returns the name of the field.
     *
     * @return file name.
     */
    String getField();
}