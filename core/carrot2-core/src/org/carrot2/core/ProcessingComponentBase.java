
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2008, Dawid Weiss, Stanisław Osiński.
 * Portions (C) Contributors listed in "carrot2.CONTRIBUTORS" file.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.core;

import java.util.concurrent.ExecutorService;

import org.carrot2.util.ExecutorServiceUtils;

/**
 * A base class for implementation of the {@link ProcessingComponent} interface that
 * provides empty implementations of all life cycle methods.
 */
public abstract class ProcessingComponentBase implements ProcessingComponent
{
    private ControllerContext context;

    /*
     * 
     */
    public void init(ControllerContext context)
    {
        this.context = context;
    }

    /*
     * 
     */
    public void beforeProcessing() throws ProcessingException
    {
    }

    /*
     * 
     */
    public void process() throws ProcessingException
    {
    }

    /*
     * 
     */
    public void afterProcessing()
    {
    }

    /**
     * Return the {@link ControllerContext} passed in {@link #init(ControllerContext)}.
     */
    protected final ControllerContext getContext()
    {
        if (context == null)
        {
            throw new IllegalStateException(
                "Context not available (check if you call super.init(ControllerContext)).");
        }

        return context;
    }

    /*
     * 
     */
    protected ExecutorService getSharedExecutor(int maxConcurrentThreads, Class<?> clazz)
    {
        final ControllerContext context = getContext();
        synchronized (context)
        {
            final String contextKey = clazz.getName() + ".executorService";
            ExecutorService service = (ExecutorService) context.getAttribute(contextKey);
            if (service == null)
            {
                service = ExecutorServiceUtils.createExecutorService(maxConcurrentThreads, clazz);
                context.setAttribute(contextKey, service);
                context.addListener(new ExecutorServiceShutdownListener(contextKey));
            }
            return service;
        }
    }

    /*
     * 
     */
    public void dispose()
    {
    }
}