package com.the3dsandwich.haileyandweiweibackend.configuration;/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */

import org.hibernate.boot.Metadata;
import org.hibernate.boot.model.relational.ColumnOrderingStrategyLegacy;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.temptable.TemporaryTableColumn;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Constraint;
import org.hibernate.mapping.Table;
import org.hibernate.mapping.UserDefinedType;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Configuration
public class LegacyColumnOrderingStrategy extends ColumnOrderingStrategyLegacy implements HibernatePropertiesCustomizer {

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.COLUMN_ORDERING_STRATEGY, this);
    }

    public interface ColumnOrderingStrategy {

        List<Column> orderTableColumns(Table table, Metadata metadata);

        List<Column> orderConstraintColumns(Constraint constraint, Metadata metadata);

        List<Column> orderUserDefinedTypeColumns(UserDefinedType userDefinedType, Metadata metadata);

        void orderTemporaryTableColumns(List<TemporaryTableColumn> temporaryTableColumns, Metadata metadata);

    }

}
