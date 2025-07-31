/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.mysql.cj.exceptions;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.CJException;

public class AssertionFailedException
extends CJException {
    private static final long serialVersionUID = 5832552608575043403L;

    public static AssertionFailedException shouldNotHappen(Exception ex) throws AssertionFailedException {
        throw new AssertionFailedException(ex);
    }

    public static AssertionFailedException shouldNotHappen(String assertion) throws AssertionFailedException {
        return new AssertionFailedException(assertion);
    }

    public AssertionFailedException(Exception ex) {
        super(Messages.getString("AssertionFailedException.0") + ex.toString() + Messages.getString("AssertionFailedException.1"), ex);
    }

    public AssertionFailedException(String assertion) {
        super(Messages.getString("AssertionFailedException.2", new Object[]{assertion}));
    }
}

