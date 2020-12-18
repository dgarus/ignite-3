package org.apache.ignite.internal.schema.builder;

import java.util.Arrays;
import org.apache.ignite.schema.builder.PrimaryKeyBuilder;
import org.apache.ignite.schema.builder.SchemaTableBuilder;

public class PrimaryKeyBuilderImpl implements PrimaryKeyBuilder {
    private final SchemaTableBuilderImpl schemaTableBuilder;

    private String[] columns;

    private String[] affinityColumns;

    public PrimaryKeyBuilderImpl(SchemaTableBuilderImpl schemaTableBuilder) {
        this.schemaTableBuilder = schemaTableBuilder;
    }

    @Override public PrimaryKeyBuilderImpl withColumns(String... columns) {
        this.columns = columns;

        return this;
    }

    @Override public PrimaryKeyBuilderImpl withAffinityColumns(String... affinityColumns) {
        this.affinityColumns = affinityColumns;

        return this;
    }

    @Override public SchemaTableBuilder done() {
       // All column are 'affinify columns' if not specified,
        if (affinityColumns == null)
            affinityColumns = columns;
        else
            assert Arrays.stream(affinityColumns).allMatch(c -> Arrays.asList(columns).contains(c));

        schemaTableBuilder.pkIndex = this;

        return schemaTableBuilder;
    }

    String[] affinityColumns() {
        return affinityColumns;
    }

    String[] columns() {
        return columns;
    }
}
