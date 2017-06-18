package com.devact.projects.championsleague.model;

/**
 * @author Srdjan Simidzija
 *
 * Marker interface for autowiring of spring beans.
 */
public interface ModelComponents {

    /*
    * Method <code>substring()</code> takes argument 8 to remove String
    * "package" from full package name
    */
    public static final String ENTITY_PACKAGE = ModelComponents.class.getPackage()
                                                                            .toString()
                                                                            .substring(8);
}
