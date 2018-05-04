/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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
package org.graalvm.compiler.nodes;

import org.graalvm.compiler.core.common.type.Stamp;
import org.graalvm.compiler.graph.NodeClass;
import org.graalvm.compiler.graph.spi.Canonicalizable;
import org.graalvm.compiler.nodeinfo.NodeInfo;
import org.graalvm.compiler.nodes.spi.LIRLowerable;
import org.graalvm.compiler.nodes.spi.NodeLIRBuilderTool;

import jdk.vm.ci.meta.TriState;

@NodeInfo
public abstract class UnaryOpLogicNode extends LogicNode implements LIRLowerable, Canonicalizable.Unary<ValueNode> {

    public static final NodeClass<UnaryOpLogicNode> TYPE = NodeClass.create(UnaryOpLogicNode.class);
    @Input protected ValueNode value;

    @Override
    public ValueNode getValue() {
        return value;
    }

    public UnaryOpLogicNode(NodeClass<? extends UnaryOpLogicNode> c, ValueNode value) {
        super(c);
        assert value != null;
        this.value = value;
    }

    @Override
    public void generate(NodeLIRBuilderTool gen) {
    }

    public Stamp getSucceedingStampForValue(boolean negated, Stamp valueStamp) {
        Stamp succStamp = getSucceedingStampForValue(negated);
        if (succStamp != null) {
            succStamp = succStamp.join(valueStamp);
        }
        return succStamp;
    }

    public abstract Stamp getSucceedingStampForValue(boolean negated);

    public abstract TriState tryFold(Stamp valueStamp);
}
