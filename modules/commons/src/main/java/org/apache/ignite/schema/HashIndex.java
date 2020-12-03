package org.apache.ignite.schema;

import java.util.Collection;

public interface HashIndex extends TableIndex {
    /**
     * @return Index columns.
     */
    Collection<String> columns();

    // TODO: What about hidden cols?
}
