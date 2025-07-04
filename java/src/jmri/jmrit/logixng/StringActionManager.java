package jmri.jmrit.logixng;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import jmri.Category;

/**
 * Manager for StringActionBean
 *
 * @author Dave Duchamp       Copyright (C) 2007
 * @author Daniel Bergqvist   Copyright (C) 2018
 */
public interface StringActionManager extends BaseManager<MaleStringActionSocket> {

    /**
     * Remember a NamedBean Object created outside the manager.
     * This method creates a MaleStringActionSocket for the action.
     *
     * @param action the bean
     * @return the male socket for this action
     * @throws IllegalArgumentException if the action has an invalid system name
     */
    MaleStringActionSocket registerAction(@Nonnull StringActionBean action)
            throws IllegalArgumentException;

    /**
     * Create a new system name for an StringActionBean.
     * @return a new system name
     */
    String getAutoSystemName();

    FemaleStringActionSocket createFemaleSocket(
            Base parent, FemaleSocketListener listener, String socketName);

    /**
     * Get a set of classes that implements the DigitalAction interface.
     *
     * @return a set of entries with category and class
     */
    Map<Category, List<Class<? extends Base>>> getActionClasses();

    /*.*
     * Add an Action.
     *
     * @param action the action to add
     * @throws IllegalArgumentException if the action has an invalid system name
     */
//    void addAction(Action action)
//            throws IllegalArgumentException;

    /*.*
     * Locate via user name, then system name if needed. Does not create a new
     * one if nothing found
     *
     * @param name User name or system name to match
     * @return null if no match found
     */
//    Action getAction(String name);

//    Action getByUserName(String s);

//    Action getBySystemName(String s);

    /**
     * {@inheritDoc}
     *
     * The sub system prefix for the StringActionManager is
     * {@link #getSystemNamePrefix() } and "SA";
     */
    @Override
    default String getSubSystemNamePrefix() {
        return getSystemNamePrefix() + "SA";
    }

    /**
     * Delete StringAction by removing it from the manager. The Action must first be
     * deactivated so it stops processing.
     *
     * @param x the StringAction to delete
     */
    void deleteStringAction(MaleStringActionSocket x);

}
