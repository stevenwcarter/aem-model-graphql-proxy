package com.bounteous.model;

import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.Map;

public class AEMResponsiveGrid extends AEMContainerComponent {

    private int columnCount;
    private Map<String, String> columnClassNames;
    private String gridClassNames;
    private AEMAllowedComponents allowedComponents;

    @GraphQLQuery
    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    @GraphQLQuery
    public Map<String, String> getColumnClassNames() {
        return columnClassNames;
    }

    public void setColumnClassNames(Map<String, String> columnClassNames) {
        this.columnClassNames = columnClassNames;
    }

    @GraphQLQuery
    public String getGridClassNames() {
        return gridClassNames;
    }

    public void setGridClassNames(String gridClassNames) {
        this.gridClassNames = gridClassNames;
    }

    @GraphQLQuery
    public AEMAllowedComponents getAllowedComponents() {
        return allowedComponents;
    }

    public void setAllowedComponents(AEMAllowedComponents allowedComponents) {
        this.allowedComponents = allowedComponents;
    }
}
