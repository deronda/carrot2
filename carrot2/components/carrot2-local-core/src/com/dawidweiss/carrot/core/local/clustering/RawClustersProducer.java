
/*
 * Carrot2 Project
 * Copyright (C) 2002-2004, Dawid Weiss
 * Portions (C) Contributors listed in carrot2.CONTRIBUTORS file.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the CVS checkout or at:
 * http://www.cs.put.poznan.pl/dweiss/carrot2.LICENSE
 *
 * Sponsored by: CCG, Inc.
 */

package com.dawidweiss.carrot.core.local.clustering;

/**
 * A marker interface  for components that can  produce {@link RawCluster}
 * objects.
 * 
 * <p>
 * Successor components to this one should implement the corresponding {@link
 * RawClustersConsumer} interface.
 * </p>
 *
 * @author Dawid Weiss
 * @version $Revision$
 *
 * @see RawCluster
 * @see RawClustersConsumer
 * @see com.dawidweiss.carrot.core.local.LocalComponent#getComponentCapabilities()
 * @see com.dawidweiss.carrot.core.local.LocalComponent
 */
public interface RawClustersProducer {
}
