
/*
 * Carrot2 Project
 * Copyright (C) 2002-2004, Dawid Weiss
 * Portions (C) Contributors listed in carrot2.CONTRIBUTORS file.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the CVS checkout or at:
 * http://www.cs.put.poznan.pl/dweiss/carrot2.LICENSE
 */

package com.stachoodev.carrot.filter.lingo;

import com.stachoodev.carrot.filter.lingo.common.*;

import org.jdom.Element;

import java.text.NumberFormat;

import java.util.*;


/**
 * Helper class for cluster-jdom operations.
 */
public class JDomClusterStructureHelpers {
    /** DOCUMENT ME! */
    private static final NumberFormat numberFormat;

    static {
        numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
    }

    /**
     * Creates a new JDomClusterStructureHelpers object.
     */
    private JDomClusterStructureHelpers() {
    }

    /**
     * @param clusteringContext
     * @param documentList
     */
    public static void addSnippets(
        AbstractClusteringContext clusteringContext, List documentList) {
        for (Iterator i = documentList.iterator(); i.hasNext();) {
            Element document = (Element) i.next();

            String title = document.getChildText("title");
            String snippet = document.getChildText("snippet");

            clusteringContext.addSnippet(new Snippet(document.getAttributeValue(
                        "id"), title, snippet));
        }
    }

    /**
     * @param rootGroup
     * @param cluster
     */
    public static void addToElement(Element rootGroup, Cluster cluster) {
        if (cluster.getScore() == 0) {
            return;
        }

        Element group = new Element("group");

        if (cluster.isOtherTopics()) {
            group.setAttribute("othertopics", "yes");
        }

        Element title = new Element("title");
        String[] labels = cluster.getLabels();

        if (labels != null) {
            for (int k = 0; k < labels.length; k++) {
                Element phrase = new Element("phrase");
                phrase.setText(labels[k]);
                title.addContent(phrase);
            }
        } else {
            Element phrase = new Element("phrase");
            phrase.setText("Group");
            title.addContent(phrase);
        }

        group.addContent(title);

        Snippet[] clusterDocuments = cluster.getSnippets();

        for (int k = 0; k < clusterDocuments.length; k++) {
            Element doc = new Element("document");
            doc.setAttribute("refid", clusterDocuments[k].getSnippetId());
            doc.setAttribute("score",
                numberFormat.format(cluster.getSnippetScore(k)));
            group.addContent(doc);
        }

        Cluster[] clusters = cluster.getClusters();

        for (int i = 0; i < clusters.length; i++) {
            addToElement(group, clusters[i]);
        }

        rootGroup.addContent(group);
    }
}
