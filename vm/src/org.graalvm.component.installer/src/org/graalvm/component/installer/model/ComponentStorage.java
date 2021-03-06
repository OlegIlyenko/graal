/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.graalvm.component.installer.model;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Serialization of {@link ComponentRegistry}.
 */
public interface ComponentStorage {

    /**
     * Deletes component's files.
     * 
     * @param id component ID
     * @throws IOException on load error
     */
    void deleteComponent(String id) throws IOException;

    /**
     * Loads list of components.
     * 
     * @return set of component IDs
     * @throws IOException when I/O fails
     */
    Set<String> listComponentIDs() throws IOException;

    /**
     * Loads component files into its metadata.
     * 
     * @param ci the component metadata
     * @return the modified ComponentInfo
     * @throws IOException on I/O errors
     */
    ComponentInfo loadComponentFiles(ComponentInfo ci) throws IOException;

    /**
     * Deserializes Component's metadata.
     * 
     * @param id component ID
     * @return ComponentInfo instance
     * @throws IOException on I/O errors
     */
    ComponentInfo loadComponentMetadata(String id) throws IOException;

    Map<String, String> loadGraalVersionInfo();

    Map<String, Collection<String>> readReplacedFiles() throws IOException;

    void saveComponent(ComponentInfo info) throws IOException;

    void updateReplacedFiles(Map<String, Collection<String>> replacedFiles) throws IOException;

}
